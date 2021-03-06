/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.j2cl.ast.visitors;

import com.google.j2cl.ast.AbstractRewriter;
import com.google.j2cl.ast.AstUtils;
import com.google.j2cl.ast.CompilationUnit;
import com.google.j2cl.ast.DeclaredTypeDescriptor;
import com.google.j2cl.ast.Expression;
import com.google.j2cl.ast.MethodCall;
import com.google.j2cl.ast.MethodDescriptor;
import com.google.j2cl.ast.SuperReference;
import com.google.j2cl.ast.ThisReference;
import com.google.j2cl.ast.TypeDescriptor;
import com.google.j2cl.ast.TypeDescriptors;
import com.google.j2cl.ast.TypeDescriptors.BootstrapType;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Devirtualizes method calls to instance methods of Object, unboxed types (Boolean, Double, String)
 * and their super classes and super interfaces to corresponding static method calls.
 */
public class DevirtualizeMethodCalls extends NormalizationPass {
  @Override
  public void applyTo(CompilationUnit compilationUnit) {
    compilationUnit.accept(
        new AbstractRewriter() {
          @Override
          public Expression rewriteMethodCall(MethodCall methodCall) {
            MethodDescriptor targetMethodDescriptor = methodCall.getTarget();
            if (!targetMethodDescriptor.isPolymorphic()) {
              return methodCall;
            }
            return devirtualize(methodCall);
          }

          /**
           * Mapping from the TypeDescriptor, whose instance methods should be devirtualized, to the
           * TypeDescriptor that contains the devirtualized static methods that should be dispatched
           * to.
           *
           * <p>The instance methods of unboxed types (Boolean, Double, String) are translated to
           * corresponding static methods that are translated automatically by J2cl. Instance
           * methods of Object and the super classes/interfaces of unboxed types are translated to
           * the trampoline methods which are implemented in corresponding types (Objects, Numbers,
           * etc.).
           */
          private final Map<DeclaredTypeDescriptor, DeclaredTypeDescriptor>
              devirtualizedMethodTargetTypeDescriptorByTypeDescriptor = new LinkedHashMap<>();

          {
            devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.put(
                TypeDescriptors.get().javaLangObject, BootstrapType.OBJECTS.getDescriptor());
            devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.put(
                TypeDescriptors.get().javaLangBoolean, TypeDescriptors.get().javaLangBoolean);
            devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.put(
                TypeDescriptors.get().javaLangDouble, TypeDescriptors.get().javaLangDouble);
            devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.put(
                TypeDescriptors.get().javaLangString, TypeDescriptors.get().javaLangString);
            devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.put(
                TypeDescriptors.get().javaLangNumber, BootstrapType.NUMBERS.getDescriptor());
            devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.put(
                TypeDescriptors.get().javaLangComparable.toRawTypeDescriptor(),
                BootstrapType.COMPARABLES.getDescriptor());
            devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.put(
                TypeDescriptors.get().javaLangCharSequence,
                BootstrapType.CHAR_SEQUENCES.getDescriptor());
          }

          private MethodCall devirtualizeRegularMethodCall(MethodCall methodCall) {
            // Do *not* perform Object method devirtualization. The point with super method calls
            // is to *not* call the default version of the method on the prototype and instead call
            // the specific version of the method in the super class. If we were to perform Object
            // method devirtualization then the resulting routing through Objects.doFoo() would end
            // up calling back onto the version of the method on the prototype (aka the wrong one).
            // Also as an optimization we do not perform devirtualization on 'this' method calls as
            // the trampoline is not necessary.
            if (methodCall.getQualifier() instanceof SuperReference
                || methodCall.getQualifier() instanceof ThisReference) {
              return methodCall;
            }

            TypeDescriptor enclosingTypeDescriptor =
                methodCall.getTarget().getEnclosingTypeDescriptor().toRawTypeDescriptor();
            if (devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.containsKey(
                enclosingTypeDescriptor)) {
              return AstUtils.devirtualizeMethodCall(
                  methodCall,
                  devirtualizedMethodTargetTypeDescriptorByTypeDescriptor.get(
                      enclosingTypeDescriptor));
            }
            return methodCall;
          }

          private MethodCall devirtualizeJsFunctionImplMethodCalls(MethodCall methodCall) {
            DeclaredTypeDescriptor enclosingTypeDescriptor =
                methodCall.getTarget().getEnclosingTypeDescriptor();
            if (methodCall.getTarget().isJsFunction()) {
              // Do not devirtualize the JsFunction method.
              return methodCall;
            }
            return AstUtils.devirtualizeMethodCall(methodCall, enclosingTypeDescriptor);
          }

          private MethodCall devirtualize(MethodCall methodCall) {
            if (methodCall.getTarget().getEnclosingTypeDescriptor().isJsFunctionImplementation()) {
              return devirtualizeJsFunctionImplMethodCalls(methodCall);
            } else {
              return devirtualizeRegularMethodCall(methodCall);
            }
          }
        });
  }

}

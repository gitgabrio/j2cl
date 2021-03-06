"""readable_example build macro

Confirms the JS compilability of some transpiled Java.


Example usage:

# Creates verification target
readable_example(
    srcs = glob(["*.java"]),
)

"""


load("//build_def:j2cl_util.bzl", "J2CL_OPTIMIZED_DEFS")
load("//third_party/java/j2cl:j2cl_library.bzl", "j2cl_library")
load("//tools/build_rules:build_test.bzl", "build_test")

JAVAC_FLAGS = [
  "-source 9",
  "-target 9",
  "-XepDisableAllChecks",
]

def readable_example(
    srcs, native_srcs=[],
    deps=[], js_deps=[], plugins=[], defs=[], test_externs_list=None,
    _declare_legacy_namespace=False):
  """Macro that confirms the JS compilability of some transpiled Java.

  deps are Labels of j2cl_library() rules. NOT labels of
  java_library() rules.

  Args:
    srcs: Source files to make readable output for.
    native_srcs: Foo.native.js files to merge in.
    deps: J2CL libraries referenced by the srcs.
    js_deps: JS libraries referenced by the srcs.
    plugins: APT processors to execute when generating readable output.
    defs: Custom flags to pass to the JavaScript compiler.
    test_externs_list: Custom externs to support build test verification.
    _declare_legacy_namespace: Whether to use legacy namespaces in output.
  """

  # Transpile the Java files.
  j2cl_library(
      name="readable",
      srcs=srcs,
      javacopts=JAVAC_FLAGS,
      native_srcs=native_srcs,
      deps=deps,
      plugins=plugins,
      generate_build_test=False,
      _js_deps=js_deps,
      _readable_source_maps=True,
      _declare_legacy_namespace=_declare_legacy_namespace,
  )

  # Verify compilability of generated JS.
  if not test_externs_list:
    test_externs_list=["//javascript/externs:common"]
  native.js_binary(
      name="readable_binary",
      defs=J2CL_OPTIMIZED_DEFS + [
          "--conformance_config=third_party/java_src/j2cl/transpiler/javatests/com/google/j2cl/transpiler/readable/conformance_proto.txt",
          "--jscomp_warning=conformanceViolations",
          "--jscomp_warning=strictPrimitiveOperators",
          "--summary_detail_level=3",
      ] + defs,
      compiler="//javascript/tools/jscompiler:head",
      externs_list=test_externs_list,
      extra_inputs=["//transpiler/javatests/com/google/j2cl/transpiler/readable:conformance_proto"],
      deps=[":readable"],
  )

  build_test(
      name="readable_build_test",
      targets=["readable_binary"],
  )

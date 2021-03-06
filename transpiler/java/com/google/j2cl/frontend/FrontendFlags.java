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
package com.google.j2cl.frontend;

import com.google.common.annotations.VisibleForTesting;
import com.google.j2cl.common.J2clUtils;
import com.google.j2cl.common.Problems;
import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/** The set of supported flags. */
public class FrontendFlags {
  @Argument(metaVar = "<source files>", required = true)
  protected List<String> files = new ArrayList<>();

  @Option(
    name = "-classpath",
    aliases = "-cp",
    metaVar = "<path>",
    usage = "Specifies where to find user class files and annotation processors."
  )
  protected String classPath = "";

  @Option(
    name = "-nativesourcepath",
    metaVar = "<path>",
    usage = "Specifies where to find zip files containing native.js files for native methods."
  )
  protected String nativeSourcePath = "";

  @Option(
    name = "-d",
    metaVar = "<path>",
    usage = "Directory or zip into which to place compiled output."
  )
  @VisibleForTesting
  public String output = ".";

  @Option(name = "-help", usage = "print this message")
  protected boolean help = false;

  @Option(
    name = "-readablesourcemaps",
    usage = "Coerces generated source maps to human readable form.",
    hidden = true
  )
  protected boolean readableSourceMaps = false;

  @Option(
    name = "-declarelegacynamespaces",
    usage =
        "Enable goog.module.declareLegacyNamespace() for generated goog.module()."
            + " For Docs during onboarding, do not use.",
    hidden = true
  )
  protected boolean declareLegacyNamespaces = false;

  @Option(
      name = "-generatekytheindexingmetadata",
      usage =
          "Generates Kythe indexing metadata and appends it onto the generated JavaScript files.",
      hidden = true)
  protected boolean generateKytheIndexingMetadata = false;

  /** Parses the given args list and updates values. */
  public static FrontendFlags parse(String[] args, Problems problems) {
    FrontendFlags flags = new FrontendFlags();
    CmdLineParser parser = new CmdLineParser(flags);

    final String usage = "Usage: j2cl <options> <source files>";

    try {
      parser.parseArgument(args);
    } catch (CmdLineException e) {
      if (!flags.help) {
        String message = e.getMessage() + "\n";
        message += usage + "\n";
        message += "use -help for a list of possible options";
        problems.error(message);
        problems.abort();
      }
    }

    if (flags.help) {
      String message = usage + "\n";
      message += "where possible options include:\n";
      message += J2clUtils.streamToString(parser::printUsage);
      problems.info(message);
      problems.abort();
    }

    return flags;
  }
}

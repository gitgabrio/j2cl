# Description:
#   Thirdparty dependencies indirection for bazel.

package(default_visibility = ["//:__subpackages__"])

# Apache2
licenses(["notice"])

java_library(
    name = "guava",
    exports = ["@com_google_guava//jar"],
)

java_library(
    name = "apache_commons_lang3",
    exports = ["@org_apache_commons_lang3//jar"],
)

java_library(
    name = "apache_commons_text",
    exports = ["@org_apache_commons_text//jar"],
)

java_library(
    name = "jsr305_annotations",
    exports = ["@com_google_jsr305//jar"],
)

java_library(
    name = "jscomp",
    exports = ["@com_google_jscomp//jar"],
)

java_library(
    name = "args4j",
    exports = ["@args4j//jar"],
)

java_library(
    name = "jdt-core",
    exports = [
        "@org_eclipse_jdt_compiler_apt//jar",
        "@org_eclipse_jdt_content_type//jar",
        "@org_eclipse_jdt_core//jar",
        "@org_eclipse_jdt_equinox_common//jar",
        "@org_eclipse_jdt_equinox_preferences//jar",
        "@org_eclipse_jdt_jobs//jar",
        "@org_eclipse_jdt_osgi//jar",
        "@org_eclipse_jdt_resources//jar",
        "@org_eclipse_jdt_runtime//jar",
        "@org_eclipse_jdt_text//jar",
    ],
)

java_plugin(
    name = "auto_annotation_plugin",
    processor_class = "com.google.auto.value.processor.AutoAnnotationProcessor",
    deps = ["@com_google_auto_value//jar"],
)

java_plugin(
    name = "auto_value_plugin",
    processor_class = "com.google.auto.value.processor.AutoValueProcessor",
    deps = ["@com_google_auto_value//jar"],
)

java_library(
    name = "auto_value",
    exported_plugins = [
        ":auto_annotation_plugin",
        ":auto_value_plugin",
    ],
    exports = ["@com_google_auto_value//jar"],
)

java_library(
    name = "auto_service",
    exports = ["@com_google_auto_service//jar"],
)

java_library(
    name = "auto_common",
    exports = ["@com_google_auto_common//jar"],
)

java_library(
    name = "gson",
    exports = ["@com_google_gson//jar"],
)

java_library(
    name = "jakarta_velocity",
    exports = [
        "@org_apache_commons_collections//jar",
        "@org_apache_commons_lang2//jar",
        "@org_apache_velocity//jar",
    ],
)

java_library(
    name = "junit",
    testonly = 1,
    exports = ["@org_junit//jar"],
)

java_library(
    name = "mockito",
    testonly = 1,
    exports = ["@org_mockito//jar"],
)

java_library(
    name = "truth",
    testonly = 1,
    exports = ["@com_google_truth//jar"],
)

java_library(
    name = "compile_testing",
    testonly = 1,
    exports = ["@com_google_testing_compile//jar"],
)

alias(
    name = "closure_testcase",
    actual = "@com_google_javascript_closure_library//:testcase",
)

alias(
    name = "closure_reflect",
    actual = "@com_google_javascript_closure_library//:reflect",
)

alias(
    name = "closure_math_long",
    actual = "@com_google_javascript_closure_library//:math_long",
)

alias(
    name = "closure_base",
    actual = "@com_google_javascript_closure_library//:base",
)

alias(
    name = "gwt_java_emul",
    actual = "@org_gwtproject_gwt//user:java_emul",
)

alias(
    name = "gwt_java_emul_test",
    actual = "@org_gwtproject_gwt//user:java_emul_test"
)

alias(
    name = "gwt_java_emul_internal",
    actual = "@org_gwtproject_gwt//user:java_emul_internal",
)

java_library(
    name = "gwt-javaemul-internal-annotations-j2cl",
    exports = ["@org_gwtproject_gwt//user:gwt-javaemul-internal-annotations",]
)

java_library(
    name = "gwt-jsinterop-annotations",
    exports = ["@com_google_jsinterop_annotations//jar"],
)

java_library(
    name = "gwt-jsinterop-annotations-j2cl",
    exports = ["@com_google_jsinterop_annotations//jar"],
)

package(default_visibility = [
    "//tools:__subpackages__",
    "//transpiler:__subpackages__",
])

# Apache2
licenses(["notice"])

java_library(
    name = "frontend",
    srcs = glob(["*.java"]),
    deps = [
        "//third_party:args4j",
        "//third_party:auto_value",
        "//third_party:guava",
        "//third_party:gwt-jsinterop-annotations",
        "//third_party:jdt-core",
        "//third_party:jsr305_annotations",
        "//transpiler/java/com/google/j2cl/ast",
        "//transpiler/java/com/google/j2cl/common",
    ],
)

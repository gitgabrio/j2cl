# Apache2
licenses(["notice"])

load(
    "//transpiler/javatests/com/google/j2cl/transpiler/integration:integration_test.bzl",
    "integration_test",
)

# Creates targets
# blaze build :optimized_js
# blaze test :compiled_test
# blaze test :uncompiled_test
integration_test(
    name = "jsasync",
    srcs = glob(["*.java"]),
    defs = [
        "--allow_method_call_decomposing",
        "--nocollapse_properties",  # TODO(skill): remove once b/69456597 is fixed
    ],
    deps = ["//third_party/java/gwt:gwt-jsinterop-annotations-j2cl"],
)

package(default_visibility = ["//transpiler/javatests/com/google/j2cl/transpiler/sizetest:__pkg__"])

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
    name = "box2d",
    srcs = glob(["*.java"]),
    closure_defines = {
        "jre.checks.checkLevel": "NORMAL",
    },
    # Override integration_test default since we don't want asserts for box2d tracking
    defs = ["--remove_j2cl_asserts=true"],
    deps = [
        "//transpiler/javatests/com/google/j2cl/transpiler/integration/box2d:box2d_j2cl",
    ],
)

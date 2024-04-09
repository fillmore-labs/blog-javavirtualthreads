load("@bazel_tools//tools/jdk:default_java_toolchain.bzl", "BASE_JDK9_JVM_OPTS", "DEFAULT_JAVACOPTS", "DEFAULT_TOOLCHAIN_CONFIGURATION", "default_java_toolchain")
load("@rules_java//java:defs.bzl", "java_library", "java_plugin")

# ---

default_java_toolchain(
    name = "jdk_21",
    configuration = DEFAULT_TOOLCHAIN_CONFIGURATION,
    java_runtime = "@rules_java//toolchains:remotejdk_21",
    javacopts = DEFAULT_JAVACOPTS,
    jvm_opts = BASE_JDK9_JVM_OPTS,
    source_version = "21",
    target_version = "21",
    visibility = ["//visibility:public"],
)

# ---
# https://github.com/openjdk/jmh

java_plugin(
    name = "jmh_processor",
    processor_class = "org.openjdk.jmh.generators.BenchmarkProcessor",
    deps = ["@maven//:org_openjdk_jmh_jmh_generator_annprocess"],
)

java_library(
    name = "jmh",
    exported_plugins = ["jmh_processor"],
    visibility = ["//visibility:public"],
    exports = ["@maven//:org_openjdk_jmh_jmh_core"],
)

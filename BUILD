load("@bazel_tools//tools/jdk:default_java_toolchain.bzl", "BASE_JDK9_JVM_OPTS", "DEFAULT_JAVACOPTS", "DEFAULT_TOOLCHAIN_CONFIGURATION", "default_java_toolchain")
load("@rules_java//java:defs.bzl", "java_binary", "java_library")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "jvt",
    srcs = glob(["src/main/java/com/fillmore_labs/blog/jvt/*.java"]),
)

[
    java_binary(
        name = "try%s" % i,
        srcs = [
            "src/main/java/Try%s.java" % i,
            "src/main/java/com/fillmore_labs/blog/run/Run%s.java" % i,
        ],
        javacopts = ["--enable-preview"],
        jvm_flags = [
            "--enable-preview",
            "-XX:+UnlockExperimentalVMOptions",
            # "-XX:-DoJVMTIVirtualThreadTransitions",
        ],
        main_class = "Try%s" % i,
        deps = [":jvt"],
    )
    for i in range(1, 8)
]

PROFILER_DATA = select({
    "//third_party/async_profiler:supports_profiling": [
        "//third_party/async_profiler",
    ],
    "//conditions:default": [],
})

PROFILER_PATH = select({
    "//third_party/async_profiler:supports_profiling": [
        "-Djava.library.path=$$(dirname $(rootpath //third_party/async_profiler))",
    ],
    "//conditions:default": [],
})

[
    java_binary(
        name = "bench%s" % i,
        srcs = [
            "src/main/java/com/fillmore_labs/blog/benchmark/Bench%s.java" % i,
            "src/main/java/com/fillmore_labs/blog/run/Run%s.java" % i,
        ],
        data = PROFILER_DATA,
        javacopts = ["--enable-preview"] if i > 5 else [],
        jvm_flags = ["--enable-preview"] if i > 5 else [] + [
            "-XX:+UnlockExperimentalVMOptions",
            # "-XX:-DoJVMTIVirtualThreadTransitions",
        ] + PROFILER_PATH,
        main_class = "org.openjdk.jmh.Main",
        deps = [
            ":jvt",
            "//toolchain:jmh",
        ],
    )
    for i in range(1, 7)
]

# ---

default_java_toolchain(
    name = "toolchain_jdk_21",
    configuration = DEFAULT_TOOLCHAIN_CONFIGURATION,
    java_runtime = "@rules_java//toolchains:remotejdk_21",
    javacopts = DEFAULT_JAVACOPTS + ["--enable-preview"],
    jvm_opts = BASE_JDK9_JVM_OPTS + ["--enable-preview"],
    source_version = "21",
    target_version = "21",
    visibility = ["//visibility:public"],
)

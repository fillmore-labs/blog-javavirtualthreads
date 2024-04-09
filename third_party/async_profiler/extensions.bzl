load(":defs.bzl", "async_profiler_repositories")

def _async_profiler_impl(_ctx):
    async_profiler_repositories()

async_profiler = module_extension(
    implementation = _async_profiler_impl,
)

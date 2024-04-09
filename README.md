# Java Virtual Threads

[![Test](https://github.com/fillmore-labs/blog-javavirtualthreads/actions/workflows/test.yml/badge.svg?branch=main)](https://github.com/fillmore-labs/blog-javavirtualthreads/actions/workflows/test.yml)
[![License](https://img.shields.io/github/license/fillmore-labs/blog-javavirtualthreads)](https://www.apache.org/licenses/LICENSE-2.0)

Code for articles about Java virtual threads. See more on the
[Fillmore Labs Blog](https://blog.fillmore-labs.com/posts/javavirtualthreads-1/).

To build these examples, you will need to [install Bazel](http://bazel.io/docs/install.html).

Run the sample by:

```shell
> bazel run //:try1
```

Create a [flame graph](https://brendangregg.com/flamegraphs.html) with:

```shell
> bazel run //:bench1 -- -prof "async:output=flamegraph;direction=forward"
> open "$(bazel info bazel-bin)/bench"1".runfiles/_main/"*"/flame-cpu-forward.html"
```

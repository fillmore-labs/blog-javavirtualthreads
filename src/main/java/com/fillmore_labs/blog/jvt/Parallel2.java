// Copyright 2024 Oliver Eikemeier. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// SPDX-License-Identifier: Apache-2.0

package com.fillmore_labs.blog.jvt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public record Parallel2(ExecutorService e) {
  public int fibonacci(int n) throws ExecutionException, InterruptedException {
    if (n < 2) {
      return n;
    }

    var ff1 = e.submit(() -> fibonacci(n - 1));
    var fn2 = fibonacci(n - 2);

    return ff1.get() + fn2;
  }
}

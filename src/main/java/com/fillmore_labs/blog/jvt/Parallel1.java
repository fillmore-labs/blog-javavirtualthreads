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
import java.util.concurrent.FutureTask;

public final class Parallel1 {
  public static int fibonacci(int n) throws ExecutionException, InterruptedException {
    if (n < 2) {
      return n;
    }

    var ff1 = new FutureTask<>(() -> fibonacci(n - 1));
    Thread.startVirtualThread(ff1);
    var ff2 = new FutureTask<>(() -> fibonacci(n - 2));
    Thread.startVirtualThread(ff2);

    return ff1.get() + ff2.get();
  }
}

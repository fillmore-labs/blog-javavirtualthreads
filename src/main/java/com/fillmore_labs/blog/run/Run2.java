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

package com.fillmore_labs.blog.run;

import static com.fillmore_labs.blog.jvt.Parallel1.fibonacci;

import com.fillmore_labs.blog.jvt.Aggregate;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

public final class Run2 {
  private static final int sequence = 27;

  public static Aggregate.Mean run(int c) throws ExecutionException, InterruptedException {
    var s = new Aggregate();

    for (int i = 0; i < c; i++) {
      var queryStart = Instant.now();

      fibonacci(sequence);
      s.add(Duration.between(queryStart, Instant.now()));
    }

    return s.result();
  }
}

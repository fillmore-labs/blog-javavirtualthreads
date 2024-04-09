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

import static com.fillmore_labs.blog.jvt.Slow2.fibonacci;

import com.fillmore_labs.blog.jvt.Aggregate;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public final class Run6 {
  private static final int sequence = 27;

  public static Result run(int c, Instant deadline) throws InterruptedException {
    var s = new Aggregate();
    var canceled = new AtomicInteger();

    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      for (int i = 0; i < c; i++) {
        var queryStart = Instant.now();

        scope.fork(
            () -> {
              try {
                fibonacci(sequence);
                s.add(Duration.between(queryStart, Instant.now()));
              } catch (InterruptedException _) {
                canceled.getAndIncrement();
              }
              return null;
            });
      }

      scope.joinUntil(deadline);

    } catch (TimeoutException _) {
    }


    return new Result(s.result(), canceled.get());
  }

  public record Result(Aggregate.Mean mean, int canceled) {}
}

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

import static com.fillmore_labs.blog.jvt.Task.task;

import com.fillmore_labs.blog.jvt.TestException;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;

public final class Run7 {
  private static final Duration processingTime = Duration.ofSeconds(1L);

  public static Optional<Throwable> run() throws Exception {
    var deadLine = Instant.now().plus(processingTime.multipliedBy(2));

    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      var duration1 = processingTime.dividedBy(3);
      scope.fork(Executors.callable(() -> task("task1", duration1, null)));

      var duration2 = processingTime.dividedBy(2);
      scope.fork(
          Executors.callable(() -> task("task2", duration2, new TestException("task2 failed"))));

      var duration3 = processingTime;
      scope.fork(Executors.callable(() -> task("task3", duration3, null)));

      scope.joinUntil(deadLine);

      return scope.exception();
    }
  }
}

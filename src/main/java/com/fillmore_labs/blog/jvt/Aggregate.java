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

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Aggregate {
  private final Lock lock = new ReentrantLock();

  private int count;
  private double mean, m2;

  public void update(double value) {
    lock.lock();
    try {
      count++;
      var delta = value - mean;
      mean += delta / count;
      var delta2 = value - mean;
      m2 += delta * delta2;
    } finally {
      lock.unlock();
    }
  }

  public void add(Duration d) {
    update(d.toNanos());
  }

  public Mean result() {
    lock.lock();
    try {
      var stdDev = count > 0 ? Math.sqrt(m2 / count) : 0d;
      return new Mean(count, Duration.ofNanos((long) mean), Duration.ofNanos((long) stdDev));
    } finally {
      lock.unlock();
    }
  }

  public record Mean(int count, Duration avg, Duration dev) {}
}

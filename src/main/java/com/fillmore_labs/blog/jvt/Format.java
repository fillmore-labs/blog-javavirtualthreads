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

public final class Format {
  private static final long NANOS_PER_MICRO = 1_000L;
  private static final long NANOS_PER_MILLI = 1_000_000L;
  private static final long NANOS_PER_SECOND = 1_000_000_000L;

  private Format() {}

  public static String duration(Duration d) {
    var l = d.toNanos();
    if (l >= NANOS_PER_SECOND) {
      return String.format("%.3fs", (double) l / NANOS_PER_SECOND);
    }
    if (l >= NANOS_PER_MILLI) {
      return String.format("%.3fms", (double) l / NANOS_PER_MILLI);
    }
    if (l >= NANOS_PER_MICRO) {
      return String.format("%.3fµs", (double) l / NANOS_PER_MICRO);
    }
    return String.format("%dns", l);
  }
}

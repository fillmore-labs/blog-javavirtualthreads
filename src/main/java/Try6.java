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

import static com.fillmore_labs.blog.run.Run6.run;

import com.fillmore_labs.blog.jvt.Format;
import java.time.Duration;
import java.time.Instant;

final int count = 1_000;
final Duration timeout = Duration.ofMillis(100L);

void main() throws Exception {
  var start = Instant.now();
  var deadline = start.plus(timeout);
  var r = run(count, deadline);

  var duration = Duration.between(start, Instant.now());
  System.out.println(STR."""
  *** Finished \{r.mean().count()} runs (\{r.canceled()} canceled) in \{Format.duration(duration)} \
  - avg \{Format.duration(r.mean().avg())}, stddev \{Format.duration(r.mean().dev())}""");
}

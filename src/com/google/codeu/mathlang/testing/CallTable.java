// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.mathlang.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// CALL TABLE
//
// This class is used to verify that specific methods within a target class
// have been called with the expected arguments in a specific order. For a given
// target class, a subclass must be defined that overrides each method of the
// target class with a method that writes to the call table. The call table
// can then be checked to see if the expected calls were made.
public final class CallTable {

  public interface Entry {
    Entry and(Object value);
  };

  private final Queue<List<Object>> entries = new LinkedList<>();

  // NEW ENTRY
  //
  // Create a new entry in the call table. This method returns an |Entry| which
  // holds information about the call.
  public Entry newEntry() {
    final List<Object> entry = new ArrayList<>();
    entries.add(entry);

    return new Entry() {
      @Override
      public Entry and(Object value) {
        entry.add(value);
        return this;
      }
    };
  }

  // ASSERT NEXT
  //
  // Check if the next call in the history matches what is given. If the entry
  // does not match exactly, throw an exception.
  public void assertNext(Object... expected) throws Exception {
    final Object[] actual = entries.size() > 0 ?
                            entries.remove().toArray() :
                            null;

    if (!Arrays.equals(actual, expected)) {
      throw new Exception(String.format(
          "Expected %s but got %s", Arrays.toString(expected), Arrays.toString(actual)));
    }
  }

  // ASSERT END
  //
  // Check that all calls have been consumed via calls to |assertNext|. If
  // any calls have not been consumed via |assertNext|, throw an exception.
  public void assertEnd() throws Exception {
    if (entries.size() > 0) {
      throw new Exception(String.format(
          "Expected all calls to have completed, but there are still %d unchecked calls.",
          entries.size()));
    }
  }
}

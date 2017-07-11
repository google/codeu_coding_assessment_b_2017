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

import java.util.Collection;

import com.google.codeu.mathlang.functions.NoteFunction;
import com.google.codeu.mathlang.testing.CallTable;

// TEST NOTE FUNCTION
//
// Extend the Note Function and add entries to the call table
// so that Note Function calls can be validated.
public final class TestNoteFunction extends NoteFunction {

  private final CallTable table;

  public TestNoteFunction(CallTable table) {
    this.table = table;
  }

  @Override
  protected void onNoteBody(Collection<String> segments) {
    // Add the call to the call table.
    final CallTable.Entry entry = table.newEntry();
    entry.and("note");
    for (final String segment : segments) {
      entry.and(segment);
    }

    super.onNoteBody(segments);
  }
}

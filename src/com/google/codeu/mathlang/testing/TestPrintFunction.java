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
import java.util.Map;

import com.google.codeu.mathlang.core.Variable;
import com.google.codeu.mathlang.functions.PrintFunction;
import com.google.codeu.mathlang.testing.CallTable;

// TEST PRINT FUNCTION
//
// Extend the Print Function and add entries to the call table
// so that Print Function calls can be validated.
public final class TestPrintFunction extends PrintFunction {

  private final CallTable table;

  public TestPrintFunction(Map<String, Variable> variables, CallTable table) {
    super(variables);
    this.table = table;
  }

  @Override
  protected void printLine(Collection<String> segments) {
    // Add the call to the call table.
    final CallTable.Entry entry = table.newEntry();
    entry.and("print");
    for (final String segment : segments) {
      entry.and(segment);
    }

    super.printLine(segments);
  }
}

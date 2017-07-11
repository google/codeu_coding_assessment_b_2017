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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.codeu.mathlang.core.Function;
import com.google.codeu.mathlang.core.State;
import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.core.Variable;
import com.google.codeu.mathlang.impl.MyTokenReader;
import com.google.codeu.mathlang.parsing.StatementReader;
import com.google.codeu.mathlang.parsing.TokenReader;
import com.google.codeu.mathlang.testing.CallTable;
import com.google.codeu.mathlang.testing.TestLetFunction;
import com.google.codeu.mathlang.testing.TestNoteFunction;
import com.google.codeu.mathlang.testing.TestPrintFunction;

// TESTER
//
// This class sets up the environment and runs a test. The Tester
// should isolate all failures so that one test does not affect another.
//
// When initializing a tester, a Test Listener must be provided so that
// the tester can signal which tests pass and which tests fail.
public final class Tester {

  private final TestListener listener;

  public Tester(TestListener listener) {
    this.listener = listener;
  }

  // TEST
  //
  // Given sample input and a test criteria, run the input through the
  // parser and interpeter. Once execution is complete, run the test
  // criteria to ensure that the execution met the expectations of the
  // test.
  public void test(String name, String input, TestCriteria criteria) {
    listener.onTestStart(name);
    try {
      runTest(input, criteria);
      listener.onPass();
    } catch (Exception ex) {
      listener.onFail(ex);
    }
  }

  private static void runTest(String input, TestCriteria criteria) throws Exception {
    final CallTable callTable = new CallTable();

    final Map<String, Variable> variables = new HashMap<>();
    final State state = new State();
    state.defineFunction("let", new TestLetFunction(variables, callTable));
    state.defineFunction("print", new TestPrintFunction(variables, callTable));
    state.defineFunction("note", new TestNoteFunction(callTable));

    final TokenReader tr = new MyTokenReader(input);
    final StatementReader sr = new StatementReader(tr);

    for (Token[] statement = sr.next(); statement != null; statement = sr.next()) {
      state.run(statement);
    }

    criteria.onTestEnd(callTable);
  }
}

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

package com.google.codeu.mathlang;

import com.google.codeu.mathlang.functions.operations.AddOperation;
import com.google.codeu.mathlang.functions.operations.Operation;
import com.google.codeu.mathlang.functions.operations.SubtractOperation;
import com.google.codeu.mathlang.testing.CallTable;
import com.google.codeu.mathlang.testing.TestCriteria;
import com.google.codeu.mathlang.testing.TestListener;
import com.google.codeu.mathlang.testing.Tester;

final class Test {

  public static void main(String[] args) {

    final Tester tester = new Tester(new TestListener() {

      @Override
      public void onTestStart(String testName) {
        System.out.print("RUNNING: ");
        System.out.print(testName);
        System.out.println(":");
      }

      @Override
      public void onPass() {
        System.out.println("    PASS");
      }

      @Override
      public void onFail(Exception ex) {
        System.out.print("    FAIL (");
        System.out.print(ex.toString());
        System.out.println(")");
      }
    });

    tester.test(
        "One Comment On One Line",
        lines("note \"my comment\";"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("note", "my comment");
            calls.assertEnd();
          }
        });

    tester.test(
        "Two Comments One Line No Space",
        lines("note \"comment 1\"\"comment 2\";"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("note", "comment 1", "comment 2");
            calls.assertEnd();
          }
        });

    tester.test(
        "Two Comments One Line With Space",
        lines("note \"comment 1\" \"comment 2\";"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("note", "comment 1", "comment 2");
            calls.assertEnd();
          }
        });

    tester.test(
        "Two Comments Two Lines",
        lines("note \"comment 1\"",
              "     \"comment 2\";"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("note", "comment 1", "comment 2");
            calls.assertEnd();
          }
        });

    tester.test(
        "Print One String",
        lines("print \"hello\";"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("print", "hello");
            calls.assertEnd();
          }
        });

    tester.test(
        "Print Two Strings",
        lines("print \"hello\" \"world\";"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("print", "hello", "world");
            calls.assertEnd();
          }
        });

    tester.test(
        "Print On Different Lines",
        lines("print \"hello\";",
              "print \"world\";"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("print", "hello");
            calls.assertNext("print", "world");
            calls.assertEnd();
          }
        });

    tester.test(
        "Assign variable as constant",
        lines("let x = 5;"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("let", new AddOperation(5));
            calls.assertEnd();
          }
        });

    tester.test(
        "Adding two constant",
        lines("let x = 5 + 3;",
              "print x;"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("let", new AddOperation(5), new AddOperation(3));
            calls.assertNext("print", "8.0"); // use print to verify result
            calls.assertEnd();
          }
        });

    tester.test(
        "Subtract two constant",
        lines("let x = 5 - 3;",
              "print x;"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("let", new AddOperation(5), new SubtractOperation(3));
            calls.assertNext("print", "2.0"); // use print to verify result
            calls.assertEnd();
          }
        });

    tester.test(
        "Add two variables",
        lines("let x = 5;",
              "let y = 3;",
              "let z = x + y;",
              "print z;"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("let", new AddOperation(5));
            calls.assertNext("let", new AddOperation(3));
            calls.assertNext("let", new AddOperation(5), new AddOperation(3));
            calls.assertNext("print", "8.0"); // use print to verify result
            calls.assertEnd();
          }
        });

    tester.test(
        "Subtract two variables",
        lines("let x = 5;",
              "let y = 3;",
              "let z = x - y;",
              "print z;"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("let", new AddOperation(5));
            calls.assertNext("let", new AddOperation(3));
            calls.assertNext("let", new AddOperation(5), new SubtractOperation(3));
            calls.assertNext("print", "2.0"); // use print to verify result
            calls.assertEnd();
          }
        });

    tester.test(
        "Add negative constant to variable",
        lines("let x = 5;",
              "let y = x + -3;",
              "print y;"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("let", new AddOperation(5));
            calls.assertNext("let", new AddOperation(5), new AddOperation(-3));
            calls.assertNext("print", "2.0"); // use print to verify result
            calls.assertEnd();
          }
        });

    tester.test(
        "Math with no spaces",
        lines("let x=5+3;",
              "print x;"),
        new TestCriteria() {
          @Override
          public void onTestEnd(CallTable calls) throws Exception {
            calls.assertNext("let", new AddOperation(5), new AddOperation(3));
            calls.assertNext("print", "8.0"); // use print to verify result
            calls.assertEnd();
          }
        });
  }

  private static String lines(String... lines) {

    final StringBuilder builder = new StringBuilder();

    for (final String line : lines) {
      builder.append(line).append("\n");
    }

    // Remove the trailing new line before returning.
    return builder.substring(0, Math.max(0, builder.length() - 1));
  }
}

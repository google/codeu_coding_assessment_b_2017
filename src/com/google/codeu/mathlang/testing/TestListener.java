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

// TEST LISTENER
//
// This interface defines the callbacks that |Tester| will use to communicate
// events that happen while running through tests.
public interface TestListener {

  // ON TEST START
  //
  // Called when a test first starts.
  void onTestStart(String testName);

  // ON PASS
  //
  // Called when a test completed successfully.
  void onPass();

  // ON FAIL
  //
  // Called when a test fails to complete successfully. The exception
  // passed to the method is the original exception thrown during
  // execution.
  void onFail(Exception ex);
}

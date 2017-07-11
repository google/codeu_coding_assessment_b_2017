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

package com.google.codeu.mathlang.functions.operations;

public interface Operation {

  // APPLY TO
  //
  // Apply the operation to the value x and return the result.
  double applyTo(double x);

  // TO STRING
  //
  // All operations must supply a |toString| implementation so
  // operations can be printed when debugging.
  String toString();

  // HASH CODE
  //
  // All operations must implement hashCode to ensure that they can work
  // well with hash-based data structures.
  int hashCode();

  // EQUALS
  //
  // Check for euality with other objects. All operations must implement
  // this to make working with them easier.
  boolean equals(Object other);
}

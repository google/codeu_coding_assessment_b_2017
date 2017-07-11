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

package com.google.codeu.mathlang.core.tokens;

// TOKEN
//
// This is the common interface for all token types. A token is a
// block of information parsed from the input.
public interface Token {

  // TO STRING
  //
  // Require all tokens to supply a |toString| implementation so that
  // tokens can be printed when debugging.
  String toString();

  // HASH CODE
  //
  // All tokens must implement hashCode to ensure that tokens can work
  // well with hash-based data structures.
  int hashCode();

  // EQUALS
  //
  // Check for equality with other objects. All tokens must implement
  // this to make working with tokens easier.
  boolean equals(Object other);
}

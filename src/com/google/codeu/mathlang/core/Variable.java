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

package com.google.codeu.mathlang.core;

// VARIABLE
//
// This class writes the value of a variable. This is done to allow
// a variable to be passed around by reference.
public final class Variable {

  private double value = 0;

  // READ
  //
  // Read the value that this variable holds.
  public double read() {
    return value;
  }

  // WRITE
  //
  // Write a new value to the variable, replacing the previous value held by the variable.
  public void write(double value) {
    this.value = value;
  }
}

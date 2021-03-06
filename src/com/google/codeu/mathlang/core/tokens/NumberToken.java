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

// NUMBER TOKEN
//
// This class represents a floating-point numerical token.
public final class NumberToken implements Token {

  public final double value;

  public NumberToken(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("NUMBER(%f)", value);
  }

  @Override
  public int hashCode() {
    return Double.hashCode(value);
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof NumberToken && equals(this, (NumberToken) other);
  }

  private static boolean equals(NumberToken a, NumberToken b) {
    return a == b || (a != null && b != null && a.value == b.value);
  }
}

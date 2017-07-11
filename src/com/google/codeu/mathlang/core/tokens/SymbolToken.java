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

// SYMBOL TOKEN
//
// This class represents a single character token. The internal value is
// limited to characters that have a special meaning to the parser.
public final class SymbolToken implements Token {

  public final char value;

  public SymbolToken(char value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("SYMBOL(%c)", value);
  }

  @Override
  public int hashCode() {
    return Character.hashCode(value);
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof SymbolToken && equals(this, (SymbolToken) other);
  }

  private static boolean equals(SymbolToken a, SymbolToken b) {
    return a == b || (a != null && b != null && a.value == b.value);
  }
}

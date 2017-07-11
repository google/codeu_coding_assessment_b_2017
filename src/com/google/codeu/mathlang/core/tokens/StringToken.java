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

// STRING TOKEN
//
// This class represents a UTF-8 string token. There are no restrictions
// on what characters can be in it.
public final class StringToken implements Token {

  public final String value;

  public StringToken(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("STRING(%s)", value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof StringToken && equals(this, (StringToken) other);
  }

  private static boolean equals(StringToken a, StringToken b) {
    return a == b || (a != null && b != null && a.value.equals(b.value));
  }
}

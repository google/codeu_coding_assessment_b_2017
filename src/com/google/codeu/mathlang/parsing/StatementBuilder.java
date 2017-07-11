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

package com.google.codeu.mathlang.parsing;

import java.util.ArrayList;
import java.util.Collection;

import com.google.codeu.mathlang.core.tokens.Token;

// STATEMENT BUILDER
//
// The statement builder helps create a series of tokens. It supplies
// an array of tokens when the series ends.
final class StatementBuilder {

  private final Collection<Token> tokens = new ArrayList<>();

  // APPEND
  //
  // Add the token to the end of the series.
  public void append(Token token) {
    tokens.add(token);
  }

  // BUILD
  //
  // Create an array from the series of tokens.
  public Token[] build() {
    final Token[] out = new Token[tokens.size()];
    tokens.toArray(out);
    return out;
  }

  // RESET
  //
  // Reset the internal state of the builder. This should be called
  // before starting to append the tokens of a statement.
  public void reset() {
    tokens.clear();
  }
}

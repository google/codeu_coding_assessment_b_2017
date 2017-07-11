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

package com.google.codeu.mathlang.functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.google.codeu.mathlang.core.Function;
import com.google.codeu.mathlang.core.Variable;
import com.google.codeu.mathlang.core.tokens.NameToken;
import com.google.codeu.mathlang.core.tokens.NumberToken;
import com.google.codeu.mathlang.core.tokens.StringToken;
import com.google.codeu.mathlang.core.tokens.Token;

// PRINT FUNCTION
//
// This function writes out the current value of a token. It can handle
// strings, variables, and numbers. This class is not final so it can
// be overridden in our tests.
public class PrintFunction implements Function {

  // A map of variables that print will use to look up values.
  private final Map<String, Variable> variables;

  public PrintFunction(Map<String, Variable> variables) {
    this.variables = variables;
  }

  @Override
  public void run(Token[] statement) throws Exception {
    final Collection<String> segments = new ArrayList<>();

    for (int i = 1; i < statement.length; i++) {
      segments.add(toString(statement[i]));
    }

    printLine(segments);
  }

  private String toString(Token token) throws Exception {
    if (token instanceof NumberToken) {
      final NumberToken number = (NumberToken) token;
      return Double.toString(number.value);
    }

    if (token instanceof StringToken) {
      final StringToken string = (StringToken) token;
      return string.value;
    }

    if (token instanceof NameToken) {
      final NameToken name = (NameToken) token;
      final Variable v = variables.get(name.value);
      if (v == null) {
        throw new Exception(String.format("Attempting to read value of \"%s\" before assigning it a value.", name.value));
      } else {
        return Double.toString(v.read());
      }
    }

    throw new Exception(String.format("PRINT could not handle %s", token));
  }

  // PRINT LINE
  //
  // This method is responsible for writing out a series of segments. This
  // implementation does not write to anythng. This method is exposed as
  // protected so that it can be overridden in our tests.
  protected void printLine(Collection<String> segments) { }
}

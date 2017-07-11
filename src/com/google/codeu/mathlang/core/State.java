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

import java.util.HashMap;
import java.util.Map;

import com.google.codeu.mathlang.core.tokens.NameToken;
import com.google.codeu.mathlang.core.tokens.Token;

// STATE
//
// This class represents the execution environment for all functions. State
// maintains a list of all functions and routes statements to the correct
// function.
public final class State {

  private final Map<String, Function> functions = new HashMap<>();

  // DEFINE FUNCTION
  //
  // Assign a function to a keyword. This will overwrite any previous assigned
  // function with the same name.
  public void defineFunction(String name, Function function) {
    functions.put(name, function);
  }

  // RUN
  //
  // Find the correct function for the given statement and execute the
  // statement.
  public void run(Token[] statement) throws Exception {

    if (statement.length == 0) {
      // An empty statement should act like a No-op.
      return;
    }

    final Token head = statement[0];

    if (!(head instanceof NameToken)) {
      throw new Exception(String.format("Expecting name token but found %s", head));
    }

    final NameToken name = (NameToken) head;
    final Function function = functions.get(name.value);

    if (function == null) {
      throw new Exception(String.format("Unknown function \"%s\"", name.value));
    }

    function.run(statement);
  }
}

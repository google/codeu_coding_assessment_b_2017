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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.google.codeu.mathlang.core.Function;
import com.google.codeu.mathlang.core.Variable;
import com.google.codeu.mathlang.core.tokens.NameToken;
import com.google.codeu.mathlang.core.tokens.NumberToken;
import com.google.codeu.mathlang.core.tokens.SymbolToken;
import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.functions.operations.AddOperation;
import com.google.codeu.mathlang.functions.operations.Operation;
import com.google.codeu.mathlang.functions.operations.SubtractOperation;

// LET FUNCTION
//
// This function is used to assign values. It expects a syntax similar to the
// following examples:
//   let x = 5;
//   let y = 3 + 5;
//   let z = x + y;
//
// This class is not marked as final so that functions can be overridden in
// the tests.
public class LetFunction implements Function {

  private static final Token EQUAL = new SymbolToken('=');
  private static final Token ADD = new SymbolToken('+');
  private static final Token SUBTRACT = new SymbolToken('-');
  private static final Token NEGATIVE = new SymbolToken('-');

  private final Map<String, Variable> variables;

  public LetFunction(Map<String, Variable> variables) {
    this.variables = variables;
  }

  @Override
  public void run(Token[] statement) throws Exception {

    final Queue<Token> tokens = new LinkedList<>();
    Collections.addAll(tokens, statement);

    tokens.remove(); // the first token is the command name

    if (!(tokens.peek() instanceof NameToken)) {
      throw new Exception(String.format("Expecting variable name but found %s", tokens.peek()));
    }

    final NameToken targetName = (NameToken) tokens.remove();

    final Token assignment = tokens.remove();
    if (!EQUAL.equals(assignment)) {
      throw new Exception(String.format("Expecting %s but found %s", EQUAL, assignment));
    }

    final Collection<Operation> operations = new ArrayList<>();

    operations.add(new AddOperation(readValue(tokens)));

    while (tokens.size() > 0) {
      final Token op = tokens.remove();
      if (ADD.equals(op)) {
        final double value = readValue(tokens);
        operations.add(new AddOperation(value));
      } else if (SUBTRACT.equals(op)) {
        final double value = readValue(tokens);
        operations.add(new SubtractOperation(value));
      } else {
        throw new Exception(String.format("Expecting symbol %s or %s but found %s", ADD, SUBTRACT, op));
      }
    }

    final double finalValue = evaluate(operations);

    // Assign the value to the correct variable. If the variable does not exist, create it.
    // Do not create variables early, since it would then be readable before it is given a
    // value.
    if (variables.get(targetName.value) == null) {
      variables.put(targetName.value, new Variable());
    }
    variables.get(targetName.value).write(finalValue);
  }

  // READ VALUE
  //
  // Read the next value from the top of the Queue. This method needs the queue
  // so that it can handle the presence of a negation.
  private double readValue(Queue<Token> tokens) throws Exception {
    double sign = 1.0;

    if (NEGATIVE.equals(tokens.peek())) {
      sign = -1.0;
      tokens.remove();
    }

    final Token token = tokens.remove();
    double value = 0.0;
    if (token instanceof NumberToken) {
      final NumberToken number = (NumberToken) token;
      value = number.value;
    } else if (token instanceof NameToken) {
      final NameToken name = (NameToken) token;
      final Variable variable = variables.get(name.value);
      if (variable == null) {
        throw new Exception(String.format(
            "Attempting to read value of \"%s\" before assigning value.",
            name.value));
      }
      value = variable.read();
    } else {
      throw new Exception(String.format("Cannot evaluate %s", token));
    }

    return sign * value;
  }

  // EVALUATE
  // Evaluate a collection of operations, reducing them to a single floating-point
  // value. This method is exposed as protected so that it can be overridden
  // in our tests.
  protected double evaluate(Collection<Operation> ops) {
    double runningValue = 0;

    for (final Operation op : ops) {
      runningValue = op.applyTo(runningValue);
    }

    return runningValue;
  }
}

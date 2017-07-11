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

import java.io.IOException;

import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.core.tokens.SymbolToken;

// STATEMENT READER
//
// The statement reader takes a token reader and creates a stream
// of executable statements.
public final class StatementReader {

  private static final Token END_OF_STATEMENT = new SymbolToken(';');

  private final StatementBuilder builder = new StatementBuilder();
  private final TokenReader reader;

  public StatementReader(TokenReader reader) {
    this.reader = reader;
  }

  // NEXT
  //
  // Get the next statement as an array of tokens. When the end of stream is
  // reached (there are no more statements) this method returns null. This
  // method will only return complete statements, if an end of stream is reached
  // before a complete statement is recognized, an IOException will be thrown.
  public Token[] next() throws IOException {
    builder.reset();

    Token next = reader.next();

    // If there is nothing when we start reading, assume that we made it to the
    // end of the stream safely.
    if (next == null) {
      return null;
    }

    while (next != null && !next.equals(END_OF_STATEMENT)) {
      builder.append(next);
      next = reader.next();
    }

    // If next is null here it means that the last statement is incomplete.
    if (next == null) {
      throw new IOException("Reached end of stream while still reading statement.");
    }

    return builder.build();
  }
}

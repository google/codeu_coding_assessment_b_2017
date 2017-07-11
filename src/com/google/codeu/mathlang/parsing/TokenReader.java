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

// TOKEN READER
//
// Defines a stream of tokens.
public interface TokenReader {

  // NEXT
  //
  // Get the next token in the stream. When the end of stream has been reached
  // |next| should return |null|. The only valid tokens that can be returned are:
  //  - com.google.codeu.mathlang.core.tokens.StringToken
  //  - com.google.codeu.mathlang.core.tokens.NameToken
  //  - com.google.codeu.mathlang.core.tokens.SymbolToken
  //  - com.google.codeu.mathlang.core.tokens.NumberToken
  // If there is ever a problem with the source data, |next| should throw an
  // IOException.
  Token next() throws IOException;
}

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

import com.google.codeu.mathlang.core.Function;
import com.google.codeu.mathlang.core.tokens.StringToken;
import com.google.codeu.mathlang.core.tokens.Token;

// NOTE FUNCTION
//
// This class defines the note functionality of mathlang. A note is similar
// to a comment in other programming languages. However, in Mathlang the only
// text that can appear in a note are quoted strings. For example:
//    NOTE "this is a note";
//    NOTE "this is the first half" "and this is this second";
//    Note "this is the first half"
//         "and this is the second";
//
// This class is not marked as final so that it can be overriden in our tests.
public class NoteFunction implements Function {

  @Override
  public void run(Token[] statement) throws Exception {

    final Collection<String> segments = new ArrayList<>();

    for (int i = 1; i < statement.length; i++) {
      if (statement[i] instanceof StringToken) {
        final StringToken string = (StringToken) statement[i];
        segments.add(string.value);
      } else {
        throw new Exception(String.format("NOTE should only contain string values but found %s", statement[i]));
      }
    }

    onNoteBody(segments);
  }

  // ON NOTE BODY
  //
  // This method does not need to do anything. It is exposed so NOTE
  // calls can be checked in our tests.
  protected void onNoteBody(Collection<String> segments) { }
}

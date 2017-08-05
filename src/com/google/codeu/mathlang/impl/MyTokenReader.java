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

package com.google.codeu.mathlang.impl;

import java.io.IOException;

import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.core.tokens.NameToken;
import com.google.codeu.mathlang.core.tokens.NumberToken;
import com.google.codeu.mathlang.core.tokens.StringToken;
import com.google.codeu.mathlang.core.tokens.SymbolToken;
import com.google.codeu.mathlang.parsing.TokenReader;

// MY TOKEN READER
//
// This is YOUR implementation of the token reader interface. To know how
// it should work, read src/com/google/codeu/mathlang/parsing/TokenReader.java.
// You should not need to change any other files to get your token reader to
// work with the test of the system.
public final class MyTokenReader implements TokenReader {

  private Token result;
  private String source;
  private int i = 0;
  private int count = 0;
  private char whitespace = ' ';

  public MyTokenReader(String source) {
    // Your token reader will only be given a string for input. The string will
    // contain the whole source (0 or more lines).

    this.source = source;
  }

  @Override
  public Token next() throws IOException {

    // Most of your work will take place here. For every call to |next| you should
    // return a token until you reach the end. When there are no more tokens, you
    // should return |null| to signal the end of input.

    // If for any reason you detect an error in the input, you may throw an IOException
    // which will stop all execution.

    i++;

    while(i >= source.length()){
      count++;
      if ((source.charAt(i)) == this.whitespace){
        if (count == 9){
          if(source.charAt(i - 8) > 65 && source.charAt(i - 8) < 90 ||
            source.charAt(i - 8) > 97 && source.charAt(i - 8) < 122){
              count = 0;
              this.result = new NameToken(source.substring(i - 8, i));
              return result;
          }
          else{
              count = 0;
              this.result = new StringToken(source.substring(i - 8, i));
              return result;
          }
        }
        else if (count == 2){
          if((source.substring(i - 1, i)).equals((source.charAt(i-1)) + "")){
            count = 0;
            this.result = new SymbolToken(source.charAt(i-1));
            return result;
          }
          else{
            return next();
          }
        }
        else {
          if(source.substring(i - count, i).equals((Double.parseDouble(source.substring(i - count, i))) + "")){
            count = 0;
            this.result = new NumberToken(Double.parseDouble(source.substring(i - count, i)));
            return result;
            }
          else{
            return next();
          }
        }
      }
      return next();
    }

    return null;
  }
}

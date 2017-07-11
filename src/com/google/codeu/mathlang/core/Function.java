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

import com.google.codeu.mathlang.core.tokens.Token;

// FUNCTION
//
// This interface is for a single functional unit of work defined by the
// paramters passed to it as an array of tokens. Functions should avoid
// carrying internal state.
public interface Function {

  // RUN
  //
  // This method is called when the there is a statement ready for it. The first
  // token will always be the identifier of the function. The function should
  // ignore this token. If any other token does not meet the expectations of the
  // function, an exception should be thrown and execution will be stopped.
  void run(Token[] statement) throws Exception;
}

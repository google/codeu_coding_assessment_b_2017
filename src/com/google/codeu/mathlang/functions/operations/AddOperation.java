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

package com.google.codeu.mathlang.functions.operations;

public final class AddOperation implements Operation {

  private final double amount;

  public AddOperation(double amount) {
    this.amount = amount;
  }

  @Override
  public double applyTo(double x) {
    return x + amount;
  }

  @Override
  public String toString() { return String.format("ADD(%f)", amount); }

  @Override
  public int hashCode() { return Double.hashCode(amount); }

  @Override
  public boolean equals(Object other) {
    return other instanceof AddOperation && equals(this, (AddOperation) other);
  }

  private static final boolean equals(AddOperation a, AddOperation b) {
    return a == b || (a != null && b != null && a.amount == b.amount);
  }
}


# CodeU Coding Assessment - Math Lang

# Overview
This project allows students to demonstrate their coding skills by implementing
a component of a larger system.

MathLang is a made-up math scripting language. Students are expected to
implement `com.google.codeu.mathlang.impl.MyTokenReader`. The class has already
been created and students only need to finish implementing its functionality.

`MyTokenReader` is responsible for parsing the text input and converting it to
a stream of tokens that the MathLang parser will use to build statements.
Statements are then passed to the interpreter to be executed. Everything except
for `MyTokenReader` has already been implemented.

To further understand how `MyTokenReader` should behave, students can look at
`com.google.codeu.mathlang.core.tokens.TokenReader`. `TokenReader` is the
interface that `MyTokenReader` implements and contains in-code documentation
for how each method should behave.

# The Goal
When students have finished their implementation of `MyTokenReader`, they should
run the tests and see all tests pass. If any test fails, the student is not
done. The tests provided are only for testing MathLang. There are no tests for
`MyTokenReader`. Students are encouraged to write their own tests to ensure
their implementation is working.

Students should not use any third-party libraries to complete this assessment.
The one exception is the use of a third-party test library to help test their
implementation of `MyTokenReader`.

When done, an evaluator should be able to run the student's project using the
same build and run instructions provided here. This means that if a student
uses a third-party test library, all dependencies must be included with the
project and `build.sh` and `run.sh` must be updated.

# Build Instructions
These instructions are based on a Linux environment using BASH and JAVA 7.

To clean, run `./clean.sh`.
To build, run `./build.sh`.
To run all tests, run `./run.sh`.
/*
Brendan Niroula - 10/11/19
startShell.h contains the signature for startShell which is used in main.cpp
*/

#ifndef STARTSHELL_H_
#define STARTSHELL_H_

/*
startShell is where the input loop resides, it runs this loop until a user enter "quit", if it is not quit, it will send the input through input handler and process the input there.
it takes no parameters and returns 1 when it finishes the loop.
*/

int startShell();

#endif
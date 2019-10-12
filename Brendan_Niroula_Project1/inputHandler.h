/*
Brendan Niroula - 10/11/19
inputHandler.h contains the signature for inputHandler which is used in startShell.cpp
*/

#ifndef INPUTHANDLER_H_
#define INPUTHANDLER_H_
using namespace std;

/*
This function does most of the heavy lifting for the shell, it is essentially a parser and dispatcher all in one. 
It is called in startShell.cpp and it takes a parameter of a string which is whatever the user enters. The string is the raw input. 
It will then run some if statements to decide what needs to be done to the string and which function will handle it accordingly. 
If it is a simple command a.k.a no pipes or redirection, the command will be forked and exec'ed here in this function.
It will always return one since the error handling is done internally and not by the calling function.
*/

int inputHandler(string);

#endif
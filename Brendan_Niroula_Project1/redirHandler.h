/*
Brendan Niroula - 10/11/19
redirHandler.h contains the signature for redirHandler which is used in inputHandler.cpp
*/

#ifndef REDIRHANDLER_H_
#define REDIRHANDLER_H_

using namespace std;

/*
This program is called in inputHandler if the users input contains a I/O character, it forks and iterates through the input and marks the index of each command,
once we know the index of each command we also know which commands are present and we can change stdin, stdout and stderr accordingly. At the end we call execvp and 
check for errors. 
Parameters are a vector of the tokenized input, the index of the first redirection symbol, a flag to check if we want this to run in the 
background. 
The function returns 1 when finished.
*/

int redirHandler(vector <string>, int, int);

#endif
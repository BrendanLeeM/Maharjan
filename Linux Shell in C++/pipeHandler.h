/*
Brendan Niroula - 10/11/19
pipeHandler.h contains the signature for pipeHandler which is used in inputHandler.cpp
*/

#ifndef PIPEHANDLER_H_
#define PIPEHANDLER_H_

/*
This function is called in inputHandler if the user inputs anything with pipes. 
It parses the input into a char* array then changes stdin and stdout to the pipes in fd then execs the respective commands.
The parameters are a vector of tokenized inputs from the user and a flag to let us know if the user wants it to be run in the background, deonted by an "&".
Error handling is done internally and always returns 1.
*/

using namespace std;
int pipeHandler(vector <string>, int);

#endif
/*
Brendan Niroula - 10/11/19
processBatch.h contains the signature for processBatch which is used in main.cpp
*/

#ifndef PROCESSBATCH_H_
#define PROCESSBATCH_H_

/*
This function is called in main if another argument is entered on program invocation, this function simply takes input from the input file until EOF is reached, for each line
we fork to another program and call inputHandler which will dispatch that line to whichever function it needs to go to.
It takes a char * for the name of the batch file and returns 1 when the function finishes. 
*/

int processBatch(char *);

#endif
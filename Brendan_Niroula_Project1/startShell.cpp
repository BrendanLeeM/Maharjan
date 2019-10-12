/*
Brendan Niroula - 10/11/19
Function with the input loop and prints prompt.
*/
#include <unistd.h>
#include <stdio.h>
#include <limits.h>
#include <stdlib.h>
#include <string>
#include <iostream>
#include "inputHandler.h"

using namespace std;

/*
startShell is where the input loop resides, it runs this loop until a user enter "quit", if it is not quit, it will send the input through input handler and process the input there.
it takes no parameters and returns 1 when it finishes the loop.
*/

int startShell(){

char cwd[PATH_MAX];

if(getcwd(cwd,sizeof(cwd)) == NULL) { perror("cwd error"); }
setenv("SHELL",cwd,1); /* set shell variable to the directory which ran the program*/
printf("[%s]: ", cwd);
string input = "";

while(input != "quit"){
getline(cin, input); 
inputHandler(input); 
if(getcwd(cwd,sizeof(cwd)) == NULL) { perror("cwd error"); }
printf("[%s]: ", cwd);
} 
printf("Exited shell. \n");
return 1;
}


/*
Brendan Niroula - 10/11/19
Function to process a batch file.  
*/

#include <string>
#include <fstream>
#include <iostream>
#include "inputHandler.h"
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

using namespace std;


/*
This function is called in main if another argument is entered on program invocation, this function simply takes input from the input file until EOF is reached, for each line
we fork to another program and call inputHandler which will dispatch that line to whichever function it needs to go to.
It takes a char * for the name of the batch file and returns 1 when the function finishes. 
*/

int processBatch(char *filename){
ifstream file;
file.open(filename);

if(!file){
	printf("Failed to open file %s \n",filename);
	return 1;
}
string temp;
while(getline(file,temp)){
	if(fork() == 0){
		cout << "\n";
		inputHandler(temp);
		exit(0);
	}
}
return 1;
}
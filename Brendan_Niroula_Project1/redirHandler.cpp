/*
Brendan Niroula - 10/11/19
Function to handle redirects
*/

#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <vector>
#include <sstream>
#include <iostream>
#include <sys/wait.h>
#include <sys/types.h>
#include <limits.h>
#include <fstream>
#include <sys/stat.h>
#include <fcntl.h>

using namespace std;

/*
This program is called in inputHandler if the users input contains a I/O character, it forks and iterates through the input and marks the index of each command,
once we know the index of each command we also know which commands are present and we can change stdin, stdout and stderr accordingly. At the end we call execvp and 
check for errors. 
Parameters are a vector of the tokenized input, the index of the first redirection symbol, a flag to check if we want this to run in the 
background. 
The function returns 1 when finished.
*/
int redirHandler(vector<string> tokens, int index, int ampFlag){
	if(fork() == 0){
	string symbols [3] = {">",">>","<"};
	unsigned long int symbolFlags [3] = {0,0,0};
	
	/*This loop checks which redirect symbols exist and gets their indexes, this allows us to call the proper open commands and lets us know which files need to be opened which will be at index i + 1. */
	for(unsigned long int i = 0; i < tokens.size();i++){
		if(tokens[i] == symbols[0]){
			symbolFlags[0] = i;
		}
		if(tokens[i] == symbols[1]){
			symbolFlags[1] = i;
		}
		if(tokens[i] == symbols[2]){
			symbolFlags[2] = i;
		}
	}
	
	char *charArray [index + 1];
	for(int i = 0; i < index;i++){
		charArray[i] = strdup(tokens[i].c_str());
	}
	charArray[index] = NULL;
	
	/*3 if statements, each one checks if any of >,>>,< were called. Note I initialized the array to 0 so it will not be > 0 unless I set it in the for loop above. 
	Based on which commands are called, we will set the file open calls accordingly.
	*/
	if(symbolFlags[0] > 0){
		
		if(tokens.size() < symbolFlags[0] + 2){
			printf("File not specified \n");
			exit(0);
		}
		
		int fileOut = open(tokens[symbolFlags[0] + 1].c_str(),O_CREAT|O_TRUNC|O_WRONLY,0600);
		if(fileOut == -1){
			printf("Error opening %s \n", tokens[symbolFlags[0] + 1].c_str());
			exit(0);
			return 1;
		}
		
		dup2(fileOut,STDOUT_FILENO);
		dup2(fileOut,STDERR_FILENO);
		close(fileOut);
		
	}
	
	if(symbolFlags[1] > 0){
		
		if(tokens.size() < symbolFlags[1] + 2){
			printf("File not specified \n");
			exit(0);
		}
		
		int fileOut = open(tokens[symbolFlags[1] + 1].c_str(),O_CREAT|O_APPEND|O_WRONLY,0600);
		if(fileOut == -1){
			printf("Error opening %s \n", tokens[symbolFlags[1] + 1].c_str());
			exit(0);
			return 1;
		}
		
		dup2(fileOut,STDOUT_FILENO);
		dup2(fileOut,STDERR_FILENO);
		close(fileOut);
	
	}
	
	if(symbolFlags[2] > 0){
		
		if(tokens.size() < symbolFlags[2] + 2){
			printf("File not specified \n");
			exit(0);
		}
		
		int fileIn = open(tokens[symbolFlags[2] + 1].c_str(),O_RDONLY,0600);
		if(fileIn == -1){
			printf("Error opening %s \n", tokens[symbolFlags[2] + 1].c_str());
			exit(0);
			return 1;
		}
		
		dup2(fileIn,STDIN_FILENO);
		close(fileIn);
		
	}
	/*Exec the commands and output will go wherever the redirects told us to go.*/
	execvp(charArray[0],charArray);
	exit(0);
	}
	if(ampFlag == 0){
	wait(0);
	wait(0);
	}
	return 1;
}

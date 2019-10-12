/*
Brendan Niroula - 10/11/19
 Code for handling pipe inputs
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


/*
This function is called in inputHandler if the user inputs anything with pipes. 
It parses the input into a char* array then changes stdin and stdout to the pipes in fd then execs the respective commands.
The parameters are a vector of tokenized inputs from the user and a flag to let us know if the user wants it to be run in the background, deonted by an "&".
Error handling is done internally and always returns 1.
*/
using namespace std;

int pipeHandler(vector <string> tokens, int ampFlag){

vector<string> writeTokens;
vector<string> readTokens;
/*Open the pipe.*/
int fd [2];
if (pipe(fd) == -1){
	printf("Pipe failed.");
	return 1;
}
int pipeFlag = 0;
/*Seperate the write commands and the read commands into 2 seperate arrays.*/
for(unsigned long int i = 0;i < tokens.size();i++){
	if(tokens[i] == "|"){
		pipeFlag = 1;
		continue;
	}
	if(pipeFlag == 0){
		writeTokens.push_back(tokens[i]);
	}else{
		readTokens.push_back(tokens[i]);
	}
}
char *writeArray [writeTokens.size() + 1];
	for(long unsigned int i = 0; i < writeTokens.size();i++){
		writeArray[i] = strdup(writeTokens[i].c_str());
	}
	writeArray[writeTokens.size()] = NULL;

char *readArray [readTokens.size() + 1];
	for(long unsigned int i = 0; i < readTokens.size();i++){
		readArray[i] = strdup(readTokens[i].c_str());
	}
	readArray[readTokens.size()] = NULL;

	/*Fork and handle each command seperately.*/
	if(fork() == 0){
		close(STDOUT_FILENO);
		dup(fd[1]);
		close(fd[0]);
		close(fd[1]);
		
		if(execvp(writeArray[0],writeArray) == -1){
				printf("Command write %s failed \n", writeArray[0]);
				exit(0);
		}
	}
	
	if(fork() == 0){
		close(STDIN_FILENO);
		dup(fd[0]);
		close(fd[0]);
		close(fd[1]);
		
		if(execvp(readArray[0],readArray) == -1){
				printf("Command read %s failed \n", readArray[0]);
				exit(0);
		}
	}
	
		close(fd[0]);
		close(fd[1]);
		if(ampFlag == 0){
		wait(0);
		wait(0);
		}
		return 1;
}
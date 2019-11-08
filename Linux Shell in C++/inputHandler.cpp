/*
Brendan Niroula - 10/11/19
Function for parsing and handling inputs
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
#include "pipeHandler.h"
#include "redirHandler.h"
using namespace std;

/*
This function does most of the heavy lifting for the shell, it is essentially a parser and dispatcher all in one. 
It is called in startShell.cpp and it takes a parameter of a string which is whatever the user enters. The string is the raw input. 
It will then run some if statements to decide what needs to be done to the string and which function will handle it accordingly. 
If it is a simple command a.k.a no pipes or redirection, the command will be forked and exec'ed here in this function.
It will always return one since the error handling is done internally and not by the calling function.
*/
int inputHandler(string input){
	
	if(input == ""){ /*If no input just return.*/
		return 1;
	}
	
	vector <string> tokens;
	vector <string> semitokens;
	stringstream spaceDelim (input); 
	stringstream semiDelim (input);
	string temp; 
	int semiFlag = 0;
	pid_t pid = getpid();
	int index = 0;
	int count = 0;
	int ampFlag = 0;
	
	/*Tokenize input and put it in a vector.*/
	while(spaceDelim >> temp){ 
		tokens.push_back(temp);
	}
	
	/*If there is nothing in the vector after tokenizing return.*/
	if(tokens.size() == 0)
		return 1;
	
	/*Check for background processing command*/
	if(tokens[tokens.size() - 1] == "&"){
		ampFlag = 1;
	}
	/*Check for pipes or redirects and handle accourdingly.*/
	for(unsigned long int i = 0;i<tokens.size();i++){
		if(tokens[i] == "|"){
			pipeHandler(tokens, ampFlag);
			return 1;
		}
		/*Checks for redirect symbols, gets the index and counts them.*/
		if(tokens[i] == "<" || tokens[i] == ">" || tokens[i] == ">>" ){
			if (index == 0){
			index = i;
			}			
			count++;
		}
	}
	/*Process redirects if they exist.*/
	if(count > 0){
		redirHandler(tokens, index, ampFlag);
		return 1;
	}
	
	if(tokens[0] == "quit"){
		return 1;
	}
	
	if(tokens[0] == "cd"){
	
		char cwd[PATH_MAX];
		if(tokens.size() == 1){
			return 1; 
		} else{
			if (chdir(tokens[1].c_str()) == -1){
			printf("Directory (%s) is inaccessible \n",tokens[1].c_str());
		} else {
			setenv("PWD",getcwd(cwd,sizeof(cwd)),1);
			
		}
		
	}
		return 1;
	} 
	/*Check for semicolon arguments.*/
	while(getline(semiDelim, temp, ';')){
		semitokens.push_back(temp);
	}
	/*If semicolons exist, set a flag to one.*/
	if(semitokens.size() > 1){
		semiFlag = 1;
		/*Iterate through the multiple commands and fork each one.*/
		for(long unsigned int i = 0; i <semitokens.size();i++){
			if(fork() == 0){
				tokens.clear();
				tokens.push_back(semitokens[i]);
				break;
			}
		}
	}
	/*If semicolons are flagged, the parent process will come here. This makes the parent process wait until all children are done unless
	ampFlag is set to one, in which case we want parent to return immediately.*/
	if(semiFlag == 1 && getpid() == pid){
		if(ampFlag == 1){return 1;}
		for(long unsigned int i = 0;i < semitokens.size();i++){
			wait(0);
			printf("\n");
		}
		return 1;
	}		
	/*If there are no semicolons, we will fork for one command and tell the parent to wait, we have 2 wait statements because after 
	the & is called once, the order in which processes print will be messed up. This fixes this issue.*/
	if(semiFlag == 0 && getpid() == pid){
		if(fork() == 0){
			if(ampFlag){
				vector<string>::iterator it = tokens.end();
				tokens.erase(it);
			}
		} else {
			if(ampFlag){
			return 2;
			}
			wait(0);
			wait(0);
			if(tokens[0] != "clear" && tokens[0] != "clr") printf("\n");
			return 1;
		}
	}
	/*Set up a command array for execvp() and execute them.*/
	if(tokens[0] == "clr"){
		tokens[0] = "clear";
	}
	if(tokens[0] == "environ"){
		tokens[0] = "env";
	}
	if(tokens[0] == "pause"){
		tokens.clear();
		tokens.push_back("read");
		tokens.push_back("-rsp");
		tokens.push_back("Press enter to continue");
	}
	if(tokens[0] == "time"){
		tokens.clear();
		tokens.push_back("date");
		tokens.push_back("+%T");
	}
	if(tokens[0] == "help"){
		tokens.clear();
		tokens.push_back("man");
		tokens.push_back("man");
	}

	 char *charArray [tokens.size() + 1];
	for(long unsigned int i = 0; i < tokens.size();i++){
		charArray[i] = strdup(tokens[i].c_str());
	}
	charArray[tokens.size()] = NULL;
	printf("\n");
	
	if(execvp(charArray[0],charArray) == -1){
	printf("Invalid command \n");
	exit(0);
	}
	return 1;
	}
	

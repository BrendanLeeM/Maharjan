/*
Brendan Niroula - 10/11/19
Main function which is ran on program invocation
*/

#include "startShell.h"
#include "processBatch.h"

/*
This is my main function which simply checks whether the user inputs another argument such as a batch file. 
If there is another argument, the function processBatch handles it, if not, we begin the normal shell through startShell.
The paramaters are the typical main parameters, the only time we use them is if the user enters a batch file to be read.
Upon ending it returns a 1.
*/
int main(int argc, char **argv){
	
if(argc == 2){
processBatch(argv[1]);	
}else{
startShell();
}
return 1;
}
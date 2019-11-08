CXX = g++
CXXFLAGS = -Wall -g

myShell: main.o startShell.o  inputHandler.o pipeHandler.o processBatch.o redirHandler.o
	$(CXX) $(CXXFLAGS) -o myShell main.o startShell.o inputHandler.o pipeHandler.o processBatch.o redirHandler.o
	
main.o: main.cpp startShell.h processBatch.h
	$(CXX) $(CXXFLAGS) -c main.cpp
	
startShell.o: startShell.cpp startShell.h inputHandler.h
	$(CXX) $(CXXFLAGS) -c startShell.cpp
	
inputHandler.o: inputHandler.cpp inputHandler.h  pipeHandler.h
	$(CXX) $(CXXFLAGS) -c inputHandler.cpp
	
pipeHandler.o: pipeHandler.cpp pipeHandler.h
	$(CXX) $(CXXFLAGS) -c pipeHandler.cpp
	
processBatch.o: processBatch.cpp processBatch.h inputHandler.h
	$(CXX) $(CXXFLAGS) -c processBatch.cpp
	
redirHandler.o: redirHandler.cpp redirHandler.h
	$(CXX) $(CXXFLAGS) -c redirHandler.cpp
	
clean: 
	$(RM) *.o *~ 
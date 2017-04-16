//: Listmgr2.cpp
// CGI version of Listmgr.c in C++, which 
// extracts its input via the GET submission 
// from the associated applet. Also works as
// an ordinary CGI program with HTML forms.
#include <stdio.h>
#include "CGITools.h"
const char* dataFile = "list2.txt";
const char* notify = "Bruce@EckelObjects.com";
#undef DEBUG

// Similar code as before, except that it looks
// for the email name inside of '<>':
int inList(FILE* list, const char* emailName) {
  const int BSIZE = 255;
  char lbuf[BSIZE];
  char emname[BSIZE];
  // Put the email name in '<>' so there's no
  // possibility of a match within another name:
  sprintf(emname, "<%s>", emailName);
  // Go to the beginning of the list:
  fseek(list, 0, SEEK_SET);
  // Read each line in the list:
  while(fgets(lbuf, BSIZE, list)) {
    // Strip off the newline: 
    char * newline = strchr(lbuf, '\n');
    if(newline != 0) 
      *newline = '\0';
    if(strstr(lbuf, emname) != 0)
      return 1;
  }
  return 0;
}

void main() {
  // You MUST print this out, otherwise the 
  // server will not send the response:
  printf("Content-type: text/plain\n\n");
  FILE* list = fopen(dataFile, "a+t");
  if(list == 0) {
    printf("error: could not open database. ");
    printf("Notify %s", notify);
    return;
  }
  // For a CGI "GET," the server puts the data
  // in the environment variable QUERY_STRING:
  CGI_vector query(getenv("QUERY_STRING"));
  #if defined(DEBUG)
  // Test: dump all names and values
  for(int i = 0; i < query.size(); i++) {
    printf("query[%d].name() = [%s], ", 
      i, query[i].name());
    printf("query[%d].value() = [%s]\n", 
      i, query[i].value());
  }
  #endif(DEBUG)
  Pair name = query[0];
  Pair email = query[1];
  if(name.empty() || email.empty()) {
    printf("error: null name or email");
    return;
  } 
  if(inList(list, email.value())) {
    printf("Already in list: %s", email.value());
    return;
  }
  // It's not in the list, add it:
  fseek(list, 0, SEEK_END);
  fprintf(list, "%s <%s>;\n", 
    name.value(), email.value());
  fflush(list);
  fclose(list);
  printf("%s <%s> added to list\n", 
    name.value(), email.value());
} ///:~
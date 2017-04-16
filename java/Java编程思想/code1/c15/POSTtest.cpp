//: POSTtest.cpp
// CGI_vector works as easily with POST as it
// does with GET. Written in "pure" C++.
#include <iostream.h>
#include "CGITools.h"

void main() {
  cout << "Content-type: text/plain\n" << endl;
  // For a CGI "POST," the server puts the length
  // of the content string in the environment 
  // variable CONTENT_LENGTH:
  char* clen = getenv("CONTENT_LENGTH");
  if(clen == 0) {
    cout << "Zero CONTENT_LENGTH" << endl;
    return;
  }
  int len = atoi(clen);
  char* query_str = new char[len + 1];
  cin.read(query_str, len);
  query_str[len] = '\0';
  CGI_vector query(query_str);
  // Test: dump all names and values
  for(int i = 0; i < query.size(); i++)
    cout << "query[" << i << "].name() = [" <<
      query[i].name() << "], " <<
      "query[" << i << "].value() = [" <<
      query[i].value() << "]" << endl;
  delete query_str; // Release storage
} ///:~
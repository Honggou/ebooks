# From Thinking in Java, 2nd Edition
# At http://www.BruceEckel.com
# (c) Bruce Eckel 2000
# Copyright notice in Copyright.txt
# Automatically-generated MAKEFILE 
# For examples in directory .\appendixb
# using the JDK 1.2 compiler
# Invoke with: make

JVC = javac
JVCFLAGS =
.SUFFIXES : .class .java
.java.class :
	$(JVC) $(JVCFLAGS) $<

# Microsoft Visual C++:
CPP=cl
DLLFLAG=-LD
OFLAG=-Fe

# Borland C++. A command-line version of this compiler
# is available for free download from 
# http://www.borland.com/bcppbuilder/freecompiler/
#CPP=bcc32
#DLLFLAG=-WD
#OFLAG=-o

# IMPORTANT: Adjust this for your environment:
WININCLUDE = -Ic:/ProgTools/Java/include -Ic:/ProgTools/Java/include/win32
UNIXINCLUDE = -I/usr/local/java/jdk1.2.2/include -I/usr/local/java/jdk1.2.2/include/linux

dos:  \
	ShowMessage.class \
	UseObjects.class \
	MsgImpl.dll \
	UseObjImpl.dll
	
linux: \
	ShowMessage.class \
	UseObjects.class \
	MsgImpl.so \
	UseObjImpl.so

ShowMessage.class: ShowMessage.java

UseObjects.class: UseObjects.java

ShowMessage.h: ShowMessage.class
	javah -jni ShowMessage

UseObjects.h: UseObjects.class
	javah -jni UseObjects

MsgImpl.dll: MsgImpl.cpp ShowMessage.h
	$(CPP) $(WININCLUDE) $(DLLFLAG) MsgImpl.cpp $(OFLAG)MsgImpl.dll

UseObjImpl.dll: UseObjImpl.cpp UseObjects.h
	$(CPP) $(WININCLUDE) $(DLLFLAG) UseObjImpl.cpp $(OFLAG)UseObjImpl.dll

MsgImpl.so: MsgImpl.cpp ShowMessage.h
	g++ -o MsgImpl.so -shared -Wl,-soname,MsgImpl.so $(UNIXINCLUDE) \
	MsgImpl.cpp -static -lc

UseObjImpl.so: UseObjImpl.cpp UseObjects.h
	g++ -o UseObjImpl.so -shared -Wl,-soname,UseObjImpl.so $(UNIXINCLUDE) \
	UseObjImpl.cpp -static -lc

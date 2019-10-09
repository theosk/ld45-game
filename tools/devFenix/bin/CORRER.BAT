@echo off
if exist c:\devfenix\bin\stderr.txt  del c:\devfenix\bin\stderr.txt
if exist c:\devfenix\bin\stdout.txt  del c:\devfenix\bin\stdout.txt
if exist %2.dcb    del %2.dcb
pushd  %3 
c:\devfenix\bin\fxc.exe  %1
if exist c:\devfenix\bin\stdout.txt   type c:\devfenix\bin\stdout.txt
if exist c:\devfenix\bin\stderr.txt   type c:\devfenix\bin\stderr.txt
if exist c:\devfenix\bin\stderr.txt   goto salida
c:\devfenix\bin\fxi.exe %2
if exist c:\devfenix\bin\stderr.txt  type c:\devfenix\bin\stderr.txt
:salida
pause 

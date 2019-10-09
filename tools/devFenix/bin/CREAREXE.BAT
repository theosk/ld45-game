@ECHO OFF
if exist c:\devfenix\bin\stderr.txt  del c:\devfenix\bin\stderr.txt
if exist c:\devfenix\bin\stdout.txt  del c:\devfenix\bin\stdout.txt
if exist %2.dcb    del %2.dcb
pushd  %3 
echo ----------- BUILDING EXE FILE ------------
c:\DevFenix\bin\fxc.exe -s fxi -a %1
echo ------------------------------------------



:salida
pause 

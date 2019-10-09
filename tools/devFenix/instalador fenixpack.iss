;  Fenix Pack
;  by ColDev
;

[Setup]
AppName=Fenix Pack 4
AppVerName=Fenix Pack 4
AppPublisher=ColDev Tools
DefaultDirName=c:\devFenix
DisableDirPage=yes
DefaultGroupName=Fenix Pack
Compression=lzma/normal
SolidCompression=yes

[Tasks]

[Files]
Source: "D:\DevFenix\instalador\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
;prg editor
Name: "{group}\PRG Editor (NotepadPP)"; Filename: "{app}\ide\notepad.exe"

;font editor
Name: "{group}\Tools\Font Editor\FNT edit"; Filename: "{app}\FNT Edit\fntedit.exe"
;fpg editor
Name: "{group}\Tools\FPG Editor\FPG edit"; Filename: "{app}\FPG Edit\fpgedit.exe"
;explosion
Name: "{group}\Tools\Explosion Gen\ExplosionGen"; Filename: "{app}\tools\explosion gen\explosion gen\explogen.exe"; WorkingDir: "{app}\tools\explosion gen\explosion gen"
Name: "{group}\Tools\Explosion Gen\Explosion Manual"; Filename: "{app}\tools\explosion gen\explosion gen\explogen.hlp"
Name: "{group}\Tools\Explosion Gen\Preview Pics"; Filename: "{app}\tools\explosion gen\explosion gen\"
;sprites gen
Name: "{group}\Tools\Sprites Gen\2D Sprites Gen"; Filename: "{app}\tools\sprite gen\charasex\Charas.EX.exe"
Name: "{group}\Tools\Sprites Gen\3D Sprites Gen"; Filename: "{app}\tools\sprite gen\colspritegen\ColSpriteGen.exe"; WorkingDir: "{app}\tools\sprite gen\colspritegen"
Name: "{group}\Tools\Sprites Gen\3D Sprites Gen Preview"; Filename: "{app}\tools\sprite gen\colspritegen\preview"


;graphic editor idraw
Name: "{group}\Tools\Graph Editor\Idraw"; Filename: "{app}\tools\graph editor\idraw\idraw3.exe"; WorkingDir: "{app}\tools\graph editor\idraw"
;graphic editor  ea_graph
Name: "{group}\Tools\Graph Editor\EAgraph"; Filename: "{app}\tools\graph editor\eagraph\EAGRAPH.EXE"; WorkingDir: "{app}\tools\graph editor\eagraph"
;pack utility
Name: "{group}\Tools\Pack\Packator"; Filename: "{app}\tools\pack\PakAtor.exe"
;Tile and map editor
Name: "{group}\Tools\TileAndMap Editor\TileStudio"; Filename: "{app}\tools\ts\ts.exe"; WorkingDir: "{app}\tools\ts"


;desinstalador
Name: "{group}\UnInstall\UnInstall"; Filename:"{uninstallexe}";

[INI]
;ruta compilador


;crea las extensiones prg, fnt y fpg
[Registry]
;flamebird
Root: HKCR; Subkey: ".PRG"; ValueType:expandsz; valuedata:"Fenix.PRG"; Flags: uninsdeletekey
Root: HKCR; Subkey: "Fenix.PRG\shell\open\command"; ValueType:expandsz; valuedata:"{app}\ide\notepad.exe ""%1"" ";
Root: HKCR; Subkey: "Fenix.PRG\shell\Edit with NotepadPP\command"; ValueType:expandsz; valuedata:"{app}\ide\notepad.exe ""%1"" "; Flags: uninsdeletekey


Root: HKCR; Subkey: ".FNT"; ValueType:expandsz; valuedata:"Fenix.FNT"; Flags: uninsdeletekey
Root: HKCR; Subkey: "Fenix.FNT\shell\open\command"; ValueType:expandsz; valuedata:"{app}\FNT Edit\fntedit.exe ""%1"" ";

Root: HKCR; Subkey: ".FPG"; ValueType:expandsz; valuedata:"Fenix.FPG";  Flags: uninsdeletekey
Root: HKCR; Subkey: "Fenix.FPG\shell\open\command"; ValueType:expandsz; valuedata:"{app}\FPG Edit\fpgedit.exe ""%1"" ";

;Ruta de instalacion
Root: HKLM; Subkey: "Software\Fenix\FenixPack"; ValueType: string; ValueName: "InstallPath"; ValueData: "{app}"; Flags: uninsdeletekey


[Code]



var
  FinishedInstall: Boolean;



procedure DeinitializeSetup();
var
  stdoutname: String;
  fxcname:string;
  fxcbatname:string;
  ResultCode: Integer;
  Cadena: TArrayOfString;
begin
  if FinishedInstall then
  begin

//------------ normal run
    SetArrayLength(Cadena, 13);
    Cadena[0]:= '@echo off';
    Cadena[1]:= 'if exist c:\devfenix\bin\stderr.txt  del c:\devfenix\bin\stderr.txt';
    Cadena[2]:= 'if exist c:\devfenix\bin\stdout.txt  del c:\devfenix\bin\stdout.txt';
    Cadena[3]:= 'if exist %2.dcb    del %2.dcb';
    Cadena[4]:= 'pushd  %3 ';
    Cadena[5]:= 'c:\devfenix\bin\fxc.exe  %1';
    Cadena[6]:= 'if exist c:\devfenix\bin\stdout.txt   type c:\devfenix\bin\stdout.txt';
    Cadena[7]:= 'if exist c:\devfenix\bin\stderr.txt   type c:\devfenix\bin\stderr.txt';
    Cadena[8]:= 'if exist c:\devfenix\bin\stderr.txt   goto salida';
    Cadena[9]:= 'c:\devfenix\bin\fxi.exe %2';
    Cadena[10]:= 'if exist c:\devfenix\bin\stderr.txt  type c:\devfenix\bin\stderr.txt';
    Cadena[11]:= ':salida';
    Cadena[12]:= 'pause ';
    

    SaveStringsToFile('c:\devfenix\bin\correr.bat' ,cadena, false);
//------------ debug run

    Cadena[0]:= '@echo off';
    Cadena[1]:= 'if exist c:\devfenix\bin\stderr.txt  del c:\devfenix\bin\stderr.txt';
    Cadena[2]:= 'if exist c:\devfenix\bin\stdout.txt  del c:\devfenix\bin\stdout.txt';
    Cadena[3]:= 'if exist %2.dcb    del %2.dcb';
    Cadena[4]:= 'pushd  %3 ';
    Cadena[5]:= 'c:\devfenix\bin\fxc.exe -d -g %1';
    Cadena[6]:= 'if exist c:\devfenix\bin\stdout.txt   type c:\devfenix\bin\stdout.txt';
    Cadena[7]:= 'if exist c:\devfenix\bin\stderr.txt   type c:\devfenix\bin\stderr.txt';
    Cadena[8]:= 'if exist c:\devfenix\bin\stderr.txt   goto salida';
    Cadena[9]:= 'c:\devfenix\bin\fxi.exe  -d %2';
    Cadena[10]:= 'if exist c:\devfenix\bin\stderr.txt  type c:\devfenix\bin\stderr.txt';
    Cadena[11]:= ':salida';
    Cadena[12]:= 'pause ';


    SaveStringsToFile('c:\devfenix\bin\correrd.bat' ,cadena, false);
//------------ build exe

    Cadena[0]:= '@ECHO OFF';
    Cadena[1]:= 'if exist c:\devfenix\bin\stderr.txt  del c:\devfenix\bin\stderr.txt';
    Cadena[2]:= 'if exist c:\devfenix\bin\stdout.txt  del c:\devfenix\bin\stdout.txt';
    Cadena[3]:= 'if exist %2.dcb    del %2.dcb';
    Cadena[4]:= 'pushd  %3 ';
    Cadena[5]:= 'echo ----------- BUILDING EXE FILE ------------';
    Cadena[6]:= 'c:\DevFenix\bin\fxc.exe -s fxi -a %1';
    Cadena[7]:= 'echo ------------------------------------------';
    Cadena[8]:= '';
    Cadena[9]:= '';
    Cadena[10]:= '';
    Cadena[11]:= ':salida';
    Cadena[12]:= 'pause ';


    SaveStringsToFile('c:\devfenix\bin\crearexe.bat' ,cadena, false);

//------------

  end;
end;



procedure CurStepChanged(CurStep: TSetupStep);
begin
  if CurStep=ssDone then   // cuando la instalacion es completada OK
    FinishedInstall := True;
end;



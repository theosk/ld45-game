;*
; *  COLOMBIAN SPRITE GENERATOR
; *  Current release       : CSG 
; *  Last stable release   :
; *  Project documentation : 
; *
; *
; *  This program is free software; you can redistribute it and/or modify
; *  it under the terms of the GNU General Public License as published by
; *  the Free Software Foundation; either version 2 of the License, or
; *  (at your option) any later version.
; *
; *  This program is distributed in the hope that it will be useful,
; *  but WITHOUT ANY WARRANTY; without even the implied warranty of
; *  MERCHANTABILITY Or FITNESS For A PARTICULAR PURPOSE.  See the
; *  GNU General Public License For more details.
; *
; *  You should have received a copy of the GNU General Public License
; *  along with this program; if not, write to the Free Software
; *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
; *
; *  Copyright © 2006 Colombian Developers
; *  
; *  
; */

;/*
; * FILE        : colspritegen.bb
; * DESCRIPTION : sprite generator
; *
; * HISTORY:
; * V1.02  More Options, camera and object moving ,new models
; *
; * V1.01  Initial release
; *
; */

Graphics3D 800,600,16,2


AppTitle "Colombian Sprite Generator V1.02        ColDev (c) 2006       FenixPowered(fenix.divsite.net)"

Global MALLA=False

; include de la Ngui
;--------------------
Include "Ngui.bb"

; on active le theme
;------------------------
; le 1 est par défaut, donc dans ce cas, ce n'est obligatoire
;-------------------------------------------------------------
NG_SetStyle 4

; activation de la souris
;--------------------------
NG_ActiveMouse ( 1 )

; initialisation
;------------------
NG_Initialize ()

; création de notre scene
;--------------------------
light = CreateLight()
cam  = CreateCamera ()
	MoveEntity cam , 0 , 0 , -50
	CameraClsColor cam,0,200,255

;variables para el manejo de los combos :
Global UltimoSeleccionado$=""
modeloseleccionado$=""
texturaseleccionada$=""
vistaseleccionada$=""
armaseleccionada$=""
panimacionseleccionada$=""
idarma=0
idmodelo=0
TexturadeRender=0
CuadroAnimacion=0
;-------

x1=0: y1=0 : z1=0 ; posicion de la camara
ax1=0: ay1=0 : az1=0 ; posicion del arma
;------


;------------------------------------
; ventana acerca de
;------------------------------------

fenetreExp = NG_CreateWindow ( 20 , 420 , 300 , 140 , "Colombian Sprite Generator." , 1+2+8 )
	info = NG_CreateText ( fenetreExp , 10 , 35 , "A little FENIX sprite generator.   MD2, X, 3DS, B3D, model support.     " , 48 , 0 , 0 , 0 , 0 )
	quitter = NG_CreateButton ( fenetreExp , 220 , 100 , 70 , 30 , "Quit" , 0 , "click to exit program" )



;--------------------------------
; ventana scene info
;---------------------------------
fenetre = NG_CreateWindow ( 20 , 10 , 300 , 330 , "Scene Info." , 1+2+8 )

    efectos = NG_CreateCombo ( fenetre , 180 , 30 , 110 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( efectos, "no Effect" )
	NG_AddComboItem ( efectos , "Fx1" )
	NG_AddComboItem ( efectos , "Fx2" )
	NG_AddComboItem ( efectos , "Fx3" )
	NG_AddComboItem ( efectos , "Fx4" )	
	NG_SetCombo ( efectos , "Fx1" )


	alpha = NG_CreateDoseur ( fenetre , 20 , 20 , 260 , "Alpha"  , 0.0 , 1.0 , 1.0 , 0 , "Color Alpha" )
	wire  = NG_CreateCheckBox ( fenetre , 20 , 100 , "Wireframe" )

	r = NG_CreateInput ( fenetre , 10 , 120 , 40 , 20 , "Red: ", 2 ,   0 , "Background" )
	g = NG_CreateInput ( fenetre , 100 , 120 , 40 , 20 , "Green: ", 2 ,255 , "Background" )
	b = NG_CreateInput ( fenetre , 200 , 120 , 40 , 20 , "Blue: ", 2 , 0 , "Background" )
	
 	cbmodelos = NG_CreateCombo ( fenetre , 10 , 220 , 280 , 20 , "" , 0 , "" , 6 )
	    CARGAR_LISTA_MODELOS("models","",cbmodelos )       
	NG_SetCombo (cbmodelos , UltimoSeleccionado$)
	
	cbtexturas = NG_CreateCombo ( fenetre , 10 , 250 , 280 , 20 , "" , 0 , "" , 6 )
	    CARGAR_LISTA_MODELOS("models\"+UltimoSeleccionado$+".tex\",UltimoSeleccionado$,cbtexturas)       	
	NG_SetCombo ( cbtexturas , UltimoSeleccionado$)
	
    cbarmas = NG_CreateCombo ( fenetre , 10 , 280 , 280 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( cbarmas , "No Weapon" )
	    CARGAR_LISTA_MODELOS("weapons","",cbarmas)       	
	NG_SetCombo ( cbarmas , "No Weapon" )



;------------------------
;ventana FPS
;------------------------
;fenetreT = NG_CreateWindow ( 350 , 0 , 200 , 100 , "" , 1+2+32  )
;framesPsec = NG_CreateFPS ( fenetreT , 10 , 10 , 0 , "FPS : " , "FPS" )


;------------------------
; ventana sprite render 
;-------------
fenetre3d = NG_CreateWindow ( 500 , 10 , 300 , 580 , "Sprite render Camera." , 1+2+8 )
	;f3d = NG_Create3dFrame ( fenetre3d , 10 , 130 , 128 , 128 , 0 , 1 )
	
	NG_WindowTabOn ( fenetre3d )
	NG_AddTab ( fenetre3d , "Camera" ,LoadImage ( "maindata\win_icon_4.png" ))
	NG_AddTab ( fenetre3d , "Others" ,LoadImage ( "maindata\TabIcon_1.png" ))
	NG_AddTab ( fenetre3d , "Render" ,LoadImage ( "maindata\TabIcon_2.png" ))
	

	cbvistas = NG_CreateCombo ( fenetre3d , 10 , 270 , 280 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( cbvistas  , "Platform View" )
	NG_AddComboItem ( cbvistas  , "Isometric View" )
	NG_AddComboItem ( cbvistas  , "Strategy view" )
	NG_SetCombo ( cbvistas  , "Platform View" )
    NG_AttachCombo ( cbvistas , "Camera" )	


	zoomd = NG_CreateDoseur ( fenetre3d , 20 , 280 , 265 , "Zoom"  , 0.0 , 10.0 , 1.0 , 0 , "zoom camera" )
    NG_AttachDoseur ( zoomd , "Camera" )	
	
	x1 = NG_CreateDoseur ( fenetre3d , 20 , 330 , 265 , "X"  , 0 , 360 , 0 , 2 , "Rotate X" )
    NG_AttachDoseur ( x1 , "Camera" )		
	y1 = NG_CreateDoseur ( fenetre3d , 20 , 380 , 265 , "Y"  , 0 , 360 , 0 , 2 , "Rotate Y" )
    NG_AttachDoseur ( y1 , "Camera" )	
	z1 = NG_CreateDoseur ( fenetre3d , 20 , 430 , 265 , "Z"  , 0 , 360 , 0 , 2 , "Rotate Z" )
    NG_AttachDoseur ( z1 , "Camera" )	

	
;--------------	
	infofrequ = NG_CreateText ( fenetre3d , 10 , 250 , "Texture Size:" , 34 , 0 , 0 , 0 , 0 )
	NG_AttachText ( infofrequ , "Others" )	

	cbtexturatam = NG_CreateCombo ( fenetre3d , 10 , 300 , 280 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( cbtexturatam , "32x32" )
	NG_AddComboItem ( cbtexturatam , "64x64" )
	NG_AddComboItem ( cbtexturatam , "128x128" )
	NG_AddComboItem ( cbtexturatam , "256x256" )
	NG_AddComboItem ( cbtexturatam , "512x512" )	
	NG_SetCombo ( cbtexturatam , "32x32" )
    NG_AttachCombo ( cbtexturatam , "Others" )	

	Wx2 = NG_CreateInput ( fenetre3d , 10 , 340 , 40 , 20 , "WeaponX: ", 2 ,   0 , "Weapon" )
	NG_AttachInput ( Wx2 , "Others" )	
	Wy2 = NG_CreateInput ( fenetre3d , 130 , 340 , 40 , 20 , "Wy: ", 2 , 0, "Weapon" )
	NG_AttachInput ( Wy2 , "Others" )		
	Wz2 = NG_CreateInput ( fenetre3d , 200 , 340 , 40 , 20 , "Wz: ", 2 , 0 , "Weapon" )
	NG_AttachInput ( Wz2 , "Others" )
	
	RWx2 = NG_CreateInput ( fenetre3d , 10 , 370 , 40 , 20 , "RotaWeapX: ", 2 ,   0 , "Weapon" )
	NG_AttachInput ( RWx2 , "Others" )	
	RWy2 = NG_CreateInput ( fenetre3d , 130 , 370 , 40 , 20 , "Wy: ", 2 , 180, "Weapon" )
	NG_AttachInput ( RWy2 , "Others" )		
	RWz2 = NG_CreateInput ( fenetre3d , 200 , 370 , 40 , 20 , "Wz: ", 2 , 0 , "Weapon" )
	NG_AttachInput ( RWz2 , "Others" )
	
	
	Mx3 = NG_CreateInput ( fenetre3d , 10 , 400 , 40 , 20 , "Modelx: ", 2 ,   0 , "Model" )
	NG_AttachInput ( Mx3 , "Others" )	
	My3 = NG_CreateInput ( fenetre3d , 130 , 400 , 40 , 20 , "My: ", 2 , 3, "Model" )
	NG_AttachInput ( My3 , "Others" )		
	Mz3 = NG_CreateInput ( fenetre3d , 200 , 400 , 40 , 20 , "Mz: ", 2 , 0 , "Model" )
	NG_AttachInput ( Mz3 , "Others" )	
	
	RMx3 = NG_CreateInput ( fenetre3d , 10 , 430 , 40 , 20 , "RotaModelx: ", 2 ,   0 , "Model" )
	NG_AttachInput ( RMx3 , "Others" )	
	RMy3 = NG_CreateInput ( fenetre3d , 130 , 430 , 40 , 20 , "My: ", 2 , 180, "Model" )
	NG_AttachInput ( RMy3 , "Others" )		
	RMz3 = NG_CreateInput ( fenetre3d , 200 , 430 , 40 , 20 , "Mz: ", 2 , 0 , "Model" )
	NG_AttachInput ( RMz3 , "Others" )	

;-------------
    totalcuadros = NG_CreateText ( fenetre3d , 150 , 200 , "Total Frames : 350" , 34 , 0 , 0 , 0 , 0 )
	NG_AttachText ( totalcuadros , "Render" )	
	bpreview= NG_CreateButton ( fenetre3d , 150 , 150 , 70 , 30 , "Preview" , 0 , "Click to preview animation " )
    NG_AttachButton ( bpreview , "Render" )	
	brender= NG_CreateButton ( fenetre3d , 20 , 280 , 70 , 30 , "Render" , 0 , "Render to BMP" )
    NG_AttachButton ( brender , "Render" )	
	initframe = NG_CreateInput ( fenetre3d , 120 , 280 , 40 , 20 , "1st: ", 2 ,   0 , "First Frame" )
    NG_AttachInput ( initframe , "Render" )	
    endframe = NG_CreateInput ( fenetre3d , 200 , 280 , 40 , 20 , "Last: ", 2 , 39 , "End Frame" )
    NG_AttachInput ( endframe , "Render" )	

	w_initframe = NG_CreateInput ( fenetre3d , 60 , 310 , 40 , 20 , "Weapon 1st: ", 2 ,   0 , "First Frame" )
    NG_AttachInput ( w_initframe , "Render" )	
    w_endframe = NG_CreateInput ( fenetre3d , 200 , 310 , 40 , 20 , "Last: ", 2 , 39 , "End Frame" )
    NG_AttachInput ( w_endframe , "Render" )	
    ;plantilla animaciones 
    panimacion = NG_CreateCombo ( fenetre3d , 20 , 340 , 110 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( panimacion , "Stand" )
	NG_AddComboItem ( panimacion , "Run" )
	NG_AddComboItem ( panimacion , "Attack" )
	NG_AddComboItem ( panimacion , "Pain" )
	NG_AddComboItem ( panimacion , "Jump" )	
    NG_AddComboItem ( panimacion , "Flip" )	
	NG_AddComboItem ( panimacion , "Salute" )	
	NG_AddComboItem ( panimacion , "Taunt" )	
	NG_AddComboItem ( panimacion , "Wave" )	
	NG_AddComboItem ( panimacion , "Point" )	
	NG_AddComboItem ( panimacion , "Crstnd" )	
	NG_AddComboItem ( panimacion , "Crwalk" )	
	NG_AddComboItem ( panimacion , "Crattak" )	
	NG_AddComboItem ( panimacion , "Crpain" )	
	NG_AddComboItem ( panimacion , "Crdeath" )	
	NG_AddComboItem ( panimacion , "Death" )		
	NG_SetCombo ( panimacion , "Stand" )
	
    NG_AttachCombo ( panimacion  , "Render" )

;--------------	
	; configuration de la camera du frame3d
	;-----------------------------------------
	;Global camera
	piv=CreatePivot()
		

	
	EntityParent cam, piv		   

    Gosub Cargar_Modelo
    Gosub CargarTextura 
    
	

Salir=False
; boucle prinipale
;-------------------
While ( (Not KeyDown(1)) And (Not Salir))
    
	;NG_RenderCam ( f3d )	
	
;lee el efecto seleccionado :
	i=0
   If  NG_ReturnCombo$ ( efectos) = "Fx1" Then i = 1    
   If  NG_ReturnCombo$ ( efectos) = "Fx2" Then i = 2
   If  NG_ReturnCombo$ ( efectos) = "Fx3" Then i = 4
   If  NG_ReturnCombo$ ( efectos) = "Fx4" Then i = 32     
          EntityFX idmodelo, i  ;aplica efecto     

	
	; efectos de render
	;-----------------------------
		EntityAlpha idmodelo , NG_ReturnDoseur ( alpha )
		MALLA=NG_ReturnCheckBox ( wire )
		WireFrame MALLA
		amb = NG_ReturnInput$ ( force )		
			
			
	;movimientos
	;------------------------------------
	CameraZoom cam , NG_ReturnDoseur ( zoomd )

	
	
	CameraClsColor cam , NG_ReturnInput( R ), NG_ReturnInput( G ), NG_ReturnInput( B )	
	
    RotateEntity  piv, NG_ReturnDoseur ( x1),NG_ReturnDoseur ( y1 ),NG_ReturnDoseur ( z1 ),True
    If (idarma <> 0) Then
      PositionEntity  idarma, NG_ReturnInput ( Wx2),NG_ReturnInput ( Wy2 ),NG_ReturnInput ( Wz2 ),True
      RotateEntity  idarma, NG_ReturnInput ( RWx2),NG_ReturnInput ( RWy2 ),NG_ReturnInput ( RWz2 ),True
    EndIf

    PositionEntity idmodelo, NG_ReturnInput ( Mx3),NG_ReturnInput ( My3 ),NG_ReturnInput ( Mz3 ),True
    RotateEntity idmodelo, NG_ReturnInput ( RMx3),NG_ReturnInput ( RMy3 ),NG_ReturnInput ( RMz3 ),True


    Gosub Cargar_Modelo
    Gosub CargarTextura 
    Gosub CargarArmas
	Gosub CargarVistas
	Gosub CargarPAnimacion

	If NG_ReturnButton ( bpreview ) Then   Gosub HacerPreview
	If NG_ReturnButton ( brender ) Then	 
	  Gosub HacerRender
	  resultado = NG_CreateInfoWindow ( "Render" , "Render Process Ok" , "" , "Ok" )
	EndIf

	
	; bouton quitter
	;-----------------
	If NG_ReturnButton ( quitter ) Or NG_ReturnWindowOpen (fenetreExp) = False ;salir ?
			Salir=True	
	EndIf



	;------------------------
	UpdateWorld
	RenderWorld			
	
	NG_Update ()

	Flip
		
	Cls
Wend

; on détruit Ngui pour libérer correctement la mémoire
;-------------------------------------------------------
NG_Kill ()

; fin
;------
End




.Cargar_Modelo
 If modeloseleccionado$ <> NG_ReturnCombo$ ( cbmodelos )  Then
    modeloseleccionado$= NG_ReturnCombo$ ( cbmodelos ) 

    If idmodelo<>0 Then FreeEntity idmodelo

    If  Instr( Lower(modeloseleccionado$),".md2",1) >0 Then
	 idmodelo = LoadMD2 ("models\"+modeloseleccionado$ )
     NG_SetText (totalcuadros , "Total Frames : "+MD2AnimLength(idmodelo) ) 
	Else
	 idmodelo = LoadAnimMesh ("models\"+modeloseleccionado$ )
	 NG_SetText (totalcuadros , "Total Frames : "+AnimLength (idmodelo) ) 
	EndIf
		
    
    NG_DeleteAllComboItem ( cbtexturas ) ;hace un listado de las texturas del modelo
        CARGAR_LISTA_MODELOS("models\"+modeloseleccionado$+".tex\",modeloseleccionado$,cbtexturas)       	
	NG_SetCombo ( cbtexturas , UltimoSeleccionado$) 
	
    NG_SetCombo ( cbarmas , "No Weapon" ) : idarma=0  ; configura el combo de weapons

 EndIf 
Return


.CargarTextura
  If texturaseleccionada$ <> NG_ReturnCombo$ ( cbtexturas )  Then
    texturaseleccionada$= NG_ReturnCombo$ ( cbtexturas )   
  
   FreeTexture tex 
   tex=LoadTexture( "models\"+modeloseleccionado$+".tex\" +texturaseleccionada$) 
   EntityTexture idmodelo,tex 
  EndIf
Return



.CargarArmas
  If armaseleccionada$<> NG_ReturnCombo$ ( cbarmas )  Then
    armaseleccionada$= NG_ReturnCombo$ ( cbarmas )
   
    If idarma <> 0 Then 
       FreeEntity idarma       
       FreeTexture tex
       idarma=0
    EndIf    
    
    If armaseleccionada$ <> "No Weapon" Then
     If  Instr( Lower(armaseleccionada$),".md2",1) >0 Then
	  idarma = LoadMD2 ("weapons\"+armaseleccionada$ )
	 Else
	  idarma = LoadAnimMesh ("weapons\"+armaseleccionada$)
	 EndIf	
	

	 tex=LoadTexture( "weapons\textures\" +armaseleccionada$ + ".pcx")
     EntityTexture idarma,tex 

     EntityParent idarma,idmodelo ;le pega el arma
	EndIf    
    
  EndIf
Return


.CargarVistas
 If vistaseleccionada$ <> NG_ReturnCombo$ ( cbvistas )  Then
    vistaseleccionada$= NG_ReturnCombo$ ( cbvistas )   
    valor1=0 : valor2=180 : valor3=0    
 
    If  vistaseleccionada$ = "Isometric View" Then 
       valor1=45 : valor2=45 : valor3=0
    EndIf
    If  vistaseleccionada$ = "Strategy view" Then 
       valor1=90 : valor2=0 : valor3=0
    EndIf
     
    NG_SetDoseur ( x1, valor1, 0 , 360 ,  "Rotate X" )
    NG_SetDoseur ( y1, valor2, 0 , 360 ,  "Rotate Y" )
    NG_SetDoseur ( z1, valor3, 0 , 360 ,  "Rotate Z" )
  EndIf
Return


.CargarPAnimacion
 If panimacionseleccionada$ <> NG_ReturnCombo$ ( panimacion ) Then
   panimacionseleccionada$= NG_ReturnCombo$ ( panimacion )

   Select panimacionseleccionada$ 
   Case   "Stand" 
     NG_SetInput(initframe,"0") : NG_SetInput(endframe,"39")
   Case   "Run" 
     NG_SetInput(initframe,"40") : NG_SetInput(endframe,"45")     
   Case   "Attack"  
     NG_SetInput(initframe,"46") : NG_SetInput(endframe,"53")     
   Case  "Pain"  
     NG_SetInput(initframe,"54") : NG_SetInput(endframe,"65")     
   Case   "Jump"  
     NG_SetInput(initframe,"66") : NG_SetInput(endframe,"71")     
   Case   "Flip"  
     NG_SetInput(initframe,"72") : NG_SetInput(endframe,"83")     
   Case   "Salute"  
     NG_SetInput(initframe,"84") : NG_SetInput(endframe,"94")     
   Case   "Taunt"  
     NG_SetInput(initframe,"95") : NG_SetInput(endframe,"111")     
   Case   "Wave"  
     NG_SetInput(initframe,"112") : NG_SetInput(endframe,"122")     
   Case  "Point"  
     NG_SetInput(initframe,"123") : NG_SetInput(endframe,"134")     
   Case   "Crstnd"  
     NG_SetInput(initframe,"135") : NG_SetInput(endframe,"153")     
   Case   "Crwalk"  
     NG_SetInput(initframe,"154") : NG_SetInput(endframe,"159")     
   Case   "Crattak"  
     NG_SetInput(initframe,"160") : NG_SetInput(endframe,"168")     
   Case   "Crpain"  
     NG_SetInput(initframe,"169") : NG_SetInput(endframe,"172")     
   Case   "Crdeath" 
     NG_SetInput(initframe,"173") : NG_SetInput(endframe,"177")      
   Case   "Death"  
     NG_SetInput(initframe,"178") : NG_SetInput(endframe,"197")          
   End Select
 EndIf	
Return


.HacerRender
  Gosub HacerPreview;llama la animacion

  activo=True
  CuadroAnimacion=1  			


  While (activo)
    activo=False

    If  Instr( Lower(modeloseleccionado$),".md2",1) >0 Then 
      If  MD2Animating (idmodelo) =1 Then  activo=True          
    Else 
      If Animating(idmodelo) Then activo=True
    EndIf
   ; ----------------
   ;busca el tamaño de la textura
   tamano=32
   If  NG_ReturnCombo$ ( cbtexturatam) = "64x64" Then  tamano=64
   If  NG_ReturnCombo$ ( cbtexturatam) = "128x128" Then  tamano=128
   If  NG_ReturnCombo$ ( cbtexturatam) = "256x256" Then  tamano=256
   If  NG_ReturnCombo$ ( cbtexturatam) = "512x512" Then  tamano=512

   If TexturadeRender<>0 Then FreeImage TexturadeRender
   TexturadeRender= CreateImage(tamano,tamano) ;crea la textura del tamaño NxN
   ; ----------------
   Cls
   SetBuffer BackBuffer ()	
   Viewport 0 , 0 , tamano, tamano
   CameraViewport cam , 0 , 0 , tamano, tamano
   
   UpdateWorld ;actualiza datos de blitz3d
   RenderWorld	
   ;copia la pantalla		
   CopyRect 0 , 0 , tamano, tamano, 0 , 0 , BackBuffer() , ImageBuffer (TexturadeRender) 
   ;la salva en disco
   SaveBuffer(ImageBuffer(TexturadeRender),"preview\pic_"+CuadroAnimacion+".bmp")
   CuadroAnimacion=CuadroAnimacion+1

  Wend  

  Viewport 0 , 0 , 800, 600; restaura la pantalla
  CameraViewport cam , 0 , 0 , 800, 600
Return 


.HacerPreview
 ;anima modelo
 If  Instr( Lower(modeloseleccionado$),".md2",1) >0 Then  
   AnimateMD2  idmodelo, 3,1, NG_ReturnInput( initframe ),NG_ReturnInput( endframe )
 Else
   Animate idmodelo, 3,1,ExtractAnimSeq( idmodelo,NG_ReturnInput(initframe),NG_ReturnInput(endframe) )
 EndIf
	
 ;anima arma
 If armaseleccionada$ <> "No Weapon" Then
   If  Instr( Lower(armaseleccionada$ ),".md2",1) >0 Then  
    AnimateMD2  idarma, 3,1, NG_ReturnInput( w_initframe ),NG_ReturnInput( w_endframe )
   Else
    Animate idarma, 3,1,ExtractAnimSeq( idarma,NG_ReturnInput(w_initframe),NG_ReturnInput(w_endframe) )
  EndIf
 EndIf

Return


Function CARGAR_LISTA_MODELOS( Directorio$,comparacion$,idobjeto )
; Define what folder to start with ... 
folder$=Directorio$ 
UltimoSeleccionado$=""
; Open up the directory, and assign the handle to myDir 
myDir=ReadDir(folder$) 
; Let's loop forever until we run out of files/folders to list! 
Repeat 
; Assign the next entry in the folder to file$ 
file$=NextFile$(myDir) 
; If there isn't another one, let's exit this loop 
If file$="" Then Exit 
; Use FileType to determine if it is a folder (value 2) or a file and print results 
If FileType(folder$+"\"+file$) = 2 Then ;directorio 
Else ;archivo
 posicion= 1
  If  comparacion$ <> "" Then 
     posicion= Instr( Lower( file$),Lower(comparacion$),1)   
   EndIf  
  If posicion > 0 Then
     NG_AddComboItem ( idobjeto , file$  )
    UltimoSeleccionado$ = file$
  EndIf

End If 
Forever 
; Properly close the open folder 
CloseDir myDir 

End Function
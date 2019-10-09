;------------------------------------------------------------------------
; Modules : Preview
;------------------------------------------------------------------------

;------------------------------------------------------
; Crée une fenetre Preview
;------------------------------------------------------
Function NG_Preview ( Px , Py , File$ , tline=False )


	;---------------------------------------
	; chargement du fichier
	;---------------------------------------
	
	;--------------------------------------
	; identification de l'extension
	;--------------------------------------
	
	;--------------------------------------
	; ext à 3 lettres
	;--------------------------------------
	ext$ = Right$ ( file$ , 4 )
	
	ext$ = Lower$ (ext$)
	
	;---------------
	; Images
	;---------------
	If ext$ = ".jpg" Or ext$ = ".bmp" Or ext$ = ".png" Or ext$ = ".pcx" Or ext$ = ".tif"
	
		media$ = "image"
	
		image = LoadImage ( file$ )
		
		Itx = ImageWidth ( image )
		Ity = ImageHeight ( image )
			
	;---------------
	; objets 3D
	;---------------
	Else If ext$ = ".3ds" Or ext$ = ".b3d" Or ext$ = ".md2" Or Right$ ( file$ , 2 ) = ".x"
	
		media$ = "3d"
		
		Itx = 400
		Ity = 400
	
	;---------------
	; son
	;---------------
	Else If ext$ = ".wav" Or ext$ = ".mp3"
	
		media$ = "son"
		
	;---------------
	; video
	;---------------
	Else If ext$ = ".mpg" Or ext$ = ".asf" Or ext$ = ".avi" Or ext$ = ".wmv" Or ext$ = "mpeg"
	
		media$ = "video"
	
	;---------------
	; texte
	;---------------
	Else If ext$ = ".txt" Or ext$ = ".doc" Or ext$ = ".ini" Or ext$ = ".rtf"
	
		media$ = "texte"
		
		Itx = 300
		Ity = 300
				
	EndIf

	;-----------------------
	; Taille de la fenetre
	;-----------------------
	Tx = iTx + 20
	Ty = iTy + 70
	
	;---------------------------------
	; création de la fenetre
	;---------------------------------
	; activer la title line
	If TLine = True Then TL = 256
	
	pwindow = NG_CreateWindow ( Px , Py , Tx , Ty , "Preview : "+file$ , 8+512+TL )
	
	NG.NG_Window = Object.NG_Window(pwindow)
	
	If NG <> Null
	
		;-----------------------------------------
		; si elle est créée par le File Selector
		;-----------------------------------------
		If NG_InfoWindow = True
		
			NG\FileSelectorPermission = True
		
		EndIf
			
		;------------------------------------------------
		; création des gadgets selon le type de preview
		;------------------------------------------------
		
		;---------------------------
		; Image Preview
		;---------------------------
		If media$ = "image"
		
			; les boutons zoom -/+
			NG\moins   = NG_CreateButton ( pwindow , 10 , 30 , 20 , 20 , "-" )
			NG\plus    = NG_CreateButton ( pwindow , 31 , 30 , 20 , 20 , "+" )
			
			; l'image
			NG\image = NG_CreateImage ( pwindow , file$ , 10 , 60 , 0 , 0 , 0 , -1 , -1 , -1 )
			NG\image0 = LoadImage ( file$ )
		
		;---------------------------
		; Video Preview
		;---------------------------
		Else If media$ = "video"
		
			; les boutons zoom -/+
			NG\moins   = NG_CreateButton ( pwindow , 10 , 30 , 20 , 20 , "-" )
			NG\plus    = NG_CreateButton ( pwindow , 31 , 30 , 20 , 20 , "+" )
			
			; la video
			NG\video = OpenMovie (file$)
			
			; resize fenetre
			NG\videoTX = MovieWidth (NG\video)
			NG\videoTY = MovieHeight (NG\video)
			
			Tx = NG\videoTX + 20
			Ty = NG\videoTY + 70
			
			NG_SetWindowSize ( pwindow , Tx , Ty )			
		
		;---------------------------
		; Sound Preview
		;---------------------------
		Else If media$ = "son"
		
			; texte
			NG\txt = NG_CreateText ( pwindow , 10 , 30 , file$ , 60 )
			NG\base_txt$ = file$
			NG\new_txt$  = file$
			
			; le son/la musique
			NG\sound = LoadSound (file$)
			LoopSound NG\sound 
			PlaySound NG\sound
						
			; resize fenetre
			NG_SetWindowSize ( pwindow , 400 , 100 )
			
		;---------------------------
		; 3D Preview
		;---------------------------
		Else If media$ = "3d"
		
			; les boutons zoom -/+
			NG\moins   = NG_CreateButton ( pwindow , 10 , 30 , 20 , 20 , "-" )
			NG\plus    = NG_CreateButton ( pwindow , 31 , 30 , 20 , 20 , "+" )
			
			; les boutons up/down
			NG\bup   = NG_CreateArrowButton ( pwindow , 61 , 30 , "UP" )
			NG\bdown = NG_CreateArrowButton ( pwindow , 83 , 30 , "DOWN" )
			
			; le doseur Zoom
			NG\DoseurZ = NG_CreateDoseur ( pwindow , 120 , -10 , 100 , "" , -5 , +5 , 0 , 4 )
			
			; le doseur Rotation
			NG\DoseurR = NG_CreateDoseur ( pwindow , 230 , -10 , 100 , "" , -10 , +10 , 0 , 4 )
			
			; loin de la scène 3d
			x = Rnd (-60000,-100000)
			y = Rnd (-60000,-100000)
			z = Rnd (-60000,-100000)			
						
			; le model 3d
			If ext$ = ".md2"
			
				NG\model = LoadMD2 ( file$ )
							
			Else
			
				NG\model = LoadMesh ( file$ )
			
			EndIf
			
			PositionEntity NG\model , x , y , z
			
			; la camera
			NG\pivot = CreatePivot ( NG\model )
			NG\cam = CreateCamera ( NG\pivot )
			MoveEntity NG\cam , 0 , 0 , -30
			
			; la lumière
			NG\light = CreateLight( NG\model )
			
			; la 3d frame
			NG\frame3d = NG_Create3dFrame ( pwindow , 10 , 60 , iTx , iTy , NG\cam )
			NG\FrameTX = iTX
			NG\FrameTY = iTY
		
		;---------------------------
		; Text Preview
		;---------------------------
		Else If media$ = "texte"
		
			; les boutons zoom -/+
			NG\moins   = NG_CreateButton ( pwindow , 10 , 30 , 20 , 20 , "-" )
			NG\plus    = NG_CreateButton ( pwindow , 31 , 30 , 20 , 20 , "+" )
			
			NG\textTX = iTX
			NG\textTY = iTY
					
			; le texte
			txt = ReadFile ( file$ )
	
			While Eof (txt) = False
				
				ligne$ = ReadLine$ ( txt ) 
			
				texte$ = texte$ + ligne$
			
			Wend
			
			CloseFile txt
			
			NG\minput = NG_CreateMultiInput ( pwindow , 10 , 60 , iTx , iTy , "" , 0 , texte$ )
		
			NG_SetMultiInputToTop ( NG\minput )
			
			;-----------------------
			; Taille de la fenetre
			;-----------------------
			Tx = iTx + 20 + 30
			Ty = iTy + 70
			
			NG_SetWindowSize ( pwindow , Tx , Ty )
		
		EndIf
		
		;----------------------------------------
		; cette fenetre est une fenetre preview
		;----------------------------------------
		NG\preview = True
		NG\media$ = media$
		
	EndIf
	
		
	;----------------------------------------------------------
	; on renvoie, au cas où, le handle de la fenetre Preview
	;----------------------------------------------------------
	Return pwindow
	
	
End Function
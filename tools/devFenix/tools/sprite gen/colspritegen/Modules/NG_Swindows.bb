;------------------------------------------------------------------------
; Modules : création et gestion des fenetres spéciales
;------------------------------------------------------------------------

;--------------------------------------------------------------------------
; Crée une fenetre Question
;--------------------------------------------------------------------------
; Attention : cette fenetre gèle le temps jusqu'à ce qu'elle soit validée
;--------------------------------------------------------------------------
Function NG_CreateInfoWindow ( titre$ , texte_info$ , bouton1$="OK" , bouton2$="" , bouton3$="" )

	;---------------------------------------------------------------------------------------------------
	; si la longueur du titre est plus grande que celle du texte, alors c'est sa longueur la reference
	;---------------------------------------------------------------------------------------------------
	If Len(texte_info$) > Len(titre$)
		texte$ = texte_info$
	Else
		texte$ = titre$
	EndIf 

	;----------------------------------------------
	; calcul de la taille de la question en pixel
	;----------------------------------------------
	tailleX = StringWidth ( texte$ )
	
	;-------------------------------------------------------------------------------------------
	; si la taille de la question dépasse la moitié de l'ecran, il faudra retourner à la ligne
	; ( eh oh ! pas de trop longues lignes !!)
	;-------------------------------------------------------------------------------------------
	If tailleX > (GraphicsWidth() / 2 )
		char = 200
	Else
		char = Len ( texte$ )
	EndIf
	
	;----------------------------
	; nombre de lignes donc :
	;----------------------------
	lignes = Len ( texte$ ) / char
	If lignes < 1 Then lignes = 1
	
	;------------------------------------------------
	; on active le mode NG_InfoWindow = true
	;------------------------------------------------
	NG_InfoWindow = True
	
	;----------------------
	; on crée la fenetre
	;----------------------
	winTx = tailleX + 50
	winTy = ( ( FontHeight () + 5 ) * lignes ) + 100 
	winPx = (GraphicsWidth()/2) - (winTx/2)
	winPy = (GraphicsHeight()/2) - (winTy/2)
	
	info = NG_CreateWindow ( winPx , winPy , winTx , winTy , titre$ , 1+2+8 )
	
	NG_ActiveThisWindow ( info )
	NG_AlwaysOnTop ( info )
		
	;---------------------------------
	; combien de boutons
	;---------------------------------
	If bouton1$ <> "" Then style = 1
	If bouton2$ <> "" Then style = 2
	If bouton3$ <> "" Then style = 3
		
	;-------------------------
	; Création des boutons
	;-------------------------
	SetFont NG_NormalFont
	
	BTy = FontHeight () + 10	
	
	If style = 1
		
		BTx = StringWidth ( bouton1$ ) + 30
		b1 = NG_CreateButton ( info , winTx -BTx -10 , winTy -BTy -10 , BTx , BTy , bouton1$ )
		
	Else If style = 2
	
		BTx = StringWidth ( bouton1$ ) + 30
		b1 = NG_CreateButton ( info , winTx -BTx -10 , winTy -BTy -10 , BTx , BTy , bouton1$ )
		
		BTx2 = StringWidth ( bouton2$ ) + 30
		b2 = NG_CreateButton ( info , winTx -BTx2 -10 -Btx - 10 , winTy -BTy -10 , BTx2 , BTy , bouton2$ )
		
	Else If style = 3
	
		BTx = StringWidth ( bouton1$ ) + 30
		b1 = NG_CreateButton ( info ,    winTx -BTx -10                          ,   winTy -BTy -10 , BTx , BTy , bouton1$ )
	
		BTx2 = StringWidth ( bouton2$ ) + 30
		b2 = NG_CreateButton ( info ,    winTx -BTx2 -10   -Btx -10              ,   winTy -BTy -10 , BTx2 , BTy , bouton2$ )		
		
		BTx3 = StringWidth ( bouton3$ ) + 30
		b3 = NG_CreateButton ( info ,    winTx -BTx3 -10   -Btx2 -10    -Btx -10 ,   winTy -BTy -10 , BTx3 , BTy , bouton3$ )		
		
	EndIf
	
	;--------------------------------
	; le texte
	;--------------------------------		
	NG_CreateText ( info , 20 , 20 , texte_info$ , char , 0 , NG_Font_r , NG_Font_g , NG_Font_b )
	
	;-------------------------------------------------------------------------------
	; on gèle le programme jusqu'à ce qu'on ai répondu
	;-------------------------------------------------------------------------------	
	retour = 0
	
	While retour = 0
	
		; bouton 1
		If NG_ReturnButton ( b1 ) Then retour = 1
	
		; bouton 2
		If NG_ReturnButton ( b2 ) Then retour = 2
			
		; bouton 3
		If NG_ReturnButton ( b3 ) Then retour = 3
		
		
		UpdateWorld

		RenderWorld

		NG_Update ()
				
		Flip
		
		Cls
		
	Wend
	
	;--------------------------------------------
	; on desactive le mode InfoWindow
	;--------------------------------------------
	NG_InfoWindow = False
	
	;---------------------------------------------
	; on détruit la fenetre info
	;---------------------------------------------
	NG_DeleteWindow ( info )
	
	;---------------------------------------------
	; on envoie le resultat à l'utilisateur
	;---------------------------------------------	
	Return retour
	
End Function



;--------------------------------------------------------------------------
; Crée une fenetre "A propos de l'auteur"
;--------------------------------------------------------------------------
; Attention : cette fenetre gèle le temps jusqu'à ce qu'elle soit validée
;--------------------------------------------------------------------------
Function NG_CreateAboutWindow ()

	;---------------------------------------------------------------------------------------------------
	; si la longueur du titre est plus grande que celle du texte, alors c'est sa longueur la reference
	;---------------------------------------------------------------------------------------------------
	texte$ = NG_AboutText$
	
	;----------------------------------------------
	; calcul de la taille de la question en pixel
	;----------------------------------------------
	tailleX = StringWidth ( texte$ )
	
	;-------------------------------------------------------------------------------------------
	; si la taille de la question dépasse la moitié de l'ecran, il faudra retourner à la ligne
	; ( eh oh ! pas de trop longues lignes !!)
	;-------------------------------------------------------------------------------------------
	If tailleX > (GraphicsWidth() / 2 )
		char = 200
	Else
		char = Len ( texte$ )
	EndIf
	
	;----------------------------
	; nombre de lignes donc :
	;----------------------------
	lignes = Len ( texte$ ) / char
	If lignes < 1 Then lignes = 1
	
	;------------------------------------------------
	; on active le mode NG_InfoWindow = true
	;------------------------------------------------
	NG_InfoWindow = True	
	
	;----------------------
	; on crée la fenetre
	;----------------------
	winTx = tailleX + 50
	winTy = 200 
	winPx = (GraphicsWidth()/2) - (winTx/2)
	winPy = (GraphicsHeight()/2) - (winTy/2)
	
	info = NG_CreateWindow ( winPx , winPy , winTx , winTy , NG_AboutTitle$ , 1+2+8 )
	NG_ActiveThisWindow ( info )
	NG_AlwaysOnTop ( info )
	
	;---------------------------------
	; combien de boutons
	;---------------------------------
	If bouton1$ <> "" Then style = 1
	If bouton2$ <> "" Then style = 2
	If bouton3$ <> "" Then style = 3
		
	;-------------------------
	; Création des boutons
	;-------------------------
	SetFont NG_NormalFont
	
	BTy = FontHeight () + 10	
	
	BTx = StringWidth ( "OK" ) + 30
	b1 = NG_CreateButton ( info , winTx -BTx -10 , winTy -BTy -10 , BTx , BTy , "OK" )
	
	BTx2 = StringWidth ( NG_AboutMail$ ) + 30
	b2 = NG_CreateButton ( info , winTx -BTx2 -10 -Btx - 10 , winTy -BTy -10 , BTx2 , BTy , NG_AboutMail$   )
		
	;--------------------------------
	; le texte
	;--------------------------------		
	NG_CreateText ( info , 20 , 50 , texte$ , char , 0 , NG_Font_r , NG_Font_g , NG_Font_b )
	
	;-------------------------------------------------------------------------------
	; on gèle le programme jusqu'à ce qu'on ai répondu
	;-------------------------------------------------------------------------------	
	retour = 0
	
	While retour = 0
	
		; bouton 1
		If NG_ReturnButton ( b1 ) Then retour = 1
		
		If NG_ReturnButton ( b2 ) Then retour = 2 : ExecFile "mailto:blurgzien@hotmail.com?subject=Ngui..."
		
		UpdateWorld

		RenderWorld

		NG_Update ()

		Flip
		
		Cls

	Wend
	
	;--------------------------------------------
	; on desactive le mode InfoWindow
	;--------------------------------------------
	NG_InfoWindow = False
	
	;---------------------------------------------
	; on détruit la fenetre info
	;---------------------------------------------
	NG_DeleteWindow ( info )
	
	;---------------------------------------------
	; on envoie le resultat à l'utilisateur
	;---------------------------------------------	
	Return retour
	
End Function


;--------------------------------------------------------------------------
; Crée une fenetre Requete
;--------------------------------------------------------------------------
; Attention : cette fenetre gèle le temps jusqu'à ce qu'elle soit validée
;--------------------------------------------------------------------------
Function NG_CreateRequestWindow$ ( titre$ , texte_info$ , long=400 , bouton1$="OK" , bouton2$="" )

	;---------------------------------------------------------------------------------------------------
	; si la longueur du titre est plus grande que celle du texte, alors c'est sa longueur la reference
	;---------------------------------------------------------------------------------------------------
	If Len(texte_info$) > Len(titre$)
		texte$ = texte_info$
	Else
		texte$ = titre$
	EndIf 

	;----------------------------------------------
	; calcul de la taille de la question en pixel
	;----------------------------------------------
	tailleX = StringWidth ( texte$ )
	
	;-------------------------------------------------------------------------------------------
	; si la taille de la question dépasse la moitié de l'ecran, il faudra retourner à la ligne
	; ( eh oh ! pas de trop longues lignes !!)
	;-------------------------------------------------------------------------------------------
	If tailleX > (GraphicsWidth() / 2 )
		char = 200
	Else
		char = Len ( texte$ )
	EndIf
	
	;----------------------------
	; nombre de lignes donc :
	;----------------------------
	lignes = Len ( texte$ ) / char
	If lignes < 1 Then lignes = 1
	
	;------------------------------------------------
	; on active le mode NG_InfoWindow = true
	;------------------------------------------------
	NG_InfoWindow = True	
	
	;----------------------
	; on crée la fenetre
	;----------------------
	winTx = long
	winTy = ( ( FontHeight () + 5 ) * lignes ) + 120 
	winPx = (GraphicsWidth()/2) - (winTx/2)
	winPy = (GraphicsHeight()/2) - (winTy/2)
	
	info = NG_CreateWindow ( winPx , winPy , winTx , winTy , titre$ , 1+2+8 )
	
	NG_ActiveThisWindow ( info )
	NG_AlwaysOnTop ( info )
	
	;---------------------------------
	; combien de boutons
	;---------------------------------
	If bouton1$ <> "" Then style = 1
	If bouton2$ <> "" Then style = 2
		
	;-------------------------
	; Création des boutons
	;-------------------------
	SetFont NG_NormalFont
	
	BTy = FontHeight () + 10	
	
	If style = 1
		
		BTx = StringWidth ( bouton1$ ) + 30
		b1 = NG_CreateButton ( info , winTx -BTx -10 , winTy -BTy -10 , BTx , BTy , bouton1$ )
		
	Else If style = 2
	
		BTx = StringWidth ( bouton1$ ) + 30
		b1 = NG_CreateButton ( info , winTx -BTx -10 , winTy -BTy -10 , BTx , BTy , bouton1$ )
		
		BTx2 = StringWidth ( bouton2$ ) + 30
		b2 = NG_CreateButton ( info , winTx -BTx2 -10 -Btx - 10 , winTy -BTy -10 , BTx2 , BTy , bouton2$ )
		
	EndIf
	
	;--------------------------------
	; le texte
	;--------------------------------		
	NG_CreateText ( info , 20 , 20 , texte_info$ , char , 0 , NG_Font_r , NG_Font_g , NG_Font_b )
	
	;--------------------------------
	; l'input
	;--------------------------------
	zone = NG_CreateInput ( info , 20 , 70 , winTx - 40 , 20 , "", 0 , "" , "" )
	
	;--------------------------------
	; on donne le focus à l'input
	;--------------------------------
	NG_TF_Id = zone

	;-------------------------------------------------------------------------------
	; on gèle le programme jusqu'à ce qu'on ai répondu
	;-------------------------------------------------------------------------------	
	retour$ = "NONE"
	
	While retour$ = "NONE"
	
		; touches entrée ?
		entree = False
		If KeyDown(28) Or KeyDown(156)
			entree = True
		EndIf
	
		; bouton 1
		If NG_ReturnButton ( b1 ) Or entree
			retour$ = NG_ReturnInput$ ( zone )
		EndIf
	
		; bouton 2
		If NG_ReturnButton ( b2 )
			retour$ = ""
		EndIf
		
		UpdateWorld

		RenderWorld

		NG_Update ()
		
		Flip
		
		Cls
		
	Wend
	
	;--------------------------------------------
	; on desactive le mode InfoWindow
	;--------------------------------------------
	NG_InfoWindow = False
	
	;---------------------------------------------
	; on détruit la fenetre info
	;---------------------------------------------
	NG_DeleteWindow ( info )
	
	;---------------------------------------------
	; on envoie le resultat à l'utilisateur
	;---------------------------------------------	
	Return retour$
	
End Function


;-----------------------------------------------------
; Crée la fenetre selecteur de fichiers
;-----------------------------------------------------
; Cette fenetre est crée à l'initialisation
;-----------------------------------------------------
Function NG_CreateFileSelector ()

	xdisp = GraphicsWidth()
	ydisp = GraphicsHeight()
	
	xmid = xdisp / 2
	ymid = ydisp / 2
	
	;--------------------------------------------------
	; le chemin par défaut est celui de l'application
	;--------------------------------------------------
	NG_FS_dirpath$ = CurrentDir$()
	
	If Right$ ( NG_FS_dirpath$ , 1 ) <> "\" Then NG_FS_dirpath$ = NG_FS_dirpath$ + "\"

	;-------------------------------
	; on crée la fenetre
	;-------------------------------
	NG_FS = NG_CreateWindow ( xmid - 305 , ymid - 205 , 630 , 410 , NG_FStitle$ , 1+8 )
	
	;-----------------------------------------
	; Permission du File Selector
	;-----------------------------------------
	NG.NG_Window = Object.NG_Window(NG_FS)
	
	If NG <> Null

		NG\FileSelectorPermission = True
	
	EndIf
	
	;-------------------------------
	; chemin repertoire
	;-------------------------------
	NG_FSpath = NG_CreateInput ( NG_FS , 10 , 40 , 340 , 20 , NG_FSpathLabel$ )
	
	;-------------------------------
	; Icones raccourcis
	;-------------------------------
	NG_FSshortcut = NG_CreateIconViewer ( NG_FS , 10 , 70 , 1 , 5 , 2 , 32 , 1 )
	
	;-------------------------------
	; Icones fichiers
	;-------------------------------
	NG_FSfiles = NG_CreateIconViewer ( NG_FS , 170 , 70 , 2 , 9 , 1 , 16 , True )
	
	;-------------------------------
	; zone saisie nom de fichier
	;-------------------------------
	NG_FSfilename = NG_CreateInput ( NG_FS , 170 , 340 , 315 , 20 , NG_FSfilenameLabel$ )
	
	;-----------------------------------
	; zone choix extension de fichier
	;-----------------------------------
	NG_FSextension = NG_CreateCombo ( NG_FS , 170 , 370 , 324 , 20 , NG_FSextensionLabel$ )
		NG_PerformExtensionList ( "*.*#.jpg#.htm" )
		NG_SetCombo ( NG_FSextension , "*.*" )
		
		NG_FS_ExtSel$ = NG_ReturnCombo$ ( NG_FSextension )
	
	;-----------------------------------
	; boutons
	;-----------------------------------
	NG_FSok = NG_CreateButton ( NG_FS , 540 , 339 , 80 , 25 , "OK" )
	NG_FScancel = NG_CreateButton ( NG_FS , 540 , 370 , 80 , 25 , "Cancel" )
	
	NG_FSgo = NG_CreateButton ( NG_FS , 495 , 39 , 30 , 23 , "go" )
	NG_FSprev    = NG_CreateArrowButton ( NG_FS , 533 , 39 , "LEFT" )
	NG_FSnew     = NG_CreateButton ( NG_FS , 561 , 39 , 23 , 23 , "*" )
	NG_FSpreview = NG_CreateButton ( NG_FS , 589 , 39 , 23 , 23 , "P!" )
	
	;-----------------------------------------------------------
	; ajout des raccourcis de base, notamment les disques durs
	;-----------------------------------------------------------
	For i = 65 To 90 ; de A à Z
	
		;If FileType ( Chr$ (i) +":\" ) = 2
		
			NG_AddShorcut ( Chr$ (i) +":\" )
	
		;EndIf
	
	Next
	
	NG_AddShorcut ( NG_FS_dirpath$ )
	NG_AddShorcut ( SystemProperty ("windowsdir") )
	NG_AddShorcut ( SystemProperty ("appdir") )
	
	;---------------------------------
	; on ferme le selecteur
	;---------------------------------	
	NG_CloseWindow ( NG_FS )
	
End Function


;-----------------------------------------------------
; Crée la fenetre Color Picker
;-----------------------------------------------------
; Cette fenetre est crée à l'initialisation
;-----------------------------------------------------
Function NG_CreateColorPicker ()

	xdisp = GraphicsWidth()
	ydisp = GraphicsHeight()
	
	xmid = xdisp / 2
	ymid = ydisp / 2
	
	;-------------------------------
	; on crée la fenetre
	;-------------------------------
	NG_CP = NG_CreateWindow ( xmid - 265 , ymid - 205 , 530 , 410 , NG_CPtitle$ , 1+8 )
	
	
	;-----------------------------------
	; visualisateur d'images
	;-----------------------------------
	NG_CPimage = NG_CreateImage ( NG_CP , "" , 10 , 50 , 256 , 256 )
	
	NG_SetImageTrueGadget ( NG_CPimage )
	
	;-----------------------------------
	; autres gadgets
	;-----------------------------------
	frame = NG_CreateFrame ( NG_CP , 285 , 50 , 225 , 255 , NG_CPRed$ + "/" + NG_CPGreen$ + "/" + NG_CPBlue$ )
	
	NG_CPinpR = NG_CreateInput ( NG_CP , 295 , 100 , 50 , 20 , NG_CPRed$ + " : " , 2 )
	NG_CPinpG = NG_CreateInput ( NG_CP , 295 , 130 , 50 , 20 , NG_CPGreen$ + " : " , 2 )
	NG_CPinpB = NG_CreateInput ( NG_CP , 295 , 160 , 50 , 20 , NG_CPBlue$ + " : " , 2 )
	
	NG_CPSwitch = NG_CreateDoseur ( NG_CP , 295 , 180 , 200 , NG_CPClair$+"/"+NG_CPObscur$ , 0 , 255 , 255 , 1+2+4 )
	
	NG_CPDoseur = NG_CreateDoseur ( NG_CP , 295 , 225 , 200 , NG_CPMelange$  , 0 , 2 , 0 , 1+2+4 )
	
	;-----------------------------------
	; boutons
	;-----------------------------------
	NG_CPok = NG_CreateButton ( NG_CP , 440 , 339 , 80 , 25 , "OK" )
	NG_CPcancel = NG_CreateButton ( NG_CP , 440 , 370 , 80 , 25 , "Cancel" )
	
	;---------------------------------
	; on ferme le selecteur
	;---------------------------------	
	NG_CloseWindow ( NG_CP )
	
End Function


;-----------------------------------------------------------------
; va au repertoire parent sans changer de repertoire via blitz
;-----------------------------------------------------------------
Function NG_PreviousDirectory$ ( path$ )

	;------------------------------------------
	; sauf si l'on est à la racine
	;------------------------------------------
	If Len (path$) <= 3
		Return path$
	EndIf

	;-------------------------------------------
	; sinon c'est OK
	;-------------------------------------------
	loop$ = ""
	car$  = ""
	pos = 0
	
	;------------------------------------------------------
	; reperage du dernier "\", sauf le tout tout dernier
	;------------------------------------------------------
	For i = 1 To Len ( path$ )
	
		If i <> Len ( path$ )
		
			loop$ = Mid$ ( path$ , i , 1 )
		
			If loop$ = "\"
			
				car$ = loop$
				pos = i
				
			EndIf
			
		EndIf
		
	Next
	
	new_path$ = ""
	loop$ = ""
	
	;-----------------------------------------------------
	; copie de tout jusqu'au dernier "\"
	;-----------------------------------------------------
	For i = 1 To pos
		
		loop$ = Mid$ ( path$ , i , 1 )
		new_path$ = new_path$ + loop$ 
	
	Next
	
	If Right$ ( new_path$ , 1 ) <> "\"
	
		new_path$ = new_path$ + "\"
	
	EndIf
	
	Return new_path$
	
End Function

;--------------------------------------------
; ajoute un élément à la liste des fichiers
;--------------------------------------------
Function NG_AddFileList ( typ , name$ , ext$ )

	FS.NG_FileList = New NG_FileList
	
		FS\typ = typ
		
		FS\name$ = name$
		
		FS\ext$ = ext$
		
	NG_FileListNo = NG_FileListNo + 1
	
	;----------------------------------------------------------
	; place le fichier dans l'ordre alphabetique
	;----------------------------------------------------------
	If NG_FileListNo > 1
	
		FL.NG_FileList = First NG_FileList
	
		count = 1
	
		While count < NG_FileListNo
		
			If Upper$(FL\name$) > Upper$(FS\name$)
			
				Insert FS Before FL
				
				count = NG_FileListNo
			
			EndIf
			
			count = count + 1
			
			FL = After FL
		
		Wend
	
	EndIf
			
End Function









;------------------------------------------
; efface la liste des fichiers créée 
;------------------------------------------
Function NG_DeleteFileList ()

	For FL.NG_filelist = Each NG_filelist
	
		Delete FL
		
	Next
	
End Function

;-----------------------------------------------------------------
; met à jour la liste des dossiers dans le selecteur de fichiers
;-----------------------------------------------------------------
Function NG_CheckDir ( rep$ )

	;-----------------------------------------
	; si le répertoire n'existe pas
	;-----------------------------------------
	If FileType ( rep$ ) <> 2
	
		Return
		
	EndIf
	
	;---------------------------------
	; on l'ouvre
	;---------------------------------
	dir = ReadDir ( rep$ )
	
	;-----------------------------------------------
	; on supprime toutes les anciennes icones
	;-----------------------------------------------
	NG_DeleteIconViewerIcons ( NG_FSfiles )
	
	;-----------------------------------------------------
	; on lit les fichiers et on crée les icones
	;-----------------------------------------------------
	NG_FileListNo = 0
	
	Repeat
	
		file$ = NextFile$ ( dir )
	
		If file$ = "" Then Exit
	
		;-----------------------------------
		; si c'est un repertoire
		;-----------------------------------
		If FileType ( rep$+file$ ) = 2
		
			NG_AddFileList ( 2 , file$ , "" )
					
		;----------------------------------
		; si c'est un fichier
		;----------------------------------
		Else
			
			;---------------------------------------------------------------
			; vérifie l'extension entrée dans la zone "type de fichier"
			;---------------------------------------------------------------
			tipe$ = NG_ReturnCombo$ ( NG_FSextension )
			
			tipe$ = Lower$ (tipe$)
			
			;--------------------------------------
			; récupère l'extension
			;--------------------------------------
			car$ = ""
			ext$ = ""
			
			start = 0

			For i = 1 To Len (file$)
			
				car$ = Mid$ ( file$ , i , 1 )
						
				If car$ = "." Then start = 1
				
				If start = 1
								
					ext$ = ext$ + car$
				
				EndIf				
				
			Next
			
			ext$ = Lower$ ( ext$ )
			
			;---------------------------------------------------------------------------------
			; on ne valide que s'il s'agit de l'extension choisie ou de toutes (*.*)
			;---------------------------------------------------------------------------------
			If ( tipe$ = ext$ ) Or ( Left$ ( tipe$ , 1 ) <> "." )
			
				NG_AddFileList ( 1 , file$ , ext$ )
											
			EndIf
				
		End If
		
	Forever
	
	;----------------------------------------
	; crée les icones
	;----------------------------------------	
	NG_CreateFileSelectorIcons ()
	
	;----------------------------------------
	; le nom du repertoire dans la zone
	;----------------------------------------
	NG_SetInput ( NG_FSpath , rep$ )	
	
	;-----------------------------
	; on ferme le repertoire
	;-----------------------------
	CloseDir dir
	
	;--------------------------------------
	; on rétablit l'ancien
	;--------------------------------------
	; ...

End Function


; variables pour l'ordre alphabétique
Global NG_FileListNo
Global NG_AlphabetPos = 1
Global NG_Alphabet$ = " ._0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

;-----------------------------------------------------
; classe par ordre alphabétique
;-----------------------------------------------------
Function NG_SortAlphabet ()

	Long = Len ( NG_Alphabet$ )
	
	;---------------------------------------------------
	; on retrouve la lettre de l'alphabet à vérifier
	;---------------------------------------------------
	Lettre$ = Mid$ ( NG_Alphabet$ , NG_AlphabetPos , 1 )
	
	;---------------------------------------
	; limites de l'alphabet
	;---------------------------------------	
	If NG_AlphabetPos >= ( Long - 1 )
	
		NG_AlphabetPos = 1
		
	EndIf
	
	Return Lettre$
	
End Function

;-----------------------------------------------------------------
; met à jour la liste des dossiers dans le selecteur de fichiers
;-----------------------------------------------------------------
Function NG_CreateFileSelectorIcons ()

	;-----------------------------------------------
	; d'abord les répertoires
	;-----------------------------------------------
	FL.NG_FileList = First NG_FileList	
	
	For FL.NG_FileList = Each NG_FileList
		
		If FL\typ = 2
		
			NG_AddIcon ( NG_FSfiles , NG_IconFolder , FL\name$ )
			
		EndIf
	
	Next
	
	;-----------------------------------------------
	; ensuite les fichiers
	;-----------------------------------------------
	tipe$ = NG_ReturnCombo$ ( NG_FSextension )
	
	For FL.NG_FileList = Each NG_FileList
	
		If FL\typ =1
				
			;---------------------------------------------------------------------------------
			; on ne valide que s'il s'agit de l'extension choisie ou de toutes (*.*)
			;---------------------------------------------------------------------------------
			If ( tipe$ = FL\ext$ ) Or ( Left$ ( tipe$ , 1 ) <> "." )
			
				;--------------------------------------
				; fichier video
				;--------------------------------------
				If FL\ext$=".avi" Or FL\ext$=".mpg" Or FL\ext$=".wmv" Or FL\ext$=".ram" Or FL\ext$=".mpeg" Or FL\ext$=".asf"
					
					NG_AddIcon ( NG_FSfiles , NG_IconVideo , FL\name$ )
					
				;--------------------------------------
				; fichier audio
				;--------------------------------------
				Else If FL\ext$=".wav" Or FL\ext$=".mp3" Or FL\ext$=".wma" Or FL\ext$=".mid" Or FL\ext$=".mod"  
				
					NG_AddIcon ( NG_FSfiles , NG_IconAudio , FL\name$ )
					
				;--------------------------------------
				; fichier photo
				;--------------------------------------
				Else If FL\ext$=".bmp" Or FL\ext$=".jpg" Or FL\ext$=".gif" Or FL\ext$=".png" Or FL\ext$=".pcx" Or FL\ext$=".tif" 
				
					NG_AddIcon ( NG_FSfiles , NG_IconPhoto , FL\name$ )
				
				;--------------------------------------
				; fichier texte ou html
				;--------------------------------------
				Else If FL\ext$=".txt" Or FL\ext$=".doc" Or FL\ext$=".htm" Or FL\ext$=".rtf" Or FL\ext$=".html"
				
					NG_AddIcon ( NG_FSfiles , NG_IconText , FL\name$ )
				
				;--------------------------------------
				; fichier exe ou équivalent
				;--------------------------------------
				Else If FL\ext$=".exe" Or FL\ext$=".bat"
				
					NG_AddIcon ( NG_FSfiles , NG_IconExe , FL\name$ )
				
				;--------------------------------------
				; fichier système ou autre
				;--------------------------------------
				Else
				
					NG_AddIcon ( NG_FSfiles , NG_IconSystem , FL\name$ )
				
				EndIf
				
			EndIf
				
		End If
		
	Next
	
	;-----------------------------------------
	; on supprime la liste des fichiers
	;-----------------------------------------
	NG_DeleteFileList ()

End Function


;-----------------------------------------------------------
; règle le file selector sur un repertoire
;-----------------------------------------------------------
Function NG_SetFileSelectorPath ( path$ )

	new_path$ = path$
	
	If FileType ( new_path$ ) <> 2
	
		Return
		
	EndIf
	
	If Right$ ( new_path$ , 1 ) <> "\"
	
		new_path$ = new_path$ + "\"
		
	EndIf	
	
	NG_FS_dirpath$ = new_path$
		
	NG_CheckDir ( NG_FS_dirpath$ )
	
End Function

;-----------------------------------------------------------
; crée un liste d'extension pour un selecteur de fichier
;-----------------------------------------------------------
Function NG_PerformExtensionList ( liste$ )

	;--------------------------------------
	; d'abord on vide la liste
	;--------------------------------------
	NG_DeleteAllComboItem ( NG_FSextension )

	;----------------------------------
	; longueur de la liste
	;----------------------------------
	long = Len ( liste$ )+1
	
	;-----------------------
	; varaiables
	;-----------------------
	car$ = ""
	ext$ = ""
	
	no = 1
	
	;-------------------------------------
	; extraction
	;-------------------------------------
	For i = 1 To Long
			
		car$ = Mid$ ( liste$ , i , 1 )
				
		;------------------------------------------------------------------------
		; on a trouvé une extension, on l'enregistre et on passe à la suivante 
		;------------------------------------------------------------------------
		If car$ = "#" Or i = long
	
			NG_AddComboItem ( NG_FSextension , ext$ )

			If no = 1 Then NG_SetCombo ( NG_FSextension , ext$ )

			car$ = ""
			ext$ = ""

			no = no + 1
			
		EndIf
		
		ext$ = ext$ + car$
		
	Next

End Function

;--------------------------------------------------------------------------
; change ls textes du selecteur de fichier
;--------------------------------------------------------------------------
Function NG_SetFileSelectorLabels$ ( title$ , ok$="" , cancel$="" )

	NG_SetWindowTitle ( NG_FS , title$ , 1 )
	
	;----------------
	; bouton OK
	;----------------
	If ok$ <> ""
		NG_SetButtonLabel ( NG_FSok , ok$ )
	EndIf

	;----------------
	; bouton cancel
	;----------------
	If cancel$ <> ""
		NG_SetButtonLabel ( NG_FScancel , cancel$ )
	EndIf

End Function


;------------------------------------------------------------------
; rend la selecteur de fichier bougeable/non bougeable
;------------------------------------------------------------------
Function NG_SetFileSelectorMovable ( on=True )

	NG_SetWindowMovable ( NG_FS , on )
	
End Function


;--------------------------------------------------------------------------
; Ouvre le sélecteur de fichier
;--------------------------------------------------------------------------
; Attention : cette fenetre gèle le temps jusqu'à ce qu'elle soit validée
;--------------------------------------------------------------------------
Function NG_OpenFileSelector$ ( filed=1 , folderonly=False )

	;-------------------------------------------------
	; Selecteur de fichier ouvert
	;-------------------------------------------------
	NG_FSopen = True

	;-------------------------------------------------
	; regle sur la derniere extension filtree
	;-------------------------------------------------
	NG_SetCombo ( NG_FSextension , NG_FS_Lastext$ )

	;---------------------------------------
	; on lit le répertoire
	;---------------------------------------
	NG_CheckDir ( NG_FS_dirpath$ )
		
	;-------------------------------------------------
	; on ouvre le selecteur de fichiers
	;-------------------------------------------------
	NG_OpenWindow ( NG_FS )
	
	NG_ActiveThisWindow ( NG_FS )
	
	;----------------------
	; zou, au 1er plan
	;----------------------
	NG_AlwaysOnTop ( NG_FS )
	
	;--------------------------------------------
	; on active le mode InfoWindow
	;--------------------------------------------
	NG_InfoWindow = True
	
	NG_FSOpen = True
	
	;-------------------------------------------------------------------------------
	; on gèle le programme jusqu'à ce qu'on ai répondu
	;-------------------------------------------------------------------------------	
	retour$ = "NONE"
	
	While retour$ = "NONE"
	
		;-----------------------------------------
		; vérifie si on a cliqué sur un icone
		;-----------------------------------------
		If NG_MouseUp1
		
			file$ = NG_ReturnIconViewerIcon$ ( NG_FSfiles )
			
			;------------------------------------------------------------
			; on vérifie si on a double cliqué
			;------------------------------------------------------------
			; le double clic ne fonctionne que dans la zone des icones
			;------------------------------------------------------------
			DC = 0
			
			If NG_DoubleClic ()
			
				;------------------------------------------------------
				; si l'on a double cliqué sur la liste des fichiers
				;------------------------------------------------------
				If NG_OnIconViewer = NG_FSfiles
			
					DC = 1
					
				;------------------------------------------------------
				; si l'on a double cliqué sur la liste des raccourcis
				;------------------------------------------------------
				Else If NG_OnIconViewer = NG_FSshortcut
				
					DC = 2
				
				EndIf
			
			EndIf
			
			;---------------------------------------
			; si c'est sur un fichier
			;---------------------------------------
			If FileType ( NG_FS_dirpath$ + file$ ) = 1
			
				NG_SetInput ( NG_FSfilename , file$ )
				
			;--------------------------------------------------------------------------
			; si c'est sur un repertoire (double clic obligatoire pour interactions)
			;--------------------------------------------------------------------------
			Else If FileType ( NG_FS_dirpath$ + file$ ) = 2 And DC = 1
			
				;------------------------------------
				; repertoire précedent
				;------------------------------------
				If file$ = ".."
				
					NG_FS_dirpath$ = NG_PreviousDirectory$ ( NG_FS_dirpath$ )
					
					NG_CheckDir ( NG_FS_dirpath$ )
					
				;------------------------------------
				; racine
				;------------------------------------
				Else If file$ = "."

					NG_FS_dirpath$ = Mid$ ( NG_FS_dirpath$ , 1 , 3 )
					
					NG_CheckDir ( NG_FS_dirpath$ )
					
				;------------------------------------
				; repertoire enfant
				;------------------------------------
				Else
				
					If Len (NG_FS_dirpath$ + file$) > Len (NG_FS_dirpath$)
				
						NG_FS_dirpath$ = NG_FS_dirpath$ + file$ + "\"
						
						NG_CheckDir ( NG_FS_dirpath$ )
					
					EndIf			
				
				EndIf
			
			EndIf
			
			;--------------------------------------------------------------
			; si on a double cliqué sur un raccourci
			;--------------------------------------------------------------
			If DC = 2
			
				short$ = NG_ReturnIconViewerIcon$ ( NG_FSshortcut )
							
				NG_FS_dirpath$ = short$
					
				NG_CheckDir ( NG_FS_dirpath$ )
			
			EndIf
			
			;--------------------------------------------------------------
			; si on a changé de type de fichier extension
			;--------------------------------------------------------------			
			new_sel$ = NG_ReturnCombo$ ( NG_FSextension )
			
			If NG_FS_ExtSel$ <> new_sel$
			
				NG_FS_ExtSel$ = NG_ReturnCombo$ ( NG_FSextension )
			
			 	;---------------------------------------
				; on réactualise
				;---------------------------------------
				NG_CheckDir ( NG_FS_dirpath$ )

			EndIf
		
		
		EndIf
		
				
		;-----------------------------------------------------------------
		; suppresson d'un fichier ou d'un repertoire via l'explorateur
		;-----------------------------------------------------------------
		If KeyHit (211) And NG_TF_Id = -1 ; SUPPR/DEL
		
			F$ = NG_ReturnIconViewerIcon$ ( NG_FSfiles )
			
			sorte = FileType ( NG_FS_dirpath$ + F$ )
			
			;------------------------
			; fichier
			;------------------------
			If sorte = 1
		
				choix = NG_CreateInfoWindow ( NG_FSsupprTitle$ , NG_FSsupprLabel$ , "OK" , NG_FSsupprCancel$ )
		
				If choix = 1 Then DeleteFile NG_FS_dirpath$ + F$
				
				NG_CheckDir ( NG_FS_dirpath$ )
				
				;---------------------------
				; on reste dans ce mode
				;---------------------------
				NG_InfoWindow = True
				NG_ActiveThisWindow ( NG_FS )
				NG_AlwaysOnTop ( NG_FS )
			
			;------------------------
			; répertoire
			;------------------------
			Else If sorte = 2 
		
				choix = NG_CreateInfoWindow ( NG_FSsupprTitle$ , NG_FSsupprLabel$ , "OK" , NG_FSsupprCancel$ )
		
				If choix = 1 Then DeleteDir NG_FS_dirpath$ + F$
				
				NG_CheckDir ( NG_FS_dirpath$ )
				
				;---------------------------
				; on reste dans ce mode
				;---------------------------
				NG_InfoWindow = True
				NG_ActiveThisWindow ( NG_FS )
				NG_AlwaysOnTop ( NG_FS )
			
		
			EndIf
			
		EndIf
		
		;-----------------------------
		; go par touche entrée
		;-----------------------------
		goNow = False
		
		If NG_TF_Id = NG_FSpath
		
			If KeyDown (28) Or KeyHit(28) Or KeyDown (156) Or KeyHit (156)
			
				goNow = True

			EndIf
		
		EndIf
		
		;-----------------------------
		; bouton go 
		;-----------------------------
		If NG_ReturnButton ( NG_FSgo ) Or goNow = True
			
			path$ = NG_ReturnInput$ ( NG_FSpath )
			
			If path$ <> ""
			
				NG_FS_dirpath$ = path$
				
			EndIf
								
			NG_CheckDir ( NG_FS_dirpath$ )

		EndIf
		
		;-----------------------------
		; bouton Preview ! (P!)
		;-----------------------------
		If NG_ReturnButton ( NG_FSpreview )
		
			F$ = NG_ReturnIconViewerIcon$ ( NG_FSfiles )
			
			TheFile$ = NG_FS_dirpath$ + F$
			
			If FileType ( TheFile$ )

				xdisp = GraphicsWidth()
				ydisp = GraphicsHeight()
				
				xmid = xdisp / 2
				ymid = ydisp / 2
	
				xmid = xmid - 305
				ymid = ymid - 205

				xs = xmid + Rand (-50,+50)
				ys = yMid + Rand (-50,+50)
				
				NG_Preview ( xs , ys , TheFile$ )
			
			EndIf
		
		EndIf
		
		
		;-----------------
		; bouton previous
		;-----------------
		If NG_ReturnButton ( NG_FSprev )
		
			NG_FS_dirpath$ = NG_PreviousDirectory$ ( NG_FS_dirpath$ )
			
			NG_CheckDir ( NG_FS_dirpath$ )
			
		EndIf
		
		;--------------------------
		; bouton nouveau dossier
		;--------------------------
		If NG_ReturnButton ( NG_FSnew )
		
			nom$ = NG_CreateRequestWindow$ ( NG_FSNewFolderTitle$ , NG_FSNewFolderText$ , 400 , "OK" , NG_FSsupprCancel$ )
								
			If nom$ <> ""
			
				CreateDir NG_FS_dirpath$ + nom$ 
				
				NG_CheckDir ( NG_FS_dirpath$ )
			
			EndIf
			
			;---------------------------
			; on reste dans ce mode
			;---------------------------
			NG_InfoWindow = True
			NG_ActiveThisWindow ( NG_FS )
			NG_AlwaysOnTop ( NG_FS )

		EndIf

		
		
		;-----------------
		; bouton cancel
		;-----------------
		If NG_ReturnButton ( NG_FScancel )
			retour$ = ""
		EndIf
		
		;------------------
		; bouton x
		;------------------
		If NG_ReturnWindowOpen (fenetreExp) = False
			;retour$ = ""
		EndIf
		
		;-----------------------------
		; bouton OK
		;-----------------------------
		If NG_ReturnButton ( NG_FSok )
		
			thefile$ = NG_ReturnInput ( NG_FSfilename )
			
			selfile$ = NG_ReturnIconViewerIcon$ ( NG_FSfiles )
			
			;------------------------------------------------
			; clic sur un fichier
			;------------------------------------------------
			If thefile$ <> "" Or folderonly = True
		
				If filed = 1 ; juste le nom du fichier
			
					If folderonly = False
			
						retour$ = NG_ReturnInput ( NG_FSfilename )
				
					Else
					
						retour$ = NG_FS_dirpath$
					
					EndIf
					
				
				Else ; le chemin complet !
				
					If folderonly = False
				
						retour$ = NG_FS_dirpath$ + NG_ReturnInput ( NG_FSfilename )
			
					Else
					
						retour$ = NG_FS_dirpath$
					
					EndIf
			
			
				EndIf
											
			EndIf
		
		EndIf
		
				
		;-----------------------------
		; double clic
		;-----------------------------
		If DC = 1 And NG_DoubleClic ()
		
			F$ = NG_ReturnIconViewerIcon$ ( NG_FSfiles )
			
			sorte = FileType ( NG_FS_dirpath$ + F$ )
			
			;------------------------
			; fichier
			;------------------------
			If sorte = 1
		
				If filed = 1 ; juste le nom du fichier
			
					retour$ = NG_ReturnInput ( NG_FSfilename )
				
				Else ; le chemin complet !
				
					retour$ = NG_FS_dirpath$ + NG_ReturnInput ( NG_FSfilename )
			
				EndIf
			
			EndIf
		
		EndIf
		
		;---------------------------------------------------
		; validation par double clic
		;---------------------------------------------------
		If NG_DoubleClic () And NG_ReturnInput ( NG_FSfilename ) <> ""
			;retour$ = NG_ReturnInput ( NG_FSfilename )
		EndIf
		
		
		
		UpdateWorld

		RenderWorld

		NG_Update ()
		
		Flip
		
		Cls	
		
		
	Wend
	
	;--------------------------------------------
	; enregistre la derniere extension filtrée
	;--------------------------------------------
	NG_FS_Lastext$ = NG_ReturnCombo$ ( NG_FSextension )
	
	
	;--------------------------------------------
	; on desactive le mode InfoWindow
	;--------------------------------------------
	NG_InfoWindow = False
	
	NG_FSOpen = False
	
	;---------------------------------------------
	; on détruit la fenetre info
	;---------------------------------------------
	NG_CloseWindow ( NG_FS )
	
	NG_DisactiveThisWindow ( NG_FS )
	
	FlushMouse

	;-----------------------------------------------------------------------
	; destruction de toutes les fenetres Preview crées par le File Selector
	;-----------------------------------------------------------------------
	For NG.NG_Window = Each NG_Window
	
		If NG\media$ <> ""
		
			If NG\FileSelectorPermission = True
			
				NG_DeleteWindow (NG\Id)
			
			EndIf
		
		EndIf
	
	Next
	
	;---------------------------------------------
	; on envoie le resultat à l'utilisateur
	;---------------------------------------------	
	Return retour$
	
End Function


;-----------------------------------------------------------------------------
; Retourne la derniere extension selectionnée dans le selecteur de fichiers
;-----------------------------------------------------------------------------
Function NG_ReturnFileSelectorExtension$ ()

	Return NG_FS_Lastext$

End Function


;--------------------------------------------------------------------------
; Ouvre le color picker
;--------------------------------------------------------------------------
; Attention : cette fenetre gèle le temps jusqu'à ce qu'elle soit validée
;--------------------------------------------------------------------------
Function NG_OpenColorPicker ( r=-1 , G=-1 , B=-1 , Mode=-1 )

	;-------------------------------------------------
	; on ouvre le coor picker
	;-------------------------------------------------
	NG_OpenWindow ( NG_CP )
	
	NG_ActiveThisWindow ( NG_CP )
	
	;----------------------
	; zou, au 1er plan
	;----------------------
	NG_AlwaysOnTop ( NG_CP )
	
	;--------------------------------------------
	; on active le mode InfoWindow
	;--------------------------------------------
	NG_InfoWindow = True
	
	ancien_R = NG_CPr
	ancien_G = NG_CPg
	ancien_B = NG_CPb
	
	If R > - 1 And G > -1 And B > -1
	
		NG_CP_Mode = mode
	
		If Mode = 1
		
			NG_CP_MX = R
			NG_CP_MY = G
			NG_SetDoseur ( NG_CPSwitch , B )
							
		Else If NG_CP_Mode = 2
				
			NG_CP_MX = R
			NG_CP_MY = B
			NG_SetDoseur ( NG_CPSwitch , G )
					
		Else If NG_CP_Mode = 3
					
			NG_CP_MX = G
			NG_CP_MY = B
			NG_SetDoseur ( NG_CPSwitch , R )
				
		EndIf
		
		NG_SaveColorsFunction ()
		
	EndIf
	
	;-------------------------------------------------------------------------------
	; on gèle le programme jusqu'à ce qu'on ai répondu
	;-------------------------------------------------------------------------------	
	retour$ = "NONE"
	
	While retour$ = "NONE"
	
		;-------------------------------------------
		; Position de references
		;-------------------------------------------	
		Xref = NG_ReturnWindowPositionX (NG_CP)
		Yref = NG_ReturnWindowPositionY (NG_CP)
			
		Xzone = Xref+10
		Yzone = Yref+50
		
		NG_SaveColors = False
	
		;---------------------------------------------------------------------
		; si un changement a été fait, il faut à nouveau rendre les couleurs
		;---------------------------------------------------------------------
		mode = NG_ReturnDoseur# ( NG_CPDoseur )
		
		mode = mode + 1
		
		mode = Int (mode)
		
		If mode <> NG_CP_Mode
		
			NG_CP_Mode = mode
			
			NG_CP_ModeRendered = False
			
		EndIf
				
		;-------------------------------------------------
		; changement clair/obscur
		;-------------------------------------------------
		If NG_OnDoseur = True And NG_OnDoseurId = NG_CPswitch
		
			NG_CPmin = Int ( NG_ReturnDoseur# ( NG_CPSwitch ) )
			
			NG_CP_PrepareToRender = True
							
		EndIf
		
		If NG_CP_PrepareToRender = True
		
			If NG_OnDoseur = False
		
				NG_CP_PrepareToRender = False
				
				NG_CP_ModeRendered = False
				
			EndIf
		
		EndIf			
		
		;----------------------------------
		; rendu du set de couleurs
		;----------------------------------
		If NG_CP_ModeRendered = False
		
			tmp = CreateImage (256,256)
		
			SetBuffer ImageBuffer (tmp)
		
			If NG_CP_Mode = 1
			
				For x = 0 To 255
					For y = 0 To 255
					
						r = x
						g = y
						b = NG_CPmin
						
						Color r , g , b
						
						Plot x , y
						
					Next
				Next
			
			;-----------------------------
			; Mode Rouge + Bleu
			;-----------------------------
			Else If NG_CP_Mode = 2
			
				For x = 0 To 255
					For y = 0 To 255
					
						r = x
						g = NG_CPmin
						b = y
						
						Color r , g , b
						
						Plot x , y
						
					Next
				Next
	
			;------------------------------
			; Mode Vert + Bleu
			;------------------------------
			Else If NG_CP_Mode = 3
			
				For x = 0 To 255
					For y = 0 To 255
					
						r = NG_CPmin
						g = x
						b = y
						
						Color r , g , b
						
						Plot x , y
						
					Next
				Next
			
			EndIf
			
			NG_SetImageFromHandleCopy ( NG_CPimage , tmp )
			
			FreeImage tmp
						
			;--------------------------------
			; c'est bon tout est rendu
			;--------------------------------
			SetBuffer BackBuffer ()
			
			NG_CP_ModeRendered = True
			
			;---------------------------------------
			; on reengistre les nouvelles couleurs
			;---------------------------------------
			NG_SaveColors = True
								
		EndIf
			
		;------------------------------------------------
		; rendu de la croix de selection de la couleur
		;------------------------------------------------
		If NG_MouseZone ( Xzone , Yzone , 255 , 255 , True )
			
			NG_CP_MX = MouseX() - Xzone
				
			NG_CP_MY = MouseY() - Yzone
				
			;------------------
			; limites
			;------------------
			If NG_CP_MX < 0   Then NG_CP_MX = 0
			If NG_CP_MY < 0   Then NG_CP_MY = 0
			If NG_CP_MX > 255 Then NG_CP_MX = 254
			If NG_CP_MY > 255 Then NG_CP_MY = 254
				
			;--------------------------------------
			; écris les valeurs dans les inputs
			;--------------------------------------
			NG_SaveColors = True
							
		EndIf
		
		
		;-------------------------------------------
		; change via les inputs
		;-------------------------------------------
		
			;--------
			; ROUGE
			;--------
			If NG_TF_Id = NG_CPinpR
			
				If NG_CP_Mode = 1 Or NG_CP_Mode = 2
					
					NG_CP_MX = NG_ReturnInput ( NG_CPinpR )
						
				Else If  NG_CP_Mode = 3
					
					NG_SetDoseur ( NG_CPswitch , NG_ReturnInput ( NG_CPinpR ) )
						
					val = Int ( NG_ReturnDoseur# ( NG_CPSwitch ) )
						
				EndIf
					
			;-------
			; VERT
			;-------			
			Else If NG_TF_Id = NG_CPinpG
				
				If NG_CP_Mode = 1
					
					NG_CP_MY = NG_ReturnInput ( NG_CPinpG )
					
				Else If  NG_CP_Mode = 2
					
					NG_SetDoseur ( NG_CPswitch , NG_ReturnInput ( NG_CPinpG ) )
						
					val = Int ( NG_ReturnDoseur# ( NG_CPSwitch ) )			
						
				Else If NG_CP_Mode = 3
					
					NG_CP_MX = NG_ReturnInput ( NG_CPinpG )
						
				EndIf
				
			;-------
			; BLEU
			;-------
			Else If NG_TF_Id = NG_CPinpB
				
				If  NG_CP_Mode = 1
					
					NG_SetDoseur ( NG_CPswitch , NG_ReturnInput ( NG_CPinpB ) )
						
					val = Int ( NG_ReturnDoseur# ( NG_CPSwitch ) )
							
				Else If NG_CP_Mode = 2 Or NG_CP_Mode = 3
					
					NG_CP_MY = NG_ReturnInput ( NG_CPinpB )
						
				EndIf
					
			EndIf
		
			
		;--------------------------------------------------
		; changement clair/obscur via inputs
		;--------------------------------------------------
		If NG_TF_Id = -1
			
			If val <> NG_CPmin
					
				NG_CPmin = Int ( NG_ReturnDoseur# ( NG_CPSwitch ) )
			
				NG_CP_PrepareToRender = True
									
			EndIf
			
		EndIf
		
		val = Int ( NG_ReturnDoseur# ( NG_CPSwitch ) )
		
		;---------------------------------------------
		; Faut-il réengistrer les nouvelles couleurs
		;---------------------------------------------
		If NG_SaveColors = True
		
			NG_SaveColorsFunction ()
						
		EndIf		
		
					
		UpdateWorld

		RenderWorld
		

		NG_Update ()
		
		;-----------------
		; bouton cancel
		;-----------------
		If NG_ReturnButton ( NG_CPcancel )
			
			retour$ = "CANCEL"
				
		EndIf
		
		;------------
		; bouton OK
		;------------
		If NG_ReturnButton ( NG_CPok )
		
			retour$ = "OK"
						
		EndIf		
			
		;----------------------
		; retrouve la couleur
		;----------------------
		GetColor Xzone + NG_CP_MX , Yzone + NG_CP_MY
			
		value = ColorRed() + ColorGreen() + ColorBlue() 
			
		;----------------------
		; sombre ou claire ?
		;----------------------
		If value < 128 * 3
			
			Color 255 , 255 , 255
				
		Else
			
			Color 0 , 0 , 0
				
		EndIf
			
		x = NG_CP_MX + Xzone
		y = NG_CP_MY + Yzone
			
		Rect x , y - 5 , 1 , 11
		Rect x - 5 , y , 11 , 1
		
				
		Flip
		
		
		Cls
			
	Wend
	
	;-----------------------
	; bouton OK
	;-----------------------
	If retour$ = "OK"
	
		NG_SaveColorsFunction ()
					
	Else If retour$ = "CANCEL"
	
		NG_CPr = Ancien_R
		NG_CPg = Ancien_G
		NG_CPb = Ancien_B
		
	EndIf
	
	;--------------------------------------------
	; on desactive le mode InfoWindow
	;--------------------------------------------
	NG_InfoWindow = False
	
	;---------------------------------------------
	; on détruit la fenetre info
	;---------------------------------------------
	NG_CloseWindow ( NG_CP )
	
	NG_DisactiveThisWindow ( NG_CP )
	
	FlushMouse
	
	;---------------------------------------------
	; enristrement des valeurs si...
	;---------------------------------------------
	
	;---------------------------------------------
	; on envoie le resultat à l'utilisateur
	;---------------------------------------------	
	;Return retour$
	
End Function

;-----------------------------------------
; enregistre les valeurs des couleurs
;-----------------------------------------
Function NG_SaveColorsFunction ()
			
	If NG_CP_Mode = 1
				
		NG_CPr = NG_CP_MX
		NG_CPg = NG_CP_MY
		NG_CPb = NG_ReturnDoseur ( NG_CPSwitch )
							
	Else If NG_CP_Mode = 2
				
		NG_CPr = NG_CP_MX
		NG_CPg = NG_ReturnDoseur ( NG_CPSwitch )
		NG_CPb = NG_CP_MY
				
	Else If NG_CP_Mode = 3
				
		NG_CPr = NG_ReturnDoseur ( NG_CPSwitch )
		NG_CPg = NG_CP_MX
		NG_CPb = NG_CP_MY
				
	EndIf
						
	NG_SetInput ( NG_CPinpR , NG_CPr )
	NG_SetInput ( NG_CPinpG , NG_CPg )
	NG_SetInput ( NG_CPinpB , NG_CPb )
			
End Function


;------------------------------------------------------------------
; rend le color picker bougeable/non bougeable
;------------------------------------------------------------------
Function NG_SetColorPickerMovable ( on=True)

	NG_SetWindowMovable ( NG_CP , on )
	
End Function
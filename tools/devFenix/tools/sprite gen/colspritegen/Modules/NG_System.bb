;--------------------------------------------
; Modules : fonctions système
;--------------------------------------------

;------------------------------
; Initialise l'interface Ngui
;------------------------------
Function NG_Initialize ()

	NG_LoadExternalFiles ()
	
	;-------------------------------------
	; création du selecteur de fichiers
	;-------------------------------------
	NG_CreateFileSelector ()
	
	;-------------------------------------
	; création du color picker
	;-------------------------------------
	NG_CreateColorPicker ()
	
	;-------------------------------------
	; language
	;-------------------------------------
	NG_TranslateInto ( NG_Language$ ) 

End Function

;-------------------------------
; Détruit correctement Ngui
;-------------------------------
Function NG_Kill ()

	; destruction de toutes les fenetres et de tous les gadgets
	NG_DeleteAllWindows ()
	
	; détruit le selecteur de fichier
	NG_DeleteWindow ( NG_FS )
	
	; détruit le color picker
	NG_DeleteWindow ( NG_CP )
	
	; détruit les fonts
	FreeFont NG_TitleFontA
	FreeFont NG_TitleFontB
	FreeFont NG_NormalFont
	
	NG_TitleFontA = 0
	NG_TitleFontB = 0
	NG_NormalFont = 0
	
	; détruit la souris
	NG_FreeMouse ( True )

	;-------------------------------------
	; libère la mémoire des images Ngui
	;-------------------------------------
	
	If NG_Sphere0 <> 0 Then FreeImage NG_Sphere0
	If NG_Sphere0_O <> 0 Then FreeImage NG_Sphere0_O
	
	If NG_Sphere1 <> 0 Then FreeImage NG_Sphere1
	If NG_Sphere1_O <> 0 Then FreeImage NG_Sphere1_O
	
	If NG_Sphere2 <> 0 Then FreeImage NG_Sphere2
	If NG_Sphere2_O <> 0 Then FreeImage NG_Sphere2_O
	
	If NG_Sphere3 <> 0 Then FreeImage NG_Sphere3
	If NG_Sphere3_O <> 0 Then FreeImage NG_Sphere3_O
	
	If NG_DefaultSkinFile <> 0 Then FreeImage NG_DefaultSkinFile
	
	If NG_Arrow_UP <> 0 Then FreeImage NG_Arrow_UP
	If NG_Arrow_DOWN <> 0 Then FreeImage NG_Arrow_DOWN
	If NG_Arrow_LEFT <> 0 Then FreeImage NG_Arrow_LEFT
	If NG_Arrow_RIGHT <> 0 Then FreeImage NG_Arrow_RIGHT
	
	If NG_AgentIcon <> 0 Then FreeImage NG_AgentIcon
	
	
	If NG_CloseButton <> 0 Then FreeImage NG_CloseButton
	If NG_MinimizeButton <> 0 Then FreeImage NG_MinimizeButton
	
	If NG_CloseButton_O <> 0 Then FreeImage NG_CloseButton_O
	If NG_MinimizeButton_O <> 0 Then FreeImage NG_MinimizeButton_O
		
	If NG_Doseurs <> 0 Then FreeImage NG_Doseurs
	
	If NG_Check <> 0 Then FreeImage NG_Check
	
	If NG_Tab_P <> 0 Then FreeImage NG_Tab_P
	If NG_Tab_N <> 0 Then FreeImage NG_Tab_N
	If NG_TabIcon <> 0 Then FreeImage NG_TabIcon
	
	If NG_ValueB <> 0 Then FreeImage NG_ValueB
	
	If NG_MenuS <> 0 Then FreeImage NG_MenuS
	If NG_MenuM <> 0 Then FreeImage NG_MenuM
	If NG_MenuE <> 0 Then FreeImage NG_MenuE
	
	If NG_IconFolder <> 0 Then FreeImage NG_IconFolder
	If NG_IconVideo <> 0 Then FreeImage NG_IconVideo
	If NG_IconAudio <> 0 Then FreeImage NG_IconAudio
	If NG_IconPhoto <> 0 Then FreeImage NG_IconPhoto
	If NG_IconText <> 0 Then FreeImage NG_IconText
	If NG_IconExe <> 0 Then FreeImage NG_IconExe
	If NG_IconSystem <> 0 Then FreeImage NG_IconSystem
	If NG_IconShort <> 0 Then FreeImage NG_IconShort
	
	If NG_CottageSelect <> 0 Then FreeImage NG_CottageSelect
	
	If NG_CottageS <> 0 Then FreeImage NG_CottageS
	If NG_CottageM <> 0 Then FreeImage NG_CottageM
	If NG_CottageE <> 0 Then FreeImage NG_CottageE
	
	If NG_CottageIconOn <> 0 Then FreeImage NG_CottageIconOn
	If NG_CottageIconOff <> 0 Then FreeImage NG_CottageIconOff
	
	; reset
	NG_Sphere0 = 0
	NG_Sphere0_O = 0
	
	NG_Sphere1 = 0
	NG_Sphere1_O = 0
	
	NG_Sphere2 = 0
	NG_Sphere2_O = 0
	
	NG_Sphere3 = 0
	NG_Sphere3_O = 0
	
	NG_DefaultSkinFile = 0
	
	NG_Arrow_UP = 0
	NG_Arrow_DOWN = 0
	NG_Arrow_LEFT = 0
	NG_Arrow_RIGHT = 0	
	
	NG_CloseButton = 0
	NG_MinimizeButton = 0
	
	NG_AgentIcon = 0

	NG_Button_S = 0
	NG_Button_M = 0
	NG_Button_E = 0
	NG_Button_So = 0
	NG_Button_Mo = 0
	NG_Button_Eo = 0
	
	NG_MenuS = 0
	NG_MenuM = 0
	NG_MenuE = 0
	
	NG_ValueB = 0
	
	NG_Doseurs = 0
	
	NG_Check = 0
	
	NG_IconFolder = 0
	NG_IconVideo = 0
	NG_IconAudio = 0
	NG_IconPhoto = 0
	NG_IconText = 0
	NG_IconExe = 0
	NG_IconSystem = 0
	NG_IconShort = 0
	
	
	NG_CottageS = 0
	NG_CottageS = 0
	NG_CottageS = 0
	
	NG_CottageIconOn = 0
	NG_CottageIconOff = 0
	
End Function

;--------------------------------------
; active la souris
;--------------------------------------
Function NG_ActiveMouse ( hide_true_pointer=0 )

	NG_PointerOn = True
	
	mouse = LoadImage ( NG_file_mouse$ + NG_PointerNum + NG_ext$ )
	
	Dim NG_Pointer (4)
	
	NG_Pointer (1) = CreateImage ( 30 , 30 )
		CopyRect 0 , 0 , 30 , 30 , 0 , 0 , ImageBuffer(mouse) , ImageBuffer( NG_Pointer (1) )
		MaskImage NG_Pointer (1) , NG_maskR , NG_maskG , NG_maskB
	
	NG_Pointer (2) = CreateImage ( 30 , 30 )
		CopyRect 30 , 0 , 30 , 30 , 0 , 0 , ImageBuffer(mouse) , ImageBuffer( NG_Pointer (2) )
		MaskImage NG_Pointer (2) , NG_maskR , NG_maskG , NG_maskB
		
	NG_Pointer (3) = CreateImage ( 30 , 30 )
		CopyRect 60 , 0 , 30 , 30 , 0 , 0 , ImageBuffer(mouse) , ImageBuffer( NG_Pointer (3) )
		MaskImage NG_Pointer (3) , NG_maskR , NG_maskG , NG_maskB
	
	NG_Pointer (4) = CreateImage ( 30 , 30 )
		CopyRect 90 , 0 , 30 , 30 , 0 , 0 , ImageBuffer(mouse) , ImageBuffer( NG_Pointer (4) )
		MaskImage NG_Pointer (4) , NG_maskR , NG_maskG , NG_maskB

	
	FreeImage mouse
	
	If hide_true_pointer
		HidePointer
	EndIf
		
End Function


;--------------------------------------
; désactive la souris
;--------------------------------------
Function NG_FreeMouse ( show_true_pointer=0 )

	NG_PointerOn = False
		
	If NG_Pointer (1) <> 0 Then FreeImage NG_Pointer (1)
	If NG_Pointer (2) <> 0 Then FreeImage NG_Pointer (2)
	If NG_Pointer (3) <> 0 Then FreeImage NG_Pointer (3)
	If NG_Pointer (4) <> 0 Then FreeImage NG_Pointer (4)
	
	NG_Pointer (1) = 0
	NG_Pointer (2) = 0
	NG_Pointer (3) = 0
	NG_Pointer (4) = 0
	
	If show_true_pointer
		ShowPointer
	EndIf
	
End Function

;--------------------------------
; Charge les fichiers externes
;--------------------------------
Function NG_LoadExternalFiles ()

	;---------
	; charge
	;---------
	
	If NG_SphereOn = True
	
		NG_Sphere0 = LoadImage ( NG_file_Sphere0$ + NG_ext$ )
		MaskImage NG_Sphere0 , Ng_maskR , NG_maskG , NG_maskB
			NG_Sphere0_O = LoadImage ( NG_file_Sphere0_O$ + NG_ext$ )
			MaskImage NG_Sphere0_O , Ng_maskR , NG_maskG , NG_maskB
		
		NG_Sphere1 = LoadImage ( NG_file_Sphere1$ + NG_ext$ )
		MaskImage NG_Sphere1 , Ng_maskR , NG_maskG , NG_maskB
			NG_Sphere1_O = LoadImage ( NG_file_Sphere1_O$ + NG_ext$ )
			MaskImage NG_Sphere1_O , Ng_maskR , NG_maskG , NG_maskB
		
		NG_Sphere2 = LoadImage ( NG_file_Sphere2$ + NG_ext$ )
		MaskImage NG_Sphere2 , Ng_maskR , NG_maskG , NG_maskB
			NG_Sphere2_O = LoadImage ( NG_file_Sphere2_O$ + NG_ext$ )
			MaskImage NG_Sphere2_O , Ng_maskR , NG_maskG , NG_maskB
		
		NG_Sphere3 = LoadImage ( NG_file_Sphere3$ + NG_ext$ )
		MaskImage NG_Sphere3 , Ng_maskR , NG_maskG , NG_maskB
			NG_Sphere3_O = LoadImage ( NG_file_Sphere3_O$ + NG_ext$ )
			MaskImage NG_Sphere3_O , Ng_maskR , NG_maskG , NG_maskB
		
	EndIf
		
	NG_Doseurs = LoadImage ( NG_file_Doseur$ + NG_ext$ )
		Tx = ImageWidth ( NG_Doseurs )
		HandleImage NG_Doseurs , Tx/2 , 0
		MaskImage NG_Doseurs , Ng_maskR , NG_maskG , NG_maskB

	NG_Check = LoadImage ( NG_file_Check$ + NG_ext$ ) 
		MaskImage NG_Check , Ng_maskR , NG_maskG , NG_maskB
		
	NG_ValueB = LoadImage ( NG_file_ValueB$ + NG_ext$ ) 
		MaskImage NG_ValueB , Ng_maskR , NG_maskG , NG_maskB
		
		
	;-----------------------------------------------
	; Fleches
	;-----------------------------------------------
	tmp = LoadImage ( NG_file_Arrow$ + NG_ext$ )
	
	NG_Arrow_UP = CreateImage (20,20)
		CopyRect 0 , 0 , 20 , 20 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Arrow_UP)
		MaskImage NG_Arrow_UP , Ng_maskR , NG_maskG , NG_maskB
	
	NG_Arrow_DOWN = CreateImage (20,20)
		CopyRect 20 , 0 , 20 , 20 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Arrow_DOWN)
		MaskImage NG_Arrow_DOWN , Ng_maskR , NG_maskG , NG_maskB
		
	NG_Arrow_LEFT = CreateImage (20,20)
		CopyRect 40 , 0 , 20 , 20 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Arrow_LEFT)
		MaskImage NG_Arrow_LEFT , Ng_maskR , NG_maskG , NG_maskB
			
	NG_Arrow_RIGHT = CreateImage (20,20)
		CopyRect 60 , 0 , 20 , 20 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Arrow_RIGHT)
		MaskImage NG_Arrow_RIGHT , Ng_maskR , NG_maskG , NG_maskB
		
	
		
	FreeImage tmp
	
	;-----------------------------------------------
	; Tabs
	;-----------------------------------------------
	tmp = LoadImage ( NG_file_Tabs$ + NG_ext$ )
		Tx = ImageWidth (tmp)
		Ty = ImageHeight (tmp) 
		
		NG_Tab_P = CreateImage ( Tx/2 , Ty )
		CopyRect 0 , 0 , Tx/2 , Ty , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Tab_P)
		MaskImage NG_Tab_P , Ng_maskR , NG_maskG , NG_maskB

		
		NG_Tab_N = CreateImage ( Tx/2 , Ty )
		CopyRect Tx/2 , 0 , Tx/2 , Ty , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Tab_N)
		HandleImage NG_Tab_N , Tx/2 , 0 
		MaskImage NG_Tab_N , Ng_maskR , NG_maskG , NG_maskB
		
		FreeImage tmp
		
	NG_TabIcon = LoadImage ( NG_file_TabIcon$ + NG_ext$ )
		MaskImage NG_TabIcon , Ng_maskR , NG_maskG , NG_maskB
	
	;-----------------------
	; Menus
	;-----------------------	
	tmp = LoadImage ( NG_file_Menu$ + NG_ext$ )
		Tx = ImageWidth (tmp)
		Ty = ImageHeight (tmp) 
		
		NG_Menu_S = CreateImage ( Tx/4 , Ty )
		CopyRect 0 , 0 , Tx/4 , Ty , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Menu_S)
		MaskImage NG_Menu_S , Ng_maskR , NG_maskG , NG_maskB
		
		NG_Menu_M = CreateImage ( Tx/2 , Ty )
		CopyRect Tx/4 , 0 , Tx/2 , Ty , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Menu_M)
		MaskImage NG_Menu_M , Ng_maskR , NG_maskG , NG_maskB
		
		NG_Menu_E = CreateImage ( Tx/4 , Ty )
		CopyRect (Tx/4)*3 , 0 , Tx/4 , Ty , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_Menu_E)
		MaskImage NG_Menu_E , Ng_maskR , NG_maskG , NG_maskB
		
		FreeImage tmp

	;-----------------------
	; les icones
	;-----------------------
	tmp = LoadImage ( NG_file_Icons$ + NG_ext$ )
		
		x = 0
		
		NG_IconFolder = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconFolder)
		MaskImage NG_IconFolder , Ng_maskR , NG_maskG , NG_maskB
		
		x = x + 32
		
		NG_IconVideo = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconVideo)
		MaskImage NG_IconVideo , Ng_maskR , NG_maskG , NG_maskB
		
		x = x + 32
		
		NG_IconAudio = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconAudio)
		MaskImage NG_IconAudio , Ng_maskR , NG_maskG , NG_maskB
		
		x = x + 32
		
		NG_IconPhoto = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconPhoto)
		MaskImage NG_IconPhoto , Ng_maskR , NG_maskG , NG_maskB
		
		x = x + 32
		
		NG_IconText = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconText)
		MaskImage NG_IconText , Ng_maskR , NG_maskG , NG_maskB
		
		x = x + 32
		
		NG_IconExe = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconExe)
		MaskImage NG_IconExe , Ng_maskR , NG_maskG , NG_maskB
		
		x = x + 32
		
		NG_IconSystem = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconSystem)
		MaskImage NG_IconSystem , Ng_maskR , NG_maskG , NG_maskB
		
		x = x + 32
		
		NG_IconShort = CreateImage ( 32 , 32 )
		CopyRect x , 0 , 32 , 32 , 0 , 0 , ImageBuffer (tmp) , ImageBuffer(NG_IconShort)
		MaskImage NG_IconShort , Ng_maskR , NG_maskG , NG_maskB

	FreeImage tmp
	
	;--------------------------
	; Cottage
	;--------------------------
	If NG_CottageLoadOn = True
	
		NG_CottageS = LoadImage ( NG_file_CottageS$ + NG_ext$ )
			MaskImage NG_CottageS , Ng_maskR , NG_maskG , NG_maskB
		NG_CottageM = LoadImage ( NG_file_CottageM$ + NG_ext$ )
			MaskImage NG_CottageM , Ng_maskR , NG_maskG , NG_maskB
		NG_CottageE = LoadImage ( NG_file_CottageE$ + NG_ext$ )
			MaskImage NG_CottageE , Ng_maskR , NG_maskG , NG_maskB
			
		NG_CottageSelect = LoadImage ( NG_file_CottageSelect$ + NG_ext$ )
			MaskImage NG_CottageSelect , Ng_maskR , NG_maskG , NG_maskB
		
		NG_WidthS = ImageWidth ( NG_CottageS )
		NG_WidthM = ImageWidth ( NG_CottageM )
		NG_WidthE = ImageWidth ( NG_CottageE )
		
		NG_HeightM = ImageHeight ( NG_CottageM )
			
		NG_CottageIconOn = LoadImage ( NG_file_CottageIconOn$ + NG_ext$ )
			MaskImage NG_CottageIconOn , Ng_maskR , NG_maskG , NG_maskB
		NG_CottageIconOff = LoadImage ( NG_file_CottageIconOff$ + NG_ext$ )
			MaskImage NG_CottageIconOff , Ng_maskR , NG_maskG , NG_maskB
			
	EndIf
	
	;---------------------------------
	; Agent de base
	;---------------------------------
	NG_AgentIcon = LoadImage ( NG_file_Agent$ + NG_ext$ )
		MaskImage NG_AgentIcon , Ng_maskR , NG_maskG , NG_maskB	
	
	;---------------------------------
	; Cygne
	;---------------------------------
	If NG_CygneLoadOn = True
	
		NG_CygneLoadElements ()
	
	EndIf
		
		
End Function
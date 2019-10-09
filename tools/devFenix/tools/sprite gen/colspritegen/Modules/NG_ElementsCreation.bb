;------------------------------------------------------------------------
; Modules : création des éléments (gadgets)
;------------------------------------------------------------------------

;--------------------------
; Crée une frame
;--------------------------
Function NG_CreateFrame ( WinId , Px , Py , Tx , Ty , Label$ , Flag=0 , r=-1 , g=-1 , b=-1 )

	NG.NG_Frame = New NG_Frame
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\Label$ = Label$
		
		;----------------------
		; couleurs du texte
		;----------------------
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		If r>-1 Then NG\r = R
		If g>-1 Then NG\g = G
		If b>-1 Then NG\b = B
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\ombre = False		
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf

	Return NG\Id

End Function


;--------------------------
; Crée un menu
;--------------------------
Function NG_CreateMenu ( WinId , Px , Py , Tx , Ty , Largeur=25 , sens = 1 )

	NG.NG_Menu = New NG_Menu
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		; gelé par défaut
		NG\ice = True
		
		NG\Largeur = Largeur
		
		; 1 pour de haut en bas
		; 2 pour de bas en haut
		NG\sens = sens
				
	Return NG\Id

End Function

;--------------------------
; Crée un Texte
;--------------------------
Function NG_CreateText ( WinId , Px , Py , Label$ , Chars , Flag=0 , r=-1 , g=-1 , b=-1 , font=0 )

	NG.NG_Text = New NG_Text
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		NG\Chars = Chars
	
		NG\Label$ = Label$
		
		;----------------------
		; couleurs du texte
		;----------------------
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		If r>-1 Then NG\r = R
		If g>-1 Then NG\g = G
		If b>-1 Then NG\b = B
		
		;-------------------------------------------------
		; font définie par l'utilisateur ?
		;-------------------------------------------------
		NG\font = font
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\Center = False
		NG\ombre = False		
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 2
			F = F - 2
			NG\Center = True
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf

	Return NG\Id

End Function


;--------------------------
; Crée un value
;--------------------------
Function NG_CreateValue ( WinId , Px , Py , Value# , min# , max# , pas#=0 , PreLabel$="" , Flag=0 , help$="" )

	NG.NG_Value = New NG_Value
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\min# = min#
		NG\max# = max#
		NG\value# = value#
				
		NG\PreLabel$ = PreLabel$
		
		NG\help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\integer = False
		NG\ombre = False
		NG\editable = True		
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 4
			F = F - 4
			NG\Editable = False
		EndIf	
		
		If F >= 2
			F = F - 2
			NG\integer = True
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
		
		;----------------------------------
		; pas
		;----------------------------------
		If pas# > 0
		
			NG\pas# = pas#
									
		Else
		
			NG\pas# = 0.1
		
			If NG\integer Then NG\pas# = 1
			
		EndIf
		
		
						
	Return NG\Id

End Function


;--------------------------
; Crée un Input
;--------------------------
Function NG_CreateInput ( WinId , Px , Py , Tx , Ty , PreLabel$ , Flag=0 , Label$="" , help$="" )

	NG.NG_Input = New NG_Input
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\Chars = 100000
				
		NG\PreLabel$ = PreLabel$
		NG\Label$ = Label$
		
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\alpha = True
		NG\ombre = False
		NG\editable = True		
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 4
			F = F - 4
			NG\Editable = False
		EndIf	
		
		If F >= 2
			F = F - 2
			NG\alpha = False
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
		
		;----------------------------------------------------------------------------------
		; Si c'est un champ texte numerique seulement, on converti le label en numerique
		;----------------------------------------------------------------------------------
		;If NG\alpha = False
		;	NG\Label$ = Int (NG\Label$)
		;EndIf
					
	Return NG\Id

End Function

;-----------------------------
; Crée un Input multilignes
;-----------------------------
Function NG_CreateMultiInput ( WinId , Px , Py , Tx , Ty , PreLabel$ , Flag=0 , Label$="" , help$="" )

	NG.NG_MultiInput = New NG_MultiInput
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
				
		NG\PreLabel$ = PreLabel$
		NG\Label$ = Label$
		
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\help$ = help$
		
		NG\ToTop = True
		
		;----------------------------
		; bouton up/down
		;----------------------------
		NG\Bprev = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
		NG_SetButtonType ( NG\Bprev , "UP" )
		NG\Bnext = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
		NG_SetButtonType ( NG\Bnext , "DOWN" )
		
		;---------------------
		; Slider
		;---------------------
		NG\Bslider = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
		NG\slidX = 0
		NG\slidY = 0
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\alpha = True
		NG\ombre = False
		NG\editable = True	
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 4
			F = F - 4
			NG\Editable = False
		EndIf	
		
		If F >= 2
			F = F - 2
			NG\alpha = False
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
		
	Return NG\Id

End Function

;--------------------------
; Crée une Combo Box
;--------------------------
Function NG_CreateCombo ( WinId , Px , Py , Tx , Ty , PreLabel$ , Flag=0 , help$="" , lignmax = 5 )

	NG.NG_Combo = New NG_Combo
				
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\Chars = 100000
				
		NG\PreLabel$ = PreLabel$
		
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\help$ = help$
		
		NG\lignMax = lignmax
		
		If NG\lignmax < 2 Then NG\lignmax = 2
		
		NG\lignmax_saved = NG\lignmax
		
		;-----------------------
		; bouton down
		;-----------------------
		TailleX = 20
		BoutonX = NG\Px + Xzone + NG\Tx - TailleX
		NG\Button = NG_CreateButton ( 0 , BoutonX , NG\Py , TailleX , NG\Ty , "" )
		NG_SetButtonType ( NG\Button , "DOWN" )
		
		;----------------------------
		; bouton up/down
		;----------------------------
		NG\Bprev = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
		NG_SetButtonType ( NG\Bprev , "UP" )
		NG\Bnext = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
		NG_SetButtonType ( NG\Bnext , "DOWN" )
		
		;---------------------
		; Slider
		;---------------------
		NG\Bslider = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
				
		;-----------------------
		; par défaut
		;-----------------------
		NG\ombre = False		
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
							
	Return NG\Id

End Function

;--------------------------
; Crée une CheckBox
;--------------------------
Function NG_CreateCheckBox ( WinId , Px , Py , Label$ , Flag=0 , help$="" )

	NG.NG_CheckBox = New NG_CheckBox
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = 10
		NG\Ty = 10
		
		NG\Label$ = Label$
		
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\Check = False
		NG\ombre = False	
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 2
			F = F - 2
			NG\Check = True
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf

		
	Return NG\Id

End Function

;----------------------------------
; Crée un Bouton Flèché
;----------------------------------
Function NG_CreateArrowButton ( WinId , Px , Py , dir$="UP" , Flag=0 , help$="" )

	NG.NG_Button = New NG_Button
				
		NG\WinId = WinId

		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True

		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = 20
		NG\Ty = 20
		
		NG\State = 0
		
		thedir$ = Upper$ (dir$)
		
		NG\typ$ = thedir$
		
		NG\Label$ = ""
		
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\box   = True
		NG\ombre = False	
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 2
			F = F - 2
			NG\Box = False
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
		
	Return NG\Id



End Function


;--------------------------
; Crée une Bouton
;--------------------------
Function NG_CreateButton ( WinId , Px , Py , Tx , Ty , Label$ , Flag=0 , help$="" )

	NG.NG_Button = New NG_Button
				
		NG\WinId = WinId

		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True

		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\State = 0
		
		NG\Label$ = Label$
		
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\box   = True
		NG\ombre = False	
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 2
			F = F - 2
			NG\Box = False
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
		
	Return NG\Id

End Function

;--------------------------
; Crée une Bouton cyclique
;--------------------------
Function NG_CreateCycleButton ( WinId , Px , Py , Tx , Ty , Flag=0 , Label1$="" , Label2$="" , Label3$="" , Label4$="" , Label5$="" ,  help$="" )

	NG.NG_CycleButton = New NG_CycleButton
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\State = 0
		
		;----------------------------
		; définition des Labels
		;----------------------------
		; 1
		If Label1$ <> ""
		
			NG\Label$[1] = Label1$
			NG\max_label = 1
		
		Else
			
			NG\Label$[1] = "Au moins 1 Eh!"
			NG\max_label = 1

		EndIf
		
		; 2
		If Label2$ <> ""
		
			NG\Label$[2] = Label2$
			NG\max_label = 2

		EndIf
		
		; 3
		If Label3$ <> ""
		
			NG\Label$[3] = Label3$
			NG\max_label = 3

		EndIf
		
		; 4
		If Label4$ <> ""
		
			NG\Label$[4] = Label4$
			NG\max_label = 4

		EndIf
		
		; 5
		If Label5$ <> ""
		
			NG\Label$[5] = Label5$
			NG\max_label = 5

		EndIf
		
		NG\current_label = 1		
		
	
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\box   = True
		NG\ombre = False	
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 2
			F = F - 2
			NG\Box = False
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
		
	Return NG\Id

End Function


;--------------------------
; Crée un Doseur
;--------------------------
Function NG_CreateDoseur ( WinId , Px , Py , Tx , Label$ , min# , max# , val# , flag=0 , help$="" )

	NG.NG_Doseur = New NG_Doseur
				
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		
		NG\Label$ = Label$
		
		NG\min# = min#
		NG\max# = max#
		
		NG\val# = val#
		
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\re = 0
		NG\ge = 0
		NG\be = 0
		
		NG\help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\integer = False
		NG\ombre   = False
		NG\info    = True	
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 4
			F = F - 4
			NG\info = False
		EndIf	
		
		If F >= 2
			F = F - 2
			NG\integer = True
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf

		
	Return NG\Id

End Function

;----------------------------------------
; Crée une image integrée à une fenetre
;----------------------------------------

Function NG_CreateImage ( WinId , file$ , Px , Py , Tx=0 , Ty=0 , flag=0 , maskr=-1 , maskg=-1 , maskb=-1 )

	NG.NG_Image = New NG_Image
		
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		If file$ <> ""
		
			NG\image = LoadImage ( file$ )
			
			If NG\Tx = 0 Then NG\Tx = ImageWidth ( NG\image )
			If NG\Ty = 0 Then NG\Ty = ImageHeight ( NG\image )
			
			If NG\Tx <> 0 And NG\Ty <> 0
				ResizeImage NG\image , NG\Tx , NG\Ty
			EndIf
			
			If maskr > - 1 And maskg > - 1 And maskb > - 1
			
				MaskImage NG\image , maskr , maskg , maskb
			
				NG\Block = False
				
			Else
			
				NG\Block = True
			
			EndIf
					
		EndIf
		
		NG\r = 0
		NG\g = 0
		NG\b = 0
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\ombre = False
		NG\Bord  = True
	
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 4
			F = F - 4
			NG\Block = False
		EndIf
		
		If F >= 2
			F = F - 2
			NG\Bord = False
		EndIf

		
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
					
	Return NG\Id

End Function


;--------------------------------------------------------------
; Crée une image integrée à une fenetre à partir d'un handle
;--------------------------------------------------------------

Function NG_CreateImageFromHandle ( WinId , file , Px , Py , Tx=0 , Ty=0 , flag=0 , maskr=-1 , maskg=-1 , maskb=-1  )

	NG.NG_Image = New NG_Image
		
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		If file > 0
		
			NG\image = file
			
			If NG\Tx = 0 Then NG\Tx = ImageWidth ( NG\image )
			If NG\Ty = 0 Then NG\Ty = ImageHeight ( NG\image )
			
			If NG\Tx <> 0 And NG\Ty <> 0
				ResizeImage NG\image , NG\Tx , NG\Ty
			EndIf
			
			If maskr > - 1 And maskg > - 1 And maskb > - 1
			
				MaskImage NG\image , maskr , maskg , maskb
			
			EndIf
		
		EndIf
		
		NG\r = 0
		NG\g = 0
		NG\b = 0
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\ombre = False
		NG\Bord  = True	
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 2
			F = F - 2
			NG\Bord = False
		EndIf	
			
		If F >= 1
			F = F - 1
			NG\Ombre = True
		EndIf
					
	Return NG\Id

End Function


;----------------------------------------
; Crée un bouton image
;----------------------------------------

Function NG_CreateImageButton ( WinId , Px , Py , Tx , Ty , file1$ , file2$="" , Flag=0 , help$="" , mr=-1 , mg=-1 , mb=-1 )

	NG.NG_ImageButton = New NG_ImageButton
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\state = 0
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\box   = True
		NG\resize = True
	
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 2
			F = F - 2
			NG\box = False
			
		EndIf
		
		If F >= 1
			F = F - 1
			NG\resize = False
		EndIf
		
		; masques
		NG\mr = mr
		NG\mg = mg
		NG\mb = mb
		
		;------------------------
		; les images
		;------------------------
		NG\image1 = LoadImage ( file1$ )
		
		; un masque ou pas ?
		If mr>=0 And mg>=0 And mb>=0
			MaskImage NG\image1 , mr , mg , mb
		EndIf
				
		If NG\resize
			ResizeImage NG\image1 , NG\Tx , NG\Ty
		EndIf
		
		; centrer l'image
		HandleImage NG\image1 , ImageWidth (NG\image1)/2 , ImageHeight (NG\image1)/2
		
		;----------------------------------------------------------------
		; Si on ne définit pas une 2ème image, la 1ère sera utilisée
		;----------------------------------------------------------------		
		If file2$ <> ""
		
			NG\image2 = LoadImage ( file2$ )
			
			; un masque ou pas ?
			If mr >= 0 And mg >= 0 And mb >= 0
				MaskImage NG\image2 , mr , mg , mb
			EndIf
			
			If NG\resize
				ResizeImage NG\image2 , NG\Tx , NG\Ty
			EndIf
			
			; centrer l'image
			HandleImage NG\image2 , ImageWidth (NG\image2)/2 , ImageHeight (NG\image2)/2

		EndIf
			
		NG\r = 0
		NG\g = 0
		NG\b = 0
		
		NG\help$ = help$
		
	Return NG\Id

End Function


;--------------------------------------------
; Crée un bouton image à partir d'un handle
;--------------------------------------------

Function NG_CreateImageButtonFromHandle ( WinId , Px , Py , Tx , Ty , file1 , file2=0 , Flag=0 , help$="" , mr=-1 , mg=-1 , mb=-1 )

	NG.NG_ImageButton = New NG_ImageButton
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\state = 0
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\box   = True
		NG\resize = True
	
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 2
			F = F - 2
			NG\box = False
			
		EndIf
		
		If F >= 1
			F = F - 1
			NG\resize = False
		EndIf
		
		; masques
		NG\mr = mr
		NG\mg = mg
		NG\mb = mb
		
		;------------------------
		; les images
		;------------------------
		NG\image1 = file1
		
		; un masque ou pas ?
		If mr>=0 And mg>=0 And mb>=0
			MaskImage NG\image1 , mr , mg , mb
		EndIf
				
		If NG\resize
			ResizeImage NG\image1 , NG\Tx , NG\Ty
		EndIf
		
		; centrer l'image
		HandleImage NG\image1 , ImageWidth (NG\image1)/2 , ImageHeight (NG\image1)/2
		
		;----------------------------------------------------------------
		; Si on ne définit pas une 2ème image, la 1ère sera utilisée
		;----------------------------------------------------------------		
		If file2 > 0
		
			NG\image2 = file2
			
			; un masque ou pas ?
			If mr >= 0 And mg >= 0 And mb >= 0
				MaskImage NG\image2 , mr , mg , mb
			EndIf
			
			If NG\resize
				ResizeImage NG\image2 , NG\Tx , NG\Ty
			EndIf
			
			; centrer l'image
			HandleImage NG\image2 , ImageWidth (NG\image2)/2 , ImageHeight (NG\image2)/2

		EndIf
			
		NG\r = 0
		NG\g = 0
		NG\b = 0
		
		NG\help$ = help$
		
	Return NG\Id

End Function


;----------------------------------------
; Crée une frame 3D
;----------------------------------------

Function NG_Create3dFrame ( WinId , Px , Py , Tx , Ty , cam=0 , freq=1 , wire=False )

	NG.NG_3dFrame = New NG_3dFrame
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\r = 0
		NG\g = 0
		NG\b = 0
		
		NG\active = True
		
		NG\help$ = help$
		
		NG\freq = freq
		
		NG\wire = wire
		
		If cam=0
			NG\camera = CreateCamera()
		Else
			NG\camera = cam
		EndIf
		
		;--------------------------------
		; application
		;--------------------------------
		CameraViewport NG\camera , 0 , 0 , Tx , Ty
		
		NG\image = CreateImage ( NG\Tx , NG\Ty )
		
		;MoveEntity NG\camera,0,0,-100
		
		;---------------------------------
		; on la déactive
		;---------------------------------
		CameraProjMode NG\camera , 0
		
					
	Return NG\Id

End Function


;--------------------------
; Crée une couleur
;--------------------------
Function NG_CreateColor ( WinId , Px , Py , Tx , Ty , r , g , b , label$="" , help$ = "" )

	NG.NG_Color = New NG_Color
	
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		NG\Tx = Tx
		NG\Ty = Ty

		NG\R = R
		NG\G = G
		NG\B = B
		
		NG\Mode = 1
		
		NG\label$ = label$
		
		NG\help$ = help$

	Return NG\Id

End Function


;----------------------------------
; crée un Agent
;----------------------------------
Function NG_CreateAgent ( WinId , Px , Py , icon=0 , LimitX = False , LimitY = False , Xs=0 , Ys=0 , Xe=0 , Ye=0 , Help$="" )

	NG.NG_Agent = New NG_Agent
	
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\LimitX = LimitX
		NG\LimitY = LimitY
		
		NG\Xs = Xs
		NG\Ys = Ys
		
		NG\Xe = Xe
		NG\Ye = Ye
		
		; l'icone
		If Icon = 0
		
			NG\Icon = NG_AgentIcon
			
		Else
		
			NG\Icon = Icon
				
		EndIf
		
		NG\Help$ = help$

		
		Return NG\Id		

End Function

;--------------------------
; Crée un indicateur FPS
;--------------------------
Function NG_CreateFPS ( WinId , Px , Py , Flag=0 , Label$="Images par Sec :" , help$ = "" )

	
	NG.NG_FPS = New NG_FPS
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		;----------------------
		; couleurs du texte
		;----------------------
		NG\r = NG_Font_r
		NG\g = NG_Font_g
		NG\b = NG_Font_b
		
		NG\Label$ = Label$
		
		NG\Help$ = help$
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\ombre   = False
	
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag	
		
		If F >= 1
			F = F - 1
			NG\ombre = True
		EndIf
		
		;----------------------------------------
		; Définit la taille selon celle du label
		;----------------------------------------
		NG\Tx = StringWidth ( NG\Label$)
		NG\Ty = 50
		
		;----------------------------------------
		; enregistrement de la valeur de depart
		;----------------------------------------
		NG\newtime = MilliSecs()
		
	Return NG\Id

End Function

;--------------------------
; Crée une Progress Bar
;--------------------------
Function NG_CreateProgress ( WinId , Px , Py , Tx , Ty , Flag=0 , Label$="" , Rs=-1 , Gs=-1 , Bs=-1 , Re=-1 , Ge=-1 , Be=-1 , help$ = "" )

	NG.NG_Progress = New NG_Progress
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\level = 100
		
		NG\Label$ = Label$
		
		NG\Help$ = help$
		
		NG\Ombre = False
		
		;--------------------------------
		; dégradé début
		;--------------------------------
		If Rs <> -1 Then NG\R_s = Rs
		If Gs <> -1 Then NG\G_s = Gs
		If Bs <> -1 Then NG\B_s = Bs
		
		;--------------------------------
		; dégradé fin
		;--------------------------------
		If Re <> -1 Then NG\R_e = Re
		If Ge <> -1 Then NG\G_e = Ge
		If Be <> -1 Then NG\B_e = Be
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\LabelOn = True
		NG\LevelOn = True
		NG\Grad    = True
	
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 8
			F = F - 8
			NG\Ombre = True
		EndIf
		
		If F >= 4
			F = F - 4
			NG\Grad = False
		EndIf
		
		If F >= 2
			F = F - 2
			NG\LevelOn = False
		EndIf	
		
		If F >= 1
			F = F - 1
			NG\LabelOn = False
		EndIf
		
	Return NG\Id

End Function


;--------------------------
; Crée un IconViewer
;--------------------------
Function NG_CreateIconViewer ( WinId , Px , Py , IconX , IconY , Style=1 , Size=32 , Flag=0 , help$ = "" )

	NG.NG_IconViewer = New NG_IconViewer
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\IconX = IconX
		NG\IconY = IconY
		
		NG\Label = label
		
		NG\Style = style
		
		NG\Icons = True
		
		NG\IconSize = size
		
		NG\sel_x = -1
		NG\sel_y = -1
		
		;-----------------------
		; par défaut
		;-----------------------
		NG\Label = False
		NG\Drag = False
		NG\Icons_disp = True
			
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 4
			F = F - 4
			NG\icons_disp = False
		EndIf
	
		
		If F >= 2
			F = F - 2
			NG\drag = True
		EndIf
		
		If F >= 1
			F = F - 1
			NG\label = True
		EndIf

		
		;--------------------
		; Boutons
		;--------------------
		; Viewer horizontal
		;--------------------
		If NG\style = 1
		
			NG\Bprev = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
				NG_SetButtonType ( NG\Bprev , "LEFT" )
			NG\Bnext = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
				NG_SetButtonType ( NG\Bnext , "RIGHT" )
		
		;--------------------
		; Viewer vertical
		;--------------------		
		Else
		
			NG\Bprev = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
				NG_SetButtonType ( NG\Bprev , "UP" )
			NG\Bnext = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
				NG_SetButtonType ( NG\Bnext , "DOWN" )
			
		EndIf
		
		;---------------------
		; Slider
		;---------------------
		NG\Bslider = NG_CreateButton ( 0 , 0 , 0 , 20 , 20 , "" )
						
	Return NG\Id

End Function

;------------------------------------
; Crée un gadget pong (super utile)
;------------------------------------
Function NG_CreatePong ( WinId , Px , Py , Tx , Ty , Start=1 , Titre$="Npong" , Info$="Affrontez les plus grands adversaires !" )
	
	NG.NG_Pong = New NG_Pong
		
		NG\WinId = WinId
		
		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True
		
		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\Titre$ = Titre$
		NG\Info$ = Info$
				
		;--------------------------
		; position des raquettes
		;--------------------------
		; milieux de l'ecran
		midX = NG\Tx / 2
		midY = NG\Ty / 2
		
		; taille des raquettes
		NG\Raq_Tx = midX / 10
		NG\Raq_Ty = midY / 3
		
		; position du joueur
		ecart = NG\Tx / 200
		NG\Player_Px = ecart
		NG\Player_Py = midy - (NG\Raq_Ty/2)
		
		; position de l'enemi
		NG\Enemy_Px = NG\Tx - ecart - NG\Raq_Tx 
		NG\Enemy_Py = midy - (NG\Raq_Ty/2)
		
		;-------------------------
		; la balle
		;-------------------------
		
		; taille
		NG\Ball_Tx = NG\Tx / 50
		
		; position
		NG\Ball_Px = midx - NG\Ball_Tx/2
		NG\Ball_Py = midy - NG\Ball_Tx/2
		
		NG\Mx = Rnd (-1,+1)
		NG\My = Rnd (-1,+1)
		
		
		;-------------------------
		; scores et start
		;-------------------------	
		NG\Player_score = 0
		NG\Enemy_score = 0
		
		;-------------------------
		; start !
		;-------------------------
		NG\Start = start
		
		;-----------
		; saves
		;-----------
		NG\Raq_saved_Py = NG\Player_Py
		NG\Ball_saved_px = NG\Ball_Px
		NG\Ball_saved_py = NG\Ball_Py
		
		
					
	Return NG\Id

End Function

;--------------------------
; Crée un Board
;--------------------------
Function NG_CreateBoard ( WinId , Px , Py , Tx , Ty , help$="" )

	NG.NG_Board = New NG_Board
				
		NG\WinId = WinId

		NG\Id = Handle (NG)
		
		; montré par défaut
		NG\Show = True

		NG\Px = Px
		NG\Py = Py
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\help$ = help$
						
	Return NG\Id

End Function
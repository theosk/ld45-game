;------------------------------------------------------------------------
; Modules : mise à jour des éléments (gadgets)
;------------------------------------------------------------------------

; -------------------------------------------------
; Pour obtenir la selection d'un menu
; -------------------------------------------------
Function NG_ReturnMenu$ ( Id )

	NG.NG_Menu = Object.NG_Menu(Id)
	
	If NG <> Null
	
		If NG_MenuOpenId = NG\Id
		
			Return NG_SelectedMenu$
			
		EndIf
		
	EndIf	
	
End Function



; -------------------------------------------------
; Pour obtenir l'état d'un bouton (normal/pressé)
; -------------------------------------------------
Function NG_ReturnButton ( Id )

	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null
		
		If NG\State = 1
			
			Return True	
			
		Else
			
			Return False
			
		EndIf
		
	EndIf	
	
End Function

; -------------------------------------------------------------------------
; Pour obtenir l'état d'un bouton (normal/pressé) (meme en cours d'appui)
; -------------------------------------------------------------------------
Function NG_ReturnButtonDown ( Id )

	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null
			
		If NG\State <> 0
			
			Return True
			
		Else
			
			Return False
			
		EndIf
		
	EndIf
	
End Function

; -------------------------------------------------------------
; Pour vérifier si un bouton cyclique a été pressé
; -------------------------------------------------------------
Function NG_ReturnCycleButton ( Id )
	
	NG.NG_CycleButton = Object.NG_CycleButton(Id)
		
	If NG <> Null
			
		If NG\State = 1
			
			Return True	
			
		Else
			
			Return False
			
		EndIf
		
	EndIf
	
End Function

; -------------------------------------------------------------
; Pour obtenir l'état d'un bouton cyclique (numero de cycle)
; -------------------------------------------------------------
Function NG_ReturnCycleButtonNum ( Id )

	NG.NG_CycleButton = Object.NG_CycleButton(Id)
	
	If NG <> Null
	
		Return NG\current_label
						
	EndIf
	
End Function

; -------------------------------------------------------------
; Pour obtenir l'état d'un bouton cyclique (label de cycle)
; -------------------------------------------------------------
Function NG_ReturnCycleButtonLabel$ ( Id )

	NG.NG_CycleButton = Object.NG_CycleButton(Id)
	
	If NG <> Null
		
		Return NG\Label$ [NG\current_label]
			
	EndIf
	
End Function

; ----------------------------------------------------------------------------------------------------
; Pour obtenir l'état d'un bouton Image (normal/pressé) (Aaaahh c'est vachement différent hein !!)
; ----------------------------------------------------------------------------------------------------
Function NG_ReturnImageButton ( Id )
	
	NG.NG_ImageButton = Object.NG_ImageButton(Id)
	
	If NG <> Null
			
		If NG\State = 1
			
			Return True
							
		Else
			
			Return False
			
		EndIf
		
	EndIf
	
End Function

; ----------------------------------------------------------------------------------------------------------
; Pour obtenir l'état d'un bouton Image (normal/pressé) ('tention ! : meme si il est en cours d'appui !!))
; ----------------------------------------------------------------------------------------------------------
Function NG_ReturnImageButtonDown ( Id )
	
	NG.NG_ImageButton = Object.NG_ImageButton(Id)	
	
	If NG <> Null
			
		If NG\State <> 0
			
			Return True
							
		Else
			
			Return False
			
		EndIf
		
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour obtenir l'état d'une checkbox (cochée ou pas cochée)
; ----------------------------------------------------------
Function NG_ReturnCheckBox ( Id )
	
	NG.NG_CheckBox = Object.NG_CheckBox(Id)
	
	If NG <> Null
			
		Return NG\Check
		
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour obtenir la valeur d'un doseur
; ----------------------------------------------------------
Function NG_ReturnDoseur# ( Id )
	
	NG.NG_Doseur = Object.NG_Doseur(Id)
	
	If NG <> Null
			
		If NG\integer
						
			Return Int (NG\val#)
			
		Else
			
			Return NG\val#
			
		EndIf
		
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour obtenir le controle d'une image d'un gadfget image
; ----------------------------------------------------------
Function NG_ReturnImage ( Id )
	
	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null
			
		Return NG\image
		
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour obtenir le texte entrée dans une input box
; ----------------------------------------------------------
Function NG_ReturnValue# ( Id )
	
	NG.NG_Value = Object.NG_Value(Id)
	
	If NG <> Null
		
		If NG\integer
					
			Return NG\value_int
		
		Else
		
			Return NG\value#
		
		EndIf
	
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour obtenir le texte entrée dans une input box
; ----------------------------------------------------------
Function NG_ReturnInput$ ( Id )
	
	NG.NG_Input = Object.NG_Input(Id)
	
	If NG <> Null
			
		Return NG\Label$
	
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour obtenir la position X d'un agent
; ----------------------------------------------------------
Function NG_ReturnAgentX ( Id )
	
	NG.NG_Agent = Object.NG_Agent(Id)
	
	If NG <> Null
			
		Return NG\Px
	
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour obtenir la position Y d'un agent
; ----------------------------------------------------------
Function NG_ReturnAgentY ( Id )
	
	NG.NG_Agent = Object.NG_Agent(Id)
	
	If NG <> Null
		
		Return NG\Py
			
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour obtenir le choix d'une combo Box
; ----------------------------------------------------------
Function NG_ReturnCombo$ ( Id )
	
	NG.NG_Combo = Object.NG_Combo(Id)
	
	If NG <> Null
	
		Return NG\selection$
		
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour obtenir le nombre d'items entrés
; ----------------------------------------------------------
Function NG_ReturnComboHowManyItems ( Id )
	
	NG.NG_Combo = Object.NG_Combo(Id)
	
	If NG <> Null
	
		Return NG\max_item
		
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour obtenir un item par rapport à son numero
; ----------------------------------------------------------
Function NG_ReturnComboItemByNumber$ ( Id , num )
	
	NG.NG_Combo = Object.NG_Combo(Id)
	
	no = 1
	
	If NG <> Null
	
		For Item.NG_ComboItem = Each NG_ComboItem
		
			If NG\ID = Item\ComboID
			
				If no = num
				
					Return Item\Label$
					
				EndIf				
				
				no = no + 1
								
			EndIf
			
		Next
		
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour obtenir l'icone selectionnée d'une iconViewer
; ----------------------------------------------------------
Function NG_ReturnIconViewerIcon$ ( Id )
	
	NG.NG_IconViewer = Object.NG_IconViewer (Id)
	
	If NG <> Null
	
		For Icon.NG_Icon = Each NG_Icon
		
			If NG\sel_ID = Icon\ID
			
				Return Icon\Label$
			
			EndIf
			
		Next
		
	EndIf
	
End Function


; -------------------------------------------------------------
; Pour obtenir le tag de l'icone selectionnée d'une iconViewer
; -------------------------------------------------------------
Function NG_ReturnIconViewerTag$ ( Id )
	
	NG.NG_IconViewer = Object.NG_IconViewer (Id)
	
	If NG <> Null
	
		For Icon.NG_Icon = Each NG_Icon
		
			If NG\sel_ID = Icon\ID
			
				Return Icon\Tag$
			
			EndIf
			
		Next
		
	EndIf
	
End Function


;--------------------------------------------------
; Renvoi si une icone a été droppée
;--------------------------------------------------
Function NG_ReturnDrop ()

	Return NG_DropIcon

End Function

;--------------------------------------------------
; Renvoi si une icone est en drag...
;--------------------------------------------------
Function NG_ReturnDrag ()

	Return NG_DragIcon

End Function

; ------------------------------------------------------------
; Pour obtenir le texte entrée dans une input box multiligne
; ------------------------------------------------------------
Function NG_ReturnMultiInput$ ( Id )
	
	NG.NG_MultiInput = Object.NG_MultiInput(Id)
	
	If NG <> Null
			
		Return NG\Label$
		
	EndIf
	
End Function

; ------------------------------------------------------------
; Pour obtenir la fréquence d'une frame 3D
; ------------------------------------------------------------
Function NG_Return3dFRameFrequence ( Id )
	
	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null
			
		Return NG\freq
		
	EndIf
	
End Function

; ------------------------------------------------------------
; Pour obtenir la fréquence d'une frame 3D
; ------------------------------------------------------------
Function NG_Return3dFrameCamera ( Id )
	
	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null
			
		Return NG\camera
		
	EndIf


End Function

; ------------------------------------------------------------
; Pour obtenir le nombre de FPs d'un Gadget FPS
; ------------------------------------------------------------
Function NG_ReturnFPS ( Id )
	
	NG.NG_FPS = Object.NG_FPS(Id)
	
	If NG <> Null
			
		Return NG\frames
		
	EndIf


End Function

; ------------------------------------------------------------
; Pour obtenir le level d'une progress bar
; ------------------------------------------------------------
Function NG_ReturnProgress ( Id )
	
	NG.NG_Progress = Object.NG_Progress (Id)
	
	If NG <> Null
			
		Return ( Int (NG\level) )
		
	EndIf


End Function

; ------------------------------------------------------------
; Pour obtenir la couleur Rouge d'un gadget Color
; ------------------------------------------------------------
Function NG_ReturnColorRed ( Id )
	
	NG.NG_Color = Object.NG_Color (Id)
	
	If NG <> Null
			
		Return NG\R
		
	EndIf

End Function


; ------------------------------------------------------------
; Pour obtenir la couleur Verte d'un gadget Color
; ------------------------------------------------------------
Function NG_ReturnColorGreen ( Id )
	
	NG.NG_Color = Object.NG_Color (Id)
	
	If NG <> Null
			
		Return NG\G
		
	EndIf

End Function


; ------------------------------------------------------------
; Pour obtenir la couleur Bleue d'un gadget Color
; ------------------------------------------------------------
Function NG_ReturnColorBlue ( Id )
	
	NG.NG_Color = Object.NG_Color (Id)
	
	If NG <> Null
			
		Return NG\B
		
	EndIf

End Function


;--------------------------------------------
; renvoi la couleur rouge du color picker
;--------------------------------------------
Function NG_ReturnRed ()

	Return NG_CPr

End Function

;--------------------------------------------
; renvoi la couleur verte du color picker
;--------------------------------------------
Function NG_ReturnGreen ()

	Return NG_CPg

End Function

;--------------------------------------------
; renvoi la couleur bleue du color picker
;--------------------------------------------
Function NG_ReturnBlue ()

	Return NG_CPb

End Function

;--------------------------------------------
; renvoi le mode de mélange du color picker
;--------------------------------------------
Function NG_ReturnMode ()

	Return NG_CP_Mode

End Function

; ----------------------------------------------------------
; Pour changer le texte d'un Gadget Text
; ----------------------------------------------------------
Function NG_SetText ( Id , label$ )
	
	NG.NG_Text = Object.NG_Text(Id)
	
	If NG <> Null
			
		NG\Label$ = Label$
		
	EndIf


End Function

;--------------------------------------------
; Renvoi les scores du joueur d'un Pong
;--------------------------------------------
Function NG_ReturnPlayerScore ( ID )

	NG.NG_Pong = Object.NG_Pong(ID)
	
	If NG <> Null
	
		Return NG\Player_score
		
	EndIf
			
End Function

;--------------------------------------------
; Renvoi les scores de l'enemi d'un Pong
;--------------------------------------------
Function NG_ReturnComputerScore ( ID )

	NG.NG_Pong = Object.NG_Pong(ID)
	
	If NG <> Null
	
		Return NG\Enemy_score
		
	EndIf
			
End Function


; ----------------------------------------------------------
; Pour changer la couleur d'un texte
; ----------------------------------------------------------
Function NG_SetTextColor ( Id , r , g , b )

	NG.NG_Text = Object.NG_Text(Id)
	
	If NG <> Null

		NG\r = r
		NG\g = g
		NG\b = b
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer la position d'un texte
; ----------------------------------------------------------
Function NG_SetTextPosition ( Id , Px=-999 , Py=-999 )

	NG.NG_Text = Object.NG_Text(Id)
	
	If NG <> Null

		If Px <> -999 Then NG\Px = Px
		If Py <> -999 Then NG\Py = Py
			
	EndIf
	
End Function

; ------------------------------------------------------------------
; Pour déplacer un texte en X et/ou Y (utile pour les génériques)
; ------------------------------------------------------------------
Function NG_MoveText ( Id , mx=0 , my=0 )
	
	NG.NG_Text = Object.NG_Text(Id)
	
	If NG <> Null

		NG\Px = NG\Px + mx
		NG\Py = NG\Py + my
			
	EndIf
	
End Function


;--------------------------------------------------------
; active/désactive et paramètre l'affichage sur une zone
; seulement (viewport)
;--------------------------------------------------------
Function NG_SetTextZone ( Id , on=True , xs , ys , xe , ye )

	NG.NG_Text = Object.NG_Text(Id)
	
	If NG <> Null
	
		NG\zone = on
		
		NG\zone_xs = xs
		NG\zone_ys = ys
		NG\zone_xe = xe
		NG\zone_ye = ye
		
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer le texte d'un bouton normal
; ----------------------------------------------------------
Function NG_SetButtonLabel ( Id , label$ )
	
	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null

		NG\Label$ = label$
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer la taille d'un bouton
; ----------------------------------------------------------
Function NG_SetButtonSize ( Id , Tx , Ty=-1 )
	
	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null

		NG\Tx = Tx
		
		If Ty > - 1 Then NG\Ty = Ty
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer la position d'un bouton
; ----------------------------------------------------------
Function NG_SetButtonPosition ( Id , Px=-1 , Py=-1 )
	
	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null

		If Px > - 1 Then NG\Px = Px
		
		If Py > - 1 Then NG\Py = Py
			
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour changer la position d'un bouton
; ----------------------------------------------------------
Function NG_SetButtonImmuable ( Id , on=0 )
	
	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null

		NG\immuable = on
			
	EndIf
	
End Function

;-----------------------------------------------------------
; fixe le type d'un bouton spécial : UP, DOWN...
;-----------------------------------------------------------
Function NG_SetButtonType ( Id , Typ$ )

	NG.NG_Button = Object.NG_Button ( Id )
	
	If NG <> Null

		NG\typ$ = typ$
			
	EndIf

End Function


; ----------------------------------------------------------
; Pour changer le texte d'un bouton cyclique
; ----------------------------------------------------------
Function NG_SetCycleButtonLabel ( Id , labelnum , label$ )
	
	NG.NG_CycleButton = Object.NG_CycleButton(Id)
	
	If NG <> Null
	
		NG\Label$ [labelnum] = label$
			
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour changer le numero du texte d'un bouton à cycle
; ----------------------------------------------------------
Function NG_SetCycleButtonNum ( Id , labelnum )
	
	NG.NG_CycleButton = Object.NG_CycleButton(Id)
	
	If NG <> Null

		NG\current_label = labelnum
			
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour la ou les image(s) d'un bouton image
; ----------------------------------------------------------
Function NG_SetImageButton ( Id , file1$ , file2$="" )
	
	NG.NG_ImageButton = Object.NG_ImageButton(Id)
	
	If NG <> Null
	
			NG\image1 = LoadImage ( file1$ )
		
			; un masque ou pas ?
			If mr>=0 And mg>=0 And mb>=0
				MaskImage NG\image1 , NG\mr , NG\mg , NG\mb
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
					MaskImage NG\image2 , NG\mr , NG\mg , NG\mb
				EndIf
				
				If NG\resize
					ResizeImage NG\image2 , NG\Tx , NG\Ty
				EndIf
				
				; centrer l'image
				HandleImage NG\image2 , ImageWidth (NG\image2)/2 , ImageHeight (NG\image2)/2
	
			EndIf
			
	EndIf
	
End Function

;--------------------------------------
; change d'image d'un gadget image
;--------------------------------------
Function NG_SetImage ( Id , file$ )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null

		If NG\image > 0 Then FreeImage NG\image

		NG\image = LoadImage ( file$ )
		
		If NG\Tx = 0 Then NG\Tx = ImageWidth ( NG\image )
		If NG\Ty = 0 Then NG\Ty = ImageHeight ( NG\image )
			
		If NG\Tx <> 0 And NG\Ty <> 0
			ResizeImage NG\image , NG\Tx , NG\Ty
		EndIf

	EndIf
	
End Function


;--------------------------------------
; change la position d'une image
;--------------------------------------
Function NG_SetImagePosition ( Id , Px , Py )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null

		NG\Px = Px
		NG\Py = Py
		
	EndIf
	
End Function


;--------------------------------------------------------
; change d'image d'un gadget image à partir d'un handle
;--------------------------------------------------------
Function NG_SetImageFromHandle ( Id , Hand )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null
	
		NG\image = hand
		
		If NG\Tx = 0 Then NG\Tx = ImageWidth ( NG\image )
		If NG\Ty = 0 Then NG\Ty = ImageHeight ( NG\image )
			
		If NG\Tx <> 0 And NG\Ty <> 0
			ResizeImage NG\image , NG\Tx , NG\Ty
		EndIf

	EndIf
	
End Function

;--------------------------------------------------------
; change d'image d'un gadget image à partir d'un handle
; sans la redimensionner !
;--------------------------------------------------------
Function NG_SetImageFromHandleForPreview ( Id , Hand , Tx , Ty )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null
	
		NG\image = hand

		NG\Tx = Tx
		NG\Ty = Ty
			
	EndIf
	
End Function

;------------------------------------------------------------------
; change d'image d'un gadget image à partir d'une copie de handle
;------------------------------------------------------------------
Function NG_SetImageFromHandleCopy ( Id , Hand )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null
	
		NG\image = CopyImage (hand)
		
		If NG\Tx = 0 Then NG\Tx = ImageWidth ( NG\image )
		If NG\Ty = 0 Then NG\Ty = ImageHeight ( NG\image )
			
		If NG\Tx <> 0 And NG\Ty <> 0
			ResizeImage NG\image , NG\Tx , NG\Ty
		EndIf

	EndIf
	
End Function


;--------------------------------------------------------
; rend une image reperable pour NG_OnGadget = True
;-------------ie de handle-------------------------------------------
Function NG_SetImageTrueGadget ( Id )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null
	
		NG\truegadget = True
		
	EndIf
	
End Function


;--------------------------------------------------------
; active/désactive et paramètre l'affichage sur une zone
; seulement (viewport)
;--------------------------------------------------------
Function NG_SetImageZone ( Id , on=True , xs , ys , xe , ye )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null
	
		NG\zone = on
		
		NG\zone_xs = xs
		NG\zone_ys = ys
		NG\zone_xe = xe
		NG\zone_ye = ye
		
	EndIf
	
End Function



; ----------------------------------------------------------
; Pour switcher l'état d'un checkbox
; ----------------------------------------------------------
Function NG_SwitchCheckBox ( Id )

	NG.NG_CheckBox = Object.NG_CheckBox(Id)
	
	If NG <> Null
	
		NG\Check = 1 - NG\Check
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer l'état d'un checkbox
; ----------------------------------------------------------
Function NG_SetCheckBox ( Id , Check )
	
	NG.NG_CheckBox = Object.NG_CheckBox(Id)
	
	If NG <> Null
			
		NG\Check = check
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer le texte d'un bouton à cocher
; ----------------------------------------------------------
Function NG_SetCheckBoxLabel ( Id , Label$ )
	
	NG.NG_CheckBox = Object.NG_CheckBox(Id)
	
	If NG <> Null
	
		NG\Label$ = label$
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer la valeur d'une Value
; ----------------------------------------------------------
Function NG_SetValue ( Id , value# )
	
	NG.NG_Value = Object.NG_Value(Id)
	
	If NG <> Null
		
		NG\value_int = Int value#
		
		NG\value# = value#
		
		; le Text Focus est désactivé
		;NG_TF_Id  = -1
		;NG_TF_On  = False
			
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour changer le texte d'un input box 1 ligne
; ----------------------------------------------------------
Function NG_SetInput ( Id , Label$ )
	
	NG.NG_Input = Object.NG_Input(Id)
	
	If NG <> Null
		
		NG\Label$ = Label$
		
		; le Text Focus est désactivé
		;NG_TF_Id  = -1
		;NG_TF_On  = False
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer le texte d'un input box multi-ligne
; ----------------------------------------------------------
Function NG_SetMultiInput ( Id , Label$ )

	NG.NG_MultiInput = Object.NG_MultiInput(Id)
	
	If NG <> Null

		NG\Label$ = Label$
			
		NG_SetMultiInputToTop ( Id )
								
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer la taille d'un multi input
; ----------------------------------------------------------
Function NG_SetMultiInputSize ( Id , Tx , Ty )

	NG.NG_MultiInput = Object.NG_MultiInput(Id)
	
	If NG <> Null

		NG\Tx = Tx
		NG\Ty = Ty
		
		NG_SetMultiInputToTop ( Id )
								
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour changer le texte d'un input box multi-ligne
; ----------------------------------------------------------
Function NG_SetMultiInputToTop ( Id )
	
	NG.NG_MultiInput = Object.NG_MultiInput(Id)
	
	If NG <> Null

		NG\ToTop = True
					
	EndIf

End Function


;------------------------------------------------------
; Ajoute un item à un Menu
;------------------------------------------------------
Function NG_AddMenuItem ( MenuID , Label$ )

	;-------------------------------------------
	; on recherche le menu
	;-------------------------------------------
	Men.NG_Menu = Object.NG_Menu (MenuID)
	
	If Men <> Null
	
		NG.NG_MenuItem = New NG_MenuItem
	
			NG\MenuId = MenuId
			
			;---------------------------------------
			; par defaut, c'est un menu parent
			;---------------------------------------
			NG\me_parent = True
			
			;---------------------------------------
			; extraction de l'item
			;---------------------------------------
			the_label$ = ""
			car$ = ""
			
			found = 0
			
						
			For i = 1 To Len (Label$)
			
				car$ = Mid$ ( Label$ , i , 1 )
				
				;------------------------------------------------------------------
				; si on tombe sur un "\", c'est donc que c'"était un menu parent
				;------------------------------------------------------------------
				; donc notre item sera bien un menu enfant
				;------------------------------------------------------------------
				If car$ <> "\"
				
					the_label$ = the_label$ + car$
				
				Else If car$ = "\"
				
					found = 1
									
					NG\parent$ = the_label$
					NG\me_parent = False
					NG\SubLevel = True
					
					For item2.NG_MenuItem = Each NG_MenuItem
						If item2\MenuId = MenuId
							If item2\Label$ = NG\Parent$
								item2\me_parent = True
							EndIf
						EndIf
					Next
										
					the_label$ = ""				
				
				EndIf
							
			Next
			
			;If found = 1
			;	NG\me_parent = False
			;	;NG\parent$ = the_label$
			;EndIf
			
			;--------------------------------
			; attribution du label
			;--------------------------------
			NG\label$ = the_label$
						
	EndIf

End Function


;------------------------------------------------------
; Ajoute une icone dans un IconViewer
;------------------------------------------------------
Function NG_AddIcon ( ViewerID , image , Label$ , Tag$="" )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		NG.NG_Icon = New NG_Icon
		
			NG\ViewerID = ViewerID
			
			NG\ID = Handle (NG)
			
			NG\image = image
			
			NG\Label$ = Label$
			
			NG\Tag$ = Tag$
			
			NG\size = IV\IconSize
			
			If IV\Icons_disp = True
			
				width = ImageWidth (NG\image)
				height = ImageHeight (NG\image)
				
				;-----------------------------------------------------------
				; si l'icone est de taille differente, on la redimensionne
				;-----------------------------------------------------------
				If width <> NG\size Or heigth <> NG\size
					
					ResizeImage ( NG\image , NG\size , NG\size )
				
				EndIf 
				
				MaskImage NG\image , NG_maskR , NG_maskG , NG_maskB
			
			EndIf
			
			;--------------------------------
			; une icone de plus
			;--------------------------------
			IV\Icons = IV\Icons + 1
	
	EndIf
		
		
End Function


;------------------------------------------------------
; Change le nom d'une icone d'un IconViewer
;------------------------------------------------------
Function NG_SetIconLabel ( ViewerID , Label$ , NewLabel$ )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		For NG.NG_Icon = Each NG_Icon
		
			If NG\ViewerID = ViewerID
			
				If NG\Label$ = Label$
				
					NG\Label$ = NewLabel$
					
				EndIf
			
			EndIf
		
		Next
	
	EndIf
	
End Function


;----------------------------------------------------------------
; Change le nom d'une icone d'un IconViewer à partir de son tag
;----------------------------------------------------------------
Function NG_SetIconLabelFromTag ( ViewerID , Tag$ , NewLabel$ )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		For NG.NG_Icon = Each NG_Icon
		
			If NG\ViewerID = ViewerID
			
				If NG\Tag$ = Tag$
				
					NG\Label$ = NewLabel$
					
				EndIf
			
			EndIf
		
		Next
	
	EndIf
	
End Function


;------------------------------------------------------
; Change le tag d'une icone d'un IconViewer
;------------------------------------------------------
Function NG_SetIconTag ( ViewerID , Label$ , NewTag$ )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		For NG.NG_Icon = Each NG_Icon
		
			If NG\ViewerID = ViewerID
			
				If NG\Label$ = Label$
				
					NG\Tag$ = NewTag$
					
				EndIf
			
			EndIf
		
		Next
	
	EndIf
	
End Function


;----------------------------------------------------------------
; Change le tag d'une icone d'un IconViewer à partir de son tag
;----------------------------------------------------------------
Function NG_SetIconTagFromTag ( ViewerID , Tag$ , NewTag$ )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		For NG.NG_Icon = Each NG_Icon
		
			If NG\ViewerID = ViewerID
			
				If NG\Tag$ = Tag$
				
					NG\Tag$ = NewTag$
					
				EndIf
			
			EndIf
		
		Next
	
	EndIf
	
End Function


;------------------------------------------------------
; Supprime une icone d'un IconViewer
;------------------------------------------------------
Function NG_DeleteIcon ( ViewerID , Label$ )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		For NG.NG_Icon = Each NG_Icon
		
			If NG\ViewerID = ViewerID
			
				If NG\Label$ = Label$
				
					Delete NG
					
				EndIf
			
			EndIf
		
		Next
	
	EndIf
	
End Function


;----------------------------------------------------------
; Supprime une icone d'un IconViewer à partir de son Tag
;----------------------------------------------------------
Function NG_DeleteIconFromTag ( ViewerID , Tag$ )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		For NG.NG_Icon = Each NG_Icon
		
			If NG\ViewerID = ViewerID
			
				If NG\Tag$ = Tag$
				
					Delete NG
					
				EndIf
			
			EndIf
		
		Next
	
	EndIf
	
End Function

;------------------------------------------------------
; Supprime toutes les icones d'un IconViewer
;------------------------------------------------------
Function NG_DeleteIconViewerIcons ( ViewerID )

	;-------------------------------------------
	; on recherche l'iconviewer
	;-------------------------------------------
	IV.NG_IconViewer = Object.NG_IconViewer (ViewerID)
	
	If IV <> Null

		For NG.NG_Icon = Each NG_Icon
		
			If NG\ViewerID = ViewerID
			
				Delete NG
				
			EndIf
				
		Next
		
		IV\index = 1
		IV\Icons  = 0
		IV\sel_x  = -1
		IV\sel_y  = -1
		IV\sel_ID = -1
	
	EndIf
	
End Function


;------------------------------------------------------
; ajoute un raccourci pour le file selector
;------------------------------------------------------
Function NG_AddShorcut ( path$ )

	NG_AddIcon ( NG_FSshortcut , NG_IconShort , path$ )

End Function

;------------------------------------------------------
; supprime un raccourci pour le file selector
;------------------------------------------------------
Function NG_DeleteShorcut ( path$ )

	NG_DeleteIcon ( NG_FSshortcut , path$ )

End Function


;-----------------------------------------------------
; ajoute une colonne à un Board
;-----------------------------------------------------
Function NG_AddColumn ( BoardId , Label$ )

	NG.NG_Board = Object.NG_Board ( BoardId )
	
	If NG <> Null
	
		Col.NG_BoardCol = New NG_BoardCol
		
			Col\BoardId = BoardId
			
			Col\Id = Handle (Col)
				
			Col\Label$ = Label$
			
			;---------------------------------
			; taille du bouton
			;---------------------------------
			SetFont ( NG_NormalFont )
			
			width = StringWidth ( Label$ ) + 10
			height = FontHeight () + 5
						
			Col\Button = NG_CreateButton ( 0 , 0 , 0 , width , height , Col\Label$ , 0 , Col\Label$ )
			
			Col\Width = width
				
	EndIf

End Function

;---------------------------------------------------------------
; ajoute un item ligne pour un Board et une Colonne spécifiés
;---------------------------------------------------------------
Function NG_AddBoardElement ( BoardId , Num , Label$ )

	NG.NG_Board = Object.NG_Board (ID)
	
	If NG <> Null
	
		For Col.NG_BoardCol = Each NG_BoardCol
		
			If Col\BoardId = BoardId
			
				Ligne.NG_BoardLine = New NG_BoardLine
			
				Ligne\BoardId = BoardId
			
				Ligne\Id = Handle (Ligne)
			
				Ligne\ColId = Col\Id
				
				Ligne\ColLabel$ = Col\Label$
			
				Ligne\Label$ = Label$
				
				Ligne\num = num		
		
			EndIf
		
		Next
			
	EndIf

End Function



;--------------------------------------------
; Déplace la raquette joueur d'un Pong
;--------------------------------------------
Function NG_MovePong ( ID , Val )

	NG.NG_Pong = Object.NG_Pong(ID)
	
	If NG <> Null
	
		NG\Player_Py = NG\Player_Py + val
		
	EndIf
			
End Function


;--------------------------------------------
; lance ou termine une partie Pong
;--------------------------------------------
Function NG_SetPongGame ( ID , set )

	NG.NG_Pong = Object.NG_Pong(ID)
	
	If NG <> Null
	
		If set = 1 ; start

			NG\start = True
			
			NG\Player_Py = NG\Raq_saved_Py
			NG\Enemy_Py = NG\Raq_saved_Py
			
			NG\Ball_Px = NG\Ball_saved_Px
			NG\Ball_Py = NG\Ball_saved_Py
			
			NG\Player_score = 0
			NG\Enemy_score = 0
			
		Else ; finish
		
			NG\start = False
			
		EndIf	
		
	EndIf
			
End Function


;--------------------------------------------
; Règle la selection d'une comboBox
;--------------------------------------------
Function NG_SetCombo ( ComboID , Label$ )

	NG.NG_Combo = Object.NG_Combo(ComboID)
	
	If NG <> Null
	
	;-------------------------------------------------
	; mise à jour du nombre d'items de la combo box
	;-------------------------------------------------
		For Item.NG_ComboItem = Each NG_ComboItem
	
			If Item\ComboID = ComboID
		
				If Item\Label$ = Label$
		
					NG\selection$ = ITem\Label$
					
				EndIf
		
			EndIf
			
		Next
		
	EndIf
			
End Function

;--------------------------------------------
; Ajoute un item à une comboBox
;--------------------------------------------
Function NG_AddComboItem ( ComboID , Label$ )

	;-------------------------------------------------
	; mise à jour du nombre d'items de la combo box
	;-------------------------------------------------
	NG.NG_Combo = Object.NG_Combo(ComboID)
	
	If NG <> Null
		
		NG\max_item = NG\max_item + 1
		
		If NG\max_item < NG\lignmax_saved
		
			NG\lignmax = NG\max_item
		
		Else
		
			NG\lignmax = NG\lignmax_saved
		
		EndIf	
		
		
						
	EndIf
	
	;--------------------------
	; création du nouvel item
	;--------------------------
	Item.NG_ComboItem = New NG_ComboItem
	
		Item\ComboID = ComboID
		
		Item\Label$ = Label$

End Function

;--------------------------------------------
; Efface un item d'une comboBox
;--------------------------------------------
Function NG_DeleteComboItem ( ComboID , Label$ )

	;-------------------------------------------------
	; mise à jour du nombre d'items de la combo box
	;-------------------------------------------------
	NG.NG_Combo = Object.NG_Combo(ComboID)
	
	If NG <> Null
		
		NG\max_item = NG\max_item - 1
		
		If NG\max_item = 0 Then NG\selection$ = ""
			
	EndIf


	;-------------------------------
	; suppression de l'item
	;-------------------------------
	For Item.NG_ComboItem = Each NG_ComboItem
	
		If Item\ComboID = ComboID
		
			If Item\Label$ = Label$
		
				Delete Item
				
			EndIf
		
		EndIf
		
	Next
		
End Function

;--------------------------------------------
; Efface tous les items de la combo box
;--------------------------------------------
Function NG_DeleteAllComboItem ( ComboID )

	;-------------------------------------------------
	; mise à jour du nombre d'items de la combo box
	;-------------------------------------------------
	NG.NG_Combo = Object.NG_Combo(ComboID)
	
	If NG <> Null
		
		NG\max_item = 0
			
	EndIf


	;-------------------------------
	; suppression de l'item
	;-------------------------------
	For Item.NG_ComboItem = Each NG_ComboItem
	
		If Item\ComboID = ComboID
		
			Delete Item
					
		EndIf
		
	Next
		
End Function

; ----------------------------------------------------------
; Pour changer les valeurs d'un doseur
; ----------------------------------------------------------
Function NG_SetDoseur ( Id , val# , min#=-6666666 , max#=-6666666 , label$="" )

	NG.NG_Doseur = Object.NG_Doseur(Id)
	
	If NG <> Null
	
		NG\Val# = val#
			
		If min# <> -6666666
			NG\min# = min#
		EndIf
			
		If max# <> -6666666
			NG\max# = max#
		EndIf
			
		If label$ <> ""
			
			NG\Label$ = label$
			
		EndIf
			
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer la position d'un agent
; ----------------------------------------------------------
Function NG_SetAgentPosition ( Id , Px=-999 , Py=-999 )
	
	NG.NG_Agent = Object.NG_Agent(Id)
	
	If NG <> Null
		
		If Px <> -999 Then NG\Px = Px
		If Py <> -999 Then NG\Py = Px
		
	EndIf
	
End Function

; ----------------------------------------------------------
; Pour changer les limites de mouvement d'un agent
; ----------------------------------------------------------
Function NG_SetAgentLimits ( Id , LimitX=False , LimitY=False )
	
	NG.NG_Agent = Object.NG_Agent(Id)
	
	If NG <> Null
		
		NG\LimitX = LimitX
		NG\LimitY = LimitY
		
	EndIf
	
End Function


; ----------------------------------------------------------
; Pour changer les limites de zone d'un agent
; ----------------------------------------------------------
Function NG_SetAgentZone ( Id , Xs , Ys , Xe , Ye )
	
	NG.NG_Agent = Object.NG_Agent(Id)
	
	If NG <> Null
		
		NG\Xs = Xs
		NG\Ys = Ys
		NG\Xe = Xe
		NG\Ye = Ye
		
	EndIf
	
End Function

; -------------------------------------------------------------------
; Pour changer la frequence d'une 3d Frame
; -------------------------------------------------------------------
Function NG_Set3dFrameFrequence ( Id , freq )

	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null
	
		NG\freq = freq
						
	EndIf
	
End Function


;--------------------------------------------------------
; rend une 3dframe reperable pour NG_OnGadget = True
;--------------------------------------------------------
Function NG_Set3dFrameTrueGadget ( Id )

	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null
	
		NG\truegadget = True
		
	EndIf
	
End Function


; -------------------------------------------------------------------
; Pour changer la frequence d'une 3d Frame
; -------------------------------------------------------------------
Function NG_Set3dFrameCamera ( Id , cam )

	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null

		FreeEntity NG\camera			
		NG\camera = cam
						
	EndIf
	
End Function

; ---------------------------------------
; Pour de switcher (on/off) une 3dframe
; ---------------------------------------
Function NG_Switch3dFrame ( Id )
	
	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null

		NG\active = 1 - NG\active
						
	EndIf
	
End Function


; ---------------------------------------
; Pour de switcher (on/off) une 3dframe
; ---------------------------------------
Function NG_Set3dFrame ( Id , active )
	
	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null
		
		NG\active = active
						
	EndIf
	
End Function


; ---------------------------------------
; Pour régler la taile d'une 3dframe
; ---------------------------------------
Function NG_Set3dFrameSize ( Id , Tx , Ty )
	
	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null
		
		NG\Tx = Tx
		NG\Ty = Ty
		
		ResizeImage NG\image , Tx , Ty
						
	EndIf
	
End Function


; ----------------------------------------------
; Pour de régler la valeur d'une progress bar
; ----------------------------------------------
Function NG_SetProgress ( Id , level )
	
	NG.NG_Progress = Object.NG_Progress(Id)
	
	If NG <> Null
		
		NG\Level = level
		
		;-------------------------------------
		; limites
		;-------------------------------------
		If NG\level < 0 Then NG\level = 0
		If NG\level > 100 Then NG\level = 100
								
	EndIf
	
End Function

;---------------------------------------------
; détruit complétement une frame 
;---------------------------------------------
Function NG_DeleteFrame (Id , from_window=0 )

	For NG.NG_Frame = Each NG_Frame
	
		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function

;--------------------------------------------
; détruit complétement un texte
;--------------------------------------------
Function NG_DeleteText (Id , from_window=0 )

	For NG.NG_Text = Each NG_Text
	
		; valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function


;------------------------------------------------------
; détruit complètement une couleur
;------------------------------------------------------
Function NG_DeleteColor (Id , from_window=0 )

	For NG.NG_Color = Each NG_Color
	
		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function

;------------------------------------------------------
; détruit complètement un bouton
;------------------------------------------------------
Function NG_DeleteButton (Id , from_window=0 )

	For NG.NG_Button = Each NG_Button

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function

;------------------------------------------------------
; détruit complètement une value
;------------------------------------------------------
Function NG_DeleteValue (Id , from_window=0 )

	For NG.NG_Value = Each NG_Value

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function

;------------------------------------------------------
; détruit complètement un input
;------------------------------------------------------
Function NG_DeleteInput (Id , from_window=0 )

	For NG.NG_Input = Each NG_Input

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function

;------------------------------------------------------
; détruit complètement un multi input
;------------------------------------------------------
Function NG_DeleteMultiInput (Id , from_window=0 )

	For NG.NG_MultiInput = Each NG_MultiInput

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function

;------------------------------------------------------
; détruit complètement un cycle bouton
;------------------------------------------------------
Function NG_DeleteCycleButton (Id , from_window=0 )

	For NG.NG_CycleButton = Each NG_CycleButton

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function

;------------------------------------------------------
; détruit complètement un cycle bouton
;------------------------------------------------------
Function NG_DeleteDoseur (Id , from_window=0 )

	For NG.NG_Doseur = Each NG_Doseur

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function


;------------------------------------------------------
; détruit complètement un IconViewer
;------------------------------------------------------
Function NG_DeleteIconViewer (Id , from_window=0 )

	For NG.NG_IconViewer = Each NG_IconViewer

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
			
				NG_DeleteIconViewerIcons ( NG\ID )
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
			
				NG_DeleteIconViewerIcons ( NG\ID )
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function

;------------------------------------------------------
; détruit complètement un cycle bouton
;------------------------------------------------------
Function NG_DeleteImageButton (Id , from_window=0 )

	For NG.NG_ImageButton = Each NG_ImageButton

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				If NG\image1 <> 0 Then FreeImage NG\image1
				If NG\image2 <> 0 Then FreeImage NG\image2

				Delete NG
			
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				If NG\image1 <> 0 Then FreeImage NG\image1
				If NG\image2 <> 0 Then FreeImage NG\image2

				Delete NG
			
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function

;------------------------------------------------------
; détruit complètement une image
;------------------------------------------------------
Function NG_DeleteImage (Id , from_window=0 )

	For NG.NG_Image = Each NG_Image

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				If NG\image <> 0 Then FreeImage NG\image
			
				Delete NG
			
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				If NG\image <> 0 Then FreeImage NG\image
			
				Delete NG
			
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function

;------------------------------------------------------
; détruit complètement un FPS
;------------------------------------------------------
Function NG_DeleteFPS (Id , from_window=0 )

	For NG.NG_FPS = Each NG_FPS

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function

;------------------------------------------------------
; détruit complètement une comboBox
;------------------------------------------------------
Function NG_DeleteCombo (Id , from_window=0 )

	For NG.NG_Combo = Each NG_Combo

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
			
				NG_DeleteAllComboItem ( NG\ID )
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
			
				NG_DeleteAllComboItem ( NG\ID )
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function

;------------------------------------------------------
; détruit complètement une 3d frame
;------------------------------------------------------
Function NG_Delete3dFrame (Id , from_window=0 )

	For NG.NG_3dFrame = Each NG_3dFrame

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				If NG\image <> 0 Then FreeImage NG_image
			
				If NG\camera <> 0 Then FreeEntity NG\camera
			
				Delete NG
			
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				If NG\image <> 0 Then FreeImage NG_image
			
				If NG\camera <> 0 Then FreeEntity NG\camera
			
				Delete NG
			
				Exit

	
			EndIf
		
		EndIf

	Next 

End Function

;------------------------------------------------------
; détruit complètement une checkbox
;------------------------------------------------------
Function NG_DeleteCheckBox (Id , from_window=0 )

	For NG.NG_CheckBox = Each NG_CheckBox

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function


;------------------------------------------------------
; détruit complètement une progress bar
;------------------------------------------------------
Function NG_DeleteProgress ( Id , from_window=0 )

	For NG.NG_Progress = Each NG_Progress

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function


;------------------------------------------------------
; détruit complètement un agent
;------------------------------------------------------
Function NG_DeleteAgent ( Id , from_window=0 )

	For NG.NG_Agent = Each NG_Agent

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf

	Next 

End Function


;------------------------------------------------------
; détruit complètement un Menu
;------------------------------------------------------
Function NG_DeleteMenu ( Id , from_window=0 )

	For NG.NG_Menu = Each NG_Menu

		; détruit le gadget si la valeur ID est la sienne
		;--------------------------------------------------
		If from_window = 0
		
			If NG\Id = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		; détruit le gadget si la valeur ID est celle de sa fenetre parente
		;--------------------------------------------------------------------
		Else If from_window = 1
		
			If NG\WinId = Id
	
				Delete NG
				
				Exit
	
			EndIf
		
		EndIf


	Next 

End Function


;---------------------------------------------------------------------------
; Quand on ferme une fenetre, son Display passe à False ce qui l'empeche
; d'être traitée dans la boucle NG_DrawWindow ()... Donc, tous ses
; gadgets ne sont plus traités non plus. Pourtant, ils doivent etre mis
; en State = 0 pour ne pas êtres considerés comme tout le temps appuyés
; Cette fonction permet de les mettre à 0
;---------------------------------------------------------------------------
Function NG_ResetAllButtons ( WinId )

	win.NG_Window = Object.NG_Window ( WinId )
	
	If win <> Null

		For NG1.NG_Button = Each NG_Button
		
			If NG1\WinId = Win\Id
			
				NG1\State = 0
								
			EndIf
		
		Next
		
		For NG2.NG_CycleButton = Each NG_CycleButton
		
			If NG2\WinId = Win\Id
			
				NG2\State = 0
								
			EndIf
		
		Next
		
		For NG3.NG_ImageButton = Each NG_ImageButton
		
			If NG3\WinId = Win\Id
			
				NG3\State = 0
								
			EndIf
		
		Next
		
	EndIf
	
End Function


;----------------
; T A B S
;----------------


;-----------------------------------------
; attache une frame à un tab
;-----------------------------------------
Function NG_AttachFrame ( Id , tab_label$ )

	NG.NG_Frame = Object.NG_Frame(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function

;-----------------------------------------
; attache un texte à un tab
;-----------------------------------------
Function NG_AttachText ( Id , tab_label$ )

	NG.NG_Text = Object.NG_Text(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function

;-----------------------------------------
; attache une couleur à un tab
;-----------------------------------------
Function NG_AttachColor ( Id , tab_label$ )

	NG.NG_Color = Object.NG_Color(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function

;-----------------------------------------
; attache un input à un tab
;-----------------------------------------
Function NG_AttachInput ( Id , tab_label$ )

	NG.NG_Input = Object.NG_Input(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function


;-----------------------------------------
; attache une value à un tab
;-----------------------------------------
Function NG_AttachValue ( Id , tab_label$ )

	NG.NG_Value = Object.NG_Value(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache un Multi input à un tab
;-----------------------------------------
Function NG_AttachMultiInput ( Id , tab_label$ )

	NG.NG_MultiInput = Object.NG_MultiInput(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache une checkbox à un tab
;-----------------------------------------
Function NG_AttachCheckBox ( Id , tab_label$ )

	NG.NG_CheckBox = Object.NG_CheckBox(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function

;-----------------------------------------
; attache une combo box à un tab
;-----------------------------------------
Function NG_AttachCombo ( Id , tab_label$ )

	NG.NG_Combo = Object.NG_Combo(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache un bouton à un tab
;-----------------------------------------
Function NG_AttachButton ( Id , tab_label$ )

	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache un cycle bouton à un tab
;-----------------------------------------
Function NG_AttachCycleButton ( Id , tab_label$ )

	NG.NG_CycleButton = Object.NG_CycleButton(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function

;-----------------------------------------
; attache un doseur à un tab
;-----------------------------------------
Function NG_AttachDoseur ( Id , tab_label$ )

	NG.NG_Doseur = Object.NG_Doseur(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function

;-----------------------------------------
; attache une image à un tab
;-----------------------------------------
Function NG_AttachImage ( Id , tab_label$ )

	NG.NG_Image = Object.NG_Image(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache un bouton image à un tab
;-----------------------------------------
Function NG_AttachImageButton ( Id , tab_label$ )

	NG.NG_ImageButton = Object.NG_ImageButton(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf
	
End Function

;-----------------------------------------
; attache une frame 3D à un tab
;-----------------------------------------
Function NG_Attach3dFrame ( Id , tab_label$ )

	NG.NG_3dFrame = Object.NG_3dFrame(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache une IconViewer à un tab
;-----------------------------------------
Function NG_AttachIconViewer ( Id , tab_label$ )

	NG.NG_IconViewer = Object.NG_IconViewer(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache un FPS à un tab
;-----------------------------------------
Function NG_AttachFPS ( Id , tab_label$ )

	NG.NG_FPS = Object.NG_FPS(Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function


;-----------------------------------------
; attache une Progress bar à un tab
;-----------------------------------------
Function NG_AttachProgress ( Id , tab_label$ )

	NG.NG_Progress = Object.NG_Progress (Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function

;-----------------------------------------
; attache un Agent à un tab
;-----------------------------------------
Function NG_AttachAgent ( Id , tab_label$ )

	NG.NG_Agent = Object.NG_Agent (Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function


;-----------------------------------------
; attache un Menu
;-----------------------------------------
Function NG_AttachMenu ( Id , tab_label$ )

	NG.NG_Menu = Object.NG_Menu (Id)
	
	If NG <> Null
	
		NG\Tab$ = tab_label$
					
	EndIf

	
End Function


;-----------------------------------------------
; Les fonctions Hide/Show
;-----------------------------------------------

;-----------------------------------------
; montre/masque une Frame
;-----------------------------------------
Function NG_ShowFrame ( Id , show=True )

	NG.NG_Frame = Object.NG_Frame (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Texte
;-----------------------------------------
Function NG_ShowText ( Id , show=True )

	NG.NG_Text = Object.NG_Text (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une couleur
;-----------------------------------------
Function NG_ShowColor ( Id , show=True )

	NG.NG_Color = Object.NG_Color (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un input
;-----------------------------------------
Function NG_ShowInput ( Id , show=True )

	NG.NG_Input = Object.NG_Input (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function

;-----------------------------------------
; montre/masque une value
;-----------------------------------------
Function NG_ShowValue ( Id , show=True )

	NG.NG_Value = Object.NG_Value (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un multi input
;-----------------------------------------
Function NG_ShowMultiInput ( Id , show=True )

	NG.NG_MultiInput = Object.NG_MultiInput (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une checkbox
;-----------------------------------------
Function NG_ShowCheckBox ( Id , show=True )

	NG.NG_CheckBox = Object.NG_CheckBox (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un combo
;-----------------------------------------
Function NG_ShowCombo ( Id , show=True )

	NG.NG_Combo = Object.NG_Combo (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un bouton
;-----------------------------------------
Function NG_ShowButton ( Id , show=True )

	NG.NG_Button = Object.NG_Button (Id)
	
	If NG <> Null
	
		NG\Show = show
		
		NG\state = 0
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une bouton à cycle
;-----------------------------------------
Function NG_ShowCycleButton ( Id , show=True )

	NG.NG_CycleButton = Object.NG_CycleButton (Id)
	
	If NG <> Null
	
		NG\Show = show
		
		NG\state = 0
					
	EndIf
	
End Function

;-----------------------------------------
; montre/masque un doseur
;-----------------------------------------
Function NG_ShowDoseur ( Id , show=True )

	NG.NG_Doseur = Object.NG_Doseur (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function

;-----------------------------------------
; montre/masque une image
;-----------------------------------------
Function NG_ShowImage ( Id , show=True )

	NG.NG_Image = Object.NG_Image (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un bouton image
;-----------------------------------------
Function NG_ShowImageButton ( Id , show=True )

	NG.NG_ImageButton = Object.NG_ImageButton (Id)
	
	If NG <> Null
	
		NG\Show = show
		
		NG\state = 0
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une 3D Frame
;-----------------------------------------
Function NG_Show3DFrame ( Id , show=True )

	NG.NG_3DFrame = Object.NG_3DFrame (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Icon Viewer
;-----------------------------------------
Function NG_ShowIconViewer ( Id , show=True )

	NG.NG_IconViewer = Object.NG_IconViewer (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un FPS
;-----------------------------------------
Function NG_ShowFPS ( Id , show=True )

	NG.NG_FPS = Object.NG_FPS (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une Progress Bar
;-----------------------------------------
Function NG_ShowProgress ( Id , show=True )

	NG.NG_Progress = Object.NG_Progress (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Agent
;-----------------------------------------
Function NG_ShowAgent ( Id , show=True )

	NG.NG_Agent = Object.NG_Agent (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Menu
;-----------------------------------------
Function NG_ShowMenu ( Id , show=True )

	NG.NG_Menu = Object.NG_Menu (Id)
	
	If NG <> Null
	
		NG\Show = show
					
	EndIf
	
End Function


;----------------------------------------------------------
; fonctions Ice
;----------------------------------------------------------
; par défaut, tous les gadgets suivent l'origine de dessin
; de leur fenetre, meme si elle est modifiée (<> 0,0)
; Il est alors possible de les rendre statiques, cad avec
; des coordonnée de references tjs identiques : 0,0
;----------------------------------------------------------

;-----------------------------------------
; montre/masque une Frame
;-----------------------------------------
Function NG_FreezeFrame ( Id , ice=True )

	NG.NG_Frame = Object.NG_Frame (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une Couleur
;-----------------------------------------
Function NG_FreezeColor ( Id , ice=True )

	NG.NG_Color = Object.NG_Color (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function
 

;-----------------------------------------
; montre/masque un Texte
;-----------------------------------------
Function NG_FreezeText ( Id , ice=True )

	NG.NG_Text = Object.NG_Text (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Input
;-----------------------------------------
Function NG_FreezeInput ( Id , ice=True )

	NG.NG_Input = Object.NG_Input (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une valeur
;-----------------------------------------
Function NG_FreezeValue ( Id , ice=True )

	NG.NG_Value = Object.NG_Value (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un MultiInput
;-----------------------------------------
Function NG_FreezeMultiInput ( Id , ice=True )

	NG.NG_MultiInput = Object.NG_MultiInput (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une Check Box
;-----------------------------------------
Function NG_FreezeCheckBox ( Id , ice=True )

	NG.NG_CheckBox = Object.NG_CheckBox (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une Combo
;-----------------------------------------
Function NG_FreezeCombo ( Id , ice=True )

	NG.NG_Combo = Object.NG_Combo (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un bouton
;-----------------------------------------
Function NG_FreezeButton ( Id , ice=True )

	NG.NG_Button = Object.NG_Button (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un bouton cycle
;-----------------------------------------
Function NG_FreezeCycleButton ( Id , ice=True )

	NG.NG_CycleButton = Object.NG_CycleButton (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Doseur
;-----------------------------------------
Function NG_FreezeDoseur ( Id , ice=True )

	NG.NG_Doseur = Object.NG_Doseur (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une Image
;-----------------------------------------
Function NG_FreezeImage ( Id , ice=True )

	NG.NG_Image = Object.NG_Image (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un bouton image
;-----------------------------------------
Function NG_FreezeImageButton ( Id , ice=True )

	NG.NG_ImageButton = Object.NG_ImageButton (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une 3D Frame
;-----------------------------------------
Function NG_Freeze3dFrame ( Id , ice=True )

	NG.NG_3dFrame = Object.NG_3dFrame (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un IconViewer
;-----------------------------------------
Function NG_FreezeIconViewer ( Id , ice=True )

	NG.NG_IconViewer = Object.NG_IconViewer (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une FPS
;-----------------------------------------
Function NG_FreezeFPS ( Id , ice=True )

	NG.NG_FPS = Object.NG_FPS (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque une Progress Bar
;-----------------------------------------
Function NG_FreezeProgress ( Id , ice=True )

	NG.NG_Progress = Object.NG_Progress (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Agent
;-----------------------------------------
Function NG_FreezeAgent ( Id , ice=True )

	NG.NG_Agent = Object.NG_Agent (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function


;-----------------------------------------
; montre/masque un Menu (par défaut, oui)
;-----------------------------------------
Function NG_FreezeMenu ( Id , ice=True )

	NG.NG_Menu = Object.NG_Menu (Id)
	
	If NG <> Null
	
		NG\ice = ice
					
	EndIf
	
End Function
;------------------------------------------------------------------------
; Modules : Gestion de la souris Ngui
;------------------------------------------------------------------------

;----------------------
; Met à jour la Mouse
;----------------------
Function NG_UpdateMouse ()

	;-------------------------
	; précedent clic
	;-------------------------
	NG_MouseOldClic1 = NG_MouseClic1

	;----------------
	; reset des clic
	;----------------
	;NG_MouseOldClic = NG_MouseClic1
	NG_MouseClic1 = 0
	NG_MouseClic2 = 0
	NG_MouseClic3 = 0
	NG_Mouse2Clic = 0
	
	
	;---------------------------------
	; anciennes coordonnées
	;---------------------------------
	NG_MouseOldX = NG_MouseX
	NG_MouseOldY = NG_MouseY
	
	;---------------------------------
	; enregistrement des coordonnées
	;---------------------------------
	NG_MouseX = MouseX()
	NG_MouseY = MouseY()
	NG_MouseZ = MouseZ()
	
	;---------------------------
	; enregistrement des clics
	;---------------------------
	NG_MouseClic1 = MouseDown(1)
	NG_MouseClic2 = MouseDown(2)
	NG_MouseClic3 = MouseDown(3)
	
	;-------------------------------------------------------------------------------------------------------------
	; enregistrement de la position des clics et perte du TF (on le retrouvera si on clique sur une input box)
	;-------------------------------------------------------------------------------------------------------------
	If NG_MouseClic1 And NG_MouseClicX = -1
		NG_MouseClicX = NG_MouseX
		NG_MouseClicY = NG_MouseY
		NG_TF_Id = -1
	EndIf
	
	;--------------------------------------------
	; enregistrement des drag X et Y
	;--------------------------------------------
	If NG_MouseClic1
	
		NG_MouseDragX = NG_MouseX - NG_MouseOldX
		NG_MouseDragY = NG_MouseY - NG_MouseOldY
	
	Else
	
		NG_MouseDragX = 0
		NG_MouseDragY = 0
	
	EndIf
	
	
	;------------------------------------------------------------------------
	; Pour savoir si l'on relache le bouton 1 de la souris
	;------------------------------------------------------------------------
	; SuperUtile pour ne valider les choix qu'à se moment (pour se décider)
	;------------------------------------------------------------------------
	If NG_MouseOldClic1 = True And  NG_MouseClic1 = False
	
		NG_MouseUp1 = True
		
		NG_ActiveSphere = -1
				
	Else 
	
		NG_MouseUp1 = False
	
	EndIf
		
	;--------------------------------------------------------
	; gestion des positions Y du curseur en mode NG_OnValue
	;--------------------------------------------------------
	If NG_OnValueSaveRes = True And NG_Onvalue = True
	
		NG_OnValueSaveY = GraphicsHeight()
		
		NG_OnValueSaveRes = False
		
	EndIf
	
	If NG_OnValue = True
	
		If NG_MouseY = NG_OnValueSaveY - 1
		
			MoveMouse NG_MouseX , 0
			
		Else If NG_MouseY = 0
		
			MoveMouse NG_MouseX , NG_OnValueSaveY
		
		EndIf
	
	EndIf
	
	NG_OnOldValue = NG_OnValue
	
	NG_OldMenuOpenId = NG_MenuOpenId
	
	;--------------------------------------------------------
	; annulation complète du NG_OnValue
	;--------------------------------------------------------
	; et annulation du NG_MenuOpenLabel$ 
	;--------------------------------------------------------
	If NG_MouseUp1 = True
	
		NG_OnValue = False
		
		NG_OnValueSaveRes = True
		
		NG_MenuOpenLabel$ = ""
		
		NG_parent_label$ = ""
		
		NG_MenuOpenId = -1
	
	EndIf
	
	;--------------------------------------------------------
	; annulation complète du DragIcon (drag/drop d'icones)
	;--------------------------------------------------------
	If NG_MouseClic1 = False
	
		NG_DropIcon = False
	
		;-----------------------------------------------
		; valide si on a droppé une icone quelque part
		;-----------------------------------------------
		If NG_DragIcon = True
		
			NG_DropIcon = True
			
		EndIf
	
		NG_DragIcon = False
	
	EndIf
	
	
	;------------------------------------------
	; on ferme une comboBox
	;------------------------------------------
	
	NG_OldComboBoxOpen = NG_ComboBoxOpen ; garde la valeur 1 boucle de plus pour tester si on a choisi un element de la liste
	
	If NG_MouseUp1 And NG_ComboBoxOpen = True And NG_ComboBoxOnButton = False
		
		NG_ComboBoxOpen = False
		
		NG_MouseClic1 = False
		
	EndIf
	
	; reset de NG_ComboBoxOnButton
	;NG_ComboBoxOnButton = False
	
	; reset complet des combo box
	If NG_OldComboBoxOpen = False And NG_ComboBoxOpen = False
	
		NG_ComboBoxOpenID = - 1
	
	EndIf
	
	;-----------------------------------------
	; repositionnement des fenetres actives
	;-----------------------------------------
	If NG_MouseClic1 = False
	
		NG_MouseClicX = - 1
		NG_MoveWindow = False
	
	Else
		
		FlushMouse
		
	EndIf
		
	;--------------------------
	; gestion du double clic
	;--------------------------
	
	NG_2ClicTimer = NG_2ClicTimer + 1
	
	If NG_MouseUp1
		
		;----------------------------------------------------------------
		; on vérifie déjà si ce n'est pas le 2ème clic d'un double clic
		;----------------------------------------------------------------
		;If NG_FirstClicTimer > NG_2ClicTimer - NG_2ClicDelay
		If NG_2ClicTimer <= NG_2ClicDelay
		
			; si oui, c'est un double clic
			NG_Mouse2Clic = True
						
		;----------------------------------------------------------
		; sinon on enregistre le 1er clic du futur double clic
		;----------------------------------------------------------
		Else
		
			;NG_FirstClicTimer = NG_2ClicTimer ;MilliSecs()
			
			NG_2ClicTimer = 0
		
		EndIf
	
	EndIf
	
End Function

;--------------------------------------------------
; Récupère le double clic de la souris
;--------------------------------------------------
Function NG_DoubleClic ()

	Return NG_Mouse2Clic

End Function

;--------------------------------------------------
; Configure la vitesse du double clic
;--------------------------------------------------
Function NG_SetDoubleClicSpeed ( speed = 300 )

	NG_2ClicDelay = speed

End Function

;-----------------------------------------------------------------------------------
; vérifie si la souris se trouve dans les coordonnées spécifiée (avec option clic)
;-----------------------------------------------------------------------------------
Function NG_MouseZone ( Px , Py , Tx , Ty , Clic=0 )

	If NG_MouseX >= Px And NG_MouseX <= Px + Tx
		If NG_MouseY >= Py And NG_MouseY <= Py + Ty
			resultat = 1
		EndIf
	EndIf
	
		
	; option clic
	If resultat = 1 And clic > 0
		
		If NG_MouseClic1
		
			resultat = 1
		
		Else If NG_MouseClic2
		
			resultat = 2
		
		Else If NG_MouseClic3
		
			resultat = 3
		
		Else If NG_MouseUp1
	
			resultat = 99
		
		Else
		
			resultat = 0
		
		EndIf
	
	EndIf
	
	Return resultat
	
End Function

; ------------------------------------------------
; Vérifie si l'on est sur une fenetre quelconque
; ------------------------------------------------
Function NG_MouseOnWindow ()

	If NG_OnInactiveWindow = 0 And NG_OnActiveWindow = 0
	
		Return False
		
	Else
		
		Return True
		
	EndIf
	
End Function
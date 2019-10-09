;--------------------------------------------
; Modules : création et gestion des fenetres
;--------------------------------------------

;-----------------------
; crée une fenetre
;-----------------------
Function NG_CreateWindow ( Px , Py , Tx , Ty , Titre$ , Flag=0 ) ;Taille=2 , Minimize=1 , Close=1 , BackGround=1 , Movable=1 )

	NG_WindowID = NG_WindowID + 1

	NG.NG_Window = New NG_Window
	
		NG\Id = Handle (NG)
		
		NG\Display = True
	
		NG\Px = Px
		NG\Py = Py
		NG\Tx = Tx
		NG\Ty = Ty
		
		NG\Titre$ = Titre$
		
		NG\hide = False
		
		NG\skin_on = False
		
		;-----------------------
		; Cottage Icon
		;-----------------------
		NG\CottageIconOn  = NG_CottageIconOn
		NG\CottageIconOff = NG_CottageIconOff
		
		
		;-----------------------
		; réglages par défaut
		;-----------------------
		NG\Taille_Titre = 2
		NG\Background = True
		NG\Movable = True
		NG\Close = True : NG\SphereCode = NG\SphereCode + 1
		NG\Minimize = True : NG\SphereCode = NG\SphereCode + 10
		NG\ombre = False
		NG\Grad = True
		NG\Dcmin = False
		NG\TitleLine = False
		NG\Cottage = False
		NG\IncludeForCottage = True
		
		;-----------------------
		; Gestion des flags
		;-----------------------
		F = flag
		
		If F >= 2048
			F = F - 2048
			NG\Invisible = True
		EndIf
		
		If F >= 1024
			F = F - 1024
			NG\IncludeForCottage = False
		EndIf
		
		If F >= 512
			F = F - 512
			NG\SphereCode = NG\SphereCode + 100
		EndIf
		
		If F >= 256
			F = F - 256
			NG\TitleLine = True
		EndIf
		
		If F >= 128
			F = F - 128
			NG\DCmin = True
		EndIf
		
		If F >= 64
			F = F - 64
			NG\Grad = False
		EndIf
		
		If F >= 32
			F = F - 32
			NG\Background = False
		EndIf
		
		If F >= 16
			F = F - 16
			NG\Ombre = True
		EndIf
		
		If F >= 8
			F = F - 8
			NG\Taille_Titre = 1
		EndIf
		
		If F >= 4
			F = F - 4
			NG\Movable = False
		EndIf
		
		If F >= 2
			F = F - 2
			NG\Minimize = False
			NG\SphereCode = NG\SphereCode - 10
		EndIf
		
		If F >= 1
			F = F - 1
			NG\Close = False
			NG\SphereCode = NG\SphereCode - 1
		EndIf
				
	;---------------------------------------------------------------------------
	; C'est la nouvelle fenetre active (et celle au 1er plan) et pas les autres
	;---------------------------------------------------------------------------
	; sauf si elle est transparente puisqu'elle reste tjs au dernier plan
	;---------------------------------------------------------------------------
	
	If NG\BackGround = True
	
		NG_ActiveLastWindow ()
	
	EndIf
	
	;------------------------------------------
	; une skin est-elle prédéfinie ?
	;------------------------------------------
	If NG_DefaultSkin$ <> ""
	
		NG_SetWindowSkinFromHandle ( NG\Id , True , NG_DefaultSkinFile )
			
	EndIf
	
	
	;--------------------------------------
	; création d'une Cwindow
	;--------------------------------------
	NG_C.NG_Cwindow = New NG_Cwindow
	
		NG_C\WinId = NG\Id
	
	
	;------------------------------
	; Retour du Handle Utilisateur
	;------------------------------
	Return NG\Id

End Function

;---------------------------------------------
; Active (ou pas) une skin pour une fenetre
;---------------------------------------------
; si skin$="", alors la skine st désactivée
;---------------------------------------------
Function NG_SetWindowSkin ( WinId , on=False , skin$="" )

	For NG.NG_Window = Each NG_Window
	
		If NG\Id = WinId
		
			If on = True
			
				NG\skin = LoadImage ( skin$ )
				NG\skin_on = True
				NG\skin_master = True
			
			Else
			
				NG\skin_on = False
			
			EndIf
							
		EndIf
	
	Next

End Function


;------------------------------------------------------------------
; Active (ou pas) une skin pour une fenetre à partir d'un handle
;------------------------------------------------------------------
; si skin$="", alors la skine st désactivée
;------------------------------------------------------------------
Function NG_SetWindowSkinFromHandle ( WinId , on=False , skin )

	For NG.NG_Window = Each NG_Window
	
		If NG\Id = WinId
		
			If on = True
			
				NG\skin = skin
				NG\skin_on = True
				NG\skin_master = False
			
			Else
			
				NG\skin_on = False
			
			EndIf
							
		EndIf
	
	Next

End Function


;------------------------------------------------------------------
; Active (ou pas) une skin pour toutes les fenetres
;------------------------------------------------------------------
; si skin$="", alors la skine st désactivée
;------------------------------------------------------------------
Function NG_SetAllWindowSkin ( on=False , skin$ )

	For NG.NG_Window = Each NG_Window
	
		If on = True
			
			NG\skin = LoadImage ( skin$ )
			NG\skin_on = True
			NG\skin_master = True
			
		Else
			
			NG\skin_on = False
			
		EndIf
		
	Next

End Function

;-------------------------------------------------------------------------
; Active (ou pas) une skin pour toutes les fenetres à partir d'un handle
;-------------------------------------------------------------------------
; si skin$="", alors la skine st désactivée
;-------------------------------------------------------------------------
Function NG_SetAllWindowSkinFromHandle ( on=False , skin )

	For NG.NG_Window = Each NG_Window
	
		If on = True
			
			NG\skin = skin
			NG\skin_on = True
			NG\skin_master = False
			
		Else
			
			NG\skin_on = False
			
		EndIf
		
	Next

End Function


;-------------------------------------
; Prépare une fenetre pour ses Tabs
;-------------------------------------
Function NG_AddWindowIcon ( WinId , Icon , on=1 )

	For NG.NG_Window = Each NG_Window
	
		If NG\Id = WinId
		
			NG\icon_on = on
			
			NG\icon = icon
		
			If icon > 0
			
				NG\Debut_titre = ImageWidth (icon)
			
			Else
			
				NG\debut_titre = 0
			
			EndIf
										
		EndIf
	
	Next

End Function


;-------------------------------------
; Prépare une fenetre pour ses Tabs
;-------------------------------------
Function NG_WindowTab ( WinId , Px , Py , Tx , Ty , on=1 )

	For NG.NG_Window = Each NG_Window
	
		If NG\Id = WinId
		
			NG\tab_on = on
			
			NG\tab_Px = Px
			NG\tab_Py = Py
			NG\tab_Tx = Tx
			NG\tab_Ty = Ty
							
		EndIf
	
	Next

End Function

;-------------------------------------
; Prépare une fenetre pour ses Tabs
;-------------------------------------
Function NG_WindowTabOn ( WinId , on=1 )

	For NG.NG_Window = Each NG_Window
	
		If NG\Id = WinId
		
			NG\tab_on = on
													
		EndIf
	
	Next

End Function

;----------------------------------
; Règle le style des tab
;----------------------------------
Function NG_SetClassicTab ( on=1 )

	NG_ClassicTab = on

End Function


;----------------------------------
; Ajoute un tab à une fenetre
;----------------------------------
Function NG_AddTab ( WinId , Label$ , icone=0 )

	For NG.NG_Window = Each NG_Window
	
		If NG\Id = WinId

			NG\tab_max = NG\tab_max + 1
			
			NG\Tab_label$ [ NG\tab_max ] = Label$
			
			NG\tab_current = 1
			
			If icone > 0
			
				NG\tab_icone [ NG\tab_max ] = icone
			
			Else
			
				icone = NG_TabIcon
			
				NG\tab_icone [ NG\tab_max ] = icone
			
			EndIf

		EndIf
		
	Next
	
End Function

;-----------------------------------------
; supprime le dernier tab d'une fenetre
;-----------------------------------------
Function NG_DeleteTab ( WinId )

	For NG.NG_Window = Each NG_Window
	
		If NG\Id = WinId
		
			NG\Tab_label$ [ NG\tab_max ] = ""

			NG\tab_max = NG\tab_max - 1
						
		EndIf
		
	Next
	
End Function

;-------------------------------------------------
; Vérifie si la souris survole la fenetre active
;-------------------------------------------------
Function NG_CheckActiveWindow ()
	
	info = False
	
	ActiveWindow.NG_Window = Object.NG_Window( NG_ActiveWindow )
	
	
	If ActiveWindow <> Null
	
		;--------------------------------------------------------------------
		; Si c'est une fenetre transparente, on ne la remet pas au 1er plan
		;--------------------------------------------------------------------		
		;If ActiveWindow\background = False Then Return
		
		;-----------------------------
		; si elle n'est pas réduite
		;-----------------------------
		If ActiveWindow\hide = 0
		
			ZoneYcouverte = ActiveWindow\Ty
					
		Else
		
		;-----------------------------------------------------------
		; si elle est réduite, on ne prend que la barre titre
		;-----------------------------------------------------------
			ZoneYcouverte = ActiveWindow\Taille_BarreTitre
		
		EndIf

		;------------------------------------------------------------------------------
		; On vérifie si l'utilisateur à cliqué sur la zone active de la fenetre ACTIVE
		;------------------------------------------------------------------------------
		If NG_MouseZone ( ActiveWindow\Px , ActiveWindow\Py , ActiveWindow\Tx , ZoneYcouverte , 1 ) = 1
			info = True
		EndIf
		
		;------------------------------------------------------------------------------------
		; On vérifie si l'utilisateur est positionné sur la zone active de la fenetre ACTIVE
		;------------------------------------------------------------------------------------
		If NG_MouseZone ( ActiveWindow\Px , ActiveWindow\Py , ActiveWindow\Tx , ZoneYcouverte ) = 1
			If ActiveWindow\display = True
				info = True
				NG_OnActiveWindow = True
			EndIf
		EndIf
				
	EndIf
	
	Return info
	
End Function

;-----------------------------------------------------------------------
; Rend la fenêtre spécifiée active (et elle seule)
;-----------------------------------------------------------------------	
Function NG_ActiveThisWindow ( WinId )
	
	NG.NG_Window = Object.NG_Window ( WinId )

	If NG <> Null
	
		NG_ActiveWindow = NG\Id
		
		NG\Active = True
			
		NG.NG_Window = Last NG_Window
				
		; on déscative toutes les autres fenetres, puisqu'il ne doit y en avoir qu'une active
		NG_DisactiveAllWindowsBut ()
		
	EndIf

End Function

;-----------------------------------------------------------------------
; Rend la fenêtre spécifiée désactivée (et elle seule)
;-----------------------------------------------------------------------	
Function NG_DisactiveThisWindow ( WinId )
	
	NG.NG_Window = Object.NG_Window ( WinId )

	If NG <> Null
	
		NG_ActiveWindow = 0;NG\Id
			
		NG\Active = False
			
		;NG.NG_Window = Last NG_Window
			
		; on déscative toutes les autres fenetres, puisqu'il ne doit y en avoir qu'une active
		;NG_DisactiveAllWindowsBut ()

	EndIf

End Function


; ------------------------------------------------------------------
; rend la fenetre actuellement considerée comme active, non-active
; ------------------------------------------------------------------
Function NG_SetActiveWindowToNonActiveWindow ()

	ActiveWindow.NG_Window = Object.NG_Window ( NG_ActiveWindow )
	
	If ActiveWindow <> Null
	
		ActiveWindow\Active = False
	
	EndIf

End Function

;-------------------------------------------------------------
; Désactive toutes les autres fenetres sauf celle spécifiée
;-------------------------------------------------------------
Function NG_DisactiveAllWindowsBut ()

	For NG.NG_Window = Each NG_Window
	
		If NG\Id <> NG_ActiveWindow
			
			NG\Active = False
			
		Else
		
			NG\Active = True
		
		EndIf  
	
	Next

End Function


; ----------------------------------------------------------------
; N'active que la derniere fenetre affiche
; ----------------------------------------------------------------
Function NG_ActiveLastWindow ()

	; ----------------------------------------
	; Désative toute les fenetres
	; ----------------------------------------
	For NG.NG_Window = Each NG_Window
	
		NG\Active = False
	
	Next
	
	; ----------------------------------------		
	; La derniere fenetre est activée
	; ----------------------------------------
	NG.NG_Window = Last NG_Window
	NG_ActiveWindow = Handle ( NG )
	NG\Active = True

End Function

;--------------------------------------------
; détruit complétement toutes les fenetres
;--------------------------------------------
Function NG_DeleteAllWindows ()

	For NG.NG_Window = Each NG_Window
	
		If NG\Id <> NG_FS And NG\Id <> NG_CP
	
			NG_DeleteWindow ( NG\Id )
		
		EndIf
	
	Next

End Function


; ----------------------------------------------------------------------------
; détruit complétement une fenetre
; ----------------------------------------------------------------------------
Function NG_DeleteWindow ( Id )

	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null

			;---------------------------------------
			; Tous ses gadgets sont supprimés
			;---------------------------------------
		
			NG_DeleteFrame       ( NG\Id , True )
			NG_DeleteText        ( NG\Id , True )
			NG_DeleteButton      ( NG\Id , True )
			NG_DeleteInput       ( NG\Id , True )
			NG_DeleteMultiInput  ( NG\Id , True )
			NG_DeleteCycleButton ( NG\Id , True )
			NG_DeleteImageButton ( NG\Id , True )
			NG_DeleteImage       ( NG\Id , True )
			NG_DeleteCheckBox    ( NG\Id , True )
			NG_Delete3dFrame     ( NG\Id , True )
			NG_DeleteDoseur      ( NG\Id , True )
			NG_DeleteFPS         ( NG\Id , True )
			NG_DeleteCombo       ( NG\Id , True )
			NG_DeleteColor       ( NG\Id , True )
			NG_DeleteProgress    ( NG\Id , True )
			NG_DeleteValue       ( NG\Id , True )
			NG_DeleteMenu        ( NG\Id , True )
			NG_DeleteAgent       ( NG\Id , True )

			;-----------------------
			; si il y a une skin
			;-----------------------
			If NG\skin_on And NG\skin_master = True
				FreeImage NG\skin
			EndIf
			
			;--------------------------------------
			; destruction d'une Cwindow
			;--------------------------------------
			For NG_C.NG_Cwindow = Each NG_Cwindow
			
				If NG_C\WinId = NG\Id
			
					Delete NG_C
				
				EndIf
			
			Next
			
			;------------------------------------
			; fenetres preview
			;------------------------------------
			If NG\media$ = "son"
			
				FreeSound NG\sound
			
			Else If NG\media$ = "video"
			
				CloseMovie NG\video
				
			Else If NG\media$ = "3d"
			
				FreeEntity NG\pivot
				FreeEntity NG\light
				FreeEntity NG\model		
			
			EndIf
			
					
			;---------------------------------
			; Et enfin.... paix à son âme...
			;---------------------------------
			Delete NG
		
	EndIf
	
End Function

;---------------------------------------
; Fonctions Utitlisateur à propos des
;       FENETRES et de la SOURIS
;---------------------------------------

; --------------------------------------------------
; Pour vérifier si une fenetre est réduite ou non
; --------------------------------------------------
; 0=visible , 1=minimisée
;---------------------------------------------------
Function NG_ReturnWindowMinimize ( Id )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null
	
		Return NG\hide
		
	EndIf
	
End Function


; --------------------------------------------------
; renvoie la position X de la fenetre
; --------------------------------------------------
Function NG_ReturnWindowPositionX ( Id )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null
	
		Return NG\Px
		
	EndIf
	
End Function


; --------------------------------------------------
; renvoie la position Y de la fenetre
; --------------------------------------------------
Function NG_ReturnWindowPositionY ( Id )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null
	
		Return NG\Py
		
	EndIf
	
End Function


; --------------------------------------------------
; Pour vérifier si une fenetre est fermée ou non
; --------------------------------------------------
; 1=ouverte , 0=fermée (mais pas détruite !!)
;---------------------------------------------------
Function NG_ReturnWindowOpen ( Id )

	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null
		
		Return NG\Display
		
	EndIf
	
End Function

; --------------------------------------------------
; Pour vérifier si une fenetre est active ou non
; --------------------------------------------------
; 1=active , 0=inactive
;---------------------------------------------------
Function NG_ReturnWindowActive ( Id )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null
		
		Return NG\Active
		
	EndIf
	
End Function

; --------------------------------------------------
; Pour obtenir le nom du tab de la fenetre
; --------------------------------------------------
Function NG_ReturnWindowTab$ ( Id )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null

			
		If NG\Tab_on = False Then Return -1
			
		Return NG\Tab_Label$ [NG\Tab_current]
		
	EndIf
	
End Function


;--------------------------------------------
; change l'icone Cottage d'une fenetre 
;--------------------------------------------
Function NG_SetWindowCottageIcons ( Id , CottageIconOn , CottageIconOff ) 

	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null

		NG\CottageIconOn = CottageIconOn
		NG\CottageIconOff = CottageIconOff
	
	EndIf

End Function


;------------------------------------------------------------
; change le mode cottage d'une fenetre 
;------------------------------------------------------------
; false (fenetre ouverte), true (reduite dans le cottage)
;------------------------------------------------------------
Function NG_SetWindowCottage ( Id , Cottage=True ) 

	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null

		NG\Cottage = Cottage
		
	EndIf

End Function


; --------------------------------------------------
; Pour placer le tab sur ...
; --------------------------------------------------
Function NG_SetWindowTab$ ( Id , Label$ )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null
			
		If NG\Tab_on = False Then Return -1
			
		For i = 1 To NG\tab_max
		
			If NG\Tab_Label$ [ i ] = Label$
			
				NG\Tab_current = i
			
			EndIf
		
		Next
		
	EndIf
	
End Function

; -------------------------------------------------------------------
; Pour changer le mode dégradé de toutes les fenetres
; -------------------------------------------------------------------
Function NG_SetAllWindowsGradient ( on=True )
	
	For NG.NG_Window = Each NG_Window
	
		NG\Grad = on
	
	Next 
	
End Function


; -------------------------------------------------------------------
; Pour changer le titre d'une fenetre (et accessoirement sa taille)
; -------------------------------------------------------------------
Function NG_SetWindowTitle ( Id , Titre$ , taille=2 )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null
		
		NG\Titre$ = titre$
			
		NG\taille_titre = taille
		
	EndIf
	
End Function


; ------------------------------------------
; Pour changer la position d'une fenetre
; ------------------------------------------
Function NG_SetWindowPosition ( Id , Px=-1 , Py=-1 )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null

			
		If NG\Px <> - 1 Then NG\Px = Px			
		If NG\Py <> - 1 NG\Py = Py
		
	EndIf
	
End Function

; ------------------------------------------
; Pour changer la taille d'une fenetre
; ------------------------------------------
Function NG_SetWindowSize ( Id , Tx=-1 , Ty=-1 )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null

		If NG\Tx <> - 1 NG\Tx = Tx			
		If NG\Ty <> - 1 NG\Ty = Ty
		
	EndIf
	
End Function


; ------------------------------------------
; Pour changer le mode dégradé des fenetres
; ------------------------------------------
Function NG_SetWindowGradient ( Id , on=True )
	
	NG.NG_Window = Object.NG_Window(Id)
	
	If NG <> Null

		NG\Grad = on
		
	EndIf
	
End Function

;--------------------------------------------------------------------------
; change le mode movable d'une fenetre
;--------------------------------------------------------------------------
Function NG_SetWindowMovable ( WinId , on=True )

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null

		NG\Movable = on
		
	EndIf
	
End Function


;--------------------------------------------------------------------------
; change le point de depart (origine) pour dessiner les gadgets
;--------------------------------------------------------------------------
Function NG_SetWindowOrigin ( WinId , xs=0 , ys=0 )

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null

		NG\origine_x = xs
		NG\origine_y = ys
		
	EndIf
	
End Function

;-----------------------------------------------------
; Switche une fenetre en Diplay=True / Display=False
;-----------------------------------------------------
Function NG_SwitchWindowDisplayMode ( WinId )

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null
	
		NG\Display = 1 - NG\Display
		
		NG_ResetAllButtons ( NG\Id )
		
		NG_ActiveThisWindow ( NG\Id )
		
		;------------------------------------
		; fenetres preview
		;------------------------------------
		If NG\media$ <> ""
		
			NG\Killme = True			
		
		EndIf
		
	
	EndIf

End Function

;-----------------------------------------------------
; Ouvre une fenetre qui était en Display=False
;-----------------------------------------------------
Function NG_OpenWindow ( WinId )

	NG.NG_Window = Object.NG_Window(WinId)
	
	If NG <> Null

		NG\Display = True
			
		; au cas ou (bug)
		;--------------------
		NG_ActiveWindow = WinId
		NG\Active = True			
		NG.NG_Window = Last NG_Window
		
		NG_SetActiveWindowToNonActiveWindow ()
			
		NG_ActiveThisWindow ( NG\Id )
		
		NG_AlwaysOnTop ( WinId )
		
		;--------------------------------------
		; maj d'une Cwindow
		;--------------------------------------
		For NG_C.NG_Cwindow = Each NG_Cwindow
		
			If NG_C\WinId = NG\Id
			
				NG_C = Last NG_Cwindow
				
			EndIf
			
		Next
		
			
	EndIf
	
End Function

;-----------------------------------------------------
; Ferme une fenetre qui était en Display=True
;-----------------------------------------------------
Function NG_CloseWindow ( WinId )

	NG.NG_Window = Object.NG_Window(WinId)
	
	If NG <> Null

		NG\Display = False
			
		NG_ResetAllButtons ( WinId )
		
		NG_DisactiveThisWindow ( WinId )
		
		;------------------------------------
		; fenetres preview
		;------------------------------------
		If NG\media$ <> ""
		
			NG\Killme = True		
		
		EndIf
						
	EndIf
	
End Function

;-----------------------------------------------------
; agrandit une fenetre qui était en Hide=True
;-----------------------------------------------------
Function NG_MaximizeWindow ( WinId )

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null
	
		NG\hide = 0
		
		NG_ActiveThisWindow ( NG\Id )
	
	EndIf

End Function

;-----------------------------------------------------
; réduit une fenetre qui était en Hide=0
;-----------------------------------------------------
Function NG_MinimizeWindow ( WinId )

	If NG_ComboBoxOpenID > -1 Then Return

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null
	
		NG\hide = 1
		
		NG_ResetAllButtons ( Handle( NG ) )
	
	EndIf

End Function

;------------------------------------------------------------
; Switche une fenetre en Hide=True / Hide=False (réduction)
;------------------------------------------------------------
Function NG_SwitchWindowMinimizeMode ( WinId )

	If NG_ComboBoxOpenID > -1 Then Return

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null
	
		NG\hide = 1 - NG\hide
		
		NG_ResetAllButtons ( NG\Id )
		
		NG_ActiveThisWindow ( NG\Id )
	
	EndIf

End Function

;--------------------------------------------------------
; Ferme toutes les fenetres (mais ne les détruit pas !)
;--------------------------------------------------------
Function NG_CloseAllWindows ()

	For NG.NG_Window = Each NG_Window
	
		If NG\Id <> NG_FS And NG\Id <> NG_CP

	
			NG\Display = False
			
			NG_ResetAllButtons ( Handle( NG ) )
		
		EndIf
			
	Next

End Function

;--------------------------------------------------------
; Ouvre toutes les fenetres
;--------------------------------------------------------
Function NG_OpenAllWindows ()

	For NG.NG_Window = Each NG_Window
	
		If NG\Id <> NG_FS And NG\Id <> NG_CP

	
			NG\Display = True
			
			; au cas ou (bug)
			;--------------------
			NG_ActiveWindow = Handle( NG )
			NG\Active = True			
			NG.NG_Window = Last NG_Window
			
			NG_ActiveThisWindow ( NG\Id )
			
			;--------------------------------------
			; maj d'une Cwindow
			;--------------------------------------
			For NG_C.NG_Cwindow = Each NG_Cwindow
			
				If NG_C\WinId = NG\Id
				
					NG_C = Last NG_CWindow
					
				EndIf
				
			Next
			
		EndIf
			
	Next

End Function

;--------------------------------------------------------
; Réduit toutes les fenetres
;--------------------------------------------------------
Function NG_MinimizeAllWindows ()

	If NG_ComboBoxOpenID > -1 Then Return

	For NG.NG_Window = Each NG_Window
	
		If NG\Id <> NG_FS And NG\Id <> NG_CP

			NG\hide = True
		
			NG_ResetAllButtons ( Handle( NG ) )
			
		EndIf
			
	Next

End Function

;--------------------------------------------------------
; agrandit toutes les fenetres
;--------------------------------------------------------
Function NG_MaximizeAllWindows ()

	For NG.NG_Window = Each NG_Window
	
		If NG\Id <> NG_FS And NG\Id <> NG_CP

			NG\hide = False
			
			NG_ActiveThisWindow ( NG\Id )
			
		EndIf
			
	Next

End Function


;-------------------------------------------------------------
; place la fenetre spécifiée au 1er plan
;-------------------------------------------------------------
Function NG_AlwaysOnTop ( WinId )

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null
	
		Insert NG After Last NG_Window ;NG.NG_Window = Last NG_Window
		
		NG_SetActiveWindowToNonActiveWindow ()
		
		NG_ActiveThisWindow ( NG\Id )
		
	EndIf

End Function

;-------------------------------------------------------------------------------------
; place la fenetre spécifiée au dernier plan (utile pour les fenetre transparentes)
;-------------------------------------------------------------------------------------
Function NG_AlwaysOnBack ( WinId )

	NG.NG_Window = Object.NG_Window ( WinId )
	
	If NG <> Null
	
		Insert NG After First NG_Window
		
		NG_ActiveThisWindow ( NG\Id )
	
	EndIf

End Function
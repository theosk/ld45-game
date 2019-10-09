;------------------------------------------------------------------------
; Modules : Création et Mise à jour de l'interface graphique
;------------------------------------------------------------------------

;-----------------------------------
; Met à jour toute la Ngui
;-----------------------------------
Function NG_Update ()

	;------------------------
	; Cygne 1ere étape
	;------------------------
	NG_DrawCygneFirst ()

	;----------------------
	; Gestion de la souris
	;----------------------
	NG_UpdateMouse ()
	
	;----------------------------------
	; Gestion du Focus des input texts
	;----------------------------------
	NG_UpdateTF ()
	
	;------------------------
	; Mises à jour
	;------------------------
	NG_UpdateWindow ()
	
	;------------------------
	; Affichage de la Ngui
	;------------------------
	NG_DrawNgui ()
	
	;------------------------
	; Cygne dernière étape
	;------------------------
	NG_DrawCygneLast ()
	
End Function


;-------------------------
; Affiche Ngui à l'écran
;-------------------------
Function NG_DrawNgui ()

	NG_DrawWindow ()
	
	;-----------------------
	; FX : Effets spéciaux
	;-----------------------
	NG_DrawCloseFX ()
	
	;-------------------------------------------------------
	; dessine les éléments supplémentaires du color picker
	;-------------------------------------------------------
	NG_DrawColorPickerElements ()
	
	;----------------------------
	; Cottage
	;----------------------------
	NG_DrawCottage ()
	
	;-------------------
	; en dernier !
	;-------------------
	If NG_CygneOn = False
	
		NG_DrawMouse ()
	
	EndIf
	
End Function

;---------------------------------------------------------
; dessine les CloseFX
;---------------------------------------------------------
Function NG_DrawCloseFX ()

	For FX.NG_CloseFX = Each NG_CloseFX
	
		Color 255 , 255 , 255
		
		Rect FX\Px , FX\Py , FX\Tx , FX\Ty , False
		
		inc = 30
		
		FX\Px = FX\Px + inc
		FX\Py = FX\Py + inc 
		FX\Tx = FX\Tx - (inc * 2)
		FX\Ty = FX\TY - (inc * 2)
		
		;---------------------------------
		; destruction du CloseFX
		;---------------------------------
		If FX\Tx < 1 	
			
			Delete FX
		
		EndIf
	
	
	Next

End Function


;---------------------------------------------------------
; Dessine le Cottage
;---------------------------------------------------------
Function NG_DrawCottage ()

	If NG_CottageOn = False Or NG_CottageLoadOn = False Or NG_InfoWindow = True Then Return

	;---------------------------------------------
	; combien de fenetres ouvertes ?
	;---------------------------------------------
	count = 0
	
	For NG.NG_Window = Each NG_Window
	
		If NG\display = True
		
			If NG\IncludeForCottage = True

				count = count + 1
			
				;------------------------------------------------------------------------------------
				; Annulation si la fenetre est transparente et que l'on empeche sa prise en compte	
				;------------------------------------------------------------------------------------	
				If NG_DisplayTransparentWindowsForCottage = False And NG\Background = False
					
					count = count - 1 ; annulation
					
				EndIf

			EndIf

		EndIf
	
	Next
	
	;-----------------------------------------------------------
	; pas de fenetre : pas de cottage
	;-----------------------------------------------------------
	If count = 0 Then Return
	
	;--------------------------------------------
	; dessin du cottage et de ses icones
	;--------------------------------------------
	center = GraphicsWidth() / 2
	
	X = Center - NG_WidthS
	X = X - ( ( NG_WidthM / 2 ) * count )
	
	StartX = X
	
	;--------------------------------------------
	; dessin
	;--------------------------------------------
	DrawImage NG_CottageS , X , NG_CottagePy
	
	X = X + NG_WidthS
	
	SetFont NG_CottageFont
	
	
	;------------------------------------------------------------------------------------------
	; on recherche les fenetres dans leur ordre de création, grace au Cwindows...
	;------------------------------------------------------------------------------------------
	
	For NG_C.NG_Cwindow = Each NG_Cwindow
	
	
		NG.NG_Window = Object.NG_Window ( NG_C\WinId )
		
		If NG <> Null
		
			;------------------------------------------------------------------------------------
			; Annulation si la fenetre est transparente et que l'on empeche sa prise en compte	
			;------------------------------------------------------------------------------------	
			If NG_DisplayTransparentWindowsForCottage = False
			
				If NG\Background = False
				
					go = False
				
				Else
				
					go = True
				
				EndIf
			
			Else
			
				go = True 
				
			EndIf	
				
			;------------------------------------------------------------------------------------
			; si la fenetres est ouverte et si elle autorise le cottage à la prendre en compte
			;------------------------------------------------------------------------------------
			If NG\display = True
			
				If NG\IncludeForCottage = True
				
					If go = True
		
						DrawImage NG_CottageM , X , NG_CottagePy
						
						;---------------------------------
						; Est-ce la fenetre active ?
						;---------------------------------
						If NG_ActiveWindow = Handle (NG)
							
							NG\CottageIconShake = NG\CottageIconShake + 10
								
							plus = Sin ( NG\CottageIconShake ) * 10
							
						Else
							
							NG\CottageIconShake = 0
							
							plus = 0
						
						EndIf
						
						;-------------------------------------------------------------------
						; survole t-on la zone de cette icone ? si oui, le selecteur svp !
						;-------------------------------------------------------------------
						If NG_MouseZone ( X , NG_CottagePy , NG_WidthM , NG_HeightM )
						
							DrawImage NG_CottageSelect , X , NG_CottagePy 
												
						EndIf
			

					
						;-------------------------------
						; icone de la fenetre
						;-------------------------------
						If NG\Cottage = False
						
							DrawImage NG\CottageIconOn , X + 2.5 , NG_CottagePy + 4 + plus
						
						Else If NG\Cottage = True
						
							DrawImage NG\CottageIconOff , X + 2.5 , NG_CottagePy + 4 + 10
						
						EndIf
						
						;-------------------------------------------------
						; clique t-on sur cette icone ?
						;-------------------------------------------------
						If NG_OnCottage = True
						
							If NG_MouseZone ( X , NG_CottagePy , NG_WidthM , NG_HeightM , True ) = 99
			
								;End
								
								NG\Cottage = False
								
								Insert NG After Last NG_Window
												
								;--------------------------------------
								; On la configure comme fenetre active
								;--------------------------------------
								NG_SetActiveWindowToNonActiveWindow ()
												
								NG_ActiveWindow = Handle( NG )
												
								NG\Active = True
												
								NG_ActiveWindowOn = True
							
							EndIf
						
						EndIf
						
						
						;--------------------------------
						; texte de la fenetre
						;--------------------------------
						t = 14
						If Len ( NG\Titre$ ) > t
						
							titre$ = Left$ ( NG\Titre$ , t ) + "..."
						
						Else
						
							titre$ = NG\Titre$
						
						EndIf
						
						Xref = X + ( NG_WidthM / 2 )
						
						Yref = NG_CottagePy - (FontHeight () * 3) + NG_HeightM
						
						long = StringWidth(titre$) / 2
						
						Xref = Xref - long
						
						Color NG_Cottage_R , NG_Cottage_G , NG_Cottage_B
									
						NG_DisplayText ( Titre$ , Xref , Yref , 100 )
					
						X = X + NG_WidthM
					
					EndIf
													
				EndIf
			
			EndIf
				
		EndIf
	
	Next
	
	DrawImage NG_CottageE , X , NG_CottagePy
	
	EndX = X
	
	LongX = EndX - StartX
	
	;--------------------------------------------------------
	; verification : la souris est-elle sur le cottage ?
	;--------------------------------------------------------
	If NG_MouseZone ( StartX , NG_CottagePy , LongX , NG_HeightM )
	
		NG_OnCottage = True
		
	Else
	
		NG_OnCottage = False
	
	EndIf
	
	
End Function


;---------------------------------------------------------
; dessine les éléments supplémentaires du color picker
;---------------------------------------------------------
Function NG_DrawColorPickerElements ()

	If NG_ReturnWindowOpen ( NG_CP )
	
		Xref = NG_ReturnWindowPositionX ( NG_CP )
		Yref = NG_ReturnWindowPositionY ( NG_CP )
		
		Color NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB
		
		Rect Xref + 440 - 1 , Yref + 100 - 1 , 52 , 52 , True
		
		Xzone = Xref + 10
		Yzone = Yref + 50
		
		
		mx = NG_CP_MX
		my = NG_CP_MY
				
		If NG_CP_MX < 0 Then mx = 0
		If NG_CP_MX > 255 Then mx = 255
		
		If NG_CP_MY < 0 Then my = 0
		If NG_CP_MY > 255 Then my = 255
		
		
		posX = mx + Xzone
		posY = my + Yzone
		
		GetColor PosX , PosY
		
		Color ColorRed() , ColorGreen() , ColorBlue()
				
		Rect Xref + 440 , Yref + 100 , 50 , 50
	
	EndIf
	
End Function


;-------------------------
; Met à jour une fenetre
;-------------------------
Function NG_UpdateWindow ()

	;---------------------------------
	; Reset
	;---------------------------------
	NG_OnInactiveWindow = False
	NG_OnActiveWindow   = False
	
	NG_OnIconViewer = False
	
	NG_ComboBoxOnButton = False
	
	NG_OldOnGadget = NG_OnGadget
	
	NG_SelectedMenu$ = ""

	NG_HelpLabel$ = ""

	;-------------------------------------------------------
	; place sur le bon pointer du type de la fenetre active
	;-------------------------------------------------------
	NG_ActiveWindowOn = NG_CheckActiveWindow ()
	
	NG.NG_Window = Last NG_Window
		
	; ----------------------------
	; Maj de chaque fenetre
	; ----------------------------
	While NG <> Null
	
			;-------------------------------------------------------------------------------------
			; On ne s'occupe de cette fenetre que si elle est affcihée
			;-------------------------------------------------------------------------------------
			
			If NG\Display = True
			
				;-----------------------------------------------
				; Quelle est la zone mouvante de la fenetre ?
				;-----------------------------------------------
				
				;-----------------------------
				; si elle n'est pas réduite
				;-----------------------------
				If NG\hide = 0
				
					ZoneYcouverte = NG\Ty
							
				Else
				
				;-----------------------------------------------------------
				; si elle est réduite, on ne prend que la barre titre
				;-----------------------------------------------------------
					ZoneYcouverte = NG\Taille_BarreTitre
				
				EndIf
				
				; ------------------------------------------------------------------------------------
				; L'utilisateur a t-il positionné sa souris dans la zone en cliquant sur le bouton 1
				;  -----------------------------------------------------------------------------------
				If NG\display = True
					NG_OnInactiveWindow = NG_MouseZone ( NG\Px , NG\Py , NG\Tx , ZoneYcouverte )
				EndIf
				
				bouton = NG_MouseZone ( NG\Px , NG\Py , NG\Tx , ZoneYcouverte , 1 )
					
				If  bouton = 1
								
					;-----------------------------------------------------------------------------------
					; Le mode de capture ne se fait que si c'est un tout nouveau clic (et pas un hold)
					;-----------------------------------------------------------------------------------
					If NG_MouseOldClic1 = False And NG_MouseClic1 = True And NG_OnGadget = False And NG_OnCottage = False

						;--------------------------------------------------------------------------------------
						; on ne permet le changement de fenetre que si il n'y a pas de fenetre info affichée
						;--------------------------------------------------------------------------------------
						If NG_InfoWindow = False Or NG\FileSelectorPermission = True
					
							;--------------------------------------------------------------
							; On vérifie que l'on ne re-clique pas sur la fenetre active
							;--------------------------------------------------------------
							If NG_ActiveWindowOn = False
								
								;---------------------------------------------------
								; Notre nouvelle fenetre active passe au 1er plan !
								;---------------------------------------------------
								If NG\BackGround = True
								
									Insert NG After Last NG_Window
									
									;--------------------------------------
									; On la configure comme fenetre active
									;--------------------------------------
									NG_SetActiveWindowToNonActiveWindow ()
									
									NG_ActiveWindow = Handle( NG )
									
									NG\Active = True
									
									NG_ActiveWindowOn = True
								
								EndIf
						
							EndIf
							
						EndIf
					
					EndIf
				
				EndIf
	
				; -------------------------------------------------------------------
				; On click sur la barre de titre de la fenetre Active  ?
				; -------------------------------------------------------------------
				If NG_MouseZone ( NG\Px , NG\Py , NG\Tx , ZoneYcouverte , 1 ) = 1 And NG\Active = True
	
					;-----------------------------------------------------------------------------------
					; Le mode de capture ne se fait que si c'est un tout nouveau clic (et pas un hold)
					;-----------------------------------------------------------------------------------
					
					;--------------------------------------------------------------
					; Attention : si bugs remettre : If NG_MouseOldClic1 = false
					;--------------------------------------------------------------
					
					If NG_MouseOldClic1 = True And NG_MouseClic1 = True And NG_OnGadget = False And NG_ComboBoxOpenID = - 1 And NG_OnCottage = False
					
						If NG\Movable = True
	
							;-----------------------
							; Mode déplacement SVP
							;-----------------------
							;If NG\BackGround = 0 Then NG_AlwaysOnBack ( NG\Id )						
							
							NG_MoveWindow = True
						
						EndIf
																		
					EndIf
						
					;---------------------------------------------------------------------
					; Si ce n'est pas la fenetre active, alors on la descative en config
					;---------------------------------------------------------------------
				
					If NG_ActiveWindow <> Handle( NG ); And NG\background = False
					
						;NG\Active = False
					
					EndIf			
					
				
				EndIf
				
			EndIf
			
		;---------------------------------------------------------------------
		; Pratique pour selectionner dans l'ordre les fenetres du 1er plan
		;---------------------------------------------------------------------
		NG = Before NG
			
	Wend
	
End Function

;-----------------------
; Dessine une fenetre
;-----------------------
Function NG_DrawWindow ( )
	
	;--------------------------------------
	; On doit redessiner chaque fenetre
	;--------------------------------------
	
	;----------------------------------------
	; On suppose qu'on est sur aucun gadget
	;----------------------------------------
	; Ce n'est le cas que si le bouton 1 de
	; la souris n'est pas enfoncé... 
	;----------------------------------------
	If NG_MouseClic1 = False
		
		NG_OnGadget = False
		NG_OnSpecialGadget = False
		NG_OnDoseur = False
		NG_OnSlider = False
	
	EndIf
	
	For NG.NG_Window = Each NG_Window
	
		;-------------------------------------------------------------------------------------
		; On ne s'occupe de cette fenetre que si elle est affcihée
		;-------------------------------------------------------------------------------------
		
		If NG\Display = True And NG\Cottage = False
	
			;-----------------------------------------------------
			; vérifie d'abord si la fenetre est minimisée ou non
			;-----------------------------------------------------
			If NG\hide = 0 ; non
			
				WinTy = NG\Ty
				
				;-------------------------------------
				; s'il y a une skin FreeForm Organic
				;-------------------------------------
				If NG\organic Then WinTy = NG\organic_Oty
			
			Else
			
				WinTy = NG\Taille_BarreTitre
				
				;-------------------------------------
				; s'il y a une skin FreeForm Organic
				;-------------------------------------
				If NG\organic Then WinTy = NG\organic_Rty
			
			EndIf
			
			;-------------------------------------------------------------------------------
			; On ne dessine la fenetre que si son NG\BackGround = 1
			;-------------------------------------------------------------------------------
			If NG\BackGround And NG\Invisible = False
			
				;-----------------------------------------------
				; degradé : on démarre des valeurs par défaut
				;-----------------------------------------------
				
				; viewport pour le fond de le la fenetre
				;-----------------------------------------
				Viewport NG\Px+1 , NG\Py+1 , NG\Tx-2 , WinTy-2
												
				
				;--------------------------------------------
				; On dessine le fond s'il n'y a pas de skin
				;--------------------------------------------
				
				If NG\skin_on = False And NG\organic = False
				
					;--------------------------------------
					; Si l'option dégradé est activée
					;--------------------------------------
					If NG\Grad = True
				
						;si la fenetre est active
						If NG\Active = True
						
							red_start#   = NG_red_s   : red_end#      = NG_red_e
							green_start# = NG_green_s : green_end#    = NG_green_e
							blue_start#  = NG_blue_s  : blue_end#     = NG_blue_e
											
						; si elle est inactive
						Else
				
							red_start#   = NG_red_sI   : red_end#    = NG_red_eI
							green_start# = NG_green_sI : green_end#  = NG_green_eI
							blue_start#  = NG_blue_sI  : blue_end#   = NG_blue_eI
						
						EndIf
				
				
						; création du dégradé
						Dir = 1
						
						GradientRed#   = ( Red_end - Red_start ) / WinTy
						GradientGreen# = ( Green_end - Green_start ) / WinTy
						GradientBlue#  = ( Blue_end - Blue_start ) / WinTy
										
						For Y = NG\Py To ( NG\Py + WinTy ) 
						
							Color Red_start , Green_start , Blue_start
							
							Rect NG\Px+1 , Y , NG\Tx - 2 , 1
							
							Red_start = Red_start + GradientRed
							Green_start = Green_start + GradientGreen
							Blue_start = Blue_start + GradientBlue
							
						Next
					
					;----------------------------------------------
					; Si elle ne l'est pas
					;----------------------------------------------
					Else If NG\Grad = False
					
						If NG\Active
					
							NG_red_M   = ( (NG_red_s + NG_red_e) / 2 )
							NG_green_M = ( (NG_green_s + NG_green_e) / 2 )
							NG_blue_M  = ( (NG_blue_s + NG_blue_e) / 2 )
					
						Else
						
							NG_red_M   = ( (NG_red_s + NG_red_e) / 3 )
							NG_green_M = ( (NG_green_s + NG_green_e) / 3 )
							NG_blue_M  = ( (NG_blue_s + NG_blue_e) / 3 )
						
						EndIf
										
						Color NG_red_M , NG_green_M , NG_blue_M
						
						Rect NG\Px+1 , NG\Py , NG\Tx - 2 , WinTy , True
					
					EndIf
			
				;----------------------------------------------------
				; sinon, on dessine la skin
				;----------------------------------------------------
				Else If NG\skin_on = True And NG\organic = False
				
					TileBlock NG\skin , NG\Px , NG\Py
				
				;----------------------------------------------------
				; Ou encore, on dessine la skin Freeform organic
				;----------------------------------------------------
				Else If NG\organic = True
				
					If NG\hide = 0
									
						NG_SetWindowSize ( NG\Id , NG\organic_Otx , NG\organic_Oty )
						
						If NG_ReturnWindowActive ( NG\Id )
						
							DrawImage NG\organic_OA , NG\Px , NG\Py
						
						Else
						
							DrawImage NG\organic_OI , NG\Px , NG\Py
						
						EndIf
					
					Else
					
						NG_SetWindowSize ( NG\Id , NG\organic_Rtx , NG\organic_Rty )
						
						If NG_ReturnWindowActive ( NG\Id )
						
							DrawImage NG\organic_RA , NG\Px , NG\Py
						
						Else
						
							DrawImage NG\organic_RI , NG\Px , NG\Py
						
						EndIf
					
					EndIf
			
					
				EndIf
				
				
				;------------------------------------
				; Doit-on dessiner la Title Line ?
				;------------------------------------
				If NG\TitleLine = True And NG\organic = False
				
					;--------------------------------------
					; Si l'option dégradé est activée
					;--------------------------------------
					If NG\Grad = True
				
						;----------------------
						; deux tiers
						;----------------------
						deux_tiers = ( NG\Taille_BarreTitre / 3 ) * 2
						
						;----------------------
						; trois tiers
						;----------------------
						trois_tiers = NG\Taille_BarreTitre
						
						;----------------------------
						; de 0 à 2 tiers
						;----------------------------
						
							;--------------------------
							;si la fenetre est active
							;--------------------------
							If NG\Active = True
									
								red_start#   = NG_red_s   : red_end#      = NG_red_e
								green_start# = NG_green_s : green_end#    = NG_green_e
								blue_start#  = NG_blue_s  : blue_end#     = NG_blue_e
							
							;------------------------							
							; si elle est inactive
							;------------------------
							Else
							
								red_start#   = NG_red_sI   : red_end#    = NG_red_eI
								green_start# = NG_green_sI : green_end#  = NG_green_eI
								blue_start#  = NG_blue_sI  : blue_end#   = NG_blue_eI
									
							EndIf
							
							;-----------------------
							; création du dégradé
							;-----------------------
							Dir = 1
									
							GradientRed#   = ( Red_end - Red_start ) / deux_tiers
							GradientGreen# = ( Green_end - Green_start ) / deux_tiers
							GradientBlue#  = ( Blue_end - Blue_start ) / deux_tiers
													
							For Y = NG\Py To ( NG\Py + deux_tiers ) 
									
								Color Red_start , Green_start , Blue_start
										
								Rect NG\Px+1 , Y , NG\Tx - 2 , 1
										
								Red_start = Red_start + GradientRed
								Green_start = Green_start + GradientGreen
								Blue_start = Blue_start + GradientBlue
										
							Next
						
						;----------------------------
						; de 2 à 3 tiers
						;----------------------------
						
							;--------------------------
							;si la fenetre est active
							;--------------------------
							If NG\Active = True
									
								red_start#   = NG_red_e   : red_end#      = NG_red_s
								green_start# = NG_green_e : green_end#    = NG_green_s
								blue_start#  = NG_blue_e  : blue_end#     = NG_blue_s
							
							;------------------------							
							; si elle est inactive
							;------------------------
							Else
							
								red_start#   = NG_red_eI   : red_end#    = NG_red_sI
								green_start# = NG_green_eI : green_end#  = NG_green_sI
								blue_start#  = NG_blue_eI  : blue_end#   = NG_blue_sI
									
							EndIf
							
							;-----------------------
							; création du dégradé
							;-----------------------
							Dir = 1
									
							GradientRed#   = ( Red_end - Red_start ) / trois_tiers
							GradientGreen# = ( Green_end - Green_start ) / trois_tiers
							GradientBlue#  = ( Blue_end - Blue_start ) / trois_tiers
													
							For Y = ( NG\Py + deux_tiers ) To ( NG\Py + trois_tiers )
									
								Color Red_start , Green_start , Blue_start
										
								Rect NG\Px+1 , Y , NG\Tx - 2 , True
										
								Red_start = Red_start + GradientRed
								Green_start = Green_start + GradientGreen
								Blue_start = Blue_start + GradientBlue
										
							Next
						
					;----------------------------------------------
					; Si elle ne l'est pas
					;----------------------------------------------
					Else If NG\Grad = False
					
						inc = 20
					
						If NG\Active
										
							NG_red_M   = ( (NG_red_s + NG_red_e) / 2 ) + inc
							NG_green_M = ( (NG_green_s + NG_green_e) / 2 )  + inc
							NG_blue_M  = ( (NG_blue_s + NG_blue_e) / 2 ) + inc
							
							If NG_red_M > 255 Then NG_red_M = 255
							If NG_green_M > 255 Then NG_green_M = 255
							If NG_blue_M > 255 Then NG_blue_M = 255
					
						Else
						
							NG_red_M   = ( (NG_red_s + NG_red_e) / 3 ) + inc
							NG_green_M = ( (NG_green_s + NG_green_e) / 3 ) + inc
							NG_blue_M  = ( (NG_blue_s + NG_blue_e) / 3 ) + inc
							
							If NG_red_M > 255 Then NG_red_M = 255
							If NG_green_M > 255 Then NG_green_M = 255
							If NG_blue_M > 255 Then NG_blue_M = 255
						
						EndIf
										
						Color NG_red_M , NG_green_M , NG_blue_M
						
						Rect NG\Px+1 , NG\Py , NG\Tx - 2 , NG\Taille_BarreTitre , True
						
						trois_tiers = NG\Taille_BarreTitre
					
					EndIf
					
					;--------------------------------
					; ligne de séparation
					;--------------------------------
					If NG\Active = True
					
						r = NG_red_s - 100 : If r < 0 Then r = 0
						g = NG_red_g - 100 : If g < 0 Then g = 0
						b = NG_red_b - 100 : If b < 0 Then b = 0
					
						Color r , g , b
					
					Else
					
						r = NG_red_sI - 100 : If r < 0 Then r = 0
						g = NG_red_gI - 100 : If g < 0 Then g = 0
						b = NG_red_bI - 100 : If b < 0 Then b = 0
					
						Color r , g , b
					
					EndIf
					
					Rect NG\Px+1 , NG\Py + trois_tiers + 1 , NG\Tx - 2 , 1


								
				EndIf
								
				;-------------
				; bordure
				;-------------
				If NG\Organic = False
				
					;-----------------------------------------
					; viewport pour la bordure
					;-----------------------------------------
					Viewport NG\Px , NG\Py , NG\Tx , NG\Ty
				
					NG_DrawBordure ( NG\Px , NG\Py , NG\Tx , WinTy , NG_Bord_r , NG_Bord_g , NG_Bord_b )
				
				EndIf
					
			EndIf
			
			
			;--------------------------------------
			; limite X des titres des fenetres
			;--------------------------------------
			limitX = 5								
									
			;-----------------------
			; Boutons Spheres
			;-----------------------
			debut = 22
			
			;---------------------------------------
			; y a t-il au moins une des 3 spheres ?
			;---------------------------------------
			If NG\SphereCode > 0 And NG_SphereOn = True
			
				limitX = limitX + 22
			
				width  = ImageWidth ( NG_Sphere0_O ) + 5
				height = ImageWidth ( NG_Sphere0_O )
				
				width2 = width * 2
				width3 = width * 3
				
				m = 5
				
				;------------------------------------------------------
				; on a encore ouvert aucune sphere de cette fenetre...
				;------------------------------------------------------
				If NG_ActiveSphere <> NG\Id

					;------------------------------------
					; survole t-on la sphere 0 ?
					;------------------------------------
					If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
				
						DrawImage NG_Sphere0_O , NG\Px + NG\Tx - width  , NG\Py + 5
						
						;-------------------------------------------------------------------------------
						; clique t-on dessus ?
						;-------------------------------------------------------------------------------
						; ou la survole t-on ? (mode ng_spherefly = true)
						;-------------------------------------------------------------------------------
						
						;---------------------------
						; survol simple
						;---------------------------
						If NG_SphereFly = True
						
							If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height ) = True And NG_OnCottage = False; up
						
								NG_ActiveSphere = NG\ID
													
							EndIf
						
						;---------------------------
						; clic dessus
						;---------------------------
						Else
						
							If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height , True ) = 99 And NG_OnCottage = False; up
						
								NG_ActiveSphere = NG\ID
						
							EndIf	
						
						EndIf
						
					;-----------------
					; ou pas ?
					;-----------------	
					Else
					
						DrawImage NG_Sphere0 , NG\Px + NG\Tx - width , NG\Py + 5
										
					EndIf
				
				;--------------------------------------------
				; a t-on ouvert les spheres ?
				;--------------------------------------------
				Else If NG_ActiveSphere = NG\ID
				
					;-------------------
					; close seulement
					;-------------------
					If NG\SphereCode = 1
					
						If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere1_O , NG\Px + NG\Tx - width , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								;------------------------------------------------
								; fermeture et création d'un CloseFX
								;------------------------------------------------
								NG_SwitchWindowDisplayMode ( NG\Id )
								
								FX.NG_CloseFX = New NG_CloseFX
									
									FX\WinId = NG\Id
									FX\Px = NG\Px
									FX\Py = NG\Py
									FX\Tx = NG\Tx
									FX\Ty = NG\Ty						 
																	
							EndIf
																				
						Else
						
							DrawImage NG_Sphere1 , NG\Px + NG\Tx - width , NG\Py + 5
						
						EndIf
					
					;-------------------
					; min seulement
					;-------------------
					Else If NG\SphereCode = 10
					
						If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere2_O , NG\Px + NG\Tx - width , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_MouseOldClic1 = 0 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG_SwitchWindowMinimizeMode ( NG\Id )
												
							EndIf
						
						Else
						
							DrawImage NG_Sphere2 , NG\Px + NG\Tx - width , NG\Py + 5
						
						EndIf
					
					;-------------------
					; cottage seulement
					;-------------------
					Else If NG\SphereCode = 100
					
						 If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere3_O , NG\Px + NG\Tx - width , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG\Cottage = True
								
								NG\CottageMad = CottageMadStart				 
																	
							EndIf
						
						Else
						
							DrawImage NG_Sphere3 , NG\Px + NG\Tx - width , NG\Py + 5
						
						EndIf
					
					;-------------------
					; close + min
					;-------------------
					Else If NG\SphereCode = 11
					
						limitX = limitX + 27
					
						If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere1_O , NG\Px + NG\Tx - width , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								;------------------------------------------------
								; fermeture et création d'un CloseFX
								;------------------------------------------------
								NG_SwitchWindowDisplayMode ( NG\Id )
								
								FX.NG_CloseFX = New NG_CloseFX
									
									FX\WinId = NG\Id
									FX\Px = NG\Px
									FX\Py = NG\Py
									FX\Tx = NG\Tx
									FX\Ty = NG\Ty						 
																	
							EndIf
						
						Else
						
							DrawImage NG_Sphere1 , NG\Px + NG\Tx - width , NG\Py + 5
						
						EndIf
						
						
						If NG_MouseZone ( NG\Px + NG\Tx - width2 , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere2_O , NG\Px + NG\Tx - width2 , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_MouseOldClic1 = 0 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG_SwitchWindowMinimizeMode ( NG\Id )
												
							EndIf
						
						Else
						
							DrawImage NG_Sphere2, NG\Px + NG\Tx - width2 , NG\Py + 5
						
						EndIf
											 					
					;-------------------
					; close + cottage
					;-------------------
					Else If NG\SphereCode = 101
					
						limitX = limitX + 27
					
						If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere1_O , NG\Px + NG\Tx - width , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								;------------------------------------------------
								; fermeture et création d'un CloseFX
								;------------------------------------------------
								NG_SwitchWindowDisplayMode ( NG\Id )
								
								FX.NG_CloseFX = New NG_CloseFX
									
									FX\WinId = NG\Id
									FX\Px = NG\Px
									FX\Py = NG\Py
									FX\Tx = NG\Tx
									FX\Ty = NG\Ty						 
																	
							EndIf
						
						Else
						
							DrawImage NG_Sphere1 , NG\Px + NG\Tx - width , NG\Py + 5
						
						EndIf
						
						
						If NG_MouseZone ( NG\Px + NG\Tx - width2 , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere3_O , NG\Px + NG\Tx - width2 , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG\Cottage = True
								
								NG\CottageMad = CottageMadStart							 
																	
							EndIf
						
						Else
						
							DrawImage NG_Sphere3, NG\Px + NG\Tx - width2 , NG\Py + 5
						
						EndIf
					
					;-------------------
					; min + cottage
					;-------------------
					Else If NG\SphereCode = 110
					
						limitX = limitX + 27				
						 If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere2_O , NG\Px + NG\Tx - width , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_MouseOldClic1 = 0 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG_SwitchWindowMinimizeMode ( NG\Id )
												
							EndIf
						
						Else
						
							DrawImage NG_Sphere2 , NG\Px + NG\Tx - width , NG\Py + 5
						
						EndIf
						
						
						If NG_MouseZone ( NG\Px + NG\Tx - width2 , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere3_O , NG\Px + NG\Tx - width2 , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG\Cottage = True
								
								NG\CottageMad = CottageMadStart						 
																	
							EndIf
						
						Else
						
							DrawImage NG_Sphere3, NG\Px + NG\Tx - width2 , NG\Py + 5
						
						EndIf
					
					;-------------------------
					; close + min + cottage
					;-------------------------
					Else If NG\SphereCode = 111
					
						limitX = limitX + 54
					
						If NG_MouseZone ( NG\Px + NG\Tx - width , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere1_O , NG\Px + NG\Tx - width , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								;------------------------------------------------
								; fermeture et création d'un CloseFX
								;------------------------------------------------
								NG_SwitchWindowDisplayMode ( NG\Id )
								
								FX.NG_CloseFX = New NG_CloseFX
									
									FX\WinId = NG\Id
									FX\Px = NG\Px
									FX\Py = NG\Py
									FX\Tx = NG\Tx
									FX\Ty = NG\Ty						 
																	
							EndIf

						Else
						
							DrawImage NG_Sphere1 , NG\Px + NG\Tx - width , NG\Py + 5
						
						EndIf
						
						
						If NG_MouseZone ( NG\Px + NG\Tx - width2 , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere2_O , NG\Px + NG\Tx - width2 , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_MouseOldClic1 = 0 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG_SwitchWindowMinimizeMode ( NG\Id )
												
							EndIf
						
						Else
						
							DrawImage NG_Sphere2, NG\Px + NG\Tx - width2 , NG\Py + 5
						
						EndIf
						
						
						If NG_MouseZone ( NG\Px + NG\Tx - width3 , NG\Py + 5 , width , height )
						
							DrawImage NG_Sphere3_O , NG\Px + NG\Tx - width3 , NG\Py + 5
							
							;-------------------
							; validation
							;-------------------
							If NG_MouseClic1 And NG_DragIcon = False And NG_OnDoseur = False And NG_OnCottage = False
					
								NG\Cottage = True
								
								NG\CottageMad = CottageMadStart					 
																	
							EndIf
						
						Else
						
							DrawImage NG_Sphere3, NG\Px + NG\Tx - width3 , NG\Py + 5
						
						EndIf
	
					
					EndIf
				
				
				EndIf
			
			
			EndIf

			;---------------------
			; Titre de la fenetre
			;---------------------
			If NG\Taille_Titre = 2
			
				SetFont NG_TitleFontB
				dec = 3 ; décalage 
			
			Else
			
				SetFont NG_TitleFontA
				dec = 2 ; décalage 
			
			EndIf
			
			longueur_BarreTitre = NG\Tx
			
			;---------------------------
			; Taille de la barre titre
			;---------------------------
			If NG\Titre$ <> "" Or NG\TitleLine = True
			
				NG\Taille_BarreTitre = FontHeight()
				NG\Taille_BarreTitre = NG\Taille_BarreTitre + 10 ; bordures
			
			Else
			
				NG\Taille_BarreTitre = 10
			
			EndIf
							
			; Viewport pour éviter les débordements
			Viewport NG\Px , NG\Py , NG\Tx - limitX , WinTy
			
			;----------	
			; ombrage
			;----------
			If NG\icon_on
			
				start = NG\Px + 10 + NG\Debut_titre + dec
			
			Else
			
				start = NG\Px + 5 + dec
			
			EndIf
			
			
			If NG\ombre
			
				Color NG_Font_or , NG_Font_og , NG_Font_ob
						
				Text ( start + dec , NG\Py + 5 + dec , NG\Titre$ )
			
			EndIf
			
			;-----------	
			; normales
			;-----------
			Color NG_TFont_r , NG_TFont_g , NG_TFont_b 
			Text ( start , NG\Py + 5 , NG\Titre$ )
			
			
			;-------------------------
			; icone ?
			;-------------------------
			If NG\icon_on
			
				If NG\hide
				
					Viewport NG\Px , NG\Py , start + NG\Debut_titre + 5 , WinTy - 2
				
				EndIf
			
				start = NG\Px + 5 + dec
			
				DrawImage NG\icon , start , NG\Py + 5 
			
			EndIf
			
			;------------------		
			; retablissement
			;------------------
			Viewport 0 , 0 , GraphicsWidth() , GraphicsHeight()

			;----------------------------
			; longueur de la barre Titre
			;----------------------------
			longueur_BarreTitre = longueur_BarreTitre - debut
			NG\Longueur_BarreTitre = longueur_BarreTitre
			
			;-------------------------------------
			; Affichage des gadgets de la fenetre
			;-------------------------------------
			
			If NG_ClassicTab = True
									
				NG_DrawTab         ( Handle (NG) )
			
			EndIf
			
			NG_DrawImage       ( Handle (NG) )
			NG_DrawFrame       ( Handle (NG) )
			NG_DrawPong        ( Handle (NG) )
			NG_DrawValue       ( Handle (NG) )
			NG_DrawInput       ( Handle (NG) )
			NG_DrawMultiInput  ( Handle (NG) )
			NG_DrawCheckBox    ( Handle (NG) )
			NG_DrawButton      ( Handle (NG) )
			NG_DrawCycleButton ( Handle (NG) )
			NG_DrawDoseur      ( Handle (NG) )			
			NG_DrawImageButton ( Handle (NG) )
			NG_Draw3dFrame     ( Handle (NG) )
			NG_DrawText        ( Handle (NG) )
			NG_DrawIconViewer  ( Handle (NG) )
			NG_DrawColor       ( Handle (NG) )
			NG_DrawCombo       ( Handle (NG) )
			NG_DrawBoard       ( Handle (NG) )
			NG_DrawAgent       ( Handle (NG) )
			NG_DrawProgress    ( Handle (NG) )
			NG_DrawFPS         ( Handle (NG) )
			
			If NG_ClassicTab = False
			
				NG_DrawDesignTab         ( Handle (NG) )
			
			EndIf
						
			NG_DrawMenu        ( Handle (NG) )
									
			;-------------------------------------------------
			; en dernier les help labels
			;-------------------------------------------------
			If NG_HelpLabel$ <> "" And NG_HelpOn
			
				Viewport 0 , 0 , GraphicsWidth() , GraphicsHeight()
				NG_DisplayHelpBox ( NG_MouseX + 15 , NG_MouseY , NG_HelpLabel$ )
			
			EndIf
			
						
			; retablissement du viewport
			Viewport 0 , 0 , GraphicsWidth() , GraphicsHeight()
			
			;----------------------------------------------
			; minimiser en double cliquant
			;----------------------------------------------
			If NG_Mouse2Clic And NG_MouseZone ( NG\Px , NG\Py , NG\Tx - (18+18) , WinTy ) And NG_OnGadget = False And NG_OnSpecialGadget = False And NG\Dcmin = True
			
				If NG_InfoWindow = False
			
					NG_SwitchWindowMinimizeMode ( NG\Id )
				
				EndIf
				
			EndIf
						
			; -------------------------------------------------------------------------------------------
			; Si notre fenetre est active et en deplacement, puisque ceci va influencer sur nos dessins
			; -------------------------------------------------------------------------------------------
			; De plus, il faut qu'elle soit définie comme mouvante
			;--------------------------------------------------------------------------------------------
			
			;-----------------------------------------------------------------------------------------------
			; on place ce code ici parcequ'on doit d'abord vérifier que la souris n'est sur aucun gadget
			;-----------------------------------------------------------------------------------------------
			
			If  NG_MoveWindow And NG\Active = True And NG\Movable And NG_ComboBoxOpen = False
			
				If NG_OnGadget = False And NG_OnSpecialGadget = False
				
					NG\Px = NG\Px + NG_MouseDragX
		    		NG\Py = NG\Py + NG_MouseDragY

				EndIf
						
			EndIf
			
			
				;----------------------------------------------------------
				;----------------------------------------------------------
				; 						Module Preview
				;----------------------------------------------------------
				;----------------------------------------------------------
				
				;--------------------------------------------
				; si notre fenetre est une fenetre preview
				;--------------------------------------------
				
				min = 80
				
				;------------------------
				; Image Preview
				;------------------------
				If NG\media$ = "image"
								
					If NG_ReturnButton ( NG\moins )
					
						img = CopyImage (NG\image0)
						
						oldimg = NG_ReturnImage (NG\image)
				
						width = ImageWidth (oldimg)
						height = ImageHeight (oldimg)
						
						width = width / 2
						height = height / 2
						
						If width > min And height > min 
						
							ResizeImage img , width , height
							
							NG_SetImageFromHandleForPreview ( NG\image , img , width , height )
																			
							Tx = width  + 20
							Ty = height + 70
							
							NG_SetWindowSize ( NG\ID , Tx , Ty )
						
						EndIf
						
					Else If NG_ReturnButton ( NG\plus )
					
						img = CopyImage (NG\image0)
				
						oldimg = NG_ReturnImage (NG\image)
				
						width = ImageWidth (oldimg)
						height = ImageHeight (oldimg)
						
						width = width * 2
						height = height * 2
						
						ResizeImage img , width , height
							
						NG_SetImageFromHandleForPreview ( NG\image , img , width , height )
																			
						Tx = width  + 20
						Ty = height + 70
							
						NG_SetWindowSize ( NG\ID , Tx , Ty )
																	
					EndIf
				
				;------------------------
				; Video Preview
				;------------------------
				Else If NG\media$ = "video"
				
					; important
					gwidth  = GraphicsWidth()
					gheight = GraphicsHeight()
				
					If NG_ReturnButton ( NG\moins )
					
						If ( NG\videoTX / 2 ) > min  And ( NG\videoTY / 2 ) > min
					
							NG\videoTX = NG\videoTX / 2
							NG\videoTY = NG\videoTY / 2
													
						EndIf
						
						Tx = NG\videoTX + 20
						Ty = NG\videoTY + 70
							
						NG_SetWindowSize ( NG\Id , Tx , Ty )
						
					Else If NG_ReturnButton ( NG\plus )
					
						If ( NG\videoTX * 2 ) < GWidth  And ( NG\videoTY * 2 ) < GHeight

							NG\videoTX = NG\videoTX * 2
							NG\videoTY = NG\videoTY * 2
						
						EndIf
						
						Tx = NG\videoTX + 20
						Ty = NG\videoTY + 70
							
						NG_SetWindowSize ( NG\Id , Tx , Ty )
																	
					EndIf
					
					; seulement si la fenetre est agrandie
					If NG\hide = False
					
						; dessine la video
						DrawMovie NG\video , NG\PX + 10 , NG\PY + 60 , NG\videoTX , NG\videoTY 
					
						; bordure
						NG_DrawTrueBordure ( NG\PX + 10 - 1 , NG\PY + 60 - 1 , NG\videoTX + 2 , NG\videoTY + 2 , 1 , 1 , 1 )
					
					EndIf
					
					; limitation des coordonnées de la fenetre à cause du bug des video Blitz (arf!)
					If NG\Px < 0 Then NG\Px = 0
					If NG\Py < 0 Then NG\Py = 0
					If NG\Px + NG\Tx > gwidth  - 2 Then NG\Px = gwidth  - NG\Tx - 2
					If NG\Py + NG\Ty > gheight - 2 Then NG\Py = gheight - NG\Ty - 2
					
				;------------------------
				; Sound Preview
				;------------------------
				Else If NG\media$ = "son"
				
					NG\new_txt$ = NG\new_txt$ + "."
					
					If Len(NG\new_txt$) > 50
					
						NG\new_txt$ = NG\base_txt$
					
					EndIf 
					
					NG_SetText ( NG\txt , NG\new_txt$ )
					
				;------------------------
				; 3D Preview
				;------------------------
				Else If NG\media$ = "3d"
				
					If NG_ReturnButton ( NG\moins )
					
						If ( NG\frameTX / 2 ) > min  And ( NG\frameTY / 2 ) > min
					
							NG\frameTX = NG\frameTX / 2
							NG\frameTY = NG\frameTY / 2
													
							NG_Set3dFrameSize ( NG\frame3d , NG\frameTX , NG\frameTY )						
													
						EndIf
						
						Tx = NG\frameTX + 20
						Ty = NG\frameTY + 70
							
						NG_SetWindowSize ( NG\Id , Tx , Ty )
						
					Else If NG_ReturnButton ( NG\plus )
					
						NG\frameTX = NG\frameTX * 2
						NG\frameTY = NG\frameTY * 2

					
						Tx = NG\frameTX + 20
						Ty = NG\frameTY + 70
						
						NG_Set3dFrameSize ( NG\frame3d , NG\frameTX , NG\frameTY )
							
						NG_SetWindowSize ( NG\Id , Tx , Ty )
						
					Else If NG_ReturnButtonDown ( NG\Bup )
					
						MoveEntity NG\cam , 0 , +2 , 0					
					
					Else If NG_ReturnButtonDown ( NG\Bdown )
					
						MoveEntity NG\cam , 0 , -2 , 0	
																	
					EndIf
					
					;-----------------------------------
					; Zoom
					;-----------------------------------
					If NG_MouseUp1 Then NG_SetDoseur ( NG\DoseurZ , 0 )
					
					z# = NG_ReturnDoseur# ( NG\DoseurZ )
					MoveEntity NG\cam , 0 , 0 , z#
					
					;-----------------------------------
					; Rotate
					;-----------------------------------
					If NG_MouseUp1 Then NG_SetDoseur ( NG\DoseurR , 0 )
					
					rota# = NG_ReturnDoseur# ( NG\DoseurR )
					TurnEntity NG\pivot , 0 , rota# , 0
					
					;------------------------
					; Point camera
					;------------------------
					PointEntity NG\cam , NG\pivot
				
				
				;------------------------
				; Text Preview
				;------------------------
				Else If NG\media$ = "texte"
				
					If NG_ReturnButton ( NG\moins )
					
						If ( NG\textTX / 2 ) > min  And ( NG\textTY / 2 ) > min
					
							NG\textTX = NG\textTX / 2
							NG\textTY = NG\textTY / 2
													
							NG_SetMultiInputSize ( NG\minput , NG\textTX , NG\textTY )						
													
						EndIf
						
						Tx = NG\textTX + 20 + 30
						Ty = NG\textTY + 70
							
						NG_SetWindowSize ( NG\Id , Tx , Ty )
						
					Else If NG_ReturnButton ( NG\plus )
					
						NG\textTX = NG\textTX * 2
						NG\textTY = NG\textTY * 2
					
						Tx = NG\textTX + 20 + 30
						Ty = NG\textTY + 70
						
						NG_SetMultiInputSize ( NG\minput , NG\textTX , NG\textTY )
							
						NG_SetWindowSize ( NG\Id , Tx , Ty )
					
					EndIf						
								
																
				EndIf
				
				;--------------------------------------------------------------------------------------
				; cas particulier : les fenetres Preview sont détruites si il y a un simple fermeture
 				;--------------------------------------------------------------------------------------
				If NG\Killme = True
				
					NG_DeleteWindow ( NG\Id)
				
				EndIf
				
				
		
		EndIf
	
	
	Next
	
	
End Function

;--------------------------------
; Dessine une bordure biseautée
;--------------------------------
Function NG_DrawBordure ( Px , Py , Tx , Ty , r=212 , g=212 , b=212 )

	Color r , g , b
	
	Rect Px+1 , Py , Tx-2 , 1       ; haut
	Rect Px+1 , Py+Ty-1 , Tx-2 , 1 ; bas
	
	Rect Px , Py+1 , 1 , Ty-2           ; gauche
	Rect Px+Tx-1 , Py+1 , 1 , Ty-2 ; droite
	
End Function

;-----------------------------------
; Dessine une bordure non biseautée
;-----------------------------------
Function NG_DrawTrueBordure ( Px , Py , Tx , Ty , r=0 , g=0 , b=0 , mode=1 , grad=0 )

	Color r , g , b
	
	;Rect Px , Py , Tx , Ty , 0
	
	; état normal
	If mode = 2
	
		Color NG_Grad_Ro , NG_Grad_Go , NG_Grad_Bo 
		Rect Px , Py , Tx  , 2
		Rect Px , Py , 2 , Ty
		
		Color NG_Grad_Rl , NG_Grad_Gl , NG_Grad_Bl
		Rect Px + Tx - 1  , Py , 1 , Ty
		Rect Px , Py + Ty - 1 , Tx , 1
	
	; état d'un bouton appuyé (par exemple)
	Else If mode = 1
	
		Color NG_Grad_Ro , NG_Grad_Go , NG_Grad_Bo 
		Rect Px , Py , Tx  , 1
		Rect Px , Py , 1 , Ty
		
		Color NG_Grad_Ro , NG_Grad_Go , NG_Grad_Bo
		Rect Px + Tx - 1  , Py , 1 , Ty
		Rect Px , Py + Ty - 1 , Tx , 1
 		
	EndIf
	
	;--------------------------------
	; ombres profondeur
	;--------------------------------
	If grad = True And NG_Shadow = True
	
		;--------------------
		; haut
		;--------------------
		
		R = 0
		G = 0
		B = 0
				
		cR# = Float (NG_Input_R / 4)
		cG# = Float (NG_Input_G / 4)
		cB# = Float (NG_Input_B / 4)
		
		R = R + cR
			
		G = G + cG
			
		B = B + cB	
		
		
		For i = 1 To 3
		
			Color R , G , B
			
			Rect Px + 1 + (i-1) , Py + i , Tx-2 - (i-1) , 1 
			
			R = R + cR
			
			G = G + cG
			
			B = B + cB			
				
		Next
		
		;--------------------
		; gauche
		;--------------------
		
		R = 0
		G = 0
		B = 0
		
		R = R + cR
			
		G = G + cG
			
		B = B + cB	
		
		For i = 1 To 3
		
			Color R , G , B
			
			Rect Px + i , Py + 1 + (i-1) , 1 , Ty-2  - (i-1)
			
			R = R + cR
			
			G = G + cG
			
			B = B + cB 
				
		Next

	
	EndIf
	
End Function

;---------------------------------
; Dessine les frames
;---------------------------------
Function NG_DrawFrame ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Frame = Each NG_Frame
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide = 0 And TabOK = True And NG\show = True
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;------------------------------------
			; on dessine la frame sur une image
			;------------------------------------
			; on utilise la couleur coté clair des boutons
			;-----------------------------------------------
			
			tmp = CreateImage ( NG\Tx , NG\Ty )
			MaskImage tmp , NG_maskR , NG_maskG , NG_maskB
								
			SetBuffer ImageBuffer(tmp)
			
			; on colorie l'image de cette couleur
			;---------------------------------------
			Color NG_maskR , NG_maskG , NG_maskB			
			Rect 0 , 0 , NG\Tx , NG\Ty , True
			
			; on dessine le cadre
			;---------------------			
			Color NG_Font_r , NG_Font_g , NG_Font_b
			Rect 0 , FontHeight()/2 , NG\Tx , NG\Ty - FontHeight()/2 , False
			
			; on efface la zone texte
			;--------------------------
			SetFont NG_NormalFont 
			
			Color NG_maskR , NG_maskG , NG_maskB			
			Rect 5 , 0 , StringWidth(NG\Label$)+10 , 10 , True
			
			; on rend le buffer
			;--------------------
			SetBuffer BackBuffer()
			
			; on affiche l'image
			;----------------------
			DrawImage tmp , Xref , Yref
			
			; on supprime l'image
			;----------------------
			FreeImage tmp
					
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
			SetFont NG_NormalFont
						
			; si le texte est ombré
			If NG\ombre
				
				Color NG_Font_or , NG_Font_og , NG_Font_ob
				 
				NG_DisplayText( NG\Label$ , Xref+12 , Yref + 2 - FontHeight() - 3 , 200 )
			
			EndIf
			
			; le vrai texte
			Color NG\r , NG\g , NG\b
			NG_DisplayText( NG\Label$ , Xref+10 , Yref - FontHeight() - 3 , 200 )
		
		EndIf
	Next

End Function

;---------------------------------
; Dessine les Menus
;---------------------------------
Function NG_DrawMenu ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Menu = Each NG_Menu
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide = 0 And TabOK = True And NG\show = True
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			longueur = NG\Tx
			hauteur = NG\Ty
			
			;---------------------------------------------
			; oui nous sommes sur un gadget !
			;---------------------------------------------
			If NG_MouseZone ( Xref , Yref , longueur , hauteur )
			
				NG_OnGadget = True
			
			EndIf
			
			
			;-----------
			; viewport
			;-----------
			;Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre , ParentWindow\Tx , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			;Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			Viewport 0,0,GraphicsWidth(),GraphicsHeight()

			;--------------------------------------
			; d'abord on dessine le fond du menu
			;--------------------------------------
			
			;---------------------
			; bord gauche
			;---------------------
			bord_w = ImageWidth ( NG_Menu_S )
			mid_w  = ImageWidth ( NG_Menu_M )
			
			DrawImage NG_Menu_S , Xref , Yref
			
			;---------------------
			; milieu
			;---------------------
			longueur_mid = longueur - bord_w - bord_w
			
			pas = longueur_mid / mid_w
			
			pos_x = 0
			
			For x = 0 To pas
			
				pos_x = Xref + bord_w + ( x * mid_w )
				
				DrawImage NG_Menu_M , pos_X , Yref
			
			Next
			
			;----------------------
			; bord droit
			;----------------------
			pos_x = pos_x + mid_w
			
			DrawImage NG_Menu_E , pos_x , Yref
			
			;----------------------------------------------
			; les menus prinipaux
			;----------------------------------------------
			SetFont NG_NormalFont
			
			start_write = Xref + 10
			
			For Item.NG_MenuItem = Each NG_MenuItem
			
				If Item\MenuId = NG\Id
				
					If Item\me_parent = True And Item\SubLevel = 0
										
						Color NG_Font_r , NG_Font_g , NG_Font_b
						NG_DisplayText ( Item\Label$ , start_write , Yref - FontHeight() + 3 , 50 )
					
						;-----------------------------------------------
						; interactions
						;-----------------------------------------------
						bouton = NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty , 1 )
						
						If MouseX() >= start_write And MouseX() <= start_write + StringWidth ( Item\Label$) + 10
						
							If MouseY() >= Yref - FontHeight() And MouseY() <= Yref + hauteur; - FontHeight()								
								
								If bouton = 99 And NG_OnCottage = False
									
									NG_MenuOpenId = NG\Id
																																	
								EndIf
								
								old_MenuOpenLabel$ = NG_MenuOpenLabel$
									
								NG_MenuOpenLabel$ = Item\Label$
								
								;-------------------------------------
								; on change d'item à explorer
								;-------------------------------------
								If old_MenuOpenLabel$ <> NG_MenuOpenLabel$
								
									NG_parent_label$ = ""
								
								EndIf
									
								
								NG_MenuOpen_x = start_write
								
							EndIf
						
						EndIf						
						
						start_write = start_write + StringWidth ( Item\Label$ ) + 20
					
					EndIf				
				
				EndIf
				
			Next
			
			;-------------------------------------------------
			; les sous menus (cadre)
			;-------------------------------------------------
			start_y = Yref + 15
			
			max_long = 0
			max_haut = 0
			
			If NG_MenuOpenId = NG\Id
						
				For Item.NG_MenuItem = Each NG_MenuItem
			
					If Item\MenuId = NG\Id
					
						If Item\parent$ = NG_MenuOpenLabel$
						
							;---------------------------------------
							; longueur max
							;---------------------------------------
							item_w = StringWidth ( Item\Label$ )
							item_h = StringHeight ( Item\Label$ )
						
							If max_long < item_w
							
								max_long = Item_w
							
							EndIf
														
							;---------------------------------------
							; hauteur du menu
							;---------------------------------------
							max_haut = max_haut + item_h + 8
						
						EndIf
					
					EndIf
				
				Next
				
				;------------------------------
				; reglages...
				;------------------------------
				max_long = max_long + NG\Largeur
				max_haut = max_haut + 5			
			
			EndIf
			
			;-----------------------------------------------------
			; on dessine le cadre
			;-----------------------------------------------------
			If NG_MenuOpenId = NG\Id

				Color 0,0,200
				
				;-----------------------------------------------
				; debut du cadre
				;-----------------------------------------------
				
				;-------------------------------------
				; deploiement vers le bas (normal)
				;-------------------------------------
				If NG\sens = 1
				
					Dy = start_y + 15
					
				;----------------------------------------------------
				; deploiement vers le haut (comme le menu demarrer)
				;----------------------------------------------------
				Else
					
					Dy = start_y - 15 - max_haut
				
				EndIf			
				
				;-----------------------------------------------
				; degradé : on démarre des valeurs par défaut
				;-----------------------------------------------
				red_start#   = NG_Menu_Rs : red_end#   = NG_Menu_Re
				green_start# = NG_Menu_Gs : green_end# = NG_Menu_Ge
				blue_start#  = NG_Menu_Bs : blue_end#  = NG_Menu_Be
								
				; décalage du à l'appui du bouton
				decalage = 0
				
				; création du dégradé
				Dir = 1
				
				GradientRed#   = ( Red_end - Red_start ) / max_haut
				GradientGreen# = ( Green_end - Green_start ) / max_haut
				GradientBlue#  = ( Blue_end - Blue_start ) / max_haut
								
				For Y = Dy To ( Dy + max_haut ) 
				
					Color Red_start , Green_start , Blue_start
					
					Rect NG_MenuOpen_x - 5 , Y , max_long , 1
					
					Red_start = Red_start + GradientRed
					Green_start = Green_start + GradientGreen
					Blue_start = Blue_start + GradientBlue
					
				Next
				
				;--------------------
				; Bordure du menu
				;--------------------
				NG_DrawTrueBordure ( NG_MenuOpen_x - 5  , Dy  , max_long , max_haut+1 , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB )
								
			EndIf			
			
			;-----------------------------------------------------
			; les sous menus (textes)
			;-----------------------------------------------------			
			If NG_MenuOpenId = NG\Id
			
				;-------------------------------------
				; deploiement vers le bas (normal)
				;-------------------------------------
				If NG\sens = 1
				
					Dy = start_y
					
				;----------------------------------------------------
				; deploiement vers le haut (comme le menu demarrer)
				;----------------------------------------------------
				Else
					
					Dy = start_y - FontHeight() - max_haut -10
					
				EndIf
													
				For Item.NG_MenuItem = Each NG_MenuItem
			
					If Item\MenuId = NG\Id
					
						If Item\parent$ = NG_MenuOpenLabel$
						
							zone = NG_MouseZone ( NG_MenuOpen_x , Dy +  FontHeight()+3 , max_long , FontHeight()+3 )
																											
							If zone = True Or NG_parent_label$ = Item\Label$
							
								Color NG_Sel_R , NG_Sel_G , NG_Sel_B		
								Rect NG_MenuOpen_x - 3 , Dy +  FontHeight()+3 , max_long - 6 , FontHeight()+3				
							
								Color NG_Font_r , NG_Font_g , NG_Font_b
								
								;---------------------------------
								; y a t il un sous menu ?
								;---------------------------------
								parent = Item\me_parent
								
								If parent = True
									
									NG_parent_label$ = Item\Label$
										
									start_x = NG_MenuOpen_x + max_long
										
									start_y = Dy + FontHeight()
								
								Else
								
									NG_parent_label$ = ""
								
								EndIf
																					
							Else
								
								Color NG_Font_r , NG_Font_g , NG_Font_b
														
							EndIf
												
							NG_DisplayText ( Item\Label$ , NG_MenuOpen_x , Dy , 50 )
							
							;-----------------------------------------
							; fleche de sous menu ?
							;-----------------------------------------
							If Item\me_parent
							
								DrawImage NG_Arrow_RIGHT , NG_MenuOpen_x + max_long - 24 , Dy + FontHeight() + 2
								
							EndIf
							
							;----------------------------------
							; selection de l'item du menu
							;----------------------------------
							bouton = NG_MouseZone ( NG_MenuOpen_x , Dy +  FontHeight()+3 , max_long , FontHeight()+3 , True )
							
							If bouton = 1
															
								NG_SelectedMenu$ = Item\Label$
							
							EndIf
													
							;---------------------------------
							; suivant...
							;---------------------------------							
							Dy = Dy + FontHeight() + 3 + 5							
						
						EndIf
					
					EndIf
				
				Next
				
				;--------------------------------------------------------
				; test recherche de sous menus à l'infini
				;--------------------------------------------------------
				Repeat
				
					If parent = True
					
						max_long = 0
						max_haut = 0
						
						For Item.NG_MenuItem = Each NG_MenuItem
					
							If Item\MenuId = NG\Id
							
								If Item\parent$ = NG_parent_label$
		
									;---------------------------------------
									; longueur max
									;---------------------------------------
									item_w = StringWidth ( Item\Label$ )
									item_h = StringHeight ( Item\Label$ )
									
									If max_long < item_w
										
										max_long = Item_w
										
									EndIf
									
									;---------------------------------
									; y a t il un sous menu ?
									;---------------------------------
									parent = Item\me_parent
									
									If parent = True
									
										NG_parent_label$ = Item\Label$
										
										NG_MenuOpen_x = NG_MenuOpen_x + max_long
										
										NG_MenuOpen_y = Dy
										
										
									EndIf
																	
									;---------------------------------------
									; hauteur du menu
									;---------------------------------------
									max_haut = max_haut + item_h + 8
								
								EndIf
						
							EndIf
						
						Next
							
						;------------------------------
						; reglages...
						;------------------------------
						max_long = max_long + NG\Largeur
						max_haut = max_haut + 5
																		
						Color 0,0,200
				
						;-----------------------------------------------
						; debut du cadre
						;-----------------------------------------------
						
						;-------------------------------------
						; deploiement vers le bas (normal)
						;-------------------------------------
						saved_start_y = start_y
						
						If NG\sens = 1
						
							start_y = start_y - 3
							
						;----------------------------------------------------
						; deploiement vers le haut (comme le menu demarrer)
						;----------------------------------------------------
						Else
							
							start_y = start_y - 3 ; - max_haut
						
						EndIf			
						
						;-----------------------------------------------
						; degradé : on démarre des valeurs par défaut
						;-----------------------------------------------
						red_start#   = NG_Menu_Rs : red_end#   = NG_Menu_Re
						green_start# = NG_Menu_Gs : green_end# = NG_Menu_Ge
						blue_start#  = NG_Menu_Bs : blue_end#  = NG_Menu_Be
										
						; décalage du à l'appui du bouton
						decalage = 0
						
						; création du dégradé
						Dir = 1
						
						GradientRed#   = ( Red_end - Red_start ) / max_haut
						GradientGreen# = ( Green_end - Green_start ) / max_haut
						GradientBlue#  = ( Blue_end - Blue_start ) / max_haut
										
						For Y = start_y To ( start_y + max_haut ) 
						
							Color Red_start , Green_start , Blue_start
							
							Rect start_x - 5 , Y , max_long , 1
							
							Red_start = Red_start + GradientRed
							Green_start = Green_start + GradientGreen
							Blue_start = Blue_start + GradientBlue
							
						Next
						
						;--------------------
						; Bordure du menu
						;--------------------
						NG_DrawTrueBordure ( start_x - 5  , start_y  , max_long , max_haut+1 , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB )
												
						;-------------------------------------
						; deploiement vers le bas (normal)
						;-------------------------------------
						If NG\sens = 1
						
							start_y = saved_start_y - FontHeight() + 3
							
						;----------------------------------------------------
						; deploiement vers le haut (comme le menu demarrer)
						;----------------------------------------------------
						Else
							
							start_y = saved_start_y - 10 - 3;- FontHeight() - max_haut
						
						EndIf
															
						For Item.NG_MenuItem = Each NG_MenuItem
					
							If Item\MenuId = NG\Id
							
								If Item\parent$ = NG_parent_label$
								
									zone = NG_MouseZone ( start_x , start_y +  FontHeight()+3 , max_long , FontHeight()+3 )
																						
									If zone = True
									
										Color NG_Sel_R , NG_Sel_G , NG_Sel_B		
										Rect start_x - 3 , start_y +  FontHeight()+3 , max_long - 6 , FontHeight()+3				
									
										Color NG_Font_r , NG_Font_g , NG_Font_b
										
										;---------------------------------
										; y a t il un sous menu ?
										;---------------------------------
										parent = Item\me_parent
										
										If parent = True
											
											NG_parent_label$ = Item\Label$
												
											start_x = start_x + max_long
												
											start_y = start_y + FontHeight()
										
										EndIf
																							
									Else
										
										Color NG_Font_r , NG_Font_g , NG_Font_b
																
									EndIf
														
									NG_DisplayText ( Item\Label$ , start_x , start_y , 50 )
									
									;-----------------------------------------
									; fleche de sous menu ?
									;-----------------------------------------
									If Item\me_parent
									
										DrawImage NG_Arrow_RIGHT , NG_MenuOpen_x + max_long - 24 , Dy + FontHeight() + 2
								
									EndIf
									
									;----------------------------------
									; selection de l'item du menu
									;----------------------------------
									bouton = NG_MouseZone ( start_x , start_y +  FontHeight()+3 , max_long , FontHeight()+3 , True )
									
									If bouton = 1
																	
										NG_SelectedMenu$ = Item\Label$
									
									EndIf
															
									;---------------------------------
									; suivant...
									;---------------------------------							
									start_y = start_y + FontHeight() + 3 + 5							
								
								EndIf
							
							EndIf
						
						Next
						
										
					Else
					
						Exit
					
					EndIf				
				
				Forever
			
			EndIf	
					
		EndIf
		
	Next

End Function


;---------------------------------
; Dessine les textes
;---------------------------------
Function NG_DrawText ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Text = Each NG_Text
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide = 0 And TabOK = True And NG\show = True
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
		
			;-----------------------
			; Classique
			;-----------------------
			If NG\zone = False
			
				Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;-----------------------------------------------------
			; ou à Zone, définie par l'utilisateur
			;-----------------------------------------------------
			Else
			
				Viewport ParentWindow\Px + NG\zone_xs , ParentWindow\Py + NG\zone_ys , NG\zone_Xe , NG\zone_Ye
			
			EndIf
			
			dy = ParentWindow\Py + ParentWindow\Taille_BarreTitre
			fy = dy + ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
			
			;--------------------------------------------
			; Utilisation de la font Standard
			;--------------------------------------------
			If NG\font = 0
			
				SetFont NG_NormalFont
				
			;--------------------------------------------
			; ou définie par l'utilisateur
			;--------------------------------------------	
			Else
			
				SetFont NG\font
						
			EndIf	
				
			; si le texte est ombré
			If NG\ombre
				
				Color NG_Font_or , NG_Font_og , NG_Font_ob
				 
				NG_DisplayText ( NG\Label$ , Xref+2 , Yref+2 , NG\Chars , NG\Center )
			
			EndIf
			
			; le vrai texte
			Color NG\r , NG\g , NG\b
			;NG_DisplayText ( NG\Label$ , Xref , Yref , NG\Chars , NG\Center )
			NG_DisplayTextFast ( NG\Label$ , Xref , Yref , NG\Chars , dy , fy , NG\Center )
		
		EndIf
	Next

End Function


;---------------------------------
; Dessine les Progress Bar
;---------------------------------
Function NG_DrawProgress ( parent_window )

	Local level$

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Progress = Each NG_Progress
				
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide = 0 And TabOK = True And NG\show = True
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;-------------------------------------------------
			; on dessine la progress bar
			;-------------------------------------------------
			longueur = NG\Tx
			hauteur  = NG\Ty
			
			;-------------------------------------------------
			; calcul de la longueur remplie par rapport au %
			;-------------------------------------------------
			rempli# = ( Float (longueur) / 100.0 ) *  (NG\level)
			
			;------------------------------------------------
			; fond
			;------------------------------------------------
			Color NG_PB_r , NG_PB_g , NG_PB_b
			Rect Xref + rempli - 1 , Yref , longueur - rempli , hauteur , True
			Rect Xref , Yref , longueur , hauteur , True

			
			;--------------------------------------------------
			; avec degradé
			;--------------------------------------------------
			If NG\grad = True
			
				;-----------------------------------------------
				; degradé : on démarre des valeurs par défaut
				;-----------------------------------------------
				red_start#   = NG\R_s : red_end#   = NG\R_e
				green_start# = NG\G_s : green_end# = NG\G_e
				blue_start#  = NG\B_e : blue_end#  = NG\B_e
				
				;----------------------
				; création du dégradé
				;----------------------
				Dir = 1
				
				GradientRed#   = ( Red_end - Red_start ) / (NG\Ty)
				GradientGreen# = ( Green_end - Green_start ) / (NG\Ty)
				GradientBlue#  = ( Blue_end - Blue_start ) / (NG\Ty)
								
				For Y = Yref To ( Yref + NG\Ty-1 ) 
				
					Color Red_start , Green_start , Blue_start
					
					Rect Xref+1 , Y , rempli - 2 , 1
					
					Red_start = Red_start + GradientRed
					Green_start = Green_start + GradientGreen
					Blue_start = Blue_start + GradientBlue
					
				Next
			
			Else
			
				Color NG\R_s , NG\G_s , NG\B_s
				
				Rect Xref , Yref , rempli , hauteur , True
			
			EndIf
						
			;----------------------
			; bordure de la PBar
			;----------------------
			NG_DrawTrueBordure ( Xref , Yref-1 , longueur , hauteur + 2  )
			
		
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
			SetFont NG_NormalFont
			
			Milieu = longueur / 2
			
			level$ = Int (NG\level) + " %"
			
			Mtext = StringWidth ( level$ ) / 2
				Xref2 = Xref + Milieu - Mtext
			
			Milieu = hauteur / 2
			Mtext = StringHeight ( level$ ) / 2
				Yref2 = Yref + Milieu - Mtext - FontHeight() - 5
				
			If NG\levelOn
			
				lv = Int (NG\level)
			
				;------------------------
				; L'ombre
				;------------------------
				If NG\Ombre
				
					Color NG_Font_or , NG_Font_og , NG_Font_ob
					 
					NG_DisplayText ( level$ , Xref2 + 2 , Yref2 + 2 , 50 )
				
				EndIf
				
				;------------------------
				; le vrai texte
				;------------------------
				Color NG_Font_r , NG_Font_g , NG_Font_b
				NG_DisplayText ( level$ , Xref2 , Yref2 , 50 )
		
			EndIf
			
			If NG\LabelOn
			
				Color NG_Font_r , NG_Font_g , NG_Font_b
				NG_DisplayText ( NG\Label$ , Xref , Yref - FontHeight() - 25 , 50 )
				
			EndIf
			
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , longueur , hauteur )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
			
			
		
		EndIf
	Next

End Function

;---------------------------------
; dessine les couleurs
;---------------------------------
Function NG_DrawColor ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Color = Each NG_Color
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
				
		If NG\WinId = ParentWindow\Id And ParentWindow\hide = 0 And TabOK = True And NG\show = True
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;-------------------------------------
			; carré couleur
			;-------------------------------------
			Color NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB
			
			Rect Xref-1 , Yref-1 , NG\Tx+2 , NG\Ty+2
			
			Color NG\R , NG\G , NG\B
			
			Rect Xref , Yref , NG\Tx , NG\Ty
			
			
			
			; S'il s'agit bien d'un bouton de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 )  And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False

			
					bouton = NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty , 1 )
				
					;------------------------------
					; on est bien sur un gadget
					;------------------------------
					If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
						NG_OnGadget = True
					EndIf
												
					If bouton = 99
						
						NG_OpenColorPicker ( NG\R , NG\G , NG\B , NG\Mode )
						
						NG\R = NG_ReturnRed ()
						
						NG\G = NG_ReturnGreen ()
												
						NG\B = NG_ReturnBlue ()
						
						NG\Mode = NG_ReturnMode ()
						
					EndIf
				
			EndIf
			
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
			SetFont NG_NormalFont
						
			If NG\label$ <> ""
			
				Color NG_Font_r , NG_Font_g , NG_Font_b
				NG_DisplayText ( NG\Label$ , Xref + NG\Tx + 10 , Yref - FontHeight() - 3 , 1000 )
			
			EndIf

			
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf


			
			
		EndIf
	Next

End Function


;---------------------------------
; Dessine les boutons
;---------------------------------
Function NG_DrawButton ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Button = Each NG_Button
	
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True
		
			;-----------------------------
			; par défaut....
			;-----------------------------
			NG\State = 0
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			
			
			;-----------------------------
			; Pour dessiner et calculer
			;-----------------------------
			SetFont NG_NormalFont
			
			; 2ndes réfenrences pour les textes, pour centrer en X et en Y
			Xref2 = ( (NG\Tx)/2 ) - ( StringWidth(NG\Label$) / 2 )
			Yref2 = ( (NG\Ty)/2 ) - ( FontHeight () / 2 ) - 20 ; -20pour compenser le systeme de coords de Blitz
			
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			; S'il s'agit bien d'un bouton de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False And NG_OnOldValue = False  And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
					bouton = NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty , 1 )
				
					;------------------------------
					; on est bien sur un gadget
					;------------------------------
					If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
						NG_OnGadget = True
					EndIf
												
					If bouton = 1 And NG\Immuable = False
										
						; on le place en mode appuyé mais en attente
						NG\State = -1
					
					; mouseUp
					Else If bouton = 99
											
						NG\State = 1
					
					Else
					
						NG\State = 0
						
					EndIf
				
			EndIf
			
			;--------------------------------
			; On dessine d'abord le bouton
			;------------------------------------------
			; Que si le bouton lui meme doit l'etre
			;------------------------------------------
			
			;--------------------------------
			; Gestion des décalages
			;--------------------------------
			If NG\State = 0
				decalage = 0
			Else
				decalage = 1
			EndIf
			
			If NG\box
			
				; si normal
				If NG\state = 0
				
					;-----------------------------------------------
					; degradé : on démarre des valeurs par défaut
					;-----------------------------------------------
					red_start#   = NG_IB_Re : red_end#   = NG_IB_Rs
					green_start# = NG_IB_Ge : green_end# = NG_IB_Gs
					blue_start#  = NG_IB_Be : blue_end#  = NG_IB_Bs
				
					; décalage du à l'appui du bouton
					decalage = 0
				
				Else
				
					;-----------------------------------------------
					; degradé : on démarre des valeurs par défaut
					;-----------------------------------------------
					red_start#   = NG_IB_Rs : red_end#   = NG_IB_Re
					green_start# = NG_IB_Gs : green_end# = NG_IB_Ge
					blue_start#  = NG_IB_Bs : blue_end#  = NG_IB_Be
					
					; décalage du à l'appui du bouton
					decalage = 1
				
				EndIf
				
				; création du dégradé
				Dir = 1
				
				GradientRed#   = ( Red_end - Red_start ) / NG\Ty
				GradientGreen# = ( Green_end - Green_start ) / NG\Ty
				GradientBlue#  = ( Blue_end - Blue_start ) / NG\Ty
								
				For Y = Yref To ( Yref + NG\Ty-1 ) 
				
					Color Red_start , Green_start , Blue_start
					
					;Line Xref+1 , Y , Xref + NG\Tx - 2 , Y
					Rect Xref+1 , Y , NG\Tx - 2 , 1
					
					Red_start = Red_start + GradientRed
					Green_start = Green_start + GradientGreen
					Blue_start = Blue_start + GradientBlue
					
				Next
				
				;--------------------
				; Bordure du bouton 
				;--------------------
				If NG\state = 0
					NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx , NG\Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB )
				Else
					NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx , NG\Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB , 2 )
				EndIf
					
			
			EndIf
						
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
							
			; si le texte est ombré
			If NG\ombre
					
				Color NG_Font_or , NG_Font_og , NG_Font_ob
				 
				NG_DisplayText( NG\Label$ , Xref + Xref2 + decalage + 2 , Yref + Yref2 + decalage + 2 , 50 )
				
			EndIf
				
			; le vrai texte
			Color NG\r , NG\g , NG\b
			
			px = Xref + Xref2 + decalage - 10
			py = Yref + Yref2 + decalage + 20
			
			Select NG\typ$
				
				Case "UP"
				
					DrawImage NG_Arrow_UP , px , py 	
				
				Case "DOWN"
					
					DrawImage NG_Arrow_DOWN , px , py 	
					
				Case "LEFT"
					
					DrawImage NG_Arrow_LEFT , px , py 
					
				Case "RIGHT"
			
					DrawImage NG_Arrow_RIGHT , px , py 
				
				Default 
			
					NG_DisplayText( NG\Label$ , Xref + Xref2 + decalage , Yref + Yref2 + decalage , 50 )
			
			End Select

			
			;NG_DisplayText( NG\Label$ , Xref + Xref2 + decalage , Yref + Yref2 + decalage , 50 )
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
		
		EndIf
		
	Next

End Function


;---------------------------------
; Dessine le bouton spécifié
;---------------------------------
; Très utile pour les gadgets à
; boutons ; combobox etc
;---------------------------------
Function NG_DrawThisButton ( ID , Xref , Yref )

	NG.NG_Button = Object.NG_Button(Id)
	
	If NG <> Null
	
		;-----------------------------
		; par défaut....
		;-----------------------------
		NG\State = 0
		
		; ---------------------------------------------------------------
		; On cherche les coordonnées X/Y de la fenetre comme réfenrence
		; ---------------------------------------------------------------
			
		;Xref = Xref + NG\Px - NG\Tx 
		;Yref = Yref + NG\Py
			
		;-----------------------------
		; Pour dessiner et calculer
		;-----------------------------
		SetFont NG_NormalFont
			
		; 2ndes réfenrences pour les textes, pour centrer en X et en Y
		Xref2 = ( (NG\Tx)/2 ) - ( StringWidth(NG\Label$) / 2 )
		Yref2 = ( (NG\Ty)/2 ) - ( FontHeight () / 2 ) - 20 ; -20pour compenser le systeme de coords de Blitz
			
		;--------------------------------------
		; Interactions
		;--------------------------------------
			
		; S'il s'agit bien d'un bouton de la fenetre active
		;-----------------------------------------------------
		; et si on est pas déjà en train de bouger un doseur
		;-----------------------------------------------------
		If NG_OnDoseur = False And NG_DragIcon = False  And NG_OnSliderSaveId = True And NG_OnOldValue = False And NG_OnCottage = False; And NG_OldMenuOpenId = -1 And NG_ComboBoxOpenID = -1 
		
				bouton = NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty , 1 )
					
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
					NG_OnSpecialGadget = True
				EndIf
				
													
				If bouton = 1 And NG\Immuable = False
												
					; on le place en mode appuyé mais en attente
					NG\State = -1
							
				; mouseUp
				Else If bouton = 99 
								
					NG\State = 1
							
				Else
							
					NG\State = 0
								
				EndIf
				
							
		EndIf
			
		;--------------------------------
		; On dessine d'abord le bouton
		;------------------------------------------
		; Que si le bouton lui meme doit l'etre
		;------------------------------------------
			
		;--------------------------------
		; Gestion des décalages
		;--------------------------------
		If NG\State = 0
			decalage = 0
		Else
			decalage = 1
		EndIf
			
		If NG\box
			
			; si normal
			If NG\state = 0
				
				;-----------------------------------------------
				; degradé : on démarre des valeurs par défaut
				;-----------------------------------------------
				red_start#   = NG_IB_Re : red_end#   = NG_IB_Rs
				green_start# = NG_IB_Ge : green_end# = NG_IB_Gs
				blue_start#  = NG_IB_Be : blue_end#  = NG_IB_Bs
				
				; décalage du à l'appui du bouton
				decalage = 0
				
			Else
				
				;-----------------------------------------------
				; degradé : on démarre des valeurs par défaut
				;-----------------------------------------------
				red_start#   = NG_IB_Rs : red_end#   = NG_IB_Re
				green_start# = NG_IB_Gs : green_end# = NG_IB_Ge
				blue_start#  = NG_IB_Bs : blue_end#  = NG_IB_Be
					
				; décalage du à l'appui du bouton
				decalage = 1
				
			EndIf
				
			; création du dégradé
			Dir = 1
				
			GradientRed#   = ( Red_end - Red_start ) / NG\Ty
			GradientGreen# = ( Green_end - Green_start ) / NG\Ty
			GradientBlue#  = ( Blue_end - Blue_start ) / NG\Ty
								
			For Y = Yref To ( Yref + NG\Ty-1 ) 
			
				Color Red_start , Green_start , Blue_start
				
				Rect Xref+1 , Y , NG\Tx - 2 , 1
					
				Red_start = Red_start + GradientRed
				Green_start = Green_start + GradientGreen
				Blue_start = Blue_start + GradientBlue
					
			Next
				
			;--------------------
			; Bordure du bouton 
			;--------------------
			If NG\state = 0
				NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx , NG\Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB )
			Else
				NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx , NG\Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB , 2 )
			EndIf
					
			
		EndIf
						
		;--------------------------------
		; On peut dessiner les textes
		;--------------------------------
							
		; si le texte est ombré
		If NG\ombre
					
			Color NG_Font_or , NG_Font_og , NG_Font_ob
							 
			NG_DisplayText( NG\Label$ , Xref + Xref2 + decalage + 2 , Yref + Yref2 + decalage + 2 , 50 )
				
		EndIf
				
		; le vrai texte
		Color NG\r , NG\g , NG\b
		
		px = Xref + Xref2 + decalage - 10
		py = Yref + Yref2 + decalage + 20
		
		Select NG\typ$
			
			Case "UP"
			
				DrawImage NG_Arrow_UP , px , py 	
			
			Case "DOWN"
				
				DrawImage NG_Arrow_DOWN , px , py 	
				
			Case "LEFT"
				
				DrawImage NG_Arrow_LEFT , px , py 
				
			Case "RIGHT"
		
				DrawImage NG_Arrow_RIGHT , px , py 
			
			Default 
		
				NG_DisplayText( NG\Label$ , Xref + Xref2 + decalage , Yref + Yref2 + decalage , 50 )
		
		End Select
		
					
	EndIf
		
	
End Function


;----------------------------------------------
; Dessine les boutons clycliques
;----------------------------------------------

Function NG_DrawCycleButton ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_CycleButton = Each NG_CycleButton
	
	
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True
		
			;-----------------------------
			; par défaut....
			;-----------------------------
			NG\State = 0

		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			
			; 2ndes réfenrences pour les textes, pour centrer en X et en Y
			Xref2 = ( (NG\Tx)/2 ) - ( StringWidth( NG\Label$[ NG\current_label ] ) / 2 )
			Yref2 = ( (NG\Ty)/2 ) - FontHeight ()/2 - 20 ; -20pour compenser le systeme de coords de Blitz
			
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			; S'il s'agit bien d'un bouton de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
				bouton = NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty , 1 )
			
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
					NG_OnGadget = True
				EndIf
											
				If bouton = 1
									
					; on le place en mode appuyé mais en attente
					NG\State = -1
				
				; mouseUp
				Else If bouton = 99 
					
					NG\State = 1
					
					;-----------------------------------
					; On change de label (le suivant)
					;-----------------------------------
					NG\current_label = NG\current_label + 1
					
					If NG\current_label > NG\max_label
						NG\current_label = 1
					EndIf
				
				Else
				
					NG\State = 0
					
				EndIf

			
			EndIf
			
			;--------------------------------
			; On dessine d'abord le bouton
			;------------------------------------------
			; Que si le bouton lui meme doit l'etre
			;------------------------------------------
			
			;--------------------------------
			; Gestion des décalages
			;--------------------------------
			If NG\State = 0
				decalage = 0
			Else
				decalage = 1
			EndIf
			
			If NG\box
			
				; si normal
				If NG\state = 0
				
					;-----------------------------------------------
					; degradé : on démarre des valeurs par défaut
					;-----------------------------------------------
					red_start#   = NG_IB_Re : red_end#   = NG_IB_Rs
					green_start# = NG_IB_Ge : green_end# = NG_IB_Gs
					blue_start#  = NG_IB_Be : blue_end#  = NG_IB_Bs
					
					; décalage du à l'appui du bouton
					decalage = 0
				
				Else
				
					;-----------------------------------------------
					; degradé : on démarre des valeurs par défaut
					;-----------------------------------------------
					red_start#   = NG_IB_Rs : red_end#   = NG_IB_Re
					green_start# = NG_IB_Gs : green_end# = NG_IB_Ge
					blue_start#  = NG_IB_Bs : blue_end#  = NG_IB_Be
					
					; décalage du à l'appui du bouton
					decalage = 1
				
				EndIf
				
				; création du dégradé
				Dir = 1
				
				GradientRed#   = ( Red_end - Red_start ) / NG\Ty
				GradientGreen# = ( Green_end - Green_start ) / NG\Ty
				GradientBlue#  = ( Blue_end - Blue_start ) / NG\Ty
								
				For Y = Yref To ( Yref + NG\Ty-1 ) 
				
					Color Red_start , Green_start , Blue_start
					
					Rect Xref+1 , Y , NG\Tx - 2 , 1
					
					Red_start = Red_start + GradientRed
					Green_start = Green_start + GradientGreen
					Blue_start = Blue_start + GradientBlue
					
				Next
				
				;--------------------
				; Bordure du bouton 
				;--------------------
				If NG\state = 0
					NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx , NG\Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB )
				Else
					NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx , NG\Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB , 2 )
				EndIf
			
			EndIf
						
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
			SetFont NG_NormalFont
				
			; si le texte est ombré
			If NG\ombre
					
				Color NG_Font_or , NG_Font_og , NG_Font_ob
				 
				NG_DisplayText( NG\Label$[ NG\current_label ] , Xref + Xref2 + decalage + 2 , Yref + Yref2 + decalage + 2 , 50 )
				
			EndIf
				
			; le vrai texte
			Color NG\r , NG\g , NG\b 
			NG_DisplayText( NG\Label$[ NG\current_label ] , Xref + Xref2 + decalage , Yref + Yref2 + decalage , 50 )
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
		
		EndIf
		
	Next

End Function


;---------------------------------
; Dessine une Value
;---------------------------------
Function NG_DrawValue ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Value = Each NG_Value
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			; attention : si un prélabel à été spécifié, alors ces coordonnées indiquent ceux du prelabel
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
						
			;-------------------------------------
			; On peut dessiner la zone de l'input
			;-------------------------------------
			SetFont NG_NormalFont
			
			;-----------------------
			; s'il y a un prelabel
			;-----------------------
			Xzone = Xref + StringWidth ( NG\PreLabel$ ) + 10 ; mou
			
			;-------------------------------------------------------
			; integer ou pas
			;-------------------------------------------------------
			If NG\integer
			
				NG\value_int = Int (NG\value)
				NG\min_int   = Int (NG\min)
				NG\max_int   = Int (NG\max)
			
			EndIf
			
			
			;----------------------
			; on dessine la zone
			;----------------------
			Color NG_Input_R , NG_Input_G , NG_Input_B
									
			;------------------------
			; taille de l'input box
			;------------------------
			If NG\integer
			
				longueur = StringWidth ( NG\max_int ) + 40
				hauteur  = StringHeight ( NG\max_int ) + 5 
			
			Else
						
				longueur = StringWidth ( NG\max ) + 40
				hauteur  = StringHeight ( NG\max ) + 5 ; (pour le cercle régleur)
		
			EndIf
			
			Rect Xzone , Yref , longueur , hauteur , 1
			
			;---------------------
			; bordure de la zone
			;---------------------
			NG_DrawTrueBordure ( Xzone-1 , Yref-1 , longueur + 2 , hauteur + 2 , 0 , 0 , 0 , True , True   )
						
			;--------------------------------------
			; Interactions
			;--------------------------------------
			;-----------------------------------------------------
			; S'il s'agit bien d'un input de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
				bouton = NG_MouseZone ( Xzone , Yref , longueur - 40 , hauteur+2 , 1 )
				
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xzone , Yref , longueur , hauteur )
					NG_OnGadget = True
				EndIf
			
				;---------------------------------------------------------------
				; edition du champ au clavier, à la maniere habituelle
				;---------------------------------------------------------------
				If bouton = 99 And NG\editable And NG_TF_Id = -1 And active_beta = True
					
					;-----------------------------------------
					; le Text Focus est activé sur cet input
					;-----------------------------------------
					NG_TF_Id  = NG\Id
					NG_TF_On  = True
					
					;------------------------------------------------------------
					; on positionne le TF à la fin de la valeur entrée
					;------------------------------------------------------------
					If NG\integer
					
						NG_TF_pos = Len ( NG\Value_int )
					
					Else
					
						NG_TF_pos = Len ( NG\Value )
					
					EndIf
								
				EndIf
				
				;------------------------------------------------
				; on ne corrige que le texte input actuel
				;------------------------------------------------
				If NG_TF_Id = NG\Id
				
					If NG\integer
						
						val$ = NG_UpdateInputLabel$ ( NG\Value_int , False )
						
						NG\value_int = Int (val$)
						
						NG\value = val$
						
					Else
					
						val$ = NG_UpdateInputLabel$ ( NG\Value , False )
												
						NG\value = val$
																	
					EndIf
						
				EndIf
				
				;------------------------------------------
				; editable à la souris
				;------------------------------------------
				;bouton = NG_MouseZone ( Xzone + longueur - 40 , Yref , 40 , hauteur+2 , 1 )
				bouton = NG_MouseZone ( Xzone , Yref , longueur , hauteur+2 , 1 )
			
				If bouton = 1
					
					;-----------------------------------------------------
					; modification sur ce value en cours...
					;-----------------------------------------------------
					If NG_OnValue = False
					
						NG_OnValueID = NG\ID
						
						NG_OnValue = True
						
					EndIf
																			
				EndIf
				
				;----------------------------------------------
				; edition ici
				;----------------------------------------------
				If NG_OnValue = True
				
					NG_MYS = MouseYSpeed()
				
					If NG\integer
				
						NG\value_int = NG\value_int - ( NG_MYS * NG\pas )
						
						NG\value = NG\value - ( NG_MYS * NG\pas )
					
					Else
					
						NG\value = NG\value - ( NG_MYS * NG\pas )
					
					EndIf				
				
				EndIf

			
			EndIf
			
			
			;-----------------------------------------------------
			; limite de la valeur
			;-----------------------------------------------------
			If NG\integer
			
				If NG\value_int < NG\min Then NG\value_int = NG\min_int
				If NG\value_int > NG\max Then NG\value_int = NG\max_int
						
			Else
			
				If NG\value < NG\min Then NG\value = NG\min
				If NG\value > NG\max Then NG\value = NG\max
			
			EndIf
							
			;-------------------------------------------------------
			; viewport pour ne dessiner que dans la zone input box
			;-------------------------------------------------------
			Viewport Xzone + 1 , Yref + 1 , longueur - 20 - 2 , hauteur - 2
			
			;-------------------------------------------------------
			; On ajoute le TF au texte si on a cliqué sur l'input
			;-------------------------------------------------------
			taille_texte = Len ( NG\Value )
				
				If NG\Id = NG_TF_Id
				
					;--------------------------------------------------------------------
					; on ne l'ajoute que s'il est dans sa phase visible du clignotement
					;--------------------------------------------------------------------
					If NG_TF_Timer >= 0
						
						TF$ = NG_TF$
					
					Else
					
						TF$ = ""					
					
					EndIf
					
					If NG\integer
					
						texte$ = Int ( Left$ ( NG\value_int , NG_Tf_Pos ) )
						
						texte$ = Int (texte$) +TF$
					
					Else
					
						texte$ = Left$ ( NG\value , NG_Tf_Pos ) + TF$
					
					EndIf
							
				Else
				
					If NG\integer
				
						texte$ = NG\value_int
					
					Else
					
						texte$ = NG\value
					
					EndIf
				
				EndIf
				
			;----------------------------------------------
			; On dessine la valeur entrée dans l'input box
			;----------------------------------------------
			SetFont NG_NormalFont
			
			Color NG_IT_R , NG_IT_G , NG_IT_B
			
			;--------------------------------------
			; Affichage de la valeur
			;--------------------------------------
			NG_DisplayText( texte$ , Xzone + 5 + 3 , Yref+2 - FontHeight() - 3 , 100 )
						
			;-------------------------------------------------------
			; viewport pour le bouton value
			;-------------------------------------------------------
			Viewport Xzone + 1 , Yref + 1 , longueur - 2 , hauteur - 2
						
			;------------------------------------------------
			; affichage du bouton value
			;------------------------------------------------
			Imx = ImageWidth ( NG_ValueB )
			Imy = ImageHeight ( NG_ValueB )
			
			DrawImage NG_ValueB , Xzone + longueur - Imx - 2 , Yref ;+ Imy
			
			
			;-------------------------------------------
			; On peut dessiner les textes du pré-label
			;-------------------------------------------
			SetFont (NG_NormalFont)
			
			;-----------
			; viewport
			;-----------
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			
			;------------------------
			; d'abord le prelabel
			;------------------------
			If NG\PreLabel$ <> ""
			
				;------------------------
				; si le texte est ombré
				;------------------------
				If NG\ombre
					
					Color NG_Font_or , NG_Font_og , NG_Font_ob
					
					NG_DisplayText( NG\PreLabel$ , Xref+2 , Yref+2 - FontHeight() - 3 , 100 )
				
				EndIf
				
				;-----------------
				; le vrai texte
				;-----------------
				Color NG_Font_R , NG_Font_G , NG_Font_B

				NG_DisplayText( NG\PreLabel$ , Xref , Yref -  FontHeight() - 3 , 100 )
			
			EndIf
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				St = StringWidth ( NG\PreLabel$ )
			
				If NG_MouseZone ( Xref , Yref , st + longueur , hauteur )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf

		
		EndIf
	Next

End Function



;---------------------------------
; Dessine les inputs
;---------------------------------
Function NG_DrawInput ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Input = Each NG_Input
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			; attention : si un prélabel à été spécifié, alors ces coordonnées indiquent ceux du prelabel
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
						
			;-------------------------------------
			; On peut dessiner la zone de l'input
			;-------------------------------------
			SetFont NG_NormalFont
			
			; s'il y a un prelabel
			
			lon = StringWidth ( NG\PreLabel$ )
			
			; limites pour le selecteur de fichier
			If NG\Id = NG_FSpath
			
				If lon > 137 Then lon = 137
			
			EndIf
			
			Xzone = Xref + lon ; mou
			
			; on dessine la zone
			Color NG_Input_R , NG_Input_G , NG_Input_B
			
			; taille de l'input box
			longueur = NG\Tx 
			hauteur  = NG\Ty
			
			Rect Xzone , Yref , longueur , hauteur , 1
			
			; bordure de la zone
			NG_DrawTrueBordure ( Xzone-1 , Yref-1 , longueur + 2 , hauteur + 2 , 0 , 0 , 0 , True , True )
			
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			; S'il s'agit bien d'un input de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False  And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
				bouton = NG_MouseZone ( Xzone , Yref , longueur , hauteur , 1 )
				
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xzone , Yref , longueur , hauteur )
					NG_OnGadget = True
				EndIf
			
				If bouton = 99 And NG\editable And NG_TF_Id = -1
					
					; le Text Focus est activé sur cet input
					NG_TF_Id  = NG\Id
					NG_TF_On  = True
					
					; on positionne le TF en fonction des coordonnés X du clic
					
					; d'abord on calcule le nombre de caractères visibles
					If Len ( NG\Label$ ) > 0
					
						caracteres = Len ( NG\Label$ ) / StringWidth ( NG\Label$ )
					
					Else
					
						caracteres = 0
					
					EndIf
					
					caracteres = caracteres * longueur
					
					; ensuite on cherche la position du clic X relative à l'input box
					ClicX = NG_MouseClicX - Xzone
					
					; et on cherche à quel caractere visible ca correspond
					soluce = ClicX / longueur
					soluce = soluce * caracteres
					
					; le nombre de caracteres entre le TF et la fin du texte est :
					restants = caracteres - soluce
					
					; donc la position est :
					NG_TF_pos = Len (NG\Label$) - restants
					
					; debug : pour le moment ce sera ca :
					NG_TF_pos = Len (NG\Label$)
								
				EndIf
				
				;------------------------------------------------
				; on ne corrige que le texte input actuel
				;------------------------------------------------
				If NG_TF_Id = NG\Id
					NG\Label$ = NG_UpdateInputLabel$ ( NG\Label$ , NG\alpha )				
				EndIf
			
			EndIf
			
			;----------------------------------------------
			; On dessine le texte entré dans l'input box
			;----------------------------------------------
			SetFont NG_NormalFont
			
			Color NG_IT_R , NG_IT_G , NG_IT_B
			
			; viewport pour ne dessiner que dans la zone input box
			Viewport Xzone + 1 , Yref + 1 , longueur - 2 , hauteur - 2
			
				;----------------------------------------------
				; ICI : on dessine le texte selon sa longueur
				;----------------------------------------------
				
				; -22 pour compenser le systeme de coordonnées des textes Blitz
			
				;----------------------------------------------
				; si le texte est PLUS long que la zone texte
				;----------------------------------------------
				
				;----------------------------------------------
				; nous ne sommes pas en mode edition
				;----------------------------------------------
				If NG\Id <> NG_TF_Id
				
					If StringWidth(NG\Label$) >= longueur
					
						;---------------------------------------------------------
						; ecart : valeur à mettre à jour selon la font utilisée
						;---------------------------------------------------------
						ecart = 60
						
						decalage = ( StringWidth(NG\Label$) - longueur )
						
						If decalage < 0 Then decalage = 0
						
						depart_x = (Xzone + 2) - decalage - ecart
					
					;----------------------------------------------
					; si le texte est MOINS long que la zone texte
					;----------------------------------------------
					Else
					
						depart_x = Xzone + 2
					
					EndIf
				
				;-----------------------------------------------------------------------
				; nous sommes en mode edition : c'est le TF qui determine l'affichage
				;-----------------------------------------------------------------------				
				Else
				
					essai$ = Left$ ( NG\Label$ , NG_TF_pos ) + TF$
				
					If StringWidth( essai$ ) >= longueur
					
						;---------------------------------------------------------
						; ecart : valeur à mettre à jour selon la font utilisée
						;---------------------------------------------------------
						ecart = 60
						
						decalage = ( StringWidth ( essai$ ) - longueur )
						
						If decalage < 0 Then decalage = 0
						
						depart_x = (Xzone + 2) - decalage - ecart
					
					;----------------------------------------------
					; si le texte est MOINS long que la zone texte
					;----------------------------------------------
					Else
					
						depart_x = Xzone + 2
					
					EndIf				
				
				EndIf			
				
				;-------------------------------------------------------
				; On ajoute le TF au texte si on a cliqué sur l'input
				;-------------------------------------------------------
				taille_texte = Len ( NG\Label$ )
				
				If NG\Id = NG_TF_Id
				
					; on ne l'ajoute que s'il est dans sa phase visible du clignotement
					If NG_TF_Timer >= 0
						
						TF$ = NG_TF$
					
					Else
					
						TF$ = ""					
					
					EndIf
					
					texte$ = Left$ ( NG\Label$ , NG_TF_pos ) + TF$ + Mid$ ( NG\Label$ , NG_TF_pos + 1 )
								
				Else
				
					texte$ = NG\Label$
				
				EndIf
				
				;--------------------------------------
				; Affichage final du label (pfiou !)
				;--------------------------------------
				NG_DisplayText( texte$ , depart_x + 3 , Yref+2 - FontHeight() - 3 , NG\Chars )
			
			;-------------------------------------------
			; On peut dessiner les textes du pré-label
			;-------------------------------------------
			SetFont (NG_NormalFont)
			
			Color NG_Font_R , NG_Font_G , NG_Font_B
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
						
			; d'abord le prelabel
			If NG\PreLabel$ <> ""
			
				; si le texte est ombré
				If NG\ombre
					
					Color NG_Font_or , NG_Font_og , NG_Font_ob
					
					; -22 pour compenser le systeme de coordonnées des textes Blitz
					NG_DisplayText( NG\PreLabel$ , Xref+2 , Yref+2 - FontHeight() - 3 , NG\Chars )
				
				EndIf
				
				; le vrai texte
				Color NG\r , NG\g , NG\b
				; -22 pour compenser le systeme de coordonnées des textes Blitz
				NG_DisplayText( NG\PreLabel$ , Xref , Yref -  FontHeight() - 3 , NG\Chars )
			
			EndIf
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				St = StringWidth ( NG\PreLabel$ )
			
				If NG_MouseZone ( Xref , Yref , st + longueur , hauteur )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf

		
		EndIf
	Next

End Function


;---------------------------------
; Dessine les inputs Multilignes
;---------------------------------
Function NG_DrawMultiInput ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_MultiInput = Each NG_MultiInput
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			; attention : si un prélabel à été spécifié, alors ces coordonnées indiquent ceux du prelabel
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
						
			;-------------------------------------
			; On peut dessiner la zone de l'input
			;-------------------------------------
			SetFont NG_NormalFont
			
			; s'il y a un prelabel
			Xzone = Xref + StringWidth ( NG\PreLabel$ ) + 5 ; mou
			
			; on dessine la zone
			Color NG_Input_R , NG_Input_G , NG_Input_B
			
			; taille de l'input box
			longueur = NG\Tx 
			hauteur  = NG\Ty
			
			Rect Xzone , Yref , longueur , hauteur , 1
			
			; bordure de la zone
			NG_DrawTrueBordure ( Xzone-1 , Yref-1 , longueur + 2 , hauteur + 2 , 0 , 0 , 0 , True , True )
			
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			; S'il s'agit bien d'un bouton de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
				bouton = NG_MouseZone ( Xzone , Yref , longueur , hauteur , 1 )
				
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xzone , Yref , longueur + 22 , hauteur )
					NG_OnGadget = True
				EndIf
			
				If bouton = 99 And NG\editable And NG_TF_Id <> NG\Id ;= -1
					
					; le Text Focus est activé sur cet input
					NG_TF_Id  = NG\Id
					NG_TF_On  = True
					
					If NG_TF_Id = NG\ID
					
						; on positionne le TF en fonction des coordonnés X du clic
						
						; d'abord on calcule le nombre de caractères visibles
						If Len ( NG\Label$ ) > 0
						
							caracteres = Len ( NG\Label$ ) / StringWidth ( NG\Label$ )
						
						Else
						
							caracteres = 0
						
						EndIf
	
						caracteres = caracteres * longueur
						
						; ensuite on cherche la position du clic X relative à l'input box
						ClicX = NG_MouseClicX - Xzone
						
						; et on cherche à quel caractere visible ca correspond
						soluce = ClicX / longueur
						soluce = soluce * caracteres
						
						; le nombre de caracteres entre le TF et la fin du texte est :
						restants = caracteres - soluce
						
						; donc la position est :
						NG_TF_pos = Len (NG\Label$) - restants
						
						NG_TF_pos = Len (NG\Label$)
					
					EndIf

									
				EndIf
				
				;------------------------------------------------
				; on ne corrige que le texte input actuel
				;------------------------------------------------
				If NG_TF_Id = NG\Id
				
					;--------------------------------------------
					; nombre de caracteres par pages
					;--------------------------------------------
					cpp = NG\lpp * NG\chars
				
					NG\Label$ = NG_UpdateInputLabel$ ( NG\Label$ , NG\alpha , NG\chars-4 , cpp )				
				
				
				EndIf

			
			EndIf
			
			;----------------------------------------------
			; On dessine le texte entrée dans l'input box
			;----------------------------------------------
			SetFont NG_NormalFont
			
			Color NG_IT_R , NG_IT_G , NG_IT_B
			
			;-----------------------------------------------------------------------------------
			; Il faut trouver, avec la longueur de l'input, le nombre de caractères par ligne
			;-----------------------------------------------------------------------------------
			
			;------------------------------
			; longueur du texte en pixels
			;------------------------------
			longueur_texte = StringWidth ( NG\Label$ )
			
			;------------------
			; nombre de lignes
			;------------------
			
			nombre_lignes_float# = Float (longueur_texte) / Float ( longueur - 20 ) ; pour etre sur de ne pas couper les textes
			
			nombre_ligne_int% = Int (nombre_lignes_float#)
			
			If nombre_lignes_float# > nombre_ligne_int%
			
				nombre_lignes = nombre_ligne_int% + 1
			
			Else
			
				nombre_lignes = nombre_ligne_int%
			
			EndIf
				
			NG\lpp = nombre_lignes
			
			;-----------------------------------------------------------------------------------
			; y a t-il des lignes supplémentaires dues aux caractères spéciaux 10 et && ?
			;-----------------------------------------------------------------------------------
			; analyse...
			;---------------------
			;new_nombre_lignes = nombre_lignes			
			
			For i=1 To Len ( NG\Label$ )
			
				;---------------------------------
				; 10 rajoute une ligne !
				;---------------------------------
				If Mid$ ( NG\Label$ , i , 1 ) = Chr$(10)
				
					new_nombre_lignes = new_nombre_lignes + 1
				
				EndIf
				
				;---------------------------------
				; && rajoutent une ligne !
				;---------------------------------
				If Mid$ ( NG\Label$ , i , 1 ) = "&"
				
					If Mid$ ( NG\Label$ , i+1 , 1 ) = "&"
				
						new_nombre_lignes = new_nombre_lignes + 1
					
					EndIf
				
				EndIf
						
			Next
						
			
			;---------------------------------------------
			; nombre de caracteres par ligne en pixels
			;---------------------------------------------
			If nombre_lignes = 0 Then nombre_lignes = 1
			
			longueur_ligne_float# = Float (longueur_texte) / Float ( nombre_lignes )
			
			longueur_ligne_int% = Int (longueur_ligne_float#)
			
			If longueur_ligne_float# > longueur_ligne_int%
			
				longueur_ligne = longueur_ligne_int% + 1
			
			Else
			
				longueur_ligne = longueur_ligne_int%
			
			EndIf

			;----------------------------------------------
			; longueur de ligne en caracteres
			;----------------------------------------------
			longueur_ligne_2 = Len (NG\Label$) / nombre_lignes
			
			NG\Chars = longueur_ligne_2; / 1.1;1.3
			
			;---------------------------------------------------------
			; viewport pour ne dessiner que dans la zone input box
			;---------------------------------------------------------
			Viewport Xzone + 1 , Yref , longueur + 22 , hauteur
			
				;----------------------------------------------
				; ICI : on dessine le texte selon sa longueur
				;----------------------------------------------
				Yref_texte = Yref + 2 - ( FontHeight() - 3 )
							
				;----------------------------------------------
				; quelle est la hauteur du texte ?
				;----------------------------------------------
				texte_hauteur = StringHeight (NG\Label$) + 3
				
				;----------------------------------------------
				; donc la hauteur totale du texte est :
				;----------------------------------------------
				
				;-----------------------------------
				; interlignes ?
				;-----------------------------------
				il = 0
				
				rajout = StringHeight (NG\Label$) * new_nombre_lignes
				
				;---------------------------------------------------
				; nous ne sommes pas en mode edition
				;---------------------------------------------------
				If NG\ID <> NG_TF_Id
				
					hauteur_totale = ( ( texte_hauteur + il ) * nombre_lignes ) + rajout ; + 0 pour les interlignes
					
					;----------------------------------------------------
					; si cette hauteur dépasse celle de la zone texte
					;----------------------------------------------------
					If hauteur_totale >= hauteur
					
						;------------------------------------------------
						; de combien elle la dépasse ?
						;------------------------------------------------
						reste = hauteur_totale - hauteur
					
						ecart = StringHeight ( NG\Label$ ) / 2
					
						depart_y = Yref_texte - reste - ( ecart * nombre_lignes )
						
						depart_y = depart_y + NG\Location
						
						
					
					;----------------------------------------------
					; si le texte est MOINS long que la zone texte
					;----------------------------------------------
					Else
					
						depart_y = Yref_texte
					
					EndIf
				
				;------------------------------------------------------
				; nous sommes en mode edition
				;------------------------------------------------------
				Else
				
					;------------------------------
					; longueur du texte en pixels
					;------------------------------
					essai$ = Left$ ( NG\Label$ , NG_TF_pos ) + TF$
					
					;------------------
					; nombre de lignes
					;------------------
					longueur_texte = StringWidth( essai$ )
					nombre_lignes = longueur_texte / longueur
				
					hauteur_totale = ( ( texte_hauteur + il ) * nombre_lignes ) + rajout ; + 0 pour les interlignes
					
					;----------------------------------------------------
					; si cette hauteur dépasse celle de la zone texte
					;----------------------------------------------------
					If hauteur_totale >= hauteur
					
						;------------------------------------------------
						; de combien elle la dépasse ?
						;------------------------------------------------
						reste = hauteur_totale - hauteur
					
						ecart = FontHeight()/2
					
						depart_y = Yref_texte - reste - ( ecart * nombre_lignes )
					
					;----------------------------------------------
					; si le texte est MOINS long que la zone texte
					;----------------------------------------------
					Else
					
						depart_y = Yref_texte
					
					EndIf
				
				EndIf				
				
				;-------------------------------------------------------
				; On ajoute le TF au texte si on a cliqué sur l'input
				;-------------------------------------------------------
				taille_texte = Len ( NG\Label$ )
				
				If NG\Id = NG_TF_Id
				
					; on ne l'ajoute que s'il est dans sa phase visible du clignotement
					If NG_TF_Timer >= 0
						
						TF$ = NG_TF$
					
					Else
					
						TF$ = ""					
					
					EndIf
					
					m=0;1
					
					If m=1
					
						texte_debut$ = Left$ ( NG\Label$ , NG_TF_pos )
						
						texte$ = texte_debut$ + TF$
						
						texte_fin$ = Mid$ ( NG\Label$ , NG_TF_pos + 1 )
						
						texte$ = texte$ + texte_fin$
						
					Else
					
						texte$ = Left$ ( NG\Label$ , NG_TF_pos ) + TF$ + Mid$ ( NG\Label$ , NG_TF_pos + 1 )
					
					EndIf
								
				Else
				
					texte$ = NG\Label$
				
				EndIf
				
				;--------------------------------------
				; Affichage final du label (pfiou !)
				;--------------------------------------
				;NG_DisplayText ( texte$ , Xzone + 2 + 3 , depart_y - 3 , NG\Chars )
				NG_DisplayTextFast ( texte$ , Xzone + 2 + 3 , depart_y - 3 , NG\Chars , Yref , Yref + NG\Ty )

										
			;------------------------------------------------------------
			; ici on dessine les bouton up/down et le slider
			;------------------------------------------------------------
			NG_DrawThisButton ( NG\Bprev , Xzone + longueur + 1 , Yref )
			NG_DrawThisButton ( NG\Bnext , Xzone + longueur + 1 , Yref + hauteur - 20 )
			
			;------------------------------------------------
			; cliqué dessus ?
			;------------------------------------------------
			mov = 20
			
			If NG_ReturnButtonDown ( NG\Bprev )
			
				If depart_y < Yref_texte			
			
					NG\Location = NG\Location + mov
				
				EndIf
			
			EndIf			
			
			If NG_ReturnButtonDown ( NG\Bnext )
			
				If depart_y > Yref_texte - reste - (ecart*nombre_lignes)
				
					NG\Location = NG\Location - mov
				
				EndIf
			
			EndIf
			
			;-------------------------
			; Zone de l'ascenseur
			;-------------------------
			Color NG_Input_R , NG_Input_G , NG_Input_B
					
			Rect Xzone + longueur + 1 , Yref + 20 , 19 , hauteur - 20 - 20 , True
				
			;--------------------------------
			; hauteur et dessin du slider
			;--------------------------------
			parcours_beta = hauteur -20 -20
			
			If hauteur_totale > parcours_beta
				
				hauteur_slider = ( Float (parcours_beta) / Float (hauteur_totale) ) * parcours_beta
								
			Else
				
				hauteur_slider = parcours_beta
				
			EndIf
							
			NG_SetButtonSize ( NG\Bslider , 20 , hauteur_slider )
			
			If NG\slidX = 0 Then NG\slidX = Xzone + longueur
			If NG\slidY = 0 Then NG\slidY = Yref + 20
			
			;-------------------------------------------
			; bouger le slider en cliquant dessus
			;-------------------------------------------
			
			; par défaut
			NG\editSlid = False
			
			If NG_MouseClic1 = False Then NG\SaveSlid = False
			
			If NG_ReturnButtonDown ( NG\Bslider ) Or NG\SaveSlid = True
			
				NG\SaveSlid = True
			
				my = MouseY()
							
				NG\slidY = my - (hauteur_slider/2)
				
				NG\editSlid = True
				
				; annulation
				If MouseY() < Yref + 20 Or MouseY() > Yref + hauteur - 20 - (hauteur_slider/2)
				
					NG\editSlid = False
					NG\SaveSlid = False
				
				EndIf
			
			Else
			
				NG\SaveSlid = False
				
				;----------------------------------------------
				; si l'on clique dans la zone blanche
				;----------------------------------------------
				If NG_MouseZone ( Xzone + longueur + 1 , Yref + 20 , 19 , hauteur - 20 - 20 , 1 )
				
					my = MouseY()
							
					NG\slidY = my - (hauteur_slider/2)
					
					NG\editSlid = True
				
				EndIf
			
			EndIf
			
			;-------------------------------------------
			; limites du slider
			;-------------------------------------------
			If NG\slidY < Yref + 20
			
				NG\slidY = Yref + 20
			
			EndIf
			
			If NG\slidY > Yref + hauteur - 20 - hauteur_slider
			
				NG\slidY = Yref + hauteur - 20 - hauteur_slider
			
			EndIf	
			
			;----------------------------------------------------------------------------
			; calcul de la longueur totale du texte et reposition par rapport au slider
			;----------------------------------------------------------------------------
			parcours = Yref_texte - ( Yref_texte - reste - (ecart*nombre_lignes) )
			parcours_slider = hauteur - 20 - 20 - hauteur_slider
			
			If NG\editSlid = True; And ( hauteur < hauteur_totale )
			
				inc# = Float (parcours) / Float (parcours_slider)
				
				posY = NG\SlidY - ( Yref + 20 )
				
				NG\Location = parcours - (posY * inc)
			
			;-----------------------------------------------------------------------------
			; ou reposition du slider par rapport au texte...
			;-----------------------------------------------------------------------------
			Else If NG\editSlid = False
			
				pos_texte = Yref_texte - depart_y
				
				If pos_texte < 0 Then pos_texte = 0
				
				posYslider# = Float (parcours_slider) / Float (parcours)
				
				posYslider# = posYslider# * pos_texte
				
				NG\slidY = Yref + 20 + posYslider
				
			EndIf
			
			;----------------------------------------------------------------------------
			; Calcul de la Location Maximum (top)
			;----------------------------------------------------------------------------
			inc# = Float (parcours) / Float (parcours_slider)
			posY = 0	
			NG\MaxLocation = parcours; - (posY * inc)
			
			;RuntimeError 
			
			;-------------------------------------------------------------------
			; faut-il le mettre au top ?
			;-------------------------------------------------------------------
			If NG\ToTop = True
			
				NG\ToTop = False
				
				NG\Location = NG\MaxLocation
				
				NG\SlidY = 0
			
			EndIf				
			
			
			;-------------------------------------------
			; limites du slider
			;-------------------------------------------
			If bob=1
			If NG\slidY < Yref + 20
			
				NG\slidY = Yref + 20
			
			EndIf
			
			If NG\slidY > Yref + hauteur - 20 - hauteur_slider
				NG\slidY = Yref + hauteur - 20 - hauteur_slider
			EndIf			
			EndIf
			
			
			NG_DrawThisButton ( NG\Bslider , Xzone + longueur + 1 , NG\slidY )
			
					
			;-------------------------------------------
			; On peut dessiner les textes du pré-label
			;-------------------------------------------
			SetFont NG_NormalFont
			Color NG_Font_R , NG_Font_G , NG_Font_B
					
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
						
			; d'abord le prelabel
			If NG\PreLabel$ <> ""
			
				; si le texte est ombré
				If NG\ombre
					
					Color NG_Font_or , NG_Font_og , NG_Font_ob
					
					; -22 pour compenser le systeme de coordonnées des textes Blitz
					NG_DisplayText( NG\PreLabel$ , Xref+2 , Yref+2 - FontHeight() - 3 , NG\Chars )
				
				EndIf
				
				; le vrai texte
				Color NG\r , NG\g , NG\b
				; -22 pour compenser le systeme de coordonnées des textes Blitz
				NG_DisplayText( NG\PreLabel$ , Xref , Yref  - FontHeight() - 3 , NG\Chars )
			
			EndIf
			
			;------------------------------------------------------
			;
			;------------------------------------------------------
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				St = StringWidth ( NG\PreLabel$ )
			
				If NG_MouseZone ( Xref , Yref , st + longueur , hauteur )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf

		
		EndIf
	Next

End Function


;---------------------------------
; Dessine les Combo
;---------------------------------
Function NG_DrawCombo ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Combo = Each NG_Combo
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True
	
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			; attention : si un prélabel à été spécifié, alors ces coordonnées indiquent ceux du prelabel
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; taille du combo
			longueur = NG\Tx 
			hauteur  = NG\Ty
			
			; s'il y a un prelabel
			Xzone = Xref + StringWidth ( NG\PreLabel$ ) ; mou
			
			
			; viewport limité à la fenetre
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
								
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			;-----------------------------------------------------
			; S'il s'agit bien d'un input de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
				bouton = NG_MouseZone ( Xzone , Yref , longueur , hauteur , 1 )
				
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , longueur , hauteur )
					NG_OnGadget = True
				EndIf
				
				;--------------------------
				; appui dans la comboBox
				;--------------------------
				If bouton = 99 Or NG_ReturnButton (NG\button)
				
					If NG_ComboBoxOpen = False And NG_ComboBoxOpenID = - 1
					
						NG\index = 1
					
						NG_ComboBoxOpen = True
						
						NG_ComboBoxOpenID = NG\ID
						
						Insert NG After Last NG_Combo
						
						FlushMouse
						
					EndIf
						
				EndIf
												
			EndIf
			
			;-------------------------------------
			; On peut dessiner la zone combo
			;-------------------------------------
			SetFont NG_NormalFont
			
			;-----------------------------
			; on dessine la zone combo
			;-----------------------------
			Color NG_Input_R , NG_Input_G , NG_Input_B
			
			;---------------------------------------------
			; affichage combo box fermée
			;---------------------------------------------
			
			; taille du combo
			; --> on garde la taille définie précedemment <--
				
			Rect Xzone , Yref , longueur , hauteur , 1
				
			; bordure de la zone
			NG_DrawTrueBordure ( Xzone-1 , Yref-1 , longueur + 2 , hauteur + 2 , 0 , 0 , 0 , True , True   )
			
			
			
					
			;----------------------------------------------
			; affichage combo box ouverte
			;----------------------------------------------
			If NG_OldComboBoxOpen = True And NG_ComboBoxOpenID = NG\ID
			
				;----------------------------------------------
				; zone protégée
				;----------------------------------------------
				true_hauteur = hauteur * NG\lignmax
				
				If NG_MouseZone ( Xzone + longueur - 19 , Yref + 20 , 20 , true_hauteur , False )
				
					NG_ComboBoxOnButton = True
														
				EndIf
				
				;-------------------------------------------------------
				; a t-on appuyé sur un bouton up/down du combo ?
				;-------------------------------------------------------
				If NG_ReturnButtonDown ( NG\Bprev )
					
					NG\index = NG\index - 1
																					
				Else If NG_ReturnButton ( NG\Bnext )
	
					NG\index = NG\index + 1
					
				EndIf
										
				;-------------------------------------------------------
				; ou par molette de souris
				;-------------------------------------------------------
				NG\index = NG\index + ( MouseZSpeed() * -1)
				
				
				;---------------------------------------
				; limites
				;---------------------------------------
				max = NG\max_item - NG\lignMax + 1
							
				If NG\index < 1 Then NG\index = 1
				If NG\index > max Then NG\index = max
						
				;-------------------------------------------------------
				; on peut tout dessiner selon les nouvelles données
				;-------------------------------------------------------
				Color NG_Input_R , NG_Input_G , NG_Input_B
			
				; la combo box ouverte peut dépasser la fenetre
				Viewport 0 , 0 , GraphicsWidth() , GraphicsHeight()
						
				; taille du combo
				longueur = NG\Tx 
				hauteur  = NG\Ty * ( NG\lignmax + 1 ) ;( NG\max_item + 1 ) ; +1 pour la 1ere ligne obligatoire
				
				Rect Xzone , Yref , longueur , hauteur , 1
				
				; bordure de la zone
				NG_DrawTrueBordure ( Xzone-1 , Yref-1 , longueur + 2 , hauteur + 2 , 0 , 0 , 0 , True , True   )

				;---------------------------------------------
				; affichage de la barre de selection
				;---------------------------------------------
				testY = Yref
				testY = testY + FontHeight() + 3
								
				;---------------------------------------------
				; reposition par rapport à l'index
				;---------------------------------------------
				;testY = testY + ( NG\index * ( FontHeight() + 3 ) )				
				
				selection = 0
				
				For i = 1 To NG\lignmax 
				
					;--------------------------------------------
					; si la souris survole une ligne
					;--------------------------------------------
					If NG_MouseZone ( Xzone , testY , longueur-20 , FontHeight() )
					
						Color NG_Sel_R , NG_Sel_G , NG_Sel_B
			
						Rect Xzone + 2 , testY , NG\Tx -4 -20 , FontHeight() + 1 , True
					
						selection = i 
					
						Exit
					
					EndIf
			
					testY = testY + FontHeight() + 3
			
				Next
				
				;-------------------------------------------------
				; on dessine les boutons
				;-------------------------------------------------
				If NG\max_item > NG\lignMax
									
					NG_DrawThisButton ( NG\Bprev , Xzone+longueur-19 , Yref+hauteur-40 )
					NG_DrawThisButton ( NG\Bnext , Xzone+longueur-19 , Yref+hauteur-20 )
	
				EndIf
								
			
			EndIf
			


			;--------------------------------------
			; Affichage du texte de la selection
			;--------------------------------------
			; viewport limité à la fenetre
			;--------------------------------------
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
						
			SetFont NG_NormalFont
			Color NG_IT_R , NG_IT_G , NG_IT_B
			
			NG_DisplayText( NG\selection$ , Xzone + 5 , Yref - FontHeight() - 3 , NG\Chars )
			
			;------------------------------------
			; Dessine le bouton de la combo box
			;------------------------------------
			bx = Xzone + longueur - 19
			
			NG_DrawThisButton ( NG\button , bx , Yref )
			
			;-------------------------------------------------
			; Affichage de la liste de la combo Box ouverte
			;-------------------------------------------------
			If NG_OldComboBoxOpen = True And NG_ComboBoxOpenID = NG\ID
			
				; viewport limité à la combo box
				Viewport Xzone , Yref , longueur , hauteur 
			
				departY = Yref
				
				;---------------------------------------------------------
				; * 2 = 2, comme ca à l'index 1 : 2 - 1 = 1
				;---------------------------------------------------------
				departY = departY + ( 1 * ( FontHeight() + 3 ) )
				
				;---------------------------------------------
				; reposition par rapport à l'index
				;---------------------------------------------
				departY = departY - ( NG\index * ( FontHeight() + 3 ) )	
			
				;-------------------------------------------------------------
				; ligne de séparation entre la 1ère ligne et les suivantes
				;-------------------------------------------------------------
				Rect XZone + 5 , NG\Ty , XZone + NG\Tx - 10 , 1
			
				numero = 1 - (NG\index-1)
				
				; viewport limité à la combo box développée
				Viewport Xzone , Yref+( FontHeight() + 3 ) , longueur , hauteur - ( FontHeight() + 3 )
			
				For Item.NG_ComboITem = Each NG_ComboItem
				
					If Item\ComboID = NG\ID
					
						; si on est sur la selection, la couleur est l'inverse (negatif)
						If numero = selection
						
							Color NG_Font_R , NG_Font_G , NG_Font_B
							
							;--------------------------------------
							; Validation du choix si on clique
							;--------------------------------------
							If NG_MouseClic1
							
								NG\selection$ = Item\Label$
							
							EndIf
						
						Else							
						
							Color NG_IT_R , NG_IT_G , NG_IT_B
						
						EndIf
					
						NG_DisplayText ( Item\Label$ , Xzone + 5 , departY , NG\Chars )
					
						departY = departY + FontHeight() + 3
					
						numero = numero + 1
					
					EndIf
				
				Next			
			
			EndIf
			
			
			;-------------------------------------------
			; On peut dessiner les textes du pré-label
			;-------------------------------------------
			; viewport limité à la fenetre
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			
			SetFont NG_NormalFont
			Color NG_Font_R , NG_Font_G , NG_Font_B
									
			
			; d'abord le prelabel
			If NG\PreLabel$ <> ""
			
				; si le texte est ombré
				If NG\ombre
					
					Color NG_Font_or , NG_Font_og , NG_Font_ob
					
					NG_DisplayText( NG\PreLabel$ , Xref+2 , Yref+2 - FontHeight() - 3 , NG\Chars )
				
				EndIf
				
				; le vrai texte
				Color NG\r , NG\g , NG\b
				NG_DisplayText( NG\PreLabel$ , Xref , Yref - FontHeight() - 3 , NG\Chars )
			
			EndIf
						
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				St = StringWidth ( NG\PreLabel$ )
			
				If NG_MouseZone ( Xref , Yref , longueur + st , hauteur )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf

		
		EndIf
	
	Next

End Function

;--------------------------
; Dessine les CheckBox
;---------------------------

Function NG_DrawCheckBox ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_CheckBox = Each NG_CheckBox
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			; pas de décalage de coordonnées dus aux (pre)labels puisqu'ils sont apres la checkbox et non avant (cf inputs)
			
			;--------------------------------
			; On peut dessiner la checkbox
			;--------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			
			; pour la zone d'interactivité puisqu'il faudra prendre en compte le label aussi
			; sinon on devriendrait fou s'il fallait cliquer sur le petit carré de la check box seulement
			longueur = NG\Tx + StringWidth(NG\Label$) + 20  ; pour le mou
			hauteur  = FontHeight()
			
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			; S'il s'agit bien d'un bouton de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
				bouton = NG_MouseZone ( Xref , Yref , longueur , hauteur , 1 )
				
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , longueur , hauteur )
					NG_OnGadget = True
				EndIf
			
				; mouseUp
				If bouton = 99 
					
					NG\Check = 1 - NG\Check
				
				Else
				
					;NG\Check = NG\Check
					
				EndIf
			
			EndIf
			
			
			Color NG_Input_R , NG_Input_G , NG_Input_B
				
			Rect Xref , Yref , NG\Tx , NG\Ty , 1
			
			; bordure de la checkbox
			NG_DrawTrueBordure ( Xref-1 , Yref-1 , NG\Tx+2 , NG\Ty+2 , 0 , 0 , 0 , True , True   ) 
			
			; la checkbox est-elle cochée ?
			If NG\Check
			
				DrawImage NG_Check , Xref+1 , Yref+1
			
			EndIf
			
			
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
			SetFont NG_NormalFont
			
			If NG\Label$ <> ""
			
				Xref2 = Xref + 20  ; mou
				Yref2 = Yref - 26 ; -22 pour compenser le systeme de coordonnées des textes Blitz
			
				; si le texte est ombré
				If NG\ombre
					
					Color NG_Font_or , NG_Font_og , NG_Font_ob
										
					NG_DisplayText( NG\Label$ , Xref2 + 2 , Yref2 + 2 , 100 )
								
				EndIf
				
				; le vrai texte
				Color NG\r , NG\g , NG\b
				; -22 pour compenser le systeme de coordonnées des textes Blitz
				NG_DisplayText( NG\Label$ , Xref2 , Yref2 + 2 , 100 )
			
			EndIf
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , longueur , hauteur )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
			
		EndIf
	Next

End Function

;--------------------------
; Dessine les IconViewer
;---------------------------

Function NG_DrawIconViewer ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_IconViewer = Each NG_IconViewer
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True
	
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			; pas de décalage de coordonnées dus aux (pre)labels puisqu'ils sont apres la checkbox et non avant (cf inputs)
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			;--------------------------------------
			; retablissement du slider
			;--------------------------------------
			NG_SetButtonImmuable ( NG\Bslider , True )			
					
			;---------------------
			; On dessine la zone
			;---------------------
			Color NG_Input_R , NG_Input_G , NG_Input_B
			
			SetFont NG_NormalFont
			
			Fheight = FontHeight()
			
			;--------------------------------------------------------------------------------------
			; Si c'est un iconviewer horizontal (style "liste" de l'explorateur windows)
			;--------------------------------------------------------------------------------------
			If NG\style = 1
				
				;-------------------------------------------
				; si les labels doivent etre affichés
				;-------------------------------------------
				If NG\label = True
				
					wd = FontWidth()
					ht = FontHeight()
					
					;-----------------------------------------
					; restrictions pour le File Selector
					;-----------------------------------------
					If NG_FSopen = True
					
						wd=25
						ht=16			
					
					EndIf	
					
					If NG\icons_disp = True
			
						iTX = ( NG\IconSize + wd * 8 )
						iTY = ( NG\IconSize + 10 )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
					
					Else If NG\icons_disp = False
					
						iTX = ( wd * 8 )
						iTY = ( ht + 5 )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
					
					EndIf
			
				Else
				
					If NG\icons_disp = True
				
						iTX = ( NG\IconSize + 10 )
						iTY = ( NG\IconSize + 10 )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
					
					Else If NG\icons_disp = False
					
						iTX = ( 10 )
						iTY = ( 10 )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
					
					EndIf
					
							
				EndIf
			
			;----------------------------------------------------------------------------------------------
			; Si c'est un iconviewer vertical (style "grandes icones" de l'explorateur windows)
			;----------------------------------------------------------------------------------------------
			Else
			
				;-------------------------------------------
				; si les labels doivent etre affichés
				;-------------------------------------------
				If NG\label = True
				
					If NG\icons_disp = True
			
						iTX = ( NG\IconSize + FontWidth() * 4 )
						iTY = ( NG\IconSize + 15 + Fheight )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
						
						If NG\Id = NG_FSshortcut
						
							Tx = 132
						
						EndIf
			
					Else If NG\icons_disp = False
					
						iTX = ( FontWidth() * 4 )
						iTY = ( 15 + FontHeight() )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
					
					EndIf
			
				Else
				
					If NG\icons_disp = True
				
						iTX = ( NG\IconSize + 10 )
						iTY = ( NG\IconSize + 15 )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
					
					Else If NG\icons_disp = False
					
						iTX = ( 10 )
						iTY = ( 15 )
						Tx = NG\IconX * iTX	
						Ty = NG\IconY * iTY
					
					EndIf
				
				EndIf
			
			EndIf
			
			;-----------------------------------------------
			; on définit le nombre de caracteres visibles
			;-----------------------------------------------
			Lmax = 25
			
			;------------
			; viewport
			;------------
			Viewport Xref-1 , Yref-1 , Tx+2 , Ty+2
			
			
			;-------------------------
			; Zone de l'iconViewer
			;-------------------------
			Rect Xref , Yref , Tx , Ty , True
			
			;-------------
			; bordure
			;-------------
			NG_DrawTrueBordure ( Xref-1 , Yref-1 , Tx+2 , Ty+2 , 0 , 0 , 0 , True , True   )
			
			;-----------------------------------------------
			; vérifie si l'on est dans la zone des icones
			;-----------------------------------------------
			If NG_MouseZone ( Xref , Yref , Tx , Ty ) And ParentWindow\ID = NG_ActiveWindow And NG_OnValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
				If NG_OnDoseur = False
			
					NG_OnIconViewer = NG\ID
		
				EndIf
			
			EndIf
			
			
			;------------------------------------
			; On dessine les icones
			;------------------------------------
			
			; nombre d'icones traités
			Icones = 0
			Icones_limite = 0
			
			; depart
			departX = Xref + 5
			departY = Yref + 5
			
			departX_saved = departX
			departY_saved = departY
			
			;--------------------------------------------------
			; recitifications de l'index & nombre de lignes
			;--------------------------------------------------
			If NG\index > 1
		
				;----------------------------------
				; Viewer Horizontal
				;----------------------------------
				If NG\Style = 1
				
					departX = departX - ( iTX * ( NG\index - 1 ) )
					
					departX_saved = departX
				
				Else If NG\style = 2
				
					departY = departY - ( iTY * ( NG\index - 1 ) )
					
					departY_saved = departY
				
				EndIf
		
			EndIf
			
			;--------------------------
			; selection d'une icone
			;--------------------------
			bouton = NG_MouseZone ( Xref , Yref , Tx , Ty , True )
				
			;-----------------------------------------
			; si on a bien cliqué dans la zone
			;-----------------------------------------
			If bouton = 1 And NG_DragIcon = False And NG_OnOldValue = False	
			
				If NG_FSopen = False 
				
					NG\sel_x = NG_MouseX
					NG\sel_y = NG_MouseY
						
					NG\sel_ID = -1
				
				Else If NG_Fsopen = True And ParentWindow\Id = NG_FS
				
					NG\sel_x = NG_MouseX
					NG\sel_y = NG_MouseY
						
					NG\sel_ID = -1
				
				EndIf				
									
			EndIf
			
			;------------------------------------------------------------------------------------
			; On dessine tout ce qui se trouve dans la zone icones (icones, labels, selections)
			;------------------------------------------------------------------------------------
			;NG\sel_done = False
			
			For Icon.NG_Icon = Each NG_Icon
							
				If Icon\ViewerID = NG\ID
				
					;---------------------------------------
					; taille du label de l'icone
					;---------------------------------------
					
					;------------------------------------------
					; on tronque le nom au cas où il dépasse
					;------------------------------------------
					icone$ = Mid$ ( Icon\Label$ , 1 , Lmax)

					LabelTX = StringWidth ( icone$ )
					LabelTY = StringHeight ( icone$ )
										
					;-----------------------------------------------------------
					; On retrouve l'icone selectionnée
					;-----------------------------------------------------------
					; Si on a déjà cliqué
					;-----------------------------------------------------------
					If NG\sel_x > - 1 And NG\sel_y > - 1
					
						;-------------------------------------------------
						; doit on activer le mode Drag and Drop d'icones
						;-------------------------------------------------
						If NG_MouseOldClic1 And NG_MouseClic1 And NG_OnIconViewer = NG\ID
						
							If NG\drag
						
								NG_DragIcon = True
							
							EndIf
						
						EndIf
					
						;------------------------------------------------
						; enregistrement de la selection
						;------------------------------------------------						
						If NG\sel_ID = -1
						
							ecart_x = NG\sel_x - departX
							ecart_y = NG\sel_y - departY
							
							;-------------------------------
							; horizontal
							;-------------------------------
							If NG\style = 1
							
								If NG\icons_disp = True
							
									If ecart_x >= 0 And ecart_x < NG\IconSize + LabelTX	
											
										If ecart_y >=0 And ecart_y < NG\IconSize
									
											NG\sel_ID = Icon\ID
										
										EndIf
									
									EndIf
								
								Else If NG\icons_disp = False
								
									If ecart_x >= 0 And ecart_x < LabelTX	
											
										If ecart_y >=0 And ecart_y < LabelTY
									
											NG\sel_ID = Icon\ID
										
										EndIf
									
									EndIf								
								
								EndIf
							
							;-------------------------------
							; vertical
							;-------------------------------
							Else If NG\style = 2
							
								If NG\icons_disp = True
							
									newTX = LabelTX - NG\IconSize
								
									If ecart_x >= 0 And ecart_x < NG\IconSize + newTX
											
										If ecart_y >=0 And ecart_y < NG\IconSize + LabelTY
									
											NG\sel_ID = Icon\ID
										
										EndIf
									
									EndIf
								
								Else If NG\icons_disp = False
								
									newTX = LabelTX; - NG\IconSize
								
									If ecart_x >= 0 And ecart_x <  LabelTX;newTX; + NG\IconSize
											
										If ecart_y >= 0 And ecart_y < LabelTY + 5 ;+ NG\IconSize
									
											NG\sel_ID = Icon\ID
										
										EndIf
									
									EndIf
								
								EndIf
							
							EndIf
								
						EndIf
						
						;-------------------------------------
						; si on est dans la zone d'une icone
						;-------------------------------------					
						If NG\sel_ID = Icon\ID
						
							Color NG_Sel_R , NG_Sel_G , NG_Sel_B
							
							newTX = LabelTX - NG\IconSize
							
							;--------------------------------------
							; Horizontal
							;--------------------------------------
							If NG\style = 1
							
								If NG\icons_disp = True
									
									Rect departX - 2 , departY - 5 , NG\IconSize + LabelTX + 19 , NG\IconSize + 10 , True
							
								Else If NG\icons_disp = False
								
									Rect departX - 2 , departY - 1 , LabelTX + 19 , LabelTY + 3 , True
															
								EndIf
							
							;--------------------------------------
							; vertical
							;--------------------------------------
							Else If NG\style = 2
							
								If NG\icons_disp = True
							
									Rect departX - 2 , departY - 5 , NG\IconSize + newTX + 19 , NG\IconSize + LabelTY + 20 , True
							
								Else If NG\icons_disp = False
								 
									Rect departX - 2 , departY + 8 , LabelTX + 19 , LabelTY + 3 , True
																																
								EndIf
							
							EndIf
							
						EndIf
												
								
					EndIf
															
					;----------------------------------------------
					; dessins des icones
					;----------------------------------------------
					If NG\icons_disp = True
					
						If departX > Xref-1 And departX < Xref-1 + Tx+2
						
							If departY > Yref-1 And departY < Yref-1 + Ty+2
						
								DrawImage Icon\Image , departX , departY
					
							EndIf
						
						EndIf
					
					EndIf
										
					Icones = Icones + 1
					Icones_limite = Icones_limite + 1
					
					;------------------------------------------------------
					; préparation pour écrire les labels si c'est demandé
					;------------------------------------------------------
					
					;---------------------------------------------------------------------
					; couleur normale ou inversée (negatif) si il s'agit de la séléction
					;---------------------------------------------------------------------
					If NG\sel_ID = Icon\ID
					
						Color NG_Font_R , NG_Font_G , NG_Font_B
					
					Else					
						
						Color NG_IT_R , NG_IT_G , NG_IT_B
	
					EndIf
													
					;------------------------------------
					; si c'est un IconViewer horizontal
					;------------------------------------
					If NG\Style = 1
						
						;-----------------------------------------------
						; on écrit les labels si c'est autorisé
						;-----------------------------------------------
						If NG\Label = True
						
							If NG\icons_disp = True
							
								NG_DisplayText( Icone$ , departX + NG\IconSize + 5 , departY - FontHeight() - 3 , 1000 )
							
							Else If NG\icons_disp = False
							
								NG_DisplayText( Icone$ , departX + 5 , departY - FontHeight() - 3 , 1000 )
							
							EndIf

												
						EndIf
										
						;------------------------------------------
						; on ajoute d'abord verticalement
						;------------------------------------------
						departY = departY + iTY
											
						;------------------------------------------
						; si c'est la limite verticale
						;------------------------------------------
						If Icones_limite >= NG\IconY
						
							departX = departX + iTX
							departY = departY_saved
							Icones_limite = 0
							
						EndIf
														
					;------------------------------------
					; si c'est un IconViewer vertical
					;------------------------------------
					Else If NG\Style = 2
					
						;-----------------------------------------------
						; on écrit les labels si c'est autorisé
						;-----------------------------------------------
						If NG\Label = True
						
							If NG\icons_disp = True
							
								icone$ = Mid$ ( Icon\Label$ , 1 , Lmax)
								NG_DisplayText( Icone , departX , departY + (NG\IconSize+5) - FontHeight() , 1000 , True )
						
							Else If NG\icons_disp = False
							
								icone$ = Mid$ ( Icon\Label$ , 1 , Lmax)
								NG_DisplayText( Icone , departX , departY + 5 - FontHeight() , 1000 , True )
							
							EndIf
						
						EndIf
						
						;------------------------------------------
						; on ajoute d'abord horizontalement
						;------------------------------------------
						departX = departX + iTX
											
						;------------------------------------------
						; si c'est la limite verticale
						;------------------------------------------
						If Icones_limite >= NG\IconX
						
							departY = departY + iTY
							departX = departX_saved
							Icones_limite = 0
													
						EndIf
						
					EndIf
																
				EndIf
			
			Next
			
			;----------------------------------------------------
			; Y a t-il plus d'icones que la zone visible ?
			;----------------------------------------------------
			Plus_Icones = 0
			
			If Icones > NG\IconX * NG\IconY
			
				Plus_Icones = True
					
			EndIf
			
			;---------------------------------------------------
			; Nombre de lignes ou de colones
			;---------------------------------------------------
			LC = 0
			
			If NG\Style = 1
			
				LC = NG\Icons / NG\IconY
			
			Else If NG\Style = 2
			
				LC = NG\Icons / NG\IconX
			
			EndIf
			
						
			;-----------------------------------------------
			; viewport fenetre
			;-----------------------------------------------
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			
			;-----------------------------------------------
			; Est-ce necessaire de dessiner l'ascenseur ?
			;-----------------------------------------------
			If Plus_Icones > 0
				
				;-----------------------------
				; viewer horizontal
				;-----------------------------
				If NG\Style = 1
				
					;-------------------------
					; Zone de l'ascenseur
					;-------------------------
					Color NG_Input_R , NG_Input_G , NG_Input_B
					
					Rect Xref , Yref + Ty , Tx , 20 , True
					
					;-------------
					; bordure
					;-------------
					NG_DrawTrueBordure ( Xref , Yref + Ty , Tx , 20 )
					
					;---------------------------------------
					; Les boutons
					;---------------------------------------					
					NG_DrawThisButton ( NG\Bprev , Xref - 1 , Yref + Ty )
					NG_DrawThisButton ( NG\Bnext , Xref + Tx - 19 , Yref + Ty )
						
					;--------------------------------------------------------
					; Redimensionnement du slider selon le nombre d'icones
					;--------------------------------------------------------
					Btx = TX - 20  - 20
					Bty = 20
						
					Offset_x = Btx / LC
					Offset_y = 20
						
					index = NG\Index - 1
					
					NG_SetButtonSize ( NG\Bslider , Offset_x , Offset_y )
					
					;-------------------------------------------
					; position du slider
					;-------------------------------------------
					
					dX = Xref + 20 + (Offset_x * index)
					dY = Yref + Ty
					
					NG_DrawThisButton ( NG\Bslider , dX , dY )
					
				; Viewer vertical
				;------------------------------
				Else
				
					;-------------------------
					; Zone de l'ascenseur
					;-------------------------
					Color NG_Input_R , NG_Input_G , NG_Input_B
					
					Rect Xref + Tx , Yref - 1 , 20 , Ty , True
					
					;-------------
					; bordure
					;-------------
					NG_DrawTrueBordure ( Xref + Tx , Yref - 1 , 20 , Ty )
					
					;---------------------------------------
					; Les boutons
					;---------------------------------------
					NG_DrawThisButton ( NG\Bprev , Xref + Tx  , Yref - 1 )
					NG_DrawThisButton ( NG\Bnext , Xref + Tx  , Yref + Ty - 19 )
						
					;--------------------------------------------------------
					; Redimensionnement du slider selon le nombre d'icones
					;--------------------------------------------------------
					Btx = 20
					Bty = TY - 20 - 20 ; -31
									
					Offset_x = 20
					Offset_y = Bty / LC
						
					index = NG\Index - 1
						
					NG_SetButtonSize ( NG\Bslider , Offset_x , Offset_y )
					
					;-------------------------------------------
					; position du slider
					;-------------------------------------------
					
					dX = Xref + Tx
					dY = Yref + 20 + ( (Offset_y * index) )
													
					NG_DrawThisButton ( NG\Bslider , dX , dY )					
									
				EndIf
			
			EndIf
			
			;-----------------------------------------------
			; Interactions
			;-----------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_MoveWindow = False And NG_ComboBoxOpenID = -1
				
				;-------------------------------------
				; reglage drag/drog du slider
				;-------------------------------------
				
				;----------------------
				; horizontal
				;----------------------
				If NG\style = 1
					
					bouton = NG_MouseZone ( Xref + 20 , Yref + Ty , Btx , Bty , 1 )
				
				;----------------------
				; vertical
				;----------------------
				Else If NG\style = 2
				
					bouton = NG_MouseZone ( Xref + Tx , Yref + 20 , Btx , Bty , 1 )
					
				EndIf
				
				
				If bouton = 1
				
					NG_OnGadget = True
					
					If NG_OnSliderSaveId = True
					
						NG_OnSlider = True
						
						NG_OnSliderId = NG\Bslider
						
					EndIf
						
					NG_OnSliderSaveId = False
					
				Else
					
					NG_OnSlider = False
						
					NG_OnSliderId = -1
					NG_OnSliderSaveId = True
					
				EndIf
				
				
				If NG_OnSlider = False
			
					;--------------------
					; boutons fleches
					;-------------------- 
					If NG_ReturnButton ( NG\Bprev ) Then NG\index = NG\index - 1
					If NG_ReturnButton ( NG\Bnext ) Then NG\index = NG\index + 1
						
					If NG\index < 1
						
						NG\index = 1				
						
					Else If NG\index > LC
						
						NG\index = LC
							
					EndIf
					
					;---------------------------------
					;
					;---------------------------------
				
				
				Else If NG_OnSlider = True

					;--------------------------------------------
					; il faut qu'on soit sur le même doseur
					;--------------------------------------------		
					If NG_OnSliderId = NG\Bslider
					
						;--------------------------------
						; horizontal......
						;--------------------------------
						If NG\Style = 1
						
							;End
											
							;-----------------------
							; on est sur un doseur
							;-----------------------
							NG_OnSlider = True
									
							;--------------------------------------------------
							; position du clic par rapport au debut du slider
							;--------------------------------------------------				
							PosX = NG_MouseX - ( Xref+20 )
									
							;-----------------------------
							; conversion
							;-----------------------------
							temp# = Float (LC) / Btx
									
							index = temp * PosX
							
							If index < 1 Then index = 1
									
							NG\index = index									
									
						;------------------
						; Vertical.....
						;------------------		
						Else If NG\Style = 2
						
							;-----------------------
							; on est sur un doseur
							;-----------------------
							NG_OnSlider = True
									
							;--------------------------------------------------
							; position du clic par rapport au debut du slider
							;--------------------------------------------------				
							PosY = MouseY() - ( Yref+20 )
							
							;-----------------------------
							; conversion
							;-----------------------------
							temp# = Float (LC) / Bty
							
							index = temp * PosY
							
							If index < 1 Then index = 1
							
							NG\index = index
							
					EndIf
																				
					EndIf
					
				EndIf
															
			EndIf			
			
			;-----------------------------------------------------
			; S'il s'agit bien d'un bouton de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1
			
				bouton = NG_MouseZone ( Xref , Yref , Tx , Ty , 1 )
				
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , Tx , Ty )
					NG_OnGadget = True
				EndIf
			
				; ici				
			
			EndIf
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , Tx , Ty )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
		
			Color 0,0,0
					
		EndIf
		
		
		
	Next

End Function

;------------------------
; Dessine les Doseurs
;------------------------

Function NG_DrawDoseur ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Doseur = Each NG_Doseur
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			; ces references sont pour le texte, le doseur, lui, sera un peu en dessous
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			
			; ici la position Y du doseur
			Yref2 = Yref + 50
			
			; taille Y du doseur
			Ty = 3
			
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			; S'il s'agit bien d'un bouton de la fenetre active
			If ( ParentWindow\Active Or ParentWindow\Background = 0 )
			
				zoneY_debut = Yref2 - 20
				zoneY_fin   = Ty     + 40
			
				;----------------------------------------------------------------
				; si c'est la premiere fois ; seulement si on est dans la zone
				;----------------------------------------------------------------
				If NG_OnDoseur = False  And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False ; And NG_MoveWindow = False; And NG_MouseOldClic1 = False
				
					bouton = NG_MouseZone ( Xref , zoneY_debut , NG\Tx , zoneY_fin , 1 )
					
					;--------------------------------------------------------
					; on est bien sur un gadget (si c'est la première fois)
					;--------------------------------------------------------
					If NG_MouseZone ( Xref , zoneY_debut , NG\Tx , zoneY_fin )
					
						NG_OnGadget = True
					
						On_me = True
					
					EndIf
													
				;----------------------------------------------------------------
				; si c'est la 2ème fois ; seulement si on clique toujours...
				;----------------------------------------------------------------
				Else If NG_DragIcon = False And NG_OnValue = False And NG_OldMenuOpenId = -1
				
					bouton = NG_MouseClic1
					
					;--------------------------------------------------------
					; on vérifie quand même qu'on est dans le range Xs/Xe
					;--------------------------------------------------------
					;If NG_MouseZone ( Xref , 0 , NG\Tx , GraphicsWidth() )
					;	NG_OnGadget = True
					;EndIf
					
					NG_OnGadget = True
					
					On_me = True
													
				EndIf
				
				
			
				; mouseDown
				;-------------------------------------------------------------------
				; On conserve le clic si on est déjà en train ed bouger un doseur
				;-------------------------------------------------------------------
				If bouton = 1 And NG_OldOnGadget = True And NG_ComboBoxOpenID = -1 And On_me = True And NG_OnSlider = False
				
					If NG_OnDoseurSaveId = True
						NG_OnDoseurId = NG\Id
					EndIf
					
					NG_OnDoseurSaveId = False
								
					;--------------------------------------------
					; il faut qu'on soit sur le même doseur
					;--------------------------------------------		
					If NG_OnDoseurId = NG\Id
									
						; on est sur un doseur
						;-----------------------
						NG_OnDoseur = True
						
						ValeurX = NG_MouseX - Xref
						
						;-------------------------------------------------------
						; calcul de la valeur totale comprise entre min et max
						;-------------------------------------------------------
						total# = NG\max# - NG\min#
						
						;------------------------------------------------------
						; cette valeur divisée par la longueur du gadget NG\Tx
						;------------------------------------------------------
						unite# = (total# / NG\Tx)
						
						;----------------------------------
						; puis multipliée par la valeur X
						;----------------------------------
						NG\val# = (unite# * ValeurX)
						
						If NG\min# > 0
						
							NG\val# = NG\val# + NG\min#
						
						EndIf
						
						;----------------------------------------
						; prend en compte les valeurs negatives
						;----------------------------------------
						If NG\min# < 0
						
							NG\val# = NG\val# + NG\min#	
						
						EndIf
						
						;----------
						; limites
						;----------
						If NG\val# < NG\min# Then NG\val# = NG\min#
						If NG\val# > NG\max# Then NG\val# = NG\max#
					
					EndIf
				
				;-----------------------------------------
				; sinon on le perd la capture du doseur
				;-----------------------------------------					
				Else
				
					NG_OnDoseur = False
					
					NG_OnDoseurId = -1
					NG_OnDoseurSaveId = True			
									
				EndIf
			
				
			
			
			EndIf

			
			;-------------------------------
			; On dessine d'abord le Doseur
			;-------------------------------
			
			;-------------
			; bordure
			;-------------
			NG_DrawBordure ( Xref , Yref2 , NG\Tx , Ty , NG_Font_r , NG_Font_g , NG_Font_b )
						
			;------------------------------
			; On peut dessiner le texte
			;------------------------------
			SetFont NG_NormalFont
			
			;---------------------------------------------
			; si l'affichage de l'information est activé
			;---------------------------------------------
			If NG\info
				
				;-------------------------
				; c'est un doseur entier
				;-------------------------
				If NG\integer
				
					valeur# = Int (NG\val)
					
				Else
				
					valeur# = NG\val#
					
				EndIf
				
				info$ = "    :   " + valeur#
				
			EndIf			
			
			;------------------------
			; si le texte est ombré
			;------------------------
			If NG\ombre
				
				Color NG_Font_or , NG_Font_og , NG_Font_ob
				 
				NG_DisplayText( NG\Label$ + info$ , Xref+2 , Yref+2 , 100 )
							
			EndIf
			
			;----------------
			; le vrai texte
			;----------------
			Color NG\r , NG\g , NG\b
			NG_DisplayText( NG\Label$ + info$ , Xref , Yref , 100 )
			
			;----------------------------------------
			; Enfin on dessine le curseur du doseur
			;----------------------------------------
			
			;-------------------------------------------------------
			; calcul de la valeur totale comprise entre min et max
			;-------------------------------------------------------
			total# = NG\max# - NG\min#
			
			;------------------------------------------------------------
			; calcul du nombre de positions par rapport à la taille Tx
			;------------------------------------------------------------
			positions# = Float (NG\Tx) / total#
			
			;------------------------------------
			; calcul de la position du curseur
			;------------------------------------
			cursor_pos# = positions# * NG\val#
			
			;----------------------------
			; compensentation negative
			;----------------------------
			negatives# = positions# * Abs(NG\min#)
			
			;-----------------------------------------
			; correction des cas particuliers
			;-----------------------------------------			
			If NG\min# < 0
			
				cursor_pos# = cursor_pos# + negatives#
			
			Else If NG\min# > 0
			
				cursor_pos# = cursor_pos# - negatives#
		
			EndIf
			
			;------------------------------------------------------
			; faut-il tout mettre en chiffre entier (integer=1) ?
			;------------------------------------------------------
			If NG\integer
			
				;-------------------------------------
				; version fluide
				;-------------------------------------
				;cursor_pos_int = Int cursor_pos#			
				
				;-------------------------------------
				; version saccadée, plus appropriée
				;-------------------------------------
				cursor_pos_int = ( positions# * Int(NG\val#) ) - negatives#
				
				;------------------------------------------------------------
				; on place le curseur en Y un peu plus haut pour le centrer
				;------------------------------------------------------------
				DrawImage NG_Doseurs , Xref + cursor_pos_int , Yref2 - 9
			
			;-----------
			; ou alors
			;-----------
			Else If NG\integer = 0
			
				;------------------------------------------------------------
				; on place le curseur en Y un peu plus haut pour le centrer
				;------------------------------------------------------------
				DrawImage NG_Doseurs , Xref + cursor_pos# , Yref2 - 9
			
			EndIf
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , NG\Tx , 50 + Ty )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
		
		EndIf
	Next

End Function


;---------------------------------
; Dessine les Boards
;---------------------------------
Function NG_DrawBoard ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Board = Each NG_Board
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide = 0 And TabOK = True And NG\show = True
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;-----------------------------------------------------------------
			; Dessine le Board
			;-----------------------------------------------------------------
			
			;------------------------------
			; Chaque Colonne
			;------------------------------
			
			;------------------------------
			; point de départ
			;------------------------------
			dx = Xref
			
			;-----------------------
			; font/color
			;-----------------------
			SetFont (NG_NormalFont)
			Color NG_Font_R , NG_Font_G , NG_Font_B
			
			;---------------------------------
			; retrouve les bonnes colonnes
			;---------------------------------
			For Col.NG_BoardCol = Each NG_BoardCol
			
				;-----------------------------
				; Colonne de ce Board ?
				;-----------------------------
				If Col\BoardId = NG\Id
			
					NG_DrawThisButton ( Col\Button , Dx , Yref )
					
					dx = dx + Col\width
					
				EndIf
			
			Next
				
		EndIf
	
	Next

End Function

;---------------------------------
; Dessine les Agents
;---------------------------------
Function NG_DrawAgent ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Agent = Each NG_Agent
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide = 0 And TabOK = True And NG\show = True
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			Xwin = ParentWindow\Px
			Ywin = ParentWindow\Py + ParentWindow\Taille_BarreTitre
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xwin = ParentWindow\Px + ParentWindow\origine_x
				Ywin = ParentWindow\Py + ParentWindow\Taille_BarreTitre + ParentWindow\origine_y
			
				Xref = Xwin + NG\Px 
				Yref = Ywin + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xwin = ParentWindow\Px
				Ywin = ParentWindow\Py + ParentWindow\Taille_BarreTitre
			
				Xref = Xwin + NG\Px 
				Yref = Ywin + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;----------------------------
			; infos
			;----------------------------
			width  = ImageWidth (NG\Icon)
			height = ImageHeight (NG\Icon)
			
			;-----------------------------------------
			; Capture par la souris
			;-----------------------------------------
			
			On_me = False
			
			
			If NG_OnDoseur = False  And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False ; And NG_MoveWindow = False; And NG_MouseOldClic1 = False
				
				;--------------------------------
				; 1 er clic pour capturer ?
				;--------------------------------
				If NG_MouseZone ( Xref , Yref , width + 5 , height + 5 , 1 )
				
					NG_OnGadget = True
			
					On_me = True
													
				EndIf
			
			Else If NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False ; And NG_MoveWindow = False; And NG_MouseOldClic1 = False
							
				;----------------------------------
				; ensuite ? on conserve le clic ?
				;----------------------------------
				If NG_OnDoseurId = NG\Id
				
					;End
				
					NG_OnGadget = True
					
					On_me = True
				
				EndIf
										
			EndIf			
			
			;-------------------------------------------------------------------
			; On conserve le clic si on est déjà en train de bouger un doseur
			;-------------------------------------------------------------------
			If NG_MouseClic1 = 1 And NG_ComboBoxOpenID = -1 And On_me = True And NG_OnSlider = False; And NG_OldOnGadget = True 
				
				;End
				
				If NG_OnDoseurSaveId = True
					NG_OnDoseurId = NG\Id
				EndIf
					
				NG_OnDoseurSaveId = False
								
				;--------------------------------------------
				; il faut qu'on soit sur le même doseur
				;--------------------------------------------		
				If NG_OnDoseurId = NG\Id
				
					;---------------------------
					; on est sur un doseur
					;-----------------------
					NG_OnDoseur = True
				
					;-------------------------------------------
					; coordonnées de la souris
					;-------------------------------------------
					Mx = NG_MouseX
					My = NG_MouseY
					
					;-------------------------------------------
					; conditions de zone :
					;-------------------------------------------
					
					;---------------------------------------------
					; limites par défaut : celles de la fenetre
					;---------------------------------------------
					If NG\Xs = 0 Then Xs = Xwin + 1
					If NG\Ys = 0 Then Ys = Ywin + 4
					
					If NG\Xe = 0 Then Xe = Xwin + ParentWindow\Tx - 1 - width
					If NG\Ye = 0 Then Ye = Ywin + ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 6 - height
					
					;---------------------------------------------
					; ou : limites personnalisées
					;---------------------------------------------
					If NG\Xs <> 0 Then Xs = Xwin + NG\Xs
					If NG\Ys <> 0 Then Ys = Ywin + NG\Ys
					If NG\Xe <> 0 Then Xe = Xwin + NG\Xe
					If NG\Ye <> 0 Then Ye = Ywin + NG\Ye
					
					;---------------------------------------------
					; calcul des nouvelles coordonnée de l'agent
					;---------------------------------------------
					
					;-------------------------------------------
					; en X, si pas de limites imposées
					;-------------------------------------------
					If NG\LimitX = False
					
						Xpos = Mx
						
						If Xpos < Xs Then Xpos = Xs
						If Xpos > Xe Then Xpos = Xe
						
						NG\Px = Xpos - Xwin
						
					;Else
					
						;Xpos = NG\Px
					
					EndIf
					
					;-------------------------------------------
					; en Y, si pas de limites imposées
					;-------------------------------------------
					If NG\LimitY = False
					
						Ypos = My
						
						If Ypos < Ys Then Ypos = Ys
						If Ypos > Ye Then Ypos = Ye
						
						NG\Py = Ypos - Ywin
						
					;Else
					
						;Ypos = NG\Py
					
					EndIf
				
				;-----------------------------------------
				; sinon on le perd la capture du doseur
				;-----------------------------------------					
				Else
					
					NG_OnDoseur = False
						
					NG_OnDoseurId = -1
					NG_OnDoseurSaveId = True		
					
				End If
												
			EndIf
			
			;---------------------------------------------------
			; enfin, on peut dessiner l'agent
			;---------------------------------------------------
			DrawImage NG\Icon , Xwin + NG\Px , Ywin + NG\Py
						
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , width , height )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf
				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
				
							
		EndIf
	
	Next

End Function


;---------------------------------
; Dessine les images
;---------------------------------


Function NG_DrawImage ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Image = Each NG_Image
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf

			
			;-----------------------
			; Classique
			;-----------------------
			If NG\zone = False
			
				Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			
			;-----------------------------------------------------
			; ou à Zone, définie par l'utilisateur
			;-----------------------------------------------------
			Else
			
				Viewport ParentWindow\Px + NG\zone_xs , ParentWindow\Py + NG\zone_ys , NG\zone_Xe , NG\zone_Ye
			
			EndIf
			
			;------------------------------------
			; Une ombre sous l'image d'abord ?
			;------------------------------------
			If NG\ombre
			
				Color NG_Font_or , NG_Font_og , NG_Font_ob
				decalage = 4
				Rect Xref + decalage , Yref + decalage , NG\Tx , NG\Ty , True
			
			EndIf
			
			;--------------------------------
			; On peut dessiner l'image
			;--------------------------------
			If NG\image > 0
			
				If NG\Block = False
			
					DrawImage NG\image , Xref , Yref
			
				Else
				
					DrawBlock NG\image , Xref , Yref
				
				EndIf
						
			EndIf
			
			;--------------------------------
			; sa bordure
			;--------------------------------
			If NG\Bord
			
				NG_DrawTrueBordure ( Xref-1 , Yref-1 , NG\Tx +2 , NG\Ty +2 , NG\r , NG\g , NG\b )
			
			EndIf
			
			;--------------------------------
			; detection ?
			;--------------------------------
			If NG\truegadget = True
			
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
					NG_OnGadget = True
				EndIf
				
			EndIf
			
			
						
		EndIf
	Next

End Function


;---------------------------------
; Dessine les boutons images
;---------------------------------
Function NG_DrawImageButton ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_ImageButton = Each NG_ImageButton
	
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True
		
			;-----------------------------
			; Reset par défaut....
			;-----------------------------
			NG\State = 0
		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			; bordure suplémentaires
			bord = 5 ;NG\Tx/10 ; pixels de chaque coté
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px - bord
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py - bord
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px - bord
				Yref = ParentWindow\Py + NG\Py - bord
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			

			
			;--------------------------------------
			; Interactions
			;--------------------------------------
			
			; S'il s'agit bien d'un bouton de la fenetre active
			;-----------------------------------------------------
			; et si on est pas déjà en train de bouger un doseur
			;-----------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG_OnDoseur = False And NG_MoveWindow = False And NG_ComboBoxOpenID = -1 And NG_DragIcon = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
							
				bouton = NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty , 1 )
				
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
					NG_OnGadget = True
				EndIf
			
				If bouton = 1
					
					; on le place en mode appuyé mais en attente
					NG\State = -1
					
				; mouseUp
				Else If bouton = 99
				
					NG\State = 1
					
				Else
				
					NG\State = 0
					
				EndIf
				
			EndIf

			
			;--------------------------------
			; On dessine d'abord le bouton
			;--------------------------------
			; seulement si NG\box = true
			;--------------------------------
			
			;-------------------------------------------
			; Attention nouvelle définition de Ty
			;-------------------------------------------
			
			Ty = NG\Ty + (bord*1.5) +2
			
			If NG\box
			
				; si normal
				If NG\state = 0
				
					;-----------------------------------------------
					; degradé : on démarre des valeurs par défaut
					;-----------------------------------------------
					red_start#   = NG_IB_Re : red_end#   = NG_IB_Rs
					green_start# = NG_IB_Ge : green_end# = NG_IB_Gs
					blue_start#  = NG_IB_Be : blue_end#  = NG_IB_Bs
				
				Else
				
					;-----------------------------------------------
					; degradé : on démarre des valeurs par défaut
					;-----------------------------------------------
					red_start#   = NG_IB_Rs : red_end#   = NG_IB_Re
					green_start# = NG_IB_Gs : green_end# = NG_IB_Ge
					blue_start#  = NG_IB_Bs : blue_end#  = NG_IB_Be
					
				EndIf
				
				; création du dégradé
				Dir = 1
				
				GradientRed#   = ( Red_end - Red_start ) / Ty
				GradientGreen# = ( Green_end - Green_start ) / Ty
				GradientBlue#  = ( Blue_end - Blue_start ) / Ty
								
				For Y = Yref To ( Yref + Ty -1 ) 
				
					Color Red_start , Green_start , Blue_start
					
					Rect Xref+1 , Y , NG\Tx +(bord*1.5) , 1
					
					Red_start = Red_start + GradientRed
					Green_start = Green_start + GradientGreen
					Blue_start = Blue_start + GradientBlue
					
				Next
				
				;--------------------
				; Bordure du bouton 
				;--------------------
				If NG\state = 0
					NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx +(bord*1.5) +3 , Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB )
				Else
					NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx +(bord*1.5) +2 , Ty , NG_ButtonBordR , NG_ButtonBordG , NG_ButtonBordB , 2 )
				EndIf
			
			EndIf
			
			;--------------------------------
			; On peut dessiner l'image
			;--------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; si normal
			
			If NG\State = 0
			
				DrawImage NG\image1 , Xref + (NG\Tx/2) , Yref + (NG\Ty/2)
			
			Else
			
				decalage = 1
			
				If NG\image2 = 0
				
					DrawImage NG\image1 , Xref + (NG\Tx/2) + decalage , Yref + (NG\Ty/2) + decalage
				
				Else If NG\image2 > 0
				
					DrawImage NG\image2 , Xref + (NG\Tx/2) + decalage , Yref + (NG\Ty/2) + decalage
				
				EndIf
				
			EndIf
			
			;--------------------------------
			; sa bordure
			;--------------------------------
			;NG_DrawTrueBordure ( Xref-1 , Yref-1 , NG\Tx +2 , NG\Ty +2 , NG\r , NG\g , NG\b )
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf

				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf

						
		EndIf
	Next

End Function

;--------------------------------------
; dessine les 3D Preview
;--------------------------------------
Function NG_Render3DPreview ()

	For NG.NG_Window = Each NG_Window
	
		If NG\media$ = "3d"
			
			; montre
			ShowEntity NG\model
			CameraProjMode NG\cam , 1	
	
			NG_RenderCam ( NG\frame3d )
	
			; cache
			HideEntity NG\model
			CameraProjMode NG\cam , 0
		
		EndIf
	
	Next

End Function

;---------------------------------
; Dessine les 3D Frames
;---------------------------------
Function NG_Draw3dFrame ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
	
	For NG.NG_3dFrame = Each NG_3dFrame
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			

			
			;-----------------------------------------
			; On peut dessiner l'image de la caméra
			;-----------------------------------------
			DrawBlock NG\image , Xref , Yref
			
			;---------------------------------
			; bordure
			;---------------------------------
			NG_DrawTrueBordure ( Xref-1 , Yref-1 , NG\Tx +2 , NG\Ty +2 , NG\r , NG\g , NG\b )
			
			;--------------------------------
			; detection ?
			;--------------------------------
			If NG\truegadget = True
			
				;------------------------------
				; on est bien sur un gadget
				;------------------------------
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
					NG_OnGadget = True
				EndIf
				
			EndIf
				
		EndIf
	Next

End Function

;---------------------------------
; Dessine les 3D Frames
;---------------------------------
Function NG_RenderCam ( Id , renderatextura=0,ancho=0,alto=0)

	NG.NG_3dFrame = Object.NG_3dFrame( Id )
	
	For NG.NG_3dFrame = Each NG_3dFrame
	
		If NG\Id = Id
	
		;----------------------------------
		; on dessine l'image de la camera
		;----------------------------------
			
		;-----------------------------------------------
		; si la fréquence est atteinte
		;-----------------------------------------------
		NG\time = NG\time + 1
			
		If NG\time => NG\freq And NG\active = True
		
			;-----------------------------------
			; reset pour commencer proprement
			;-----------------------------------
			Cls
			
			;--------------------------------
			; on rend l'image de la camera
			;--------------------------------
		    SetBuffer BackBuffer ()			
			
			If renderatextura<>0 Then
			 Viewport 0 , 0 , ancho , alto
			Else
			 Viewport 0 , 0 , NG\Tx , NG\Ty
			EndIf
			
			Origin 0,0
			
			CameraProjMode NG\camera , 1
			
			If renderatextura<>0 Then
			 CameraViewport NG\camera , 0 , 0 , ancho , alto
			Else
			 CameraViewport NG\camera , 0 , 0 , NG\Tx , NG\Ty
			EndIf
				
			WireFrame MALLA;NG\wire
				
			RenderWorld()
			
			;---------------------------------------
			; c'est fait, on désactive la caméra...
			;---------------------------------------				
			CameraProjMode NG\camera , 0
				
			WireFrame 0
			
			;----------------------------------------------
			; On copie le rendu dans l'image du 3d frame
			;----------------------------------------------
			If renderatextura<>0 Then CopyRect 0 , 0 , ancho , alto , 0 , 0 , BackBuffer() , ImageBuffer (renderatextura) 
			
			CopyRect 0 , 0 , NG\Tx , NG\Ty , 0 , 0 , BackBuffer() , ImageBuffer (NG\Image) 
			;GrabImage NG\image , 0 , 0
				
			NG\time = 0
			
			;------------------------
			; reset
			;------------------------
			SetBuffer BackBuffer()
			
			Cls
			
		EndIf
		
		EndIf
		
	Next
	
End Function


;---------------------------------------
; Dessine un indicateur FPS
;---------------------------------------
Function NG_DrawFPS ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_FPS = Each NG_FPS
	
	
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

		
			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			

			
			; 2ndes réfenrences pour les textes, pour centrer en X et en Y
			Xref2 = Xref + 5 
			Yref2 = Yref + 5 - 20; -20pour compenser le systeme de coords de Blitz
			
			;-------------------------------------------
			; Calcul des images par seconde
			;-------------------------------------------
			
			; on  compte 1 frame suplémentaire si on est toujours dans la meme seconde
			NG\count_frames = NG\count_frames + 1
			
			; on enregistre la nouvelle valeur
			NG\newtime = MilliSecs()
			
			; différence entre les deux temps
			ecart = NG\newtime - NG\oldtime
			
			; si cet ecart est égal à 1 sec (1000 millisecondes)
			If ecart >= 1000
			
				; alors on enregistre la nouvelle valeur de base pour calculer le prochain ecart
				NG\oldtime = NG\newtime
				
				; on enregistre le nombre de frames
				NG\frames = NG\count_frames
				
				; on remet le compteur de frames à 0
				NG\count_frames = 0
				
			EndIf
						
			;--------------------------------
			; On dessine d'abord le cadre
			;--------------------------------
				
			;-----------------------------------------------
			; degradé : on démarre des valeurs par défaut
			;-----------------------------------------------
			red_start#   = NG_IB_Rs : red_end#   = NG_IB_Re
			green_start# = NG_IB_Gs : green_end# = NG_IB_Ge
			blue_start#  = NG_IB_Bs : blue_end#  = NG_IB_Be
					
			; création du dégradé
			Dir = 1
				
			GradientRed#   = ( Red_end - Red_start ) / NG\Ty
			GradientGreen# = ( Green_end - Green_start ) / NG\Ty
			GradientBlue#  = ( Blue_end - Blue_start ) / NG\Ty
								
			For Y = Yref To ( Yref + NG\Ty-1 ) 
				
				Color Red_start , Green_start , Blue_start
					
				Rect Xref+1 , Y , NG\Tx - 2 , 1
					
				Red_start = Red_start + GradientRed
				Green_start = Green_start + GradientGreen
				Blue_start = Blue_start + GradientBlue
					
			Next
				
			;--------------------
			; Bordure du cadre 
			;--------------------
			NG_DrawTrueBordure ( Xref  , Yref  , NG\Tx , NG\Ty , 0 , 0 , 50 )
			
			;--------------------------------
			; On peut dessiner les textes
			;--------------------------------
			SetFont NG_NormalFont
			
			;--------------------------------------------------------------
			
			;--------------------------------------------------------------
				
			; si le texte est ombré
			If NG\ombre
					
				Color NG_Font_or , NG_Font_og , NG_Font_ob
				 
				NG_DisplayText( NG\Label$ , Xref2 + 2 , Yref2 + 2 , 50 )
				NG_DisplayText( NG\frames , Xref2 + (NG\Tx/2) + 2 , Yref2 + 2 + 20 , 50 , True )
				
			EndIf
				
			; le vrai texte
			Color NG\r , NG\g , NG\b 
			NG_DisplayText( NG\Label$ , Xref2 , Yref2 , 50 )
			NG_DisplayText( NG\frames , Xref2 + (NG\Tx/2) , Yref2 + 20 , 50 , True )
			
			;------------------------------------------------------
			; On dessine enfin, s'il y en a une, la boite d'aide
			;------------------------------------------------------
			If ( ParentWindow\Active Or ParentWindow\Background = 0 ) And NG\Help$ <> ""
			
				If NG_MouseZone ( Xref , Yref , NG\Tx , NG\Ty )
				
					NG\HelpTime = NG\HelpTime + 1
					
					If NG\HelpTime > NG_HelpTime

						NG_HelpLabel$ = NG\Help$
						
					EndIf

				
				Else
				
					NG\HelpTime = 0
				
				EndIf
			
			EndIf
		
		EndIf
		
	Next

End Function


;---------------------------------
; Dessine les pongs
;---------------------------------
Function NG_DrawPong ( parent_window )

	Local score$

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	For NG.NG_Pong = Each NG_Pong
		
		; -------------------------------------------
		; Si la fenetre n'est pas réduite  (hide=1)
		; -------------------------------------------
		
		If NG\Tab$ = "" Or NG\Tab$ = ParentWindow\tab_label$ [ ParentWindow\tab_current ]
			TabOK = True
		Else
			TabOK = False
		EndIf
		
		
		If NG\WinId = ParentWindow\Id And ParentWindow\hide=0 And TabOK = True And NG\show = True

			; ---------------------------------------------------------------
			; On cherche les coordonnées X/Y de la fenetre comme réfenrence
			; ---------------------------------------------------------------
			
			;------------------------------------
			; origine : suit celle de la fenetre
			;------------------------------------
			If NG\ice = False
			
				Xref = ParentWindow\Px + ParentWindow\origine_x + NG\Px 
				Yref = ParentWindow\Py + ParentWindow\origine_y + NG\Py
			
			;------------------------------------
			; gelé : conserve 0,0
			;------------------------------------
			Else
			
				Xref = ParentWindow\Px + NG\Px 
				Yref = ParentWindow\Py + NG\Py
			
			EndIf
			
			midX = NG\Tx / 2
			midY = NG\Ty / 2
			
			; viewport
			Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			

			
			;---------------------
			; Fond de Pong
			;---------------------
			
			Color 0 , 0 , 0
			Rect Xref , Yref , NG\Tx , NG\Ty , True
			
			;--------------------------------------------------------
			; Ecran Titre : Start = 0
			;--------------------------------------------------------
			If NG\Start = 0
			
				SetFont NG_TitleFontB
				Color 255 , 255 , 255
				
				long = StringWidth (NG\Titre$)
				larg = FontHeight ()
															
				NG_DisplayText ( NG\titre$ , Xref + MidX - ( long/2 ) , Yref + MidY - (larg) , 300 )
				
				NG\timer = NG\timer - 1
				
				;----------------
				; clignotements
				;----------------				
				If NG\timer < 1000
				
					SetFont NG_NormalFont
					
					long = StringWidth (NG\info$)
					larg = FontHeight ()
															
					NG_DisplayText ( NG\info$ , Xref + MidX - ( long/2 ) , Yref + NG\Ty - (larg*3) , 300 )
				
					If NG\timer < - 60
						NG\timer = 15
					EndIf
				
				EndIf
				
			;-----------------------------------------------------------
			; En cours de partie
			;-----------------------------------------------------------
			Else
			
				;------------------------------------
				; Limites de mouvements
				;------------------------------------
				If NG\Player_Py < 0 Then NG\Player_Py = 0
				If NG\Player_Py + NG\Raq_Ty > NG\Ty Then NG\Player_Py = NG\Ty - NG\Raq_Ty
				
				If NG\Enemy_Py < 0 Then NG\Enemy_Py = 0
				If NG\Enemy_Py + NG\Raq_Ty > NG\Ty Then NG\Enemy_Py = NG\Ty - NG\Raq_Ty
							
				;------------------------------------
				; on dessine les raquettes
				;------------------------------------
				Color 255 , 255 , 255
				
				Rect Xref + NG\Player_Px , Yref + NG\Player_Py , NG\Raq_Tx , NG\Raq_Ty , True
				Rect Xref + NG\Enemy_Px , Yref + NG\Enemy_Py , NG\Raq_Tx , NG\Raq_Ty , True

				;------------------------------------
				; la balle...
				;------------------------------------
				NG\Ball_Px = NG\Ball_Px + NG\mx
				NG\Ball_Py = NG\Ball_Py + NG\my
				
				; limites
				;----------
				If NG\Ball_Px < 0 Then NG\Ball_Px = 0 : NG\Enemy_score = NG\Enemy_score + 1
				If NG\Ball_Py < 0 Then NG\Ball_Py = 0
				If NG\Ball_Px + NG\Ball_Tx > NG\Tx Then NG\Ball_Px = NG\Tx - NG\Ball_Tx : NG\Player_score = NG\Player_score + 1
				If NG\Ball_Py + NG\Ball_Tx > NG\Ty Then NG\Ball_Py = NG\Ty - NG\Ball_Tx
												
				Oval Xref + NG\Ball_Px , Yref + NG\Ball_Py , NG\Ball_Tx , NG\Ball_Tx , True
				
				;-------------------------------------
				; le filet
				;-------------------------------------
				Rect Xref + midX , Yref , 1 , NG\Ty
				
				;---------------------------------------------
				; Affichage des scores
				;---------------------------------------------
				Color 0 , 0 , 100
				Rect Xref + 2 , Yref + NG\Ty - 10 - FontHeight() , NG\Tx - 4 , 5 + FontHeight() , True
				
				Color 255 , 255 , 255
				Rect Xref + 1 , Yref + NG\Ty - 10 - FontHeight() - 1 , NG\Tx - 2 , 5 + FontHeight() + 2 , False
				
				SetFont NG_NormalFont
				Color 255 , 255 , 255
				
				score$ = "SCORE"
				long = StringWidth ( score$ )
				larg = FontHeight ()
				
				NG_DisplayText ( score$ , Xref + MidX - ( long/2 ) , Yref + NG\Ty - (larg*2.8) , 300 )
				
				; score joueur
				NG_DisplayText ( NG\player_score , Xref + 5 , Yref + NG\Ty - (larg*2.8) , 300 )
								
				; score joueur
				long = StringWidth ( NG\enemy_score )
				NG_DisplayText ( NG\enemy_score , Xref + NG\Tx - 5 - long , Yref + NG\Ty - (larg*2.8) , 300 )

							
				
			EndIf
			
			
			;--------------------------------
			; sa bordure
			;--------------------------------
			;NG_DrawTrueBordure ( Xref-1 , Yref-1 , NG\Tx +2 , NG\Ty +2 , NG\r , NG\g , NG\b )
						
		EndIf
	Next

End Function

;---------------------------------
; Dessine les design tabs
;---------------------------------
Function NG_DrawDesignTab ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	; -------------------------------------------
	; Si la fenetre n'est pas réduite  (hide=1)
	; -------------------------------------------
	; et si les tabs sont activés !
	;--------------------------------------------
	If ParentWindow\hide=0 And ParentWindow\tab_on = True

		;---------------------------------------------------------------
		; On cherche les coordonnées X/Y de la fenetre comme réfenrence
		;---------------------------------------------------------------
		
		st = 50
		sp = 20
		sp2 = sp/2
		
		Xref = ParentWindow\Px
		Yref = ParentWindow\Py + ParentWindow\Taille_BarreTitre

		StartX = Xref + sp2
		StartY = Yref + sp2
		
		;------------------------------------------
		; dessine les icones et interactions avec
		;------------------------------------------
		For i = 1 To ParentWindow\tab_max
		
			;----------------------------------
			; survol
			;----------------------------------
						
			;----------------------------------
			; tab séléctionné
			;----------------------------------
			If ParentWindow\tab_current = i
			
				;si la fenetre est active
				If ParentWindow\Active = True
						
					red_start#   = NG_red_s   : red_end#      = NG_red_e
					green_start# = NG_green_s : green_end#    = NG_green_e
					blue_start#  = NG_blue_s  : blue_end#     = NG_blue_e
											
				; si elle est inactive
				Else
				
					red_start#   = NG_red_sI   : red_end#    = NG_red_eI
					green_start# = NG_green_sI : green_end#  = NG_green_eI
					blue_start#  = NG_blue_sI  : blue_end#   = NG_blue_eI
						
				EndIf
				
				; création du dégradé
				Dir = 1
						
				GradientRed#   = ( Red_end - Red_start ) / (st+sp2+2)
				GradientGreen# = ( Green_end - Green_start ) / (st+sp2+2)
				GradientBlue#  = ( Blue_end - Blue_start ) / (st+sp2+2)
										
				For Y = StartY - sp2 + 2 To ( StartY + st + sp2 ) 
						
					Color Red_start , Green_start , Blue_start
							
					Rect StartX - sp2 + 1 , Y , st + sp , 1
							
					Red_start = Red_start + GradientRed
					Green_start = Green_start + GradientGreen
					Blue_start = Blue_start + GradientBlue
							
				Next
				
			;----------------------------------
			; tab survolé ?
			;----------------------------------
			Else
			
				If ParentWindow\Active And NG_OnDoseur = False And NG_MoveWindow = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
					
					Zx = StartX - sp2 + 1
					Zy = StartY - sp2 + 2
				
					If NG_MouseZone ( Zx , Zy , st + sp , st + sp )
					
						NG_OnGadget = True
												
						;si la fenetre est active
						If ParentWindow\Active = True
								
							red_start#   = NG_red_s   : red_end#      = NG_red_e
							green_start# = NG_green_s : green_end#    = NG_green_e
							blue_start#  = NG_blue_s  : blue_end#     = NG_blue_e
													
						; si elle est inactive
						Else
						
							red_start#   = NG_red_sI   : red_end#    = NG_red_eI
							green_start# = NG_green_sI : green_end#  = NG_green_eI
							blue_start#  = NG_blue_sI  : blue_end#   = NG_blue_eI
								
						EndIf
						
						; création du dégradé
						Dir = 1
								
						;GradientRed#   = ( Red_end - Red_start ) / (st+sp2+2)
						;GradientGreen# = ( Green_end - Green_start ) / (st+sp2+2)
						;GradientBlue#  = ( Blue_end - Blue_start ) / (st+sp2+2)
						
						GradientRed#   = ( Red_start - Red_end ) / (st+sp2+2)
						GradientGreen# = ( Green_start - Green_end ) / (st+sp2+2)
						GradientBlue#  = ( Blue_start - Blue_end ) / (st+sp2+2)
								
						Color Red_start , Green_start , Blue_start
						
						Rect Zx , Zy , st + sp , st + sp , True
						
						;---------------------------------------------
						; validation
						;---------------------------------------------
						If NG_MouseUp1
						
							ParentWindow\tab_current = i				
						
						EndIf
						
						
						
					EndIf
				
				EndIf
						
			EndIf
				
			;----------------------------------------
			; texte et icone du tab
			;----------------------------------------
			DrawImage ParentWindow\tab_icone [i] , StartX , StartY
			
			label$ = ParentWindow\Tab_label$[i]
			
			label$ = Left$ (label$ , 10)
			
			SetFont NG_NormalFont
			Color NG_Font_r , NG_Font_g , NG_Font_b
			
			wdth = StringWidth ( label$ )
			
			mid_wdth = wdth / 2
			
			Text StartX + (st/2) - mid_wdth , StartY + st - FontHeight()/2 + 4 , label$			

			
			;-----------------------------------------
			; suite
			;-----------------------------------------			
			StartX = StartX + st + sp
			
			;----------------------------------------
			; limite X : retour à la ligne
			;----------------------------------------
			If StartX > ParentWindow\Px + ParentWindow\Tx - st - (sp2)
				
				If ParentWindow\tab_max = i Then Exit
			
				StartX = Xref + sp2
				StartY = StartY + st + sp
			
			EndIf
					
		Next
		
		;--------------------------------------------
		; ligne de séparation
		;--------------------------------------------
		StartY = StartY + st + sp2 + FontHeight()
		
		Color 0,0,0
		
		Rect ParentWindow\Px + 1 , StartY , ParentWindow\Tx - 2 , 1


	EndIf

End Function

;---------------------------------
; Dessine les tabs
;---------------------------------
Function NG_DrawTab ( parent_window )

	ParentWindow.NG_Window = Object.NG_Window( Parent_Window )
			
	; -------------------------------------------
	; Si la fenetre n'est pas réduite  (hide=1)
	; -------------------------------------------
	; et si les tabs sont activés !
	;--------------------------------------------
	If ParentWindow\hide=0 And ParentWindow\tab_on = True
		
		;---------------------------------------------------------------
		; On cherche les coordonnées X/Y de la fenetre comme réfenrence
		;---------------------------------------------------------------
		Xref = ParentWindow\Px + ParentWindow\tab_Px
		Yref = ParentWindow\Py + ParentWindow\tab_Py
		
		;----------------------------------------------------------------
		; taille des tabs
		;----------------------------------------------------------------
		Tx = ParentWindow\tab_Tx
		Ty = ParentWindow\tab_Ty
		
		;---------------------------------------------
		; Tailles des images tabs
		;---------------------------------------------
		ZoneX = ImageWidth (NG_Tab_P)
		ZoneY = ImageHeight (NG_Tab_P)
		
		;---------------------------------------
		; tab en cours
		;---------------------------------------
		tabno = ParentWindow\tab_current
	
		; viewport
		Viewport ParentWindow\Px , ParentWindow\Py + ParentWindow\Taille_BarreTitre + 5 , ParentWindow\Tx - 1 , ParentWindow\Ty - ParentWindow\Taille_BarreTitre - 5
			

		
		;--------------------------------------
		; Interactions
		;-----------------------------------------------------	
		; S'il s'agit bien d'un bouton de la fenetre active
		;-----------------------------------------------------
		; et si on est pas déjà en train de bouger un doseur
		;-----------------------------------------------------
		If ParentWindow\Active And NG_OnDoseur = False And NG_MoveWindow = False And NG_OnOldValue = False And NG_OldMenuOpenId = -1 And NG_OnCottage = False
			
			;----------------------
			; Bouton Précedent
			;----------------------
			bouton = NG_MouseZone ( Xref , Yref , ZoneX , ZoneY , 1 )
			
			; on est bien sur un gadget
			If NG_MouseZone ( Xref , Yref , ZoneX , ZoneY )
				NG_OnGadget = True
			EndIf
											
			; mouseUp
			If bouton = 1
			
				decalage1 = 2
			
			Else If bouton = 99 
					
				ParentWindow\tab_current = ParentWindow\tab_current - 1
				If ParentWindow\tab_current < 1 Then ParentWindow\tab_current = 1
												
			EndIf
			
			;---------------------------------------
			; reset pour traiter le bouton suivant
			;---------------------------------------
			bouton = 0			
			
			;----------------------
			; Bouton Suivant
			;----------------------
			bouton = NG_MouseZone ( Xref + Tx - ZoneX , Yref , ZoneX , ZoneY , 1 )
			
			; on est bien sur un gadget
			If NG_MouseZone ( Xref + Tx - ZoneX , Yref , ZoneX , ZoneY )
				NG_OnGadget = True
			EndIf
											
			; mouseUp
			If bouton = 1
			
				decalage2 = 2
			
			Else If bouton = 99 
					
				ParentWindow\tab_current = ParentWindow\tab_current + 1
				If ParentWindow\tab_current > ParentWindow\tab_max Then ParentWindow\tab_current = ParentWindow\tab_max
												
			EndIf
		
		EndIf
			
		;--------------------------------
		; On dessine d'abord le cadre
		;--------------------------------
				
		;-----------------------------------------------
		; degradé : on démarre des valeurs par défaut
		;-----------------------------------------------
		
		If ParentWindow\BackGround = True And ParentWindow\Invisible = False
		
			;------------------------------------------------------------------
			; on ne dessine pas le fond si la fenetre a une skin
			;------------------------------------------------------------------
				
			If ParentWindow\Skin_on = False
			
				;--------------------------------------------
				; Si l'option dégradé est activée...
				;--------------------------------------------
				If ParentWindow\Grad = True
			
					;si la fenetre est active
					If ParentWindow\Active = True
							
						red_start#   = NG_red_s   : red_end#      = NG_red_e
						green_start# = NG_green_s : green_end#    = NG_green_e
						blue_start#  = NG_blue_s  : blue_end#     = NG_blue_e
												
					; si elle est inactive
					Else
					
						red_start#   = NG_red_sI   : red_end#    = NG_red_eI
						green_start# = NG_green_sI : green_end#  = NG_green_eI
						blue_start#  = NG_blue_sI  : blue_end#   = NG_blue_eI
							
					EndIf
					
					; création du dégradé
					Dir = 1
							
					GradientRed#   = ( Red_end - Red_start ) / Ty
					GradientGreen# = ( Green_end - Green_start ) / Ty
					GradientBlue#  = ( Blue_end - Blue_start ) / Ty
											
					For Y = Yref To ( Yref + Ty ) 
					
						Color Red_start , Green_start , Blue_start
								
						Rect Xref+1 , Y , Tx - 2 , 1
								
						Red_start = Red_start + GradientRed
						Green_start = Green_start + GradientGreen
						Blue_start = Blue_start + GradientBlue
								
					Next
				
				;----------------------------------------------
				; Si elle ne l'est pas
				;----------------------------------------------
				Else
				
					If ParentWindow\Active
				
						NG_red_M   = ( (NG_red_s + NG_red_e) / 2 )
						NG_green_M = ( (NG_green_s + NG_green_e) / 2 )
						NG_blue_M  = ( (NG_blue_s + NG_blue_e) / 2 )
					
					Else
						
						NG_red_M   = ( (NG_red_s + NG_red_e) / 3 )
						NG_green_M = ( (NG_green_s + NG_green_e) / 3 )
						NG_blue_M  = ( (NG_blue_s + NG_blue_e) / 3 )
						
					EndIf
										
					Color NG_red_M , NG_green_M , NG_blue_M
						
					Rect Xref + 1 , Yref , Tx - 2 , Ty , True
					
				EndIf
			
			EndIf
					
			;--------------------
			; Bordure du cadre 
			;--------------------
			NG_DrawTrueBordure ( Xref  , Yref  , Tx , Ty , 0 , 0 , 50 )
		
		EndIf
		
		;--------------------------------
		; On peut dessiner les textes
		;--------------------------------
		SetFont NG_NormalFont
		
		labelTx = StringWidth ( ParentWindow\tab_Label$ [tabno] )
		
		compY = 10
				
		; si le texte est ombré
		If ParentWindow\ombre
					
			Color NG_Font_or , NG_Font_og , NG_Font_ob				 
			NG_DisplayText( ParentWindow\tab_Label$ [tabno] , Xref + (Tx/2) - (labelTx/2) + 2 , Yref - compY + 2 , 300 )
				
		EndIf
		
		; le vrai texte
		Color NG_Font_r , NG_Font_g , NG_Font_b
		NG_DisplayText( ParentWindow\tab_Label$ [tabno] , Xref + (Tx/2) - (labelTx/2) , Yref - compY, 300 )
		
		;--------------------------------
		; Boutons du Tab
		;--------------------------------
		If ParentWindow\tab_current > 1
			DrawImage NG_Tab_P , Xref+5 + decalage1 , Yref+5 + decalage1
		EndIf
		If ParentWindow\tab_current < ParentWindow\tab_max
			DrawImage NG_Tab_N , Xref+Tx-5 + decalage2 , Yref+5 + decalage2
		EndIf
		
		
	EndIf

End Function

;--------------------------------------------------
; Dessine la souris
;--------------------------------------------------
Function NG_DrawMouse ()

	If NG_PointerOn
		
		;--------------------
		; mode deplacement
		;--------------------	
		If NG_MoveWindow And NG_OnGadget = False And NG_OnSpecialGadget = False And NG_DragIcon = False And NG_OnValue = False
			
			DrawImage NG_Pointer (2) , NG_MouseX , NG_MouseY
	
		;--------------------
		; mode classique
		;--------------------
		Else If NG_DragIcon = False And NG_OnValue = False
		
			DrawImage NG_Pointer (1) , NG_MouseX , NG_MouseY
	
		;--------------------------
		; mode drag/drop d'icones
		;--------------------------
		Else If NG_DragIcon = True And NG_OnValue = False
	
			DrawImage NG_Pointer (3) , NG_MouseX , NG_MouseY
			
		;---------------------------
		; mode Value edit
		;---------------------------
		Else If NG_DragIcon = False And NG_OnValue = True
		
			DrawImage NG_Pointer (4) , NG_MouseX , NG_MouseY	
	
		EndIf
	
	EndIf

End Function
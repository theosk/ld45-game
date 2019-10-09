;------------------------------------------------------------------------
; Modules : gestion des styles/thèmes
;------------------------------------------------------------------------

;---------------------------------------
; Fonctions Utitlisateur à propos des
;              THEMES
;---------------------------------------


;-------------------------------------------------------------------
; Charge un thème préconfiguré par Moi
;-------------------------------------------------------------------
Function NG_SetStyle ( theme=1 , reset=False )

	If reset = True
	
		NG_Path$ = "Ngui_files\"
		NG_ext$  = ".png"

	EndIf


	Select theme
	
		; NIGHT GUI STANDARD
		Case 1
		
			NG_DefaultSkin$ = ""
			
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 255
			NG_Font_b = 255
			NG_Font_g = 255
			
			; couleurs normales pour les titres
			NG_TFont_r = 255
			NG_TFont_b = 255
			NG_TFont_g = 255
			
			; ombrage
			NG_ombrage = -200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
			
			; fenetres actives
			NG_red_s   = 42  : NG_red_e   = 6
			NG_green_s = 119 : NG_green_e = 22
			NG_blue_s  = 172 : NG_blue_e  = 68
			
			; fenetres inactives
			NG_red_sI   = 34  : NG_red_eI   = 3
			NG_green_sI = 94  : NG_green_eI = 11
			NG_blue_sI  = 136 : NG_blue_eI  = 33
			
			; pour les cadres des menus
			NG_Menu_Rs = 42  : NG_Menu_Re = 6
			NG_Menu_Gs = 119 : NG_Menu_Ge = 22
			NG_Menu_Bs = 172 : NG_Menu_Be = 68
			
			; bordures des fenetres
			NG_Bord_R = 255
			NG_Bord_G = 255
			NG_Bord_B = 255

			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 65
			NG_Grad_Gl = 65
			NG_Grad_Bl = 155
			
			; coté assombri
			NG_Grad_Ro = 24
			NG_Grad_Go = 26
			NG_Grad_Bo = 100
									
			; boites d'aide
			NG_Help_r = 144
			NG_Help_g = 134
			NG_Help_b = 108
			
			; dégradés des boutons
			NG_IB_Rs = 62 : NG_IB_Re = 42
			NG_IB_Gs = 68 : NG_IB_Ge = 46
			NG_IB_Bs = 121 : Ng_IB_Be = 83
			
			; couleur du texte des inputs box
			NG_IT_R = 0
			NG_IT_G = 0
			NG_IT_B = 0
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 255
			NG_Input_G = 255
			NG_Input_B = 255
			
			; barre de selection
			NG_Sel_R = 0
			NG_Sel_G = 0
			NG_Sel_B = 100
			
			; fond des progress bar
			NG_PB_r = 0
			NG_PB_g = 0
			NG_PB_b = 0
			
			; pour les cottages
			NG_Cottage_R = 255
			NG_Cottage_G = 255
			NG_Cottage_B = 255
		
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow"
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"

			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"

			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"

			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"

			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , False )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , True )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"

		; METAL
		Case 2
		
			NG_DefaultSkin$ = ""
			
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 0
			NG_Font_b = 0
			NG_Font_g = 0
			
			; couleurs normales pour les titres
			NG_TFont_r = 0
			NG_TFont_b = 0
			NG_TFont_g = 0
			
			; ombrage
			NG_ombrage = 200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
						
			; fenetres actives
			NG_red_s   = 196 : NG_red_e   = 250
			NG_green_s = 196 : NG_green_e  = 250
			NG_blue_s  = 196 : NG_blue_e   = 250
			
			; fenetres inactives
			NG_red_sI   = 162 : NG_red_eI   = 220
			NG_green_sI = 162 : NG_green_eI = 220
			NG_blue_sI  = 162 :  NG_blue_eI  = 220
			
			; pour les cadres des menus
			NG_Menu_Rs = 196  : NG_Menu_Re = 250
			NG_Menu_Gs = 196 : NG_Menu_Ge = 250
			NG_Menu_Bs = 196 : NG_Menu_Be = 250
			
			; bordures des fenetres
			NG_Bord_R = 0
			NG_Bord_G = 0
			NG_Bord_B = 0
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 230
			NG_Grad_Gl = 230
			NG_Grad_Bl = 230
			
			; coté assombri
			NG_Grad_Ro = 100
			NG_Grad_Go = 100
			NG_Grad_Bo = 100
			
			; boites d'aide
			NG_Help_r = 221
			NG_Help_g = 221
			NG_Help_b = 184
			
			; dégradés des boutons
			NG_IB_Rs = 196 : NG_IB_Re = 250
			NG_IB_Gs = 196 : NG_IB_Ge = 250
			NG_IB_Bs = 196 : Ng_IB_Be = 250
			
			; couleur du texte des inputs box
			NG_IT_R = 0
			NG_IT_G = 0
			NG_IT_B = 0
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 255
			NG_Input_G = 255
			NG_Input_B = 255
			
			; barre de selection
			NG_Sel_R = 200
			NG_Sel_G = 200
			NG_Sel_B = 200
			
			; fond des progress bar
			NG_PB_r = 255
			NG_PB_g = 255
			NG_PB_b = 255
			
			; pour les cottages
			NG_Cottage_R = 255
			NG_Cottage_G = 255
			NG_Cottage_B = 255
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "theme2\NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme2\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , False )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , True )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"

			
		; DARK NIGHT
		Case 3
		
			NG_DefaultSkin$ = ""
			
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 255
			NG_Font_b = 255
			NG_Font_g = 255
			
			; couleurs normales pour les titres
			NG_TFont_r = 255
			NG_TFont_b = 255
			NG_TFont_g = 255
			
			; ombrage
			NG_ombrage = -200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
			
			; fenetres actives
			NG_red_s   = 100  : NG_red_e  = 130
			NG_green_s = 100 : NG_green_e = 130
			NG_blue_s  = 100 : NG_blue_e  = 130
			
			; fenetres inactives
			NG_red_sI   = 70  : NG_red_eI  = 100
			NG_green_sI = 70 : NG_green_eI = 100
			NG_blue_sI  = 70 : NG_blue_eI  = 100
			
			; pour les cadres des menus
			NG_Menu_Rs = 100  : NG_Menu_Re = 130
			NG_Menu_Gs = 100 : NG_Menu_Ge = 130
			NG_Menu_Bs = 100 : NG_Menu_Be = 130
			
			; bordures des fenetres
			NG_Bord_R = 255
			NG_Bord_G = 255
			NG_Bord_B = 255
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 160
			NG_Grad_Gl = 160
			NG_Grad_Bl = 160
			
			; coté assombri
			NG_Grad_Ro = 60
			NG_Grad_Go = 60
			NG_Grad_Bo = 60

									
			; boites d'aide
			NG_Help_r = 0
			NG_Help_g = 0
			NG_Help_b = 0
			
			; dégradés des boutons
			NG_IB_Rs = 100 : NG_IB_Re = 70
			NG_IB_Gs = 100 : NG_IB_Ge = 70
			NG_IB_Bs = 100 : Ng_IB_Be = 70
			
			; couleur du texte des inputs box
			NG_IT_R = 205
			NG_IT_G = 210
			NG_IT_B = 80
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 45
			NG_Input_G = 80
			NG_Input_B = 130
			
			; barre de selection
			NG_Sel_R = 0
			NG_Sel_G = 0
			NG_Sel_B = 100
			
			; fond des progress bar
			NG_PB_r = 0
			NG_PB_g = 0
			NG_PB_b = 0
			
			; pour les cottages
			NG_Cottage_R = 255
			NG_Cottage_G = 255
			NG_Cottage_B = 255
		
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme3\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , False )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , True )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"
		
		; EPSILON (SIMPLE)
		Case 4
		
			NG_DefaultSkin$ = ""
			
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 0
			NG_Font_b = 0
			NG_Font_g = 0
			
			; couleurs normales pour les titres
			NG_TFont_r = 0
			NG_TFont_b = 0
			NG_TFont_g = 0
			
			; ombrage
			NG_ombrage = 200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
						
			; fenetres actives
			NG_red_s   = 218 : NG_red_e   = 136
			NG_green_s = 236 : NG_green_e  = 186
			NG_blue_s  = 255 : NG_blue_e   = 240
			
			; fenetres inactives
			NG_red_sI   = 208 : NG_red_eI   = 116
			NG_green_sI = 216 : NG_green_eI = 166
			NG_blue_sI  = 235 :  NG_blue_eI  = 220
			
			; pour les cadres des menus
			NG_Menu_Rs = 196  : NG_Menu_Re = 250
			NG_Menu_Gs = 196 : NG_Menu_Ge = 250
			NG_Menu_Bs = 196 : NG_Menu_Be = 250
			
			; bordures des fenetres
			NG_Bord_R = 0
			NG_Bord_G = 0
			NG_Bord_B = 0
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 230
			NG_Grad_Gl = 230
			NG_Grad_Bl = 230
			
			; coté assombri
			NG_Grad_Ro = 100
			NG_Grad_Go = 100
			NG_Grad_Bo = 100
			
			; boites d'aide
			NG_Help_r = 208
			NG_Help_g = 216
			NG_Help_b = 235
			
			; dégradés des boutons
			NG_IB_Rs = 126 : NG_IB_Re = 155
			NG_IB_Gs = 182 : NG_IB_Ge = 198
			NG_IB_Bs = 239 : Ng_IB_Be = 242
			
			; couleur du texte des inputs box
			NG_IT_R = 0
			NG_IT_G = 0
			NG_IT_B = 0
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 211
			NG_Input_G = 233
			NG_Input_B = 255
			
			; barre de selection
			NG_Sel_R = 52
			NG_Sel_G = 123
			NG_Sel_B = 196
			
			; fond des progress bar
			NG_PB_r = 255
			NG_PB_g = 255
			NG_PB_b = 255
			
			; pour les cottages
			NG_Cottage_R = 255
			NG_Cottage_G = 255
			NG_Cottage_B = 255
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "theme2\NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme2\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , False )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , False )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"
		
		
		; EPSILON (TRUE)
		Case 5
		
			NG_DefaultSkin$ = "NGUI_files\NG_Epsilon.png"
		
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 0
			NG_Font_b = 0
			NG_Font_g = 0
			
			; couleurs normales pour les titres
			NG_TFont_r = 0
			NG_TFont_b = 0
			NG_TFont_g = 0
			
			; ombrage
			NG_ombrage = 200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
						
			; fenetres actives
			NG_red_s   = 218 : NG_red_e   = 136
			NG_green_s = 236 : NG_green_e  = 186
			NG_blue_s  = 255 : NG_blue_e   = 240
			
			; fenetres inactives
			NG_red_sI   = 208 : NG_red_eI   = 116
			NG_green_sI = 216 : NG_green_eI = 166
			NG_blue_sI  = 235 :  NG_blue_eI  = 220
			
			; bordures des fenetres
			NG_Bord_R = 0
			NG_Bord_G = 0
			NG_Bord_B = 0
			
			; pour les cadres des menus
			NG_Menu_Rs = 196  : NG_Menu_Re = 250
			NG_Menu_Gs = 196 : NG_Menu_Ge = 250
			NG_Menu_Bs = 196 : NG_Menu_Be = 250
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 230
			NG_Grad_Gl = 230
			NG_Grad_Bl = 230
			
			; coté assombri
			NG_Grad_Ro = 100
			NG_Grad_Go = 100
			NG_Grad_Bo = 100
			
			; boites d'aide
			NG_Help_r = 208
			NG_Help_g = 216
			NG_Help_b = 235
			
			; dégradés des boutons
			NG_IB_Rs = 126 : NG_IB_Re = 155
			NG_IB_Gs = 182 : NG_IB_Ge = 198
			NG_IB_Bs = 239 : Ng_IB_Be = 242
			
			; couleur du texte des inputs box
			NG_IT_R = 0
			NG_IT_G = 0
			NG_IT_B = 0
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 211
			NG_Input_G = 233
			NG_Input_B = 255
			
			; barre de selection
			NG_Sel_R = 52
			NG_Sel_G = 123
			NG_Sel_B = 196
			
			; fond des progress bar
			NG_PB_r = 255
			NG_PB_g = 255
			NG_PB_b = 255
			
			; pour les cottages
			NG_Cottage_R = 255
			NG_Cottage_G = 255
			NG_Cottage_B = 255
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "theme2\NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme2\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
						
			NG_file_CygneEclair$ = NG_Path$ + "Cygne\NG_CygneEclair"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , True )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , False )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"
			
		
		; COSMOS
		Case 6
		
			NG_DefaultSkin$ = "NGUI_files\NG_Cosmos.png"
		
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 255
			NG_Font_b = 255
			NG_Font_g = 255
			
			; couleurs normales pour les titres
			NG_TFont_r = 0
			NG_TFont_b = 0
			NG_TFont_g = 50
			
			; ombrage
			NG_ombrage = 200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
						
			; fenetres actives
			NG_red_s   = 218 : NG_red_e   = 136
			NG_green_s = 236 : NG_green_e  = 186
			NG_blue_s  = 255 : NG_blue_e   = 240
			
			; fenetres inactives
			NG_red_sI   = 208 : NG_red_eI   = 116
			NG_green_sI = 216 : NG_green_eI = 166
			NG_blue_sI  = 235 :  NG_blue_eI  = 220
			
			; pour les cadres des menus
			NG_Menu_Rs = 196  : NG_Menu_Re = 250
			NG_Menu_Gs = 196 : NG_Menu_Ge = 250
			NG_Menu_Bs = 196 : NG_Menu_Be = 250
			
			; bordures des fenetres
			NG_Bord_R = 0
			NG_Bord_G = 0
			NG_Bord_B = 0
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 230
			NG_Grad_Gl = 230
			NG_Grad_Bl = 230
			
			; coté assombri
			NG_Grad_Ro = 100
			NG_Grad_Go = 100
			NG_Grad_Bo = 100
			
			; boites d'aide
			NG_Help_r = 208
			NG_Help_g = 216
			NG_Help_b = 235
			
			; dégradés des boutons
			NG_IB_Rs = 126 : NG_IB_Re = 155
			NG_IB_Gs = 182 : NG_IB_Ge = 198
			NG_IB_Bs = 239 : Ng_IB_Be = 242
			
			; couleur du texte des inputs box
			NG_IT_R = 0
			NG_IT_G = 0
			NG_IT_B = 0
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 211
			NG_Input_G = 233
			NG_Input_B = 255
			
			; barre de selection
			NG_Sel_R = 52
			NG_Sel_G = 123
			NG_Sel_B = 196
			
			; fond des progress bar
			NG_PB_r = 0
			NG_PB_g = 0
			NG_PB_b = 0
			
			; pour les cottages
			NG_Cottage_R = 255
			NG_Cottage_G = 255
			NG_Cottage_B = 255
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "theme2\NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme2\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , True )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , False )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"
		
		; ZOND
		Case 7
		
			NG_DefaultSkin$ = "NGUI_files\NG_Zond.png"
			
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 0
			NG_Font_b = 0
			NG_Font_g = 0
			
			; couleurs normales pour les titres
			NG_TFont_r = 0
			NG_TFont_b = 0
			NG_TFont_g = 0
			
			; ombrage
			NG_ombrage = 200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
						
			; fenetres actives
			NG_red_s   = 196 : NG_red_e   = 250
			NG_green_s = 196 : NG_green_e  = 250
			NG_blue_s  = 196 : NG_blue_e   = 250
			
			; fenetres inactives
			NG_red_sI   = 162 : NG_red_eI   = 220
			NG_green_sI = 162 : NG_green_eI = 220
			NG_blue_sI  = 162 :  NG_blue_eI  = 220
			
			; pour les cadres des menus
			NG_Menu_Rs = 196  : NG_Menu_Re = 250
			NG_Menu_Gs = 196 : NG_Menu_Ge = 250
			NG_Menu_Bs = 196 : NG_Menu_Be = 250
			
			; bordures des fenetres
			NG_Bord_R = 0
			NG_Bord_G = 0
			NG_Bord_B = 0
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 230
			NG_Grad_Gl = 230
			NG_Grad_Bl = 230
			
			; coté assombri
			NG_Grad_Ro = 100
			NG_Grad_Go = 100
			NG_Grad_Bo = 100
			
			; boites d'aide
			NG_Help_r = 221
			NG_Help_g = 221
			NG_Help_b = 184
			
			; dégradés des boutons
			NG_IB_Rs = 196 : NG_IB_Re = 250
			NG_IB_Gs = 196 : NG_IB_Ge = 250
			NG_IB_Bs = 196 : Ng_IB_Be = 250
			
			; couleur du texte des inputs box
			NG_IT_R = 0
			NG_IT_G = 0
			NG_IT_B = 0
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 255
			NG_Input_G = 255
			NG_Input_B = 255
			
			; barre de selection
			NG_Sel_R = 200
			NG_Sel_G = 200
			NG_Sel_B = 200
			
			; fond des progress bar
			NG_PB_r = 255
			NG_PB_g = 255
			NG_PB_b = 255
			
			; pour les cottages
			NG_Cottage_R = 0
			NG_Cottage_G = 0
			NG_Cottage_B = 0
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "theme2\NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme2\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S_Blanc"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M_Blanc"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E_Blanc"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect_Blanc"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_CygneEclair$ = NG_Path$ + "Cygne\NG_CygneEclair"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , True )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , True )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"
			
		
		; ABÎMES
		Case 8
		
			NG_DefaultSkin$ = "NGUI_files\NG_Abimes.jpg"
			
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 255
			NG_Font_b = 255
			NG_Font_g = 255
			
			; couleurs normales pour les titres
			NG_TFont_r = 255
			NG_TFont_b = 255
			NG_TFont_g = 255
			
			; ombrage
			NG_ombrage = 200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
						
			; fenetres actives
			NG_red_s   = 0 : NG_red_e   = 48
			NG_green_s = 0 : NG_green_e  = 47
			NG_blue_s  = 0 : NG_blue_e   = 68
			
			; fenetres inactives
			NG_red_sI   = 0 : NG_red_eI   = 28
			NG_green_sI = 0 : NG_green_eI = 27
			NG_blue_sI  = 0 :  NG_blue_eI  = 48
			
			; pour les cadres des menus
			NG_Menu_Rs = 0  : NG_Menu_Re = 48
			NG_Menu_Gs = 0 : NG_Menu_Ge = 47
			NG_Menu_Bs = 0 : NG_Menu_Be = 68
			
			; bordures des fenetres
			NG_Bord_R = 0
			NG_Bord_G = 0
			NG_Bord_B = 0
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 100
			NG_Grad_Gl = 100
			NG_Grad_Bl = 100
			
			; coté assombri
			NG_Grad_Ro = 10
			NG_Grad_Go = 10
			NG_Grad_Bo = 10
			
			; boites d'aide
			NG_Help_r = 0
			NG_Help_g = 0
			NG_Help_b = 106
			
			; dégradés des boutons
			NG_IB_Rs = 0 : NG_IB_Re = 48
			NG_IB_Gs = 0 : NG_IB_Ge = 47
			NG_IB_Bs = 0 : Ng_IB_Be = 68
			
			; couleur du texte des inputs box
			NG_IT_R = 255
			NG_IT_G = 255
			NG_IT_B = 255
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 55
			NG_Input_G = 8
			NG_Input_B = 8
			
			; barre de selection
			NG_Sel_R = 56
			NG_Sel_G = 48
			NG_Sel_B = 162
			
			; fond des progress bar
			NG_PB_r = 0
			NG_PB_g = 0
			NG_PB_b = 0
			
			; pour les cottages
			NG_Cottage_R = 255
			NG_Cottage_G = 255
			NG_Cottage_B = 255
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "theme2\NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme2\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_O"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , True )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , True )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"
		
		
		
		; BLANC
		Case 9
		
			NG_DefaultSkin$ = "NGUI_files\NG_Blanc.jpg";.png"
			
			If NG_DefaultSkin$ <> ""
		
				NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
			EndIf
		
			; couleurs normales
			NG_Font_r = 0
			NG_Font_b = 0
			NG_Font_g = 0
			
			; couleurs normales pour les titres
			NG_TFont_r = 0
			NG_TFont_b = 0
			NG_TFont_g = 0
			
			; ombrage
			NG_ombrage = 200
			
			NG_Font_or = NG_Font_r + NG_ombrage
			NG_Font_ob = NG_Font_b + NG_ombrage
			NG_Font_og = NG_Font_g + NG_ombrage
						
			; fenetres actives
			NG_red_s   = 255 : NG_red_e   = 230
			NG_green_s = 255 : NG_green_e  = 230
			NG_blue_s  = 255 : NG_blue_e   = 230
			
			; fenetres inactives
			NG_red_sI   = 230 : NG_red_eI   = 210
			NG_green_sI = 230 : NG_green_eI = 210
			NG_blue_sI  = 230 :  NG_blue_eI  = 210
			
			; pour les cadres des menus
			NG_Menu_Rs = 245  : NG_Menu_Re = 220
			NG_Menu_Gs = 245 : NG_Menu_Ge = 220
			NG_Menu_Bs = 245 : NG_Menu_Be = 220
			
			; bordures des fenetres
			NG_Bord_R = 0
			NG_Bord_G = 0
			NG_Bord_B = 0
			
			; coté des boutons (3D)
			;------------------------
			; bordures
			NG_ButtonBordR = 0
			NG_ButtonBordG = 0
			NG_ButtonBordB = 0

			; coté éclairé
			NG_Grad_Rl = 230
			NG_Grad_Gl = 230
			NG_Grad_Bl = 230
			
			; coté assombri
			NG_Grad_Ro = 100
			NG_Grad_Go = 100
			NG_Grad_Bo = 100
			
			; boites d'aide
			NG_Help_r = 255
			NG_Help_g = 255
			NG_Help_b = 255
			
			; dégradés des boutons
			NG_IB_Rs = 245 : NG_IB_Re = 230
			NG_IB_Gs = 245 : NG_IB_Ge = 230
			NG_IB_Bs = 245 : Ng_IB_Be = 230
			
			; couleur du texte des inputs box
			NG_IT_R = 0
			NG_IT_G = 0
			NG_IT_B = 0
			
			; pour les inputs boxs et les checkbox
			NG_Input_R = 255
			NG_Input_G = 255
			NG_Input_B = 255
			
			; barre de selection
			NG_Sel_R = 200
			NG_Sel_G = 236
			NG_Sel_B = 255
			
			; fond des progress bar
			NG_PB_r = 100
			NG_PB_g = 100
			NG_PB_b = 100
			
			; pour les cottages
			NG_Cottage_R = 0
			NG_Cottage_G = 0
			NG_Cottage_B = 0
			
			; fichiers externes
			;NG_Path$            = "Ngui_files\"
			;NG_ext$             = ".png"
			NG_file_Mouse$      = NG_Path$ + "NG_mouse_"
			NG_file_Doseur$     = NG_Path$ + "theme2\NG_Doseur"
			NG_file_Check$      = NG_Path$ + "NG_Check"
			NG_file_Tabs$       = NG_Path$ + "NG_tabs"
			NG_file_Icons$      = NG_Path$ + "NG_icons"
			NG_file_ValueB$     = NG_Path$ + "NG_value"
			NG_file_Menu$       = NG_Path$ + "theme2\NG_menu"
			
			NG_file_Arrow$      = NG_Path$ + "NG_Arrow" 
			
			NG_file_TabIcon$    = NG_Path$ + "NG_TabIcon"
			
			NG_file_Sphere0$       = NG_Path$ + "NG_sphere0_Blanc"
			NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0_Blanc"
			
			NG_file_Sphere1$       = NG_Path$ + "NG_sphere0_Blanc"
			NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1_O_Blanc"
			
			NG_file_Sphere2$       = NG_Path$ + "NG_sphere0_Blanc"
			NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2_O_Blanc"
			
			NG_file_Sphere3$       = NG_Path$ + "NG_sphere0_Blanc"
			NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3_O_Blanc"
			
			NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S_Blanc"
			NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M_Blanc"
			NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E_Blanc"
			
			NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect_Blanc"
			
			NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
			NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
			
			NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
			; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres )
			NG_maskR=0 : NG_maskG=255 : NG_maskB=0
			
			; fonts
			NG_FontName$ = "Tahoma"
			NG_TitleFontA = LoadFont ( NG_FontName$ , 18 , True )
			NG_TitleFontB = LoadFont ( NG_FontName$ , 36 , True )
			
			NG_FontName$ = "Tahoma"
			NG_NormalFont = LoadFont ( NG_FontName$ , 16 , True )
			
			NG_FontName$ = "Tahoma"
			NG_CottageFont = LoadFont ( NG_FontName$ , 16 , True )
			
			SetFont NG_TitleFontA
			
			NG_Language$ = "FR"


			


			
		End Select


End Function
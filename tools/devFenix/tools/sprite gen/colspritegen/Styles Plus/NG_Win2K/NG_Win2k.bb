Function NG_Plus_win2k()

	NG_Optimizer_Gradients ( False )

	NG_ChangePath ( "Styles Plus\NG_Win2K" )

	NG_DefaultSkin$ = NG_Path$ + "w2k.png"
			
	If NG_DefaultSkin$ <> ""
		
		NG_DefaultSkinFile = LoadImage ( NG_DefaultSkin$ )
			
	EndIf
		
	; couleurs normales
	NG_Font_r = 0
	NG_Font_b = 0
	NG_Font_g = 0
			
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
	NG_red_s   = 27  : NG_red_e   = 27
	NG_green_s = 54 : NG_green_e = 54
	NG_blue_s  = 120 : NG_blue_e  = 120
			
	; fenetres inactives
	NG_red_sI   = 131  : NG_red_eI   = 131
	NG_green_sI = 131  : NG_green_eI = 131
	NG_blue_sI  = 131 : NG_blue_eI  = 131
			
	; pour les cadres des menus
	NG_Menu_Rs = 212  : NG_Menu_Re = 212
	NG_Menu_Gs = 208 : NG_Menu_Ge = 208
	NG_Menu_Bs = 200 : NG_Menu_Be = 200
			
	; bordures des fenetres
	NG_Bord_R = 200
	NG_Bord_G = 190
	NG_Bord_B = 180

	; cot� des boutons (3D)
	;------------------------
	; bordures
	NG_ButtonBordR = 0
	NG_ButtonBordG = 0
	NG_ButtonBordB = 0

	; cot� �clair�
	NG_Grad_Rl = 255
	NG_Grad_Gl = 255
	NG_Grad_Bl = 255
			
	; cot� assombri
	NG_Grad_Ro = 64
	NG_Grad_Go = 64
	NG_Grad_Bo = 64
									
	; boites d'aide
	NG_Help_r = 255
	NG_Help_g = 255
	NG_Help_b = 225
			
	; d�grad�s des boutons
	NG_IB_Rs = 212 : NG_IB_Re = 200
	NG_IB_Gs = 208 : NG_IB_Ge = 190
	NG_IB_Bs = 200 : Ng_IB_Be = 180
			
	; couleur du texte des inputs box
	NG_IT_R = 0
	NG_IT_G = 0
	NG_IT_B = 0
			
	; pour les inputs boxs et les checkbox
	NG_Input_R = 255
	NG_Input_G = 255
	NG_Input_B = 255
			
	; barre de selection
	NG_Sel_R = 100
	NG_Sel_G = 136
	NG_Sel_B = 206
			
	; fond des progress bar
	NG_PB_r = 255
	NG_PB_g = 255
	NG_PB_b = 255
			
	; pour les cottages
	NG_Cottage_R = 0
	NG_Cottage_G = 0
	NG_Cottage_B = 0
		
	; couleurs des masques (chargements des boutons "r�duire" et "fermer" des fenetres )
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
	NG_file_Sphere0_O$       = NG_Path$ + "NG_sphere0"
		
	NG_file_Sphere1$       = NG_Path$ + "NG_sphere1"
	NG_file_Sphere1_O$       = NG_Path$ + "NG_sphere1"

	NG_file_Sphere2$       = NG_Path$ + "NG_sphere2"
	NG_file_Sphere2_O$       = NG_Path$ + "NG_sphere2"

	NG_file_Sphere3$       = NG_Path$ + "NG_sphere3"
	NG_file_Sphere3_O$       = NG_Path$ + "NG_sphere3"

	NG_file_CottageS$      = NG_Path$ + "NG_Cottage_S"
	NG_file_CottageM$      = NG_Path$ + "NG_Cottage_M"
	NG_file_CottageE$      = NG_Path$ + "NG_Cottage_E"

	NG_file_CottageSelect$ = NG_Path$ + "NG_CottageSelect"
			
	NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
	NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"
	
	NG_file_Agent$ = NG_Path$ + "NG_Agent"
			
	; fonts
	NG_FontName$ = "Tahoma"
	NG_TitleFontA = LoadFont ( NG_FontName$ , 16 , True )
	NG_TitleFontB = LoadFont ( NG_FontName$ , 32 , True )
			
	NG_FontName$ = "Tahoma"
	NG_NormalFont = LoadFont ( NG_FontName$ , 16 , False )
			
	NG_FontName$ = "Tahoma"
	NG_CottageFont = LoadFont ( NG_FontName$ , 16 , False )
			
	SetFont NG_TitleFontA
			
	NG_Language$ = "FR"
			
End Function
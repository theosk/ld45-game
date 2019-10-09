;--------------------------------------------
; Modules : variables et Types Ngui
;--------------------------------------------

;-------------
; variables
;-------------

Global NG_xdisp ; resolution
Global NG_ydisp

;-------------
; fenetres
;-------------

; les id de fenetres
Global NG_WindowID

; la fenetre active (une seule à la fois)
Global NG_ActiveWindow

; mode déplacement de fenetres
Global NG_MoveWindow

; le curseur de la souris est sur...

; une fenetre INACTIVE
Global NG_OnInactiveWindow

; une fenetre ACTIVE
Global NG_OnActiveWindow

; une fenetre INFO est ouverte
Global NG_InfoWindow

;---------------------------
; Couleurs de l'interface
;---------------------------

; couleurs normales
Global NG_Font_r , NG_Font_b , NG_Font_g

; couleurs normales pour les titres
Global NG_TFont_r , NG_TFont_b , NG_TFont_g

; ombrage
Global NG_ombrage
Global NG_Font_or
Global NG_Font_ob
Global NG_Font_og

; fenetres actives
Global NG_red_s , NG_red_e
Global NG_green_s , NG_green_e
Global NG_blue_s, NG_blue_e

; fenetres inactives
Global NG_red_sI , NG_red_eI
Global NG_green_sI , NG_green_eI
Global NG_blue_sI , NG_blue_eI

; bordures des fenetres
Global NG_Bord_r
Global NG_Bord_g
Global NG_Bord_b

; dégradés des boutons

; bordures
Global NG_ButtonBordR
Global NG_ButtonBordG
Global NG_ButtonBordB

; coté éclairé
Global NG_Grad_Rl
Global NG_Grad_Gl
Global NG_Grad_Bl

; coté assombri
Global NG_Grad_Ro
Global NG_Grad_Go
Global NG_Grad_Bo

; boites d'aide
Global NG_Help_r
Global NG_Help_g
Global NG_Help_b

; dégradés des boutons
Global NG_IB_Rs , NG_IB_Re
Global NG_IB_Gs , NG_IB_Ge
Global NG_IB_Bs , Ng_IB_Be

; couleur du texte des inputs box
Global NG_IT_R
Global NG_IT_G
Global NG_IT_B

; pour les inputs boxs et les checkbox
Global NG_Input_R
Global NG_Input_G
Global NG_Input_B

; pour les barres de selection
Global NG_Sel_R
Global NG_Sel_G
Global NG_Sel_B

; pour les fonds des porgress bar
Global NG_PB_r
Global NG_PB_g
Global NG_PB_b

; pour les cadres des menus
Global NG_Menu_Rs , NG_Menu_Re
Global NG_Menu_Gs , NG_Menu_Ge
Global NG_Menu_Bs , Ng_Menu_Be

; pour les textes des cottages
Global NG_Cottage_R
Global NG_Cottage_G
Global NG_Cottage_B


; couleurs des masques (chargements des boutons "réduire" et "fermer" des fenetres et la souris )
Global NG_maskR , NG_maskG , NG_maskB

;---------------------------------------
; pointeurs de souris
;---------------------------------------

; attention : tous les pointeurs doivent etre enregistrés dans le meme fichier, mouse.png
; chaque icone doit etre englobée dans un carré de 25x25

Global NG_PointerOn = False
Global NG_PointerNum = 1
Dim NG_Pointer(4)

;-------------------------------------------------
; activation des ombres
;-------------------------------------------------
Global NG_Shadow = True

;--------------------------------
; Boites d'aide
;--------------------------------
Global NG_HelpLabel$
Global NG_HelpTime = 45
Global NG_HelpOn = True

;---------------------------------------------------------
; Localisation
;---------------------------------------------------------
Global NG_Language$ = "FR"

;---------------------------------
; Crédits
;---------------------------------
Global NG_AboutTitle$
Global NG_AboutText$
Global NG_AboutMail$


;---------------------------------
; selecteurs de fichiers
;---------------------------------
Global NG_FS
Global NG_FSpath
Global NG_FSshortcut
Global NG_FSfiles
Global NG_FSfilename
Global NG_FSextension
Global NG_FSok
Global NG_FScancel
Global NG_FSgo
Global NG_FSprev
Global NG_FSnew
Global NG_FSpreview

Global NG_FStitle$ = "Selecteur de fichier"
Global NG_FSpathLabel$ = "Chemin du répertoire : "
Global NG_FSfilenameLabel$ = "fichier : "
Global NG_FSextensionLabel$ = "type : "
Global NG_FSNewFolderTitle$ = "Nouveau Répertoire"
Global NG_FSNewFolderText$ = "Donnez un nom à votre nouveau répertoire : "
Global NG_FSsupprTitle$ = "Suppression"
Global NG_FSsupprLabel$ = "Voulez-vous vraiment supprimer l'élément séléctionné ?"
Global NG_FSsupprCancel$ = "Annuler"


Global NG_FS_dir
Global NG_FS_dirpath$
Global NG_FS_checkdir

Global NG_FS_Lastext$

Global NG_FSOpen

; variables
Global NG_FS_ExtSel$

Global NG_ActiveSphere = -1
Global NG_SphereFly = False
Global NG_SphereOn = True

;--------------------------------------------------------------------------
; skins des version Epsilon, etc.
;--------------------------------------------------------------------------
Global NG_DefaultSkin$ = ""
Global NG_DefaultSkinFile

;-------------------------------------
; color picker
;-------------------------------------
Global NG_CP
Global NG_CPok
Global NG_CPcancel
Global NG_CPr , NG_CPg , NG_CPb
Global NG_CPinpR , NG_CPinpG , NG_CPinpB
Global NG_CPDoseur
Global NG_CPimage
Global NG_CP_Mode = 1
Global NG_CPSwitch

Global NG_CP_MX , NG_CP_MY
Global NG_CP_ClairObscur
Global NG_CPmin = 255
Global NG_CP_ModeRendered
Global NG_CP_PrepareToRender


Global NG_CPtitle$ = "Selecteur de couleur"
Global NG_CPRed$   = "Rouge"
Global NG_CPGreen$ = "Vert"
Global NG_CPBlue$  = "Bleu"
Global NG_CPClair$ = "Clair"
Global NG_CPObscur$ = "Obscur"
Global NG_CPMelange$ = "Mélange"


;-------------
; Fonts NGui
;-------------
; fonts
;-------------
Global NG_TitleFontA
Global NG_TitleFontB
	
Global NG_NormalFont
Global NG_CottageFont


Const NG_Frames_Max = 10*22
Global NG_Font_no = 0

Global NG_FontSpace = 2 ; spacing between each letter
Global NG_Space = 4

Global NG_CurrentFont ; font utilisée
			
;-------------
; la souris
;-------------

Global NG_Path$            = "Ngui_files\"

Global NG_ext$             = ".png"

Global NG_file_Mouse$      = NG_Path$ + "NG_mouse_"

Global NG_file_sphere0$    = NG_Path$ + "NG_sphere0"
Global NG_file_sphere0_O$  = NG_Path$ + "NG_sphere0_O"

Global NG_file_sphere1$    = NG_Path$ + "NG_sphere1"
Global NG_file_sphere1_O$  = NG_Path$ + "NG_sphere1_O"

Global NG_file_sphere2$    = NG_Path$ + "NG_sphere2"
Global NG_file_sphere2_O$  = NG_Path$ + "NG_sphere2_O"

Global NG_file_sphere3$    = NG_Path$ + "NG_sphere3"
Global NG_file_sphere3_O$  = NG_Path$ + "NG_sphere3_O"

Global NG_file_Doseur$     = NG_Path$ + "NG_Doseur"
Global NG_file_Check$      = NG_Path$ + "NG_Check"
Global NG_file_Tabs$       = NG_Path$ + "NG_tabs"
Global NG_file_Icons$      = NG_Path$ + "NG_icons"
Global NG_file_ValueB$     = NG_Path$ + "NG_value"
Global NG_file_Menu$       = NG_Path$ + "NG_menu"

Global NG_file_Arrow$      = NG_Path$ + "NG_Arrow"

Global NG_file_Buttons$    = NG_Path$ + "NG_Buttons"

Global NG_file_CottageS$   = NG_Path$ + "NG_CottageS"
Global NG_file_CottageM$   = NG_Path$ + "NG_CottageM"
Global NG_file_CottageE$   = NG_Path$ + "NG_CottageE"

Global NG_file_CottageSelect$  = NG_Path$ + "NG_CottageSelect"

Global NG_file_CottageIconOn$  = NG_Path$ + "NG_CottageIconOn"
Global NG_file_CottageIconOff$ = NG_Path$ + "NG_CottageIconOff"

Global NG_file_Agent$   = NG_Path$ + "NG_Agent"

Global NG_file_TabIcon$ = NG_Path$ + "NG_TabIcon"

Global NG_MousePointer
Global NG_MouseOldClic1
Global NG_MouseClic1 , NG_MouseClic2 , NG_MouseClic3
Global NG_MouseClicX , NG_MouseClicY
Global NG_MouseUp1 ; quand on a relaché le 1er bouton de la souris

Global NG_MouseDragX , NG_MouseDragY
Global NG_MouseOldX , NG_MouseOldY
Global NG_MouseX , NG_MouseY , NG_MouseZ
Global NG_Mouse2Clic , NG_FirstClicTimer
Global NG_2ClicDelay = 10;650
Global NG_2ClicTimer
Global NG_MouseHold


;---------------------------------------
; Text Focus (TF) pour les Text inputs
;---------------------------------------
Global NG_TF$ = "|"
Global NG_TF_Timer
Global NG_TF_Limit = 10
Global NG_TF_pos

Global NG_TF_Id = -1 ; Id du text input qui a le TF
Global NG_TF_on = False

; timer pour le temps obligatoire avant d'entrer chaque caractere
Global NG_TextEnterTime
Global NG_TextEnterTimeMax = 5;10


;--------------------------------------------
; Stockage et chargement des images externes
;--------------------------------------------

; pour les boutons sur les barres titres des fenetres
Global NG_Sphere0 , NG_Sphere0_O
Global NG_Sphere1 , NG_Sphere1_O 
Global NG_Sphere2 , NG_Sphere2_O 
Global NG_Sphere3 , NG_Sphere3_O 

Global NG_CloseButton
Global NG_MinimizeButton
Global NG_CloseButton_O
Global NG_MinimizeButton_O

Global NG_Menu_S ; start
Global NG_Menu_M ; mid
Global NG_Menu_E ; end

; pour les fleches
Global NG_Arrow_UP
Global NG_Arrow_DOWN
Global NG_Arrow_LEFT
Global NG_Arrow_RIGHT

; pour l'agent de base
Global NG_AgentIcon


; pour les tabs
Global NG_Tab_P , NG_Tab_N
Global NG_ClassicTab
Global NG_TabIcon

; pour les doseurs
Global NG_Doseurs

; pour les values
Global NG_ValueB

; pour les checkbox
Global NG_Check

; pour le cottage
Global NG_CottageS
Global NG_CottageM
Global NG_CottageE

Global NG_CottageSelect

Global NG_CottageLoadOn = True

Global NG_WidthS , NG_WidthM , NG_WidthE
Global NG_HeightM

Global NG_DisplayTransparentWindowsForCottage = False

; pour les icones
Global NG_iconFolder
Global NG_iconVideo
Global NG_iconPhoto
Global NG_iconAudio
Global NG_iconText
Global NG_iconExe
Global NG_iconSystem
Global NG_iconShort

;--------------------
; types des gagdets
;--------------------

Global NG_OnGadget
Global NG_OldOnGadget

; pour les boutons spéciaux des gadgets
; ils n'empechent pas la capture des fenetre, juste leur déplacement
Global NG_OnSpecialGadget

; pour les doseur
Global NG_OnDoseur   ; vérifie si l'on modifie un doseur
Global NG_OnOldDoseur
Global NG_OnDoseurId ; Id du doseur en cours de modification
Global NG_OnDoseurSaveId = True ; interrupteur pour savoir s'il faut enregistrer l'id d'un doseur (1) ou non (0)

; pour les iconvievers
Global NG_OnIconViewer
Global NG_DragIcon
Global NG_DropIcon

; pour les values
Global NG_OnValue
Global NG_OnValueId
Global NG_OnValueSaveRes = True
Global NG_OnValueSaveY
Global NG_OnOldValue

; pour les menus
Global NG_MenuOpenId = -1
Global NG_OldMenuOpenId = NG_MenuOpenId
Global NG_MenuOpenLabel$
Global NG_MenuOpen_x , NG_MenuOpen_y
Global NG_SelectedMenu$
Global NG_parent_label$

; pour les sliders
Global NG_OnSlider   ; vérifie si l'on modifie un doseur
Global NG_OnSliderId ; Id du doseur en cours de modification
Global NG_OnSliderSaveId = True ; interrupteur pour savoir s'il faut enregistrer l'id d'un doseur (1) ou non (0)

; si une comboxBox est ouverte
Global NG_ComboBoxOpen , NG_OldComboBoxOpen
Global NG_ComboBoxOpenID = -1
Global NG_ComboBoxOnButton

;----------------------
; pour les cottages
;----------------------
Global NG_CottageOn = False
Global NG_CottagePx , NG_CottagePy
Global NG_CottageIconOn , NG_CottageIconOff

Global NG_OnCottage

Global CottageMadStart = 100



;-----------
; fenetres
;-----------
Type NG_window

	Field Id
	
	Field Display ; affichée ou pas
	
	Field Active

	Field Px , Py
	Field Tx , Ty
	
	Field BackGround
	
	Field Movable
	
	; origin
	Field origine_x
	Field origine_y
	
	Field Cottage
	
	Field CottageIconON
	Field CottageIconOFF
	
	Field CottageIconShake
	
	Field CottageMad
	
	Field Titre$
	Field Taille_titre
	Field Taille_BarreTitre , Debut_titre , Longueur_BarreTitre
	Field ombre
	
	Field Invisible
	
	Field DCmin
	
	; preview et autres modules
	Field killme ; flag : si à 1, fenetre détruite à la fin de son traitement dans DrawWindow
	Field preview
	Field media$
	Field moins , plus , image , image0 ; pour les images
	Field video , videoTX , videoTY ; pour les video
	Field sound , txt , base_txt$ , new_txt$ ; pour les sons
	Field frame3d , bup , bdown , model , cam , pivot , light , frameTX , frameTY , doseurR , doseurZ ; pour la 3d
	Field minput , textTX , textTY ; pour les textes
	
	Field FileSelectorPermission
	
	; organic
	Field organic
	Field organic_OA , organic_OI , organic_RA , organic_RI
	Field organic_Otx , organic_Oty , organic_Rtx , organic_Rty
		
	; icones
	Field icon_on
	Field icon
	
	; tabs (onglets)
	Field tab_on
	Field tab_max
	Field tab_current
	Field Tab[99]
	Field Tab_label$[99]
	Field tab_Px , tab_Py
	Field tab_Tx , tab_Ty , tab_LabelTy
	Field tab_icone[99]
	
	Field TitleLine
	
	Field IncludeForCottage
	
	Field SphereCode
	Field OpenSphere
	
	Field hide
	Field close , minimize
	
	Field grad
	
	Field skin_on
	Field skin
	Field skin_master
	
	;

End Type


;-------------------------------
; windows reperes pour cottage
;-------------------------------
Type NG_Cwindow

	Field Id
	Field WinId

End Type

;-----------------
; Frames
;-----------------
Type NG_Frame

	Field WinId
	
	Field Id
	Field Px , Py
	Field Tx , Ty
	
	Field show
	
	Field label$
	Field ombre
	
	Field ice
	
	Field r,g,b
	
	Field tab$
	
End Type


;---------
; textes
;---------
Type NG_Text

	Field WinId

	Field Id
	Field Px , Py
	Field Chars
	Field Center
	Field r , g , b
	Field Label$
	
	Field font
	
	Field ice
	
	; zones
	Field zone
	Field zone_xs , zone_ys
	Field zone_xe , zone_ye
	
	Field show
	
	Field tab$
	
	Field ombre

End Type

;----------
; boutons
;----------
Type NG_Button

	Field WinId
	
	Field Id
	Field Px , Py
	
	Field Tx , Ty
	
	Field state
	
	Field show
	
	Field ice
	
	Field Label$
	Field ombre
	
	Field Box
	
	Field forcombo
	
	Field immuable
	
	Field typ$
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b

End Type

;---------------
; bouton cycle
;---------------
Type NG_CycleButton

	Field WinId
	
	Field Id
	Field Px , Py
	
	Field Tx , Ty
	
	Field state
	
	Field show
	
	Field ice
	
	Field max_Label
	Field current_label
	
	Field Label$[5]
		
	Field ombre
	
	Field Box
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b

End Type

;---------------
; bouton image
;---------------
Type NG_ImageButton

	Field WinId
	
	Field Id
	Field Px , Py
	
	Field Tx,Ty
	
	Field image1 , image2
	
	Field state
	
	Field show
	
	Field ice
	
	Field resize
	
	Field box
	
	Field help$
	Field helpTime
	
	Field tab$
		
	Field r , g , b
	
	Field mr , mg , mb

End Type

;------------------
; bouton à cocher
;------------------
Type NG_CheckBox

	Field WinId
	
	Field Id
	Field Px , Py
	Field Tx , Ty
	
	Field Label$
	Field ombre
	
	Field show
	
	Field ice
	
	Field check
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b

End Type

;------------------
; combo box
;------------------
; menu déroulant
;------------------
Type NG_Combo

	Field WinId
	
	Field Id
	Field Px , Py
	
	Field Tx
	Field Ty
	
	Field Chars
	
	Field show
	
	Field ice
	
	Field button
	
	Field open
	
	Field selection_no
	Field selection$
	Field max_item
	
	Field lignMax
	Field lignMax_saved
	Field index , indexMax
	
	; boutons
	Field Bprev , Bnext , Bslider
	
	Field PreLabel$
	Field ombre
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b

End Type

;----------------------
; comboBox item
;----------------------
Type NG_ComboItem

	Field ComboId
	
	Field Label$

End Type


;------------
; value
;------------
Type NG_Value

	Field WinId
	
	Field Id
	Field Px , Py
	
	; integer
	Field min_int
	Field max_int
	Field value_int
	
	; float
	Field min#
	Field max#
	Field value#
	
	Field show
	
	Field ice
	
	Field pas#
	
	Field integer
	
	Field PreLabel$
	Field ombre
	
	Field editable
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b

End Type

;------------
; input text
;------------
Type NG_input

	Field WinId
	
	Field Id
	Field Px , Py
	
	Field Tx
	Field Ty
	
	Field Chars
	
	Field PreLabel$
	Field ombre
	
	Field show
	
	Field ice
	
	Field alpha
	
	Field Label$
	
	Field editable
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b

End Type

;------------------
; multi input text
;------------------
Type NG_MultiInput

	Field WinId
	
	Field Id
	Field Px , Py
	
	Field Chars
	Field Tx , Ty
	
	Field PreLabel$
	Field ombre
	
	Field lpp ; lignes par page
	
	Field alpha
	
	Field show
	
	Field ice
	
	; boutons
	Field Bprev , Bnext , Bslider
	Field slidX , slidY
	Field saveSlid
	Field editslid
	
	Field location
	Field maxlocation
	
	Field toTop
	
	Field Label$
	
	Field editable
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b

End Type

;---------
; doseur
;---------
Type NG_Doseur

	Field WinId
	
	Field Id
	Field Px , Py
	Field Tx
	
	Field min# , max#
	Field val#
	
	Field integer
	
	Field show
	
	Field ice
	
	Field Label$
	Field ombre
	
	Field info
	
	Field help$
	Field helpTime
	
	Field tab$
	
	Field r , g , b
	Field re, ge, be

End Type

;-----------------
; Image
;-----------------
Type NG_Image

	Field WinId
	
	Field image
		
	Field Id
	Field Px,Py
	Field Tx,Ty
	
	Field ombre
	
	Field show
	
	Field ice
	
	Field Bord
	
	Field truegadget
	
	; zones
	Field zone
	Field zone_xs , zone_ys
	Field zone_xe , zone_ye
	
	Field tab$
	
	Field Block
	
	Field r , g , b

End Type


;-----------------
; 3D Frame
;-----------------
Type NG_3dFrame

	Field WinId
	
	Field active
	
	Field Id
	Field Px,Py
	Field Tx,Ty
	
	Field r , g , b

	Field freq
	Field time
	
	Field show
	
	Field ice
	
	Field camera
	Field zoom#
	Field wire
	Field fog
	Field fog_s , fog_e
	Field range_s , range_e
	
	Field truegadget
	
	Field image
	
	Field tab$
	
	Field help$

End Type

;-----------------
; FPS
;-----------------
Type NG_FPS

	Field WinId
	
	Field Id
	Field Px,Py
	Field Tx,Ty
	
	Field Label$
	Field frames
	Field count_frames
	
	Field show
	
	Field ice
	
	Field oldtime
	Field newtime
	
	Field ombre
	
	Field r , g , b
	
	Field help$
	Field HelpTime
	
	Field tab$

End Type


;----------------------------------------
; IconViewer 
;----------------------------------------
Type NG_IconViewer

	Field WinId
	
	Field Id
	
	Field Px , Py
	
	Field show
	
	Field ice
	
	; nombre d'icones en X et en Y
	Field IconX , IconY
	
	; 16 ou 32
	Field IconSize
	
	; 1 horizontal, 2 vertical
	Field style
	
	; si les lables des icones sont affichés
	Field Label
	
	; boutons
	Field Bprev , Bnext , Bslider
	
	; nombre d'icones
	Field icons 
	Field Icons_disp
	
	; coordoonées de selection
	Field sel_x , sel_y
	Field sel_done
	Field sel_ID
	
	; ligne ou colone actuelle
	Field index
	
	Field drag
		
	Field help$
	Field helptime$
	
	Field tab$
		
End Type


;----------------------------------------
; Icone (pour les IconViewer)
;----------------------------------------
Type NG_Icon

	Field ViewerId
	
	Field Id
	
	Field Size
	
	Field image
	
	Field Label$

	Field Tag$

End Type


;----------------------------------------
; Color
;----------------------------------------
Type NG_Color

	Field WinId
	
	Field Id
	
	Field show
	
	Field ice
	
	Field Px , Py
	Field Tx , Ty
	
	Field R,G,B
	Field Mode
	
	Field Label$
	
	Field tab$
	
	Field help$
	Field helptime$

End Type


;----------------------------------------
; Progress
;----------------------------------------
Type NG_Progress

	Field WinId
	
	Field Id
	
	Field show
	
	Field ice
	
	Field Px , Py
	Field Tx , Ty
	
	Field R_s , G_s , B_s
	Field R_e , G_e , B_e
	
	Field grad
	
	Field LabelOn
	Field LevelOn
	
	Field Label$
	
	Field level#
	
	Field tab$
	
	Field ombre
	
	Field help$
	Field helptime$

End Type


;---------------------
; Pong
;---------------------
Type NG_Pong

	Field WinId
	
	Field Id
	
	Field show
	
	Field ice
	
	Field Px,Py
	Field Tx,Ty
	
	Field Player_Px , Player_Py
	Field Enemy_Px , Enemy_Py
	
	Field Raq_Tx , Raq_Ty
	
	Field Ball_Px,Ball_Py
	Field Ball_Tx ; uniforme
	
	Field Raq_saved_Py
	Field Ball_saved_Px , Ball_saved_Py
	
	Field mx , my ; mouvements de la balle
	
	Field Player_score , Enemy_score
	
	Field Start
	
	Field Titre$
	Field Info$ , timer
	
	Field tab$

End Type

;-----------------------------------------------
; Boards
;-----------------------------------------------
Type NG_Board

	Field WinId
	
	Field Id
	
	Field show
	
	Field ice
	
	Field Classement$
	Field Order$
	
	Field Px,Py
	Field Tx,Ty
	
	Field tab$
	
	Field help$

End Type

;-----------------------------------------------
; Board Col
;-----------------------------------------------
Type NG_BoardCol

	Field Id
	
	Field Button
	
	Field width

	Field BoardId
	
	Field Label$
	
End Type

;-----------------------------------------------
; Board Lines
;-----------------------------------------------
Type NG_BoardLine

	Field Id

	Field BoardId
	
	Field ColId
	
	Field Label$
	
	Field num
	
	Field ColLabel$
	
	Field tab$

End Type

;-----------------------------------------------
; Agents
;-----------------------------------------------
Type NG_Agent

	Field WinId

	Field Id

	Field show
	
	Field ice
	
	Field icon
	Field Px , Py
	Field Xs , Xe
	Field Ys , Ye
	Field LimitX
	Field LimitY

	Field tab$

	Field help$
	Field helptime$

End Type

;-----------------------------------------------
; Menus integrés aux fenetres
;-----------------------------------------------
Type NG_Menu

	Field WinId
	
	Field Id
	
	Field show
	
	Field ice
	
	Field Px , Py
	Field Tx , Ty
	
	Field Largeur
	
	Field sens
	
	Field tab$
	
	Field help$
	Field helptime$

End Type

;-----------------------------------------------
; Items des Menus integrés aux fenetres
;-----------------------------------------------
Type NG_MenuItem

	Field MenuId
	
	Field Id
	
	Field Label$
	Field Level
	
	Field me_parent
	Field parent$
	
	Field sublevel
	
End Type


;-----------------------------------------------
; liste des fichiers (file selector)
;-----------------------------------------------
Type NG_filelist
	
	Field name$
	Field typ
	Field ext$

End Type

;-----------------------------------------------
; cadres noirs des fermetures de fenetres
;-----------------------------------------------
Type NG_closeFX
	
	Field WinId
	Field Px , Py
	Field Tx , Ty
	
End Type
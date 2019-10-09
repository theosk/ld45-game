;-----------------------------
;    N I G H T   G U I       ;
;-----------------------------
;      E P S I L O N         ;
;-----------------------------
;        version 2.x         ;
;-----------------------------
; par Samir Moussouni        ;
; blurgzien@hotmail.com      ;
; www.vulturecreations.fr.st ;
;-----------------------------
; aout/sept 2004             ;
;-----------------------------


;-------------------------------------------------------------------
; Interface Graphique Utilisateur  Blitz2D / BlitzPlus / Blitz3D
;-------------------------------------------------------------------
;
; la compatibilit� Blitz2D/BlitzPlus n'est pas assur�e
; veillez, dans ce cas, � supprimer les fonctions 3D et
; non compatibles.
;
;-------------------------------------------------------------------


;----------------------------------
; include des modules
;----------------------------------

Include "Modules\NG_Variables.bb"
Include "Modules\NG_System.bb"
Include "Modules\NG_Options.bb"

Include "Modules\NG_Windows.bb"
Include "Modules\NG_Swindows.bb"

Include "Modules\NG_Update.bb"

Include "Modules\NG_ElementsCreation.bb"
Include "Modules\NG_ElementsUpdate.bb"

Include "Modules\NG_Mouse.bb"
Include "Modules\NG_Texts.bb"

Include "Modules\NG_Styles.bb"

;----------------------------------
; modules additionnels
;----------------------------------

Include "Modules\NG_Optimizer.bb"
Include "Modules\NG_Preview.bb"
Include "Modules\NG_Cygne.bb"
Include "Modules\NG_Organic.bb"



;-----------------------------------------------------
; LOGS / INFORMATIONS
;-----------------------------------------------------

;-----------------------

; update 2.45
	; ajout des fonctions : NG_AddIcon ( Id, image, label$ [,tag$] )
	; ainsi que NG_DeleteIconFromTag, NG_ReturnIconViewerTag$, NG_SetIconLabel,NG_SetIconLabelFromTag,
	; NG_SetIconTag et NG_SetIconTagFromTag.
	

; update 2.40
	; ajout des design tabs (actifs par d�faut, par rapport aux Classic Tabs)
	; fonction : NG_WindowTabOn ( winid, on=1 ) pour activer/desactiver les tabs
	; fonction : NG_SetClassicTab ( on=1 ) pour afficher le tab actuel seulement, sans icone (classique)
	; am�lioration de la fonction pour cr�er un tab : NG_AddTab ( winId , label$ , iconehandle=0 )
	; ajout d'images pour les boutons UP/DOWN/LEFT/RIGHT des gadgets
	; nouveau style : 7 - ZOND
	; nouveau style : 8 - AB�MES
	; nouveau style : 9 - BLANC
	; nouveau gadget : les Arrow Buttons.
	; ajout de la fonction NG_ReturnImage (ID) pour controler cette image
	; ajout de la fonction NG_SetWindowSize (ID,Tx=-1,Ty=-1)
	; les titres ne peuvent plus d�border en X
	; Nouveau Module : Preview. Pour visualiser rapidement differents fichiers (images,...)
	; integration de Preview au S�l�cteur de Fichiers.
	; correction d'un bug tout bete sur les 3d frames : les camera n'etaient pas supprim�es � la suppression des 3dframes.
	; ajout des fonctions NG_Show... comme NG_ShowText (Id,on=true) pour afficher/masquer certains gadgets
	; ajout du param�tre FolderOnly dans la fonction NG_OpenFileSelector pour ne retourner que le nom du dossier explorf�
	; (re)mise en place du systeme drawblock pour les gadgets images � fond noir.
	; syst�me des "Styles Plus" avec un exemple tout simple
	; ajout des gadgets Agents et leurs fonctions
	; NG_SetImageZone et NG_SetTextZone
	; NG_SetImagePosition
	; Textes personnalis�s
	; ajout des fonction NG_Freeze... ( Id , freeze = true )
	; amelioration du double clic qui fonctionne pareil sur tous les pc (meme les lents :p)
	; Module Organic pour skins freeforms et pour cr�er ses applications integr�es, � l'image de DashBoard ou de Konfabulator.

; update 2.15
	; correction d'un bugf tout bete pour les nouvelles combo boxes

; update 2.14
	; boutons up/down pour les combo boxes

; update 2.13
	; un truc tout bete sur le cottage
	; ajout de l'element de selection sur le cottage
	; optimisation des textes

; update 2.12
	; correction de tous les bugs du multi input
	; 3d frames plus rapides
	; ajout des variables ng_bord_x (x pour r,g,b) pour changer la couleur du bord des fenetres
	; ajout du style cosmos (6)
	; ajout du systeme de caract�res && pour un retour automatique � la ligne. G�r� m�me dans les fichiers externes.

; update 2.11
	; ajout de la fonction NG_SetMultiInputToTop pour placer le texte � la premiere ligne.

; update 2.10
	; ajout de nouveaux caract�res allemands dans la liste des cfaract�res support�s. Merci S.Sarbok.
	; mise en place du syst�me Power Modules
	; mise en place du module Optimizer!
	; ajout de la fonction NG_FileSelectorExtension$ () pour retrouver l'extension selectionn�e
	; mise en place d'un ascenceur/slider pour les multi input
	
; update 2.01
	; correction d'un bug mineur sur les barres titres...
	; ajout de la fonction NG_SetSphereMode ( true ) pour ne pas avoir � cliquer sur la 1�re sphere pour avoir les autres.

; update 2.0 Epsilon
	; mise en place du syst�me de spheres (0,1,2 ou 3)
	; ajout des barres titres (optionnel)
	; style 4 (epsilon simple)
	; style 5 (par d�faut : epsilon true)
	; cr�ation du Cottage et de ses fonctions dans le style de Mac Os et l�g�rement de Windows (barre des taches)
	; amelioration de la gestion des gadgets images avec les masks et les bordures � d�sactiver
	; correction d'un bug avec le chr$(10) pour revenir � la ligne dans un gadget texte...

;-----------------------

; update 1.31
	; menus 2 sub levels ( je bosse sur les sub levels infinis...)
	; correction d'un bugs avec les activations des fenetres dues au menus...

; update 1.30
	; menus ajout�s avec leurs fonctions ( 1 sub level pour l'instant )
	; fonction ajout�e : NG_SetWindowTab$ ( Id , Label$ )
	; on peut maintenant utiliser la touche suppr/del dans le selecteur de fichier pour editer les textes sans supprimer le fichier...

; update 1.25
	; bugs des frames corrig� (avant elles etaient trop lentes puisque l'image temporaire du cadre n'etait pas surprim�e... oubli !)
	; amelioration de la gestion des styles
	; am�lioration du selecteur de fichier : il conserve la derniere extension filtr�e
	; les values rendent le curseur insensible aux boutons
	; amelioration du code pour eviter certains bugs
	; ajout du classement par ordre alphabetique (merci a Nick Green)
	; amelioration de la fonction NG_MouseOnWindow() qui detecte mieux si une souris est sur une fenetre ou pas.
	; ajout des fonctions NG_ReturnDrop() et NG_ReturnDrag()


; update 1.22
	; am�liorations pour les inputs : possibilit� de deplacer le curseur, page down/up, home/end, suppr, insert, scrollings etc.

; update 1.21
	; la souris scrolle indefiniment avec les values
	; les icones s'affichent mieux quand une fenetre est reduite
	; nouveau design des checkboxes
	; correction d'un bug avec le color picker

; update 1.2
	; NG_AddWindowIcon ajout�
	; NG_SetShadow ajout� pour donner un peu de profondeur aux gadgets
	; ajouts des gadgets Value et leurs fonctions

; update 1.16
	; quelques bugs corrig�s � propos du selecteur de fichier et des fonctions [Movable]
	; bug sur les skins corrig�
	; progress bar et leurs fonctions ajout�es

; update 1.15
	; selecteur de fichier : classement dossier/fichiers
	; selecteur de fichier : plus de rajout intempestifs de "\" en double cliquant...
	; selecteur de fichier : debug texts effac�s
	; selecteur de fichier : double clic = valider
	; NG_SetFileSelectorLabels() pour changer les textes du selecteur de F.
	; NG_SetFileSelectorMovable() pour rendre cette fenetre deplacable ou non
	; NG_SetColorPickerMovable() pour rendre cette fenetre deplacable ou non
	; NG_SetWindowMovable() pour rendre une fenetre deplacable ou non
	; Flag 128 pour NG_CreateWindow pour activer le [ DoubleClic = Minimiser ]

; update 1.07 et 1.07b
	; quelques bugs du selecteur de fichier corrig�s
	; quelques bugs corrig�s

; update 1.06
	; bugs corrig�s sur les help box des inputs et des combos
	; nvlles fonctions plus rapides : NG_CreateImageFromHandle, NG_CreateImageButtonFromHandle et NG_SetImageFromHandle 

; update 1.05
	; meilleur affichage des doseurs
	; tous les bugs des doseurs sont corrig�s

; update 1.0
	; iconviewers et leurs fonctions
	; colors et leurs fonctions
	; selecteur de fichiers et ses fonctions
	; selecteur de couleurs et ses fonctions
	; support chr$(10) pour retour automatique � la ligne
	; ajout des fonctions NG_SetAllWindowsGradient et d'autres bien utiles
	; amelioration de la request window (entr�e = valider)

; update 0.97
	; 2 nvlles fonctions : NG_ReturnWindowPositionX () et NG_ReturnWindowPositionY ()
	; ajout du ColorPicker et ses fonctions
	; ajout des gadgets Color et leurs fonctions

; update 0.95
	; activation/desactivation des help boxes
	; ajout du parametrage des couleurs des barres de selection

; update 0.94
	; bug corrig� avec les inputs
	; bug corrig� avec les doseurs
	; ajout des fonctions NG_TranslateInto() et NG_DeleteAllWindows()
	; plusieurs petits bugs mineurs corrig�s...

; update 0.93
	; les boites help sont am�lior�es
	; les bugs des aides des checkboxes et des gadgets sur fenetres transparentes regl�s.
	; les textes des checkboxes sont un peu plus bas cette fois.
	; les fenetres et leurs gadgets sont selectionnables directement, m�me si ils n'�taient pas au 1er plan.

; update 0.92
	; le bug des valeurs n�gatives des doseurs (sliders) est regl�

; update 0.91
	; les ombres sont toujours d�sactiv�es par d�faut (plus de rapidit� !)

; update 0.9
	; possibilit� d'activer ou non les d�grad�s fenetres (plus de rapidit� !) 

; update 0.83
	; ajout des frames

; update 0.82
	; les skins suivent les positions des fenetres

; update 0.81
	; correction du bug de detection des gadgets pendant le deplacement d'une fenetre


;-----------------------------------------------------------------------
; Les prochaines versions suivront l'aphabet grec.
;-----------------------------------------------------------------------
; pour info : Alpha, Beta , Gamma, Delta , Epsilon, Dzeta, Eta, Theta,
; Iota, Kappa, Lambda, Mu, Nu, Xi, Omicron, Pi, Rho, Sigma, Tau, Upsilon
; Phi, Khi, Omega
;-----------------------------------------------------------------------



;-------------------
; fonctionalit�s :
;-------------------

; (voir les derniers updates worklogs pour les dernieres fonctionnalit�s)


;------------------------
; sp�cficit�s avanc�es
;------------------------
; socle fenetres visible/invisible pour une GUI plus discr�te
; Idem pour les boutons
; systeme de skins super simple � mettre en marche
; avec ou sans degrad�s
; traduction francais/anglais
; boite d'aides
; Onglets (Tabs)

; et bien d'autres... � vous de voir dans les exemples.

;-----------
; Gadgets :
;-----------
; 0)  Fenetres
		; (d�placements on/off, r�ductions on/off, fermetures on/off)

; 1)  Boutons
		; (on/off et avec/sans cadre)

; 2)  Boutons � cycle
		; (jusqu'� 5 �tats)

; 3)  text inputs
		; (numeriques ou alphanumeriques avec pav� num.)

; 4)  text inputs multilignes
		; (idem)

; 5)  combo box
		; (menu d�roulants)

; 6)  doseurs
		; (entiers ou d�cimaux)

; 7)  boutons � cocher
		; (on/off)

; 8)  images integr�es aux fenetres

; 9)  boutons images avec gestion de la transparence
		; (on/off et avec/sans cadre)

; 10) frames 3d
		; (pour inserer une vue camera 3d dans une fenetre)

; 11) FPS
		; (pour obtenir le nombre d'images par seconde)
		
; 12) Color
		; carr� couleurs pouvant etre modifi� via un selecteur de couleur
		
; 13) Frames

; 14) IconViewers
		; (horizontaux, verticaux, petites ou grandes icones, avec ou sans labels, avec ou sans drag and drop)

; 15) Values
		; (� la 3ds max)
		
; 16) Menus
		; (vers le bas ou vers le haut)
	
; 17) File selector

; 18) Color picker

; 19) Popups windows
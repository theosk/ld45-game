;--------------------------------------------
; Modules : Options g�n�rales Ngui
;--------------------------------------------

;---------------------------------------------------------
; Localisation
;---------------------------------------------------------
Function NG_TranslateInto ( language$ ) 

	
	If language$ = "FR"

		NG_AboutTitle$ = "A propos de l'auteur de Ngui Epsilon"
		NG_AboutText$  = "Ngui et Ngui Epsilon ont �t� cr��s par Samir Moussouni en 2004"
		NG_AboutMail$ = "Envoyer un Mail"
	
		NG_FStitle$ = "Selecteur de fichier"
		NG_FSpathLabel$ = "Chemin du r�pertoire : "
		NG_FSfilenameLabel$ = "fichier : "
		NG_FSextensionLabel$ = "type : "
		NG_FSNewFolderTitle$ = "Nouveau R�pertoire"
		NG_FSNewFolderText$ = "Donnez un nom � votre nouveau r�pertoire : "
		NG_FSsupprTitle$ = "Suppression"
		NG_FSsupprLabel$ = "Voulez-vous vraiment supprimer l'�l�ment s�l�ctionn� ?"
		NG_FSsupprCancel$ = "Annuler"
		
		NG_CPtitle$ = "Selecteur de couleur"
		NG_CPRed$   = "Rouge"
		NG_CPGreen$ = "Vert"
		NG_CPBlue$  = "Bleu"
		NG_CPClair$ = "Clair"
		NG_CPObscur$ = "Obscur"
		NG_CPMelange$ = "M�lange"

		
	Else If language$ = "ENG"
			
		NG_AboutTitle$ = "About the Author of Ngui Epsilon"
		NG_AboutText$  = "Ngui and Ngui Epsilon have been created by Samir Moussouni, in 2004"
		NG_AboutMail$ = "Send Mail"
	
		NG_FStitle$ = "File Selector"
		NG_FSpathLabel$ = "Folder path : "
		NG_FSfilenameLabel$ = "file : "
		NG_FSextensionLabel$ = "type : "
		NG_FSNewFolderTitle$ = "New folder"
		NG_FSNewFolderText$ = "Give a name to your new folder : "
		NG_FSsupprTitle$ = "Delete"
		NG_FSsupprLabel$ = "Are you sure ?"
		NG_FSsupprCancel$ = "Cancel"

		NG_CPtitle$ = "Color Picker"
		NG_CPRed$   = "Red"
		NG_CPGreen$ = "Green"
		NG_CPBlue$  = "Blue"
		NG_CPClair$ = "Bright"
		NG_CPObscur$ = "Dark"
		NG_CPMelange$ = "Melt"

	
	EndIf
	

End Function


;------------------------------------------------------
; Active/D�sactive l'affichage des boites d'aides
;------------------------------------------------------
Function NG_SetHelpDisplay ( on=True )

	NG_HelpOn = on
	
End Function


;--------------------------------------------------------
; Active/D�sactive l'ombrage des inputs et equivalents
;--------------------------------------------------------
Function NG_SetShadow ( on=True )

	NG_Shadow = on
	
End Function


;--------------------------------------------------------
; Active/D�sactive le mode fly pour les spheres
;--------------------------------------------------------
Function NG_SetSphereMode ( on=True )

	NG_SphereFly = on
	
End Function

;---------------------------------------------------------------------
; Change le chemin d'acc�s pour les chargement des fichiers externes
;---------------------------------------------------------------------
Function NG_ChangePath ( path$ )

	NG_path$ = path$
	
	If Right$ (NG_path$,1) <> "\"
		NG_path$ = NG_path$ + "\"
	EndIf

End Function

;-------------------------------
; active/d�sactive le cottage
;-------------------------------
Function NG_SetCottage ( Py , on=True , DisplayTransparentWindowsForCottage=False )

	NG_CottageOn = on
	
	;NG_CottagePx = Px
	NG_CottagePy = Py

	;---------------------------------------------
	; prise en compte des fenetres transparentes
	;---------------------------------------------
	NG_DisplayTransparentWindowsForCottage = DisplayTransparentWindowsForCottage


End Function
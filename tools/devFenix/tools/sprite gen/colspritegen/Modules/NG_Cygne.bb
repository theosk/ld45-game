;------------------------------------------------------------------------
; Module : Cygne
;------------------------------------------------------------------------


;-------------------------------------------
; déclaration des variables utiles à Cygne
;-------------------------------------------
Global NG_CygneOn = 0;True
Global NG_CygneLoadOn = NG_CygneOn

Global NG_CygneX# = 80000
Global NG_CygneY# = 80000
Global NG_CygneZ# = 10.0

Global NG_CygnePx#
Global NG_CygnePy#
Global NG_CygnePz#


;------------------------------------
; émulation 2D
;------------------------------------
Global NG_CygneImage

;------------------------
; 3D
;------------------------
Global NG_CygneCam
Global NG_CygneLight
Global NG_CygneBase

;---------------------
; Elements
;---------------------
Global NG_CygneEclair , NG_file_CygneEclair$ = "Cygne\NG_CygneEclair.png"

;-------------------
; Types
;-------------------
Type NG_CygneFX

	Field Id
	Field sprite
	Field x# , y#
	Field z#

	Field sx#
	Field sy#
		
	; paramètres
	Field speed#
		
	; FX
	Field life
	Field fx$

End Type



;----------------------------
; Fonctions :
;----------------------------

;---------------------------------------------
; Active/Désactive l'utilisation de Cygne
;---------------------------------------------
Function NG_Cygne ( on=True )

	NG_CygneOn = on

End Function


;---------------------------------------------
; Active/Désactive le chargement de Cygne
;---------------------------------------------
Function NG_CygneLoad ( on=True )

	NG_CygneLoadOn = on
	NG_CygneOn = on

End Function


;---------------------------------------------
; charge et crée les éléments de Cygne
;---------------------------------------------
Function NG_CygneLoadElements ()

	;--------------------------
	; Création des outils
	;--------------------------
	NG_CygneCam = CreateCamera ()
		PositionEntity NG_CygneCam , NG_CygneX# , NG_CygneY# , 0
		CameraProjMode NG_CygneCam , 1
	
	NG_CygneLight = CreateLight()
		PositionEntity NG_CygneLight , NG_CygneX# , NG_CygneY# , 0
		
		
	NG_CygneBase = CreatePlane ()
		PositionEntity NG_CygneBase , NG_CygneX# , NG_CygneY# , NG_CygneZ#
		TurnEntity NG_CygneBase , -90 , 0 , 0
		EntityAlpha NG_CygneBase , 0
		EntityPickMode NG_CygneBase , 2
		
	;-----------------------------------
	; émulation 2D
	;-----------------------------------
	NG_CygneImage = CreateImage ( GraphicsWidth() , GraphicsHeight() )		

	;--------------------------
	; création des éléments
	;--------------------------

	;------------
	; 1. Eclair
	;------------
	NG.NG_CygneFX = New NG_CygneFX
		NG\Id = Handle (NG)
		NG\sprite = LoadSprite ( NG_file_CygneEclair$ + NG_Ext$ )
		NG\sx = 1.0
		NG\sy = 1.0
		PositionEntity NG\sprite , NG_CygneX# , NG_CygneY# , NG_CygneZ#
		HandleSprite NG\sprite , 0 , 0
		HideEntity NG\sprite
		
		NG_CygneEclair = NG\Id
		

End Function



;------------------------------------------------------------
; Retrouve un élément de Cygne
;------------------------------------------------------------
Function NG_CygneFind ( Id )

	NG.NG_CygneFX = Object.NG_CygneFX (Id)
	
	If NG <> Null

		Return NG\sprite
	
	Else
	
		Return False
		
	EndIf
	
End Function


;------------------------------------------------------------
; Converti les coordonnées 2D en 3D pour Cygne
;------------------------------------------------------------
Function NG_Cygne3DCoord ( x , y )

	If NG_CygneOn = True
	
		CameraPick ( NG_CygneCam , x , y )
		
		If PickedEntity () = NG_CygneBase
	
			NG_CygnePx# = PickedX#()
			NG_CygnePy# = PickedY#() 
			NG_CygnePz# = PickedZ#()
	
		EndIf
	
	EndIf
		
End Function



;------------------------------------------------------------
; Dessine Cygne (éléments 3D) sur l'interface de Ngui
;------------------------------------------------------------
Function NG_DrawCygneFirst ()

	;-----------------------------
	; désactivé ?
	;-----------------------------
	If NG_CygneOn = False Then Return
	
	
	;--------------------------------------------------
	; Dessine les éléments
	;--------------------------------------------------
		
	;---------------------------
	; double clic ?
	;---------------------------
	If KeyDown(87);NG_DoubleClic = True
	
		NG.NG_CygneFX = Object.NG_CygneFX ( NG_CygneEclair )
	
		If NG <> Null
					
			NG_Cygne3DCoord ( MouseX() , MouseY() )
			
			PositionEntity NG\sprite , NG_CygnePX , NG_CygnePY , NG_CygnePZ
			
			ShowEntity NG\sprite
			
			;----------------------
			; Zoom
			;----------------------
			NG\fx$ = "zoom"
			NG\speed# = 1.0
			NG\life = 10
			NG\sx = 1;0.01
			NG\sy = 0.01
					
		EndIf
	
	EndIf
	
	NG_UpdateCygneFX ()
	
	UpdateWorld
	RenderWorld
		
	;------------------------------------------------------------------------------
	; on copie le backbuffer sur le buffer de l'image de Cygne
	;------------------------------------------------------------------------------
	; note : cette image est maskée noir, ce qui nous permet de tjs voir le fond
	;------------------------------------------------------------------------------
	CopyRect 0 , 0 , GraphicsWidth() , GraphicsHeight() , 0 , 0 , BackBuffer() , ImageBuffer ( NG_CygneImage )
	
	Cls
		
End Function



;------------------------------------------------------------
; Dessine Cygne (éléments 3D) sur l'interface de Ngui
;------------------------------------------------------------
Function NG_DrawCygneLast ()

	;-----------------------------
	; désactivé ?
	;-----------------------------
	If NG_CygneOn = False Then Return
	
	
	;-----------------------------------------------------------
	; redessine les éléments 3d de Cygne au 1er plan
	;-----------------------------------------------------------
	DrawImage NG_CygneImage , 0 , 0

	;-------------------------------
	; Dessine la souris ici
	;-------------------------------
	NG_DrawMouse ()

		
End Function



;----------------------------------------------------------------
; Update des FX de Cygne
;----------------------------------------------------------------
Function NG_UpdateCygneFX ()

	For NG.NG_CygneFx = Each NG_CygneFx

		;------------------------------------
		; que si sa vie est encore valable
		;------------------------------------
		If NG\life > 0
		
			;---------------------------------
			; liste des effets disponibles
			;---------------------------------
			Select NG\fx$
			
				Case "zoom"
				
					NG\sx = NG\sx - 0.4
					NG\sy = NG\sy + 0.4
					
					ScaleSprite NG\sprite , NG\sx , NG\sy
					
					;MoveEntity NG\sprite , 0 , 0 , - NG\speed#
				
			End Select	
		
			;---------------------------------
			; sa vie baisse
			;---------------------------------
			NG\life = NG\life - 1		
		
		Else
		
			HideEntity NG\sprite
		
		EndIf
	
	
	
	Next



End Function
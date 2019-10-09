;*
; *  COLOMBIAN PARTICLE GENERATOR
; *  Current release       : CPG 
; *  Last stable release   :
; *  Project documentation : 
; *
; *
; *  This program is free software; you can redistribute it and/or modify
; *  it under the terms of the GNU General Public License as published by
; *  the Free Software Foundation; either version 2 of the License, or
; *  (at your option) any later version.
; *
; *  This program is distributed in the hope that it will be useful,
; *  but WITHOUT ANY WARRANTY; without even the implied warranty of
; *  MERCHANTABILITY Or FITNESS For A PARTICULAR PURPOSE.  See the
; *  GNU General Public License For more details.
; *
; *  You should have received a copy of the GNU General Public License
; *  along with this program; if not, write to the Free Software
; *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
; *
; *  Copyright © 2006 Colombian Developers
; *  
; *  
; */

;/*
; * FILE        : colspritegen.bb
; * DESCRIPTION : PARTICLE generator
; *
; * HISTORY:
; * 
; *
; * V1.01  Initial release
; *
; */

Include "particulas/particle.bb"


Graphics3D 800,600,16,2


SetBuffer BackBuffer()


; include de la Ngui
;--------------------
Include "Ngui.bb"

; on active le theme
;------------------------
; le 1 est par défaut, donc dans ce cas, ce n'est obligatoire
;-------------------------------------------------------------
NG_SetStyle 4

; activation de la souris
;--------------------------
NG_ActiveMouse ( 1 )

; initialisation
;------------------
NG_Initialize ()



fenetreExp = NG_CreateWindow ( 20 , 20 , 350 , 550 , "Colombian Particle Generator. (CPG.XploGen)" , 1+2+8 )
	
	quitter = NG_CreateButton ( fenetreExp , 180 , 500 , 70 , 30 , "Quit" , 0 , "click to exit program" )

	NG_WindowTabOn ( fenetreExp )
	NG_AddTab ( fenetreExp , "Page1" ,LoadImage ( "maindata\particula.png" ))
	NG_AddTab ( fenetreExp , "Page2" ,LoadImage ( "maindata\particula.png" ))
	NG_AddTab ( fenetreExp , "Page3" ,LoadImage ( "maindata\particula.png" ))
	NG_AddTab ( fenetreExp , "Render" ,LoadImage ( "maindata\TabIcon_2.png" ))
	

    nombrescn= NG_CreateInput ( fenetreExp , 10 , 120 , 90 , 20 , "Scene: ", 1 ,   "no_name" , "Scene Name" )
    NG_AttachInput ( nombrescn, "Page1" )
  

    saveb = NG_CreateButton ( fenetreExp , 160 , 120 , 70 , 30 , "Save" , 0 , "Save scene" )
    NG_AttachButton ( saveb , "Page1" )
    loadb = NG_CreateButton ( fenetreExp , 160 , 160 , 70 , 30 , "Load" , 0 , "Load scene" )
    NG_AttachButton ( loadb , "Page1" )
 

	escenaactual = NG_CreateCombo ( fenetreExp , 20 , 160 , 110 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( escenaactual , "scene1" )
	NG_AddComboItem ( escenaactual , "scene2" )
	NG_SetCombo ( escenaactual  , "scene1" )	
    NG_AttachCombo (escenaactual  , "Page1" )	


    efectos = NG_CreateCombo ( fenetreExp , 20 , 250 , 110 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( efectos, "Particle1" )
	NG_AddComboItem ( efectos, "Particle2" )
	NG_AddComboItem ( efectos, "Particle3" )
	NG_AddComboItem ( efectos, "Particle4" )
	NG_AddComboItem ( efectos, "Particle5" )
	NG_AddComboItem ( efectos, "Particle6" )
	NG_AddComboItem ( efectos, "Particle7" )
	NG_AddComboItem ( efectos, "Particle8" )
	NG_AddComboItem ( efectos, "Particle9" )
	NG_SetCombo ( efectos , "Particle1" )	
    NG_AttachCombo ( efectos , "Page1" )	


	imagenparticula = NG_CreateCombo ( fenetreExp , 150 , 250 , 110 , 20 , "" , 0 , "" , 6 )
	NG_AddComboItem ( imagenparticula  , "image1" )
	NG_AddComboItem ( imagenparticula  , "image2" )
	NG_SetCombo ( imagenparticula  , "image1" )	
    NG_AttachCombo (imagenparticula   , "Page1" )	
 
    rgb_r = NG_CreateInput ( fenetreExp , 20 , 290, 40 , 20 , "Background R: ", 2 ,   0 , "Red" )
    NG_AttachInput ( rgb_r, "Page1" )
    rgb_g = NG_CreateInput ( fenetreExp , 180 , 290, 40 , 20 , "G: ", 2 ,   0 , "Green" )
    NG_AttachInput ( rgb_g, "Page1" )
    rgb_b = NG_CreateInput ( fenetreExp , 260 , 290, 40 , 20 , "B: ", 2 ,   0 , "Blue" )
    NG_AttachInput ( rgb_b, "Page1" )

    p_rgb_r = NG_CreateInput ( fenetreExp , 20 , 330, 40 , 20 , "Particle R: ", 2 ,   0 , "Red" )
    NG_AttachInput ( p_rgb_r, "Page1" )
    p_rgb_g = NG_CreateInput ( fenetreExp , 180 , 330, 40 , 20 , "G: ", 2 ,   0 , "Green" )
    NG_AttachInput ( p_rgb_g, "Page1" )
    p_rgb_b = NG_CreateInput ( fenetreExp , 260 , 330, 40 , 20 , "B: ", 2 ,   0 , "Blue" )
    NG_AttachInput ( p_rgb_b, "Page1" )

    p_radius_irx = NG_CreateInput ( fenetreExp , 20 , 380, 40 , 20 , "Radius ix: ", 2 ,   0 , "" )
    NG_AttachInput ( p_radius_irx, "Page1" )
    p_radius_orx = NG_CreateInput ( fenetreExp , 140 , 380, 40 , 20 , "ox: ", 2 ,   0 , "" )
    NG_AttachInput ( p_radius_orx, "Page1" )
    p_radius_iry = NG_CreateInput ( fenetreExp , 60 , 410, 40 , 20 , "iy: ", 2 ,   0 , "" )
    NG_AttachInput (p_radius_iry, "Page1" )
    p_radius_ory = NG_CreateInput ( fenetreExp , 140 , 410, 40 , 20 , "oy: ", 2 ,   0 , "" )
    NG_AttachInput (p_radius_ory, "Page1" )
    p_radius_irz = NG_CreateInput ( fenetreExp , 60 , 440, 40 , 20 , "iz: ", 2 ,   0 , "" )
    NG_AttachInput ( p_radius_irz, "Page1" )
    p_radius_orz = NG_CreateInput ( fenetreExp , 140 , 440, 40 , 20 , "oz: ", 2 ,   0 , "" )
    NG_AttachInput ( p_radius_orz, "Page1" )

;---------
	ar_inicio = NG_CreateInput ( fenetreExp , 20 , 120, 40 , 20 , "AlphaRange Start: ", 2 ,   0 , "" )
    NG_AttachInput (ar_inicio, "Page2" )
    ar_final = NG_CreateInput ( fenetreExp , 180 , 120, 40 , 20 , "End : ", 2 ,   0 , "" )
    NG_AttachInput ( ar_final, "Page2" )

	ec_sa = NG_CreateInput ( fenetreExp , 20 , 170, 40 , 20 , "EmitterCone stheta:", 2 ,   0 , "" )
    NG_AttachInput (ec_sa, "Page2" )
    ec_ea = NG_CreateInput ( fenetreExp , 180 , 170, 40 , 20 , "etheta: ", 2 ,   0 , "" )
    NG_AttachInput ( ec_ea, "Page2" )
	ec_si = NG_CreateInput ( fenetreExp , 20 , 200, 40 , 20 , "sphi:", 2 ,   0 , "" )
    NG_AttachInput (ec_si, "Page2" )
    ec_ei = NG_CreateInput ( fenetreExp , 110 , 200, 40 , 20 , "ephi: ", 2 ,   0 , "" )
    NG_AttachInput ( ec_ei, "Page2" )
    ec_scale = NG_CreateInput ( fenetreExp , 210 , 200, 40 , 20 , "scale: ", 2 ,   0 , "" )
    NG_AttachInput ( ec_scale, "Page2" )








    previewb = NG_CreateButton ( fenetreExp , 180 , 120 , 70 , 30 , "Preview" , 0 , "Preview Scene" )
    NG_AttachButton ( previewb , "Render" )


piv = CreatePivot()

;Global snap=0
;Global snapcount=0


camera = CreateCamera(piv)
MoveEntity camera,0,2,-8

HideEntity camera


SeedRnd MilliSecs() 


camera2 = CreateCamera(piv)
MoveEntity camera2,0,8,0

PointEntity camera2,piv


Salir=False

While ( Not Salir )  
  



  ; bouton quitter
  ;-----------------
  If NG_ReturnButton ( quitter ) Or NG_ReturnWindowOpen (fenetreExp) = False ;salir ?
			Salir=True		
  EndIf

  If NG_ReturnButton ( previewb )   ;preview ?
	Gosub HacerPreview		
  EndIf

;-------------------
  UpdateWorld
  RenderWorld   		
	
  NG_Update ()
	
  Flip	
Wend




NG_Kill ()



End





.HacerPreview
  spark=LoadSprite("particulas/texturas/P10.PNG",1)  
  HideEntity spark

  pe.peEmitter = pecreateEntityEmitter(0,4,0,spark)
  ;peSetLife(pe,30,30);

  peSetemitterRadius(pe,0,0,0,0,0,0)
  pesetemittercone(pe,-90,90,0,360,0.05)
  ;peAddvector(pe,0.001,0,0)
  ;peAddvector(pe,0,-0.001,0)
  peseteffectors(pe,1)

  For j = 10 To 255 Step 10
	peAddColor(pe,j,j/2,0)
  Next

  peSetAutoEmitter(pe,50,100,1) ;pe, particles,every,duration
  peActivateEmitter(pe)

  ec = 1

  While ( (ec > 0) And  (Not KeyDown(1)) ) 

   If ( ec > 0)  Then	
	 pc = peProcessParticles()
	 peCleanupParticles()
 	 ec = peProcessEmitters()
   EndIf	

;--------
   UpdateWorld
   RenderWorld   				
   Flip	
  Wend

  FreeEntity spark
  If pe <> Null Then pedestroyemitter(pe,1)
Return






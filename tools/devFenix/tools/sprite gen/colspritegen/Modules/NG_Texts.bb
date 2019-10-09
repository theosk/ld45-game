;------------------------------------------------------------------------
; Modules : gestion des textes Ngui
;------------------------------------------------------------------------

;------------------------------------------------------
; Affiche un texte avec retour à la ligne automatique
;------------------------------------------------------
Global NG_lmax = 1000

Dim Lign$( NG_lmax ) ; tableau pour les lignes (nombre de lignes max)

Function NG_DisplayText( thetext$ , xpos , ypos , max , center=0 )

	;-------
	; reset
	;-------
	lmax = NG_lmax

	For x=0 To NG_lmax
	
		lign$ (x) = ""
	
	Next
	
	NumberCar = 1
	MaxCar    = max     ; nombre de caracteres maxi par lignes
	espace    = 0
	
	lenght           = Len ( thetext$ ) ; nbr de caractere dans le texte
	tmp_NumberLines# = Float# ( lenght ) / Float (MaxCar)
	
	
	If tmp_NumberLines# > Floor#(tmp_NumberLines#)
		
		NumberLines = Floor#(tmp_NumberLines#) + 1
	
	Else
		
		NumberLines = Floor#(tmp_NumberLines#)
	
	EndIf
	
	newcar = 38 ;&
	
	For x=1 To NumberLines
	
		If NumberCar + MaxCar - 1 < lenght
		
			If x = NumberLines Then NumberLines = NumberLines + 1
			
			For t = NumberCar To ( NumberCar + MaxCar - 1 )
			
				doublecar = False
			
				;-----------------------------------------------
				; reperage du double && pour retour à la ligne
				;-----------------------------------------------
				If Mid$ ( thetext$ , t , 1 ) = Chr$ (newcar)
				
					If Mid$ ( thetext$ , t+1 , 1 ) = Chr$ (newcar)
					
						doublecar = True
					
					EndIf
				
				EndIf
			
				;------------------------------------------------------------------------
				; ici on vérifie la présence d'un caractere spécial retour à la ligne
				;------------------------------------------------------------------------
				If Mid$(thetext$,t,1) = Chr$(10) Or doublecar = True
									
					espace = t
					
					coupe = True
					
					Exit
				
				EndIf			
			
				If Mid$(thetext$,t,1) = Chr$(32) Then espace = t
			
			Next
	
			If espace = 0 Then espace = ( NumberCar + MaxCar - 1 )
			
			If coupe = False
			
				For c = NumberCar To espace
				
					Lign$(x) = Lign$(x) + Mid$(theText$,c,1)
				
				Next
			
			Else
			
				For c = NumberCar To espace-1
				
					Lign$(x) = Lign$(x) + Mid$(theText$,c,1)
				
				Next
			
			EndIf
			
			If doublecar = False
			
				NumberCar = espace + 1
			
			Else
			
				NumberCar = espace + 2
			
			EndIf
			
			
			Saved_pos = NumberCar
			
			Text ( xpos , x*20 + ypos , Lign$(x) ) ;, center ; on affiche la ligne
		
		Else If NumberCar + MaxCar - 1 >= lenght
		
			x2 = x
			
			car = NumberCar
			
			While car <= lenght
			
				doublecar = False

				;---------------------------
				; reset
				;---------------------------
				;Lign$ (x2) = ""			
				
				;-----------------------------------------------
				; reperage du double && pour retour à la ligne
				;-----------------------------------------------
				If Mid$ ( thetext$ , car , 1 ) = Chr$ (newcar)
				
					If Mid$ ( thetext$ , car+1 , 1 ) = Chr$ (newcar)
					
						doublecar = True
					
					EndIf
				
				EndIf
				
				;------------------------------------------------------------------------
				; ici on vérifie la présence d'un caractere spécial retour à la ligne
				;------------------------------------------------------------------------
				
				coupe = False
				
				If Mid$ ( thetext$ , car , 1 ) = Chr$ ( 10 ) Or doublecar = True
				
					coupe = True
					
					;--------------------------------------------------------------------------------------------
					; d'abord on inscrit la ligne sans tenir compte du ou des dernier caractère(s) spécial(aux)
					;--------------------------------------------------------------------------------------------			
					If Mid$ ( thetext$ , car-1 , 1 ) <> "&"
						If Mid$ ( thetext$ , car-2 , 1 ) <> "&"
							Text ( xpos , x2*20 + ypos , Lign$(x2) )
						EndIf
					EndIf
					
					;-------------------------------------------------------
					; ensuite on passe à la ligne suivante
					;-------------------------------------------------------
					x2 = x2 + 1
					
					;---------------------------
					; reset
					;---------------------------
					;Lign$ (x2) = ""
					
					;------------------------------------------------
					; on saute autant de caractère qu'il le faut
					;------------------------------------------------
					If doublecar = False
					
						car = car + 1
					
					Else If doublecar = True
					
						car = car + 2
					
					EndIf
										
																		
				EndIf 
		
				If Mid$ ( thetext$ , car+1 , 1 ) <> "&"
					If Mid$ ( thetext$ , car+2 , 1 ) <> "&"
 
						Lign$(x2) = Lign$(x2) + Mid$( theText$ , car , 1 )
						car = car + 1
				
					EndIf
				EndIf
			
			
			Wend
			
			Text ( xpos , x2*20 + ypos , Lign$(x2) ) ; on affiche la ligne
			
		EndIf
	
	Next

End Function


;-------------------------------------------------------------------------------------------------------------------------------------------------
; la même fonction mais en mieux pour éviter de prendre en compte tout le texte non visible, ce qui est à éviter à cause des ralentissements
;-------------------------------------------------------------------------------------------------------------------------------------------------
Function NG_DisplayTextFast ( thetext$ , xpos , ypos , max , limit_y_start=-9999 , limit_y_end=-9999 , center=0 )

	lmax = NG_lmax

	For x=0 To NG_lmax
	
		lign$ (x) = ""
	
	Next
	
	NumberCar = 1
	MaxCar    = max     ; nombre de caracteres maxi par lignes
	espace    = 0
	
	lenght           = Len ( thetext$ ) ; nbr de caractere dans le texte
	tmp_NumberLines# = Float# ( lenght ) / Float (MaxCar)
	
	
	If tmp_NumberLines# > Floor#(tmp_NumberLines#)
		
		NumberLines = Floor#(tmp_NumberLines#) + 1
	
	Else
		
		NumberLines = Floor#(tmp_NumberLines#)
	
	EndIf
	
	newcar = 38 ;&
	
	For x=1 To NumberLines
	
		;------------------------------------------------------------------------------------------------------
		; astuce FAST (postérieure) : si la ligne dépasse la limite, on s'arrete
		; là puisque le reste ne sera pas visible
		;------------------------------------------------------------------------------------------------------
		If (x*20 + ypos) > limit_y_end
		
			If limit_y_end <> -9999
			
				Return
			
			EndIf
		
		EndIf
	
	
		If NumberCar + MaxCar - 1 < lenght
		
			If x = NumberLines Then NumberLines = NumberLines + 1
			
			For t = NumberCar To ( NumberCar + MaxCar - 1 )
			
				doublecar = False
			
				;-----------------------------------------------
				; reperage du double && pour retour à la ligne
				;-----------------------------------------------
				If Mid$ ( thetext$ , t , 1 ) = Chr$ (newcar)
				
					If Mid$ ( thetext$ , t+1 , 1 ) = Chr$ (newcar)
					
						doublecar = True
					
					EndIf
				
				EndIf
			
				;------------------------------------------------------------------------
				; ici on vérifie la présence d'un caractere spécial retour à la ligne
				;------------------------------------------------------------------------
				If Mid$(thetext$,t,1) = Chr$(10) Or doublecar = True
									
					espace = t
					
					coupe = True
					
					Exit
				
				EndIf			
			
				If Mid$(thetext$,t,1) = Chr$(32) Then espace = t
			
			Next
	
			If espace = 0 Then espace = ( NumberCar + MaxCar - 1 )
			
			If coupe = False
			
				For c = NumberCar To espace
				
					Lign$(x) = Lign$(x) + Mid$(theText$,c,1)
				
				Next
			
			Else
			
				For c = NumberCar To espace-1
				
					Lign$(x) = Lign$(x) + Mid$(theText$,c,1)
				
				Next
			
			EndIf
			
			If doublecar = False
			
				NumberCar = espace + 1
			
			Else
			
				NumberCar = espace + 2
			
			EndIf
			
			
			Saved_pos = NumberCar
			
			;------------------------------------------------------------------------------------------------------
			; astuce FAST (antérieure) : si la ligne n'est pas encore dans la limite de départ
			; on s'arrete là puisque ces lignes ne seront pas visibles
			;------------------------------------------------------------------------------------------------------
			If (x*20 + ypos) < limit_y_start
			
				If limit_y_start <> -9999
				
					okligne = False
				
				Else
				
					okligne = True
				
				EndIf
			
			Else
			
				okligne = True
			
			EndIf

			If okligne = True
			 
				Text ( xpos , x*20 + ypos , Lign$(x) ) ;, center ; on affiche la ligne
	
			EndIf	
		
		Else If NumberCar + MaxCar - 1 >= lenght
		
			x2 = x
			
			car = NumberCar
			
			While car <= lenght
			
				doublecar = False

				;---------------------------
				; reset
				;---------------------------
				;Lign$ (x2) = ""			
				
				;-----------------------------------------------
				; reperage du double && pour retour à la ligne
				;-----------------------------------------------
				If Mid$ ( thetext$ , car , 1 ) = Chr$ (newcar)
				
					If Mid$ ( thetext$ , car+1 , 1 ) = Chr$ (newcar)
					
						doublecar = True
					
					EndIf
				
				EndIf
				
				;------------------------------------------------------------------------
				; ici on vérifie la présence d'un caractere spécial retour à la ligne
				;------------------------------------------------------------------------
				
				coupe = False
				
				If Mid$ ( thetext$ , car , 1 ) = Chr$ ( 10 ) Or doublecar = True
				
					coupe = True
					
					;--------------------------------------------------------------------------------------------
					; d'abord on inscrit la ligne sans tenir compte du ou des dernier caractère(s) spécial(aux)
					;--------------------------------------------------------------------------------------------			
					If Mid$ ( thetext$ , car-1 , 1 ) <> "&"
						If Mid$ ( thetext$ , car-2 , 1 ) <> "&"
							Text ( xpos , x2*20 + ypos , Lign$(x2) )
						EndIf
					EndIf
					
					;-------------------------------------------------------
					; ensuite on passe à la ligne suivante
					;-------------------------------------------------------
					x2 = x2 + 1
					
					;---------------------------
					; reset
					;---------------------------
					;Lign$ (x2) = ""
					
					;------------------------------------------------
					; on saute autant de caractère qu'il le faut
					;------------------------------------------------
					If doublecar = False
					
						car = car + 1
					
					Else If doublecar = True
					
						car = car + 2
					
					EndIf
										
																		
				EndIf 
		
				If Mid$ ( thetext$ , car+1 , 1 ) <> "&"
					If Mid$ ( thetext$ , car+2 , 1 ) <> "&"
 
						Lign$(x2) = Lign$(x2) + Mid$( theText$ , car , 1 )
						car = car + 1
				
					EndIf
				EndIf
			
			
			Wend
			
			Text ( xpos , x2*20 + ypos , Lign$(x2) ) ; on affiche la ligne
			
		EndIf
	
	Next

End Function


;-------------------------------------------------------------
; Affiche un petit carré d'aide sur le gadget en question
;-------------------------------------------------------------
Function NG_DisplayHelpBox ( Px , Py , Label$ )

	Color NG_Help_r , NG_Help_g , NG_Help_b
	
	SetFont NG_NormalFont
	
	Tx = StringWidth ( Label$ ) + 10
	Ty = FontHeight ( ) + 4
	
	;-------------------------------------
	; On affiche la boite aide
	;-------------------------------------
	
	Rect Px , Py , Tx , Ty , True
	NG_DrawTrueBordure ( Px-1 , Py-1 , Tx+2 , Ty+2 )
	
	;-------------------------------------
	; Puis le texte
	;-------------------------------------
	Color NG_Font_r , NG_Font_g , NG_Font_b
	NG_DisplayText( Label$ , Px + 5 , Py - 20 , 100 )

End Function


;-------------------------------
; Met à jour le Text Focus (TF) 
;-------------------------------
Function NG_UpdateTF ()

	NG_TF_Timer = NG_TF_Timer - 1
	
	If NG_TF_Timer < ( NG_TF_Limit * -1 )
	
		NG_TF_Timer = NG_TF_Limit
		
	EndIf

End Function


;-------------------------------------------
; Gestion de la saisie sur un text input
;-------------------------------------------
Function NG_UpdateInputLabel$ ( label$ , alphanum , cpl=10 , cpp=0 )

	;---------------------------
	; par défaut
	;---------------------------
	new_label$ = label$

	;--------------------------------------------------------
	; liste des caracteres disponibles pour Ngui
	;--------------------------------------------------------
	If alphanum = True
		liste$ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890,;:!.&é'(-è_çà)=-+?/%*$[]°^¨ù£€\ÜüÖöäÄ<> "
	Else
		liste$= "1234567890.- "
	EndIf

	;-------------------------
	; Quelle touche appuyée ?
	;-------------------------
	car = GetKey ()
	
	;RuntimeError car 
	
	;-----------------------------------------------------------------
	; Si le caractere spécifiée est valide parmis les disponibles
	; ----------------------------------------------------------------
	If car >= Asc ("a") And car <= Asc ("z")
		;car = car - 32
	EndIf
							
	Disponibilite = ( Instr ( liste$ , Chr$ (car) , 1 ) > 0 )
	
	;--------------------------------------------------
	; on descend le compteur qui limite les keydown
	;--------------------------------------------------
	If NG_TextEnterTime >= 0
		NG_TextEnterTime = NG_TextEnterTime - 1								
	EndIf
	
	; ------------------------------
	; alors on agit en conséquence
	; ------------------------------
		If Disponibilite
													
			new_label$ = Left$ ( Label$ , NG_TF_pos ) + Chr (car) + Mid$ ( Label$ , NG_TF_pos + 1 )				
			
			Label_Len = Label_Len + 1
			
			NG_TF_pos = NG_TF_pos + 1
		
		Else
		
			;-------------------------------------------------------------------------------
			; Si ce n'était pas un caractère valide, c'était peut etre une de ces touches
			;-------------------------------------------------------------------------------
		
			; ---------------------------
			; Touche Retour Chariot <-
			; ---------------------------	
			If KeyDown(14) And NG_TF_pos > 0 And NG_TextEnterTime < 0
			 
				NG_TextEnterTime = NG_TextEnterTimeMax
			
				new_Label$ = Left$ ( Label$ , NG_TF_pos - 1 ) + Mid$ ( Label$ , NG_TF_pos + 1 )
				
				Label_Len = Label_Len - 1
				
				NG_TF_pos = NG_TF_pos - 1
			
			EndIf
			
			; ---------------------------
			; Touche Espace
			; ---------------------------	
			If KeyHit(57); And NG_TextEnterTime < 0
			 
				NG_TextEnterTime = NG_TextEnterTimeMax
			 
				new_Label$ = Left$ ( Label$ , NG_TF_pos ) + Chr$(32) + Mid$ ( Label$ , NG_TF_pos + 1 )
				
				Label_Len = Label_Len + 1
				
				NG_TF_pos = NG_TF_pos + 1
			
			EndIf
			
			; ---------------------------
			; Touche Suppr
			; ---------------------------	
			If KeyDown(211) And NG_TF_pos < Len ( Label$ ) And NG_TextEnterTime < 0
			 
				NG_TextEnterTime = NG_TextEnterTimeMax

				new_Label$ = Left$ ( Label$ , NG_TF_pos ) + Mid$ ( Label$ , NG_TF_pos + 2 )
				
				Label_Len = Label_Len - 1
				
				NG_TF_pos = NG_TF_pos 
			
			EndIf
	
			; ---------------------------
			; Touche <-
			; ---------------------------	
			If KeyDown(203) And NG_TF_pos > 0 And NG_TextEnterTime < 0
			 
				NG_TextEnterTime = NG_TextEnterTimeMax
			 
				NG_TF_pos = NG_TF_pos - 1 
			
			EndIf
	
			; ---------------------------
			; Touche ->
			; ---------------------------	
			If KeyDown(205) And NG_TF_pos < Len ( Label$ ) And NG_TextEnterTime < 0
			 
				NG_TextEnterTime = NG_TextEnterTimeMax

			 	NG_TF_pos = NG_TF_pos + 1
			
			EndIf
			
			; ---------------------------
			; Touche ^ haut
			; ---------------------------	
			If KeyDown(200) And NG_TF_pos > 0 And NG_TextEnterTime < 0
				
				If NG_TF_pos - cpl >=0
			 
					NG_TextEnterTime = NG_TextEnterTimeMax
	
				 	NG_TF_pos = NG_TF_pos - cpl
			
				EndIf
			
			EndIf
			
			; ---------------------------
			; Touche v bas
			; ---------------------------	
			If KeyDown(208) And NG_TF_pos < Len ( Label$ ) And NG_TextEnterTime < 0
			 
				If NG_Tf_pos + cpl <= Len ( label$ )
				
					NG_TextEnterTime = NG_TextEnterTimeMax
	
			 		NG_TF_pos = NG_TF_pos + cpl
		
				EndIf
			
			EndIf
			
			; ---------------------------
			; Touche Home
			; ---------------------------	
			If KeyDown(199) And NG_TextEnterTime < 0
			 
				NG_TextEnterTime = NG_TextEnterTimeMax
	
			 	NG_TF_pos = 0
		
			EndIf
			
			; ---------------------------
			; Touche End
			; ---------------------------	
			If KeyDown(207) And NG_TextEnterTime < 0
			 
				NG_TextEnterTime = NG_TextEnterTimeMax
	
			 	NG_TF_pos = Len ( label$ )
		
			EndIf
			
			; ---------------------------
			; Touche Page Prev 
			; ---------------------------	
			If KeyDown(201) And NG_TF_pos > 0 And NG_TextEnterTime < 0
				
				If NG_TF_pos - cpp >= 0
			 
					NG_TextEnterTime = NG_TextEnterTimeMax
	
				 	NG_TF_pos = NG_TF_pos - cpp
			
				EndIf
			
			EndIf

			
			; ---------------------------
			; Touche Page Next
			; ---------------------------	
			If KeyDown(209) And NG_TF_pos < Len ( Label$ ) And NG_TextEnterTime < 0
				
				If NG_TF_pos + cpp <= Len ( label$ )
						 
					NG_TextEnterTime = NG_TextEnterTimeMax
	
				 	NG_TF_pos = NG_TF_pos + cpp
			
				EndIf
			
			EndIf

	
			; ---------------------------
			; Touche Entrée L>
			; ---------------------------	
			If KeyHit(28) Or KeyHit(156)
			 
				NG_TF_Id = -1
			
			EndIf
			
			; ---------------------------
			; Gestion du Pavé numérique
			; ---------------------------
			If KeyHit(82)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "0" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(83)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "." + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(79)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "1" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(80)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "2" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(81)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "3" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(75)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "4" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(76)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "5" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(77)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "6" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(71)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "7" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(72)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "8" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(73)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "9" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(78)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "+" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(74)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "-" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(55)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "*" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			Else If KeyHit(181)
				new_label$ = Left$ ( Label$ , NG_TF_pos ) + "/" + Mid$ ( Label$ , NG_TF_pos + 1 )				
				Label_Len = Label_Len + 1
				NG_TF_pos = NG_TF_pos + 1
			EndIf
	
	
		EndIf
		
	FlushKeys()
	
	
	Return new_label$

End Function
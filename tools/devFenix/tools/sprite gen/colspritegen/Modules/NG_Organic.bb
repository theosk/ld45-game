;------------------------------------------------------------------------
; Modules : Organic (set de fonctions pour les skins freeform)
;------------------------------------------------------------------------

;--------------------------------------------------------
; Active / Désactive la skin freeform d'une fenetre
;--------------------------------------------------------
Function NG_Organic_Window ( WinId , on=True , openactive=0 , openinactive=0 , reduceactive=0 , reduceinactive=0 )

	NG.NG_Window = Object.NG_Window(WinId)
	
	If NG <> Null

		NG\organic = on
		
		NG\organic_OA = openactive
		NG\organic_OI = openinactive
		NG\organic_RA = reduceactive
		NG\organic_RI = reduceinactive
		
		; régulations
		NG\organic_Otx = ImageWidth ( openactive )
		NG\organic_Oty = ImageHeight ( openactive )
		
		NG\organic_Rtx = ImageWidth ( reduceactive )
		NG\organic_Rty = ImageHeight ( reduceactive )
		
		;RuntimeError NG\organic_Rtx
		
	EndIf
	
End Function
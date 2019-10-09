;------------------------------------------------------------------------
; Modules : Optimizer! (set de fonctions pour optimiser Ngui)
;------------------------------------------------------------------------

; -------------------------------------------------------------------
; Active / D�sactive les d�grad�s
; -------------------------------------------------------------------
Function NG_Optimizer_Gradients ( on=True )
	
	NG_SetAllWindowsGradient ( on )
	
End Function


;--------------------------------------------------------
; Active / D�sactive l'ombrage
;--------------------------------------------------------
Function NG_Optimizer_Shadows ( on=True )

	NG_SetShadow ( on )
	
End Function


;--------------------------------------------------------
; Active / D�sactive le systeme de spheres
;--------------------------------------------------------
Function NG_Optimizer_Spheres ( on=True )

	NG_SphereOn = on
	
End Function


;--------------------------------------------------------
; Active / D�sactive le Cottage
;--------------------------------------------------------
Function NG_Optimizer_Cottage ( on=True )

	NG_CottageLoadOn = on
	
End Function
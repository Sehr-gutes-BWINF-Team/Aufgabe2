Rückgabe
========
Beschreiben Typ und Größe der Rückgabe

`-width` _(Ganzzahl, laufzetbedingt < 1000)_   
`-height` _(Ganzzahl, laufzetbedingt < 1000)_  
`-type` _("GIF", "PNG")_  
`-gifSpeed` _(Delay in MS between GIF Frames)_


Ganzzahlen
=========
Beschreiben allgemeine Konstanten bezüglich des Generationsprozesses (SP = SpawnPixel / Quelle)

`-initialSP` _(Muss kleiner width * height)_  
`-SPperTick` _(SP die pro Tick zufällig innerhalb der "freien" Pixel erschaffen werden)_  

Zeit zwischen neuen "Spawns" von SP  
`-TickDuration` _(Frei wählbar)_


Speed-Werte (0.0 - 1.0)
=======================
Beschreiben die Wahrscheinlichkeit des Wachstums, entlang der Achsen oder in spezifische Richtungen

`-minSpeedNS`
`-maxSpeedNS`

`-minSpeedWE`
`-maxSpeedWE`

`-minSpeedN`
`-maxSpeedN`

`minSpeedS`
`maxSpeedS`

`-minSpeedW`
`-maxSpeedW`

`-minSpeedE`
`-maxSpeedE`

defensa = 7

all: puml svg extendido reducido

svg:
	mkdir -p build/images
	plantuml -tsvg diagramas/diagrama-*.puml -o ../build/images
	
puml:
	cat ../src/game/* | awk -f extdiagram.awk > ./diagramas/diagrama-extendido-game.puml
	cat ../src/colliders/* | awk -f extdiagram.awk > ./diagramas/diagrama-extendido-colliders.puml
	cat ../src/collisions/* | awk -f extdiagram.awk > ./diagramas/diagrama-extendido-collisions.puml
	cat ../src/entities/* | awk -f extdiagram.awk > ./diagramas/diagrama-extendido-entities.puml
	cat ../src/loading/* | awk -f extdiagram.awk > ./diagramas/diagrama-extendido-loading.puml
	cat ../src/graphics/* | awk -f extdiagram.awk > ./diagramas/diagrama-extendido-graphics.puml

extendido:
	pandoc -fmarkdown-implicit_figures -o "Diagrama-extendido-defensa-$(defensa).pdf" build/diagrama-extendido.md

reducido:
	pandoc -fmarkdown-implicit_figures -o "Diagrama-reducido-defensa-$(defensa).pdf" build/diagrama-reducido.md

secuencia:
	pandoc -fmarkdown-implicit_figures -o "diagrama-de-secuencia-1.pdf" build/diagrama-secuencia-1.md
	pandoc -fmarkdown-implicit_figures -o "diagrama-de-secuencia-2.pdf" build/diagrama-secuencia-2.md


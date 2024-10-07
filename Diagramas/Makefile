defensa = 4

all: svg extendido reducido

svg:
	mkdir -p build/images
	plantuml -tsvg diagramas/diagrama-*.puml -o ../build/images
	
extendido:
	pandoc -fmarkdown-implicit_figures -o "Diagrama-extendido-defensa-$(defensa).pdf" build/diagrama-extendido.md

reducido:
	pandoc -fmarkdown-implicit_figures -o "Diagrama-reducido-defensa-$(defensa).pdf" build/diagrama-reducido.md


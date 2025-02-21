defensa = 7

all: puml svg extendido reducido

svg:
	mkdir -p build/images
	plantuml -tsvg $$(find -name *.puml) -o ./build/images

extendido:
	pandoc -fmarkdown-implicit_figures -o "Diagrama-extendido-defensa-$(defensa).pdf" build/diagrama-extendido.md

reducido:
	pandoc -fmarkdown-implicit_figures -o "Diagrama-reducido-defensa-$(defensa).pdf" build/diagrama-reducido.md

secuencia:
	pandoc -fmarkdown-implicit_figures -o "diagrama-de-secuencia-1.pdf" build/diagrama-secuencia-1.md
	pandoc -fmarkdown-implicit_figures -o "diagrama-de-secuencia-2.pdf" build/diagrama-secuencia-2.md


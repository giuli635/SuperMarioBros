$plantuml=""
$defensa = 6
mkdir -Force -p build/images
java -jar $plantuml -tsvg diagramas/diagrama-*.puml -o ../build/images
pandoc -fmarkdown-implicit_figures -o "Diagrama-extendido-defensa-$defensa.pdf" build/diagrama-extendido.md
pandoc -fmarkdown-implicit_figures -o "Diagrama-reducido-defensa-$defensa.pdf" build/diagrama-reducido.md


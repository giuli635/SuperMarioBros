---
top-left: "Comisión 2"
bottom-left: "Tecnologías de programación"
top-right: "UNS"
bottom-right: ""
---

# Formateo del nivel

## Descripción

Los niveles están representados mediante archivos de texto que simulan un arte ASCII del diseño del nivel. Cada fila en el archivo de texto se interpreta como una "columna" o sección (chunk) del nivel en el juego. Para procesar el archivo, se asigna un mapeo que vincula cada símbolo del archivo de texto con una entidad, como se especifica más adelante. A través de un cargador (loader), se crea la entidad correspondiente y se coloca su collider.

El flujo de ejecución del parseo del nivel comienza con el LevelReader, que pasa el control del parseo al loader correspondiente al símbolo leído, a partir de ahí, el control del parseo lo retiene el loader hasta que termine de procesar la información que requiera, luego se lo devuelve al LevelReader, que repite lo anteriormente dicho.

La mayoría de los loaders cargan directamente el collider según la fila y la columna que le pasa el LevelReader, pero hay excepciones, que requieren mayor control por parte del loader, como es el caso de los QuestionBlocks y ConfigurationBlocks, ya que su contenido varía y viene determinado por el símbolo siguiente al propio de dicha entidad. Por lo tanto retienen el control una fila columna más en la lectura del archivo de texto.

Este parseo no contiene validación del espacio ocupado por algunas entidades como es el caso del castillo grande, que ocupa nueve filas, el castillo pequeño, que ocupa cinco filas y las pipes, que ocupan dos. Esta validación es posible de realizar con esta arquitectura, pero el tiempo no fue suficiente para implementarlo, por lo que el diseñador tiene que tener en cuenta estos detalles a la hora de diseñar el nivel.

## Especificación

| Símbolo | Entidad             |
|---------|---------------------|
| M       | Mario               |
| y       | Spiny               |
| k       | KoopaTroopa         |
| g       | Goomba              |
| z       | BuzzyBeetle         |
| s       | SuperMushroom       |
| b       | SolidBrick          |
| p       | Pipe (base)         |
| P       | Pipe (top)          |
| ?       | QuestionBlock       |
| l       | Lakitu              |
| r       | PiranhaPlant        |
| >       | Void                |
| v       | GreenMushroom       |
| C       | BigCastle           |
| c       | SmallCastle         |
| e       | Flag                |
| $       | Coin                |
| f       | FireFlower          |
| *       | ConfigurationBlock  |
| S       | Star                |
| #       | SolidBlock          |

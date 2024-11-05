# Formateo del nivel

## Descripción

Los niveles están representados mediante archivos de texto que simulan un arte ASCII del diseño del nivel. Cada fila en el archivo de texto se interpreta como una "columna" o sección (chunk) del nivel en el juego. Para procesar el archivo, se asigna un mapeo que vincula cada símbolo del archivo de texto con una entidad específica, como se especifica más adelante. A través de un cargador (loader), se crea la entidad correspondiente y se coloca su "collider" en la posición exacta determinada por la fila y columna del símbolo en el archivo.

En los casos particulares de los QuestionBlocks y ConfigurationBlocks, ya que su contenido varía, sus loaders manipulan las columnas y filas del LevelReader para poder manejar su creación en dichos casos especiales.

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

# Comisión 2

## Errores conocidos

- Existen algunos errores en las constantes de los diagramas extendidos debido a errores en el script

- La interacción vertical entre enemigos presenta un comportamiento "no convencional". Debido a limitaciones de tiempo en el desarrollo, se optó por una implementación simplificada, desplazando al enemigo bruscamente hasta encontrar un espacio libre. Aunque los enemigos no las atraviesen, en ocasiones pueden posicionarse encima de plataformas a las que normalmente no deberían acceder.

- Mario puede mostrar movimientos erráticos al interactuar en ciertos ángulos con las plataformas, se trata de un bug meramente visual.

- Los coliders se representan usando la clase Rectangle, y están invertidos en el eje Y respecto a su visualización en pantalla. Este detalle debe tenerse en cuenta al revisar el código, ya que las posiciones visibles en el juego no corresponden directamente a las posiciones reales de los coliders, lo que puede ser una fuente de confusión si no se considera esta inversión.

- No se alcanzó a hacer las breves explicaciones del funcionamiento del sistema

## Mejoras (no son parte de la consigna):

- Pausa: Puede pausarse el juego en cualquier momento pulsando `p` (debido a un bug el presionar `p` también permite abandonar la pantalla de ranking)

- Agacharse: SuperMario y todas sus variantes (FireMario, y sus versiones con Estrella) son capaces de agacharse, la implementación se rige por el comportamiento apreciado en la versión web del juego.

- Invensible: Se implementó el estado de Mario invensible, el cual se activa al perder un PowerUp y le da un breve momento de invulnerabilidad a Mario para evitar GameOvers abruptos

- Compatibilidad de estados: Mario es capaz de recoger PowerUps y ser afectado por los cambios de estados y sus habilidades particulares incluso bajo los efectos del estado Estrella e invensible

- Idiomas: Implementamos el soporte de distintos idiomas en el sistema, actualmente cuenta con inglés, japonés y español. Estos pueden ser intercambiados en el menú

- ConfigurationBlocks: Se implementaron una variante de los QuestionBlocks que llamamos ConfigurationBlocks, estos son usados para el cambio de idioma, modo y desplegar la pantalla con el ranking de manera dinámica en el nivel del menú

- Nivel Menú: En lugar de tener un menú estático con un JPanel y botones para dictaminar el modo (y en nuestro caso el idioma) o mostrar el ranking, decidimos implementar un nivel con bloques especiales (ConfigurationBlocks) que técnicamente permite cambiar estos aspectos en cualquier momento de la ejecución del juego sin afectar el rendimiento o obligar al jugador a cargar desde 0 la experiencia


# SuperMarioBros clone

This is a clone (with loooots of divergence from the original game) of Super Mario Bros. from the NES. Was part of an assignment for the university, for those who want to run it, there's absolutely no warranty of working properly in any computer other than the one from it was created.

The `.jar` is in the `source` folder, run it there and only there, because otherwise it will complain of missing files, we've not solved references to filesystem.

> ***Good luck!!!!***

**Important note for those who dare to read the code:** colliders use the Rectangle class from Java, they **are inverted** with respect to the Y-axis. We did our best to make this the least torturous possible.

## Known errors

- There are some errors in the extended diagrams' constants. Mistakes were made during the automation of this, we're sorry guys.

- Vertical interaction between enemies ended up weird. Due to limitations in development time (oh, yes, that's our excuse), we opted out for a simplified implementation, enemies are teleported out of collision boxes ;). So, if you pack lots of enemies in one place, don't cry if one appears on your side bro.

- Mario can make some really erratic movements while interacting with platforms in some angles.

## Other things to note

- Pause: you can pause the game pressing `p` (activating pause while seeing the ranking makes you leave the ranking, it's a feature not a bug, who knows (?)).

- Once into the game, the menu is a level configured just like the other levels, the difference is that colliding with the `ConfigurationBlocks` let you change some settings or access the game ranking.


# GradleEclipsePlus

The gradle eclipse plus plugin extends the functionality of the standard Eclipse plugin.

## Installation

Add the following entry to your build.gradle:
```
plugins {
    id 'io.github.dwarfley.eclipse-plus' version '0.1.2'
}
```

## Features

#### When the eclipse task is executed:
- Deletes the `bin/` and `.settings/` folders.

#### When the cleanEclipse task is executed:
- Create the `.settings/org.eclipse.core.resources.prefs` file.
- Set the content of the `.prefs` file to make `UTF-8` the explicit encoding set.

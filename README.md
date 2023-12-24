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

- Deletes the `bin/` and `.settings/` folders when executing the cleanEclipse task.
- Creates the `.settings/org.eclipse.core.resources.prefs` file and sets `UTF-8` as the explicit encoding set.

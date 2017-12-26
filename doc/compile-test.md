
Compiling and Testing
=====================

### Compiling EasyForger

Compiling the project is quite simple, just run `./gradlew compileScala` from the command line. This will compile all
three project modules: _root_, _tester_ and _mods_.

You can also compile only the _root_ project by appending a colon to the task name, as below:

```
./gradlew :compileScala
```

You can as well compile only one of the sub-projects by specifying its name, like the following for the _tester_ subproject:

```
./gradlew :tester:compileScala
```

or for the _mods_ subproject:

```
./gradlew :mods:compileScala
```

in both cases, the _root_ project will also be compiled because the subprojects depend on it.

### Testing EasyForger

There are two sets of tests in the project: _automated tests_ and _test mods_. The automated tests are defined in the
_tester_ subproject and are written with [uTest](https://github.com/lihaoyi/utest). They can be run with the following
command:

```
./gradlew :tester:runClient
```

Yes, that is right, to execute the tests we actually have to start the game. This is necessary because some tests
depend on a running Minecraft context. The test results can be seen in the console output.

The test mods are defined in the _mods_ subproject. Similarly to the _automated tests_, you can run them with:

```
./gradlew :mods:runClient
```

This will start a Minecraft instance with all the test mods installed. Here we have to check two things:

* The game should start ok, without errors or warnings caused by the test mods;
* The mods should behave as expected inside the game.

The steps above unfortunately requires manual checking.

EasyForger
==========

Note: this is still under heavy development and everything is subject to change!
Also, feedback is very much appreciated. A good place to send them is our mailing list at https://groups.google.com/group/easyforger

EasyForger is a DSL to help you write MinecraftForge mods for Minecraft (obviously =p)

To run the project, follow these steps:

* clone the repository
* setup your gradle environment with this command: ```./gradlew setupDecompWorkspace```
* you can make sure everything is really working with ```./gradlew runClient``` - this should launch minecraft with your mods
* import the project into your IDE
* for IDEA, after importing, run this command: ```./gradlew genIntellijruns```
* run ```Minecraft Client``` from your IDE menu (which is generated in the command above)

For detailed information have a look at the Minecraft Forge documentation here: http://www.minecraftforge.net/wiki/Installation/Source

Also, please keep in mind that EasyForger uses Scala, so you should have your IDE's Scala plugin installed.


-- 

Running in "semi" offline mode

To run EasyForger (MinecraftForge, really) without internet is not possible, but we can minimize access to the network using a nexus repository.
Start by looking at this tutorial here, to setup nexus: http://www.sonatype.org/nexus/2015/02/17/improved-minecraft-forge-modding-with-nexus/

Then, to run the project, use the ```init.gradle``` file inside the ```nexed``` folder instead of the original one mentioned in the tutorial. I suggest the following:

```./gradlew setupDecompWorkspace -g nexed```

By doing this, you don't need to put ```init.gradle``` in you local ```.gradle```, which keeps your environment clean. After that, everything will be cached in nexus - except the files downloaded from minecraftforge.net, which can't be cached (I don't know why).


--

Publishing tasks:

* publishMavenPublicationToMavenLocal (to work with snapshots, locally)
* bintrayUpload (cut a version and upload it to bintray)

# Apache Ant Build Script ##
Updated: 05/08/2022

# Tutorial
*Assumes Java and Ant having been added to the PC's path and environment variables are created*

- Build a Java project in Eclipse first
- Then add lib folder and build.xml file into the root directory of a said project (mustn't change the code)
- Next, on Eclipse (top bar) go "Window > Show View > Ant" this will bring up the Ant panel, allowing to execute Ant build files from within Eclipse

> ELSE if you would like to run from cmd then it can be done by running `ant` within the same folder the build.xml file is in - the build.xml only has one target (thing that gets executed) that it will default to running so just running `ant` works

## Connection Problems
*If struggling to connect to the robot...*
- Check that you are on the **same network** - try pinging your robot's IP (`ping [ip of robot]`)
  - If the ping fails then double check yours and the robot's network settings...
- Robot's ssh settings must be turned on - the Raspberry Pi model 4's ssh is disabled by default, see [here](https://phoenixnap.com/kb/enable-ssh-raspberry-pi) for details
- Ensure robot details are correct (check ip on the robot via `ifconfig` in a terminal and look at `wlan0`)

## Pathing Problems 
- NoClassDefFound || ClassNotFound
  - These should not occur unless the build.xml has been changed... it relates to class or jar files not being able to be found at runtime
  - If they do occur - look at build.xml file's jar creation section:
    - The class paths should be looked at every time it is loaded into a new workspace... as the referenced libraries will be different and cause the compiler to not run, therefore making the Java Jar almsot empty...
    - Ensure `<fileset>` points to where .class files (should be in .../target/classes are AND where .jar files (should be in /basedir/lib folder) are kept 
    - Also ensure that the jar (or `<zipfileset>`) excludes the correct dependency's manifest (`META-INF/*.SF`) files as this will throw `NoClassDefFound` exception
- "Unable to access jarfile" || jar not found
  - Can simply be down to inputting class/jar name in wrongly... this can also be caused by the build.xml if the `<jar destfile = "name of jar"` is changed
  - Also wise to double check the path to the jar/classes is correct...





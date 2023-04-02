# maventestWO
This project illustrates the use of maven for WebObjects Projects. It is intendet to soften the burden of developers that use WO with ant and are reluctant to move to maven as the build tool.
Many of the information is available in pieces on the web. Therefore I decided to create a working project including all necessary supplemental Information and Tools in one place.
# Java
Make sure to have a version between 1.8 and 11. 
This is my Java Version
`openjdk version "11.0.18" 2023-01-17
OpenJDK Runtime Environment Temurin-11.0.18+10 (build 11.0.18+10)
OpenJDK 64-Bit Server VM Temurin-11.0.18+10 (build 11.0.18+10, mixed mode)`
# Tools
## Maven
The easiest way to do so is via Homebrew https://brew.sh
### Maven setup for WebObjects
There is a nice writeup from Hugi https://gist.github.com/hugithordarson/d2ba6da9e4942f4ece95d7a721159cd1
The necessary .settings.xml is included for illustration in this project.
### Maven Commands that i use
` 393  mvn clean
  394  mvn clean compile -U
  395  mvn -U clean install
  399  mvn dependency:purge-local-repository`
## Git commands that i use
`  388  git commit -a -m "1"
  402  git push
  403  git status`
### Git to start with a new fresh project
1. git init
2. git add *
3. touch .gitignore
4. git add .gitignore
5. git commit -m " your message here"
6. git remote add origin https://github.com/profjens/maventestframeappWO
At this time there are still files which have not been added. All of them start with <.*>
7. git add .project
8. git add .classpath
9. git add .settings/
10. 
## Postgres
Install via Homebrew
### Postgress Tools
I use DBngin for starting stopping Postgres. If gives you also a nice overview if postgres is running. https://dbngin.com
For Debugging I use pgAdmin 4 https://www.pgadmin.org/download/
## Eclipse
At the time of this writing (March 23) I use Eclipse 2022-06 (4.24.0). It works on Apple Silicon AND Intel
### WOLIPS 
Install as "new software" in Eclipse. https://jenkins.wocommunity.org/job/WOLips_master/lastSuccessfulBuild/artifact/temp/dist/
### Maven 
As described in the description of Hugi Eclipse needs to know where the plugin is.
<img width="801" alt="Bildschirm­foto 2023-03-05 um 16 22 57" src="https://user-images.githubusercontent.com/1333381/222969637-469fd729-6c3e-4c48-85d5-2f7366ef3ca0.png">
# Sample Project -1- 
This WebObjects project has three Components (Pages). Also included is the EOModeller File for the creation and maintaining of the database. 
When the project is included in eclipse double click 
![eclipse-workspace_-_testmaven_src_main_resources_MyEOModel_eomodeld_index_eomodeld_-_Eclipse_IDE](https://user-images.githubusercontent.com/1333381/222967771-853b22c1-6761-40bc-b710-d21c62300c3d.jpg)
# Problems
## 'list' must not be a constant	Main.html
Template validation in WOLips is flakey, with or without Maven. Just delete this warning from the "Problems" tab in Eclipse. Done.
I disabled "validate on build"... 
## "unresolvable build extension..." 
_Not sure what causes this but the only way to remove the error was a hint from Hugi_
`mvn dependency:purge-local-repository`
It will reload everything and the error goes away. Note! You need to restart Eclipse for this after applying the command.
![image](https://user-images.githubusercontent.com/1333381/223358556-ba3b160d-f80c-4835-905f-0f394fc25cc4.png)
## build.plugins.plugin.version' for org.apache.maven.plugins:maven-compiler-plugin is missing
This can be solved by adding a Add a <version> element after the <plugin> <artifactId> in your pom.xml file. 
## There is no model named 'MyEOModel' in this model group
`Mär 07 08:48:01 testmaven[56417] DEBUG NSLog  - Using JDBCPlugIn 'com.webobjects.jdbcadaptor.JDBCPlugIn' for ERXJDBCAdaptor@20224131
Mär 07 08:48:01 testmaven[56417] ERROR er.extensions.appserver.ERXApplication  - testmaven failed to start.
IllegalArgumentException: There is no model named 'MyEOModel' in this model group.
  at er.extensions.migration.ERXMigrator._buildDependenciesForModelsNamed(ERXMigrator.java:274)
  at er.extensions.migration.ERXMigrator.migrateToLatest(ERXMigrator.java:185)
  at er.extensions.appserver.ERXApplication.finishInitialization(ERXApplication.java:1313)
  ... skipped 13 stack elements`
 ### The Problem is in the .project File
 This has to be included Quote from Hugi:
`Now, as for allowing the application to locate your model (and other WO resources) when launching from Eclipse your project file is missing a <nature>  required by WO/ERFoundation to recognize that it's a maven project (edited) `
Add the following inside the <natures> tag in your .project
<nature>org.maven.ide.eclipse.maven2Nature</nature>
And have a look in the provided ".project" File.
## The generated EOModeller Files miss important information
Once I did setup a new Machine I did forget to set the preferences in WOLips to point to the WonderEntity instead of the standard Entity.java. This is important because if you make intensive use of the wonder framework this is essential!
![Bildschirm­foto 2023-03-26 um 11 40 43](https://user-images.githubusercontent.com/1333381/227767695-4b13985a-aa3c-4602-920d-2f3f2d520101.png)
## I have an old (ant) WOProject and want to convert it to maven.
Well there is a nice writeup from Hugi describing the process here https://gist.github.com/hugithordarson/3c269a3196d0c4f2da486f1109c16d42


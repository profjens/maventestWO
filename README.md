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
(https://www.eclipse.org/downloads/packages/release/2022-06/r)
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
## you get the Error: "[WARNING] bootstrap class path not set in conjunction with -source 8"
Problem is in the POM - File! Change to the following as of April 23!
			`<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>2.3.2</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>`

## this is a tricky one. Error: `SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
WILL ADD SHUTDOWNHOOK
NSProperties.NestedProperties.load(): /Users/jens/WebObjects.properties
[2023-4-8 7:43:9 CEST] <main> A fatal exception occurred: null
[2023-4-8 7:43:9 CEST] <main> java.lang.ExceptionInInitializerError
	`
	Solution: edit build.properties and make sure that "classes.dir" point to "target/classes" like `classes.dir = target/classes`
## Error `IllegalStateException: adaptorValueType: unable to load class named 'LocalDate' for attribute myTimeInJava on entity MeinUser`
The POM -File is missing the following segment. If you are on ANT it is included (default!). In Maven NOT. So:
<dependency>
  <groupId>wonder.eof</groupId>
  <artifactId>ERAttributeExtension</artifactId>
  <version>${wonder.version}</version>
</dependency>
## After everything is tested you want to deploy and receive a "zip" Error:
`Exception in thread "main" com.webobjects.foundation.NSForwardException [java.util.zip.ZipException] zip END header not found:java.util.zip.ZipException: zip END header not found
	at com.webobjects.foundation.NSForwardException._runtimeExceptionForThrowable(NSForwardException.java:45)
	at er.extensions.appserver.ERXApplication$Loader.stringFromJar(ERXApplication.java:857)
	at er.extensions.appserver.ERXApplication$Loader.<init>(ERXApplication.java:517)
	at er.extensions.appserver.ERXApplication.setup(ERXApplication.java:1071)
	at er.extensions.appserver.ERXApplication.main(ERXApplication.java:884)
	at js_fb5.w_hs.de.hausadmin.app.Application.main(Application.java:28)
Caused by: java.util.zip.ZipException: zip END header not found`
I my case that happened because I created a new project based on the TEMPLATE. There the version is missing (April 23)! 
Solution: Most likely the <version> is missing in your POM for maven-compiler-plugin. So it should look like this:
			`<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>2.3.2</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>`
This did NOT solve the problem! One or more of the jars was corrupt. Tracking that down proved to be very difficult. I ended up installing "brew install JD" and after install starting it from the commandline. "java -jar /Users/jens/Downloads/jd-gui-osx-1.6.6/JD-GUI.app/Contents/Resources/Java/jd-gui-1.6.6-min.jar"
I identified one jar "apache-ldap-api-2.1.2.jar" which could not be opened.
Error message 
	`java.util.zip.ZipException: zip END header not found
	at java.base/java.util.zip.ZipFile$Source.zerror(ZipFile.java:1607)
	at java.base/java.util.zip.ZipFile$Source.findEND(ZipFile.java:1497)
	at java.base/java.util.zip.ZipFile$Source.initCEN(ZipFile.java:1504)
	at java.base/java.util.zip.ZipFile$Source.<init>(ZipFile.java:1308)
	at java.base/java.util.zip.ZipFile$Source.get(ZipFile.java:1271)
	at java.base/java.util.zip.ZipFile$CleanableResource.<init>(ZipFile.java:733)
	at java.base/java.util.zip.ZipFile$CleanableResource.get(ZipFile.java:850)
	at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:248)
	at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:177)
	at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:148)
	at jdk.jartool/sun.tools.jar.Main.extract(Main.java:1388)
	at jdk.jartool/sun.tools.jar.Main.run(Main.java:410)
	at jdk.jartool/sun.tools.jar.Main.main(Main.java:1680)`
and it is located in "org/apache/directory/api/apache-ldap-api/2.1.3/
### Well I declare defeat. The example here are working also in Deployment but I could not figure out what is wrong with this "Zip" Error. Thanks anyway to everybody that helped!

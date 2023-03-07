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
"unresolvable build extension..." Not sure what causes this but the only way to remove the error was a hint from Hugi
`mvn dependency:purge-local-repository`
It will reload everything and the error goes away.
![image](https://user-images.githubusercontent.com/1333381/223358556-ba3b160d-f80c-4835-905f-0f394fc25cc4.png)


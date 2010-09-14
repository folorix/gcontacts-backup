@echo off
java -jar ${project.build.finalName}.jar -classpath=lib/* %*

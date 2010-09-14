#!/bin/sh
export CLASSPATH=lib/*
java -jar ${project.build.finalName}.jar java info.touret.gcontacts.App $@
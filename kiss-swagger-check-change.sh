#!/bin/sh
java -cp kissSwaggerDiff-0.0.1-SNAPSHOT.jar;/d/applis/MAVEN/m2repos/org/json/json/20140107/json-20140107.jar;/d/applis/GIT/kissSwaggerDiff/target/classes fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissSwaggerMainHandler
cd /d/applis/GIT/kissSwaggerDiff
git commit -am "commit auto des fichiers json modifiés bis"
git push

#!/bin/sh
# java -cp kissSwaggerDiff-0.0.1-SNAPSHOT.jar;/d/applis/MAVEN/m2repos/org/json/json/20140107/json-20140107.jar;/d/applis/GIT/kissSwaggerDiff/target/classes fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissSwaggerMainHandler
#cd /d/applis/GIT/kissSwaggerDiff
#git commit -am "commit auto des fichiers json modifiés 4 déc bis"
#git push
#sudo chmod 777 -R src/main/resources/
cd /var/lib/jenkins/jobs/ico-dev-build/workspace && java -cp target/kiss-swagger-monitoring-dep.jar fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissWSApiDocsMonitoring
#on ajoute tous les nouveaux fichiers puis commiter et pusher sur la repos github 
#git pull origin master
git branch --set-upstream-to=origin/master master
git add *
git commit -am "commit auto des modifications des webservices KISS avec Swagger 1"
git push --repo https://ibrahimacoundoul:testtest1@github.com/ibrahimacoundoul/cgaweb-ico-dev
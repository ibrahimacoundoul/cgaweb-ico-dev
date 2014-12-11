#!/bin/sh

#sudo chmod 777 -R src/main/resources/*.json

cd /var/lib/jenkins/jobs/ico-dev-build/workspace

sudo git reset --hard HEADER # supprime tout (fichier non commit, etc)
git checkout -f master # se positionne sur la branche et revert les fichiers, au cas oÃ¹ pull origin master # met Ã our le repo
java -cp target/kiss-swagger-monitoring-dep.jar fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissWSApiDocsMonitoring # generation du json

sudo git add *
sudo git commit -am "commit auto des modifications des webservices KISS avec Swagger 1"

sudo git push --repo https://ibrahimacoundoul:testtest1@github.com/ibrahimacoundoul/cgaweb-ico-dev master:master

#!/bin/sh

#sudo chmod 777 -R src/main/resources/*.json


# On va dans le repertoire oû il ya le jar de la classe et l'excéuter pour générer ou modifier les fichiers .json
#cd /var/lib/jenkins/jobs/ico-dev-build/workspace && java -cp target/kiss-swagger-monitoring-dep.jar fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissWSApiDocsMonitoring
#on ajoute tous les nouveaux fichiers puis commiter et pusher sur la repos github 
#git pull origin master
#git branch --set-upstream-to=origin/master master
#git add *
#git -am "commit auto des modifications des webservices KISS avec Swagger 1"
#git push --repo https://ibrahimacoundoul:testtest1@github.com/ibrahimacoundoul/cgaweb-ico-dev

cd /var/lib/jenkins/jobs/ico-dev-build/workspace

sudo git reset --hard HEADER # supprime tout (fichier non commit, etc)
git checkout -f master # se positionne sur la branche et revert les fichiers, au cas où pull origin master # met àour le repo
java -cp target/kiss-swagger-monitoring-dep.jar fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissWSApiDocsMonitoring # generation du json

sudo git add *
sudo git commit -am "commit auto des modifications des webservices KISS avec Swagger 1"

sudo git push --repo https://ibrahimacoundoul:testtest1@github.com/ibrahimacoundoul/cgaweb-ico-dev master

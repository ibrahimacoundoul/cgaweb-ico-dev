java -cp kissSwaggerDiff-0.0.1-SNAPSHOT.jar;D:\applis\MAVEN\m2repos\org\json\json\20140107\json-20140107.jar;D:\applis\GIT\kissSwaggerDiff\target\classes fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissSwaggerMainHandler
REM on ajoute tous les nouveaux fichiers puis commiter et pusher sur la repos github 
git pull
git add *
git commit -am "commit auto des modifications des webservices KISS avec Swagger"
git push --repo https://ibrahimacoundoul:testtest1@github.com/ibrahimacoundoul/cgaweb-ico-dev

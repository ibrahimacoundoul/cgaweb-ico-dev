REM java -cp kissSwaggerDiff-0.0.1-SNAPSHOT.jar;D:\applis\MAVEN\m2repos\org\json\json\20140107\json-20140107.jar;D:\applis\GIT\kissSwaggerDiff\target\classes;D:\applis\MAVEN\m2repos\org\codehaus\jackson\jackson-mapper-asl\1.9.13\jackson-mapper-asl-1.9.13.jar;D:\applis\MAVEN\m2repos\org\codehaus\jackson\jackson-core-asl\1.9.13\jackson-core-asl-1.9.13.jar   fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissWSApiDocsMonitoring
java -cp kiss-swagger-monitoring-dep.jar fr.canalplus.cgaweb.swagger.kissSwaggerDiff.KissWSApiDocsMonitoring
REM on ajoute tous les nouveaux fichiers puis commiter et pusher sur la repos github 
git pull
git add *
git commit -am "commit auto des modifications des webservices KISS avec Swagger 1"
git push --repo https://ibrahimacoundoul:testtest1@github.com/ibrahimacoundoul/cgaweb-ico-dev

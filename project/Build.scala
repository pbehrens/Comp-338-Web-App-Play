import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "Comp-338-Web-App-Play"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies).settings(defaultScalaSettings:_*).settings(
//    templatesImport += "models.objects.Reservation" 
//    templatesImport += "models.objects.Resource"
//    templatesImport += "models.objects.User"
//    templatesImport += "models.objects.Role"
      templatesImport += "models.objects._"
    
    
    
    
    )

    


}

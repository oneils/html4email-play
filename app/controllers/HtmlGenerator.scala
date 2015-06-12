package controllers

import java.nio.charset.StandardCharsets
import java.nio.file.{Paths, Files}

import play.api.mvc.{Action, Controller}

import scala.io.Source

/**
 * @author Aliaksei Bahdanau.
 */
class HtmlGenerator extends Controller{

  def extract = Action {

    val html: String = Source.fromURL("http://localhost:9000/preview").mkString
    val fileName = "digest.html"


    Files.write(Paths.get(userHomeDirectory + "/" + fileName), html.getBytes(StandardCharsets.UTF_8))

    val message = "File '" + fileName + "' is extracted to folder: " + userHomeDirectory
    val savedFilePath = userHomeDirectory + "/" + fileName
    Ok(views.html.extract(fileName, savedFilePath))
  }

  def userHomeDirectory = System.getProperty("user.home")

}

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
    val defaultFileName = "digest.html"


    Files.write(Paths.get(defaultExtractDirectory + "/" + defaultFileName), html.getBytes(StandardCharsets.UTF_8))

    val message = "File '" + defaultFileName + "' is extracted to folder: " + defaultExtractDirectory
    val savedFilePath = defaultExtractDirectory + "/" + defaultFileName
    Ok(views.html.extract(defaultFileName, savedFilePath))
  }

  def defaultExtractDirectory = System.getProperty("user.home")

}

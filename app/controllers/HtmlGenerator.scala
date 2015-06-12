package controllers

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.{Paths, Files}

import play.api.mvc.{Action, Controller}
import settings.DefaultSettings

import scala.io.Source

/**
 * @author Aliaksei Bahdanau.
 */
class HtmlGenerator extends Controller{

  def extract = Action {

    val host = DefaultSettings.host
    val port = DefaultSettings.port
    val extractUrl = DefaultSettings.extractingUrl

    val html: String = Source.fromURL(host + ":" + port + "/" + extractUrl).mkString

    val defaultFileName = DefaultSettings.defaultFileName
    val defaultExtractDirectory = DefaultSettings.defaultExtractDirectory

    val savedFilePath = defaultExtractDirectory + File.separator + defaultFileName

    Files.write(Paths.get(savedFilePath), html.getBytes(StandardCharsets.UTF_8))

    Ok(views.html.extract(defaultFileName, savedFilePath))
  }
}

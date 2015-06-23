package controllers

import java.io.File
import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.{Paths, Files}

import api.{Digest, DigestPath}
import parser.JSonParser
import play.api.Play
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.mvc.{Action, Controller}
import settings.DefaultSettings
import play.api.Play.current
import scala.io.{BufferedSource, Source}

/**
 * @author Aliaksei Bahdanau.
 */
class HtmlGenerator extends Controller {

  def extract = Action {

    implicit request =>
      val digestPath = extractPathForm.bindFromRequest.get

      val jsonParser = new JSonParser

      val jsonString = getJsonAsString(digestPath.jsonPath)

      val digest: Digest = jsonParser.parse(jsonString)
      val logoFullPath = Play.classloader.getResource("public/images/logo.png").getPath

      val content = views.html.preview.render(digest, logoFullPath)

      val html: String = content.toString
      val defaultFileName = DefaultSettings.defaultFileName

      val pathForExtracting = buildExportPath(digestPath.htmlPath)
      val fullPathForExtracting: String = pathForExtracting + File.separator + defaultFileName

      saveHtmlToFile(fullPathForExtracting, html)

      Ok(views.html.extract(defaultFileName, digestPath))
  }

  def getJsonAsString(jsonPath: String): String = {
    val jsonSource: BufferedSource = Source.fromFile(jsonPath)
    try jsonSource.getLines() mkString finally jsonSource.close()
  }

  def buildExportPath(path: Any): String = path match {
    case originalPath: String => path.toString
    case _ => DefaultSettings.defaultExtractDirectory
  }

  def saveHtmlToFile(pathForSaving: String, html: String): Unit = {
    Files.write(Paths.get(pathForSaving), html.getBytes(StandardCharsets.UTF_8))
  }

  val extractPathForm = Form(
    mapping(
      "jsonPath" -> text,
      "htmlPath" -> text
    )(DigestPath.apply)(DigestPath.unapply)
  )

  def saveHtmlFile = Action {
    implicit request =>
      val digestPath = extractPathForm.bindFromRequest.get

      Ok(views.html.extract("fileName", digestPath))
  }
}

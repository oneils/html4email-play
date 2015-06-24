package controllers

import java.io._
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import api.{Digest, DigestPath}
import parser.JSonParser
import play.api.Play
import play.api.Play.current
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.mvc.{Action, Controller}
import settings.DefaultSettings

import scala.io.{BufferedSource, Source}

/**
 * @author Aliaksei Bahdanau.
 */
class HtmlGenerator extends Controller {
  val jsonParser = new JSonParser
  val defaultOutputFileName = DefaultSettings.defaultFileName

  def extract = Action {

    implicit request =>
      val digestPath = extractPathForm.bindFromRequest.get

      val digest: Digest = getDigest(digestPath)
      val htmlContent: AnyRef = getHtmlContent(digest)

      saveHtmlToFile(digestPath, htmlContent)

      Ok(views.html.extract(defaultOutputFileName, digestPath))
  }

  private def getHtmlContent(digest: Digest) = {
    val logoFullPath = Play.classloader.getResource("public/images/logo.png").getPath
    views.html.preview.render(digest, logoFullPath)
  }

  private def getDigest(digestPath: DigestPath): Digest = {
    val jsonString = getJsonAsString(digestPath.jsonPath)
    jsonParser.parse(jsonString)
  }

  private def getJsonAsString(jsonPath: String): String = {
    val jsonSource: BufferedSource = Source.fromFile(jsonPath)
    try jsonSource.getLines() mkString finally jsonSource.close()
  }

  private def buildExportPath(path: Any): String = path match {
    case originalPath: String => path.toString
    case _ => DefaultSettings.defaultExtractDirectory
  }

  def saveHtmlToFile(digestPath: DigestPath, html: AnyRef): Unit = {
    val pathForSavingHtml = buildExportPath(digestPath.htmlPath)
    val pathForSaving: String = pathForSavingHtml + File.separator + defaultOutputFileName
    Files.write(Paths.get(pathForSaving), html.toString.getBytes(StandardCharsets.UTF_8))
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

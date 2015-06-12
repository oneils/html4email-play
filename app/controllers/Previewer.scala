package controllers

import api.Digest
import parser.JSonParser
import play.api._
import play.api.mvc._
import play.api.Play.current
import scala.io.Source

/**
 * @author Aliaksei Bahdanau.
 */
class Previewer extends Controller{

  def preview = Action {

    val digestJsonUrl = Play.classloader.getResource("digest.json")
    val jsonString = Source.fromURL(digestJsonUrl).getLines().mkString

    val jsonParser = new JSonParser
    val digest: Digest = jsonParser.parse(jsonString)

    val logoFullPath = Play.classloader.getResource("public/images/logo.png").getPath

    Ok(views.html.preview(digest, logoFullPath))
  }
}

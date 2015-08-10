package controllers

import play.api.Play
import play.api.mvc._
import settings.DefaultSettings
import play.api.Play.current

import scala.io.Source


class Application extends Controller {

  def index = Action {
    Ok(views.html.index(DefaultSettings.defaultExtractDirectory, getDigestFormat))
  }

  private def getDigestFormat: String = {
    val digestJsonUrl = Play.classloader.getResource("digest.json")
    Source.fromURL(digestJsonUrl).getLines().mkString
  }
}

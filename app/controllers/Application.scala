package controllers

import play.api.mvc._
import settings.DefaultSettings

class Application extends Controller {

  def index = Action {

    Ok(views.html.index(DefaultSettings.defaultExtractDirectory))
  }

}

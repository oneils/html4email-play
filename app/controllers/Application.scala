package controllers

import api.DigestPath
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import settings.DefaultSettings


class Application extends Controller {

  def index = Action {

    Ok(views.html.index(DefaultSettings.defaultExtractDirectory, extractPathForm))
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

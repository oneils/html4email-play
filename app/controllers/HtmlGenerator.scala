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

    val html: String = Source.fromURL("http://localhost:9000").mkString

    Files.write(Paths.get("/home/abahdanau/tmp/out.html"), html.getBytes(StandardCharsets.UTF_8))

    Ok(views.html.extract("Extracted"))
  }

}

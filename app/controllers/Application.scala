package controllers

import api.Digest
import parser.JSonParser
import play.api._
import play.api.mvc._
import play.api.Play.current
import scala.io.Source

class Application extends Controller {

  def index = Action {
//    val digestJsonUrl = Play.classloader.getResource("digest.json")
//    val jsonString = Source.fromURL(digestJsonUrl).getLines().mkString
//    //  val jsonString = Source.fromURL(getClass.getResource("/digest.json")).getLines().mkString
//
//    val jsonParser = new JSonParser
//    val digest: Digest = jsonParser.parse(jsonString)
    Ok(views.html.index("index"))
  }

}

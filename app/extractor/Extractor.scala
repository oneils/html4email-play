package extractor

import java.nio.charset.StandardCharsets
import java.nio.file.{Paths, Files}

import api.Digest
import parser.JSonParser
import play.api.Play
import play.api.Play.current
import scala.io.Source

/**
 * @author Aliaksei Bahdanau.
 */
class Extractor {
  val digestJsonUrl = Play.classloader.getResource("digest.json")
  val jsonString = Source.fromURL(digestJsonUrl).getLines().mkString
//  val jsonString = Source.fromURL(getClass.getResource("/digest.json")).getLines().mkString

  val jsonParser = new JSonParser
  val digest: Digest = jsonParser.parse(jsonString)

  val html: String = Source.fromURL("http://localhost:9000").mkString

  Files.write(Paths.get("/home/abahdanau/tmp/out.html"), html.getBytes(StandardCharsets.UTF_8))
}

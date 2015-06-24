package api

import spray.json.DefaultJsonProtocol

/**
 * @author Aliaksei Bahdanau.
 */
object DigestProtocol extends DefaultJsonProtocol {
  implicit val article = jsonFormat3(Article)
  implicit val categoryFormat = jsonFormat2(Topic)
  implicit val digestFormat = jsonFormat2(Digest)
}

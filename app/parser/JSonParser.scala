package parser

import api.Digest
import api.DigestProtocol._
import spray.json._

/**
 * @author Aliaksei Bahdanau
 */
class JSonParser {

  /**
   * Parses JSON String representation and returns a Digest object.
   * @param input JSON as String
   * @return List of [[api.Topic]]
   */
  def parse(input: String): Digest = {
    input.parseJson.convertTo[Digest]
  }
}

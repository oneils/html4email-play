package settings

/**
 * @author Aliaksei Bahdanau.
 */
object DefaultSettings {

  def defaultExtractDirectory = System.getProperty("user.home")

  def defaultFileName = "digest.html"

  def host = "http://localhost"

  def port = "9000"

  def extractingUrl = "preview"
}

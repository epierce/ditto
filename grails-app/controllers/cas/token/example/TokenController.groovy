package cas.token.example

import edu.usf.cims.Security
import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 * TokenController
 * Accepts the parameters from a form and generates an authentication token to login to CAS
 *
 */
class TokenController {
  def grailsApplication

  def generateToken() { 

    def casURL = params["serverURL"]
    def username = params["username"]
    def firstName = params["firstName"]
    def lastName = params["lastName"]
    def email = params["email"]

    // Get key data from Config.grovy
    def key = grailsApplication.config.cas.token.key

    def tokenData = [ 
          generated : new Date().time,
          credentials : [ username: username,
                          firstname: firstName,
                          lastname: lastName,
                          email: email
                        ]
                    ]

    def jsonData = tokenData.encodeAsJSON()

    def encrytedToken = Security.AESencrypt(jsonData, key.data)

    def finalURL = "${casURL}/login?username=${username}&token_service=${key.name}&auth_token=${encrytedToken.encodeAsURL()}"

    // Send the data to the generateToken view
    [jsonData: jsonData, encrytedToken: encrytedToken, finalURL: finalURL]
  }
}

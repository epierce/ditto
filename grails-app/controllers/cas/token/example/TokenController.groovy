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

    def casURL = 'https://watson.it.usf.edu:8443'
    def credentials = [:]

    params.remove('controller')
    params.remove('action')

    params.each() { attribute, value ->
      if(value){
        credentials.put(attribute,value)
      }
    }

    credentials.username = params.uid[0]

    // Get key data from Config.grovy
    def key = grailsApplication.config.cas.token.key

    def tokenData = [ 
          generated : new Date().time,
          credentials : credentials ]

    def jsonData = tokenData.encodeAsJSON()

    def encrytedToken = Security.AESencrypt(jsonData, key.data)

    def finalURL = "${casURL}/login?username=${params.uid[0]}&token_service=${key.name}&auth_token=${encrytedToken.encodeAsURL()}&service=https://dev.it.usf.edu/sync_test.php"

    // Send the data to the generateToken view
    [jsonData: jsonData, encrytedToken: encrytedToken, finalURL: finalURL]

  }
}

package edu.usf.cims.ditto

import edu.usf.cims.Security
import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 * TokenController
 *
 */
class TokenController {
  def grailsApplication

  def generateToken() { 

    def casURL = 'https://watson.it.usf.edu:8443'
  
    //We,don't need the controller/action paramers
    params.remove('controller')
    params.remove('action')

    //Loop through the other parameters and copy the ones with values to a new HashMap
    def credentials = [:]
    params.each() { attribute, value ->
      if(value){
        credentials.put(attribute,value)
      }
    }

    // Get key data from Config.grovy
    def key = grailsApplication.config.cas.token.key

    def tokenData = [ 
          generated : new Date().time,
          credentials : credentials ]

    def jsonData = tokenData.encodeAsJSON()

    def encrytedToken = Security.AESencrypt(jsonData, key.data)

    def finalURL = "${casURL}/login?username=${credentials.username}&token_service=${key.name}&auth_token=${encrytedToken.encodeAsURL()}&service=https://dev.it.usf.edu/sync_test.php"

    // Send the data to the generateToken view
    [jsonData: jsonData, encrytedToken: encrytedToken, finalURL: finalURL]

  }
}

package edu.usf.cims.ditto


import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 * TokenController
 *
 */
class TokenController {
  def grailsApplication
  def tokenService

  def generateTokenDev() { 

    def debug = false
    if(params.debug == 'TRUE') debug = true
    
    def casURL = grailsApplication.config.ditto.cas.loginUrls.dev
    def key = grailsApplication.config.ditto.cas.token.keys.dev

    def tokenData = tokenService.generateToken(params,key.value)

    def finalURL = "" //"${casURL}/login?username=${credentials.username}&token_service=${key.name}&auth_token=${encrytedToken.encodeAsURL()}&service=https://dev.it.usf.edu/sync_test.php"

    // Send the data to the generateToken view
    if(debug){
      return [jsonData: tokenData.json, encrytedToken: tokenData.final, finalURL: finalURL]
    } else {
      redirect(url: finalURL)
    }
  }
}

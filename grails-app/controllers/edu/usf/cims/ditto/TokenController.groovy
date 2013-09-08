package edu.usf.cims.ditto

import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 * TokenController
 *
 */
class TokenController {
  def usfCasService
  def grailsApplication
  def tokenService

  def generateToken() { 

    def debug = false
    if(params.debug == 'TRUE') debug = true
    
    def instance = params.id
    def casURL = grailsApplication.config.ditto.cas.loginUrls["$instance"]
    def casService = grailsApplication.config.ditto.cas.serviceUrls["$instance"]
    def key = grailsApplication.config.ditto.cas.token.keys["$instance"]

    def tokenData = tokenService.generateToken(params,key.value)

    log.info "Auth Token created in CAS instance [${instance}] for [${params.username}] by [${usfCasService.username}]"
    log.debug "JSON data created for user [${usfCasService.username}]: ${tokenData.json}"

    def model = [ jsonData: tokenData.json, 
                  authToken: tokenData.final, 
                  casURL: casURL,
                  username: params.username,
                  tokenService: key.name,
                  service: casService ]

    // Send the data to the generateToken view
    if(debug){
      log.debug "Displaying token data for [${params.username}]"
      render(view: "viewToken", model: model)
    } else {
      log.debug "Sending token data for [${params.username}] to CAS Instance [${instance}]"
      render(view: "sendToken", model: model)
    }
  }
}
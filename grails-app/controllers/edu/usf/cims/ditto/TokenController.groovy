package edu.usf.cims.ditto

import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 * TokenController
 *
 */
class TokenController {
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

    def model = [ jsonData: tokenData.json, 
                  authToken: tokenData.final, 
                  casURL: casURL,
                  username: params.username,
                  tokenService: key.name,
                  service: casService ]

    // Send the data to the generateToken view
    if(debug){
      render(view: "viewToken", model: model)
    } else {
      render(view: "sendToken", model: model)
    }
  }
}
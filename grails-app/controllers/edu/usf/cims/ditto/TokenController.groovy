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

    if(! params.username){
      redirect(action:'error', params:[reason: "Username is required"])
      return
    }
    
    def instance = params.id
    def casURL = grailsApplication.config.ditto.cas.loginUrls["$instance"]
    def casService = grailsApplication.config.ditto.cas.serviceUrls["$instance"]
    def key = grailsApplication.config.ditto.cas.token.keys["$instance"]

    def tokenData = tokenService.generateToken(params,key.value)

    log.info "Authentication Token created in CAS environment [${instance}] for [${params.username}] by [${usfCasService.username}] from [${request.getRemoteAddr()}]"
    log.debug "JSON data created for user [${usfCasService.username}]: ${tokenData.json}"

    def model = [ jsonData: tokenData.json, 
                  authToken: tokenData.final, 
                  casURL: casURL,
                  username: params.username,
                  tokenService: key.name,
                  service: casService ]

    if (grailsApplication.config.ditto.notification.lists["$instance"]) {
        sendMail {
            to grailsApplication.config.ditto.notification.lists["$instance"]
            subject "[Ditto Alert] Authentication token created for ${instance} CAS environment"
            text (
"""Ditto audit record
=============================================================
ACTOR: ${usfCasService.username}
ACTION: Authentication token created
TARGET: ${params.username}
ENVIRONMENT: ${instance} 
WHEN: ${new Date().format("E, dd MMM yyyy HH:mm:ss Z")}
CLIENT IP ADDRESS: ${request.getRemoteAddr()}
SERVER IP ADDRESS: ${request.getLocalAddr()}
=============================================================
""")
      }
    }

    // Send the data to the generateToken view
    if(debug){
      log.debug "Displaying token data for [${params.username}]"
      render(view: "viewToken", model: model)
    } else {
      log.debug "Sending token data for [${params.username}] to CAS Instance [${instance}]"
      render(view: "sendToken", model: model)
    }
  }

  def error(){
    [reason: params.reason]
  }
}
package edu.usf.cims.ditto

import edu.usf.cims.Security

/**
 * TokenService
 */
class TokenService {
    def grailsApplication

    static transactional = false

    def generateToken(params, key){
      //We don't need all the values in the parameter map
      params.remove('debug')
      params.remove('controller')
      params.remove('action')
      params.remove('id')
      params.remove('org.codehaus.groovy.grails.SYNCHRONIZER_TOKEN')
      params.remove('org')
      params.remove('org.codehaus.groovy.grails.SYNCHRONIZER_URI')

      //Loop through the other parameters and copy the ones with values to a new HashMap
      def credentials = [:]
      params.each() { attribute, value ->
        if(value){
          credentials.put(attribute,value)
        }
      } 

      def tokenData = [generated : new Date().time, credentials : mapAttributes(credentials)]
      def jsonData = tokenData.encodeAsJSON()      
      def encryptedToken = Security.AESencrypt(jsonData, key)

      return [json: jsonData, final: encryptedToken]
    }

    private def mapAttributes(inputAttributes){
      //if the attribute mapping doesn't exist, just return the List
      if(! grailsApplication.config.ditto.user.attributeMapping ) return inputAttributes

      def outputAttributes = [:]
      inputAttributes.each() { attribute, value ->
        if(grailsApplication.config.ditto.user.attributeMapping[attribute]){
          outputAttributes.put(grailsApplication.config.ditto.user.attributeMapping[attribute], value)
        } else {
          outputAttributes.put(attribute, value)
        }
      }

      return outputAttributes
    }
}
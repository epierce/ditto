package edu.usf.cims.ditto

import groovy.json.JsonSlurper
import grails.converters.JSON

/**
 * PersonSearchService
 */
class PersonSearchService {
    def grailsApplication

    java.util.HashMap findPersonByIdentifer(String identifier, String searchTerm) {

      //Create an empty result set
      def dirEntry = [result: 'success', uid: '', attributes: [:], details: '' ]
      grailsApplication.config.ditto.user.attributes.each { attribute ->
        dirEntry.uid = ''
        dirEntry.attributes["${attribute}"] = ''
        dirEntry.attributes.remove('uid')
      }

      /* If no identifer/search term was passed, return the blank entry */
      if (identifier == '' && searchTerm == '') {
        return dirEntry
      } else {
        /* Use the RESTful CAS client plugin to call a webservice and get the user's info */
        try {
          def result = withCasRest(
              grailsApplication.config.directory.nams.search.url,
              "GET",
              grailsApplication.config.casRestClient.cas.username,
              grailsApplication.config.casRestClient.cas.password,
              [:],
              [ submit_type:identifier, return_type:"dir_entry", return:"json", value:searchTerm]
          )

          def jsonObj = new JsonSlurper().parseText(result)
          
          /*
          * If there was a problem with the data (no user found, etc)
          * the webservice returns an error response and 'reason' field
          */
          if(jsonObj.response == 'error'){
            dirEntry.result = 'error'
            dirEntry.details = jsonObj.reason
            return dirEntry
          }

          /* The webservice was successful, set the username and other attributes */
          dirEntry.result = 'success'
          dirEntry.uid = jsonObj.dir_entry["${grailsApplication.config.ditto.user.usernameAttribute}"][0]
          grailsApplication.config.ditto.user.attributes.each { attribute ->
            dirEntry.attributes["${attribute}"] = jsonObj.dir_entry["${attribute}"]
          }

          return dirEntry
        
        } catch(Exception e) {

          dirEntry.result = 'error'
          dirEntry.details = e.message
          return dirEntry   
        }

      }
    }

}

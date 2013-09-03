package edu.usf.cims.ditto

import groovy.json.JsonSlurper
import grails.converters.JSON

/**
 * PersonSearchService
 */
class PersonSearchService {
    def grailsApplication

    java.util.HashMap findPersonByIdentifer(String identifier, String searchTerm) {

      def dirEntry = [uid: [], attributes: [:] ]
      grailsApplication.config.ditto.user.attributes.each { attribute ->
        dirEntry.uid = ''
        dirEntry.attributes["${attribute}"] = ''
        dirEntry.attributes.remove('uid')
      }

      if (identifier == '' && searchTerm == '') {
        return dirEntry
      } else {
        /* Use the RESTful CAS client plugin to call a webservice to get the user's info */
        def result = withCasRest(
            grailsApplication.config.directory.nams.search.url,
            "GET",
            grailsApplication.config.casRestClient.cas.username,
            grailsApplication.config.casRestClient.cas.password,
            [:],
            [ submit_type:identifier, return_type:"dir_entry", return:"json", value:searchTerm]
        )

        def jsonObj = new JsonSlurper().parseText(result)
        
        jsonObj.dir_entry.each { key, value ->
          if(key == 'uid') {
            dirEntry.uid = value[0]
          } else {
            dirEntry.attributes["${key}"] = value
          }
        }

        dirEntry.attributes.remove('userpassword')
        dirEntry.attributes.remove('employeenumber')
        dirEntry.attributes.remove('description')
        dirEntry.attributes.remove('mailaccessdomain')
        dirEntry.attributes.remove('emailforwardingaddress')
        dirEntry.attributes.remove('objectclass')
        dirEntry.attributes.remove('l')
        dirEntry.attributes.remove('gecos')
        dirEntry.attributes.remove('mailhost')
      
      return dirEntry
      }
    }
}

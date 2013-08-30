package edu.usf.cims.ditto

import groovy.json.JsonSlurper
import grails.converters.JSON

/**
 * DirectorySearchService
 * A service class encapsulates the core business logic of a Grails application
 */
class DirectorySearchService {
    def grailsApplication

    static transactional = true

    def searchNams() {

      def netid = 'epierce'
      def result = withCasRest(   grailsApplication.config.directory.nams.search.url,
                                    "GET",
                                    grailsApplication.config.casRestClient.cas.username,
                                    grailsApplication.config.casRestClient.cas.password,
                                    [:],
                                    [submit_type:"netid",return_type:"dir_entry",return:"json",value:netid])
        
        def jsonObj = new JsonSlurper().parseText(result)
        def accounts = jsonObj.accounts

      return jsonObj
    }
}

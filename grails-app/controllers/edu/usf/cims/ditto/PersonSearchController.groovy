package edu.usf.cims.ditto

/**
 * DirectorySearchController
 */
class PersonSearchController {
  def personSearchService
  def usfCasService

	def index = { 

    def identifier = params['identifier'] ?: ''
    def searchTerm = params['searchTerm'] ?: ''
    
    def directoryEntry = personSearchService.findPersonByIdentifer(identifier, searchTerm)
    log.info "CAS Login by ${usfCasService.username}"
    log.debug "PersonSearch: searchTerm [${searchTerm}] identifier [${identifier}]"
    
    [result : directoryEntry] 
  }
}
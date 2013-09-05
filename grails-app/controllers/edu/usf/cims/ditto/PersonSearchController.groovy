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
    
    [result : directoryEntry] 
  }
}
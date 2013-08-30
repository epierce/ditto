package edu.usf.cims.ditto

/**
 * DirectorySearchController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class DirectorySearchController {
  def directorySearchService

	def index = { 


    render directorySearchService.searchNams() 
  }
}

    /** SSL key & truststore configuration key */
    rest.https.truststore.path = 'resources/certs/rest_client_keystore.jks'
    rest.https.keystore.path='resources/certs/rest_client_keystore.jks'
    /** Certificate Hostname Verifier configuration key */
    rest.https.cert.hostnameVerifier = 'ALLOW_ALL'
    /** Enforce SSL Socket Factory */
    rest.https.sslSocketFactory.enforce = true
    /** The CAS server tickets path **/
    casRestClient.cas.ticketsPath = "/v1/tickets"
    /** Optional Global CAS username - rather than specifying it on each call **/ 
    // casRestClient.cas.username = "mycasusername"        
    /** Optional Global CAS password - rather than specifying it on each call **/ 
    // casRestClient.cas.password = "mycaspassword"        

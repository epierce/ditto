// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.config.locations = ["file:/usr/local/etc/grails/${appName}.groovy"]

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
def catalinaBase = System.properties.getProperty('catalina.base')
if (!catalinaBase) catalinaBase = '.'   // just in case
def logDirectory = "${catalinaBase}/logs"

log4j = { root ->
     appenders {
             rollingFile name:'stdout', file:"${logDirectory}/ditto.log".toString(), maxFileSize:'100MB'
             rollingFile name:'stacktrace', file:"${logDirectory}/ditto_stack.log".toString(), maxFileSize:'100MB'
    }

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.springframework',
           'groovyx.net.http.RESTClient',
           'grails.util.GrailsUtil',
           'groovyx.net.http.ParserRegistry',
           'grails.app'

    info   'grails.app.controllers'

    debug  'grails.app.controllers.edu.usf.cims.ditto.TokenController',
           'grails.app.controllers.edu.usf.cims.ditto.PersonSearchController'

    root.level = org.apache.log4j.Level.INFO
}

grails.config.defaults.locations = [KickstartResources]

ditto.user.usernameAttribute = 'uid'
//Attributes to copy
ditto.user.attributes = ["cn","edupersonaffiliation","edupersonentitlement","edupersonprimaryaffiliation",
      "givenname","mail","namsid","physicaldeliveryofficename","sn","telephonenumber","title",
      "uid","usfeduaffiliation","usfeducampus","usfeducollege","usfedudepartment","usfeduemplid","usfeduhost",
      "usfedumiddlename","usfeduprimaryaffiliation","usfeduprimarycollege","usfeduprimarydepartment","usfeduprivacy",
      "usfeduunumber"]
//Map attribute name to CAS attributes
ditto.user.attributeMapping = [
      "cn":"CommonName", 
      "edupersonaffiliation":"eduPersonAffiliation",
      "edupersonentitlement":"eduPersonEntitlement",
      "edupersonprimaryaffiliation":"eduPersonPrimaryAffiliation",
      "givenname":"GivenName",
      "physicaldeliveryofficename":"MailStop",
      "sn":"Surname",
      "telephonenumber":"Phone",
      "title":"Title",
      "usfeduaffiliation":"USFeduAffiliation",
      "usfeducampus":"USFeduCampus",
      "usfeducollege":"USFeduCollege",
      "usfedudepartment":"USFeduDepartment",
      "usfeduemplid":"USFeduEmplid",
      "usfeduhost":"USFeduHost",
      "usfedumiddlename":"USFeduMiddlename",
      "usfeduprimaryaffiliation":"USFeduPrimaryAffiliation",
      "usfeduprimarycollege":"USFeduPrimaryCollege",
      "usfeduprimarydepartment":"USFeduPrimaryDepartment",
      "usfeduprivacy":"USFeduPrivacy"
]

//Keys to encypt the tokens with
ditto.cas.token.keys = [
  dev: [name: 'ditto', value:'1234567890123456'],
  test: [name: 'ditto', value:'ABCDEFGHIJKLMNOP'],
  production: [name: 'ditto', value:'PhefraTusUh3kABR']
]
//CAS server to send the token to
ditto.cas.loginUrls = [
  dev:'https://dev_cas.example.edu/cas',
  test:'https://test_cas.example.com/cas',
  production:'https://cas_server.example.edu/cas'
]
//URL that the user should be sent to after CAS validates the token
ditto.cas.serviceUrls = [
  dev:'https://www-dev.example.edu/myService',
  test:'https://www-test.example.edu/myService',
  production:'https://www.example.edu/myService'
]

// spring-security-cas-usf settings
grails.plugins.springsecurity.userLookup.userDomainClassName = 'edu.usf.cims.UsfCasUser'
grails.plugins.springsecurity.cas.active = true
grails.plugins.springsecurity.cas.sendRenew = false
grails.plugins.springsecurity.cas.key = '36b54490b63b916b3b931c36295d5d2e' //unique value for each app
grails.plugins.springsecurity.cas.artifactParameter = 'ticket'
grails.plugins.springsecurity.cas.serviceParameter = 'service'
grails.plugins.springsecurity.cas.filterProcessesUrl = '/j_spring_cas_security_check'
grails.plugins.springsecurity.cas.proxyCallbackUrl = 'http://localhost:8080/ditto/secure/receptor' 
grails.plugins.springsecurity.cas.proxyReceptorUrl = '/secure/receptor'
grails.plugins.springsecurity.cas.useSingleSignout = false
grails.plugins.springsecurity.cas.driftTolerance = 120000
grails.plugins.springsecurity.cas.loginUri = '/login'
grails.plugins.springsecurity.cas.useSamlValidator = true
grails.plugins.springsecurity.cas.authorityAttribute = 'eduPersonEntitlement'
grails.plugins.springsecurity.cas.serverUrlPrefix = 'https://authtest.it.usf.edu'
grails.plugins.springsecurity.cas.serviceUrl = 'http://localhost:8080/ditto/j_spring_cas_security_check'

/** Access Control via Spring Security Roles **/
ditto.roles.user = 'ROLE_DITTOUSER'
ditto.roles.admin = [ 
  dev:'ROLE_DITTO_DEV',
  test:'ROLE_DITTO_TEST', 
  production:'ROLE_DITTO_PROD'
]

grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugins.springsecurity.interceptUrlMap = [
  '/login/denied': ['IS_AUTHENTICATED_ANONYMOUSLY'], //Anyone can get to the error page
  '/token/generateToken/dev':   [ditto.roles.user, ditto.roles.admin.dev, 'IS_AUTHENTICATED_FULLY'],
  '/token/generateToken/test':   [ditto.roles.user, ditto.roles.admin.test, 'IS_AUTHENTICATED_FULLY'],
  '/token/generateToken/production':   [ditto.roles.user, ditto.roles.admin.production, 'IS_AUTHENTICATED_FULLY'],
  '/**':           [ditto.roles.user, 'IS_AUTHENTICATED_FULLY']
]

/** RESTful CAS client configuration*/
rest.https.truststore.path = 'resources/certs/rest_client_keystore.jks'
rest.https.keystore.path='resources/certs/rest_client_keystore.jks'
/** Certificate Hostname Verifier configuration key */
rest.https.cert.hostnameVerifier = 'ALLOW_ALL'
/** Enforce SSL Socket Factory */
//rest.https.sslSocketFactory.enforce = true
/** CAS server that autheticates the webservice **/
casRestClient.cas.server = "https://authtest.it.usf.edu"
casRestClient.cas.ticketsPath = "/v1/tickets"        
/** Username/password to use when accessing webservice **/
casRestClient.cas.username = "user"    
casRestClient.cas.password = "secret"
/** URL of the Webservice to call**/
directory.nams.search.url = "https://dev.it.usf.edu/vip/services/ws_convert"

/** Email Notifications **/
//Send notifications to these addresses
ditto.notification.lists = [
  dev:[],
  test:['ditto-audit@example.edu'],
  production:['ditto-audit@example.edu', 'security-admin@example.edu']
]

//Send notifcations from this address
grails.mail.default.from = "ditto_admin@example.edu"

//Send mail using this connection
/*
grails {
  mail {
   host = "smtp.gmail.com"
   port = 465
   username = "user@gmail.com"
   password = "secret"
   props = ["mail.smtp.auth":"true",
            "mail.smtp.socketFactory.port":"465",
            "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
            "mail.smtp.socketFactory.fallback":"false"]
  }
}
*/
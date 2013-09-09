// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

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

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

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


// Added by the Spring Security CAS (USF) plugin:
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

grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugins.springsecurity.interceptUrlMap = [
     '/js/**':        ['IS_AUTHENTICATED_ANONYMOUSLY'],
     '/css/**':       ['IS_AUTHENTICATED_ANONYMOUSLY'],
     '/images/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
     '/login/denied': ['IS_AUTHENTICATED_ANONYMOUSLY'],
     '/**':           ['ROLE_DITTOUSER', 'IS_AUTHENTICATED_FULLY']
]

/** SSL key & truststore configuration key */
rest.https.truststore.path = 'resources/certs/rest_client_keystore.jks'
rest.https.keystore.path='resources/certs/rest_client_keystore.jks'
/** Certificate Hostname Verifier configuration key */
rest.https.cert.hostnameVerifier = 'ALLOW_ALL'
/** Enforce SSL Socket Factory */
rest.https.sslSocketFactory.enforce = true

ditto.user.attributes = ["cn","edupersonaffiliation","edupersonentitlement","edupersonprimaryaffiliation","gidnumber","givenname","homedirectory",
      "loginshell","mail","namsid","physicaldeliveryofficename","sn","telephonenumber","title","uid","uidnumber","usfeduaffiliation",
      "usfeducampus","usfeducollege","usfedudepartment","usfeduemplid","usfeduhost","usfedumiddlename","usfeduprimaryaffiliation",
      "usfeduprimarycollege","usfeduprimarydepartment","usfeduprivacy","usfeduunumber"]

ditto.user.usernameAttribute = 'uid'
ditto.roles.admin = [ preprod:'ROLE_TEST', production:'ROLE_ADMIN']
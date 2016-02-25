grails.servlet.version = "3.0"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}.war"

grails.project.dependency.resolution = {
    inherits("global") { }
    log "warn"
    checksums true
    legacyResolve false
    repositories {
        inherits true
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://developer.ja-sig.org/maven2"
    }

    dependencies {
        compile('org.springframework.security:spring-security-cas-client:3.0.8.RELEASE') {
            transitive = false
        }
        compile('org.jasig.cas:cas-client-core:3.1.10') {
            transitive = false
        }
        compile('org.opensaml:opensaml:1.1') {
            transitive = false
        }
        compile('xml-security:xmlsec:1.3.0') {
            transitive = false
        }
    }

    plugins {
        runtime ":jquery:1.8.3"
        runtime ":resources:1.1.6"

        compile ":lesscss-resources:1.3.3"
        compile ":font-awesome-resources:3.2.1.2"
        compile ":kickstart-with-bootstrap:0.9.6"
        compile ":spring-security-core:1.2.7.3"
        compile ":spring-security-cas-usf:1.3.0"
        compile ":cas-rest-client:0.3.1"
        compile ":rest:0.7"
        compile ":mail:1.0.1"

        build ":tomcat:$grailsVersion"
    }
}

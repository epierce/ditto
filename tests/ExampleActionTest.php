<?php


namespace USF\IdM\SlimSkeleton\Action;

class ExampleActionTest extends \PHPUnit_Framework_TestCase
{
    private $app;

    public function setup()
    {

        $config = new \USF\IdM\UsfConfig(__DIR__ . '/config');

        $app = new \Slim\App(['settings' => $config->slimSettings]);
        // Set up dependencies
        include __DIR__ . '/../app/dependencies.php';
        // Register middleware
        include __DIR__ . '/../app/middleware.php';
        // Register routes
        include __DIR__ . '/../app/routes.php';
        $this->app = $app;
    }

    public function setRequest($method = 'GET', $uri = '/', $other = [])
    {
        // Prepare request and response objects
        $base = [
            'SCRIPT_NAME' => '/index.php',
            'REQUEST_URI' => $uri,
            'REQUEST_METHOD' => $method
        ];

        $env = \Slim\Http\Environment::mock(array_merge($base, $other));
        $uri = \Slim\Http\Uri::createFromEnvironment($env);
        $headers = \Slim\Http\Headers::createFromEnvironment($env);
        $cookies = (array)new \Slim\Collection();
        $serverParams = (array)new \Slim\Collection($env->all());
        $body = new \Slim\Http\Body(fopen('php://temp', 'r+'));
        return new \Slim\Http\Request($method, $uri, $headers, $cookies, $serverParams, $body);
    }

    /**
     * @vcr requestExample1-vcr.yml
     **/
    public function testExampleAction()
    {
        // This is how you mock CAS authentication and attributes
        $extra_request_params = [
            'QUERY_STRING' => '',
            'HTTP_AUTH_PRINCIPAL' => 'test',
            'HTTP_AUTH_ATTR_EDUPERSONPRIMARYAFFILIATION' => 'Student'
        ];

        $req = $this->setRequest('GET', '/example', $extra_request_params);
        $res = new \Slim\Http\Response;

        // Invoke app
        $app = $this->app;
        $resOut = $app($req, $res);
        $this->assertInstanceOf('\Psr\Http\Message\ResponseInterface', $resOut);
        $this->assertContains('<title>SlimSkeleton | Example</title>', (string) $res->getBody());
        $this->assertContains('<h1>Album 1</h1>', (string) $res->getBody());
    }

    /**
     * @vcr requestExample2-vcr.yml
     **/
    public function testExampleActionRouteParam()
    {
        // This is how you mock CAS authentication and attributes
        $extra_request_params = [
            'QUERY_STRING' => '',
            'HTTP_AUTH_PRINCIPAL' => 'test',
            'HTTP_AUTH_ATTR_EDUPERSONPRIMARYAFFILIATION' => 'Student'
        ];

        $req = $this->setRequest('GET', '/example/50', $extra_request_params);
        $res = new \Slim\Http\Response;

        // Invoke app
        $app = $this->app;
        $resOut = $app($req, $res);
        $this->assertInstanceOf('\Psr\Http\Message\ResponseInterface', $resOut);
        $this->assertContains('<title>SlimSkeleton | Example</title>', (string) $res->getBody());
        $this->assertContains('<h1>Album 50</h1>', (string) $res->getBody());
    }

    /**
     * @vcr requestExample3-vcr.yml
     **/
    public function testExampleActionQueryParam()
    {
        // This is how you mock CAS authentication and attributes
        $extra_request_params = [
            'QUERY_STRING' => 'album=25',
            'HTTP_AUTH_PRINCIPAL' => 'test',
            'HTTP_AUTH_ATTR_EDUPERSONPRIMARYAFFILIATION' => 'Student'
        ];

        $req = $this->setRequest('GET', '/example', $extra_request_params);
        $res = new \Slim\Http\Response;

        // Invoke app
        $app = $this->app;
        $resOut = $app($req, $res);
        $this->assertInstanceOf('\Psr\Http\Message\ResponseInterface', $resOut);
        $this->assertContains('<title>SlimSkeleton | Example</title>', (string) $res->getBody());
        $this->assertContains('<h1>Album 25</h1>', (string) $res->getBody());
    }
}

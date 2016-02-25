<?php
/**
* @category usf-it
* @package slim-skeleton
* @author Eric Pierce <epierce@usf.edu>
* @license http://www.opensource.org/licenses/MIT MIT
* @link https://github.com/USF-IT/slim-skeleton
 */

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

error_reporting(-1);
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
date_default_timezone_set('America/New_York');

if (PHP_SAPI == 'cli-server') {
    // To help the built-in PHP dev server, check if the request was actually for
    // something which should probably be served as a static file
    $file = __DIR__ . $_SERVER['REQUEST_URI'];
    if (is_file($file)) {
        return false;
    }
}

// Initialize Composer autoloader
if (!file_exists($autoload = '../vendor/autoload.php')) {
    throw new \Exception('Composer dependencies not installed.');
}

require_once $autoload;
session_start();

if (!class_exists('\\Slim\\App')) {
    throw new \Exception(
        'Missing Slim from Composer dependencies.  Ensure slim/slim is in composer.json'
    );
}
// Create a config object
$configDir = isset($_ENV['PS_CONFIG_DIR']) ? $_ENV['PS_CONFIG_DIR'] : '../config';
$usfConfigObject = new \USF\IdM\UsfConfig($configDir);

// Run Slim application
if (is_array($usfConfigObject->slimSettings)) {
    $app = new \Slim\App(['settings' => $usfConfigObject->slimSettings]);
    // Set up dependencies
    require __DIR__ . '../../app/dependencies.php';
    // Register middleware
    require __DIR__ . '../../app/middleware.php';
    // Register routes
    require __DIR__ . '../../app/routes.php';
    $app->run();
} else {
    throw new \Exception('No Slim configuration data found!', 500);
}

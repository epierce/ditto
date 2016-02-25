<?php

error_reporting(-1);
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
date_default_timezone_set('America/New_York');

define('PROJECT_ROOT', realpath(__DIR__.'/..'));
require_once PROJECT_ROOT.'/vendor/autoload.php';

define('TEST_CONFIG_DIR', PROJECT_ROOT.'/tests/config');

//Enable PHP-VCR for webservice recording/playback
\VCR\VCR::turnOn();
\VCR\VCR::configure()
    ->enableRequestMatchers(['host', 'method', 'url', 'query_string', 'post_fields'])
    ->setMode('once');

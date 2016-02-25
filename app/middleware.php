<?php
/**
 *
 * Configure Application Middleware
 * see: http://www.slimframework.com/docs/concepts/middleware.html
 * 
 */

$c = $app->getContainer();
$settings = $c->settings;

// -----------------------------------------------------------------------------
// Log request results
// -----------------------------------------------------------------------------
$app->add(new \USF\IdM\Middleware\RequestResultLogger($c->logger));

// -----------------------------------------------------------------------------
// Authentication and Authorization
// -----------------------------------------------------------------------------
$app->add(new \USF\auth\PSR7\UsfAuthMiddleware($settings['authentication']));

// -----------------------------------------------------------------------------
// Get Client IP and store it in the request object
// -----------------------------------------------------------------------------
$app->add(new \RKA\Middleware\IpAddress());

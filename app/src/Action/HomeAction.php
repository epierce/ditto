<?php

namespace USF\IdM\SlimSkeleton\Action;

use Slim\Views\Twig;
use Slim\Collection;
use Psr\Log\LoggerInterface;
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;


/**
 * Main page
 *
 * @category usf-it
 * @package slim-skeleton
 * @author Eric Pierce <epierce@usf.edu>
 * @license http://www.opensource.org/licenses/MIT MIT
 * @link https://github.com/USF-IT/slim-skeleton
 */
final class HomeAction
{
    private $view;
    private $logger;
    private $settings;

    /**
     * Class constructor
     *
     * @param Twig            $view      View object
     * @param LoggerInterface $logger    Log object
     * @param Collection      $settings  Slim Settings
     */
    public function __construct(Twig $view, LoggerInterface $logger, Collection $settings)
    {
        $this->view = $view;
        $this->logger = $logger;
        $this->settings = $settings;
    }

    /**
     * Run Controller
     *
     * @param ServerRequestInterface $request  PSR7 Request object
     * @param ResponseInterface      $response PSR7 Response object
     * @param array                  $args     Request arguments
     * @return ResponseInterface
     */
    public function dispatch(Request $request, Response $response, $args)
    {
        /**
         * If you need CAS authentication, make sure to update the `interceptUrlMap`
         * map in the config file.  The username will be available like this:
         *
         * $netid = $request->getHeaderLine('AUTH_PRINCIPAL');
         *
         * and the attributes:
         *
         * $usfid = $request->getHeaderLine('AUTH_ATTR_USFEDUUNUMBER');
         */

        $view_attr = [
            'page_title' => 'SlimSkeleton | Main'  // This will used in the <title> element on the page
        ];

        return $this->view->render($response, 'home.html', $view_attr);
    }
}

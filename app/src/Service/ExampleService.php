<?php
/**
 * Example Service
 *
 * @category usf-it
 * @package slim-skeleton
 * @author Eric Pierce <epierce@usf.edu>
 * @license http://www.opensource.org/licenses/MIT MIT
 * @link https://github.com/USF-IT/slim-skeleton
 */
namespace USF\IdM\SlimSkeleton\Service;

use Psr\Log\LoggerInterface;
use Slim\Collection;
use GuzzleHttp\Client;

/**
 *
 *
 * @category usf-it
 * @package slim-skeleton
 * @author Eric Pierce <epierce@usf.edu>
 * @license http://www.opensource.org/licenses/MIT MIT
 * @link https://github.com/USF-IT/slim-skeleton
 */
class ExampleService
{
    private $logger;
    private $settings;

    public function __construct(LoggerInterface $logger, Collection $settings )
    {
        $this->logger = $logger;
        $this->settings = $settings;

        // Pull config data from the settings object
        $serviceConfig = $settings['example_config'];

        // If you need to configure your service object, do it here and  add it as a
        // private property to the class.
    }

    /**
     * Get a list of photos from a public webservice (http://jsonplaceholder.typicode.com/)
     *
     * @param $input_text
     * @return mixed
     */
    public function getPhotos($album_id)
    {
        $client = new Client();

        // get JSON from the webservice and convert it into an assoc. array
        return json_decode($client->get("http://jsonplaceholder.typicode.com/album/${album_id}/photos")->getBody(), true);

    }
}

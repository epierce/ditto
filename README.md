# Slim Framework 3 Skeleton Application

This is based on the [Slim-Skeleton](https://github.com/slimphp/Slim-Skeleton) project.

Use this skeleton application to quickly setup and start working on a new Slim Framework 3 application. This is a simple example application using
* [Slim 3](https://github.com/slimphp/Slim)
* [Slim-Views](https://github.com/slimphp/PHP-View)
* [Twig](http://twig.sensiolabs.org) templates
* [USF-Auth](https://github.com/USF-IT/usf-auth) authentication middleware
* [PHP-VCR](https://github.com/php-vcr/php-vcr) for mocking web service requests

This skeleton application was built for Composer. This makes setting up a new Slim Framework application quick and easy.

## Install Composer
Directions are available [here](https://getcomposer.org/)

## Install the Application

After you install Composer, run this command from the directory in which you want to install your new Slim Framework application.

    php composer.phar create-project usf-it/slim-skeleton [my-app-name]

Replace <code>[my-app-name]</code> with the desired directory name for your new application. You'll want to:
* Point your virtual host document root to your new application's `public/` directory.
* Ensure `logs/` and `cache/` are web writeable.

### Install Node.js packages

    npm install

### Use Grunt to run the PHP server

    grunt serve

That's it! Now go build something cool.

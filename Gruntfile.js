//Gruntfile
'use strict';

module.exports = function(grunt) {

    require('load-grunt-tasks')(grunt);

    //Initializing the configuration object
    grunt.initConfig({

        // Configurable paths for the application
        appConfig: {
            phpSrc: 'app/src',
            assetSrc: 'assets',
            assetTgt: 'public/assets',
            templates: 'templates',
            webDir: 'public',
            cacheDir: 'cache',
            logDir: 'logs'
        },

        // Task configuration
        clean: {
            options: {
                "no-write": false,  // Change to true for testing
                force: true
            },
            build: [
                '<%= appConfig.assetTgt %>/**/*',
                '<%= appConfig.cacheDir %>/*',
                '<%= appConfig.logDir %>/*',
                '!**/README.md'
            ]
        },
        php: {
            dist: {
                options: {
                    hostname: '127.0.0.1',
                    port: 8080,
                    base: 'public',
                    keepalive: false,
                    open: false
                }
            }
        },
        browserSync: {
            dist: {
                bsFiles: {
                    src : [
                        '<%= appConfig.assetTgt %>/stylesheets/*.css',
                        '<%= appConfig.assetTgt %>/javascript/*.js',
                        '<%= appConfig.webDir %>/*.php',
                        '<%= appConfig.webDir %>/**/*.php',
                        '<%= appConfig.phpSrc %>/*.php',
                        '<%= appConfig.phpSrc %>/**/*.php',
                        '<%= appConfig.templates %>/*.html',
                        '<%= appConfig.templates %>/**/*.html'
                    ]
                },
                options: {
                    proxy: '<%= php.dist.options.hostname %>:<%= php.dist.options.port %>',
                    watchTask: true,
                    notify: true,
                    open: true,
                    logLevel: 'silent',
                    ghostMode: {
                        clicks: true,
                        scroll: true,
                        links: true,
                        forms: true
                    }
                }
            }
        },
        imagemin: {
            images: {
                options: {
                    optimizationLevel: 4,
                    progressive: true,
                    interlaced: true
                },
                files: [{
                    expand: true,
                    cwd: '<%= appConfig.assetSrc %>/images/',
                    src: ['**/*.{png,jpg,gif}'],
                    dest: '<%= appConfig.assetTgt %>/images/'
                }]
            }
        },
        concat: {
            options: {
                separator: ';\n'
            },
            js: {
                src: ['<%= appConfig.assetSrc %>/javascript/main.js', '<%= appConfig.assetSrc %>/javascript/functions.js'],
                dest: '<%= appConfig.assetTgt %>/javascript/main.js'
            },
            css: {
                src: [
                    '<%= appConfig.assetSrc %>/stylesheets/styles.css'
                ],
                dest: '<%= appConfig.assetTgt %>/stylesheets/main.css'
            }
        },
        cssmin: {
            main: {
                files: [{
                    expand: true,
                    cwd: '<%= appConfig.assetTgt %>/stylesheets',
                    src: ['main.css'],
                    dest: '<%= appConfig.assetTgt %>/stylesheets',
                    ext: '.min.css'
                }]
            }
        },
        uglify: {
            options: {
                mangle: true
            },
            main_js: {
                files: {
                    '<%= appConfig.assetTgt %>/javascript/main.min.js': '<%= appConfig.assetTgt %>/javascript/main.js',
                }
            }
        },
        phpunit: {
            classes: {
                dir: 'tests/'
            },
            options: {
                bin: 'vendor/bin/phpunit',
                colors: true,
                coverageHtml: 'coverage'
            }
        },
        watch: {
            main_js: {
                files: [
                    '<%= appConfig.assetSrc %>/javascript/main.js'
                ],
                tasks: [
                    'concat:main_js',
                    'uglify:frontend'
                ],
                options: {
                    livereload: true
                }
            },
            tests: {
                files: ['<%= appConfig.phpSrc %>/**/*.php'],
                tasks: ['phpunit']
            }
        }
    });

    // Task definitions
    grunt.registerTask('default', ['serve']);
    grunt.registerTask('build', [
        'clean',
        'phpunit',
        'imagemin',
        'concat',
        'cssmin',
        'uglify'
    ]);
    grunt.registerTask('serve', [
        'clean',
        'phpunit',
        'imagemin',
        'concat',
        'cssmin',
        'uglify',
        'php:dist',
        'browserSync:dist',
        'watch'
    ]);
};

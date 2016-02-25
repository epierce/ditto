//Gruntfile
'use strict';

module.exports = function(grunt) {

    require('load-grunt-tasks')(grunt);

    //Initializing the configuration object
    grunt.initConfig({

        // Configurable paths for the application
        appConfig: {
            phpSrc: 'app/src',
            npmAssets: 'node_modules',
            assetSrc: 'assets',
            assetTgt: 'public/assets',
            templates: 'templates',
            webDir: 'public'
        },

        // Task configuration
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
        sass: {
            dist: {
                options: {
                    style: 'compressed'
                },
                files: {
                    '<%= appConfig.assetTgt %>/stylesheets/main.css':'<%= appConfig.assetSrc %>/stylesheets/styles.scss'
                }
            }
        },
        copy: {
            main: {
                files: [
                    {
                        expand: true,
                        flatten: true,
                        src: ['<%= appConfig.assetSrc %>/fonts/**'],
                        dest: '<%= appConfig.assetTgt %>/fonts',
                        filter: 'isFile'
                    },
                    {
                        expand: true,
                        flatten: true,
                        src: ['<%= appConfig.assetSrc %>/images/**'],
                        dest: '<%= appConfig.assetTgt %>/images',
                        filter: 'isFile'
                    },
                ]
            }
        },
        concat: {
            options: {
                separator: ';',
            },
            main_js: {
                src: ['<%= appConfig.assetSrc %>/javascript/main.js'],
                dest: '<%= appConfig.assetTgt %>/javascript/main.js'
            }
        },
        uglify: {
            options: {
                mangle: true
            },
            main_js: {
                files: {
                    '<%= appConfig.assetTgt %>/javascript/main.js': '<%= appConfig.assetTgt %>/javascript/main.js',
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
            sass: {
                files: [
                    '<%= appConfig.assetSrc %>/stylesheets/*.scss',
                    '<%= appConfig.assetSrc %>/stylesheets/**/*.scss'
                ],
                tasks: ['sass'],
                options: {
                    livereload: true
                }
            },
            tests: {
                files: ['<%= appConfig.phpSrc %>/**/*.php'],  //the task will run only when you save files in this location
                tasks: ['phpunit']
            }
        }
    });

    // Task definition
    grunt.registerTask('default', ['watch']);
    grunt.registerTask('build', [
        'phpunit',
        'copy',
        'sass',
        'concat',
        'uglify'
    ]);
    grunt.registerTask('serve', [
        'phpunit',
        'copy',
        'sass',
        'concat',
        'uglify',
        'php:dist',         // Start PHP Server
        'browserSync:dist', // Using the php instance as a proxy
        'watch'             // Any other watch tasks you want to run
    ]);
};

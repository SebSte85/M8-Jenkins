/* groovylint-disable CompileStatic */
pipeline {
    agent any

    tools {
        maven 'maven-3.9.1'
    }
    stages {
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }
        stage('increment version...') {
            steps {
                script {
                    gv.incrementVersion()
                }
            }
        }
        stage('build jar...') {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage('build image...') {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage('deploy...') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
        stage('commit version update...') {
            steps {
                script {
                    gv.versionUpdate()
                }
            }
        }
    }
}


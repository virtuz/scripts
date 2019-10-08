#!/usr/bin/env groovy

String pipelineScript = '''
pipeline {
    agent any
    options {
        timeout(activity: true, time: 1, unit: 'HOURS')
        timestamps()
    }
    parameters { 
        string(name: 'name', defaultValue: 'stranger', description: 'just a name')
        booleanParam(name: 'dry_run', defaultValue: false, description: 'special parameter which used only for the first run to propogate actual configuration from pipeline script')
    }
    stages {
        stage('hello') {
            when { not { expression { return params.dry_run } } }
            steps {
                echo "hello ${params.name}"
            }
        }
        stage('Process Job DSLs') {
            when { not { expression { return params.dry_run } } }
            steps {
                jobDsl lookupStrategy: 'SEED_JOB', targets: 'jobs/**/*.groovy'
            }
        }
    }
}
'''
pipelineJob('dry-run') {
    parameters {
        booleanParam('dry_run', true, 'special parameter which used only for the first run to propogate actual configuration from pipeline script')
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }

}
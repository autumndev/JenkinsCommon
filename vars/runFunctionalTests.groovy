#!/usr/bin/env groovy

def call(Map config) {
	def buildNumber = config?.buildNumber
    def branchName = config?.branchName

	withEnv([
        "BUILD_NUMBER="+buildNumber,
        "BRANCH_NAME="+branchName,
		"TEST_SUITE=JenkinsFunctional"
    ]) {
	    sh(copyGlobalLibraryScript('test.sh'))
    }
	def checkstyle = scanForIssues pattern: 'Reports/**/JenkinsFunctional-phpunit-junit.xml', reportEncoding: '', sourceCodeEncoding: '', tool: [$class: 'CheckStyle']
	publishIssues id: 'functionaltests', name: 'Function Tests', issues:[checkstyle],  healthy: 10000, unHealthy: 9000
}
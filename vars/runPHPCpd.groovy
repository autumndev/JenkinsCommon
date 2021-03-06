#!/usr/bin/env groovy

def call(Map config) {
	sh(copyGlobalLibraryScript('phpcpd.sh'))
	def checkstyle = scanForIssues tool: cpd(pattern: 'reports/phpcpd.pmd.xml')
	publishIssues id: 'copyPaste', name: 'Copy Paste Detection', issues:[checkstyle],  healthy: 10000, unHealthy: 11000
}
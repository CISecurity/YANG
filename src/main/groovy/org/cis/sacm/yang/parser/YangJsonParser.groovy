package org.cis.sacm.yang.parser

import groovy.json.JsonSlurper
import org.slf4j.LoggerFactory

class YangJsonParser {
	def log = LoggerFactory.getLogger(YangJsonParser.class)

	def filepath
	def yangJson

	def parse() {
		def file = new File(filepath)
		if (file.exists()) {
			yangJson = new JsonSlurper().parse(file)
			def b
		}
	}
}

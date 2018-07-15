package org.cis.sacm.yang.coll

import groovy.xml.StreamingMarkupBuilder
import org.cis.sacm.yang.parser.Collection
import org.slf4j.LoggerFactory
import org.cis.sacm.yang.parser.Collections

class Collector {
	def log = LoggerFactory.getLogger(Collector.class)

	def results = [:]

	def collect(Collections collections) {
		log.info "[START] Starting Collection"
		collections.collections.keySet().each { ck ->
			Collection c = collections.collections[ck]
			switch (c.collectionType.family) {
				case "windows":
					switch (c.collectionType.type) {
						case "FILE":
							//
							results[c.collectionId] = new FileCollector().collect(c.collectionId, c.collectionFields)
							break
						default:
							break
					}
					break
				default:
					break
			}
		}
		log.info "[ END ] Collection Complete"

		def rez = new StreamingMarkupBuilder().bind {
			"yang-collection-results" {
				"yang-collection-items" {
					results.keySet().each { k ->
						mkp.yieldUnescaped results[k]
					}
				}
			}
		}.writeTo(new File("C:\\_Development\\Projects\\Standards\\YANG\\results\\output.xml").newWriter())
	}
}

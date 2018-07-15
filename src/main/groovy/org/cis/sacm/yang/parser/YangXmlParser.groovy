package org.cis.sacm.yang.parser

import org.slf4j.LoggerFactory

class YangXmlParser {
	def log = LoggerFactory.getLogger(YangXmlParser.class)

	def xmlParser = new XmlParser(false, false)
	//xmlParser.setFeature("http://xml.org/sax/features/external-general-entities", false)

	def filepath
	def yangXml

	def parse() {
		def file = new File(filepath)
		if (file.exists()) {
			// TODO Add schema validation if you can
			yangXml = xmlParser.parse(file)

			// If the XML parsed, the first child of the root element should
			// be the <collections> element...
			def collections = new Collections()

			def collectionsNode = yangXml.children()[0]

			// Let's parse the collections element.
			collectionsNode.collection.each { c ->
				def cid = c."collection-id".text().trim()
				def col = new Collection()
				col.parse(c)
				collections.collections[cid] = col
			}

			collections.normalize()

			return collections
		} else {
			log.error "File ${filepath} does not exist; Exiting."
			return null
		}
	}
}


class Collections {
	def collections = [:]

	/**
	 * find any collections with sets and normalize their collection references
	 * @return
	 */
	def normalize() {
		collections.keySet().each { cid ->
			def c = collections[cid]
			if (c.set) {
				def c1 = collections.keySet().find { k -> k == c.set.object1 }
				c.set.collection1 = collections[c1]

				def c2 = collections.keySet().find { k -> k == c.set.object2 }
				c.set.collection2 = collections[c2]
			}
		}
	}
}

class Collection {
	def collectionId
	def collectionType
	def collectionFields = []
	def set
	def filters = []

	def parse(def c) {
		collectionId = c."collection-id".text().trim()
		collectionType =
			new CollectionType(
				family: c."collection-type".family.text().trim(),
				type: c."collection-type".type.text().trim())
		if (c."set") {
			set = new Set()
			set.parse(c."set")
		}
		if (c."collection-fields") {
			c."collection-fields"."collection-field".each { f ->
				def cf = new CollectionField()
				cf.parse(f)
				collectionFields << cf
			}
		}
		if (c."filter") {
			c."filter".each { f ->
				def filter = new Filter()
				filter.parse(f)
				filters << filter
			}
		}
	}
}

class CollectionType {
	def family
	def type
}

class Field {
	def name
	def datatype
	def operation
	def value

	def parse(def f) {
		name = f."name".text().trim()
		datatype = f."datatype".text().trim()
		operation = f."operation".text().trim()
		value = f."value".text().trim()
	}
}

class CollectionField extends Field {

}

class Set {
	def setCombination
	def object1
	def object2

	// the referenced collections
	def collection1
	def collection2

	def parse(def s) {
		setCombination = s."set-combination".text().trim()
		object1 = s."object1".text().trim()
		object2 = s."object2".text().trim()
	}
}

class Filter {
	def filterFields = []

	def parse(def f) {
		f."filter-fields"."filter-field".each { ff ->
			def fld = new FilterField()
			fld.parse(ff)
			filterFields << fld
		}
	}
}

class FilterField extends Field {

}
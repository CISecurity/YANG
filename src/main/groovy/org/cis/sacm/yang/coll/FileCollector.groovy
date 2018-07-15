package org.cis.sacm.yang.coll

import groovy.xml.StreamingMarkupBuilder

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.UserPrincipal

class FileCollector {

	def collect(def collectionId, def collectionFields) {
		def cf = collectionFields[0]

		def file = new File(cf.value)
		Path path = Paths.get(file.toURI())
		BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class)

		long ct = attrs.creationTime().toMillis()
		long at = attrs.lastAccessTime().toMillis()
		long mt = attrs.lastModifiedTime().toMillis()
		long sz = attrs.size()

		UserPrincipal up = Files.getOwner(path)

		def rez = new StreamingMarkupBuilder().bind {
			"file-item" {
				"collection-idref" collectionId
				"filepath" file.canonicalPath
				delegate."path" file.parent
				"filename" file.name
				"owner" up.name
				"size" String.valueOf(sz)
				"a-time" String.valueOf(at)
				"c-time" String.valueOf(ct)
				"m-time" String.valueOf(mt)
//				"ms-checksum"
//				"version"
//				"file-type"
//				// 0..n
//				"file-attribute"
//				"development-class"
//				"company"
//				"internal-name"
//				"language"
//				"original-filename"
//				"product-name"
//				"product-version"
				"windows-view" "64-bit"
			}
		}

		return rez.toString()
	}
}

package org.cis.sacm.yang

import org.slf4j.LoggerFactory

/**
 * Utility Singleton
 * Created by wmunyan on 3/29/2017.
 */
@Singleton
class Utilities {
	def log = LoggerFactory.getLogger(Utilities.class)

	def console = new CommandLineConsole()
	
	/**
	 * Determine if the application is running from within a compiled JAR
	 * @return
	 */
	def isRunningInJar() {
		def path = YANG.class.protectionDomain.codeSource.location.path

		log.info "Discovered path --> ${path}"

		try {
			path = URLDecoder.decode(path, "UTF-8")
		} catch (UnsupportedEncodingException uee) {
			log.warn("UnsupportedEncodingException -- ${uee.getLocalizedMessage()}")
		}
		return (path.endsWith(".jar"))
	}

	/**
	 * Determine the path to the Assessor.jar file, if it exists.  Otherwise
	 * return the user.dir system property, indicating the working directory
	 * from which the main class was executed.
	 *
	 * @param userDirectory
	 * @return
	 */
	def getJarPath(String userDirectory = null) {
		if (!userDirectory) { userDirectory = System.getProperty("user.dir") }

		// This is the case when executing from an IDE.
		if (!isRunningInJar()) {
			return userDirectory
		}

		def path = Assessor.class.protectionDomain.codeSource.location.path
		try {
			path = URLDecoder.decode(path, "UTF-8")
			path = path.replace("/", File.separator)

			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				path = path.substring(1)
			}
		} catch (UnsupportedEncodingException uee) {
			// do nothing
		}
		return path
	}

	/**
	 * If the application is executing from a compiled JAR, get the directory
	 * in which it resides.  This becomes the working directory of the application,
	 * allowing for defaulting of relative directories, such as the "benchmarks" folder
	 *
	 * @param userDirectory
	 * @return
	 */
	def getJarDirectoryPath(String userDirectory = null) {
		if (!userDirectory) { userDirectory = System.getProperty("user.dir") }

		def jarPath = getJarPath(userDirectory)
		log.info "Discovered jar path --> ${jarPath}"
		if (!jarPath.endsWith(".jar")) {
			return userDirectory
		}

		def jarFile = new File(jarPath)
		if (!jarFile.exists() || !jarFile.isFile()) {
			return userDirectory
		}
		return jarFile.parent
	}

	/**
	 * If the application is executing from a compiled JAR, get the directory
	 * in which it resides.  This becomes the working directory of the application,
	 * allowing for defaulting of relative directories, such as the "benchmarks" folder
	 *
	 * @param userDirectory
	 * @return
	 */
	def getJarDirectoryFile(String userDirectory = null) {
		return new File(getJarDirectoryPath())
	}

	/**
	 * Determine the "local scripts dir pathname"
	 * @return
	 */
	def getApplicationScriptsPath() {
		return isRunningInJar() ?
			"${getJarDirectoryPath()}${File.separator}scripts" :
			"C:\\_Development\\Projects\\Assessor-Shared\\scripts"
	}

	/**
	 * Determine the "local scripts dir pathname"
	 * @return
	 */
	def getApplicationScriptsFile() {
		return new File(getApplicationScriptsPath())
	}
}

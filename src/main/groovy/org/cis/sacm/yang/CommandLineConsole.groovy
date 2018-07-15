package org.cis.sacm.yang

import org.slf4j.LoggerFactory

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.jar.JarFile

/**
 * Command-Line Utility methods.
 *
 * Created by wmunyan on 3/29/2017.
 */
class CommandLineConsole {

	def log = LoggerFactory.getLogger(CommandLineConsole.class)

	Console console = System.console()

	// Console in "quiet mode"?
	boolean quiet = false

	/**
	 * Read a response from the user to a prompt/question
	 * @param prompt
	 * @param session
	 * @return
	 */
	String readLine(String prompt) {
		String line = null
		if (console != null) {
			line = console.readLine(prompt)
		} else {
			print prompt
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
			try {
				line = br.readLine()
			} catch (IOException e) {
				//Ignore
			}
		}

		if (line == "q!") {
			writeSelection("User selected to quit.  Exiting.")
			System.exit(0)
		}
		return line
	}

	/**
	 * Read numeric input from the user
	 * @param prompt
	 * @param session
	 * @return
	 */
	Integer readNumericInput(String prompt) {
		// This will exit the application if the user enters q!
		String response = readLine(prompt)

		try {
			Integer value = Integer.parseInt(response)
			return value
		} catch (NumberFormatException nfe) {
			writeLine("Please enter a valid number, or 'q!' to quit.")
			return readNumericInput(prompt)
		}
	}

	/**
	 * Read numeric input from the user, allowing them to enter a blank
	 * @param prompt
	 * @param session
	 * @return
	 */
	Integer readNumericInputAllowBlank(String prompt) {
		// This will exit the application if the user enters q!
		String response = readLine(prompt)

		if (response) {
			try {
				Integer value = Integer.parseInt(response)
				return value
			} catch (NumberFormatException nfe) {
				writeLine("Please enter a valid number, leave blank for no selection, or 'q!' to quit.")
				return readNumericInput(prompt)
			}
		}
		return null
	}

	/**
	 * Allow the user to enter an obfuscated password on the console
	 * @param user
	 * @return
	 */
	def readPassword(String user) {
		def prompt = "Password for ${user}: "
		def creds

		if (console) {
			creds = console.readPassword("%s", prompt)
		} else {
			print prompt
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
			try {
				creds = br.readLine().toCharArray()
			} catch (IOException e) {
				//Ignore
			}
		}
		return creds
	}

	/**
	 * Write a line of text to the console, surrounded by blank lines
	 * @param text
	 * @return
	 */
	def writeSelection(String text) {
		if (!quiet) {
			if (console) {
				console.writer().println()
				console.writer().println(text)
				console.writer().println()
			} else {
				println ""
				println text
				println ""
			}
		} else {
			log.info text
		}
	}

	/**
	 * Write a line of text to the console
	 * @param text
	 * @return
	 */
	def writeLine(String text) {
		if (!quiet) {
			if (console) {
				console.writer().println(text)
			} else {
				println text
			}
		} else {
			log.info text
		}
	}

	/**
	 * Display the application's welcome banner
	 * @return
	 */
	def displayWelcome() {
		def (title, buildDate, version) = {
			if (Utilities.instance.isRunningInJar()) {
				def jarPath = Utilities.instance.getJarPath()
				def jar = new JarFile(jarPath)

				def attributes = jar.manifest.mainAttributes

				return [
				    attributes.getValue("Implementation-Title"),
					attributes.getValue("Build-Date"),
					"v${attributes.getValue("Implementation-Version")}"
				]
			} else {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm aa", new Locale("en-us"))

				return ["SACM YANG Parser", df.format(new Date()), "DEVELOPMENT"]
			}
		}.call()

		writeLine """
	-----------------------------------------------------------------------------------------------------    
		`8.`8888.      ,8'    .8.          b.             8     ,o888888o.    
		 `8.`8888.    ,8'    .888.         888o.          8    8888     `88.  
		  `8.`8888.  ,8'    :88888.        Y88888o.       8 ,8 8888       `8. 
		   `8.`8888.,8'    . `88888.       .`Y888888o.    8 88 8888           
		    `8.`88888'    .8. `88888.      8o. `Y888888o. 8 88 8888           
		     `8. 8888    .8`8. `88888.     8`Y8o. `Y88888o8 88 8888           
		      `8 8888   .8' `8. `88888.    8   `Y8o. `Y8888 88 8888   8888888 
		       8 8888  .8'   `8. `88888.   8      `Y8o. `Y8 `8 8888       .8' 
		       8 8888 .888888888. `88888.  8         `Y8o.`    8888     ,88'  
		       8 8888.8'       `8. `88888. 8            `Yo     `8888888P'    
	-----------------------------------------------------------------------------------------------------
	           Welcome to ${title}; Version: ${version}; built on ${buildDate}
	-----------------------------------------------------------------------------------------------------
	  An open-source project of the IETF Security Automation and Continuous Monitoring working group.
	-----------------------------------------------------------------------------------------------------
		"""
	}
}

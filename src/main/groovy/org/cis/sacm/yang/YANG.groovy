package org.cis.sacm.yang

import org.cis.sacm.yang.coll.Collector
import org.cis.sacm.yang.parser.YangJsonParser
import org.cis.sacm.yang.parser.YangXmlParser
import org.slf4j.LoggerFactory

class YANG {
	def log = LoggerFactory.getLogger(YANG.class)
	def cli = new CliBuilder(usage: 'YANG -[options] <extras>', width: 100)

	def console = new CommandLineConsole()

	/**
	 * Entry point
	 * @param args
	 */
	static void main(String[] args) {
		new YANG().start(args)
	}

	/**
	 * Initialize the command line options
	 */
	void initialize() {
		cli.with {
			h  longOpt: "help", "Show usage information"

			ix longOpt: "instance-xml",  args: 1, argName: 'YANG Instance XML file', "Absolute filepath to YANG instance XML"
			ij longOpt: "instance-json", args: 1, argName: 'YANG Instance JSON file', "Absolute filepath to YANG instance JSON"
		}

		StringWriter hdr = new StringWriter()
		hdr.println("---------------------------------------------------------------------------------------------------")
		hdr.println(" Options                                           Tip")
		hdr.println("---------------------------------------------------------------------------------------------------")

		cli.header = hdr.toString()
	}

	/**
	 * Start your engines
	 * @param args
	 */
	void start(String[] args) {
		initialize()

		def options = cli.parse(args)
		if (args.size() == 0 || options.h) {
			console.displayWelcome()
			cli.usage()
			System.exit(0)
		}

		def collections = {
			if (options.ix) {
				log.info "YANG --> XML (${options.ix})"
				return new YangXmlParser(filepath: options.ix).parse()
			} else if (options.ij) {
				log.info "YANG --> JSON"
				return new YangJsonParser(filepath: options.ij).parse()
			} else {
				log.info "No valid YANG instance type was specified; Exiting."
				return null
			}
		}.call()

		if (collections) {
			new Collector().collect(collections)
		}
		System.exit(0)
	}
}

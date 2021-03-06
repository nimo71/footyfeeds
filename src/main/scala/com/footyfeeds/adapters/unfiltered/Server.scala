package com.footyfeeds.adapters.unfiltered

import unfiltered.jetty.Http
import org.slf4j.LoggerFactory

object Server {
	def logger = LoggerFactory.getLogger(this.getClass)

	val http = Http.local(8080)

	def main(args: Array[String]) {
		http.filter( App )
			.run({ svr =>
		  		logger.info("Starting up server")
			}, { svr =>
				logger.info("Shutting down server")
			})
	}
}
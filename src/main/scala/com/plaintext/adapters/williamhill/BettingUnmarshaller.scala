package com.plaintext.adapters.williamhill

import com.plaintext.domain.Betting
import com.plaintext.domain.BetType
import scala.xml._

object BettingUnmarshaller {

	def apply(xmlString: String): Option[Betting] = {
		val root = XML.loadString(xmlString)

		val betTypes = 
			for (
				element <- (root \\ "response" \\ "williamhill" \\ "class" \\ "type").toList;
				name <- element.attribute("name")  
			) 
			yield {	new BetType(name.text) }

		if (betTypes.length > 0)
			Some(new Betting(betTypes))
		else
			None
	}
}
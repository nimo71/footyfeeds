package com.plaintext.adapters.williamhill

import com.plaintext.domain._
import scala.xml._

object BettingUnmarshaller {

	def apply(xmlString: String): Option[Betting] = {
		val root = XML.loadString(xmlString)

		val betTypes = 
			for (
				typeElement <- (root \\ "response" \\ "williamhill" \\ "class" \\ "type").toList;
				typeName <- typeElement.attribute("name")
			) 
			yield {	
				var markets = unmarshallMarkets(typeElement)	
				new BetType(typeName.text, markets)				
			}

		if (betTypes.length > 0)
			Some(new Betting(betTypes))
		else
			None
	}

	private def unmarshallMarkets(typeElement: Node): Seq[Market] = 
		for (
			marketElement <- (typeElement \\ "market").toList;
			marketId <- marketElement.attribute("id");
			marketName <- marketElement.attribute("name");
			marketUrl <- marketElement.attribute("url");
			marketDate <- marketElement.attribute("date"); 
			marketTime <- marketElement.attribute("time"); 
			marketBetTillDate <- marketElement.attribute("betTillDate");
			marketBetTillTime <- marketElement.attribute("betTillTime"); 
			marketLastUpdateDate <- marketElement.attribute("lastUpdateDate");
			marketLastUpdateTime <- marketElement.attribute("lastUpdateTime")
		)
		yield {
			var participants = unmarshallParticipants(marketElement)
				
			new Market(
				marketId.text, 
				marketName.text, 
				marketUrl.text, 
				marketDate.text, 
				marketTime.text, 
				marketBetTillDate.text, 
				marketBetTillTime.text, 
				marketLastUpdateDate.text, 
				marketLastUpdateTime.text, 
				participants )
		};

	private def unmarshallParticipants(marketElement: Node): Seq[Participant] = 
		for (
			participantElement <- (marketElement \\ "participant").toList;
			participantName <- participantElement.attribute("name");
	    	participantId <- participantElement.attribute("id");
	    	participantOdds <- participantElement.attribute("odds");
	    	participantOddsDecimal <- participantElement.attribute("oddsDecimal");
	    	participantLastUpdateDate <- participantElement.attribute("lastUpdateDate");
	    	participantLastUpdateTime <- participantElement.attribute("lastUpdateTime");
	    	participantHandicap <- participantElement.attribute("handicap")
		)
		yield {
			new Participant(
				participantName.text,  
				participantId.text, 
				participantOdds.text, 
				participantOddsDecimal.text, 
				participantLastUpdateDate.text, 
				participantLastUpdateTime.text, 
				participantHandicap.text )
		};

}
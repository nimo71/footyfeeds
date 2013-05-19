package com.footyfeeds.adapters.williamhill

import com.footyfeeds.domain.Bet
import org.slf4j.LoggerFactory

object WilliamHillFeedAdapter {
	
	def logger = LoggerFactory.getLogger(this.getClass)

	def adapt(betting: Betting): List[Bet] = {
		val bets = for(
			betType <- betting.betTypes; 
			market <- betType.markets 
		)
		yield {
			val teams = market.participants.filter {
				_.name != "Draw"	
			}
			val team1Odds = teams(0).oddsDecimal.toDouble
			val team2Odds = teams(1).oddsDecimal.toDouble
			val oddsDiff = (team1Odds - team2Odds).abs

			new Bet(betType.name, market.name, market.date, market.time, oddsDiff)
		}

		bets.filter { bet => 
			bet.marketName.endsWith("Match Betting") &&
				//bet.date == "2013-05-19" &&
				bet.oddsDiff > 5
		}.
		sortBy(_.time)
		// sortBy(_.oddsDiff)

	}
}
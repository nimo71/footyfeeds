package com.footyfeeds.adapters.williamhill

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers._
import dispatch._
import com.ning.http.client.RequestBuilder

class WilliamHillFootballFeedsSpec extends FlatSpec {

	def ukBetting = WilliamHillFootballFeeds.requestUkFootballFeed

	"Requesting UkFootballFeed" should "return Betting" in {
		ukBetting.orElse{ fail() }
	}

	"Requesting UkFootballFeed" should "return Betting containing BetTypes" in {
		val names = 
			for (
				bets <- ukBetting.toList;
				betType <- bets.betTypes
			) 
			yield {
				betType.name 
			}

		names.length should not be ('empty)
	}

	"Requesting UkFootballFeed" should "return Betting containing Markets" in {
		val ids = 
			for (
				bets <- ukBetting.toList; 
				betType <- bets.betTypes; 
				market <- betType.markets
			)
			yield {
				market.id
			}

		ids.length should not be ('empty)
	}

	"Requesting all football feeds" should "return all feeds" in {
		WilliamHillFootballFeeds.requestAllFootballFeeds().foreach { betting => 
			betting.betTypes.foreach { betType => 
				betType.name
			}
		}
	}
}
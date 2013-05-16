package com.plaintext.adapters.williamhill

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers._
import dispatch._
import com.ning.http.client.RequestBuilder

class UkFootballFeedSpec extends FlatSpec {

	def betting = UkFootballFeed.request

	"Requesting UkFootballFeed" should "return Betting" in {
		betting.orElse{ fail() }
	}

	"Requesting UkFootballFeed" should "return Betting containing BetTypes" in {
		val names = 
			for (
				bets <- betting.toList;
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
				bets <- betting.toList; 
				betType <- bets.betTypes; 
				market <- betType.markets
			)
			yield {
				market.id
			}

		ids.length should not be ('empty)
	}
}
package com.footyfeeds.adapters.williamhill

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers._

class WilliamHillFeedAdapterSpec extends FlatSpec {

    "WilliamHillFeedAdapter" should "adapt Betting to a list of Bet" in {
    	val betss = WilliamHillFootballFeeds.requestAllFootballFeeds().map {
        	WilliamHillFeedAdapter.adapt(_)
    	}

    	var i: Int = 0
		betss.foreach { bets => 
			bets.foreach { bet => 
				i += 1
				println("%s: %s".format(i, bet))
			}
		}
    }
}
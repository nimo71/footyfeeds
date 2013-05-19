package com.footyfeeds.adapters.williamhill

import org.slf4j.LoggerFactory
import dispatch._
import com.ning.http.client.RequestBuilder

object WilliamHillFootballFeeds {
	def logger = LoggerFactory.getLogger(this.getClass)

	private val ukFootballUrl = "http://whdn.williamhill.com/pricefeed/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=1&marketSort=MR&filterBIR=N"
	private val otherLeagueFootballUrl = "http://whdn.williamhill.com/pricefeed/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=274&marketSort=MR&filterBIR=N"
	private val uafaClubCompetitionsUrl = "http://whdn.williamhill.com/pricefeed/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=275&marketSort=MR&filterBIR=N"
	private val europeanMajorLeaguesUrl = "http://whdn.williamhill.com/pricefeed/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=46&marketSort=MR&filterBIR=N"

	def requestUkFootballFeed(): Option[Betting] = {
		logger.info("Requesting UK Football Feed")
		request(ukFootballUrl, 3)
	}

	def requestOtherLeagueFootballFeed(): Option[Betting] = {
		logger.info("Other League Football Feed")
		request(otherLeagueFootballUrl, 3)
	}

	def requestUafaClubCompetitionsFeed(): Option[Betting] = {
		logger.info("UAFA Club Competition Football Feed")
		request(uafaClubCompetitionsUrl, 3)
	}

	def requestEuropeanMajorLeaguesFeed(): Option[Betting] = {
		logger.info("European Major Leagues Football Feed")
		request(europeanMajorLeaguesUrl, 3)
	}

	def requestAllFootballFeeds(): List[Betting] = {
		List(requestUkFootballFeed(),
			requestOtherLeagueFootballFeed(),
		 	requestUafaClubCompetitionsFeed(),
		 	requestEuropeanMajorLeaguesFeed()
		 	).flatten
	}		

	private def request(feedUrl: String, retry: Int): Option[Betting] = {
		val feedReq = url(feedUrl).GET

		val response = Http(feedReq)()

		if (response.getStatusCode != 200) {
			if (retry == 0) 
				None
			else {
				Thread.sleep(3000)
				request(feedUrl, retry - 1)
			}
		}
		else 
			try {
				BettingUnmarshaller(response.getResponseBody)
			}
			catch {
			    case e: Throwable => {
			    	logger.error(e.getMessage())
			    	Thread.sleep(3000)
			    	request(feedUrl, retry)
			    }
			}
	}
}

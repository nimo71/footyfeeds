package com.plaintext.adapters.williamhill

import com.plaintext.domain.Betting
import dispatch._
import com.ning.http.client.RequestBuilder

object UkFootballFeed {

	def request: Option[Betting] = {

		val feedReq = url("http://whdn.williamhill.com/pricefeed/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=1&marketSort=MR&filterBIR=N").GET

		val response = Http(feedReq)()
		if (response.getStatusCode != 200) 
			None
		else 
			BettingUnmarshaller(response.getResponseBody())
	}
}
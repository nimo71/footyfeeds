package com.plaintext.adapters.williamhill

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers._
import dispatch._
import com.ning.http.client.RequestBuilder

class UkFootballFeedSpec extends FlatSpec {

	"Requesting UkFootballFeed" should "return Betting" in {
		UkFootballFeed.request.orElse{ fail() }
	}
}
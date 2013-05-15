package com.plaintext.adapters.williamhill

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers._

class BettingUnmarshallerSpec extends FlatSpec {

	val testXml = """
		<oxip version="7.1" created="2013-05-15 19:54:57" lastMsgId="" requestTime="0.4920">
			<response request="getHierarchyByMarketType" code="001" message="success" debug="">
				<williamhill>
					<class id="1" name="UK Football" maxRepDate="2013-05-15" maxRepTime="11:41:55">
						<type id="289" name="William Hill Scottish Cup" url="http://sports.williamhill.com/bet/betting/t/289" lastUpdateDate="2013-05-07" lastUpdateTime="09:20:12">
							<market id="74020128" name="Hibernian v Celtic - 1st Half Betting" url="http://sports.williamhill.com/bet/en-gb/betting/e/4323154/Hibernian%2dv%2dCeltic" date="2013-05-26" time="15:00:00" betTillDate="2013-05-26" betTillTime="15:00:00" lastUpdateDate="2013-05-13" lastUpdateTime="14:43:59">
								<participant name="Draw" id="282176127" odds="11/8" oddsDecimal="2.38" lastUpdateDate="2013-05-13" lastUpdateTime="14:44:00" handicap=""/>
								<participant name="Hibernian" id="282176126" odds="13/2" oddsDecimal="7.50" lastUpdateDate="2013-05-13" lastUpdateTime="14:44:00" handicap=""/>
								<participant name="Celtic" id="282176128" odds="4/5" oddsDecimal="1.80" lastUpdateDate="2013-05-13" lastUpdateTime="14:44:00" handicap=""/>
							</market>
						</type>
						<type id="292" name="English Championship" url="http://sports.williamhill.com/bet/betting/t/292" lastUpdateDate="2013-05-07" lastUpdateTime="09:20:12">
							<market id="81716150" name="Crystal Palace v Watford - Match Betting" url="http://sports.williamhill.com/bet/en-gb/betting/e/4449415/C%2dPalace%2dv%2dWatford" date="2013-05-27" time="15:00:00" betTillDate="2013-05-27" betTillTime="15:00:00" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26">
								<participant name="Draw" id="306565261" odds="9/4" oddsDecimal="3.25" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
								<participant name="Watford" id="306565262" odds="13/10" oddsDecimal="2.30" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
								<participant name="Crystal Palace" id="306565260" odds="21/10" oddsDecimal="3.10" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:42" handicap=""/>
							</market>
							<market id="81717209" name="Crystal Palace v Watford - 1st Half Betting" url="http://sports.williamhill.com/bet/en-gb/betting/e/4449415/C%2dPalace%2dv%2dWatford" date="2013-05-27" time="15:00:00" betTillDate="2013-05-27" betTillTime="15:00:00" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:27">
								<participant name="Draw" id="306568686" odds="19/20" oddsDecimal="1.95" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
								<participant name="Crystal Palace" id="306568685" odds="14/5" oddsDecimal="3.80" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
								<participant name="Watford" id="306568687" odds="15/8" oddsDecimal="2.88" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
							</market>
							<market id="81717213" name="Crystal Palace v Watford - 2nd Half Betting" url="http://sports.williamhill.com/bet/en-gb/betting/e/4449415/C%2dPalace%2dv%2dWatford" date="2013-05-27" time="15:00:00" betTillDate="2013-05-27" betTillTime="15:00:00" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26">
								<participant name="Watford" id="306568762" odds="13/8" oddsDecimal="2.62" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
								<participant name="Crystal Palace" id="306568760" odds="9/4" oddsDecimal="3.25" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
								<participant name="Draw" id="306568761" odds="11/8" oddsDecimal="2.38" lastUpdateDate="2013-05-15" lastUpdateTime="15:41:26" handicap=""/>
							</market>
						</type>
					</class>
				</williamhill>
			</response>
		</oxip>
	"""

    "apply" should "unmarshal xml into BetTypes" in {
        val names = 
	        for (
	        	betting <- BettingUnmarshaller(testXml).toSeq;
	        	betType <- betting.betTypes
	        ) yield {
	        	betType.name
	        } 

	    names should have length(2)
	    names should equal(List("William Hill Scottish Cup", "English Championship"))
    }
}
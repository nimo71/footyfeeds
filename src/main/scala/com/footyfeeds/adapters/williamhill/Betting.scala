package com.footyfeeds.adapters.williamhill

class Betting(val betTypes: List[BetType]) {

	override def toString = "[Betting: betTypes=%s]".format(betTypes)

	override def equals(other: Any) = {
		other match {
			case otherBetting: Betting => otherBetting.betTypes.equals(this.betTypes)
			case _ => false
		}
	}

	override def hashCode = {
		41 * betTypes.hashCode
	}
}
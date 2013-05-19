package com.footyfeeds.adapters.williamhill

class BetType(val name: String, val markets: Seq[Market]) {

	override def toString = "[BetType: name=%s]".format(name)

	override def equals(other: Any) = {
		other match {
			case otherBetType: BetType => otherBetType.name == this.name
			case _ => false
		}
	}

	override def hashCode = {
		41 * name.hashCode
	}
}
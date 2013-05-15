package com.plaintext.domain

class BetType(val name: String) {

	override def toString = "[BetType: ]"

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
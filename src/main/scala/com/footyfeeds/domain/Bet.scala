package com.footyfeeds.domain

class Bet(
	val betTypeName: String, 
	val marketName: String, 
	val date: String, 
	val time: String, 
	val oddsDiff: Double ) 
{

	override def toString = "[Bet: betTypeName=%s, marketName=%s, date=%s, time=%s, oddsDiff=%s]".
							format(betTypeName, marketName, date, time, oddsDiff)

	override def equals(other: Any) = {
		other match {
			case otherBet: Bet => true
			case _ => false
		}
	}

	override def hashCode = {
		41 * 
			(41 * 
				(41 * 
					(41 * 
						(41 + betTypeName.hashCode) 
						+ marketName.hashCode) 
					+ date.hashCode) 
				+ time.hashCode) 
			+ oddsDiff.hashCode
	}
}
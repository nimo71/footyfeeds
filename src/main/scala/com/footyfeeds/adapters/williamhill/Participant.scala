package com.footyfeeds.adapters.williamhill

class Participant(
	val name : String, 
	val id : String, 
	val odds : String, 
	val oddsDecimal : String, 
	val lastUpdateDate : String, 
	val lastUpdateTime : String, 
	val handicap : String ) 
{

	override def toString = "[Participant: name=%s, id=%s, odds=%s, oddsDecimal=%s, lastUpdateDate=%s, lastUpdateTime=%s, handicap=%s]".
		format(name, id, odds, oddsDecimal, lastUpdateDate, lastUpdateTime, handicap)
 
	override def equals(other: Any) = {
		other match {
			case otherParticipant: Participant => 
				otherParticipant.name == this.name &&
				otherParticipant.id == this.id &&
				otherParticipant.odds == this.odds &&
				otherParticipant.oddsDecimal == this.oddsDecimal &&
				otherParticipant.lastUpdateDate == this.lastUpdateDate &&
				otherParticipant.lastUpdateTime == this.lastUpdateTime && 
				otherParticipant.handicap == this.handicap
				
			case _ => false
		}
	}

	override def hashCode = {	
		41 * (
			41 * (
				41 * (
					41 * (
						41 * (
							41 * (
								41 + id.hashCode
								) + name.hashCode
							) + odds .hashCode
						) + oddsDecimal.hashCode
					) + lastUpdateDate.hashCode
				) + lastUpdateTime.hashCode
			) + handicap.hashCode
	}
}
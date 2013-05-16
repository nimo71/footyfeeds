package com.plaintext.domain

class Market(
	val id : String, 
	val name : String, 
	val url : String, 
	val date : String, 
	val time : String, 
	val betTillDate : String, 
	val betTillTime : String, 
	val lastUpdateDate : String, 
	val lastUpdateTime : String )
{
	override def toString = 
		"[Market: id=%s, name=%s, url=%s, date=%s, time=%s, betTillDate=%s, betTillTime=%s, lastUpdateDate=%s, lastUpdateTime=%s]".
			format(id, name, url, date, time, betTillDate, betTillTime, lastUpdateDate, lastUpdateTime)

	override def equals(other: Any) = {
		other match {
			case otherMarket: Market =>  
				otherMarket.id == this.id &&
				otherMarket.name == this.name &&
				otherMarket.url == this.url &&
				otherMarket.date == this.date &&
				otherMarket.time == this.time &&
				otherMarket.betTillDate == this.betTillDate &&
				otherMarket.betTillTime == this.betTillTime &&
				otherMarket.lastUpdateDate == this.lastUpdateDate &&
				otherMarket.lastUpdateTime == this.lastUpdateTime

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
				 				41 * (
				 					41 * (
				 						41 + id.hashCode
				 						) + name.hashCode
				 					) + url.hashCode
				 				) + date.hashCode
				 			) + time.hashCode
				 		) + betTillDate.hashCode
				 	) + betTillTime.hashCode
				) + lastUpdateDate.hashCode
			) + lastUpdateTime.hashCode
	}
}

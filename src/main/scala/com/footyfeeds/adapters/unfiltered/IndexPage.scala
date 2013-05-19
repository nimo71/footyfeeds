package com.footyfeeds.adapters.unfiltered

import unfiltered.response._
import javax.servlet.http.HttpServletResponse
import scala.xml._
import com.footyfeeds.adapters.williamhill._
import com.footyfeeds.domain.Bet
		
object IndexPage 
{
	def apply(): Node = {
		val title = "Games"
		htmlTemplate(title=title, heading=title, betTable())
	}
	
	def betTable(): Node = {
		val betss = WilliamHillFootballFeeds.requestAllFootballFeeds().map {
        				WilliamHillFeedAdapter.adapt(_)
    		}

    	val bets = 
	    	for (
	    		bets <- betss;
	    		bet <- bets)
	    	yield {
	 			bet
	    	}
   
   		<div>
	   		<h2>Games Where Odds Difference is Greater Than 5</h2>
			<table cellpadding="5">
				<tr>
					<th>Competition</th>
					<th>Heading</th>
					<th>Date</th>
					<th>Time</th>
					<th>Odds Diff</th>
				</tr>
				{ bets.map { betRow(_) } }
			</table>
		</div>
	}

	def betRow(bet: Bet): Node = {
		val headingLength = bet.marketName.length - " - Match Betting".length
		val matchHeading = bet.marketName.substring(0, headingLength)

		<tr>
			<td>{bet.betTypeName}</td>
			<td>{matchHeading}</td>
			<td>{bet.date}</td>
			<td>{bet.time}</td>
			<td>{bet.oddsDiff}</td>
		</tr>
	}

	def htmlTemplate(title: String, heading: String, content: NodeSeq): Node = {
		<html>
			<head>
				<title>{title}</title>
			</head>
			<body>
				<h1>{heading}</h1>
				{content}
			</body>
		</html>
	}
}
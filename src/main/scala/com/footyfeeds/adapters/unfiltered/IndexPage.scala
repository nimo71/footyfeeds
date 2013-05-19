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
	    		bet <- bets )
	    	yield {
	 			bet
	    	}

	    val sortedBets = bets.sortBy(-_.oddsDiff).sortBy(_.date)
   
   		<table cellpadding="5">
			<tr>
				<th>Competition</th>
				<th>Heading</th>
				<th>Date</th>
				<th>Time</th>
				<th>Odds Diff</th>
			</tr>
			{ sortedBets.map { betRow(_) } }
		</table>
	}

	def betRow(bet: Bet): Node = {
		val headingLength = bet.marketName.length - " - Match Betting".length
		val matchHeading = bet.marketName.substring(0, headingLength)

		<tr>
			<td>{bet.betTypeName}</td>
			<td>{matchHeading}</td>
			<td>{bet.date}</td>
			<td>{bet.time}</td>
			<td>{"%.3f".format(bet.oddsDiff)}</td>
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
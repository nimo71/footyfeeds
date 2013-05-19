package com.footyfeeds.adapters.json

import com.footyfeeds.domain.User
import JSON._

object UserJson {

	def serialize(user: User): Option[String] = {
		Some(makeJSON(Map("email" -> user.email.value, "password" -> user.password.value)))
	}

}
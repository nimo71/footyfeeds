package com.footyfeeds.adapters.json

import com.footyfeeds.domain._
import com.footyfeeds.domain.forms._

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers._

class RegistrationFormJsonSpec extends FlatSpec {

	import JSON._

    "RegistrationFormJson" should "serialize registration form object" in {
        val form = new Form(
        	new FormField("email", new Email("test@test.com")), 
        	new FormField("confirmEmail", "test@test.com"), 
        	new FormField("password", new Password("password")), 
        	new FormField("confirmPassword", "password"))

        val expectedJson: String = makeJSON(
        	Map("email" -> "test@test.com", 
        		"confirmEmail" -> "test@test.com", 
        		"password" -> "password", 
        		"confirmPassword" -> "password") )

        RegistrationFormJson.serialize(form) should equal(Some(expectedJson))
    }

    "RegistrationFormJson" should "serialize registration form object with errors" in {
        val form = new Form(
        	new FormField("email", new Email("test@test.com")), 
        	new FormField("confirmEmail", new ErrorValue("test@test.co", "Email must match confirmation")), 
        	new FormField("password", new ErrorValue("p", "Password should be at least 6 characters")), 
        	new FormField("confirmPassword", "password"))

        val expectedJson: String = makeJSON(
        	Map("email" -> "test@test.com", 
        		"confirmEmail" -> Map("value" -> "test@test.co", "message" -> "Email must match confirmation"), 
        		"password" -> Map("value" -> "p", "message" -> "Password should be at least 6 characters"), 
        		"confirmPassword" -> "password") )

        RegistrationFormJson.serialize(form) should equal(Some(expectedJson))
    }

    "RegistrationFormJson" should "deserialize json into a registration Form" in {
    	val registrationJson = """
    		{
    			"email" : "test@test.com", 
    			"confirmEmail" : "test@test.com", 
    			"password" : "testpassword", 
    			"confirmPassword" : "testpassword"
    		}
    	"""

    	val expectedForm = new Form(
    		new FormField("email", new Email("test@test.com")), 
    		new FormField("confirmEmail", "test@test.com"), 
    		new FormField("password", new Password("testpassword")), 
		new FormField("confirmPassword", "testpassword"))

    	RegistrationFormJson.deserialize(registrationJson) should equal(Some(expectedForm))
    }

    "RegistrationFormJson" should "return None when deserializing bad json" in {
    	val registrationJson = ""
    	RegistrationFormJson.deserialize(registrationJson) should equal(None)
    }
}
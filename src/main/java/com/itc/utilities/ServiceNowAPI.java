package com.itc.utilities;


import org.json.JSONObject;

import com.itc.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ServiceNowAPI extends BaseTest{

	/**
	 * @name createIncidentInServiceNowTicket
	 * @description Creates an incident ticket in ServiceNow using the description provided
	 * @description we have to always add in a try catch block
	 * @param shortDescription
	 * @param description
	 * @author Rengesh
	 * @return ticketNumber
	 */
	public String createIncidentInServiceNowTicket(String shortDescription, String description) throws Exception {
		String serviceNowURL = ConfigReader.get("servicenow.url");
		String sericeNowUserName = ConfigReader.get("servicenow.username");
		String encodedPassword = ConfigReader.get("servicenow.password");
		String serviceNowPassword = BaseTest.getDecodePassword(encodedPassword);

		JSONObject requestJson = new JSONObject();
		requestJson.put("short_description", shortDescription);
		requestJson.put("description", description);
		try {
			Response response = RestAssured.given().auth().preemptive().basic(sericeNowUserName, serviceNowPassword)
					.header("Content-Type", "application/json").body(requestJson.toString()).when().post(serviceNowURL)
					.then().extract().response();
			int statusCode = response.getStatusCode();
			if (statusCode == 201 || statusCode == 200) {
				JSONObject obj = new JSONObject(response.getBody().asPrettyString());
				if (obj.has("result") && obj.getJSONObject("result").has("number")) {
					System.out.println("Created SNOW Ticket with Incident Number: "
							+ obj.getJSONObject("result").getString("number"));
					return obj.getJSONObject("result").getString("number");
				} else
					System.out.println("Failed to Create Incident. Status: " + response.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
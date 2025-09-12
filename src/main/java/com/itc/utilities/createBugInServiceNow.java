package com.itc.utilities;

import org.testng.Reporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class createBugInServiceNow {
	
	 public static void create(String short_description, String description) {
		 

			String baseUrl = "https://itcinfotechindialtddemo5.service-now.com/api/now/table/incident";

			String username = "selenium.user"; 

			String password = "W!nd0f(hange@2025"; 
			String requestBody = "{\n" +
				    "  \"short_description\": \"" + short_description + "\",\n" +
				    "  \"description\": \"" + description + "\"\n" +
				"}";
			
			 Response response = RestAssured.given()

			            .auth()

			            .preemptive()

			            .basic(username, password)

			            .header("Content-Type", "application/json")

			            .body(requestBody)

			            .when()

			            .post(baseUrl)

			            .then()

			            .extract()

			            .response();
			 		System.out.println("Status Code: " + response.getStatusCode());
//
//			        System.out.println("Response Body:\n" + response.getBody().asPrettyString());
		 
			        JSONObject obj = new JSONObject(response.getBody().asPrettyString());
			        String numberValue = obj.getJSONObject("result").getString("number");
//			        JSONObject obj = new JSONObject(response.body());
//			        String numberValue = obj.getJSONObject("result").getString("number");	        
			        System.out.println("Created SNOW Ticket with Incident Number: " +numberValue );
			        Reporter.log("SNOW Incedent Number: "+ numberValue);

		 
	 }
}
//	        try {
//	            String instance = "https://your_instance.service-now.com";
//	            String endpoint = "/api/now/table/incident"; // use /problem or custom table if needed
//	            String username = "your_username";
//	            String password = "your_password";
//
//	            URL url = new URL(instance + endpoint);
//	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	            conn.setRequestMethod("POST");
//	            conn.setRequestProperty("Content-Type", "application/json");
//	            String auth = username + ":" + password;
//	            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
//	            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
//	            conn.setDoOutput(true);
//
//	            // JSON payload
//	            String jsonPayload = "{"
//	                    + "\"short_description\":\"" + shortDescription + "\","
//	                    + "\"description\":\"" + description + "\","
//	                    + "\"urgency\":\"2\","
//	                    + "\"impact\":\"2\""
//	                    + "}";
//
//	            OutputStream os = conn.getOutputStream();
//	            os.write(jsonPayload.getBytes());
//	            os.flush();
//
//	            int responseCode = conn.getResponseCode();
//	            if (responseCode == 201 || responseCode == 200) {
//	                System.out.println("Bug successfully created in ServiceNow.");
//	            } else {
//	                System.out.println("Failed to create bug. HTTP error code: " + responseCode);
//	            }
//
//	            conn.disconnect();
//	        } catch (Exception ex) {
//	            ex.printStackTrace();
//	        }
//	 }
//}


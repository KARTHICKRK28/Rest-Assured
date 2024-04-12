package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
public class SoapXMLrequest {

	@Test
	public void validatesoapXML() throws IOException {
		
		File file= new File("./SoapRequestFile/Add.xml");
		if(file.exists()) {
			System.out.println(">> >> File exists");
		}
		FileInputStream inputStream= new FileInputStream(file);
		
		String requestbody=IOUtils.toString(inputStream,"UTF-8");
		
		baseURI="http://www.dneonline.com";
		given()
		              .contentType("text/xml")
		              .accept(ContentType.XML)
		              .body(requestbody)
		              
		           .when()
		             .post("/calculator.asmx")
		             
		           .then()
		            .statusCode(200)
		            .log().all().and()
		            //validating XML Response by using Xpath
		            .body("//*:AddResult.text()", equalTo("88"));
		 
	}
}

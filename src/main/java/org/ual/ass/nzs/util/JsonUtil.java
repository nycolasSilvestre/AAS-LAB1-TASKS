package org.ual.ass.nzs.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	public JsonUtil() {
		super();
	}
	public JSONObject requestJsonParser(HttpServletRequest req) throws JSONException, IOException {
		StringBuilder strBuilder= new StringBuilder();
	    BufferedReader reader = req.getReader();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	strBuilder.append(line).append('\n');
	        }
	    } finally {
	        reader.close();
	    }
		return new JSONObject(strBuilder.toString());
	}
	
	public JSONObject errorMessageHandler(String message) throws JSONException {
		String errorMessage = "{'Erro':'"+message+"'}";
		return new JSONObject(errorMessage);
	}
}

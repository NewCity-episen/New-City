package shared.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

public class Response {

	public String requestId;
	public String responseBody;
	
	public Response() {
		
	}
	public Response(String requestId,String responseBody) {
		this.requestId=requestId;
		this.responseBody=responseBody;
	}
	@JsonProperty("request_id")
	public String getRequestId() {
		return requestId;
	}
	@JsonProperty("request_id")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	@JsonSetter("response_body")
	public void setResponseBody(JsonNode responseBody) {this.responseBody=responseBody.toString();}
	public void setResponseContent(final String responseBody) {this.responseBody=responseBody;}
	@JsonRawValue
	public String getResponseBody() {
		return responseBody;
	}
	
}

package shared.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

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
	@JsonProperty("response_body")
	@JsonRawValue
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
}

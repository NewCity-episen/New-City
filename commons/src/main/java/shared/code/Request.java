package shared.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

@JsonRootName(value = "request")
public class Request {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String requestId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String requestOrder;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String requestTable;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String requestBody;
	
	@JsonProperty("request_order")
	public void setRequestOrder(String requestOrder) {this.requestOrder=requestOrder;}
	@JsonProperty("request_id")
	public void setRequestId(String requestId) {this.requestId=requestId;}
	@JsonProperty("request_table")
	public void setRequestTable(String requestTable) {this.requestTable=requestTable;}
	@JsonSetter("request_body")
	public void setRequestBody(JsonNode requestBody) {this.requestBody=requestBody.toString();}
	public void setRequestContent(final String requestBody) {this.requestBody=requestBody;}
	public String getRequestTable() {return requestTable;}
	public String getRequestId() {return requestId;}
	public String getRequestOrder() {return requestOrder;}
	@JsonRawValue
	public String getRequestBody() {return requestBody;}
	
}
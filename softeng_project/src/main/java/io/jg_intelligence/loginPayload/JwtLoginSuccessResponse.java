package io.jg_intelligence.loginPayload;

public class JwtLoginSuccessResponse {

	private Boolean success;
	private String token;
	
	public JwtLoginSuccessResponse(Boolean success, String token) {
		super();
		this.success = success;
		this.token = token;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}

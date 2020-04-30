package io.jg_intelligence.loginPayload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
@NotBlank(message = "Please enter Username")
  private String username;
@NotBlank(message = "Please enter Password")
  private String password;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
  
  
}

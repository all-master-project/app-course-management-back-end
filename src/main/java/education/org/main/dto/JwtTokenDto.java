package education.org.main.dto;

import java.util.Map;

public class JwtTokenDto {

    private final Map<String, String> jwt;
    private Object errors;
    private Boolean success;
    
    
	public JwtTokenDto(Map<String, String> jwt, Object errors, Boolean success) {
		super();
		this.jwt = jwt;
		this.errors = errors;
		this.success = success;
	}

	public Object getErrors() {
		return errors;
	}
	
	public void setErrors(Object errors) {
		this.errors = errors;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Map<String, String> getJwt() {
		return jwt;
	}

   
}

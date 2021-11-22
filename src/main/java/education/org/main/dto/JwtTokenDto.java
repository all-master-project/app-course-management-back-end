package education.org.main.dto;

public class JwtTokenDto {

    private final String jwt;
    private Object errors;
    private Boolean success;

    public JwtTokenDto(String jwt, Object errors, Boolean success) {
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

    public String getJwt() {
        return jwt;
    }
}

package education.org.main.security.config;

public class LoginViewModel {
    private String username;
    private String password;
    private String captchaToken;

    public String getCaptchaToken() {
        return captchaToken;
    }
    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }
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

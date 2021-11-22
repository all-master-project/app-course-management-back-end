package education.org.main.dto;

public class DisplayErrorDto {

    private String code;
    private String description;

    public DisplayErrorDto(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public DisplayErrorDto() {
        super();
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}

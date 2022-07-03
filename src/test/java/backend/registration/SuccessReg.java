package backend.registration;

public class SuccessReg {
    private Integer id;
    private String token;

    public SuccessReg(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}

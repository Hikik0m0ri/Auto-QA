package backend.registration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UnSuccessReg {
    private String error;

    public String getError() {
        return error;
    }
}

package uz.tsx.SMS.model;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class EskizToken {

    @Getter
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEyODg2LCJyb2xlIjoidXNlciIsImlhdCI6MTcxMDg0ODA1MiwiZXhwIjoxNzEwODUxNjUyfQ.VIQo1QHw-vkBc4zTKpFh8cm9YBO-hUTP1NC9hbLubRA";

    public void setToken(String token) {
        if (Objects.nonNull(token)) {
            EskizToken.token = token;
        }
    }

}

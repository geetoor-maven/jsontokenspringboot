package id.co.geetoor.demoauth.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String name;

    private String gender;

    private String email;

    private String password;

    private String phone;
}

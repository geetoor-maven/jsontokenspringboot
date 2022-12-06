package id.co.geetoor.demoauth.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String gender;

    private String email;

    private String password;

    private String phone;

    private String userType = "USER";



}

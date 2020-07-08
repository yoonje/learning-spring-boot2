package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data // getter, setter, equal ....
@AllArgsConstructor // constructor
//@JsonIgnoreProperties(value = {"password", "ssn"})
//@JsonFilter("UserInfo") // filter를 위한 어노테이션으로 AdminUserController에서 사용
public class User {
    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
    private String name;
    @Past
    private Date joinDate;
    //@JsonIgnore
    private String password;
    //@JsonIgnore
    private String ssn;
}

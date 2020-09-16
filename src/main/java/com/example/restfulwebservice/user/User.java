package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

//@Data // getter, setter, equal ....
//@AllArgsConstructor // constructor
//@NoArgsConstructor
////@JsonIgnoreProperties(value = {"password", "ssn"})
////@JsonFilter("UserInfo") // filter를 위한 어노테이션으로 AdminUserController에서 사용
//public class User {
//    private Integer id;
//    @Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
//    private String name;
//    @Past
//    private Date joinDate;
//    //@JsonIgnore
//    private String password;
//    //@JsonIgnore
//    private String ssn;
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All details about the users.")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "")
    @ApiModelProperty(notes = "사용자의 이름을 입력해 주세요.")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력해 주세요.")
    private Date joinDate;

    @ApiModelProperty(notes = "사용자의 패스워드를 입력해 주세요.")
    private String password;

    @ApiModelProperty(notes = "사용자의 주민번호를 입력해 주세요.")
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}

package dz_15.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private int login_ID;
    private String city;
    private String email;
    private String description;
    private Role role;
}

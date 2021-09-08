package main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.domain.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String name;

    private String password;

    private boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role type;

    public Role getRoles() {
        if (type.toString().equals("ADMIN")) {
            return Role.ADMIN;
        }
        return Role.USER;
    }


}

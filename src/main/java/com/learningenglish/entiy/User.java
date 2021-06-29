package com.learningenglish.entiy;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "lastest_login_date")
    private Date lastestLoginDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = true;
        this.createDate = new Date();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(status, user.status) && Objects.equals(createDate, user.createDate) && Objects.equals(lastestLoginDate, user.lastestLoginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, status, createDate, lastestLoginDate);
    }
}

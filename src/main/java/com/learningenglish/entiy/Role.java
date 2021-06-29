package com.learningenglish.entiy;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName name;

}

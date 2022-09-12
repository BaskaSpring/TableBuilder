package ru.mfn.TableBuilder.model.auth;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75)
    private String name;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole eRole;

    @JsonIgnore
    private Boolean enabled;

}

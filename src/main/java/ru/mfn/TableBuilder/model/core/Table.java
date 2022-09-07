package ru.mfn.TableBuilder.model.core;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.mfn.TableBuilder.model.auth.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@javax.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name =  "id", columnDefinition = "VARCHAR(50)",updatable = false,nullable = false)
    private String id;

    private Boolean enabled;

    private Boolean systemTable;

    @Column(length = 75)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "table_roles",
            joinColumns = @JoinColumn(name = "table_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}

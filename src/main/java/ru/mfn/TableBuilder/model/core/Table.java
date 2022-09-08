package ru.mfn.TableBuilder.model.core;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.mfn.TableBuilder.model.annotation.ValidName;
import ru.mfn.TableBuilder.model.auth.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean enabled;

    private Boolean systemTable;

    @ValidName(message = "Invalid table name")
    @NotBlank(message = "table name cant be empty")
    private String tableName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "table_roles",
            joinColumns = @JoinColumn(name = "table_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}

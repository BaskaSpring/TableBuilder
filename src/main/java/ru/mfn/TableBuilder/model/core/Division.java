package ru.mfn.TableBuilder.model.core;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "divisions")
public class Division {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Division parent;

    private String name;

    private Boolean enabled;


}

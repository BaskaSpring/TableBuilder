package ru.mfn.TableBuilder.payload.role.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyRoleRequest {


    private Long id;

    @NotBlank
    @Size(min = 3, max = 75)
    private String name;

    @NotBlank
    @Size(min = 3, max = 75)
    private String newName;

}

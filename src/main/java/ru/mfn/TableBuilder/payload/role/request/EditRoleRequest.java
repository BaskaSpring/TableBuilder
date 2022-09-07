package ru.mfn.TableBuilder.payload.role.request;


import lombok.*;
import ru.mfn.TableBuilder.model.annotation.ValidNumberId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditRoleRequest {

    @NotNull
    @ValidNumberId
    private Long id;

    @NotBlank
    @Size(min = 3, max = 75)
    private String name;

}

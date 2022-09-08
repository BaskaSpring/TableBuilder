package ru.mfn.TableBuilder.payload.role.request;

import lombok.*;
import ru.mfn.TableBuilder.model.annotation.ValidName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddRoleRequest {

    @NotBlank
    @Size(min = 3, max = 75)
    @ValidName
    private String name;
}

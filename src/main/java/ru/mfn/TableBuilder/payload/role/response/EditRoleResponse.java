package ru.mfn.TableBuilder.payload.role.response;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditRoleResponse {
    private Long id;
    private String name;
}

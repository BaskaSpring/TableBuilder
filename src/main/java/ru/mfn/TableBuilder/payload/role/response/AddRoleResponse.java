package ru.mfn.TableBuilder.payload.role.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddRoleResponse {
    private Long id;
    private String name;
}

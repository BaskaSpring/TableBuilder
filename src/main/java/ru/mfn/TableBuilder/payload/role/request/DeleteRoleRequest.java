package ru.mfn.TableBuilder.payload.role.request;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteRoleRequest {

    private Long id;
}

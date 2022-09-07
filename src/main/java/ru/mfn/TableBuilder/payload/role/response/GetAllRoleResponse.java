package ru.mfn.TableBuilder.payload.role.response;

import lombok.*;
import ru.mfn.TableBuilder.model.auth.Role;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllRoleResponse {
    List<Role> roleList;
}

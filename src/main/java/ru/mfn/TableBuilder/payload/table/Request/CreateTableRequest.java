package ru.mfn.TableBuilder.payload.table.Request;

import lombok.*;
import ru.mfn.TableBuilder.model.annotation.ValidName;

import javax.swing.text.StyledEditorKit;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTableRequest {

    @Size(min = 3, max = 75)
    @ValidName(message = "Invalid table name")
    @NotBlank(message = "table name cant be empty")
    private String name;

    @Valid
    @Size(min = 1)
    private List<ColumnInfo> columnInfos;

    private Set<String> roles;

    @Valid
    @NotNull(message = "division cant be null")
    private Boolean division;

}

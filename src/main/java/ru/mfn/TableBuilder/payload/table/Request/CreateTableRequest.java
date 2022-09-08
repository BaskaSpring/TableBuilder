package ru.mfn.TableBuilder.payload.table.Request;

import lombok.*;
import ru.mfn.TableBuilder.model.annotation.ValidName;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTableRequest {


    @NotBlank
    @Size(min = 3, max = 75)
    @ValidName
    private String name;

    @Valid
    @Size(min = 1)
    private List<ColumnInfo> columnInfos;
}

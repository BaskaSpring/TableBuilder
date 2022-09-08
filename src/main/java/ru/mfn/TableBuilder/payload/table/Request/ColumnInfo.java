package ru.mfn.TableBuilder.payload.table.Request;

import lombok.*;
import ru.mfn.TableBuilder.model.annotation.ValidName;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColumnInfo {

    @ValidName(message = "Invalid column name")
    @NotBlank(message = "column should have a title")
    private String title;

    @Valid
    private ColumnType type;

    private Integer length;
}

package ru.mfn.TableBuilder.payload.table.Request;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteTableRequest {

    Long id;
}

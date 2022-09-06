package ru.mfn.TableBuilder.controller.handler;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    String status;
    String message;

}

package ru.mfn.TableBuilder.payload.auth.response;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageResponse {
    private String message;
}

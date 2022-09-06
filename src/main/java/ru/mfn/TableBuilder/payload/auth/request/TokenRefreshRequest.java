package ru.mfn.TableBuilder.payload.auth.request;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}

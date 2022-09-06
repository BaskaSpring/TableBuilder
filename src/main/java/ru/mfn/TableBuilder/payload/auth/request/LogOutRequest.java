package ru.mfn.TableBuilder.payload.auth.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogOutRequest {
    String userId;
}

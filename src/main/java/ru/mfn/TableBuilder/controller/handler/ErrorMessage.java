package ru.mfn.TableBuilder.controller.handler;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorMessage {
    String message;
    String stackTrace;
}

package ru.mfn.TableBuilder.exception;

public class CustomException extends Exception{

    private static final long serialVersionUID = -291211739734090347L;

    public CustomException(String message) {
        super(message,null,false,false);
    }
}

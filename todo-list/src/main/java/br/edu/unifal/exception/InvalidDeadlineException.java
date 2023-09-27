package br.edu.unifal.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidDeadlineException extends RuntimeException{

    public InvalidDeadlineException(String message){
        super(message);
    }


}

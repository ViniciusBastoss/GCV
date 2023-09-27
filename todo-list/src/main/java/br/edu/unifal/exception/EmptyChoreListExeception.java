package br.edu.unifal.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmptyChoreListExeception extends RuntimeException{
    public EmptyChoreListExeception(String message){
        super(message);
    }
}

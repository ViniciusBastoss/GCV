package br.edu.unifal.exception;

import br.edu.unifal.domain.Chore;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChoreNotFoundException extends RuntimeException{

    public ChoreNotFoundException(String message){
        super(message);
    }
}

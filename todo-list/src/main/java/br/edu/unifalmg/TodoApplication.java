package br.edu.unifalmg;

import br.edu.unifalmg.repository.ChoreRepository;
import br.edu.unifalmg.repository.impl.FileChoreRepository;
import br.edu.unifalmg.service.ChoreService;

import java.io.IOException;

public class TodoApplication {

    public static void main(String[] args){
        ChoreService service = new ChoreService(new FileChoreRepository());
        service.loadChores();
        service.printChores();
    }
}

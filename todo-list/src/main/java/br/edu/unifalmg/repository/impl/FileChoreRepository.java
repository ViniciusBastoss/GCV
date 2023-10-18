package br.edu.unifalmg.repository.impl;

import br.edu.unifalmg.domain.Chore;
import br.edu.unifalmg.repository.ChoreRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileChoreRepository implements ChoreRepository {
    private ObjectMapper mapper;

    public FileChoreRepository() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
    }

    @Override
    public List<Chore> load() {
        try{
            return mapper.readValue(new File("src/chores.json"), new TypeReference<List<Chore>>() {});
        } catch (IOException exception){
            System.out.println("ERRO: Unable to open file.");
            return new ArrayList<>();
        }



    }
}

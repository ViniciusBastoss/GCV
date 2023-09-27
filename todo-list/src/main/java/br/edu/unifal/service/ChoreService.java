package br.edu.unifal.service;

import br.edu.unifal.domain.Chore;
import br.edu.unifal.exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ChoreService {
    private List<Chore> chores;
    public ChoreService(){
        chores = new ArrayList<>();
    }
    public Chore addChore(String description, LocalDate deadline){
        if(description == null || description.isEmpty()){
            throw new InvalidDescriptionException("The description canoot be null or empty");
        }
        if(deadline == null || deadline.isBefore(LocalDate.now())){
            throw new InvalidDeadlineException("The deadline cannot be null or before the current date");
        }

        for(Chore chore : chores){
            if(chore.getDescription().equals(description)
                    && chore.getDeadline().isEqual(deadline)){
                throw new DuplicatedChoreException(("The given chore already exists."));

            }

        }
        Chore chore = new Chore();
        chore.setDescription(description);
        chore.setDeadline(deadline);
        chore.setCompleted(Boolean.FALSE);

        chores.add(chore);
        return chore;
    }

    public List<Chore> getChores(){
        return this.chores;
    }

    /**
     *
     * @param description The description of the chore
     * @param deadline The deadline of the chore
     */


    public void deleteChore(String description, LocalDate deadline){
        /*isChoreListEmpty.test(this.chores)*/
        if (this.chores.isEmpty()){
            throw new EmptyChoreListExeception("Unable to remove a chore from an empty list");
        }
        boolean isChoreExist = this.chores.stream().anyMatch(chore -> chore.getDescription().equals(description)
                && chore.getDeadline().isEqual(deadline));
        if(!isChoreExist){
            throw new ChoreNotFoundException("The given chore does not exist.");
        }

        this.chores = this.chores.stream().filter(chore -> !chore.getDescription().equals(description)
                && !chore.getDeadline().isEqual(deadline)).collect(Collectors.toList());
    }

    private final Predicate<List<Chore>> isChoreListEmpty = choreList -> choreList.isEmpty();
}

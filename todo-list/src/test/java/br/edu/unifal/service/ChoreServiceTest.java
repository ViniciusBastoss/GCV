package br.edu.unifal.service;

import br.edu.unifal.domain.Chore;
import br.edu.unifal.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ChoreServiceTest {

    @Test
    void addChoreWhenTheDescriptionIsInvalidThrowAnException(){
        ChoreService service = new ChoreService();
        assertAll(
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, null)),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", null)),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().plusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().plusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().minusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().minusDays(1)))
        );
    }

    @Test
    @DisplayName("#addChore > When the deadline is invalid > Throw an exception")
    void addChoreWhenTheDeadlineIsInvalidThrowAnException(){
        ChoreService service = new ChoreService();
        assertAll(
                () -> assertThrows(InvalidDeadlineException.class,
                        () -> service.addChore("Description", null)),
                () -> assertThrows(InvalidDeadlineException.class,
                        () -> service.addChore("Description", LocalDate.now().minusDays(1)))
        );
    }

    @Test
    @DisplayName("#addChore > When adding a chore > When the chore already exists > Throw an Exception ")
    void addChoreWhenAddingaChoreWhenTheChoreAlreadyExistsThrowAnException(){
        ChoreService service = new ChoreService();
        service.addChore("Description", LocalDate.now());
        assertThrows(DuplicatedChoreException.class,
                () -> service.addChore("Description", LocalDate.now()));
    }

    @Test
    void addChoreWhenAddingaOneChore(){
        ChoreService service = new ChoreService();
        LocalDate date =  LocalDate.now();
        Chore chore = service.addChore("Chore one", date);
        assertAll(
                () -> assertEquals("Chore one", chore.getDescription()),
                () -> assertEquals(date, chore.getDeadline())
        );
    }

    @Test
    void addChoreWhenAddingManyChore(){
        ChoreService service = new ChoreService();
        service.addChore("Chore 1", LocalDate.now());
        service.addChore("Chore 2", LocalDate.now().plusDays(1));
        service.addChore("Chore 3", LocalDate.now().plusDays(2));
        assertAll(
                () -> assertEquals(3, service.getChores().size()),
                () -> assertEquals("Chore 1", service.getChores().get(0).getDescription()),
                () -> assertEquals(LocalDate.now(), service.getChores().get(0).getDeadline()),
                () -> assertEquals("Chore 2", service.getChores().get(1).getDescription()),
                () -> assertEquals(LocalDate.now().plusDays(1), service.getChores().get(1).getDeadline()),
                () -> assertEquals("Chore 3", service.getChores().get(2).getDescription()),
                () -> assertEquals(LocalDate.now().plusDays(2), service.getChores().get(2).getDeadline())
        );
    }

    @Test
    @DisplayName("#deleteChore > When the list is empty > Thow an exception")
    void deleteChoreWhenTheListIsEmptyThowAnException(){
        ChoreService service = new ChoreService();
        assertThrows(EmptyChoreListExeception.class, () -> {
            service.deleteChore("description", LocalDate.now());
        });
    }

    @Test
    void deleteChoreWhenTheListIsNotEmptyWhenTheChoreDoesNoExistThrowAnException(){
        ChoreService service = new ChoreService();
        service.addChore("Description", LocalDate.now());
        assertThrows(ChoreNotFoundException.class,() -> {
            service.deleteChore("Chore to be deleted", LocalDate.now().plusDays(1));
        });

    }

    @Test
    @DisplayName("#deleteChore > When the list not empty > When the chore exists delete the chore")
    void deleteChoreWhenTheListNotEmptyWhenTheChoreExistsDeleteTheChore(){
        ChoreService service = new ChoreService();

        service.addChore("Chore #01", LocalDate.now().plusDays(1));
        assertEquals(1, service.getChores().size());

        service.deleteChore("Chore #01", LocalDate.now().plusDays(1));
        assertEquals(0, service.getChores().size());

    }

}

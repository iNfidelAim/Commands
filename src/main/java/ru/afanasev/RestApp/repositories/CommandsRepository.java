package ru.afanasev.RestApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.afanasev.RestApp.models.Command;

import java.util.Optional;

@Repository
public interface CommandsRepository extends JpaRepository<Command, Integer> {

    Optional<Command> findByCommandName(String commandName);
}

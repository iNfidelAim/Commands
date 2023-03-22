package ru.afanasev.RestApp.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasev.RestApp.models.Command;
import ru.afanasev.RestApp.models.Player;
import ru.afanasev.RestApp.repositories.CommandsRepository;
import ru.afanasev.RestApp.util.CommandNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class CommandsService {

    private final CommandsRepository commandsRepository;

    @Autowired
    public CommandsService(CommandsRepository commandsRepository) { this.commandsRepository = commandsRepository; }

    public List<Command> findAll() { return commandsRepository.findAll(); }

    public Command findOne(int id) {
        Optional<Command> foundCommand = commandsRepository.findById(id);
        return  foundCommand.orElseThrow(CommandNotFoundException::new);
    }

    List<Command> findByDateOfBuildIsBetweenOrderByDateOfBuild(Date dateOfBuild, Date dateOfBuild2) {
        return commandsRepository.findByDateOfBuildIsBetweenOrderByDateOfBuild(dateOfBuild, dateOfBuild2);
    }

    List<Command> findBySportTypeOrderBySportType(String sportType) {
        return commandsRepository.findBySportTypeOrderBySportType(sportType);
    }


    @Transactional
    public void save(Command command) {
        enrichCommand(command);
        commandsRepository.save(command);
    }
    private void enrichCommand(Command command) {
    }

    @Transactional
    public void update(int id, Command updatedCommand) {
        updatedCommand.setId(id);
        commandsRepository.save(updatedCommand);
    }

    @Transactional
    public void delete(int id) {
        commandsRepository.deleteById(id);
    }

    public List<Player> getPlayersByCommandId(int id) {
        Optional<Command> command = commandsRepository.findById(id);

        if (command.isPresent()) {
            Hibernate.initialize(command.get().getPlayers());
            return command.get().getPlayers();
        }
        else {
            return null;

        }
    }
}


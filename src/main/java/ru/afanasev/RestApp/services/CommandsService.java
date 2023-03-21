package ru.afanasev.RestApp.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasev.RestApp.models.Command;
import ru.afanasev.RestApp.models.Player;
import ru.afanasev.RestApp.repositories.CommandsRepository;

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
        Optional<Command> foundCity = commandsRepository.findById(id);
      //  return  foundCity.orElseThrow(CityNotFoundException::new);
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

    public Optional<Command> getCommandByCommandName(String commandName) {
        return commandsRepository.findByCommandName(commandName);
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

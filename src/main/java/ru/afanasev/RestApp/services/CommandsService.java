package ru.afanasev.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasev.RestApp.models.Command;
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
        Optional<Command> foundCommand = commandsRepository.findById(id);
        return foundCommand.orElse(null);
    }
}

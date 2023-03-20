package ru.afanasev.RestApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.afanasev.RestApp.models.Command;
import ru.afanasev.RestApp.services.CommandsService;

import java.util.List;

@RestController
@RequestMapping("/commands")
public class CommandsController {

    private final CommandsService commandsService;

    @Autowired
    public CommandsController(CommandsService commandsService) {
        this.commandsService = commandsService;
    }

    public List<Command> getCommands() {
        return commandsService.findAll();

    }
}

package ru.afanasev.RestApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.afanasev.RestApp.models.Command;
import ru.afanasev.RestApp.services.CommandsService;
import ru.afanasev.RestApp.util.CommandErrorResponse;
import ru.afanasev.RestApp.util.CommandNotCreatedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/commands")
public class CommandsController {

    private final CommandsService commandsService;

    @Autowired
    public CommandsController(CommandsService commandsService) { this.commandsService = commandsService; }

    @GetMapping()
    public List<Command> getCommands() { return commandsService.findAll(); }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Command command, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMsg = new StringBuilder();

                List<FieldError> errors = bindingResult.getFieldErrors();
                for(FieldError error : errors) {
                    errorMsg.append(error.getField())
                            .append(" - ").append(error.getDefaultMessage())
                            .append(";");
                }
                throw new CommandNotCreatedException(errorMsg.toString());
            }

            commandsService.save(command);
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<CommandErrorResponse> handleException(CommandNotCreatedException e) {
        CommandErrorResponse response = new CommandErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

package ru.afanasev.RestApp.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.afanasev.RestApp.dto.CommandDTO;
import ru.afanasev.RestApp.models.Command;
import ru.afanasev.RestApp.models.Player;
import ru.afanasev.RestApp.services.CommandsService;
import ru.afanasev.RestApp.util.CommandErrorResponse;
import ru.afanasev.RestApp.util.CommandNotCreatedException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/commands")
public class CommandsController {

    private final CommandsService commandsService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommandsController(CommandsService commandsService, ModelMapper modelMapperCommand) {
        this.commandsService = commandsService;
        this.modelMapper = modelMapperCommand;
    }

    @GetMapping()
    public String index(Model model) {
        return model.addAttribute("commands", commandsService.findAll()).toString();
    }

    //Я решил сделать пункт задания "Получить всех участников конкретной команды" по id команды
    @GetMapping("/{id}")
    public List<Player> getPlayersByCommandId(@PathVariable("id") int id, Model model) {
        model.addAttribute("command", commandsService.findOne(id));
        model.addAttribute("players", commandsService.getPlayersByCommandId(id));

        return commandsService.getPlayersByCommandId(id);
    }


    @GetMapping("/{date_of_build}")
    List<Command> findByDateOfBuildIsBetweenOrderByDateOfBuild(@PathVariable("date_of_build") Model model) {
        model.addAttribute("commands", commandsService.findAll());

        return commandsService.findAll();
    }

    @GetMapping("/command/{id}")
    public CommandDTO getCommand(@PathVariable("id") int id) {
        return convertToCommandDTO(commandsService.findOne(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CommandDTO commandDTO, BindingResult bindingResult) {
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

            commandsService.save(convertToCommand(commandDTO));
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

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("command") @Valid Command command, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "commands/edit";

        commandsService.update(id, command);
        return "redirect:/commands";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        commandsService.delete(id);
        return "Удалено";
    }

    private Command convertToCommand(CommandDTO commandDTO) {
        return modelMapper.map(commandDTO, Command.class);
    }

    private CommandDTO convertToCommandDTO(Command command) {
        return modelMapper.map(command, CommandDTO.class);
    }
}

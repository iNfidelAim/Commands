package ru.afanasev.RestApp.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.afanasev.RestApp.dto.PlayerDTO;
import ru.afanasev.RestApp.models.Player;
import ru.afanasev.RestApp.repositories.PlayersRepository;
import ru.afanasev.RestApp.services.CommandsService;
import ru.afanasev.RestApp.services.PlayersService;
import ru.afanasev.RestApp.util.PlayerErrorResponse;
import ru.afanasev.RestApp.util.PlayerNotCreatedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayersController {

    private final PlayersService playersService;
    private final CommandsService commandsService;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayersController(PlayersService playersService, CommandsService commandsService,
                             PlayersRepository playersRepository, ModelMapper modelMapperPlayer) {
        this.playersService = playersService;
        this.commandsService = commandsService;
        this.modelMapper = modelMapperPlayer;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@ModelAttribute("player") @Valid PlayerDTO playerDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PlayerNotCreatedException(errorMsg.toString());
        }
        commandsService.save(convertToPlayer(playerDTO).getOwner());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PlayerErrorResponse> handleException(PlayerNotCreatedException e) {
        PlayerErrorResponse response = new PlayerErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("player") @Valid Player player, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "players/edit";

        playersService.update(id, player);
        return "redirect:/players";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        playersService.delete(id);
        return "Удалено";
    }

    private Player convertToPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }
}


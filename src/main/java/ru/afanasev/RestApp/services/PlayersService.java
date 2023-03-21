package ru.afanasev.RestApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasev.RestApp.models.Player;
import ru.afanasev.RestApp.repositories.PlayersRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PlayersService {

    private final PlayersRepository playersRepository;

    @Autowired
    public PlayersService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Transactional
    public void save(Player player) {
        playersRepository.save(player);
    }

    @Transactional
    public void update(int id, Player updatedPlayer) {
        Player playerToBeUpdated = playersRepository.findById(id).get();

        updatedPlayer.setId(id);
        updatedPlayer.setOwner(playerToBeUpdated.getOwner());

        playersRepository.save(updatedPlayer);
    }

    @Transactional
    public void delete(int id) {
        playersRepository.deleteById(id);
    }

    public Optional<Player> getPlayerByPlayerName(String playerName) {
        return playersRepository.findByNameOfPlayer(playerName);
    }

}





package ru.afanasev.RestApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasev.RestApp.models.Command;
import ru.afanasev.RestApp.models.Player;
import ru.afanasev.RestApp.repositories.PlayersRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PlayersService {

    private final PlayersRepository playersRepository;

    @Autowired
    public PlayersService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }


    public List<Player> findByOwner(Command owner) {
        return playersRepository.findByOwner(owner);
    }

    public  List<Player> findByRoleOfPlayer(Player roleOfPlayer) {
        return playersRepository.findByRoleOfPlayer(roleOfPlayer);
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

}





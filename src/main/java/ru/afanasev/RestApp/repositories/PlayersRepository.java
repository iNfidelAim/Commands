package ru.afanasev.RestApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.afanasev.RestApp.models.Player;

import java.util.Optional;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Integer> {


    Optional<Player> findByNameOfPlayer(String playerName);
}
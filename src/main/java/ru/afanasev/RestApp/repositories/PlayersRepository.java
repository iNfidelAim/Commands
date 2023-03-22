package ru.afanasev.RestApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.afanasev.RestApp.models.Command;
import ru.afanasev.RestApp.models.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Integer> {

    List<Player> findByOwner(Command owner);

    List<Player> findByRoleOfPlayer(Player roleOfPlayer);
}
package ru.afanasev.RestApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.afanasev.RestApp.models.Player;

import java.util.List;


@Repository
public interface PlayersRepository extends JpaRepository<Player, Integer> {


    List<Player> findByRoleOfPlayer(String roleOfPlayer);

}
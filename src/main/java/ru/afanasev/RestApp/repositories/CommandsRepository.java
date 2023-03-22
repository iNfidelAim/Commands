package ru.afanasev.RestApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.afanasev.RestApp.models.Command;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface CommandsRepository extends JpaRepository<Command, Integer> {

    List<Command> findByDateOfBuildIsBetweenOrderByDateOfBuild(Date dateOfBuild, Date dateOfBuild2);

    List<Command> findBySportTypeOrderBySportType(String sportType);


}
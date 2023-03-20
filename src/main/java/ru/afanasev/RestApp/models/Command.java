package ru.afanasev.RestApp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "Command")
public class Command {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название команды должно быть от 2 до 100 символов длиной")
    @Column(name = "command_name")
    private String commandName;

    @NotEmpty(message = "Поле \"вид спорта\" не должно быть пустым")
    @Column(name = "sport_type")
    private String sportType;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotEmpty(message = "Необходимо ввести дату в формате 2000-01-01 (Год-месяц-число)")
    @Column(name = "date_of_build")
    private Date dateOfBuild;

    public Command() {
    }

    public Command(int id, String commandName, String sportType, Date dateOfBuild) {
        this.id = id;
        this.commandName = commandName;
        this.sportType = sportType;
        this.dateOfBuild = dateOfBuild;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getCommandName() { return commandName; }

    public void setCommandName(String commandName) { this.commandName = commandName; }

    public String getSportType() { return sportType; }

    public void setSportType(String sportType) { this.sportType = sportType; }

    public Date getDateOfBuild() { return dateOfBuild; }

    public void setDateOfBuild(Date dateOfBuild) { this.dateOfBuild = dateOfBuild; }
}

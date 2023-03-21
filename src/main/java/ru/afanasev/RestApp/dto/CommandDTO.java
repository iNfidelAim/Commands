package ru.afanasev.RestApp.dto;

import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class CommandDTO {


    @NotEmpty(message = "Название команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название команды должно быть от 2 до 100 символов длиной")
    private String commandName;

    @NotEmpty(message = "Поле \"вид спорта\" не должно быть пустым")
    private String sportType;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotEmpty(message = "Необходимо ввести дату в формате 2000-01-01 (Год-месяц-число)")
    private Date dateOfBuild;


    public String getCommandName() { return commandName; }

    public void setCommandName(String commandName) { this.commandName = commandName; }

    public String getSportType() { return sportType; }

    public void setSportType(String sportType) { this.sportType = sportType; }

    public Date getDateOfBuild() { return dateOfBuild; }

    public void setDateOfBuild(Date dateOfBuild) { this.dateOfBuild = dateOfBuild; }
}

package ru.afanasev.RestApp.dto;


import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class PlayerDTO {

    @NotEmpty(message = "Фамилия участника команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Фамилия участника должна быть от 2 до 100 символов длиной")
    private String surnameOfPlayer;

    @NotEmpty(message = "Имя участника команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя участника должно быть от 2 до 100 символов длиной")
    private String nameOfPlayer;

    @NotEmpty(message = "Отчество участника команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Отчество участника должно быть от 2 до 100 символов длиной")
    private String secondnameOfPlayer;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotEmpty(message = "Необходимо ввести дату в формате 2000-01-01 (Год-месяц-число)")
    private Date dateOfBirthday;

    public String getSurnameOfPlayer() { return surnameOfPlayer; }

    public void setSurnameOfPlayer(String surnameOfPlayer) { this.surnameOfPlayer = surnameOfPlayer; }

    public String getNameOfPlayer() { return nameOfPlayer; }

    public void setNameOfPlayer(String nameOfPlayer) { this.nameOfPlayer = nameOfPlayer; }

    public String getSecondnameOfPlayer() { return secondnameOfPlayer; }

    public void setSecondnameOfPlayer(String secondnameOfPlayer) { this.secondnameOfPlayer = secondnameOfPlayer; }

    public Date getDateOfBirthday() { return dateOfBirthday; }

    public void setDateOfBirthday(Date dateOfBirthday) { this.dateOfBirthday = dateOfBirthday; }
}

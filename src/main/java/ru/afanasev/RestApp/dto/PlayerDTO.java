package ru.afanasev.RestApp.dto;


import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
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

    @NotEmpty(message = "Роль участника команды не должна быть пустой")
    @Size(min = 1, max = 100, message = "Роль участника должна содержать от 1 до 100 символов длиной")
    private String roleOfPlayer;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotEmpty(message = "Необходимо ввести дату в формате 2000-01-01 (Год-месяц-число)")
    private Date dateOfBirthday;

    public String getSurnameOfPlayer() { return surnameOfPlayer; }

    public void setSurnameOfPlayer(String surnameOfPlayer) { this.surnameOfPlayer = surnameOfPlayer; }

    public String getNameOfPlayer() { return nameOfPlayer; }

    public void setNameOfPlayer(String nameOfPlayer) { this.nameOfPlayer = nameOfPlayer; }

    public String getSecondnameOfPlayer() { return secondnameOfPlayer; }

    public void setSecondnameOfPlayer(String secondnameOfPlayer) { this.secondnameOfPlayer = secondnameOfPlayer; }

    public String getRoleOfPlayer() { return roleOfPlayer; }

    public void setRoleOfPlayer(String roleOfPlayer) { this.roleOfPlayer = roleOfPlayer; }

    public Date getDateOfBirthday() { return dateOfBirthday; }

    public void setDateOfBirthday(Date dateOfBirthday) { this.dateOfBirthday = dateOfBirthday; }
}

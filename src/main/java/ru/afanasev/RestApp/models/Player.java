package ru.afanasev.RestApp.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Player")
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Фамилия участника команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Фамилия участника должна быть от 2 до 100 символов длиной")
    @Column(name = "surname_of_player")
    private String surnameOfPlayer;

    @NotEmpty(message = "Имя участника команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя участника должно быть от 2 до 100 символов длиной")
    @Column(name = "name_of_player")
    private String nameOfPlayer;

    @NotEmpty(message = "Отчество участника команды не должно быть пустым")
    @Size(min = 2, max = 100, message = "Отчество участника должно быть от 2 до 100 символов длиной")
    @Column(name = "secondname_of_player")
    private String secondnameOfPlayer;

    //в задании есть пункт "Фильтрация по роли/позиции в команде", но вс сущности участники не было такого столбца
    //поэтому я его добавил к участникам команды "Роль в команде"
    @NotEmpty(message = "Роль участника команды не должна быть пустой")
    @Size(min = 1, max = 100, message = "Роль участника должна содержать от 1 до 100 символов длиной")
    @Column(name = "role_of_player")
    private String roleOfPlayer;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotEmpty(message = "Необходимо ввести дату в формате 2000-01-01 (Год-месяц-число)")
    @Column(name = "birthday")
    private Date dateOfBirthday;

    @ManyToOne
    @JoinColumn(name = "command_id", referencedColumnName = "id")
    private Command owner;

    public Player() {
    }

    public Player(int id, String nameOfPlayer, String surnameOfPlayer, String secondnameOfPlayer, Date dateOfBirthday, Command owner) {
        this.id = id;
        this.nameOfPlayer = nameOfPlayer;
        this.surnameOfPlayer = surnameOfPlayer;
        this.secondnameOfPlayer = secondnameOfPlayer;
        this.dateOfBirthday = dateOfBirthday;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getNameOfPlayer() { return nameOfPlayer; }

    public void setNameOfPlayer(String nameOfPlayer) { this.nameOfPlayer = nameOfPlayer; }

    public String getSurnameOfPlayer() { return surnameOfPlayer; }

    public void setSurnameOfPlayer(String surnameOfPlayer) { this.surnameOfPlayer = surnameOfPlayer; }

    public String getSecondnameOfPlayer() { return secondnameOfPlayer; }

    public void setSecondnameOfPlayer(String secondnameOfPlayer) { this.secondnameOfPlayer = secondnameOfPlayer; }

    public Date getDateOfBirthday() { return dateOfBirthday; }

    public void setDateOfBirthday(Date dateOfBirthday) { this.dateOfBirthday = dateOfBirthday; }

    public Command getOwner() { return owner; }

    public void setOwner(Command owner) { this.owner = owner; }
}

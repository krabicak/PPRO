package cz.uhk.ppro.vehicle.registry.common.entities;

import cz.uhk.ppro.vehicle.registry.common.converters.BooleanConverter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Uživatel systému
 *
 * @author hotov
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    private Long idUser;
    /**
     * Informace o uživateli
     */
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "IDUSER")
    private Person person;

    /**
     * Role uživatele v systému
     */
    @Column(nullable = false, length = 7)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * Heslo ve formě HASHE!
     */
    @Column(nullable = false, length = 512)
    private String password;

    /**
     * Zdali je uživatel povolen využívat systém
     */
    @Column(length = 1, nullable = false)
    @Convert(converter = BooleanConverter.class)
    private boolean enable;


    /**
     * Login pro uživatele
     */
    @Column(nullable = false, length = 45)
    private String login;

    public User() {
    }

    public enum UserRole {
        ADMIN("Administrátor"), INSURER("Pojistitel"), CLERK("Úředník");

        private String data;

        UserRole(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", person=" + person +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                ", login='" + login + '\'' +
                '}';
    }
}

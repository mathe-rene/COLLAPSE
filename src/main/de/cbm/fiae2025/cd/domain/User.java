package de.cbm.fiae2025.cd.domain;

import java.util.Objects;

public class User {
    // Deklaration der Variablen
    int id;
    String username;
    String email;
    String password;
    boolean isAdmin;

    // Constructor
    public User( String username, String email, String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // Getter
    public int getId() {return id;}
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public boolean getAdmin() {return isAdmin;}


    // Setter
    public void setUsername(String username) {
        this.checknames(username);
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public void setId(int id) {
        if (this.id > 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    // Prüfung der Richtigkeit des Namens
    public void checknames(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Der Vorname darf nicht null sein!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }
//hallo welt
    @Override
    public int hashCode() {return Objects.hash(username);}
}

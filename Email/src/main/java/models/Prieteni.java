package models;

public class Prieteni {

    private int idUser;

    private String username;

    public Prieteni(int idUser, String username) {
        this.idUser = idUser;
        this.username = username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

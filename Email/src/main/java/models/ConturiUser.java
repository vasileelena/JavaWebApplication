package models;

public class ConturiUser {

    private String username;

    private int idUser;

    private String parola;

    public ConturiUser(String username, int idUser, String parola) {
        this.username = username;
        this.idUser = idUser;
        this.parola = parola;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Conturi user " + idUser +
                ": username='" + username +
                ", parola='" + parola + '\'' +
                '}';
    }
}

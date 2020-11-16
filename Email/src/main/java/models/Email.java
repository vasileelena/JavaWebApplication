package models;

public class Email {

    private int idEmail;

    private int idUser;

    private String data;

    private String tipEmail;

    private String usernamePrieten;

    private String subiect;

    private String mesaj;

    public Email(int idUser, String data, String tipEmail, String usernamePrieten, String subiect, String mesaj) {
        this.idUser = idUser;
        this.data = data;
        this.tipEmail = tipEmail;
        this.usernamePrieten = usernamePrieten;
        this.subiect = subiect;
        this.mesaj = mesaj;
    }

    public int getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(int idEmail) {
        this.idEmail = idEmail;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipEmail() {
        return tipEmail;
    }

    public void setTipEmail(String tipEmail) {
        this.tipEmail = tipEmail;
    }

    public String getUsernamePrieten() {
        return usernamePrieten;
    }

    public void setUsernamePrieten(String usernamePrieten) {
        this.usernamePrieten = usernamePrieten;
    }

    public String getSubiect() {
        return subiect;
    }

    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    @Override
    public String toString() {
        return "Email " + tipEmail + " catre/de la " +
                usernamePrieten + '\'' +
                "Data: " + data + '\'' +
                "Subiect: " + subiect + '\'' +
                "Mesaj: " + mesaj + '\'';
    }
}

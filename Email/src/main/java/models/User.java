package models;

public class User {

    private int idUser;

    private String nume;

    private String prenume;

    public User(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    @Override
    public String toString() {
        return "User: " +
                "idUser=" + idUser +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }
}

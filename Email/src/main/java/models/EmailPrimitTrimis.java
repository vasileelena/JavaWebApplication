package models;

public class EmailPrimitTrimis {

    private String usernamePrieten;

    private String data;

    private String subiect;

    private String mesaj;

    public EmailPrimitTrimis(String usernamePrieten, String data, String subiect, String mesaj) {
        this.usernamePrieten = usernamePrieten;
        this.data = data;
        this.subiect = subiect;
        this.mesaj = mesaj;
    }

    public String getUsernamePrieten() {
        return usernamePrieten;
    }

    public void setUsernamePrieten(String usernamePrieten) {
        this.usernamePrieten = usernamePrieten;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

}

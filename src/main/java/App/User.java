package App;

import java.io.Serializable;

public class User implements Serializable {
    private String chatId;
    private String valuta;
    public transient int doRegistration=0;
    private  boolean info=false;
    private double min;
    private double max;
    private double stavka;
    private transient double kurs=0.0;

    public User(String chatId, String valuta, double min, double max, double stavka) {
        this.chatId = chatId;
        this.valuta = valuta;
        this.min = min;
        this.max = max;
        this.stavka = stavka;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public double getStavka() {
        return stavka;
    }

    public void setStavka(double stavka) {
        this.stavka = stavka;
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId='" + chatId + '\'' +
                ", valuta='" + valuta + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", stavka=" + stavka +
                ", kurs=" + kurs +
                '}';
    }

    public double getKurs() {
        return kurs;
    }

    public void setKurs(double kurs) {
        this.kurs = kurs;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

}

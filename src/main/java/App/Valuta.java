package App;

public class Valuta {
    private String name;
    private Double kurs;

    public Valuta(String name, Double kurs) {
        this.name = name;
        this.kurs = kurs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    @Override
    public String toString() {
        return name+"="+kurs+"$";
    }
}

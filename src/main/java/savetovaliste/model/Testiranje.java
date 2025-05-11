package savetovaliste.model;

public class Testiranje {
    private final int id;
    private final Test test;
    private final double rezultat;
    private final Seansa seansa;
    public Testiranje(int id, Test test, double rezultat, Seansa seansa) {
        this.id = id;
        this.test = test;
        this.rezultat = rezultat;
        this.seansa = seansa;
    }
    public int getId() {
        return id;
    }
    public Test getTest() {
        return test;
    }
    public double getRezultat() {
        return rezultat;
    }
    public Seansa getSeansa() {
        return seansa;
    }
}

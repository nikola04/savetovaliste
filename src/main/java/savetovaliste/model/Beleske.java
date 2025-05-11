package savetovaliste.model;

public class Beleske {
    private final int id;
    private final String text;
    private final Seansa seansa;
    public Beleske(int id, String text, Seansa seansa) {
        this.id = id;
        this.text = text;
        this.seansa = seansa;
    }
    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public Seansa getSeansa() {
        return seansa;
    }
}

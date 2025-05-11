package savetovaliste.model;

public enum RazlogObjave {
    SUPERVIZIJA("supervizija"),
    PRIJAVA_POLICIJI("prijava policiji"),
    SUD("sud");

    private final String naziv;

    RazlogObjave(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}

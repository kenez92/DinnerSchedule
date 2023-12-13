package pl.kenez.enums;

public enum Unit {
    KG("kg"),
    GRAM("gram"),
    PIECE ("szt"),
    PACKAGE ("paczka");
    private String name;

    Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

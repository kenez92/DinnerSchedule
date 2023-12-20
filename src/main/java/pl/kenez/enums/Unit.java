package pl.kenez.enums;

import java.util.Arrays;

public enum Unit {
    KG("kg"),
    GRAM("gram"),
    PIECE("szt"),
    PACKAGE("paczka");
    private String name;

    Unit(String name) {
        this.name = name;
    }

    public static Unit fromName(final String name) {
        return Arrays.stream(Unit.values())
                     .filter(e -> e.getName().equals(name))
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("No enum with name: " + name));
    }

    public String getName() {
        return name;
    }
}

package org.koimiam.models;

public enum Unite {
    PIECE("u"), MILLIGRAMME("mg"), GRAMME("g"), KILOGRAMME("kg"), LITRE("l"),
    CENTILITRE("cl"), MILLILITRE("ml"), COUILLERE_CAFE("cc"), COUILLERE_SOUPE("cs"),
    PINCEE("p");

    private String unite;

    Unite(String unite) {
        this.unite = unite;
    }

    public String getUnite() {
        return unite;
    }

    public static Unite fromString(String nom) {
        for (Unite unite : Unite.values()) {
            if (unite.getUnite().equalsIgnoreCase(nom)) {
                return unite;
            }
        }
        throw new IllegalArgumentException("No enum constant " + Unite.class + " with name " + nom);
    }
}

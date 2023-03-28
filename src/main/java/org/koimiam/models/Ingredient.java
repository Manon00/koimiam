package org.koimiam.models;

public class Ingredient {
    private String nom;

    private int quantite;

    private Unite unite;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public String toString() {
        return quantite + unite.getUnite() + " " + nom;
    }
}

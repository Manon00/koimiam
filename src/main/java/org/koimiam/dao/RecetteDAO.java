package org.koimiam.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.koimiam.models.Ingredient;
import org.koimiam.models.Recette;
import org.koimiam.models.Unite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecetteDAO {
    private static final String CSV = System.getProperty("user.dir") + "/data/recettes.csv";

    private final static Logger logger = LoggerFactory.getLogger(RecetteDAO.class);

    public void readCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                System.out.println(line[0]);
                String[] ings = line[1].split("-");
                for (String ing : ings) {
                    String[] e = ing.split("\\*");
                    System.out.println("- " + e[0] + e[1] + " " + e[2]);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public List<Recette> getRecettes() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV))) {
            List<Recette> recettes = new ArrayList<>();
            String[] line;
            reader.readNext();
            Recette recette;
            while ((line = reader.readNext()) != null) {
                recette = new Recette();
                List<Ingredient> ingredientList = new ArrayList<>();
                recette.setNom(line[0]);
                String[] ings = line[1].split("-");
                for (String ing : ings) {
                    String[] e = ing.split("\\*");
                    Ingredient ingredient = new Ingredient();
                    ingredient.setNom(e[2]);
                    ingredient.setQuantite(Integer.valueOf(e[0]));
                    ingredient.setUnite(Unite.fromString(e[1]));
                    ingredientList.add(ingredient);
                }
                recette.setIngredients(ingredientList);
                recettes.add(recette);
            }

            return recettes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addRecettes(Recette recette) {
        try {
            // Open the file for writing
            FileWriter writer = new FileWriter(CSV, true); // true flag is for append mode
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Write the new line to the file
            StringBuilder newLine = new StringBuilder("\n").append(recette.getNom()).append(",");
            List<Ingredient> ingredients = recette.getIngredients();
            for (int i = 0; i < ingredients.size(); i++) {
                newLine.append(ingredients.get(i).getQuantite()).append("*").append(ingredients.get(i).getUnite())
                        .append("*").append(ingredients.get(i).getNom());
                if (i < ingredients.size()-1 )
                    newLine.append("-");
            }
            bufferedWriter.write(newLine.toString());
            bufferedWriter.newLine(); // add a new line character

            // Flush and close the writer
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            logger.error("error : écriture d'une nouvelle recette", e);
            return false;
        }
    }
}

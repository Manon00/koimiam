package org.koimiam.controller;

import org.koimiam.dao.RecetteDAO;
import org.koimiam.models.Ingredient;
import org.koimiam.models.Recette;
import org.koimiam.models.RecettesIngredients;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/recettes")
public class RecetteController {

    private RecetteDAO recetteDAO;

    public RecetteController() {
        recetteDAO = new RecetteDAO();
    }

    @GetMapping("/test")
    public String getError() {
        return "test";
    }

    @GetMapping
    public List<Recette> getRecettes() {
        return recetteDAO.getRecettes();
    }

    @GetMapping("/{nom}")
    public Recette getRecette(@PathVariable String nom) {
        Recette recette;
        try {
            recette = recetteDAO.getRecettes().stream().filter(n -> n.getNom().equals(nom)).findFirst().get();
        } catch (Exception e) {
            recette = new Recette();
            recette.setNom("Recette non trouvée");
        }
        return recette;
    }

    @GetMapping("random/{nombre}")
    public List<Recette> getRandomRecettes(@PathVariable int nombre) {
        List<Recette> recettes = recetteDAO.getRecettes();

        if (nombre < 0)
            throw new IllegalArgumentException("Le nombre de recettes voulues doit être supérieur à 0");

        if (recettes.size() < nombre)
            throw new IllegalArgumentException("Le nombre de recettes voulues doit être inférieur au nombre de recettes : " + recettes.size());

        Random random = new Random();

        while (recettes.size() != nombre)
            recettes.remove(random.nextInt(recettes.size()));

        return recettes;
    }

    @GetMapping("ingredients/{nombre}")
    public RecettesIngredients getIngredientsRecettes(@PathVariable int nombre) {
        List<Recette> recettes = getRandomRecettes(nombre);

        RecettesIngredients recettesIngredients = new RecettesIngredients();

        List<String> recettesNom = new ArrayList<>();

        Map<String, Ingredient> ingredients = new HashMap<>();

        for (Recette recette : recettes) {
            recettesNom.add(recette.getNom());

            for (Ingredient ingredient : recette.getIngredients())
                if (ingredients.containsKey(ingredient.getNom())) {
                    ingredient.setQuantite(ingredients.get(ingredient.getNom()).getQuantite()
                                    + ingredient.getQuantite());
                } else {
                    ingredients.put(ingredient.getNom(), ingredient);
                }
        }

        recettesIngredients.setRecettes(recettesNom);
        recettesIngredients.setIngredients(ingredients);

        return recettesIngredients;
    }

    //Header - Content-Type : application/json
    @PostMapping("/recette")
    public boolean addRecette(@RequestBody Recette recette) {
        return recetteDAO.addRecettes(recette);
    }
}

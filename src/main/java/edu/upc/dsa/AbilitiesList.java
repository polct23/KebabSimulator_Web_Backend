package edu.upc.dsa;
import edu.upc.dsa.models.Ability;

import java.util.List;

public interface AbilitiesList {
    Ability addAbility(String abilityName, String description, int damage, double price);
    Ability addAbility(Ability ability);
    Ability getAbility(String idWeapon);
    List<Ability> getAbilities();
    void deleteAbility(String idWeapon);

    int size();
}

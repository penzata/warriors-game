package org.example.battleunits;

public interface HasVampirism extends CanAttack {
    int getVampirism();
    void healthRestored(CanAttack damageDealer);

}

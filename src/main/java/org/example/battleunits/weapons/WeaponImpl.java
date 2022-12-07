package org.example.battleunits.weapons;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeaponImpl implements Weapon {

    private int healthStat;
    private int attackStat;
    private int defenceStat;
    private int vampirismStat;
    private int healPowerStat;
    private int piercingAttackStat;
    private int decreaseDurabilityStep;
    private int durabilityStat;

    @Override
    public void decreaseDurability() {
        if (durabilityStat > 0 && decreaseDurabilityStep > 0) {
            durabilityStat -= decreaseDurabilityStep;
        }
        if (durabilityStat <= 0) {
            breakWeapon();
        }
    }

    private void breakWeapon() {
        healthStat = 0;
        attackStat = 0;
        defenceStat = 0;
        vampirismStat = 0;
        healPowerStat = 0;
        piercingAttackStat = 0;
    }
}
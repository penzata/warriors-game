package org.example.battleunits.subsidiary;

import org.example.battleunits.units.WarriorUnit;

public interface CanHealChain {

    void setNextChain(CanHealChain nextChain);

    void canHeal(WarriorUnit unitInFront);
}

package org.example.battleunits.subsidiary;

import org.example.battleunits.units.WarriorUnit;

public interface HealChainUnit {

    void setNextChain(HealChainUnit nextChain);

    void heal(WarriorUnit unitInFront);
}

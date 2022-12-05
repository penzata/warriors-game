package org.example.battleunits;

import org.example.battlecommands.CombatUnitHitCommand;
import org.example.battlecommands.Command;
import org.example.battlecommands.ProcessCommandChain;
import org.example.battleunits.characteristics.HealPower;
import org.example.battleunits.subsidiary.CanReceiveDamage;
import org.example.battleunits.weapons.WeaponType;

public interface Healer extends CombatUnit, HealPower, ProcessCommandChain {

    @Override
    default void hit(CanReceiveDamage opponent) {
//        TODO think of handling Healer vs. Healer fight
//        if (opponent instanceof Healer) {
//            equipWeapon(WeaponType.KATANA);
//            equipWeapon(WeaponType.KATANA);
//            equipWeapon(WeaponType.KATANA);
//        }
    }

    @Override
    default void processCommand(Command command, CombatUnit commandSender) {
        if (isAlive() && command.equals(CombatUnitHitCommand.HEAL)) {
            heal(commandSender);
        }
    }

    default void heal(CombatUnit commandSender) {
        if ((commandSender.getHealth() < commandSender.getInitialHealth()) && (getMedKit() > 0)) {
            commandSender.healedBy(getHealPower());
            changeMedKitQuantity(getMedKit() - 1);
        }
    }

    void changeMedKitQuantity(int quantity);

}

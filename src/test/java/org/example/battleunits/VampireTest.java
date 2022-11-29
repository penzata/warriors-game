package org.example.battleunits;

import org.example.weapons.CustomWeapon;
import org.example.weapons.Weapon;
import org.example.fighting.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class VampireTest {
    Warrior vampire;
    Rookie rookie;

    @BeforeEach
    void init() {
        vampire = Warrior.newVampire();
        rookie = new Rookie();
    }

    @Test
    void receiveDamageFromRookie() {
        rookie.hit(vampire);
        assertEquals(39, vampire.getHealth());

        vampire.hit(rookie);
        assertEquals(40, vampire.getHealth());
    }

    @Test
    void OneVampireArmyAttacksWarriorAndKnightAndLoses() {
        Army army1 = new ArmyImpl(Warrior::newVampire, 1);
        Army army2 = new ArmyImpl(Warrior::create, 1)
                .addBattleUnits(Warrior::newKnight, 2);

        assertFalse(Battle.fight(army1, army2));
    }

    class Rookie extends WarriorImpl {
        @Override
        public int getAttack() {

            return 1;
        }
    }

    @DisplayName("different weapons equipped by Vampire")
    @ParameterizedTest(name = "equipped {0}")
    @MethodSource({"equipWeapon"})
    void EquipDifferentWeaponsOnWarriorAndVerifyItsStats (Weapon weapon, int expectedHealth, int expectedAttack, int expectedVampirism) {
        vampire.equipWeapon(weapon);
//TODO see why getVampirism() doesn't show
        assertEquals(expectedHealth, vampire.getHealth());
        assertEquals(expectedAttack, vampire.getAttack());
    }

    static Stream<Arguments> equipWeapon() {
        return Stream.of(
                arguments(Weapon.sword(), 45, 6, 50),
                arguments(Weapon.shield(), 60, 3, 50),
                arguments(Weapon.greatAxe(), 25, 9, 50),
                arguments(Weapon.katana(), 20, 10, 100),
                arguments(Weapon.magicWand(), 70, 7, 50),
                arguments(new CustomWeapon(-100, -100, -100, -100, -100), 0, 0, 0));
    }

}
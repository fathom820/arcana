package dev.mfrank.engine;

import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.spell.Spell;
import dev.mfrank.spell.SpellEmpty;
import dev.mfrank.spell.SpellFireball;
import dev.mfrank.spell.SpellSpark;

public class EngineSpell {

    private static Spell[] spells = new Spell[] {
        new SpellEmpty(),
        new SpellSpark(),
        new SpellFireball()
    };;


    public static Spell getById(String id) {
        try {
            for (Spell s : spells) {
                Debug.tell(s.getName());
                if (s.getName().equals(id)) {
                    return s;
                }
            }
        } catch (NullPointerException nullPointerException) {
            Io.tell("Spell.getById(): Unable to find spell " + id);
        }

        return null;
    }
}

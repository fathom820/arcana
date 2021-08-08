package dev.mfrank.engine;

import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.spell.*;

public class EngineSpell {

    private static Spell[] spells = new Spell[] {
        new SpellEmpty(),
        new SpellSpark(),
        new SpellFireball(),
        new SpellPutridMusk()
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

package dev.mfrank.item.scroll;

import dev.mfrank.engine.EngineSpell;
import dev.mfrank.item.Item;
import dev.mfrank.spell.Spell;

public abstract class Scroll extends Item {

    private Spell spell;
    private String id;

    public Scroll () {
        super.setType("Scroll");
    }

    // getters //
    public Spell getSpell() {
        return spell;
    }

    public String getId() {
        return id;
    }

    // setters //
    public void setSpell(String spell) {
        this.spell = EngineSpell.getById(spell);
    }

    public void setId(String id) {
        this.id = id;
    }
}

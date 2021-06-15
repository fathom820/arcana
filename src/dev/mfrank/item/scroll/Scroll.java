package dev.mfrank.item.scroll;

import dev.mfrank.engine.SpellEngine;
import dev.mfrank.item.Item;
import dev.mfrank.spell.Spell;

public abstract class Scroll extends Item {

    private Spell spell;

    public Scroll () {
        super.setType("Scroll");
    }

    // getters //
    public Spell getSpell() {
        return spell;
    }

    // setters //
    public void setSpell(String spell) {
        this.spell = SpellEngine.getById(spell);
    }
}

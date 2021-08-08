package dev.mfrank.engine;

import dev.mfrank.item.Item;
import dev.mfrank.item.scroll.ScrollFireball;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

public class EngineItem {

    private static Item[] items = new Item[] {
        // SCROLLS //

        // POTIONS //
    };

    public static Item getById(String id) {
        try {
            for (Item i : items) {
                Debug.tell(i.getName());
                if (i.getName().equals(id)) {
                    return i;
                }
            }
        } catch (NullPointerException nullPointerException) {
            Io.tell("Item.getById(): Unable to find item " + id);
        }

        return null;
    }
}

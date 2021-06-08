package dev.mfrank.level;

import dev.mfrank.paladin.io.Io;

public abstract class Level {
    private int id;
    private String name;
    private String info;

    // RUNTIME //
    public void run() {
        Io.printDivider();
        Io.tellRaw(name);
        Io.setIndent(1);
        Io.tellRaw(info);
    }

    // GETTERS //
    public int getId () {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }


    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

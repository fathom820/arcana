package dev.mfrank.paladin.context;

public class Command {

    private final String keyWord;
    private String alias;
    private String description;

    public Command (String keyWord) {                               // no aliases
        this.keyWord = keyWord;
    }

    public Command (String keyWord, String alias, String description) {                 // 1 alias
        this.keyWord = keyWord;
        this.alias = alias;
        this.description = description;
    }

    public String getKeyword() {
        return keyWord;
    }

    public String getAlias() {
        return alias;
    }

    public String getDescription() {
        return description;
    }
}

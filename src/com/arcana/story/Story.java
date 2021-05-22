package com.arcana.story;

public class Story {
    private static Level currentLevel;
    private static int currentSection = 0;



    public static Level getCurrentLevel() {
        return currentLevel;
    }

    public static int getCurrentSection() {
        return currentSection;
    }

    public static void setCurrentLevel(Level currentLevel) {
        Story.currentLevel = currentLevel;
    }

    public static void setCurrentSection(int currentSection) {
        Story.currentSection = currentSection;
    }
}
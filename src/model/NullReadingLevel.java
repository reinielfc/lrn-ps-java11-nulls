package model;

public class NullReadingLevel extends ReadingLevel {
    private static final NullReadingLevel instance = new NullReadingLevel();

    public NullReadingLevel() {
        setGrade("N/A");
        setLevel("N/A");
    }

    public static NullReadingLevel getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "No reading level set";
    }
}

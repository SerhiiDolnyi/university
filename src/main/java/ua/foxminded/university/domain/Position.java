package ua.foxminded.university.domain;

public enum Position {
    INSTRUCTOR("Instructor"),
    ASSISTANT_PROFESSOR("Assistant professor"),
    ASSOCIATE_PROFESSOR("Associate professor"),
    PROFESSOR("Professor"),
    LECTURER("Lecturer"),
    SENIOR_LECTURER("Senior lecturer"),
    ASSISTANT("Assistant");
    
    private final String displayValue;
    
    private Position(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}

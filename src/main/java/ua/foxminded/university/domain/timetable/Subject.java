package ua.foxminded.university.domain.timetable;

public enum Subject {
    ACCOUNTING("Accounting"),
    DESIGN("Design"),
    LAW("Law"),
    ECONOMICS("Economics"),
    COMPUTER_SCIENCE("Data science");
    
    private final String displayValue;
    
    private Subject(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}

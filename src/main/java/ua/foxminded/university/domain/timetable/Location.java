package ua.foxminded.university.domain.timetable;

public enum Location {
    HALL_1("Hall 1"),
    HALL_2("Hall 2"),
    HALL_3("Hall 3");
    
    private final String displayValue;
    
    private Location(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}

package enums;

public enum Type_Service {
    HOUSE("House"),
    ROOM("Room"),
    VILLA("Villa");

    private final String displayName;

    Type_Service(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static boolean isValid(String input) {
        for (Type_Service service : values()) {
            if (service.displayName.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}

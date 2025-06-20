package enums;

public enum Gender_options {
    NAM("Nam"),
    NU("Nu"),
    KhAC("Khac");

    private final String displayName;

    Gender_options(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

    public static boolean isValid(String input) {
        for (Gender_options gender: values()) {
            if (gender.displayName.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}

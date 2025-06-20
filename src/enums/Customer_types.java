package enums;

public enum Customer_types {

    DIAMOND("Diamond"),

    PLATINUM("Platinum"),

    GOLD("Gold"),

    SILVER("Silver"),

    MEMBER("Member");

    private final String displayName;

    Customer_types(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static boolean isValid(String input) {
        for (Customer_types type: values()) {
            if (type.displayName.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}

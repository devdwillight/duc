package enums;

public enum Experience_levels {
    TRUNG_CAP("Trung cấp"),
    CAO_DANG("Cao đẳng"),
    DAI_HOC("Đại học"),
    SAU_DAI_HOC("Sau đại học");


    private final String displayName;

    Experience_levels(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static boolean isValid(String input) {
        for (Experience_levels level : values()) {
            if (level.displayName.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}

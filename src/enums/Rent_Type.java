package enums;

public enum Rent_Type {
    GIO("giờ"),
    NGAY("ngày"),
    TUAN("tuần"),
    THANG("tháng"),
    NAM("năm");
    private final String displayName;

    Rent_Type(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static boolean isValid(String input) {
        input = input.trim().toLowerCase();
        for (Rent_Type service : values()) {
            if (service.displayName.toLowerCase().equals(input)) {
                return true;
            }
        }
        return false;
    }

}

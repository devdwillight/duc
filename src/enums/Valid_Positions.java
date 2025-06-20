package enums;

public enum Valid_Positions {
//     "lễ tân", "phục vụ", "chuyên viên", "giám sát", "quản lý", "giám đốc"
    LE_TAN("lễ tân"),
    PHUC_VU("phục vụ"),
    CHUYEN_VIEN("chuyên viên"),
    GIAM_SAT("giám sát"),
    QUAN_LY("quản lý"),
    GIAM_DOC("giám đốc");

    private final String displayName ;
    Valid_Positions(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
    public static boolean isValid(String input) {
        for (Valid_Positions positions : values()) {
            if (positions.displayName.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

}

package utils;

public enum CheckpointResThree {
    /**
     * 点检结果
     */
    ONE("異常"),
    TWO("正常");

    private final String Button;

    CheckpointResThree(String button) {
        Button = button;
    }

    public String getButton() {
        return Button;
    }

    public static String getButton1(int number) {
        switch (number) {
            case 0:
                return ONE.Button;
            case 1:
                return TWO.Button;
            default:
                return "";
        }
    }
}

package utils;

public enum CheckpointResTwo {
    /**
     * 点检结果
     */
    ONE("否"),
    TWO("是");

    private final String Button;

    CheckpointResTwo(String button) {
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

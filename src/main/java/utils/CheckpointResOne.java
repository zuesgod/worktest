package utils;

public enum CheckpointResOne {
    /**
     * 点检结果
     */
    ONE("缺少"),
    TWO("堵塞"),
    THREE("已破損"),
    FOUR("一切正常");

    private final String Button;

    CheckpointResOne(String button) {
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
            case 2:
                return THREE.Button;
            case 3:
                return FOUR.Button;
            default:
                return "";
        }
    }
}

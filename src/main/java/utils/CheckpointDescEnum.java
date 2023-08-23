package utils;

public enum CheckpointDescEnum {
    /**
     * 点检内容
     */
    ONE("機台表面是否清潔"),
    TWO("安全門點檢是否正常"),
    THREE("噴嘴點檢(有無缺少,堵塞及破損)"),
    FOUR("相機鏡片是否清潔"),
    FIVE("气壓點檢是否在(0.4-0.6Mpa)范圍內");


    private final String Description;

    CheckpointDescEnum(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    /**
     * @Author: zues
     * @Description: //获取对应步骤的点检内容
     * @Date: 2021/11/22 19:19
     * @Params: [int]
     * @ResultType: java.lang.String
     */
    public static String getDesc(int number) {
        switch (number){
            case 1:
                return ONE.Description;
            case 2:
                return TWO.Description;
            case 3:
                return THREE.Description;
            case 4:
                return FOUR.Description;
            case 5:
                return FIVE.Description;
            default:
                return "";
        }
    }


}

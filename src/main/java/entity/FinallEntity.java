package entity;

import lombok.Data;
import utils.CheckpointDescEnum;
import utils.CheckpointResOne;
import utils.CheckpointResThree;
import utils.CheckpointResTwo;

@Data
public class FinallEntity {

    private String step;

    private String value;

    public FinallEntity() {
    }

    public FinallEntity(String step, String value) {
        this.step = step;
        this.value = value;
    }

    /**
     * @Author: zues
     * @Description: //根据数据获取对应描述
     * @Date: 2021/11/22 19:57
     * @Params: [java.lang.Integer, java.lang.Integer]
     * @ResultType: entity.FinallEntity
     */
    public FinallEntity getData(Integer stepType, Integer value){
        switch (stepType){
            case 1:
                return new FinallEntity(CheckpointDescEnum.getDesc(stepType), CheckpointResTwo.getButton1(value));
            case 2:
                return new FinallEntity(CheckpointDescEnum.getDesc(stepType), CheckpointResTwo.getButton1(value));
            case 3:
                return new FinallEntity(CheckpointDescEnum.getDesc(stepType), CheckpointResOne.getButton1(value));
            case 4:
                return new FinallEntity(CheckpointDescEnum.getDesc(stepType), CheckpointResTwo.getButton1(value));
            case 5:
                return new FinallEntity(CheckpointDescEnum.getDesc(stepType), CheckpointResThree.getButton1(value));
            default:
                return new FinallEntity("","");
        }
    }
}

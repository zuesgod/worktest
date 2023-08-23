package entity.timeTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zeus
 * @date 2022-09-05 10:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataHistoryMini {
    private String meterId;
    private String meterValue;
    private String meterDatetime;
    private String meterName;
    private String pointTypeName;
    private String pointTypeUnit;
    private String pointTypeKey;
    private Integer chartGroup;
}

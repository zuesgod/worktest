package streamtest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zeus
 * @date 2022-06-13 10:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemSnapshot {

    private Long id;

    private List<String> type;

}

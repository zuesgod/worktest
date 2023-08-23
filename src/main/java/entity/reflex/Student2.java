package entity.reflex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zeus
 * @date 2022-06-08 14:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {

    private String name;
    private List<Integer> nos;
    private Integer age;
    private Long id;

}

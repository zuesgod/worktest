package entity;

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
public class Student {

    private String name;
    private Integer age;
    private Long id;
}

package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class TreeEntity {
    private Integer id;
    private String name;
    private Integer pid;
    private List<TreeEntity> child = new ArrayList<TreeEntity>();

    public TreeEntity(Integer id, String name, Integer pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }
}

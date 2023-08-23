package filetest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 压缩包实体
 *
 * @author zeus
 * @date 2023-04-16 14:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZipEntity {

    private String url;
    private String name;

}

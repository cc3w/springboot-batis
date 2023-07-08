package cc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    //当前的页码
    private Integer pageNum = 1;

    //每一页所显示的数量
    private Integer pageSize = 4;

    //根据用户查询
    private String name;
}

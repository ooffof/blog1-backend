package top.cuizilin.website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleQuery {

    private Integer searchType;
    private String searchContent;
    private Integer orderContent;
    private Boolean orderType;
    private Integer pageNo = 1;
    private Integer pageSize;
}

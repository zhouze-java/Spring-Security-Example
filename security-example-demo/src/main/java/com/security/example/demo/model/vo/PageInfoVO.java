package com.security.example.demo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(Include.NON_NULL)
@ApiModel(description = "分页对象")
@Data
public class PageInfoVO<T> {
	
	@ApiModelProperty(value="当前页")
    private int pageNum = 1;
	
	@ApiModelProperty(value="每页的数量",notes="默认值10", example="10")
    private int pageSize = 10;
	
    @ApiModelProperty(value="总记录数")
    private long total;
    
    @ApiModelProperty(value="总页数")
    private int pages;
    
    @ApiModelProperty(value="结果集")
    private List<T> list;

}

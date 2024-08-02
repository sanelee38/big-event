package com.sanelee.bigevent.pojo;

import com.sanelee.bigevent.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

@Data
public class Article {
    /**
     * ID
     */
    private Integer id;
    /**
     * 文章标题
     */
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;
    /**
     * 文章内容
     */
    @NotEmpty
    private String content;
    /**
     * 文章封面
     */
    @NotEmpty
    @URL
    private String coverImg;
    /**
     * 文章状态：只能是【已发布】或者【草稿】
     */
    @State
    private String state;
    /**
     * 文章分类ID
     */
    @NotNull
    private Integer categoryId;
    /**
     * 创建人ID
     */
    private Integer createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}


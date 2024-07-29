package com.sanelee.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

@Data
public class Category {

    /**
     * ID
     */
    @NotNull(groups = Update.class)
    private Integer id;
    /**
     * 分类名称
     */
    @NotEmpty//(groups = {Add.class, Update.class})
    private String categoryName;
    /**
     * 分类别名
     */
    @NotEmpty//(groups = {Add.class, Update.class})
    private String categoryAlias;
    /**
     * 创建人ID
     */
    private Integer createUser;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }

}


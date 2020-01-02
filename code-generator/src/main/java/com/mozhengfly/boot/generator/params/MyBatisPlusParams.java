package com.mozhengfly.boot.generator.params;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatisPlusParams
 * @Description MyBatisPlus代码自动生成参数  注意 本类使用的是动态数据源模型
 * @Author wangchonglin
 * @Date 2020-01-02 11:25:41
 * @Version 1.0.0
 */
@Setter
@Getter
public class MyBatisPlusParams {

    /**
     * 数据源名称
     */
    private String model;

    /**
     * 数据源配置
     */
    private DataSourceProperty dataSourceProperty;

    /**
     * 作者
     */
    private String author = "mozhengfly";

    /**
     * 包名称
     */
    private String packageName = "com.mozhengfly.boot.web";

    /**
     * 输出目录
     */
    private String outputDir = new File("").getAbsolutePath() + "/src/main/java";

    /**
     * 是否覆盖
     */
    private boolean fileOverride = false;

    /**
     * ActiveRecord 模式
     */
    private boolean activeRecord = true;

    /**
     * xml 二级缓存
     */
    private boolean enableCache = false;

    /**
     * xml baseResultMap
     */
    private boolean baseResultMap = true;

    /**
     * xml baseColumnList
     */
    private boolean baseColumnList = true;

    /**
     * Swagger2
     */
    private boolean swagger2 = true;

    /**
     * 默认不打开目录
     */
    private boolean open = false;

    /**
     * 填充字段
     */
    private List<TableFill> tableFillList = getDefaultTableFillList();

    /**
     * lombok
     */
    private boolean entityLombokModel = true;

    /**
     * moduleName
     */
    private String moduleName = "business";

    /**
     * 是否生成 xml
     */
    private boolean generateXml = true;

    /**
     * 是否生成 entity
     */
    private boolean generateEntity = true;

    /**
     * 是否生成 mapper
     */
    private boolean generateMapper = true;

    /**
     * 是否生成 service
     */
    private boolean generateService = true;

    /**
     * 是否生成 controller
     */
    private boolean generateController = true;

    private List<TableFill> getDefaultTableFillList() {
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        return tableFillList;
    }
}

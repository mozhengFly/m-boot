package com.mozhengfly.boot.generator;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.mozhengfly.boot.generator.params.MyBatisPlusParams;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * MyBatisPlusGenerator
 *
 * @Description 代码生成器
 * @Author wangchonglin
 * @Date 2019-12-31 15:18:22
 * @Version 1.0.0
 */
@Setter
public class MyBatisPlusGenerator {

    private MyBatisPlusParams myBatisPlusParams;

    public MyBatisPlusGenerator(MyBatisPlusParams myBatisPlusParams) {
        this.myBatisPlusParams = myBatisPlusParams;
    }

    /**
     * 生成代码
     */
    public void generateCode() {
        AutoGenerator mpg = new AutoGenerator();
        setGlobalConfig(mpg);
        setDataSourceConfig(mpg, myBatisPlusParams.getDataSourceProperty());
        setStrategy(mpg);
        setPackageInfo(mpg);
        setCfg(mpg);
        setTemplate(mpg);
        mpg.execute();
    }

    /**
     * 配置全局变量
     *
     * @param mpg
     */
    private void setGlobalConfig(AutoGenerator mpg) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 输出目录
        gc.setOutputDir(myBatisPlusParams.getOutputDir());
        // 是否覆盖文件
        gc.setFileOverride(myBatisPlusParams.isFileOverride());
        // 开启 activeRecord 模式
        gc.setActiveRecord(myBatisPlusParams.isActiveRecord());
        // XML 二级缓存
        gc.setEnableCache(myBatisPlusParams.isEnableCache());
        // XML ResultMap
        gc.setBaseResultMap(myBatisPlusParams.isBaseResultMap());
        // XML columnList
        gc.setBaseColumnList(myBatisPlusParams.isBaseColumnList());
        // swagger
        gc.setSwagger2(myBatisPlusParams.isSwagger2());
        // 不打开目录
        gc.setOpen(myBatisPlusParams.isOpen());
        // 作者
        gc.setAuthor(myBatisPlusParams.getAuthor());

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceImplName("%sService");
        gc.setControllerName("%sController");
        // 暂时用这个替换DS注解的位置 因为不生成service的接口 所以这个就没啥用 暂时这样用了
        gc.setServiceName(myBatisPlusParams.getModel());
        mpg.setGlobalConfig(gc);
    }

    /**
     * 设置数据源
     *
     * @param mpg
     */
    private void setDataSourceConfig(AutoGenerator mpg, DataSourceProperty dataSourceProperty) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(dataSourceProperty.getDriverClassName());
        dsc.setUrl(dataSourceProperty.getUrl());
        dsc.setUsername(dataSourceProperty.getUsername());
        dsc.setPassword(dataSourceProperty.getPassword());
        mpg.setDataSource(dsc);
    }

    /**
     * 设置策略
     *
     * @param mpg
     */
    private void setStrategy(AutoGenerator mpg) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 自定义需要填充的字段
        strategy.setTableFillList(myBatisPlusParams.getTableFillList());
        strategy.setTablePrefix("t");
        strategy.setEntityLombokModel(myBatisPlusParams.isEntityLombokModel());
        strategy.setRestControllerStyle(true);
        // 增加TableFiled字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
    }

    /**
     * 设置包路径
     *
     * @param mpg
     */
    private void setPackageInfo(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setParent(myBatisPlusParams.getPackageName() + "." + myBatisPlusParams.getModel());
        pc.setModuleName(myBatisPlusParams.getModuleName());
        pc.setMapper("mapper");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setServiceImpl("service");
        mpg.setPackageInfo(pc);
    }

    /**
     * 设置配置信息
     *
     * @param mpg
     */
    private void setCfg(AutoGenerator mpg) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("mozhengfly", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);
    }

    /**
     * 设置模板
     *
     * @param mpg
     */
    private void setTemplate(AutoGenerator mpg) {
        TemplateConfig template = new TemplateConfig();
        // 不生成 xml
        if (!myBatisPlusParams.isGenerateXml()) {
            template.setXml(null);
        }
        // 不生成 mapper
        if (!myBatisPlusParams.isGenerateMapper()) {
            template.setMapper(null);
        }
        // 不生成 service
        if (!myBatisPlusParams.isGenerateService()) {
            template.setServiceImpl(null);
        }
        // 不生成 entity
        if (!myBatisPlusParams.isGenerateEntity()) {
            template.setEntity(null);
        }
        // 不生成 controller
        if (!myBatisPlusParams.isGenerateController()) {
            template.setController(null);
        }
        template.setService(null);
        mpg.setTemplate(template);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    }

}

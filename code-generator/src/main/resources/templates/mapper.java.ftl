package ${package.Mapper};

import com.baomidou.dynamic.datasource.annotation.DS;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 * ${table.mapperName}
 * @Description ${table.comment!} Mapper 接口
 * @Author ${author}
 * @Date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 * @Version ${version!'1.0.0'}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@DS("${table.serviceName}")
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>

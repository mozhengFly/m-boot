package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * ${table.serviceImplName}
 * @Description ${table.comment!} Service服务
 * @Author ${author}
 * @Date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 * @Version ${version!'1.0.0'}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>() {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> {

}
</#if>

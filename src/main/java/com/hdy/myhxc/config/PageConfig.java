package com.hdy.myhxc.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author m760384371
 * @date 2019/8/29
 */
@Configuration
public class PageConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        // 默认值为 false ，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为 true 时，会将 RowBounds 中的 offset 参数当成 pageNum 使用，
        // 可以用页码和页面大小两个参数进行分页。
        p.setProperty("offsetAsPageNum", "true");
        // 默认值为 false ，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为 true 时，使用 RowBounds 分页会进行 count 查询。
        p.setProperty("rowBoundsWithCount", "true");
        // 分页合理化参数，默认值为 false 。当该参数设置为 true 时， pageNum<=0 时会查询第一页， pageNum>pages （超过总数时），会查询最后一页。
        // 默认 false 时，直接根据参数进行查询。
        p.setProperty("reasonable", "true");
        // 分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。 你可以配置helperDialect 属性来指定分页插件使用哪种方言。
        p.setProperty("dialect", "mysql");
        // 支持通过 Mapper 接口参数来传递分页参数，默认值 false ，分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值，
        // 查找到合适的值时就会自动分页。
        p.setProperty("supportMethodsArguments", "false");
        // 默认值为 false ，当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是 Page 类型）。
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}

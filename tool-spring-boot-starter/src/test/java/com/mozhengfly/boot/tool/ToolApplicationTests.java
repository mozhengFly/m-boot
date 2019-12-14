package com.mozhengfly.boot.tool;

import com.mozhengfly.boot.tool.util.SpringContextUtil;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolApplicationTests {

    @Autowired
    private SpringContextUtil util;

    @Test
    public void array_list_should_be_collection() {
        List<String> a = new ArrayList<>();
        Assert.assertEquals(true, a instanceof Collection);
    }

    @Test
    public void array_should_be_object() {
        String[] a = new String[20];
        Assert.assertEquals(true, a instanceof String[]);
    }

}

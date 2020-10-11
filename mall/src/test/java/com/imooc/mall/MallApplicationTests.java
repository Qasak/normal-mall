package com.imooc.mall;

import com.imooc.mall.dao.CategoryMapper;
import com.imooc.mall.pojo.Category;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@MapperScan(basePackages = "com.imooc.mall.dao")
@SpringBootTest
class MallApplicationTests {
    @Autowired
    private CategoryMapper categoryMapper;
    @Test
    public void contextLoads() {
        Category category = categoryMapper.findById(100001);
        System.out.println(category.toString());
    }
    @Test
    public void queryByIdTest() {
        Category category = categoryMapper.queryById(100002);
        System.out.println(category.toString());
    }
}

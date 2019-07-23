package com.light.sword;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {

    @Select("select * from product")
    List<Product> findAll();

}

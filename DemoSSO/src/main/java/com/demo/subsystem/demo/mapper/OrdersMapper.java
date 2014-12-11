package com.demo.subsystem.demo.mapper;

import java.util.List;

import com.demo.subsystem.common.BaseMapper;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: dept表映射器 </p> 
 * <p>Author:aaron</p>
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    
    List<User> findLeftJoin();
    

}
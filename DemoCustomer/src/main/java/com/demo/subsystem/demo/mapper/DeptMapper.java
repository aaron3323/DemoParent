package com.demo.subsystem.demo.mapper;

import com.demo.subsystem.common.BaseMapper;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: dept表映射器 </p> 
 * <p>Author:aaron</p>
 */
public interface DeptMapper extends BaseMapper<Dept> {
    
    Dept findById();
    
    Dept findOneToMany(String id);
    

}
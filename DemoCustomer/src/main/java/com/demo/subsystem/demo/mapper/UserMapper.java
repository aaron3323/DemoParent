package com.demo.subsystem.demo.mapper;

import java.util.List;

import com.demo.subsystem.common.BaseMapper;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Description: user表映射器 </p> 
 * <p>Author:aaron</p>
 */
public interface UserMapper extends BaseMapper<User> {
    int insertBatch(List<User> list);
    
    List<User> findLeftJoin();
    
    User findOneToOne(String id);

    User findByDeptId(String deptId);
}
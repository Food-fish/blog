package com.jijidom.Administration.dao.read;

import com.jijidom.Administration.entity.Permission;

import java.util.List;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 11:13 2018/6/5
 * @Description:
 */
public interface PermissionMapper {
    List<Permission> findByAdminUserId(String userId);
    List<Permission> getAll();
}

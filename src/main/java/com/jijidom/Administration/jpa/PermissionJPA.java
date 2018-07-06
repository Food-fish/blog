package com.jijidom.Administration.jpa;

import com.jijidom.Administration.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 10:51 2018/6/5
 * @Description:
 */
public interface PermissionJPA extends JpaRepository<Permission,Long>,JpaSpecificationExecutor<Permission>,Serializable {
}

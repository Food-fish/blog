package com.jijidom.Administration.jpa;

import com.jijidom.Administration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 15:30 2018/6/5
 * @Description:
 */
public interface RoleJPA extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role>,Serializable {
}

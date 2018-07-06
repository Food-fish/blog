package com.jijidom.Administration.jpa;

import com.jijidom.Administration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 19:58 2018/5/29
 * @Description:
 */
public interface UserJPA extends JpaRepository<User,Long>,JpaSpecificationExecutor<User>,Serializable {
}

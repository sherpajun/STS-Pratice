package com.cdj.blog.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{ //DB오류로 현재 비활성화 상태 import 안함

}

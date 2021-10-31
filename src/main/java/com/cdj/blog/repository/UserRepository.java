package com.cdj.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cdj.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{ 

}

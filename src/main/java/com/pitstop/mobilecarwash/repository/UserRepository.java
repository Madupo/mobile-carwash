package com.pitstop.mobilecarwash.repository;


import com.pitstop.mobilecarwash.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Emmie on 2017/04/07.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailAddress(String emailAddress);
    User findById(long id);

}

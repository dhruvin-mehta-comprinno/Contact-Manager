package com.comp.crud.repo;

import com.comp.crud.entity.Contact;
import com.comp.crud.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username =:username")
    public User getUserByUserName(@Param("username") String username);
    @Query("select c from Contact c where c.uid =:uid")
    public List<Contact> findContacts(@Param("uid") Long uid);
    public User findByUsername(String username);

    public User findByUid(Long uid);
    public User findByUsernameAndPassword(String username, String password);

//    List<Contact> findByContacts(User user);
    Boolean existsByUsername(String username);

}

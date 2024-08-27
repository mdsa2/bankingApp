package com.BankingApp.mohammed.User.Repositry;

import com.BankingApp.mohammed.User.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<user,Integer> {
    Optional<user> findByEmail(String Email);
}

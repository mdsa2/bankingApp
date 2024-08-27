package com.BankingApp.mohammed.User.Repositry;

import com.BankingApp.mohammed.User.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepositry extends JpaRepository<Permission, Integer> {
}

package com.sunil.customer.laundrySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunil.customer.laundrySystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String userName);

}

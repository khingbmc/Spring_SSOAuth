package com.saml.idp.repositories;

import com.saml.idp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, Long> {
}

package com.carteur.gestionprs.repositories;
import com.carteur.gestionprs.users.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}
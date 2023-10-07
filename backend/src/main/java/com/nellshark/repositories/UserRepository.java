package com.nellshark.repositories;

import com.nellshark.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomGenericRepository<User, Long> {

}

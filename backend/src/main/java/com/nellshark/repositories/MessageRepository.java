package com.nellshark.repositories;

import com.nellshark.models.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CustomGenericRepository<Message, Long> {

}

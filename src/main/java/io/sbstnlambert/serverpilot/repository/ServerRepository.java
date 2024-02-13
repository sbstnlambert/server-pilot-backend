package io.sbstnlambert.serverpilot.repository;

import io.sbstnlambert.serverpilot.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}

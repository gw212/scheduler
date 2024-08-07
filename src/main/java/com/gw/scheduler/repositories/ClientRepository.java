package com.gw.scheduler.repositories;

import com.gw.scheduler.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer>
{
}

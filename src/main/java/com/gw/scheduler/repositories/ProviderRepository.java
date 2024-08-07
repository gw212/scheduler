package com.gw.scheduler.repositories;

import com.gw.scheduler.entities.Provider;
import com.gw.scheduler.entities.ProviderAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer>
{
}
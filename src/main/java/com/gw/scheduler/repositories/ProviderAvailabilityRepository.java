package com.gw.scheduler.repositories;

import com.gw.scheduler.entities.ProviderAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderAvailabilityRepository extends JpaRepository<ProviderAvailability, Integer>
{
	@Query(nativeQuery = true,
		   value = "SELECT avail.* FROM scheduler.provider_availability avail LEFT JOIN scheduler"
				   + ".provider_appointments app ON avail.record_id = app.record_id "
				   + " WHERE app.record_id IS NULL AND avail.is_active = true AND "
				   + " ((DATE 'epoch' + avail.day_of_year * INTERVAL '1 day' +avail.year * INTERVAL '1 year' + "
				   + " avail.start_time ::time - INTERVAL '1 day')::timestamp " + " > CURRENT_TIMESTAMP)")
	List<ProviderAvailability> findAllByAvailability();

}
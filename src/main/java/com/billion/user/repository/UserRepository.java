package com.billion.user.repository;

import com.billion.user.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {

	// Custom query with country and id as the primary key
	@Query("SELECT * FROM users WHERE country = ?0 AND id = ?1 LIMIT 1")
	Optional<User> findByCountryAndId(String country, UUID id);

	@Query("SELECT * FROM users WHERE country = ?0")
	Optional<List<User>> findByCountry(String country);

}

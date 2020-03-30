package com.biservices.wakalni.wakalni.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biservices.wakalni.wakalni.persistence.entities.user.PlatformUser;

@Repository
public interface UserRepository extends JpaRepository<PlatformUser, Long> {
	public PlatformUser findByUsername(String userName);
	public PlatformUser findByUsernameAndPassword(String userName, String password);
}

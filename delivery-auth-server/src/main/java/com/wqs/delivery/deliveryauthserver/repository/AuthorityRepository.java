package com.wqs.delivery.deliveryauthserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.wqs.delivery.deliveryauthserver.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String>{
	
	Authority findByName(String name);

}

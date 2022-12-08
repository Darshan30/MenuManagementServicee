package com.amrita.menu.service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.amrita.menu.service.model.MessMenu;

@Repository
public interface MessRepository extends JpaRepository<MessMenu, Long> {
	
	List<MessMenu> findAll();

}

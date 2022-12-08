package com.amrita.menu.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrita.menu.service.model.MainCanteenMenu;

@Repository
public interface MainCanteenRepository extends JpaRepository<MainCanteenMenu, Long> {
	
	List<MainCanteenMenu> findAll();

}

package com.amrita.menu.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrita.menu.service.model.MbaCanteenMenu;

@Repository
public interface MbaCanteenRepository extends JpaRepository<MbaCanteenMenu, Long> {

	List<MbaCanteenMenu> findAll();
}

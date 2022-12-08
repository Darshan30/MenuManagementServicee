package com.amrita.menu.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrita.menu.service.model.ITCanteenMenu;
import com.amrita.menu.service.model.User;

@Repository
public interface ITCanteenRepository extends JpaRepository<ITCanteenMenu, Long> {

	List<ITCanteenMenu> findAll();
}

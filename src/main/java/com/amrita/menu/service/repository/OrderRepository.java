package com.amrita.menu.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrita.menu.service.model.MessMenu;
import com.amrita.menu.service.model.OrderList;

@Repository
public interface OrderRepository extends JpaRepository<OrderList, Long> {
		
	OrderList findByRollNo(String rollNo);
	
	Boolean existsByRollNo(String rollNo);

}

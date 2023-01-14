package com.amrita.menu.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrita.menu.service.model.OrderList;
import com.amrita.menu.service.model.OrderMenuList;

@Repository
public interface OrderMenuListRepository extends JpaRepository<OrderMenuList, Long> {

}

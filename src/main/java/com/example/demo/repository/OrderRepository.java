package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Modifying
    @Query(value = "insert into orders (id_user, id_cart, name_emp, tel_Emp, address_emp, city_emp)"
    		+ " VALUES (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    @Transactional
    void insertOrder(int idUser, int idCart, String nameEmp, String telEmp, String addressEmp, String cityEmp);

	@Query(value = "select * from orders o where o.id_user = ?1", nativeQuery = true)
	List<Order> findAllByIdUser(int idUser);

}

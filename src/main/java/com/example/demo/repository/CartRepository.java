package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	@Query(value = "select id from cart u where u.id_user = ?1", nativeQuery = true)
	List<Integer> findMaxId(int idUser);

	
	Optional<Cart> findByIdAndIdUser(int id, int idUser);
	
	@Modifying
    @Query(value = "insert into cart (id_user) VALUES (?1)", nativeQuery = true)
    @Transactional
    void insertNewCart(int idUser);
}

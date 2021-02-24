package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Item;

@Repository
@EnableTransactionManagement
public interface ItemRepository extends JpaRepository<Item, Integer>{

	
	@Modifying
    @Query(value = "insert into detail_cart (id_product, quantity, guarantee, id_cart) VALUES (?1,?2,?3,?4)", nativeQuery = true)
    @Transactional
    void insertItem(int idProduct, int quantity, String guarantee, int idCart);

	@Modifying
	@Query(value = "delete from detail_cart i where i.id_detail =:id", nativeQuery = true)
	@Transactional
	int deleteItemByID(@Param("id") int id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update detail_cart i set i.quantity =:quantity, i.guarantee =:guarantee where i.id_detail =:id", nativeQuery = true)
	@Transactional
	void updateItem(@Param("id") int id, @Param("quantity") int quantity, @Param("guarantee") String guarantee);
}

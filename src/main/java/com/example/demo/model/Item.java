package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2253418052757484423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detail")
	private Integer idDetail;
	
	
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "guarantee")
	private String guarantee;

	
	@ManyToOne
	@JoinColumn(name = "id_cart")
	private Cart cart;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_product")
	private Product product;
}

package com.example.demo.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4873230708414285980L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "type")
	private String type;

	@Column(name = "src_img")
	private String src_img;

	@OneToOne(targetEntity = Item.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Item productincart;
	
	public String getSPrice() {
		return moneyToString(this.price);
	}
	
	
	public String moneyToString(long Money) {
        String s = String.valueOf(Money);
        String res = "";
        int addDot = 0;
        
        for (int i = s.length(); i>0; i--){
            res = s.substring(i-1, i) + res;
            if (((res.length() - addDot) %3 == 0 ) && (i != 1)) {
                res = "." + res; 
                addDot ++;
            }
            
        }
        return res;
    }
}

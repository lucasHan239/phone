package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 125804807943516722L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_user")
	private int idUser;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Item> items = new ArrayList<>();

	
	public String getTotalCost() {
		long res = 0;
		
		for (Item item: items) {
			res += item.getProduct().getPrice() * item.getQuantity();
			if (item.getGuarantee().equals("iCare+")) {
				res += item.getQuantity() * 400000;
			}
		}
		
		
		return moneyToString(res);
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

package com.example.demo.model;
// Generated Jul 2, 2020 5:29:35 PM by Hibernate Tools 5.4.14.Final

import java.io.Serializable;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 121643277796924886L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;




}

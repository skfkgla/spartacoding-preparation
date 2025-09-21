package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Food> foods = new ArrayList<>();

	public List<Food> getFoodList() {
		return foods;
	}

	public void addFoodList(Food food) {
		foods.add(food);
		food.setUser(this);	//외래키 설정
	}

	// @OneToMany(mappedBy = "user")
	// private List<Order> orderList = new ArrayList<>();
}
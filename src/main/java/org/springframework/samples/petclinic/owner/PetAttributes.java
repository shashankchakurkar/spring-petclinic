package org.springframework.samples.petclinic.owner;

import org.springframework.samples.petclinic.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
/**
 * java pojo class to represent the pet attributes 
 */
@Entity
@Table(name = "pet_attributes")
public class PetAttributes extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "colour")
	@NotBlank
	private String colour;
	
	@Column(name = "height")
	@NotBlank
	private int height;
	
	@Column(name = "weight")
	@NotBlank
	private int weight;
	
	
	@Column(name = "age")
	@NotBlank
	private int age;

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	
	
	
}

package com.example.demo.dto;

import java.util.*;

import com.example.demo.entity.Category;

public class CategoryDTO {
	private Integer id;
	private String name;
	private Date created_at;
	private Date updated_at;
	
	public CategoryDTO() {}
	
	public CategoryDTO(Integer id, String name, Date created_at, Date updated_at) {
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.created_at = category.getCreated_at();
		this.updated_at = category.getUpdated_at();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ "]";
	}
	
}

package com.jent.service;

import java.util.List;

import com.jent.bean.Category;
import com.jent.communication.CategoryExApi;

public class CategoryService {
	public List<Category> getCategroyList(){
		return new CategoryExApi().accessCategroy();
	}
}

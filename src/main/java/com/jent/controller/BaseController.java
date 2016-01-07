package com.jent.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jent.bean.Category;
import com.jent.bean.Company;
import com.jent.repository.CompanyRepository;
import com.jent.service.CategoryService;

@Controller
public class BaseController {
	
	@RequestMapping(value ="company/{code}",method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	public ModelMap getCompanyByCode(@PathVariable String code){
		Company c = new CompanyRepository().getCompanyByCode(code);
		ModelMap resultMap = new ModelMap();
		resultMap.put("Company", c);
		return resultMap;
	}
	
	@RequestMapping(value ="companiest",method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	public ModelMap companyList(){
		ModelMap resultMap = new ModelMap();
		resultMap.put("list", new CompanyRepository().getCompanyList());
		return resultMap;
	}
	
	@RequestMapping(value ="/categories",method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	public ModelMap CategoryList(){
		ModelMap resultMap = new ModelMap();
		resultMap.put("list", new CategoryService().getCategroyList());
		return resultMap;
	}
	
	@RequestMapping("/category/view")
	public String CategoryView(Model model){
		List<Category> list = new CategoryService().getCategroyList();
		model.addAttribute("list", list);
		return "category";
	}
}

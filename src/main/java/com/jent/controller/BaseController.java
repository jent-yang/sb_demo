package com.jent.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jent.bean.Company;
import com.jent.repository.CompanyRepository;
import com.jent.service.CategoryService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/company")
public class BaseController {

	@RequestMapping("hello")
	@ResponseBody
	public String hello(){
		return "hello springboot";
	}
	
	@RequestMapping(value ="/{code}",method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	public ModelMap getCompanyByCode(@PathVariable String code){
		Company c = new CompanyRepository().getCompanyByCode(code);
		ModelMap resultMap = new ModelMap();
		resultMap.put("Company", c);
		return resultMap;
	}
	
	@RequestMapping(value ="/list",method = RequestMethod.GET,
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
	
	public static void main(String[] args) {
		SpringApplication.run(BaseController.class, args);
	}

}

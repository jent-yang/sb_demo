package com.jent.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jent.bean.Category;
import com.jent.bean.Company;
import com.jent.repository.CompanyRepository;
import com.jent.service.CategoryService;

@Controller
public class BaseController {
	
	@RequestMapping(value ="company/{code}",method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ModelMap getCompanyByCode(@PathVariable String code){
		Company c = new CompanyRepository().getCompanyByCode(code);
		ModelMap resultMap = new ModelMap();
		resultMap.put("Company", c);
		return resultMap;
	}
	
	@RequestMapping(value ="companiest",method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ModelMap companyList(){
		ModelMap resultMap = new ModelMap();
		resultMap.put("list", new CompanyRepository().getCompanyList());
		return resultMap;
	}
	
	@RequestMapping(value ="/categories",method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ModelMap categoryList(){
		ModelMap resultMap = new ModelMap();
		resultMap.put("list", new CategoryService().getCategroyList());
		return resultMap;
	}
	
	@RequestMapping(value ="/company",method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ModelMap addCompany(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "code",required = true) String code){
		ModelMap resultMap = new ModelMap();
		Company c = new Company();
		c.setCompany_code(code);
		c.setCompany_name(name);
		resultMap.put("company", c);
		return resultMap;
	}
	
	@RequestMapping("/category/view")
	public String CategoryView(Model model){
		List<Category> list = new CategoryService().getCategroyList();
		Company c = new Company();
		c.setCompany_code("xrk");
		c.setCompany_name("xiangrikui");
		model.addAttribute("list", list);
		model.addAttribute("company", c);
		return "category";
	}
}

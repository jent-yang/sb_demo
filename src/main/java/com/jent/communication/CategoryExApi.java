package com.jent.communication;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jent.bean.Category;
import com.jent.common.HttpHelper;

public class CategoryExApi {
	private  Logger log = LoggerFactory.getLogger(CategoryExApi.class);
	
	public List<Category> accessCategroy(){
		List<Category> list = new ArrayList<Category>();
		String url = "http://192.168.6.192:7001/reader/categories";
		String response = "";
		try {
			response = HttpHelper.getData(url);
		} catch (IOException e) {
			log.error(MessageFormat.format("访问外连{0}，出错", url));
		}
		JSONObject result = JSONObject.parseObject(response);
		JSONArray array = result.getJSONArray("categories");
		for(int i = 0; i < array.size(); i++){
			JSONObject object = (JSONObject) array.getJSONObject(i);
			Category item = new Category();
			item.setId(object.getString("id"));
			item.setName(object.getString("name"));
			list.add(item);
		}
		return list;
	}
}

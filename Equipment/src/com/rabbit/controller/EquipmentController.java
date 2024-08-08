package com.rabbit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rabbit.dao.Equipment;
import com.rabbit.service.RainService;

@Controller
public class EquipmentController {
	@Autowired
	@Qualifier("RainService")
	private RainService rainservice;
	
	// 如果在目录下输入为空，则跳转到指定链接
	@RequestMapping(value="/equipment/")
	 public ModelAndView index2(ModelAndView mv){
		mv.setViewName("equipment/list");
		return mv;
	}
	// 如果在目录下输入任何不存在的参数，则跳转到list
	@RequestMapping(value="/equipment/{formName}")
	 public String index2(@PathVariable String formName){
//		return formName;
		String blank = "/equipment/list";
		return blank;
	}
	
	@RequestMapping(value="/equipment/list",method=RequestMethod.GET)
	 public String index(Model model,String content){
		List<Equipment> equipment_list = rainservice.findAllEquipment();
		if (content!=null){
			equipment_list = rainservice.findAllEquipment(content);
		}
		
		model.addAttribute("list",equipment_list);
		return "equipment/list";
	}

	//修改时用到的单体查询
	@RequestMapping(value="/equipment/add",method=RequestMethod.GET)
	 public String add(Model model,Integer id){
		if(id!=null){
			Equipment equipment = rainservice.get_Info(id);
			model.addAttribute("equipment",equipment);
		}
		return "/equipment/add";
	}

	//新增或修改
	@RequestMapping(value="/equipment/add",method=RequestMethod.POST)
	 public ModelAndView add(ModelAndView mv,@ModelAttribute Equipment equipment ,Integer id){
		if(id!=null){
			rainservice.update_Info(equipment);
		}else{
			rainservice.addEquipment(equipment);
		}
		mv.setViewName("redirect:/equipment/list");
		return mv;
	}


	@RequestMapping(value="/deleteequipment/{id}",method=RequestMethod.GET)
	 public void delete(@PathVariable Integer id){
		System.out.println(id);
		if(id!=null){
			rainservice.delete_Info(id);
		}
	}
}

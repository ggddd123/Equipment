package com.rabbit.dao;

import java.io.Serializable;

public class Chuku implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String equipmentname;
	private String time;
	private Integer shuliang;
	private String fuzeren;
	private String beizhu;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public Integer getShuliang() {
		return shuliang;
	}
	public void setShuliang(Integer shuliang) {
		this.shuliang = shuliang;
	}
	public String getFuzeren() {
		return fuzeren;
	}
	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}

package com.rabbit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rabbit.mapper.EquipmentMapper;
import com.rabbit.mapper.BaofeiMapper;
import com.rabbit.mapper.BaoxiuMapper;
import com.rabbit.mapper.CaigouMapper;
import com.rabbit.mapper.ChukuMapper;
import com.rabbit.mapper.RootMapper;
import com.rabbit.mapper.RukuMapper;
import com.rabbit.mapper.UserMapper;
import com.rabbit.dao.Equipment;
import com.rabbit.dao.Baofei;
import com.rabbit.dao.Baoxiu;
import com.rabbit.dao.Caigou;
import com.rabbit.dao.Chuku;
import com.rabbit.dao.Root;
import com.rabbit.dao.Ruku;
import com.rabbit.dao.User;
import com.rabbit.service.RainService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("RainService")
public class RainServiceImpl implements RainService{
	
	@Autowired
	private RootMapper rootdao;
	@Autowired
	private UserMapper userdao;
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private CaigouMapper caigoumapper;
	@Autowired
	private RukuMapper rukuMapper;
	@Autowired
	private ChukuMapper chukuMapper;
	@Autowired
	private BaoxiuMapper baoxiumapper;
	@Autowired
	private BaofeiMapper baofeimapper;

	/**
	 * 用户信息管理
	 */
	@Override
	public Root login(String loginname, String password) {
		Root root = rootdao.get_login(loginname,password);
		return root;
	}
	@Override
	public User login2(String loginname, String password) {
		return userdao.get_login(loginname,password);
	}
	@Override
	public List<User> get_UserList() {

		return userdao.get_List();
	}
	@Override
	public List<User> get_UserLikeList(String content) {

		return userdao.get_LikeList(content);
	}
	@Override
	public User get_UserInfo(Integer id) {

		return userdao.get_Info(id);
	}
	@Override
	public void insert_UserInfo(User notice) {

		userdao.insert_Info(notice);
	}
	@Override
	public void delete_UserInfo(Integer id) {

		userdao.delete_Info(id);
	}
	@Override
	public void update_UserInfo(User notice) {

		userdao.update_Info(notice);
	}
	
	/**
	 * 用户信息管理
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Equipment> findAllEquipment() {
		return equipmentMapper.selectAllEquipment();
	}
	@Override
	public List<Equipment> findAllEquipment(String content) {

		return equipmentMapper.selectLikeAllEquipment(content);
	}
	@Override
	public Equipment get_Info(Integer id) {

		Equipment equipment = equipmentMapper.get_Info(id);
		return equipment;
	}
	@Override
	public void addEquipment(Equipment equipment) {
		//查询是否是已有的资产
		String name = equipment.getName();
		Equipment equipment1 = equipmentMapper.selectByName(name);
		//判断传入的equipment是否与之相同
		if (equipment.getChangshang().equals(equipment1.getChangshang())&&
				equipment.getDaxiao().equals(equipment1.getDaxiao())&&
				equipment.getGuige().equals(equipment1.getGuige())&&
				(equipment1.getDel()==0)){

			Integer kucun = equipment.getKucun()+equipment1.getKucun();
			equipment1.setKucun(kucun);
			equipmentMapper.update_Info(equipment1);
			return;
		}


		equipmentMapper.save(equipment);
	}
	@Override
	public void delete_Info(Integer id) {

		equipmentMapper.delete_Info(id);
	}
	@Override
	public void baofei_Info(String equipmentname) {

		equipmentMapper.baofei_Info(equipmentname);
	}
	@Override
	public void update_Info(Equipment equipment) {

		equipmentMapper.update_Info(equipment);
	}
	/**
	 * 采购信息管理
	 */
	@Override
	public List<Caigou> findAllCaigou() {
		return caigoumapper.selectAllCaigou();
	}
	@Override
	public List<Caigou> findAllCaigou(String content) {

		return caigoumapper.selectLikeAllCaigou(content);
	}
	@Override
	public Caigou get_CaigouInfo(Integer id) {

		Caigou caigou = caigoumapper.get_CaigouInfo(id);
		return caigou;
	}
	@Override
	public void addCaigou(Caigou caigou) {

		caigoumapper.save(caigou);
	}
	@Override
	public void delete_CaigouInfo(Integer id) {

		caigoumapper.delete_CaigouInfo(id);
	}
	@Override
	public void update_CaigouInfo(Caigou caigou) {

		caigoumapper.update_CaigouInfo(caigou);
	}
	/**
	 * 入库信息管理
	 */
	@Override
	public List<Ruku> findAllRuku() {
		return rukuMapper.selectAllRuku();
	}
	@Override
	public List<Ruku> findAllRuku(String content) {

		return rukuMapper.selectLikeAllRuku(content);
	}
	@Override
	public Ruku get_RukuInfo(Integer id) {

		Ruku ruku = rukuMapper.get_RukuInfo(id);
		return ruku;
	}
	@Override
	public void addRuku(Ruku ruku) {

		String equipmentname = ruku.getEquipmentname();
		Equipment equipment = equipmentMapper.selectByName(equipmentname);
		equipment.setKucun(equipment.getKucun()+ruku.getShuliang());

		equipmentMapper.update_Info(equipment);

		rukuMapper.save(ruku);

	}
	@Override
	public void delete_RukuInfo(Integer id) {
		rukuMapper.delete_RukuInfo(id);
	}
	@Override
	public void update_RukuInfo(Ruku ruku) {


		Integer id = ruku.getId();
		Ruku rukuInfo = rukuMapper.get_RukuInfo(id);

		String equipmentname = ruku.getEquipmentname();
		Equipment equipment = equipmentMapper.selectByName(equipmentname);
		equipment.setKucun(equipment.getKucun()+ruku.getShuliang()-rukuInfo.getShuliang());

		equipmentMapper.update_Info(equipment);

		rukuMapper.save(ruku);

		rukuMapper.update_RukuInfo(ruku);


		
		
	}
	/**
	 * 出库信息管理
	 */
	@Override
	public List<Chuku> findAllChuku() {
		return chukuMapper.selectAllChuku();
	}
	@Override
	public List<Chuku> findAllChuku(String content) {
		return chukuMapper.selectLikeAllChuku(content);
	}
	@Override
	public Chuku get_ChukuInfo(Integer id) {
		Chuku chuku = chukuMapper.get_ChukuInfo(id);
		return chuku;
	}
	@Override
	public void addChuku(Chuku chuku) {

		String equipmentname = chuku.getEquipmentname();
		Equipment equipment = equipmentMapper.selectByName(equipmentname);
		equipment.setKucun(equipment.getKucun()-chuku.getShuliang());

		equipmentMapper.update_Info(equipment);

		chukuMapper.save(chuku);

		chukuMapper.save(chuku);
	}
	@Override
	public void delete_ChukuInfo(Integer id) {
		chukuMapper.delete_ChukuInfo(id);
	}
	@Override
	public void update_ChukuInfo(Chuku chuku) {
		Integer id = chuku.getId();
		Chuku chukuInfo = chukuMapper.get_ChukuInfo(id);

		String equipmentname = chuku.getEquipmentname();
		Equipment equipment = equipmentMapper.selectByName(equipmentname);
		equipment.setKucun(equipment.getKucun()+chuku.getShuliang()-chukuInfo.getShuliang());

		equipmentMapper.update_Info(equipment);

		chukuMapper.save(chuku);

		chukuMapper.update_ChukuInfo(chuku);
	}
	/**
	 * 报修信息管理
	 */
	@Override
	public List<Baoxiu> findAllBaoxiu() {
		return baoxiumapper.selectAllBaoxiu();
	}
	@Override
	public Baoxiu get_BaoxiuInfo(Integer id) {
		Baoxiu baoxiu = baoxiumapper.get_BaoxiuInfo(id);
		return baoxiu;
	}
	@Override
	public void addBaoxiu(Baoxiu baoxiu) {
		baoxiumapper.save(baoxiu);
	}
	@Override
	public void delete_BaoxiuInfo(Integer id) {
		baoxiumapper.delete_BaoxiuInfo(id);
	}
	@Override
	public void update_BaoxiuInfo(Baoxiu baoxiu) {
		baoxiumapper.update_BaoxiuInfo(baoxiu);
	}
	/**
	 * 报废信息管理
	 */
	@Override
	public List<Baofei> findAllBaofei() {
		return baofeimapper.selectAllBaofei();
	}
	@Override
	public Baofei get_BaofeiInfo(Integer id) {
		Baofei baofei = baofeimapper.get_BaofeiInfo(id);
		return baofei;
	}
	@Override
	public void addBaofei(Baofei baofei) {
		baofeimapper.save(baofei);
	}
	@Override
	public void yes_BaofeiInfo(Integer id) {
		baofeimapper.yes_BaofeiInfo(id);
	}
	public void no_BaofeiInfo(Integer id) {
		baofeimapper.no_BaofeiInfo(id);
	}
	@Override
	public void update_BaofeiInfo(Baofei baofei) {
		baofeimapper.update_BaofeiInfo(baofei);
	}
	/**
	 * 员工信息的管理
	 */
//	@Override
//	public List<Employee> get_EmployeeList() {
//		/**
//		 * 将部门，职位id中的信息提取出来
//		 */
//		List<Employee> list = employeedao.get_List();
//		int size = list.size();
//		List<Employee> list2 = new ArrayList<>();
//		for(int i = 0;i<size;i++){
//			Employee data = list.get(i);
//			Equipment dept = deptDao.get_Info(data.getDept_id());
//			data.setDept(dept);
//			Job job = jobDao.get_Info(data.getJob_id());
//			data.setJob(job);
//			list2.add(i,data);
//		}
//		return list2;
//	}
//	@Override
//	public List<Employee> get_EmployeeLikeList(String content) {
//		/**
//		 * 将部门，职位id中的信息提取出来
//		 */
//		List<Employee> list = employeedao.get_LikeList(content);
//		int size = list.size();
//		List<Employee> list2 = new ArrayList<>();
//		for(int i = 0;i<size;i++){
//			Employee data = list.get(i);
//			Equipment dept = deptDao.get_Info(data.getDept_id());
//			data.setDept(dept);
//			Job job = jobDao.get_Info(data.getJob_id());
//			data.setJob(job);
//			list2.add(i,data);
//		}
//		return list2;
//	}
//	@Override
//	public Employee get_EmployeeInfo(Integer id) {
//		Employee data = employeedao.get_Info(id);
//		Equipment dept = deptDao.get_Info(data.getDept_id());
//		data.setDept(dept);
//		Job job = jobDao.get_Info(data.getJob_id());
//		data.setJob(job);
//		return data;
//	}
}

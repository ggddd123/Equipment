package com.rabbit.mapper;

import static com.rabbit.util.common.Constants.EQUIPMENTTABLE;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rabbit.mapper.provider.EquipmentDynaSqlProvider;
import com.rabbit.dao.Equipment;
public interface EquipmentMapper {
	//查询
	@Select("select * from "+EQUIPMENTTABLE+" where del=0")
	List<Equipment> selectAllEquipment();
	@Select("select * from "+EQUIPMENTTABLE+" where del=0 and name like CONCAT('%',#{content},'%')")
	List<Equipment> selectLikeAllEquipment(String content);
	
	
	@SelectProvider(type=EquipmentDynaSqlProvider.class,method="insertEquipment")
	void save(Equipment equipment);
	
	@Select("select * from "+EQUIPMENTTABLE+" where id = #{id}")
	Equipment get_Info(Integer id);

	@SelectProvider(type=EquipmentDynaSqlProvider.class,method="updateEquipment")
	void update_Info(Equipment Equipment);

	@Update(" update "+EQUIPMENTTABLE+" set del=1 where id = #{id} ")
	void delete_Info(Integer id);
	
	@Update(" update "+EQUIPMENTTABLE+" set del=1 where name = #{equipmentname} ")
	void baofei_Info(String equipmentname);

	@Select("select * from "+EQUIPMENTTABLE+" where name=#{name}")
	Equipment selectByName(String name);
}

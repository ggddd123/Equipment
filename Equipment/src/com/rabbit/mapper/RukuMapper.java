package com.rabbit.mapper;

import static com.rabbit.util.common.Constants.RUKUTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.rabbit.mapper.provider.RukuDynaSqlProvider;
import com.rabbit.dao.Ruku;
public interface RukuMapper {
	//查询
	@Select("select * from "+RUKUTABLE+" ")
	List<Ruku> selectAllRuku();
	@Select("select * from "+RUKUTABLE+" where equipmentname like CONCAT('%',#{content},'%')")
	List<Ruku> selectLikeAllRuku(String content);

	
	
	@SelectProvider(type=RukuDynaSqlProvider.class,method="insertRuku")
	void save(Ruku ruku);
	
	@Select("select * from "+RUKUTABLE+" where id = #{id}")
	Ruku get_RukuInfo(Integer id);

	@SelectProvider(type=RukuDynaSqlProvider.class,method="updateRuku")
	void update_RukuInfo(Ruku Ruku);

	@Delete(" delete from "+RUKUTABLE+" where id = #{id} ")
	void delete_RukuInfo(Integer id);

	
}

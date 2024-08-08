package com.rabbit.mapper;

import static com.rabbit.util.common.Constants.CHUKUTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.rabbit.mapper.provider.ChukuDynaSqlProvider;
import com.rabbit.dao.Chuku;
public interface ChukuMapper {
	//查询
	@Select("select * from "+CHUKUTABLE+" ")
	List<Chuku> selectAllChuku();
	@Select("select * from "+CHUKUTABLE+" where equipmentname like CONCAT('%',#{content},'%')")
	List<Chuku> selectLikeAllChuku(String content);
	
	
	@SelectProvider(type=ChukuDynaSqlProvider.class,method="insertChuku")
	void save(Chuku chuku);
	
	@Select("select * from "+CHUKUTABLE+" where id = #{id}")
	Chuku get_ChukuInfo(Integer id);

	@SelectProvider(type=ChukuDynaSqlProvider.class,method="updateChuku")
	void update_ChukuInfo(Chuku Chuku);

	@Delete(" delete from "+CHUKUTABLE+" where id = #{id} ")
	void delete_ChukuInfo(Integer id);

	
}

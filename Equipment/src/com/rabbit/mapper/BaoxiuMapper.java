package com.rabbit.mapper;

import static com.rabbit.util.common.Constants.BAOXIUTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.rabbit.mapper.provider.BaoxiuDynaSqlProvider;
import com.rabbit.dao.Baoxiu;
public interface BaoxiuMapper {
	//查询
	@Select("select * from "+BAOXIUTABLE+" ")
	List<Baoxiu> selectAllBaoxiu();

	@SelectProvider(type=BaoxiuDynaSqlProvider.class,method="insertBaoxiu")
	void save(Baoxiu baoxiu);
	
	@Select("select * from "+BAOXIUTABLE+" where id = #{id}")
	Baoxiu get_BaoxiuInfo(Integer id);

	@SelectProvider(type=BaoxiuDynaSqlProvider.class,method="updateBaoxiu")
	void update_BaoxiuInfo(Baoxiu Baoxiu);

	@Delete(" delete from "+BAOXIUTABLE+" where id = #{id} ")
	void delete_BaoxiuInfo(Integer id);

	
}

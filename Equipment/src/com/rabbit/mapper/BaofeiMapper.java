package com.rabbit.mapper;

import static com.rabbit.util.common.Constants.BAOFEITABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rabbit.mapper.provider.BaofeiDynaSqlProvider;
import com.rabbit.dao.Baofei;
public interface BaofeiMapper {
	//查询
	@Select("select * from "+BAOFEITABLE+" ")
	List<Baofei> selectAllBaofei();
	
	@SelectProvider(type=BaofeiDynaSqlProvider.class,method="insertBaofei")
	void save(Baofei baofei);
	
	@Select("select * from "+BAOFEITABLE+" where id = #{id}")
	Baofei get_BaofeiInfo(Integer id);

	@SelectProvider(type=BaofeiDynaSqlProvider.class,method="updateBaofei")
	void update_BaofeiInfo(Baofei Baofei);

	@Update(" update "+BAOFEITABLE+" set zhuangtai=1 where id = #{id} ")
	void yes_BaofeiInfo(Integer id);
	
	@Update(" update "+BAOFEITABLE+" set zhuangtai=0 where id = #{id} ")
	void no_BaofeiInfo(Integer id);

	
}

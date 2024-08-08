package com.rabbit.mapper;

import static com.rabbit.util.common.Constants.ROOTTABLE;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.rabbit.mapper.provider.RootDynaSqlProvider;
import com.rabbit.dao.Root;

public interface RootMapper {

	@Select("select * from "+ROOTTABLE+"  where loginname = #{loginname} AND password = #{password}")
	Root get_login(@Param("loginname") String loginname,
			@Param("password") String password);

	@SelectProvider(type=RootDynaSqlProvider.class,method="update_Root")
	void update_RootInfo(Root root);

}

package com.rabbit.mapper;

import static com.rabbit.util.common.Constants.USERTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rabbit.mapper.provider.UserDynaSqlProvider;
import com.rabbit.dao.User;

public interface UserMapper {

	@Select("select * from "+USERTABLE+" where del=0")
	List<User> get_List();
	@Select("select * from "+USERTABLE+"  where username like CONCAT('%',#{content},'%') and del=0")
	List<User> get_LikeList(String content);

	@Select("select * from "+USERTABLE+"  where loginname = #{loginname} AND password = #{password} and del=0")
	User get_login(@Param("loginname") String loginname,
			@Param("password") String password);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="insert_Notice")
	void insert_Info(User employee);
	
	@Select("select * from "+USERTABLE+" where id = #{id}")
	User get_Info(Integer id);

	@SelectProvider(type=UserDynaSqlProvider.class,method="update_Notice")
	void update_Info(User employee);

	@Update(" update "+USERTABLE+" set del=1 where id = #{id} ")
	void delete_Info(Integer id);

}

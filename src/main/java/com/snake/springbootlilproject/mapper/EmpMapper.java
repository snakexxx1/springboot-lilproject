package com.snake.springbootlilproject.mapper;

import com.snake.springbootlilproject.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {
    /*
    * 查询总记录数
    * */
   /* @Select("select count(*) from emp")
    public Long count();
    进行分页查询获取列表数据
    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> page(@Param("start") Integer start, @Param("pageSize") Integer pageSize);*/
    /*
    * 利用pagehelper实现查询总记录数
    * */
    //@Select("Select * from emp")
    public List<Emp> list(@Param("name") String name, @Param("gender")Short gender,
                          @Param("begin")LocalDate begin, @Param("end")LocalDate end);

    /*
    * 批量删除
    * */
    void delete(@Param("ids") List<Integer> ids);

    /*
     *
     * 新增员工
     * */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " Value (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    /*
    * 根据id查询
    * */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);


    /*
     * 修改员工
     * */
    void update(Emp emp);

    /*
     * 基础登录(根据用户名和密码查询)
     * */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /*
    * 根据部门ID删除该部门下的员工数据
    * */
    @Delete("delete from emp where dept_id =#{deptId}")
    void deleteByDeptId(Integer deptId);
}

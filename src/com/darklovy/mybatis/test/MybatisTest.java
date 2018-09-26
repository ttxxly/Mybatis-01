package com.darklovy.mybatis.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.darklovy.mybatis.bean.Employee;

public class MybatisTest {

	/**
	 * 1. 根據 xml 配置文件（全局配置文件）创建一个 SQLSessionFactory 对象 2. 创建 SQLSession 对象 3. 执行操作
	 * 4. 关闭 SQLSession 对象
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = sqlSessionFactory.openSession();

		try {
			/**
			 * 通过 com.darklovy.mybatis.EmployeeMapper.getEmpById 决定执行那个Sql
			 * namesapce.当前命名空间下的唯一标识 sql 保存在 映射文件中（EmployeeMapper.xml）
			 */
			Employee employee = openSession.selectOne("com.darklovy.mybatis.EmployeeMapper.getEmpById", 1);
			System.out.println(employee);
		} finally {
			openSession.close();
		}
	}
}

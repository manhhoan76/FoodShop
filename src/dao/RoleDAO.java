package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Role;

@Repository
public class RoleDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Role> getItems (){
		String sql ="select * from roles";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
	}
	public Role getItem (int idRole){
		String sql ="select * from roles where role_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idRole}, new BeanPropertyRowMapper<Role>(Role.class));
	}
}

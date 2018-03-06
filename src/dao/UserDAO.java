package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> getItems(int offset, int row_count) {
		String sql = "SELECT * FROM users  order by users.id desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getItems() {
		String sql = "SELECT * FROM users   order by id desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	public User getItem(int idU) {
		String sql = "SELECT * FROM users  where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idU }, new BeanPropertyRowMapper<User>(User.class));
	}
	public int editItem(User objUser) {
		String sql = "update users set  username=?, password=?, address=?, phone=?, email=?, image=?, create_at=? where id=? ";
		return jdbcTemplate.update(sql,
				new Object[] {objUser.getUsername(), objUser.getPassword(), objUser.getAddress(), objUser.getPhone(), objUser.getEmail(), objUser.getImage(), objUser.getCreate_at(), objUser.getId()  });
	}
	public int delItem(int idUser) {
		String sql = "delete from users	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idUser });
	}

	public int multildel(String result) {
		String sql = "delete from lands	where lid in ("+result+")";
		return jdbcTemplate.update(sql);
	}
	
	public int countSumUser() {
		String sql = "SELECT COUNT(*) AS sumUser FROM users   ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public String checkUsername (String username){
		String sql ="select username from users where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {username}, String.class);
	}
	public User getItem (String username){
		String sql ="select * from users where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {username},new BeanPropertyRowMapper<User>(User.class) );
	}
	public int addItem(User objUser) {
		String sql = "insert into users	(username, password, enable, email, phone, role_id, image) values (?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {objUser.getUsername(), objUser.getPassword(), objUser.getEnable(), objUser.getEmail(), objUser.getPhone(), objUser.getRole_id(), objUser.getImage()});
	}

	public int active(Timestamp date, int idUser) {
		String sql = "update users set enable=1,create_at=? where users.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idUser });
	}

	public int block(Timestamp date, int idUser) {
		String sql = "update users set enable=0,create_at=? where users.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idUser });
	}

	public List<User> getItemsSearch(String key, int offset, int row_count) {
		String sql = "SELECT  * FROM users  where username like '%"+key+"%'  || email like '%"+key+"%' order by id desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count },
				new BeanPropertyRowMapper<User>(User.class));
	}
	public int countSumSearch(String key) {
		String sql = "SELECT COUNT(*) AS sumpage FROM users  where username like '%"+key+"%'  || email like '%"+key+"%' ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}

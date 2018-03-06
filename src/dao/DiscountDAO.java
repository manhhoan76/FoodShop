package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Discount;

@Repository
public class DiscountDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Discount> getItems() {
		String sql = "select * from discount";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Discount>(Discount.class));
	}

	public List<Discount> getItems(int offset, int row_count) {
		String sql = "SELECT * FROM discount  order by discount.used desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Discount>(Discount.class));
	}

	public int delItem(int idDiscount) {
		String sql = "delete from discount	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idDiscount });
	}
	public Discount getItemName(String name) {
		String sql = "SELECT * FROM discount  where name like '"+name+"'";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Discount>(Discount.class));
	}
	public Discount getItem(String nameDiscount) {
		String sql = "SELECT * FROM discount  where name like ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { nameDiscount }, new BeanPropertyRowMapper<Discount>(Discount.class));
	}
	/*public int editItem(Discount objDiscount) {
		String sql = "update discount set name=?, created_at=?, description=? where id=? ";
		return jdbcTemplate.update(sql,
				new Object[] { objDiscount.getName(), objDiscount.getCreate_at(),objDiscount.getDescription(), objDiscount.getId()});
	}*/
	public int addItem(Discount objDiscount) {
		String sql = "insert into discount (name,percent) values(?,?) ";
		return jdbcTemplate.update(sql, new Object[] { objDiscount.getName(), objDiscount.getPercent() });
	}
	public int multildel(String result) {
		String sql = "delete from discount	where lid in (" + result + ")";
		return jdbcTemplate.update(sql);
	}
	public int used(Timestamp date, int id) {
		String sql = "update discount set used=1,create_at=? where id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, id});
	}

	public int countSumDiscount() {
		String sql = "SELECT COUNT(*) AS sumDiscount FROM discount";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}

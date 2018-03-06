package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.OrderStatus;

@Repository
public class OrderStatusDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderStatus> getItems(int offset, int row_count) {
		String sql ="SELECT * FROM order_statuses   order by id desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<OrderStatus>(OrderStatus.class));
	}

	public List<OrderStatus> getItems() {
		String sql = "SELECT * FROM order_statuses";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<OrderStatus>(OrderStatus.class));
	}

	public OrderStatus getItem(int idU) {
		String sql = "SELECT * FROM order_statuses  where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idU }, new BeanPropertyRowMapper<OrderStatus>(OrderStatus.class));
	}
	/*public int editItem(OrderStatus objOrderStatus) {
		String sql = "update order_statuses set  OrderStatusname=?, password=?, fullname=?, email=?, role_id=?, picture=?, date_create=? where id=? ";
		return jdbcTemplate.update(sql,
				new Object[] {objOrderStatus.getOrderStatusname(), objOrderStatus.getPassword(), objOrderStatus.getFullname(), objOrderStatus.getEmail(), objOrderStatus.getRole_id(), objOrderStatus.getPicture(), objOrderStatus.getDate_create(), objOrderStatus.getid()  });
	}*/
	public int delItem(int idOrderStatus) {
		String sql = "delete from order_statuses	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idOrderStatus });
	}

	public int multildel(String result) {
		String sql = "delete from lands	where lid in ("+result+")";
		return jdbcTemplate.update(sql);
	}
	
	public int countSumOrderStatus() {
		String sql = "SELECT COUNT(*) AS sumOrderStatus FROM order_statuses   ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public String checkOrderStatusname (String OrderStatusname){
		String sql ="select OrderStatusname from order_statuses where OrderStatusname=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {OrderStatusname}, String.class);
	}
	public OrderStatus getItem (String OrderStatusname){
		String sql ="select * from order_statuses where OrderStatusname=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {OrderStatusname},new BeanPropertyRowMapper<OrderStatus>(OrderStatus.class) );
	}
	/*public int addItem(OrderStatus objOrderStatus) {
		String sql = "insert into order_statuses	(OrderStatusname, password, fullname, enable, email, role_id, picture) values (?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {objOrderStatus.getOrderStatusname(), objOrderStatus.getPassword(), objOrderStatus.getFullname(), objOrderStatus.getEnable(), objOrderStatus.getEmail(), objOrderStatus.getRole_id(), objOrderStatus.getPicture()});
	}
*/
	public int active(Timestamp date, int idOrderStatus) {
		String sql = "update order_statuses set enable=1,create_at=? where order_statuses.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idOrderStatus });
	}

	public int block(Timestamp date, int idOrderStatus) {
		String sql = "update order_statuses set enable=0,create_at=? where order_statuses.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idOrderStatus });
	}

	public List<OrderStatus> getItemsSearch(String key, int offset, int row_count) {
		String sql = "SELECT lid, lname, description, date_create, lands.cid, picture, area, address, count_views, is_slide, categories.cname FROM lands INNER JOIN categories ON categories.cid=lands.cid where lands.lname like '%"+key+"%'  || lands.description like '%"+key+"%' OrderStatus by lid desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count },
				new BeanPropertyRowMapper<OrderStatus>(OrderStatus.class));
	}
	public int countSumSearch(String key) {
		String sql = "SELECT COUNT(*) AS sumpage FROM lands as l inner join categories as c on c.cid=l.cid where l.lname like '%"+key+"%'  || l.description like '%"+key+"%' ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}

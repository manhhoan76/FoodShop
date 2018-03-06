package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Order;

@Repository
public class OrderDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Order> getItems(int offset, int row_count) {
		String sql = "SELECT orders.id,id_user,id_order_status, order_statuses.name as status, address, username, phone, orders.create_at, SUM(quantity*price) as sumPrice, pay  FROM orders RIGHT JOIN order_details ON orders.id = order_details.id_order  INNER JOIN order_statuses on orders.id_order_status=order_statuses.id GROUP BY orders.id order by orders.id desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public List<Order> getItems() {
		String sql = "SELECT * FROM orders   order by id desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public List<Order> getItems(int id) {
		String sql = "SELECT orders.id,id_user,id_order_status, order_statuses.name as status, address, username, phone, orders.create_at, SUM(quantity*price) as sumPrice , pay FROM orders INNER JOIN order_details ON orders.id = order_details.id_order  INNER JOIN order_statuses on orders.id_order_status=order_statuses.id where orders.id_user =? GROUP BY orders.id order by orders.create_at desc";
		return jdbcTemplate.query(sql, new Object[] {id}, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public Order getItem(int idU) {
		String sql = "SELECT orders.id,id_user,id_order_status, order_statuses.name as status, address, username, phone, orders.create_at, SUM(quantity*price) as sumPrice, pay  FROM orders RIGHT JOIN order_details ON orders.id = order_details.id_order  INNER JOIN order_statuses on orders.id_order_status=order_statuses.id where orders.id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idU }, new BeanPropertyRowMapper<Order>(Order.class));
	}
	/*public int editItem(Order objOrder) {
		String sql = "update orders set  Ordername=?, password=?, fullname=?, email=?, role_id=?, picture=?, date_create=? where id=? ";
		return jdbcTemplate.update(sql,
				new Object[] {objOrder.getOrdername(), objOrder.getPassword(), objOrder.getFullname(), objOrder.getEmail(), objOrder.getRole_id(), objOrder.getPicture(), objOrder.getDate_create(), objOrder.getid()  });
	}*/
	public int delItem(int idOrder) {
		String sql = "delete from orders	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idOrder });
	}

	public int cancelItem(int idOrder) {
		String sql = "update orders set id_order_status=5 where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idOrder });
	}
	
	public int multildel(String result) {
		String sql = "delete from lands	where lid in ("+result+")";
		return jdbcTemplate.update(sql);
	}
	
	public int countSumOrder() {
		String sql = "SELECT COUNT(*) AS sumOrder FROM orders   ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public String checkOrdername (String Ordername){
		String sql ="select Ordername from orders where Ordername=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {Ordername}, String.class);
	}
	public Order getItem (String Ordername){
		String sql ="select * from orders where Ordername=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {Ordername},new BeanPropertyRowMapper<Order>(Order.class) );
	
	}
	
	public Order getItemByLastID (int idusser){
		String sql ="select * from orders where id_user=? order by id desc limit 1";
		return jdbcTemplate.queryForObject(sql, new Object[] {idusser},new BeanPropertyRowMapper<Order>(Order.class) );
	}
	
	public int addItem(Order objOrder) {
		String sql = "insert into orders	(id_user, id_order_status, address, username, phone, pay) values (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {objOrder.getId_user(), objOrder.getId_order_status(), objOrder.getAddress(), objOrder.getUsername(), objOrder.getPhone(), objOrder.getPay()});
	}

	public int active(Timestamp date, int idOrder) {
		String sql = "update orders set enable=1,create_at=? where orders.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idOrder });
	}
	
	public int change(Timestamp date, int idOrder, int id_status) {
		String sql = "update orders set id_order_status=?,create_at=? where orders.id=? ";
		return jdbcTemplate.update(sql, new Object[] {  id_status, date, idOrder });
	}
	
	public int block(Timestamp date, int idOrder) {
		String sql = "update orders set enable=0,create_at=? where orders.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idOrder });
	}

	public List<Order> getItemsSearch(int idStatus, int offset, int row_count) {
String sql ="SELECT orders.id,id_user,id_order_status, order_statuses.name as status, address, username, phone, orders.create_at, SUM(quantity*price) as sumPrice, pay  FROM orders RIGHT JOIN order_details ON orders.id = order_details.id_order  INNER JOIN order_statuses on orders.id_order_status=order_statuses.id where orders.id_order_status = ? limit ?,?";
		return jdbcTemplate.query(sql, new Object[] {idStatus, offset, row_count },
				new BeanPropertyRowMapper<Order>(Order.class));
	}
	public int countSumSearch(int idStatus) {
		String sql = "SELECT COUNT(*) AS sumpage FROM orders RIGHT JOIN order_details ON orders.id = order_details.id_order  INNER JOIN order_statuses on orders.id_order_status=order_statuses.id where orders.id_order_status = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idStatus},Integer.class);
	}

}

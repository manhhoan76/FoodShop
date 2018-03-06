package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.OrderDetail;

@Repository
public class OrderDetailDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderDetail> getItems(int id,int offset, int row_count) {
		String sql = "SELECT order_details.id, id_product, id_order, quantity, order_details.price, order_details.create_at, product.name, product.image FROM `order_details` INNER JOIN product ON product.id = order_details.id_product WHERE id_order =? limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { id,offset, row_count }, new BeanPropertyRowMapper<OrderDetail>(OrderDetail.class));
	}

	public List<OrderDetail> getItems(int id) {
		String sql = "SELECT order_details.id, id_product, id_order, quantity, order_details.price, order_details.create_at, product.name, product.image FROM `order_details` INNER JOIN product ON product.id = order_details.id_product WHERE id_order =?";
		return jdbcTemplate.query(sql, new Object[] { id}, new BeanPropertyRowMapper<OrderDetail>(OrderDetail.class));
	}
	
	public int countSumorder_details(int id) {
		String sql = "SELECT COUNT(*) AS sumorder_details FROM `order_details` INNER JOIN product ON product.id = order_details.id_product WHERE id_order =?   ";
		return jdbcTemplate.queryForObject(sql, new Object[] { id},Integer.class);
	}
	public OrderDetail getItem(int idorder_details) {
		String sql = "SELECT * from order_details where id=? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { idorder_details }, new BeanPropertyRowMapper<OrderDetail>(OrderDetail.class));
	}
	
	public int addItem(OrderDetail objorder_details) {
		String sql = "insert into order_details	(id_product,id_order,quantity, price) values (?,?,?,?) ";
		return jdbcTemplate.update(sql,
				new Object[] { objorder_details.getId_product(), objorder_details.getId_order(), objorder_details.getQuantity(), objorder_details.getPrice()});
	}


}

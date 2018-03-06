package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Product;

@Repository
public class ProductDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Product> getItems(int offset, int row_count) {
		String sql = "SELECT *  FROM product order by create_at desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItems() {
		String sql = "SELECT id,product.name, preview_text, detail_text, product.id_cat, picture, count_number, active, id_user, product.hiden, product.date_create, product.date_edit, category.name AS cname  FROM product INNER JOIN category ON product.id_cat = category.id_cat WHERE product.hiden = 0  order by lid desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItemsTrend() {
		String sql = "SELECT  * FROM product  WHERE product.slide = 1  order by create_at desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}

	
	public List<Product> getItemsByCid(int cid) {
		String sql = "SELECT id,product.name, preview_text, detail_text, product.id_cat, picture, count_number, active, id_user, product.hiden, product.date_create, product.date_edit, category.name AS cname  FROM product INNER JOIN category ON product.id_cat = category.id_cat WHERE product.hiden=0 && product.id_cat=? && product.active=1 order by product.id desc ";
		return jdbcTemplate.query(sql, new Object[] { cid },
				new BeanPropertyRowMapper<Product>(Product.class));
	}
	public List<Product> getItemsMostView() {
		String sql = "select id,product.name, preview_text, detail_text, product.id_cat, picture, count_number, active, id_user, product.hiden, product.date_create, product.date_edit, category.name AS cname  FROM product INNER JOIN category ON product.id_cat = category.id_cat WHERE product.hiden = 0 && product.active=1   order by product.count_number desc limit 3";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}
	public List<Product> getNew() {
		String sql = "select id,product.name, preview_text, detail_text, product.id_cat, picture, count_number, active, id_user, product.hiden, product.date_create, product.date_edit, category.name AS cname  FROM product INNER JOIN category ON product.id_cat = category.id_cat WHERE product.hiden = 0 && product.active=1 order by product.id desc limit 3";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItemsSameCid(int cid, int lid) {
		String sql = "SELECT id,product.name, preview_text, detail_text, product.id_cat, picture, count_number, active, id_user, product.hiden, product.date_create, product.date_edit, category.name AS cname  FROM product INNER JOIN category ON product.id_cat = category.id_cat WHERE product.hiden = 0 and product.id_cat=? and product.id != ? order by product.id DESC limit 4";
		return jdbcTemplate.query(sql, new Object[] { cid, lid }, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public int delItem(int idproduct) {
		String sql = "delete from product	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idproduct });
	}

	public int multildel(String result) {
		String sql = "delete from product	where lid in (" + result + ")";
		return jdbcTemplate.update(sql);
	}

	public int countSumproduct() {
		String sql = "SELECT COUNT(*) AS sumpage FROM product ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Product getItem(int idproduct) {
		String sql = "SELECT * from product where id=? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { idproduct }, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public int editItem(Product objProduct) {
		String sql = "update product set name=?,id_cat=?, description=?, price=?, image=?, create_at=? where id=? ";
		return jdbcTemplate.update(sql,
				new Object[] { objProduct.getName(), objProduct.getId_cat(), objProduct.getDescription(), objProduct.getPrice(), objProduct.getImage(), objProduct.getCreate_at(), objProduct.getId() });
	}

	public int addItem(Product objProduct) {
		String sql = "insert into product	(name, id_cat, description, price, image , slide ) values (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
				new Object[] {objProduct.getName(), objProduct.getId_cat(), objProduct.getDescription(), objProduct.getPrice(), objProduct.getImage(), objProduct.getSlide() });
	}

	public int active(Timestamp date, int idproduct) {
		String sql = "update product set slide=1,create_at=? where product.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idproduct });
	}

	public int block(Timestamp date, int idproduct) {
		String sql = "update product set slide=0,create_at=? where product.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idproduct });
	}


	public int updateView(Timestamp date, int view, int id) {
		String sql = "update product set slide=?,date_create=? where product.id=? ";
		return jdbcTemplate.update(sql, new Object[] { view, date, id });
	}

	public List<Product> getItemsSearch(String name, int idCat, int priceHight, int offset, int row_count) {
		String sql = "SELECT * FROM product  where name like '%"
				+ name + "%'  &&  id_cat ="+idCat+" && price between 0 and "+priceHight+" order by id desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Product>(Product.class));
	}
	public List<Product> getItemsSearchNotCat(String name, int priceHight, int offset, int row_count) {
		String sql = "SELECT * FROM product  where name like '%"
				+ name + "%' && price between 0 and "+priceHight+" order by id desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItemsSearchByCid(String key, int cid, int offset, int row_count) {
		String sql = "SELECT lid, lname, description, date_create, product.cid, picture, area, address, count_views, is_slide, categories.cname FROM product INNER JOIN categories ON categories.cid=product.cid where product.cid=? && (product.lname like '%"
				+ key + "%'  || product.description like '%" + key + "%') order by lid desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public int countSumSearchNotCat(String name, int priceHight) {
		String sql = "SELECT COUNT(*) AS sumpage FROM product  where name like '%"
				+ name + "%'  && price between 0 and "+priceHight+"";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int countSumSearch(String name,int idCat,  int priceHight) {
		String sql = "SELECT COUNT(*) AS sumpage FROM product  where name like '%"
				+ name + "%'  &&  id_cat ="+idCat+" && price between 0 and "+priceHight+" ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}

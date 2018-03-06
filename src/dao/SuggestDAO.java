package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Suggest;

@Repository
public class SuggestDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Suggest> getItems(int offset, int row_count) {
		String sql = "SELECT * from sugguest_product order by sugguest_product.id desc  limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Suggest>(Suggest.class));
	}

	public List<Suggest> getItems() {
		String sql = "SELECT * FROM sugguest_product   Suggest by id desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Suggest>(Suggest.class));
	}

	public List<Suggest> getItems(int id) {
		String sql = "SELECT * FROM sugguest_product where id_user=? order by create_at desc";
		return jdbcTemplate.query(sql, new Object[] { id },  new BeanPropertyRowMapper<Suggest>(Suggest.class));
	}

	
	public Suggest getItem(int idU) {
		String sql = "SELECT * FROM sugguest_product  where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idU }, new BeanPropertyRowMapper<Suggest>(Suggest.class));
	}
	/*public int editItem(Suggest objSuggest) {
		String sql = "update sugguest_product set  Suggestname=?, password=?, fullname=?, email=?, role_id=?, picture=?, date_create=? where id=? ";
		return jdbcTemplate.update(sql,
				new Object[] {objSuggest.getSuggestname(), objSuggest.getPassword(), objSuggest.getFullname(), objSuggest.getEmail(), objSuggest.getRole_id(), objSuggest.getPicture(), objSuggest.getDate_create(), objSuggest.getid()  });
	}*/
	public int delItem(int idSuggest) {
		String sql = "delete from sugguest_product	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idSuggest });
	}

	public int multildel(String result) {
		String sql = "delete from lands	where lid in ("+result+")";
		return jdbcTemplate.update(sql);
	}
	
	public int countSumSuggest() {
		String sql = "SELECT COUNT(*) AS sumSuggest FROM sugguest_product   ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public String checkSuggestname (String Suggestname){
		String sql ="select Suggestname from sugguest_product where Suggestname=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {Suggestname}, String.class);
	}
	public Suggest getItem (String Suggestname){
		String sql ="select * from sugguest_product where Suggestname=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {Suggestname},new BeanPropertyRowMapper<Suggest>(Suggest.class) );
	}
	public int addItem(Suggest objSuggest) {
		String sql = "insert into sugguest_product	(id_user, name, price, image, content) values (?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {objSuggest.getId_user(), objSuggest.getName(),objSuggest.getPrice(), objSuggest.getImage(), objSuggest.getContent()});
	}

	public int active(Timestamp date, int idSuggest) {
		String sql = "update sugguest_product set accept=1,create_at=? where sugguest_product.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idSuggest });
	}
	
	public int change(Timestamp date, int idSuggest, int id_status) {
		String sql = "update sugguest_product set id_Suggest_status=?,create_at=? where sugguest_product.id=? ";
		return jdbcTemplate.update(sql, new Object[] {  id_status, date, idSuggest });
	}
	
	public int block(Timestamp date, int idSuggest) {
		String sql = "update sugguest_product set accept=0,create_at=? where sugguest_product.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idSuggest });
	}

	public List<Suggest> getItemsSearch(String key, int offset, int row_count) {
		String sql = "SELECT lid, lname, description, date_create, lands.cid, picture, area, address, count_views, is_slide, categories.cname FROM lands INNER JOIN categories ON categories.cid=lands.cid where lands.lname like '%"+key+"%'  || lands.description like '%"+key+"%' Suggest by lid desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count },
				new BeanPropertyRowMapper<Suggest>(Suggest.class));
	}
	public int countSumSearch(String key) {
		String sql = "SELECT COUNT(*) AS sumpage FROM lands as l inner join categories as c on c.cid=l.cid where l.lname like '%"+key+"%'  || l.description like '%"+key+"%' ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}

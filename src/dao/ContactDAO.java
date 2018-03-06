package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Contact;

@Repository
public class ContactDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Contact> getItems(int offset, int row_count) {
		String sql = "SELECT * FROM contact  order by contact.id desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	public List<Contact> getItems() {
		String sql = "SELECT * FROM contact  order by id desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}


	public int delItem(int idContact) {
		String sql = "delete from contact	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idContact });
	}

	public int multildel(String result) {
		String sql = "delete from lands	where lid in ("+result+")";
		return jdbcTemplate.update(sql);
	}
	
	public int countSumContact() {
		String sql = "SELECT COUNT(*) AS sumContact FROM contact   ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int countSumContactRead() {
		String sql = "SELECT COUNT(*) AS sumContact FROM contact  where readed = 0 ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	/*public List<NumberLands> countContactByCid() {
		String sql = "SELECT categories.cid,categories.cname,COUNT(lands.cid) AS number FROM lands INNER JOIN categories ON lands.cid=categories.cid GROUP BY categories.cid ORDER BY number DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NumberLands>(NumberLands.class));
	}

	public int editItem(Contact objNew) {
		String sql = "update lands set lname =?, description =?, date_create =?, cid =?, picture =?, area =?, address =?, is_slide=?	where lid=? ";
		return jdbcTemplate.update(sql,
				new Object[] { objNew.getLname(), objNew.getDescription(), objNew.getDate_create(), objNew.getCid(),
						objNew.getPicture(), objNew.getArea(), objNew.getAddress(), objNew.getIs_slide(),
						objNew.getLid() });
	}

	public Contact getItem(int idContact) {
		String sql = "SELECT lid, lname, description, date_create, lands.cid, picture, area, address, count_views, is_slide, categories.cname FROM lands INNER JOIN categories ON categories.cid=lands.cid where lid=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idContact }, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	*/
	public int addItem(Contact objContact) {
		String sql = "insert into contact	(name,email, address, phone, content) values (?,?,?,?,?) ";
		return jdbcTemplate.update(sql,
				new Object[] { objContact.getName(), objContact.getEmail(), objContact.getAddress(), objContact.getPhone(), objContact.getContent()});
	}

	public int readed(Timestamp date, int idContact) {
		String sql = "update contact set readed=1,create_at=? where contact.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idContact });
	}

	public int readedAll() {
		String sql = "update contact set readed=1 where contact.readed=0 ";
		return jdbcTemplate.update(sql);
	}
	
	public List<Contact> getItemsSearch(String key, int offset, int row_count) {
		String sql = "SELECT lid, lname, description, date_create, lands.cid, picture, area, address, count_views, is_slide, categories.cname FROM lands INNER JOIN categories ON categories.cid=lands.cid where lands.lname like '%"+key+"%'  || lands.description like '%"+key+"%' order by lid desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count },
				new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	public int countSumSearch(String key) {
		String sql = "SELECT COUNT(*) AS sumpage FROM lands as l inner join categories as c on c.cid=l.cid where l.lname like '%"+key+"%'  || l.description like '%"+key+"%' ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}

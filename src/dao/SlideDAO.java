package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Slide;

@Repository
public class SlideDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Slide> getItems(int offset, int row_count) {
		String sql = "SELECT * FROM slide limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Slide>(Slide.class));
	}

	public List<Slide> getItems() {
		String sql = "SELECT * FROM slide";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Slide>(Slide.class));
	}
	
	public List<Slide> getItemsShow() {
		String sql = "SELECT * FROM slide where active=1 order by create_at desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Slide>(Slide.class));
	}
	
	public int active(Timestamp date, int idUser) {
		String sql = "update slide set active=1,create_at=? where slide.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idUser });
	}

	public int block(Timestamp date, int idUser) {
		String sql = "update slide set active=0,create_at=? where slide.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idUser });
	}

	public int delItem(int idslide) {
		String sql = "delete from slide	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idslide });
	}

	public int multildel(String result) {
		String sql = "delete from lands	where lid in ("+result+")";
		return jdbcTemplate.update(sql);
	}
	
	public int countSumslide() {
		String sql = "SELECT COUNT(*) AS sumslide FROM slide   ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int countSumslideRead() {
		String sql = "SELECT COUNT(*) AS sumslide FROM slide  where readed = 0 ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	/*public List<NumberLands> countslideByCid() {
		String sql = "SELECT categories.cid,categories.cname,COUNT(lands.cid) AS number FROM lands INNER JOIN categories ON lands.cid=categories.cid GROUP BY categories.cid ORDER BY number DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NumberLands>(NumberLands.class));
	}
*/
	public int editItem(Slide objSlide) {
		String sql = "update slide set name=?, create_at=?, link=? where id=? ";
		return jdbcTemplate.update(sql,
				new Object[] { objSlide.getName(), objSlide.getCreate_at(), objSlide.getLink(), objSlide.getId()});
	}

	public Slide getItem(int idslide) {
		String sql = "SELECT * from slide where id=? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { idslide }, new BeanPropertyRowMapper<Slide>(Slide.class));
	}
	
	public int addItem(Slide objslide) {
		String sql = "insert into slide	(name,link,active) values (?,?,0) ";
		return jdbcTemplate.update(sql,
				new Object[] { objslide.getName(), objslide.getLink()});
	}

	public List<Slide> getItemsSearch(String key, int offset, int row_count) {
		String sql = "SELECT lid, lname, description, date_create, lands.cid, picture, area, address, count_views, is_slide, categories.cname FROM lands INNER JOIN categories ON categories.cid=lands.cid where lands.lname like '%"+key+"%'  || lands.description like '%"+key+"%' order by lid desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count },
				new BeanPropertyRowMapper<Slide>(Slide.class));
	}
	public int countSumSearch(String key) {
		String sql = "SELECT COUNT(*) AS sumpage FROM lands as l inner join categories as c on c.cid=l.cid where l.lname like '%"+key+"%'  || l.description like '%"+key+"%' ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}

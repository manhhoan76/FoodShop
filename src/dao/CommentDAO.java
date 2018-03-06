package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Comment;
import entity.Slide;

@Repository
public class CommentDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Comment> getItems() {
		String sql ="SELECT comment.id, id_user, users.username, users.image, id_product, content, comment.create_at, comment.slide FROM comment INNER JOIN users ON comment.id_user = users.id order by comment.create_at ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Comment>(Comment.class));
	}
	
	public List<Comment> getItemsById( int idNew, int of, int row) {
		String sql ="SELECT comment.id, id_user, users.username, users.image, id_product, content, comment.create_at, comment.slide FROM comment INNER JOIN users ON comment.id_user = users.id where comment.id_product =? order by comment.create_at limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { idNew, of, row }, new BeanPropertyRowMapper<Comment>(Comment.class));
	}

	public List<Comment> getItemsSlide() {
		String sql ="SELECT comment.id, id_user, users.username, users.image, id_product, content, comment.create_at, comment.slide FROM comment INNER JOIN users ON comment.id_user = users.id where comment.slide =1 order by comment.create_at desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Comment>(Comment.class));
	}
	
	public Comment getItem(int idCom) {
		String sql = "SELECT * FROM comment  where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idCom }, new BeanPropertyRowMapper<Comment>(Comment.class));
	}
	public int active(Timestamp date, int idUser) {
		String sql = "update comment set slide=1,create_at=? where comment.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idUser });
	}
	public int addItem(Comment objComment) {
		String sql = "insert into comment	(id_user, id_product, content, slide) values (?,?,?,0) ";
		return jdbcTemplate.update(sql,
				new Object[] { objComment.getId_user(), objComment.getId_product(), objComment.getContent()});
	}
	public int block(Timestamp date, int idUser) {
		String sql = "update comment set slide=0,create_at=? where comment.id=? ";
		return jdbcTemplate.update(sql, new Object[] { date, idUser });
	}
	public List<Comment> getItems(int offset, int row_count) {
		String sql ="SELECT comment.id, id_user, users.username, users.image, id_product, content, comment.create_at, comment.slide FROM comment INNER JOIN users ON comment.id_user = users.id order by comment.create_at limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Comment>(Comment.class));
	}

	public int delItem(int idComment) {
		String sql = "delete from comment	where id=? ";
		return jdbcTemplate.update(sql, new Object[] { idComment });
	}

	public int multildel(String result) {
		String sql = "delete from Commentegory	where lid in (" + result + ")";
		return jdbcTemplate.update(sql);
	}

	public int countSumComment() {
		String sql = "SELECT COUNT(*) AS sumComment FROM comment INNER JOIN users ON comment.id_user = users.id ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int countSumCommentByID(int idProduct) {
		String sql = "SELECT COUNT(*) AS sumComment FROM comment INNER JOIN users ON comment.id_user = users.id where id_product=?";
		return jdbcTemplate.queryForObject(sql,new Object[] { idProduct}, Integer.class);
	}
}

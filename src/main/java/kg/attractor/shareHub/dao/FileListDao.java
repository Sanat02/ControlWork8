package kg.attractor.shareHub.dao;


import kg.attractor.shareHub.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FileListDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(File profileImage) {
        String sql = "insert into profile_images(userid,filename,status) " +
                "values  ( ? , ? , ? )";
        jdbcTemplate.update(sql, profileImage.getUserId(), profileImage.getFileName(),profileImage.getStatus());
    }

    public File getImageById(int imageId) {
        String sql = "select * from profile_images where id = ?";
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(File.class), imageId));
    }

    public List<File> getImageByUserId(int userId) {
        String sql = "select * from profile_images where userId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(File.class), userId);
    }
    public List<File> getAllFiles(){
        String sql = "select * from profile_images ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(File.class));
    }
    public void delete(int id) {
        String sql = "DELETE FROM profile_images WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
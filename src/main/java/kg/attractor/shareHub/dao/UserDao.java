package kg.attractor.shareHub.dao;

import kg.attractor.shareHub.model.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;


@Component

public class UserDao extends BaseDao {


    UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT id, account_name as accountName,  full_name as fullName, email, role_id as roleId, " +
                "password FROM users WHERE email = ?";

        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)));

    }

    @Override
    public int save(Object obj) {
        User user = (User) obj;
        String sql = "INSERT INTO users (account_name,full_name, email, role_id, password, enabled) " +
                "VALUES (?, ?, ?, ?, ?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getAccountName());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRoleId());
            ps.setString(5, user.getPassword());
            ps.setBoolean(6, user.isEnabled());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete resume with ID: " + id);
        }
    }

    @Override
    public void update(Object obj) {
    }
}

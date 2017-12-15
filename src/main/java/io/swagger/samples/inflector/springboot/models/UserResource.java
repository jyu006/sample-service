package io.swagger.samples.inflector.springboot.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Link;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import io.swagger.samples.inflector.springboot.domain.UserDetails;

@Component
public class UserResource implements Resource {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  private static final String SELECT_ALL_QUERY = "select * from UserDetails";

  @Override
  public List<Link> getLinks() {
    throw new NotImplementedException("TODO");
  }
  
	public List<UserDetails> getUserDetails() {
		List<UserDetails> userDetails = jdbcTemplate.query(SELECT_ALL_QUERY, new RowMapper<UserDetails>() {

			public UserDetails mapRow(ResultSet result, int rowNum) throws SQLException {
				UserDetails user = new UserDetails();
				user.setGivenName(result.getString("givenName"));
				user.setSurname(result.getString("surname"));
				user.setPreferredName(result.getString("preferredName"));
				user.setDob(result.getDate("dob"));

				return user;
			}
		});

		return userDetails;
	}

}

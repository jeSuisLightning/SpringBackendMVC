package restmvcspring.Service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import restmvcspring.Exception.ContactNotFoundException;
import restmvcspring.Models.Contacts;
import restmvcspring.Repository.Mapper.ContactRowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Component
@Slf4j
public class ServiceImpl implements ServiceMethods{
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    private final BeanPropertyRowMapper beanPropertyRowMapper;
    @Autowired
    public ServiceImpl(JdbcTemplate jdbcTemplate,DataSource dataSource,BeanPropertyRowMapper beanPropertyRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.beanPropertyRowMapper = beanPropertyRowMapper;
    }
    @Override
    public Optional<Contacts> myContactsShow() {
        log.debug("");
        String sql = "SELECT*FROM Contacts.contacts";
        Contacts contacts = DataAccessUtils.singleResult(jdbcTemplate.query(sql,
                new RowMapperResultSetExtractor<Contacts>(new ContactRowMapper(),1)));
        return Optional.ofNullable(contacts);


    }

    @Override
    public List<Contacts> showById(int id) {
        String sql = "SELECT *FROM ContactS.contacts WHERE ID = ?";
        Contacts contacts = DataAccessUtils.singleResult(jdbcTemplate.query(sql,
                new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<Contacts>(new ContactRowMapper(),1)));
        return showById(id);
    }

    @Override
    public void delete(int id) {
        log.warn("");
        String sql = "DELETE FROM Contacts.contacts WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Contacts create(Contacts contacts) {
        Contacts contacts1 = new Contacts();
        String sql = "INSERT INTO Contacts.contacts VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,contacts1.getId(),contacts1.getFirstname(),contacts1.getEmail(),contacts1.getFullname()
        ,contacts1.getLastname(),contacts1.getEmail());
        return contacts1;
    }


    @Override
    public Contacts update(Contacts contacts) {
        log.warn("");
        Contacts existedContact = showById(Math.toIntExact(contacts.getId())).stream().findAny().orElse(null);
        if(existedContact != null){
            String sql = "UPDATE Contacts.contacts SET id = ?,fullname = ?,firstname = ?,lastname = ?,email=?";
            jdbcTemplate.update(sql,contacts.getId(),contacts.getFullname(),contacts.getLastname(),contacts.getId(),contacts.getEmail());
        }
        log.warn("",contacts.getId());

        throw new ContactNotFoundException("",contacts.getId());

    }

    @Override
    public Contacts save(Contacts contacts) {
        contacts.setId((Long) System.currentTimeMillis());
        String sql = "INSERT INTO Contacts.contacts Values(?,?,?,?)";
        jdbcTemplate.update(sql,contacts.getId(),contacts.getFullname()
        ,contacts.getFirstname(),contacts.getLastname(),contacts.getEmail());
        return contacts;


    }

    @Override
    public void batchInsert(List<Contacts> contacts) {
        String sql = "INSERT INTO Contacts.contacts(id,firstname,lastname,fullname,email) VALUES(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Contacts contacts1 = contacts.get(i);
                ps.setInt(1, Math.toIntExact(contacts1.getId()));
                ps.setString(2,contacts1.getFirstname());
                ps.setString(3,contacts1.getFullname());
                ps.setString(4,contacts1.getLastname());
                ps.setString(5,contacts1.getEmail());

            }

            @Override
            public int getBatchSize() {
                return contacts.size();
            }
        });
    }
}

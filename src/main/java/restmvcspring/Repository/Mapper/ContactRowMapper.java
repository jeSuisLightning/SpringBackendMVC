package restmvcspring.Repository.Mapper;

import org.springframework.jdbc.core.RowMapper;
import restmvcspring.Models.Contacts;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contacts> {
    @Override
    public Contacts mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contacts contacts = new Contacts();
        contacts.setId((long) rs.getInt(Contacts.Fields.id));
        contacts.setFirstname(rs.getString(Contacts.Fields.firstname));
        contacts.setLastname(rs.getString(Contacts.Fields.lastname));
        contacts.setFullname(rs.getString(Contacts.Fields.fullname));
        contacts.setEmail(rs.getString(Contacts.Fields.email));
        return contacts;
    }
}

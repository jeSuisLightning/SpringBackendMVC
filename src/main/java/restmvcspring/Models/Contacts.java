package restmvcspring.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "Contacts")
@Data
@Getter
@Setter
@FieldNameConstants
public class Contacts {
    private String fullname;
    private String firstname;
    private String lastname;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

}

package restmvcspring.Service.Impl;

import org.springframework.stereotype.Service;
import restmvcspring.Models.Contacts;

import java.util.List;
import java.util.Optional;

@Service
public interface ServiceMethods {
    Optional<Contacts> myContactsShow();
    List<Contacts> showById(int id);
    void delete(int id);
    Contacts create(Contacts contacts);

    Contacts update(Contacts contacts);

    Contacts save(Contacts contacts);
    void batchInsert(List<Contacts> contacts);
}

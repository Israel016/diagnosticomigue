package utez.edu.mx.diagnosticomigue.model.dao;

import org.springframework.data.repository.CrudRepository;
import utez.edu.mx.diagnosticomigue.model.entity.ClienteBean;

public interface ClienteDao extends CrudRepository<ClienteBean, Integer> {
    ClienteBean findByCurp(String curp);
}

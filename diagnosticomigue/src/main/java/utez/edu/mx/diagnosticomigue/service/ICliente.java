package utez.edu.mx.diagnosticomigue.service;

import utez.edu.mx.diagnosticomigue.model.dto.ClienteDto;
import utez.edu.mx.diagnosticomigue.model.entity.ClienteBean;

import java.util.List;

public interface ICliente {
    ClienteBean save (ClienteDto clienteDto);

    ClienteBean update(ClienteDto clienteDto);

    ClienteBean findById(Integer id);

    void delete(ClienteBean clienteBean);

    List<ClienteBean> findAll();
}

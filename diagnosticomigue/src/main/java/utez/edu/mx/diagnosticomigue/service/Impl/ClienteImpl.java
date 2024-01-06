package utez.edu.mx.diagnosticomigue.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.diagnosticomigue.model.dao.ClienteDao;
import utez.edu.mx.diagnosticomigue.model.dto.ClienteDto;
import utez.edu.mx.diagnosticomigue.model.entity.ClienteBean;
import utez.edu.mx.diagnosticomigue.service.ICliente;

import java.util.List;


@AllArgsConstructor
@Service
public class ClienteImpl implements ICliente {

    private final ClienteDao clienteDao;

    @Transactional
    @Override
    public ClienteBean save(ClienteDto clienteDto) {
        ClienteBean cliente;
        if (clienteDto.getIdcliente() != null) {
            cliente = clienteDao.findById(clienteDto.getIdcliente()).orElse(null);
            if (cliente == null) {
                return null;
            }
            cliente.setNombre(clienteDto.getNombre());
            cliente.setApellidos(clienteDto.getApellidos());
            cliente.setCurp(clienteDto.getCurp());
            cliente.setFechanac(clienteDto.getFechanac());
        } else {
            cliente = ClienteBean.builder()
                    .nombre(clienteDto.getNombre())
                    .apellidos(clienteDto.getApellidos())
                    .curp(clienteDto.getCurp())
                    .fechanac(clienteDto.getFechanac())
                    .build();
        }
        return clienteDao.save(cliente);
    }

    public ClienteBean findByCurp(String curp) {
        return clienteDao.findByCurp(curp);
    }


    @Transactional
    @Override
    public ClienteBean findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(ClienteBean cliente) {
        clienteDao.delete(cliente);
    }

    @Transactional
    @Override
    public List<ClienteBean> findAll() {
        return (List<ClienteBean>) clienteDao.findAll();
    }

    @Transactional
    @Override
    public ClienteBean update(ClienteDto clienteDto) {
        ClienteBean clienteExistente = clienteDao.findById(clienteDto.getIdcliente()).orElse(null);

        if (clienteExistente == null) {
            return null;
        }
        clienteExistente.setNombre(clienteDto.getNombre());
        clienteExistente.setApellidos(clienteDto.getApellidos());
        clienteExistente.setCurp(clienteDto.getCurp());
        clienteExistente.setFechanac(clienteDto.getFechanac());
        return clienteDao.save(clienteExistente);
    }

}

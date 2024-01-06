package utez.edu.mx.diagnosticomigue.controller;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.diagnosticomigue.model.dto.ClienteDto;
import utez.edu.mx.diagnosticomigue.model.entity.ClienteBean;
import utez.edu.mx.diagnosticomigue.service.Impl.ClienteImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ClienteController {
    private final ClienteImpl clienteService;


    @PostMapping("/cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            String curp = clienteDto.getCurp();
            ClienteBean clienteExistente = clienteService.findByCurp(curp);

            if (clienteExistente != null) {
                response.put("msj", "Ya existe un cliente con el CURP proporcionado: " + curp);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            ClienteBean clienteSave = clienteService.save(clienteDto);
            return new ResponseEntity<>(clienteSave, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            response.put("msj", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/cliente")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            ClienteBean clienteUpdate = clienteService.save(clienteDto);
            return new ResponseEntity<>(clienteUpdate, HttpStatus.OK);
        } catch (DataAccessException ex) {
            response.put("msj", ex.getMessage());
            response.put("Card", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try{
            ClienteBean cliente = clienteService.findById(id);
            clienteService.delete(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.NO_CONTENT);
        } catch (DataAccessException ex){
            response.put("msj", ex.getMessage());
            response.put("Cliente no existe", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/cliente/{id}")
    public ClienteBean showById(@PathVariable Integer id){
        try {
            return clienteService.findById(id);
        } catch (DataAccessException dae) {
            throw new RuntimeException("Error en Base de Datos: ", dae);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener el cliente: ", ex);
        }
    }

    @GetMapping("/cliente")
    public List<ClienteBean> findAll() {
        try {
            return clienteService.findAll();
        } catch (DataAccessException dae) {
            throw new RuntimeException("Error en Base de Datos: ", dae);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener el cliente: ", ex);
        }
    }
}

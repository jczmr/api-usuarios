package com.jc.apiusuarios.web;

import com.jc.apiusuarios.exception.ResourceNotFoundException;
import com.jc.apiusuarios.service.IRolService;
import com.jc.apiusuarios.web.dto.RolDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping
    public List<RolDto> getAllRoles() {
        return rolService.findAll();
    }

    @GetMapping("{id}")
    public RolDto getRol(@PathVariable Integer id) {

        try {
            RolDto rolDto = rolService.getRolById(id);
            return rolDto;

        } catch (Exception e) {
            throw new ResourceNotFoundException("Rol not exist with id :" + id);
        }
    }

    @PostMapping
    public RolDto createRol(@RequestBody RolDto rol) {
        return rolService.saveRol(rol);
    }

    @PutMapping("{id}")
    public RolDto updateRol(@PathVariable Integer id, @RequestBody RolDto rol) {

        try {
            RolDto dto = rolService.getRolById(id);
            dto.setRoleName(rol.getRoleName());
            return rolService.updateRol(rol);

        } catch (Exception e) {
            throw new ResourceNotFoundException("Rol not exist with id :" + id);
        }
    }

    @DeleteMapping("{id}")
    public void deleteRol(@PathVariable Integer id) {
        rolService.deleteRolById(id);
    }
}

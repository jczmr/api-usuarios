package com.jc.apiusuarios.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jc.apiusuarios.service.IRolService;
import com.jc.apiusuarios.web.dto.RolDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(RolController.class)
class RolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRolService rolService;

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllRoles() throws Exception {

        RolDto rolDto1 = new RolDto();
        rolDto1.setId(1);
        rolDto1.setRoleName("ANALYST");
        rolDto1.setCreatedAt(LocalDateTime.now());
        rolDto1.setStatus("ACTIVE");
        rolService.saveRol(rolDto1);
        RolDto rolDto2 = new RolDto();
        rolDto2.setId(2);
        rolDto2.setRoleName("MANAGER");
        rolDto2.setCreatedAt(LocalDateTime.now());
        rolDto2.setStatus("ACTIVE");
        rolService.saveRol(rolDto2);
        List<RolDto> listRoles = (Arrays.asList(rolDto1, rolDto2));

        when(rolService.findAll()).thenReturn(listRoles);
        ResultActions response = this.mockMvc.perform(get("/v1/roles"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(listRoles.size()))
                .andExpect(jsonPath("$[*].roleName", Matchers.containsInAnyOrder("ANALYST", "MANAGER")));
    }

    @Test
    public void getRol() throws Exception {
        RolDto rolDto1 = new RolDto();
        rolDto1.setId(3);
        rolDto1.setRoleName("JUNIOR");
        rolDto1.setCreatedAt(LocalDateTime.now());
        rolDto1.setStatus("ACTIVE");
        rolService.saveRol(rolDto1);

        when(rolService.getRolById(3)).thenReturn(rolDto1);

        this.mockMvc.perform(get("/v1/roles/3")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value(rolDto1.getRoleName()));
    }

    @Test
    public void createRol() throws Exception {

        RolDto rolDto1 = new RolDto();
        rolDto1.setId(4);
        rolDto1.setRoleName("ANALYST");
        rolDto1.setCreatedAt(LocalDateTime.now());
        rolDto1.setStatus("ACTIVE");
        when(rolService.saveRol(rolDto1)).thenReturn(rolDto1);
        this.mockMvc.perform(post("/v1/roles")
                        .content(asJsonString(rolDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(rolDto1.getStatus()));
    }


    @Test
    public void updateRol() throws Exception {
        Integer id = 5;
        RolDto saved = new RolDto();
        saved.setId(id);
        saved.setRoleName("SENIOR");
        saved.setCreatedAt(LocalDateTime.now());
        saved.setStatus("ACTIVE");

        RolDto updated = new RolDto();
        updated.setId(id);
        updated.setRoleName("ADMIN");
        updated.setCreatedAt(LocalDateTime.now());
        updated.setStatus("ACTIVE");

        when(rolService.getRolById(id)).thenReturn(saved);
        when(rolService.updateRol(updated)).thenReturn(updated);

        ResultActions response = this.mockMvc.perform(put("/v1/roles/{id}", id)
                        .content(asJsonString(updated))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value(updated.getRoleName()))
                .andExpect(jsonPath("$.status").value(updated.getStatus()));

    }

    @Test
    public void deleteRolDto() throws Exception {
        Integer id = 4;
        RolDto rolDto1 = new RolDto();
        rolDto1.setId(4);
        rolDto1.setRoleName("ANALYST");
        rolDto1.setCreatedAt(LocalDateTime.now());
        rolDto1.setStatus("ACTIVE");

        rolService.deleteRolById(4);

        ResultActions response = this.mockMvc.perform(delete("/v1/roles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk()).andDo(print());

    }
}

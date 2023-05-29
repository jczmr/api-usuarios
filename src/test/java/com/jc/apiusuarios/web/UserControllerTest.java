package com.jc.apiusuarios.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jc.apiusuarios.service.IRolService;
import com.jc.apiusuarios.web.dto.RolDto;
import com.jc.apiusuarios.web.dto.UserDto;
import com.jc.apiusuarios.mapper.AutoRolMapper;
import com.jc.apiusuarios.mapper.AutoUserMapper;
import com.jc.apiusuarios.service.IUserService;
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
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private AutoUserMapper userMapper;

    @MockBean
    private AutoRolMapper rolMapper;

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
    void getAllUsers() throws Exception {

        RolDto rolDto = new RolDto();
        rolDto.setId(1);
        rolDto.setRoleName("ANALYST");
        rolDto.setCreatedAt(LocalDateTime.now());
        rolDto.setStatus("ACTIVE");
        rolService.saveRol(rolDto);

        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setRole(rolDto);
        userDto1.setUsername("john133");
        userDto1.setName("JOHN");
        userDto1.setLastName("JOHNSON");
        userDto1.setDateOfBirth(LocalDateTime.now());
        userDto1.setAge(43);
        userDto1.setEmail("john@mail.com");
        userDto1.setStatus("ACTIVE");
        userDto1.setCreatedAt(LocalDateTime.now());
        userService.saveUser(userDto1);

        UserDto userDto2 = new UserDto();
        userDto2.setId(1);
        userDto2.setRole(rolDto);
        userDto2.setUsername("jimmy456");
        userDto2.setName("JIMMY");
        userDto2.setLastName("JOHNSON");
        userDto2.setDateOfBirth(LocalDateTime.now());
        userDto2.setAge(34);
        userDto2.setEmail("jimmy@mail.com");
        userDto2.setStatus("ACTIVE");
        userDto2.setCreatedAt(LocalDateTime.now());
        userService.saveUser(userDto2);

        List<UserDto> listUsers = new ArrayList<>();
        listUsers.add(userDto1);
        listUsers.add(userDto2);

        when(userService.findAll()).thenReturn(listUsers);


        ResultActions response = this.mockMvc.perform(get("/v1/users"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(listUsers.size()))
                .andExpect(jsonPath("$[*].username").value(Matchers.containsInAnyOrder("jimmy456", "john133")));
    }


    @Test
    void getUser() throws Exception {

        RolDto rolDto = new RolDto();
        rolDto.setId(1);
        rolDto.setRoleName("ANALYST");
        rolDto.setCreatedAt(LocalDateTime.now());
        rolDto.setStatus("ACTIVE");
        rolService.saveRol(rolDto);

        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setRole(rolDto);
        userDto1.setUsername("john133");
        userDto1.setName("JOHN");
        userDto1.setLastName("JOHNSON");
        userDto1.setDateOfBirth(LocalDateTime.now());
        userDto1.setAge(43);
        userDto1.setEmail("john@mail.com");
        userDto1.setStatus("ACTIVE");
        userDto1.setCreatedAt(LocalDateTime.now());
        userService.saveUser(userDto1);

        when(userService.getUserById(1)).thenReturn(userDto1);

        ResultActions response = this.mockMvc.perform(get("/v1/users/" + userDto1.getId()));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userDto1.getUsername()));
    }


    @Test
    void createUSer() throws Exception {
        RolDto rolDto = new RolDto();
        rolDto.setId(1);
        rolDto.setRoleName("ANALYST");
        rolDto.setCreatedAt(LocalDateTime.now());
        rolDto.setStatus("ACTIVE");
        rolService.saveRol(rolDto);

        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setRole(rolDto);
        userDto1.setUsername("john133");
        userDto1.setName("JOHN");
        userDto1.setLastName("JOHNSON");
        userDto1.setDateOfBirth(LocalDateTime.now());
        userDto1.setAge(43);
        userDto1.setEmail("john@mail.com");
        userDto1.setStatus("ACTIVE");
        userDto1.setCreatedAt(LocalDateTime.now());

        when(userService.saveUser(userDto1)).thenReturn(userDto1);

        ResultActions response = this.mockMvc.perform(post("/v1/users")
                .content(asJsonString(userDto1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(userDto1.getStatus()));
    }


    @Test
    void updateUser() throws Exception {
        Integer id = 7;
        RolDto rolDto = new RolDto();
        rolDto.setId(id);
        rolDto.setRoleName("ANALYST");
        rolDto.setCreatedAt(LocalDateTime.now());
        rolDto.setStatus("ACTIVE");

        UserDto updated = new UserDto();
        updated.setId(id);
        updated.setRole(rolDto);
        updated.setUsername("carl7584");
        updated.setName("CARL");
        updated.setLastName("JOHNSON");
        updated.setDateOfBirth(LocalDateTime.now());
        updated.setAge(30);
        updated.setEmail("carl@mail.com");
        updated.setStatus("ACTIVE");
        updated.setCreatedAt(LocalDateTime.now());


        when(rolService.saveRol(rolDto)).thenReturn(rolDto);
        updated.setName("ROBERT");
        when(userService.getUserById(updated.getId())).thenReturn(updated);
        when(userService.updateUser(updated)).thenReturn(updated);

        ResultActions response = this.mockMvc.perform(put("/v1/users/{id}", id)
                .content(asJsonString(updated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk()).andExpect(jsonPath("$.name").value(updated.getName()));
    }

    @Test
    public void deleteUSer() throws Exception {
        Integer id = 1;

        ResultActions response = this.mockMvc.perform(delete("/v1/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk()).andDo(print());
    }
}
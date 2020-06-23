package maximstarikov.secondmemory.integration.controllers;

import maximstarikov.secondmemory.controller.RegistrationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Добавление нового пользователя")));
    }


}

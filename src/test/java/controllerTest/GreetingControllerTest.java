package controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.controller.GreetingController;
import hello.model.Greeting;
import hello.repository.GreetingRepository;
import hello.service.GreetingService;
import hello.web.WebMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestContext.class, WebMvcConfig.class})
public class GreetingControllerTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Mock
    private GreetingService greetingService;

    @Mock
    private GreetingRepository greetingRepository;

    private MockMvc mockMvc;
    protected ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){
        initMocks(this);
        //mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc= MockMvcBuilders.standaloneSetup(new GreetingController(greetingService)).build();
    }
    @Test
    public void shouldInsertGreeting() throws Exception {
        Greeting greeting = new Greeting();
        String sumit = "sumit";
        greeting.setContent(sumit);
        when(greetingService.insert(greeting)).thenReturn(1);
        //when(greetingRepository.insert(greeting, person)).thenReturn(1);
        String json = mapper.writeValueAsString(greeting);
        mockMvc.perform(post("/greeting").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetGreeting() throws Exception {
        Greeting greeting = new Greeting();
        String sumit = "sumit";
        greeting.setContent(sumit);
        ArrayList<Greeting> greetings = new ArrayList<Greeting>();
        greetings.add(greeting);
        when(greetingService.findGreeting(sumit)).thenReturn(greetings);
        when(greetingRepository.findGreeting(sumit)).thenReturn(greetings);
        String json = mapper.writeValueAsString(sumit);
        mockMvc.perform(get("/greeting/find/" + sumit).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$.[*].id", contains(0)))
        .andExpect(jsonPath("$.[*].name", contains(sumit)));
    }

}
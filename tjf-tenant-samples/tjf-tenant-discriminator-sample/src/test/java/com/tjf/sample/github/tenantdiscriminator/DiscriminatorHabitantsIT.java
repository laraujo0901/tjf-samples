package com.tjf.sample.github.tenantdiscriminator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = TenantApplication.class)
@AutoConfigureMockMvc
public class DiscriminatorHabitantsIT {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void tatooineHabitantsTest() throws Exception {
		String expectedResult = "[{\"id\":{\"id\":\"anakin\",\"tenantId\":\"tatooine\"},\"name\":\"Anakin Skywalker\",\"gender\":\"male\"},{\"id\":{\"id\":\"luke\",\"tenantId\":\"tatooine\"},\"name\":\"Luke Skywalker\",\"gender\":\"male\"},{\"id\":{\"id\":\"han\",\"tenantId\":\"tatooine\"},\"name\":\"Han Solo\",\"gender\":\"male\"}]";

		mockMvc.perform(post("/api/v1/habitants").header("X-Planet", "tatooine").contentType(MediaType.APPLICATION_JSON)
				.content(
						"[{\"id\":\"anakin\",\"name\":\"Anakin Skywalker\",\"gender\":\"male\"},{\"id\":\"luke\",\"name\":\"Luke Skywalker\",\"gender\":\"male\"},{\"id\":\"han\",\"name\": \"Han Solo\",\"gender\":\"male\"}]"))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));

		mockMvc.perform(get("/api/v1/habitants").header("X-Planet", "tatooine").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}

	@Test
	public void alderaanHabitantsTest() throws Exception {
		String expectedResult = "[{\"id\":{\"id\":\"leia\",\"tenantId\":\"alderaan\"},\"name\":\"Leia Organa\",\"gender\":\"female\"}]";

		mockMvc.perform(post("/api/v1/habitants").header("X-Planet", "alderaan").contentType(MediaType.APPLICATION_JSON)
				.content("[{\"id\":\"leia\",\"name\":\"Leia Organa\",\"gender\":\"female\"}]"))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));

		mockMvc.perform(get("/api/v1/habitants").header("X-Planet", "alderaan").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}

	@Test
	public void bespinHabitantsTest() throws Exception {
		String expectedResult = "[{\"id\":{\"id\":\"lando\",\"tenantId\":\"bespin\"},\"name\":\"Lando Calrissian\",\"gender\":\"male\"},{\"id\":{\"id\":\"dengar\",\"tenantId\":\"bespin\"},\"name\":\"Dengar, The Bounty Hunter\",\"gender\":\"male\"}]";

		mockMvc.perform(
				post("/api/v1/habitants").header("X-Planet", "bespin").contentType(MediaType.APPLICATION_JSON).content(
						"[{\"id\":\"lando\",\"name\":\"Lando Calrissian\",\"gender\":\"male\"},{\"id\":\"dengar\",\"name\":\"Dengar, The Bounty Hunter\",\"gender\": \"male\"}]"))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));

		mockMvc.perform(get("/api/v1/habitants").header("X-Planet", "bespin").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}

}

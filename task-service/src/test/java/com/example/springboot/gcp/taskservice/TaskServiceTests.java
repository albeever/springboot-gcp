package com.example.springboot.gcp.taskservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskServiceTests {

	@Autowired
	private MockMvc mvc;


	@Test
	@Order(1)
	public void testCreateTask() throws Exception{
		this.mvc.perform(post("/task")
           .contentType(MediaType.APPLICATION_JSON)
           .content(getCreateTaskData().toString()) 
           .accept(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void testGetTaskDetails() throws Exception {
		String testProductId = getCreateTaskData().getString("id");
		this.mvc.perform(get("/task/"+testProductId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(getCreateTaskData().toString()))
		;
	}

	@Test
	@Order(3)
	public void testGetTaskList() throws Exception {
		this.mvc.perform(get("/task").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json("["+getCreateTaskData().toString()+"]"));
	}


	@Test
	@Order(4)
	public void testUpdateTask() throws Exception{
		this.mvc.perform(put("/task")
           .contentType(MediaType.APPLICATION_JSON)
           .content(getUpdateTaskData().toString())) 
		   .andExpect(status().isOk());
	}


	@Test
	@Order(5)
	public void testDeleteTask() throws Exception {
		String testProductId = getCreateTaskData().getString("id");
		this.mvc.perform(delete("/task/"+testProductId))
		.andExpect(status().isOk());
	}

	//test data utility methods
	public JSONObject getCreateTaskData() throws JSONException {
        JSONObject createTaskData = new JSONObject();
        createTaskData.put("id", "test-task-1");
        createTaskData.put("title", "test task 1");
        return createTaskData;
    }


    public JSONObject getUpdateTaskData() throws JSONException {
        JSONObject createTaskData = new JSONObject();
        createTaskData.put("id", "test-task-1");
        createTaskData.put("title", "test task 1 updated");
        return createTaskData;
    }


}



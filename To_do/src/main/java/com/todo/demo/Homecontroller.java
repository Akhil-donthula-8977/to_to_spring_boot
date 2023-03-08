package com.todo.demo;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.tasks.task;

import jakarta.servlet.http.HttpServletResponse;

@EnableMongoRepositories 
@RestController

public class Homecontroller  {
   @Autowired
   taskrepo task_rep;
   
   @RequestMapping("/")
   public ModelAndView home() {
	   ModelAndView obj=new ModelAndView("home");
	   return obj;
   }
   
   
   
   
   //fetching operation
 
   @GetMapping(path="/tasks",produces= {"application/json"})//only produces data in json format
   @ResponseBody
    public List<task> alltask() {
    return task_rep.findAll();
	
   }
   
   
    
   @GetMapping("/tasks/{taskname}")//path variable represents the { } part
   @ResponseBody
   public List<task> gettask(@PathVariable("taskname") String taskname) {
   //return task_rep.findByTaskname(taskname).toString();
	     
	return task_rep.findByTaskname(taskname);
  }
   
   
   @GetMapping(path="/task" ,produces= {"application/json"})
   @ResponseBody
   public List<task> gettask( task t) {
   //return task_rep.findByTaskname(taskname).toString();
   return task_rep.findTaskByTasknameAndDeadline(t.getTaskname(),t.getDeadline());
   }
   
  
    
   //create:operation
	@PostMapping(path="/task",consumes={"application/json"})
	public task add_task(@RequestBody task t){
	 task_rep.save(t);
	 return t;
	//return "home.jsp";
		
	}
	
	
	//delete operation
	
	@DeleteMapping("/task/{deadline}")
	public List<task> deletetask(@PathVariable("deadline")String deadline) {
         	
		List<task> x= task_rep.deleteTaskByDeadline(task_rep.findTaskByDeadline(deadline)); 
		 return x;
		//we can add conditions and error exceptions to send http status response
		 //void deleteAllByDeadline(Iterable<? extends ID> ids);
		//return 
           
           
	}
	
	
	
	//update:operation
	@PutMapping(path="/task",consumes={"application/json"})
	public void updatetask(@RequestBody String taskname,task t){
          List<task> x=task_rep.findByTaskname(taskname);
          for (task element : x) {
        	  element.setTaskname(t.getTaskname());
        	  element.setDeadline(t.getDeadline());
        	  task_rep.save(element);
          }
   
	//return "home.jsp";
		
	}
	
	
	
	
	
}
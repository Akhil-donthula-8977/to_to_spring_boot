package com.todo.demo;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   
   
    
   @GetMapping("/tasks/{taskname}")//path variable represents the {{} } part
   @ResponseBody
   public List<task> gettask(@PathVariable("taskname") String taskname) {
   //return task_rep.findByTaskname(taskname).toString();
 return task_rep.findByTaskname(taskname);
  }
   
   
   
   
   /*we can send a response for a request in two ways that is through
     @REsponse Body which sends ony body forn a response adn 
     one more is response entity in wihc we can set statuscode headers for the
     response*/
    
   @GetMapping(path="/task" ,produces= {"application/json"})
   //@ResponseBody
   public ResponseEntity<List<task>> gettask(task t) {
   
	List<task> x=  task_rep.findTaskByTasknameAndDeadline(t.getTaskname(),t.getDeadline());
     if(!x.isEmpty()) {
       return new ResponseEntity<>(x,HttpStatus.OK);
     }
     else{
	   return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	 }
   }
   
  
    
   //create:operation
	@PostMapping(path="/task",consumes={"application/json"})
	public ResponseEntity<String> add_task(@RequestBody task t){
	try {
	 task_rep.save(t);
	 return  new ResponseEntity<>("added",HttpStatus.OK);
	}
	catch(Error e){
		return  new ResponseEntity<>("unable to ad",HttpStatus.BAD_REQUEST);
	  }
   }
	
	
	//delete operation
	//we can add conditions and error exceptions to send http status response
		 //void deleteAllByDeadline(Iterable<? extends ID> ids);
	@DeleteMapping("/taskid/{id}")
	public ResponseEntity<String> deletetask(@PathVariable("id")String id)throws IllegalArgumentException {
       Optional<task> x=task_rep.findById(id);
      if(x.isPresent()) {
    	  task_rep.deleteById(id);
    	  return  new ResponseEntity<>("deleted",HttpStatus.OK);
      }
      else {
    	return  new ResponseEntity<>("Not  Found",HttpStatus.BAD_REQUEST);
      
      }       
           
	}
	

	
	@DeleteMapping("/task/{taskname}")
	public void deletetaskbyname(@PathVariable String taskname)throws IllegalArgumentException {
        List<task> x=task_rep.findByTaskname(taskname);
        for(task t:x) {
        	String id__=t.getId();
		 task_rep.deleteById(id__); 
        }
		 ///return x;
		//we can add conditions and error exceptions to send http status response
		 //void deleteAllByDeadline(Iterable<? extends ID> ids);
		//return 
           
           
	}
	
	
	@DeleteMapping("/task/{deadline}")
	public void deletetaskbydeadline(@PathVariable String deadline)throws IllegalArgumentException {
        List<task> x=task_rep.findByTaskname(deadline);
        for(task t:x) {
        	String id__=t.getId();
		 task_rep.deleteById(id__); 
        }
		 ///return x;
		//we can add conditions and error exceptions to send http status response
		 //void deleteAllByDeadline(Iterable<? extends ID> ids);
		//return 
           
           
	}
	
	
	//update:operation
	@PutMapping(path="/task",consumes={"application/json"})
	public void updatetask(@RequestBody String taskname,String deadline){
		List<task> t=task_rep.findByTaskname(taskname);
		for(task a:t) {
		a.setDeadline(deadline);
		final task x=a;
		task_rep.save(a);
		    
			
		}
    
         
}
	
	
	
	
	
}

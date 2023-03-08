package com.todo.tasks;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="tasks")
public class task {
        
	    private String taskname;
        private String deadline;
		
		public String getTaskname() {
			return taskname;
		}
		public void setTaskname(String taskname) {
			this.taskname = taskname;
		}
		public String getDeadline() {
			return deadline;
		}
		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}
         
        
        
        
}

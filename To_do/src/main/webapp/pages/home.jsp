<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <!DOCTYPE html>
<html>
    <title>

    </title>
    <head>
  <style>
  #box{
    border:2px solid black;
    border-radius:10px;
    background-color: hsla(211, 68%, 35%, 0.3);
    padding:30px;
    width:25rem;
    height:auto;
    position:relative;
    left: 190px; 
    top:150px;

    
    
}
#to_Do{
    font-family: "Monospace";
    font-size:30px;
    text-decoration:underline;
    position:relative;
    left:90px ;
    margin: 20px;
}
.input{
    padding:10px;
    
    border-radius: 5px;
    margin-left: 44px ;

    
}
#dead-line{
    margin:7px ;
}
#task_label{
    font-size:20px ;
 
}
#submit{
    border:2px solid black;
    border-radius:5px;
    padding: 7px;
    position: relative;
    left: 150px;
    top: 20px;
    color:black ;
    background-color:hsla(211, 52%, 41%, 0.3) ;
    
}
#submit:hover{
    background-color: #50557c;  
}
#submit:active {
    background-color: #50557c;
    box-shadow: 0 2px #666;
    transform: translateY(4px);
  }

   
  </style>
    </head>
    <body>
        <script src="script.js"></script>
        <div id="box"><p id="to_Do"> To do list</p>
        <form action="addtask" method="post" >
            
            <label for="Task" id="task_label"> Task:</label>
            <input type="text" class="input" name="taskname" id="task" placeholder="Enter the task">
            <br>
            
            <label for="dead-line" id="task_label">dead-line:</label>
            <input type="date" class="input" name="deadline" id="dead-line" placeholder="Enter the date">
           <br>
          
           
          <div>  <input type="submit" id="submit"></div>
        </form>
        <br></br>
        <form action="/gettask">
            <input type="text" class="input" name="taskname" id="task" placeholder="Enter the task">
           <div>  <input type="submit" id="submit"></div>
          </form>
        
        </div>
          

    </body>
</html>
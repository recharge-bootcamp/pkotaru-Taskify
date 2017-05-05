package com.paypal.bootcamp.taskify.dao;

public interface DBConstants {
 public String INSERT_TASK_DETAILS ="insert into task_information(";  
 public String UPDATE_TASK_DETAILS ="update task_information SET ";
 public String FETCH_TASK_DETAILS = "select * from task_information where status != 'archived';";
 public String FILTER_TASK_DETAILS_WITH_STATUS = "select * from task_information ";
 public String DELETE_TASK_DETAILS = "delete from task_information ";	
 public String ARCHIVE_TASK_DETAILS ="update task_information SET ";
}

package com.paypal.bootcamp.taskify.services;

import java.util.List;

import com.paypal.bootcamp.taskify.vo.TaskVO;

public interface ITaskSvc {
	public List<TaskVO> getAllTaskDetails();

	public void updateTaskDetails(TaskVO taskVO, String[] taskIds);

	public void insertAndFetchTaskDetails(TaskVO taskVO);

	public void deleteTaskDetails(String[] TaskIds);

	public List<TaskVO> filterTaskDetailsBasedOnStatus(String status);
}

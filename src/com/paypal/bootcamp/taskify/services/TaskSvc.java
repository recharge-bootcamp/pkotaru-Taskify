package com.paypal.bootcamp.taskify.services;

import java.util.List;

import com.paypal.bootcamp.taskify.dao.ITaskDAO;
import com.paypal.bootcamp.taskify.dao.TaskDAO;
import com.paypal.bootcamp.taskify.vo.TaskVO;

public class TaskSvc implements ITaskSvc {

	public List<TaskVO> getAllTaskDetails() {
		ITaskDAO taskDao = new TaskDAO();
		List<TaskVO> taskVOList = taskDao.getAllTaskDetails();
		return taskVOList;

	}

	public void updateTaskDetails(TaskVO taskVO, String[] taskIds) {
		ITaskDAO taskDao = new TaskDAO();
		taskDao.updateTaskDetails(taskVO, taskIds);

	}

	public void insertAndFetchTaskDetails(TaskVO taskVO) {
		ITaskDAO taskDao = new TaskDAO();
		taskDao.insertAndFetchTaskDetails(taskVO);
	}

	public void deleteTaskDetails(String[] TaskIds) {
		ITaskDAO taskDao = new TaskDAO();
		taskDao.deleteTaskDetails(TaskIds);
	}

	@Override
	public List<TaskVO> filterTaskDetailsBasedOnStatus(String status) {
		// TODO Auto-generated method stub
		ITaskDAO taskDao = new TaskDAO();
		List<TaskVO> taskVOList = taskDao.filterTaskDetailsBasedOnStatus(status);
		return taskVOList;
	}
}

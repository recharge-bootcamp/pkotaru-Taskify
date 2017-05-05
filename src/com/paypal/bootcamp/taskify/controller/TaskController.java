package com.paypal.bootcamp.taskify.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.bootcamp.taskify.services.ITaskSvc;
import com.paypal.bootcamp.taskify.services.TaskSvc;
import com.paypal.bootcamp.taskify.vo.TaskVO;

/**
 * Servlet implementation class TaskController
 */
@WebServlet("/TaskController")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TaskController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String[] taskIds = request.getParameterValues("selected");

		String addBtn = request.getParameter("add_btn");
		if (addBtn != null) {
			System.out.println("add btn clicked");
			addTaskAndFetchDetails(request, response);
		}
		if (taskIds != null) {
			String delete_btn = request.getParameter("delete_btn");
			if (delete_btn != null) {
				System.out.println("delete btn clicked");
				ITaskSvc taskSvc = new TaskSvc();
				taskSvc.deleteTaskDetails(taskIds);
			}
			String complete_btn = request.getParameter("complete_btn");

			if (complete_btn != null) {
				System.out.println("complete btn clicked");
				System.out.println("taskIds:" + taskIds);
				ITaskSvc taskSvc = new TaskSvc();
				TaskVO vo = new TaskVO();
				vo.setStatus("Completed");
				taskSvc.updateTaskDetails(vo, taskIds);
			}
			String arch_btn = request.getParameter("arch_btn");
			if (arch_btn != null) {
				System.out.println("archive btn clicked");
				ITaskSvc taskSvc = new TaskSvc();
				TaskVO vo = new TaskVO();
				vo.setStatus("Archived");
				taskSvc.updateTaskDetails(vo, taskIds);
			}
		}
		String selectedValue = request.getParameter("statusMenu");

		if (!selectedValue.equals("All") || selectedValue != null) {
			System.out.println("menu changed");
			ITaskSvc taskSvc = new TaskSvc();
			System.out.println("selectedValue:" + selectedValue);
			TaskVO vo = new TaskVO();
			vo.setStatus(selectedValue);
			List<TaskVO> taskVOList = taskSvc.filterTaskDetailsBasedOnStatus(selectedValue);
			request.setAttribute("taskVOList", taskVOList);
		}

		request.getRequestDispatcher("/jsp/TaskManager.jsp").forward(request, response);

	}

	private void addTaskAndFetchDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		TaskVO taskVO = new TaskVO();
		taskVO.setTitle(title);
		taskVO.setDescription(description);
		if (!title.isEmpty() && !description.isEmpty()) {
			taskVO.setStatus(status);
		}
		ITaskSvc taskSvc = new TaskSvc();
		taskSvc.insertAndFetchTaskDetails(taskVO);
	}

}

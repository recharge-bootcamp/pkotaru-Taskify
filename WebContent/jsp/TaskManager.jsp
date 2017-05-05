<%@page import="com.paypal.bootcamp.taskify.login.UserBean"%>
<%@page import="java.util.List"%>
<%@page import="com.paypal.bootcamp.taskify.dao.TaskDAO"%>
<%@page import="com.paypal.bootcamp.taskify.vo.TaskVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Taskify</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/task.css"></link>

</head>
<body>

	<form name="tskForm"
		action="${pageContext.request.contextPath}/TaskController"
		method="POST">
		<div id="header">
			<p class="navbar-text p_style">
			<h1>Taskify</h1>
			</p>
		</div>
		<div class="form-group">
			<input type="text" name="title" id="title" placeholder="Title"
				class="form-control title-text" />
		</div>
		<div>
			<input type="text" name="description" id="description"
				placeholder="Description" class="form-control desc_text" />
		</div>
		<br> <input type="hidden" id="status" name="status"
			value="pending">
		<div style="padding-left: 50px">
			<input type="submit" name="add_btn" value="Done"
				class="btn btn-primary" />
			<div></div>
		</div>
		<div>
			<div class="btns">
				<input type="submit" name="delete_btn" value="DeleteTask"
					class="btn btn-primary" /> <input type="submit"
					value="Mark As Complete" name="complete_btn"
					class="btn btn-primary" /> <input type="submit" name="arch_btn"
					value="Archive" class="btn btn-primary" />
				<div class="sel_menu" id="menuId">
					<select name="statusMenu" id="statusMenu"
						class="selectpicker sel_menu" onchange="this.form.submit();">
						<option value="All" selected>All</option>
						<option value="Pending">Pending</option>
						<option value="Completed">Completed</option>
						<option value="Archived">Archived</option>
					</select>
				</div>

			</div>

		</div>
		<div id="showTasks">
			<div id="onLoadTable"></div>
			<table class="table table-sm">
				<tr>
					<th>#</th>
					<th>Title</th>
					<th>Description</th>
					<th>Status</th>
				</tr>
				<c:forEach var="taskVO" items="${requestScope.taskVOList}">
					<tr>
						<td><input type="checkbox" name="selected"
							value="${taskVO.taskId}" /></td>
						<td><c:out value="${taskVO.title}"></c:out></td>
						<td><c:out value="${taskVO.description}"></c:out></td>
						<td><c:out value="${taskVO.status}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>
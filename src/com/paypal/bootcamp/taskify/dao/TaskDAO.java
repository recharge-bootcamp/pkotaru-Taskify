package com.paypal.bootcamp.taskify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.paypal.bootcamp.taskify.vo.TaskVO;

public class TaskDAO implements ITaskDAO {

	public List<TaskVO> setTaskDetailsToTaskVO(ResultSet rs) {
		List<TaskVO> taskVOList = new ArrayList<TaskVO>();
		try {

			while (rs.next()) {
				TaskVO taskVO = new TaskVO();
				taskVO.setTaskId(rs.getInt("taskId"));
				taskVO.setTitle(rs.getString("title"));
				taskVO.setDescription(rs.getString("description"));
				taskVO.setStatus(rs.getString("status"));
				System.out
						.println(rs.getInt("taskId") + ":" + rs.getString("title") + ":" + rs.getString("description"));
				taskVOList.add(taskVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return taskVOList;

	}

	public void insertAndFetchTaskDetails(TaskVO taskVO) {
		PreparedStatement pstmnt = null;
		Connection conn = null;
		TaskDAO dao = new TaskDAO();
		int insertFlag = 0;
		List<TaskVO> taskVOList = new ArrayList<TaskVO>();
		try {
			StringBuilder insertQuery = new StringBuilder();
			insertQuery.append(DBConstants.INSERT_TASK_DETAILS);

			if (taskVO.getTitle() != null) {
				insertQuery.append("title");
			} else {
				System.out.println("title is null");
			}
			if (taskVO.getDescription() != null) {
				insertQuery.append(",description");
			} else {
				System.out.println("description is null");
			}
			if (taskVO.getStatus() != null) {
				insertQuery.append(",status)");
			} else {
				System.out.println("description is null");
			}
			insertQuery.append(" values('");

			if (taskVO.getTitle() != null) {
				insertQuery.append(taskVO.getTitle());
				insertQuery.append("'");
			}
			if (taskVO.getDescription() != null) {
				insertQuery.append(",'");
				insertQuery.append(taskVO.getDescription());
				insertQuery.append("'");
			}
			if (taskVO.getStatus() != null) {
				insertQuery.append(",'");
				insertQuery.append(taskVO.getStatus());
				insertQuery.append("'");
			}
			insertQuery.append(");");
			System.out.println("insertQuery::" + insertQuery);
			if (taskVO.getTitle() != null && taskVO.getDescription() != null && taskVO.getStatus() != null) {
				conn = dao.getDBConnection();
				pstmnt = conn.prepareStatement(insertQuery.toString());
				insertFlag = pstmnt.executeUpdate();
				System.out.println("insertFlag in TaskDAO::" + insertFlag);
			} else {
				System.out.println("bean is empty");
			}

			System.out.println(taskVOList.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				System.out.println("closing connections");
				if (pstmnt != null) {
					pstmnt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void updateTaskDetails(TaskVO taskVO, String[] taskIds) {
		System.out.println("In updateTaskDetails");
		PreparedStatement pstmnt = null;
		PreparedStatement pstmnt1 = null;
		Connection conn = null;
		TaskDAO dao = null;
		int updateFlag = 0;
		dao = new TaskDAO();
		try {
			StringBuilder updateQuery = new StringBuilder();
			updateQuery.append(DBConstants.UPDATE_TASK_DETAILS);

			if (taskVO.getTitle() != null) {
				updateQuery.append("title = '");
				updateQuery.append(taskVO.getTitle());
				updateQuery.append("'");
			}
			if (taskVO.getDescription() != null) {
				updateQuery.append(",description = '");
				updateQuery.append(taskVO.getDescription());
				updateQuery.append("'");
			}
			if (taskVO.getTitle() != null || taskVO.getDescription() != null) {
				updateQuery.append(",");
			}
			if (taskVO.getStatus() != null) {
				updateQuery.append("status = '");
				updateQuery.append(taskVO.getStatus());
				updateQuery.append("'");

			}

			updateQuery = appendTaskIds(updateQuery, taskIds);
			System.out.println("updateQuery::" + updateQuery);
			conn = dao.getDBConnection();
			pstmnt = conn.prepareStatement(updateQuery.toString());
			updateFlag = pstmnt.executeUpdate();
			System.out.println("updateFlag::" + updateFlag);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				System.out.println("closing connections");
				if (pstmnt != null) {
					pstmnt.close();
				}
				if (pstmnt1 != null) {
					pstmnt1.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public Connection getDBConnection() {
		Connection conn = null;

		try {
			conn = DBSingleton.getInstance().getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println("In getDBConnection::error while creating connection");
			e1.printStackTrace();
		}

		return conn;

	}

	public void deleteTaskDetails(String[] taskIds) {
		PreparedStatement pstmnt = null;
		PreparedStatement pstmnt1 = null;
		Connection conn = null;
		TaskDAO dao = null;
		try {
			if (taskIds != null) {
				dao = new TaskDAO();
				conn = dao.getDBConnection();
				StringBuilder deleteQuery = new StringBuilder();
				deleteQuery.append(DBConstants.DELETE_TASK_DETAILS);
				deleteQuery = appendTaskIds(deleteQuery, taskIds);
				System.out.println("deleteQuery:" + deleteQuery);
				pstmnt = conn.prepareStatement(deleteQuery.toString());
				int deleteFlag = pstmnt.executeUpdate();
				System.out.println("deleteFlag" + deleteFlag);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In deleteTaskDetails SQLException");
			e.printStackTrace();
		} finally {
			try {
				if (pstmnt != null) {
					pstmnt.close();
				}
				if (pstmnt1 != null) {
					pstmnt1.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// closeConnection();
		}
	}

	public List<TaskVO> filterTaskDetailsBasedOnStatus(String status) {
		System.out.println("In filterTaskDetailsBasedOnStatus");
		PreparedStatement stmnt = null;
		Connection conn = null;
		ResultSet rs = null;
		TaskDAO dao = null;
		List<TaskVO> taskVOList = null;
		try {
			dao = new TaskDAO();
			conn = dao.getDBConnection();
			StringBuilder filterQuery = new StringBuilder();
			if (status.equals("All")) {
				filterQuery.append(DBConstants.FETCH_TASK_DETAILS);
				System.out.println("filterQuery:" + filterQuery);
			} else {
				filterQuery.append(DBConstants.FILTER_TASK_DETAILS_WITH_STATUS);
				filterQuery.append(" where status = '");
				filterQuery.append(status);
				filterQuery.append("';");
				System.out.println("filterQuery:" + filterQuery);
			}
			stmnt = conn.prepareStatement(filterQuery.toString());
			rs = stmnt.executeQuery();
			taskVOList = setTaskDetailsToTaskVO(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In filterTaskDetailsBasedOnStatus SQLException");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmnt != null) {
					stmnt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// closeConnection();
		}
		return taskVOList;
	}

	@Override
	public List<TaskVO> getAllTaskDetails() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		TaskDAO dao = null;
		List<TaskVO> taskVOList = null;
		dao = new TaskDAO();
		conn = dao.getDBConnection();
		try {
			pstmnt = conn.prepareStatement(DBConstants.FETCH_TASK_DETAILS);
			rs = pstmnt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		taskVOList = setTaskDetailsToTaskVO(rs);

		return taskVOList;
	}

	public StringBuilder appendTaskIds(StringBuilder builder, String[] taskIds) {
		builder.append(" where taskId in(");
		for (int i = 0; i < taskIds.length; i++) {
			System.out.println("taskIds" + taskIds[i]);
			builder.append(Integer.parseInt(taskIds[i]));
			if (i != taskIds.length - 1) {
				builder.append(",");
			}
		}
		builder.append(");");
		return builder;

	}
}

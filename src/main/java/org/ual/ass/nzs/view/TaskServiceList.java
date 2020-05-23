package org.ual.ass.nzs.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.ual.ass.nzs.controllers.TaskListController;
import org.ual.ass.nzs.models.Task;
import org.ual.ass.nzs.models.TaskList;
import org.ual.ass.nzs.util.HibernateUtil;
import java.util.List;
/**
 * Servlet implementation class TaskServiceList
 */
@WebServlet(urlPatterns = "/list*")
public class TaskServiceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServiceList() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskListController taskListController = new TaskListController();
		String listId,taskId;
		String[] uri = request.getRequestURI().split("/");
		switch (uri.length) {
		case 3:
			taskListController.getLists();
			break;
		case 4:
			listId = uri[3];
			taskListController.getListById(Integer.parseInt(listId));
			break;
		case 5:
			listId = uri[3];
			taskId = uri[4];
			taskListController.getTaskById(Integer.parseInt(taskId));
		default:
			break;
		}

	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskListController taskListController = new TaskListController();
		String listId,taskId;
		String[] uri = request.getRequestURI().split("/");
		switch (uri.length) {
		case 3:
			taskListController.addList("name");
			break;
		case 4:
			listId = uri[3];
			taskListController.addTask("taskName", Integer.parseInt(listId));
			break;
		case 5:
			taskId = uri[4];
			taskListController.updateTaskStatus(Integer.parseInt(taskId), "status");
		default:
			break;
		}
	}
    @Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		TaskListController taskListController = new TaskListController();
		String listId,taskId;
		String[] uri = request.getRequestURI().split("/");
		switch (uri.length) {
		case 4:
			listId = uri[3];
			taskListController.updateListName(Integer.parseInt(listId), "newName");
			break;
		case 5:
			taskId = uri[4];
			taskListController.updateTaskDescription(Integer.parseInt(taskId), "Description");
		default:
			break;
		}
	}
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	TaskListController taskListController = new TaskListController();
		String listId,taskId;
		String[] uri = request.getRequestURI().split("/");
		switch (uri.length) {
		case 4:
			listId = uri[3];
			taskListController.deleteList(Integer.parseInt(listId));
			break;
		case 5:
			taskId = uri[4];
			taskListController.deleteTask(Integer.parseInt(taskId));
		default:
			break;
		}
    }

}

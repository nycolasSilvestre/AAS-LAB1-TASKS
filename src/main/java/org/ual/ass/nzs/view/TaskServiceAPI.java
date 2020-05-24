package org.ual.ass.nzs.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.ual.ass.nzs.controllers.TaskListController;

import org.ual.ass.nzs.util.JsonUtil;


@WebServlet("/lists/*")
public class TaskServiceAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServiceAPI() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskListController taskListController = new TaskListController();
		String listId,taskId;
		String[] uri = request.getRequestURI().split("/");
		String respStr=null;
		
		switch (uri.length) {
		case 3:
			respStr = taskListController.getLists();
			break;
		case 4:
			listId = uri[3];
			respStr = taskListController.getListByIdToJson(Integer.parseInt(listId));
			break;
		case 5:
			listId = uri[3];
			taskId = uri[4];
			respStr = taskListController.getTaskByIdToJson(Integer.parseInt(taskId));
		default:
			break;
		}
		if(isErrorMessage(respStr))
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
        out.println(respStr);
        out.close(); 
		
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskListController taskListController = new TaskListController();
		JsonUtil jsonUtil = new JsonUtil();
		JSONObject reqContent = new JSONObject();
		try {
			reqContent = jsonUtil.requestJsonParser(request);
		} catch (JSONException | IOException e1) {
			e1.printStackTrace();
		}
		String listId,taskId;
		String respStr="";
		String[] uri = request.getRequestURI().split("/");
		switch (uri.length) {
		case 3:
			try {
				respStr = taskListController.addList(reqContent.getString("list name"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			listId = uri[3];
			try {
				respStr = taskListController.addTask(reqContent.getString("task name"), Integer.parseInt(listId));
			} catch (NumberFormatException | JSONException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			taskId = uri[4];
			try {
				respStr = taskListController.updateTaskStatus(Integer.parseInt(taskId), reqContent.getString("status"));
			} catch (NumberFormatException | JSONException e) {
				e.printStackTrace();
			}
		default:
			break;
		}
		if(isErrorMessage(respStr))
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
        out.println(respStr);
        out.close(); 
		
	}
    @Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	JsonUtil jsonUtil = new JsonUtil();
		JSONObject reqContent = new JSONObject();
		try {
			reqContent = jsonUtil.requestJsonParser(request);
		} catch (JSONException | IOException e1) {
			e1.printStackTrace();
		}
		TaskListController taskListController = new TaskListController();
		String listId,taskId;
		String respStr="";
		String[] uri = request.getRequestURI().split("/");
		switch (uri.length) {
		case 4:
			listId = uri[3];
			try {
				respStr = taskListController.updateListName(Integer.parseInt(listId),  reqContent.getString("list name"));
			} catch (NumberFormatException | JSONException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			taskId = uri[4];
			try {
				respStr = taskListController.updateTaskDescription(Integer.parseInt(taskId), reqContent.getString("descriptionlist"));
			} catch (NumberFormatException | JSONException e) {
				e.printStackTrace();
			}
		default:
			break;
		}
		if(isErrorMessage(respStr))
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
        out.println(respStr);
        out.close(); 
		
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
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		
    }
    
    public boolean isErrorMessage(String message) {
		return message.toUpperCase().contains("ERRO");
	}

}

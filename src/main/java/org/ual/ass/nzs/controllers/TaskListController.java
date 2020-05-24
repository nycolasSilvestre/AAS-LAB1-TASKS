package org.ual.ass.nzs.controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.ual.ass.nzs.models.Task;
import org.ual.ass.nzs.models.TaskList;
import org.ual.ass.nzs.util.HibernateUtil;
import org.ual.ass.nzs.util.JsonUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TaskListController {
	JsonUtil jsonUtil = new JsonUtil();
	private SessionFactory sessionFactory;
	public TaskListController() {
		super();
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public String addList(String listName) throws JSONException {
		int id = -1;
		TaskList taskList = new TaskList(listName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		 id = (int) session.save(taskList);
		session.getTransaction().commit();
		session.close();
		if(id!=-1) {
			String response = "{'id':'"+id+"','List Name':'"+listName+"'}";
			return new JSONObject(response).toString()	;			
		}
		else {
			return jsonUtil.errorMessageHandler("Erro ao criar Lista de tarefas").toString();
		}
	}
	public String getLists(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Session session = sessionFactory.openSession();
		List allLists = session.createQuery("from TaskList").list();
		session.close();	
		String listsInJson = gson.toJson(allLists);
		return listsInJson;
	}
	public TaskList getListById(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TaskList taskList = (TaskList) session.get(TaskList.class, id);
		session.getTransaction().commit();
		session.close();	
		return taskList;
	}
	
	public String updateListName(int id, String newName) throws JSONException {
		TaskList temp = getListById(id);
		temp.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();		
		String response = "{'id':'"+id+"','List Name':'"+newName+"'}";
		return new JSONObject(response).toString()	;	
	}
	public void deleteList(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TaskList temp = getListById(id);
		session.delete(temp);
		session.getTransaction().commit();
		session.close();		
	}
	
	//Task Methods
	public String addTask(String description, int Listid) throws JSONException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		int previousListSize, newListSize;
		Task newTask = new Task(description);
		TaskListController taskListController = new TaskListController();
		TaskList list = taskListController.getListById(Listid);
		previousListSize = list.getTasks().size();
		list.addTask(newTask);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(list);
		session.getTransaction().commit();
		session.close();
		list = taskListController.getListById(Listid);
		newListSize = list.getTasks().size();
		if(previousListSize < newListSize) {
			return gson.toJson(list.getLastInsertedTask());
		}
		else {
			return jsonUtil.errorMessageHandler("Erro ao criar a tarefa solicitada").toString();
		}
	}
	public Task getTaskById(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Task task = (Task) session.get(Task.class, id);
		session.getTransaction().commit();
		session.close();	
		return task;
	}
	public String updateTaskStatus(int taskId, String status){
		Task temp = getTaskById(taskId);
		temp.setState(status);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();	
		return getTaskByIdToJson(taskId);
	}
	public String updateTaskDescription(int taskId, String description){
		Task temp = getTaskById(taskId);
		temp.setDescription(description);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();	
		return getTaskByIdToJson(taskId);
	}
	public void deleteTask(int id) {
		Task temp = getTaskById(id);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(temp);
		session.getTransaction().commit();
		session.close();	
	}
	public String getListByIdToJson(int id) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(getListById(id));
	}
	public String getTaskByIdToJson(int id) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(getTaskById(id));
	}
	public void close() {
		this.sessionFactory.close();
	}
}

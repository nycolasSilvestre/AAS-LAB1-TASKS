package org.ual.ass.nzs.controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ual.ass.nzs.models.Task;
import org.ual.ass.nzs.models.TaskList;
import org.ual.ass.nzs.util.HibernateUtil;

public class TaskListController {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public TaskListController() {
		super();
	}
	public int addList(String listName) {
		TaskList taskList = new TaskList(listName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int id = (int) session.save(taskList);
		session.getTransaction().commit();
		session.close();
		
		return id;
	}
	public List<TaskList> getLists(){
		Session session = sessionFactory.openSession();
		List allLists = session.createQuery("from TaskList").list();	
		session.getTransaction().commit();
		session.close();	
		return (List<TaskList>)allLists;
	}
	public TaskList getListById(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TaskList taskList = (TaskList) session.get(TaskList.class, id);
		session.getTransaction().commit();
		session.close();	
		return taskList;
	}
	
	public void updateListName(int id, String newName) {
		TaskList temp = getListById(id);
		temp.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();		
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
	public void addTask(String description, int Listid){
		Task newTask = new Task(description);
		TaskListController taskListController = new TaskListController();
		TaskList list = taskListController.getListById(Listid);
		list.addTask(newTask);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(list);
		session.getTransaction().commit();
		session.close();
	}
	public Task getTaskById(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Task task = (Task) session.get(Task.class, id);
		session.getTransaction().commit();
		session.close();	
		return task;
	}
	public void updateTaskStatus(int taskId, String status){
		Task temp = getTaskById(taskId);
		temp.setState(status);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();	
	}
	public void updateTaskDescription(int id, String description){
		Task temp = getTaskById(id);
		temp.setDescription(description);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();	
	}
	public void deleteTask(int id) {
		Task temp = getTaskById(id);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(temp);
		session.getTransaction().commit();
		session.close();	
	}
	
}

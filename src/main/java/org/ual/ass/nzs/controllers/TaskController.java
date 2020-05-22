package org.ual.ass.nzs.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ual.ass.nzs.models.Task;
import org.ual.ass.nzs.models.TaskList;
import org.ual.ass.nzs.util.HibernateUtil;

public class TaskController {
	static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public TaskController() {
		super();
	}
	public static void addTask(Task task, int Listid){
		TaskList list = TaskListController.getListById(Listid);
		list.addTask(task);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(list);
		session.getTransaction().commit();
		session.close();
	}
	public static Task getTaskById(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Task task = (Task) session.get(Task.class, id);
		session.getTransaction().commit();
		session.close();	
		return task;
	}
	public static void updateTaskStatus(int id, String status){
		Task temp = getTaskById(id);
		temp.setState(status);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();	
	}
	public static void updateTaskDescription(int id, String description){
		Task temp = getTaskById(id);
		temp.setDescription(description);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();	
	}
	public static void deleteTask(int id) {
		Task temp = getTaskById(id);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(temp);
		session.getTransaction().commit();
		session.close();	
	}
}

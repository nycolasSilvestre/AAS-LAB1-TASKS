package org.ual.ass.nzs.controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ual.ass.nzs.models.TaskList;
import org.ual.ass.nzs.util.HibernateUtil;

public class TaskListController {
	static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public TaskListController() {
		super();
	}
	public static int addList(String listName) {
		TaskList taskList = new TaskList(listName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int id = (int) session.save(taskList);
		session.getTransaction().commit();
		session.close();
		
		return id;
	}
	public static List<TaskList> getLists(){
		Session session = sessionFactory.openSession();
		List allLists = session.createQuery("from TaskList").list();	
		session.getTransaction().commit();
		session.close();	
		return (List<TaskList>)allLists;
	}
	public static TaskList getListById(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TaskList taskList = (TaskList) session.get(TaskList.class, id);
		session.getTransaction().commit();
		session.close();	
		return taskList;
	}
	
	public static void updateListName(int id, String newName) {
		TaskList temp = getListById(id);
		temp.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(temp);
		session.getTransaction().commit();
		session.close();		
	}
	public static void deleteList(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TaskList temp = getListById(id);
		session.delete(temp);
		session.getTransaction().commit();
		session.close();		
	}
	
}

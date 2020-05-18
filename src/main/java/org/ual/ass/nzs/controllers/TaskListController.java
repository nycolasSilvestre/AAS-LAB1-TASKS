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
	public void addList(String taskName) {
		//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		TaskList taskList = new TaskList(taskName);
		//taskList.addTask(new Task("Primeira tarefa1."));
		//taskList.addTask(new Task("Segunda tarefa2."));
		//taskList.addTask(new Task("Terceira tarefa3."));
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(taskList);
		session.getTransaction().commit();
		session.close();
	}
	public List<TaskList> getLists() {
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
}

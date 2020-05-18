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
		return session.createQuery("from TaskList").list();	
	}
}

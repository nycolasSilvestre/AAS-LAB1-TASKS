package org.ual.ass.nzs.controllers;

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
import org.ual.ass.nzs.models.Task;
import org.ual.ass.nzs.models.TaskList;
import org.ual.ass.nzs.util.HibernateUtil;

/**
 * Servlet implementation class TaskServiceList
 */
@WebServlet(urlPatterns = "/list")
public class TaskServiceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServiceList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		TaskList taskList = new TaskList("Lista de exemplo");
		
		// Adicionar tarefas
		taskList.addTask(new Task("Primeira tarefa1."));
		taskList.addTask(new Task("Segunda tarefa2."));
		taskList.addTask(new Task("Terceira tarefa3."));
		
		// Gravar a lista
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(taskList);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

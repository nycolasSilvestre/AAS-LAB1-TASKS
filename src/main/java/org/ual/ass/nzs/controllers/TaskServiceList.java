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
import java.util.List;
/**
 * Servlet implementation class TaskServiceList
 */
@WebServlet(urlPatterns = "/list")
public class TaskServiceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TaskListController tlc = new TaskListController();
       
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
		//tlc.addList(listName);
		//TaskList taskList = tlc.getListById(13);
		//response.getWriter().write(taskList.getName());
		//List task = tlc.getLists();
		//for (TaskList taskList :(List<TaskList>) task) {
		//	response.getWriter().write(taskList.getName());
			
		//}
		int id = tlc.addList("Teste top");
		Task nw = new Task();
		Task nw0 = new Task();
		Task nw1 = new Task();
		nw.setDescription("teste 1000 grau");
		nw0.setDescription("teste 1000 grau 1");
		nw1.setDescription("teste 1000 grau 2");
		nw.setState("Started");
		tlc.addTask(nw,id);
		tlc.addTask(nw0,id);
		tlc.addTask(nw1,id);
		
		
		//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		//TaskList taskList = new TaskList();
		
		// Adicionar tarefas
		//taskList.addTask(new Task("Primeira tarefa1."));
		//taskList.addTask(new Task("Segunda tarefa2."));
		//taskList.addTask(new Task("Terceira tarefa3."));
		
		// Gravar a lista
		
		//Session session = sessionFactory.openSession();
		//session.beginTransaction();
		//session.save(taskList);
		//session.getTransaction().commit();
		//session.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

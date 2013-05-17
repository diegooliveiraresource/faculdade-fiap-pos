package br.com.fiap.javaweb.provaonline.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.javaweb.provaonline.bean.Categoria;
import br.com.fiap.javaweb.provaonline.dao.CategoriaDaoImpl;

/**
 * Servlet implementation class CadastroCategoriasServlet
 */
@WebServlet("/paginas/CadastroCategorias")
public class CadastroCategoriasServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDaoImpl categoriaDaoImpl;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroCategoriasServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria categoria = new Categoria();
		categoria.setDescricao(request.getParameter("descr"));
		if(request.getParameter("id") != null && !request.getParameter("id").equals("")){
			categoria.setId(Long.parseLong(request.getParameter("id")));
		}
		categoriaDaoImpl.update(categoria);
		request.getSession().setAttribute("categorias", null);
		response.sendRedirect("cadastroCategorias.jsp");

	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") != null){
			Long id = Long.parseLong(request.getParameter("id"));
			System.out.println(id + " id");
			Categoria c = categoriaDaoImpl.find(id);
			request.getSession().setAttribute("categoria", c);
		}else{
			List<Categoria> lista = categoriaDaoImpl.listAll();
			request.getSession().setAttribute("categorias", lista);
		}
		response.sendRedirect("cadastroCategorias.jsp");
	}

}

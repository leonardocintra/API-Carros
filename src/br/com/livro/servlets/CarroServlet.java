package br.com.livro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.ListaCarros;
import br.com.livro.util.RegexUtil;
import br.com.livro.util.ServletUtil;


@WebServlet("/carros/*")
public class CarroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarroService carroService = new CarroService();
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		if (id != null){
			Carro carro = carroService.getCarro(id);
			if (carro != null){
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				ServletUtil.writeJSON(response, json);
			}
			else{
				response.sendError(404, "Carro n�o encontrado");
			}
		}
		else{
			List<Carro> carros = carroService.getCarros();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(carros);
			ServletUtil.writeJSON(response, json);
		}
	}
}

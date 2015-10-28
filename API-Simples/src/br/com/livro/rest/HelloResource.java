package br.com.livro.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.livro.domain.Response;

@Path("/hello")
public class HelloResource {
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML+";charset=utf-8")
	public String helloHTML(){
		return "<b>Ola mundo HTML!</b>";
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public String helloTextPlani(){
		return "<b>Ola mundo Texto!</b>";
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	public Response helloXML(){
		return Response.Ok("Oi Xis emi eli");
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloJson(){
		return Response.Ok("Oi jason");
	}
}

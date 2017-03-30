/*
 * AuthorResource.java
 * Clase que representa el recurso "/authors"
 * Implementa varios métodos para manipular las authores
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.ejb.AuthorLogic;
import co.edu.uniandes.csw.bookstore.dtos.AuthorDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "authors".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "authors". Al ejecutar la aplicación, el recurse
 * será accesible a través de la ruta "/api/authors"
 *
 *
 */
@Path("authors")
@Produces("application/json")
public class AuthorResource {

    @Inject
    AuthorLogic authorLogic;

    @GET
    public List<AuthorDetailDTO> getAuthors() throws BusinessLogicException {
        return listEntity2DetailDTO(authorLogic.getAuthors());
    }

    @GET
    @Path("{id: \\d+}")
    public AuthorDetailDTO getAuthor(@PathParam("id") Long id) throws BusinessLogicException {
        AuthorEntity entity = authorLogic.getAuthor(id);
        if (entity == null) {
            throw new WebApplicationException("El author no existe", 404);
        }
        return new AuthorDetailDTO();
    }

    @POST
    public AuthorDetailDTO createAuthor(AuthorDetailDTO author) throws BusinessLogicException {
        return new AuthorDetailDTO(authorLogic.createAuthor(author.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public AuthorDetailDTO updateAuthor(@PathParam("id") Long id, AuthorDetailDTO author) throws BusinessLogicException {
        AuthorEntity entity = authorLogic.getAuthor(id);
        if (entity == null) {
            throw new WebApplicationException("El author no existe", 404);
        }
        return new AuthorDetailDTO(authorLogic.updateAuthor(id, author.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteAuthor(@PathParam("id") Long id) throws BusinessLogicException {
        AuthorEntity entity = authorLogic.getAuthor(id);
        if (entity == null) {
            throw new WebApplicationException("El author no existe", 404);
        }
        authorLogic.deleteAuthor(id);
    }

    private List<AuthorDetailDTO> listEntity2DetailDTO(List<AuthorEntity> entityList) {
        List<AuthorDetailDTO> list = new ArrayList<>();
        for (AuthorEntity entity : entityList) {
            list.add(new AuthorDetailDTO(entity));
        }
        return list;
    }
}

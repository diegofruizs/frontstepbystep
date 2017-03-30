/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.BookDetailDTO;
import co.edu.uniandes.csw.bookstore.ejb.AuthorLogic;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Produces("application/json")
@Consumes("application/json")
public class AuthorBookResource {

    @Inject
    AuthorLogic authorLogic;

    @GET
    public List<BookDetailDTO> getAuthorBooks(@PathParam("authorsId") Long authorsId) throws BusinessLogicException {
        AuthorEntity entity = authorLogic.getAuthor(authorsId);
        if (entity == null) {
            throw new WebApplicationException("El autor no existe", 404);
        }
        return listBookEntity2DetailDTO(authorLogic.getBooks(authorsId));
    }

    @PUT
    public List<BookDetailDTO> updateAuthorBooks(@PathParam("authorsId") Long authorsId, List<BookDetailDTO> books) throws BusinessLogicException {
        AuthorEntity entity = authorLogic.getAuthor(authorsId);
        if (entity == null) {
            throw new WebApplicationException("El autor no existe", 404);
        }
        return listBookEntity2DetailDTO(authorLogic.replaceBooks(listDetailDTO2Entitys(books), authorsId));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteAuthorBook(@PathParam("authorsId") Long authorsId, @PathParam("id") Long id) throws BusinessLogicException {
        AuthorEntity entity = authorLogic.getAuthor(authorsId);
        if (entity == null) {
            throw new WebApplicationException("El autor no existe", 404);
        }
        authorLogic.removeBook(authorsId, id);
    }

    private List<BookEntity> listDetailDTO2Entitys(List<BookDetailDTO> bookList) {
        List<BookEntity> list = new ArrayList<>();
        for (BookDetailDTO book : bookList) {
            list.add(book.toEntity());
        }
        return list;
    }

    private List<BookDetailDTO> listBookEntity2DetailDTO(List<BookEntity> entityList) {
        List<BookDetailDTO> list = new ArrayList<>();
        for (BookEntity entity : entityList) {
            list.add(new BookDetailDTO(entity));
        }
        return list;
    }
}

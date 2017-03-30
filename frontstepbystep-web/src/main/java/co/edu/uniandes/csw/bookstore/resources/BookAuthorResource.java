/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.AuthorDetailDTO;
import co.edu.uniandes.csw.bookstore.ejb.BookLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
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
public class BookAuthorResource {

    @Inject
    BookLogic bookLogic;

    @GET

    public List<AuthorDetailDTO> getBookAuthors(@PathParam("booksId") Long booksId) throws BusinessLogicException {
        BookEntity entity = bookLogic.getBook(booksId);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        return listAuthorEntity2DetailDTO(bookLogic.getBookAuthors(booksId));
    }

    @PUT
    public List<AuthorDetailDTO> updateBookAuthors(@PathParam("booksId") Long booksId, List<AuthorDetailDTO> authors) throws BusinessLogicException {
        BookEntity entity = bookLogic.getBook(booksId);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        return listAuthorEntity2DetailDTO(bookLogic.replaceAuthors(listDetailDTO2Entitys(authors), booksId));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteBookAuthor(@PathParam("booksId") Long booksId, @PathParam("id") Long id) throws BusinessLogicException {
        BookEntity entity = bookLogic.getBook(booksId);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        bookLogic.deleteAuthor(booksId, id);
    }

    private List<AuthorEntity> listDetailDTO2Entitys(List<AuthorDetailDTO> authorList) {
        List<AuthorEntity> list = new ArrayList<>();
        for (AuthorDetailDTO author : authorList) {
            list.add(author.toEntity());
        }
        return list;
    }

    private List<AuthorDetailDTO> listAuthorEntity2DetailDTO(List<AuthorEntity> entityList) {
        List<AuthorDetailDTO> list = new ArrayList<>();
        for (AuthorEntity entity : entityList) {
            list.add(new AuthorDetailDTO(entity));
        }
        return list;
    }
}

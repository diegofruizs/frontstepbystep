/*
 * BookResource.java
 * Clase que representa el recurso "/books"
 * Implementa varios métodos para manipular las books
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.ejb.BookLogic;
import co.edu.uniandes.csw.bookstore.dtos.BookDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "books".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "books". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/books"
 *
 */
@Path("books")
@Produces("application/json")
@Consumes("application/json")
public class BookResource {

    @Inject
    BookLogic bookLogic;

    @GET
    public List<BookDetailDTO> getBooks() throws BusinessLogicException {
        return listBookEntity2DetailDTO(bookLogic.getBooks());
    }

    @GET
    @Path("{id: \\d+}")
    public BookDetailDTO getBook(@PathParam("id") Long id) throws BusinessLogicException {
        BookEntity entity = bookLogic.getBook(id);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        return new BookDetailDTO(entity);
    }

    @POST
    public BookDetailDTO createBook(BookDetailDTO book) throws BusinessLogicException {
        return new BookDetailDTO(bookLogic.createBook(book.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public BookDetailDTO updateBook(@PathParam("id") Long id, BookDetailDTO book) throws BusinessLogicException {
        BookEntity entity = bookLogic.getBook(id);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        return new BookDetailDTO(bookLogic.updateBook(id, book.toEntity()));
    }

    @DELETE
    @Path("{booksId: \\d+}")
    public void deleteBook(@PathParam("booksId") Long id) throws BusinessLogicException {
        BookEntity entity = bookLogic.getBook(id);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        bookLogic.deleteBook(id);
    }

    
    @Path("{booksId: \\d+}/authors")
    public AuthorBookResource getBookAuthorResource(@PathParam("booksId") Long booksId) {
         BookEntity entity = bookLogic.getBook(booksId);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        return new AuthorBookResource();
    }

  
  
    
    private List<BookDetailDTO> listBookEntity2DetailDTO(List<BookEntity> entityList) {
        List<BookDetailDTO> list = new ArrayList<>();
        for (BookEntity entity : entityList) {
            list.add(new BookDetailDTO(entity));
        }
        return list;
    }
    
    
}

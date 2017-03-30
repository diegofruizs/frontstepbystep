/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.ejb;


import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import co.edu.uniandes.csw.bookstore.persistence.PrizePersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author rcasalla
 */
public class PrizeLogic  {

    private static final Logger logger = Logger.getLogger(PrizeLogic.class.getName());
    @Inject
    private BookLogic bookLogic;

    @Inject
    private PrizePersistence prizePersistence;

    
    public List<PrizeEntity> getPrizes(Long bookId) {
        BookEntity book = bookLogic.getBook(bookId);
        return book.getPrizes();
    }

   
    public PrizeEntity getPrize(Long bookId, Long prizeId) {
        try {
            return prizePersistence.find(bookId, prizeId);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El premio no existe");
        }
    }

  
    public PrizeEntity createPrize(Long bookId, PrizeEntity prize) {
        BookEntity book = bookLogic.getBook(bookId);
        prize.setBook(book);
        prize = prizePersistence.create(prize);
        return prize;
    }

    
    public PrizeEntity updatePrize(Long bookId, PrizeEntity prize) {
        BookEntity book = bookLogic.getBook(bookId);
        prize.setBook(book);
        // Se puede cambiar el book?
        return prizePersistence.update(prize);
    }


    public void deletePrize(Long bookId, Long prizeId) {
        PrizeEntity old = getPrize(bookId, prizeId);
        prizePersistence.delete(old.getId());
    }
}
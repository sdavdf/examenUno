package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioBookImpl implements ServicioBook {
    @Inject
    EntityManager em;

    public void insertarBook(Book book){
    var tx = em.getTransaction();
    try {
        tx.begin();
        em.persist(book);
        tx.commit();
    }catch (Exception ex){
        tx.rollback();
        //ex.printStackTrace();
    }
    }

    @Override
    public Book buscarBook(Integer id) {
    return em.find(Book.class, id);
    }

    @Override
    public void actualizarBook(Book book) {
    var tx = em.getTransaction();
    try {
        tx.begin();
        em.merge(book);
        tx.commit();
    }catch (Exception ex){
        tx.rollback();
    }
    }

    @Override
    public void borrarBook(Integer id) {
    var tx = em.getTransaction();
    try {
        tx.begin();
            em.remove(this.buscarBook(id));
            tx.commit();
    }catch (Exception ex){
        tx.rollback();
    }
    }

    @Override
    public List<Book> buscarTodosBook() {
    return em.createQuery("select o from Book o").getResultList();
    }
}





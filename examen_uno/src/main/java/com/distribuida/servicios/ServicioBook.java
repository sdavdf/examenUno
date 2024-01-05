package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface ServicioBook {
  void insertarBook(Book book);
  Book buscarBook(Integer id);
  void actualizarBook(Book book);
  void borrarBook(Integer id);
  List<Book> buscarTodosBook();
}

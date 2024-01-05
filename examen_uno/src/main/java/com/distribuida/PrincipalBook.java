package com.distribuida;

import com.distribuida.db.Book;
import com.distribuida.servicios.ServicioBook;
import com.google.gson.Gson;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

public class PrincipalBook {
    static SeContainer container;

    static List<Book> buscarTodosBook(Request req, Response res) {
        var servicio = container.select(ServicioBook.class).get();

        res.type("application/json");
        return servicio.buscarTodosBook();
    }

    static Book buscarBook(Request req, Response res) {
        var servicio = container.select(ServicioBook.class).get();

        res.type("application/json");
        String _id = req.params(":id");

        var book = servicio.buscarBook(Integer.valueOf(_id));
        return book;
    }

    static Book insertarBook(Request req, Response res) {
        var servicio = container.select(ServicioBook.class).get();
        res.type("application/json");
        Gson gson = new Gson();
        Book nuevaBook = gson.fromJson(req.body(), Book.class);
        servicio.insertarBook(nuevaBook);
        return nuevaBook;
    }

    static Book actualizarBook(Request req, Response res) {
        var servicio = container.select(ServicioBook.class).get();
        res.type("application/json");
        Gson gson = new Gson();
        Book bookActualizada = gson.fromJson(req.body(), Book.class);
        servicio.actualizarBook(bookActualizada);
        return bookActualizada;
    }

    static String borrarBook(Request req, Response res) {
        try{
            var servicio = container.select(ServicioBook.class).get();
            res.type("application/json");
            String _id = req.params(":id");
            servicio.borrarBook(Integer.valueOf(_id));
            if (true){
                return "Book eliminada con éxito";
            }else{
                return "Book no encontrado para eliminar";
            }
        }catch (Exception e){
            return "No se puedo eliminar el Book";
        }
    }


    public static void main(String[] args) {

        container = SeContainerInitializer
                .newInstance()
                .initialize();

        ServicioBook servicio = container.select(ServicioBook.class).get();

        port(8081);

        Gson gson = new Gson();
        get("/books/:id", PrincipalBook::buscarBook, gson::toJson);
        get("/books", PrincipalBook::buscarTodosBook, gson::toJson);
        post("/books", PrincipalBook::insertarBook, gson::toJson);
        put("/books/:id", PrincipalBook::actualizarBook, gson::toJson);
        delete("/books/:id", PrincipalBook::borrarBook, gson::toJson);
    }
}


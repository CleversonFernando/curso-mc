package com.nelioalves.cursomc.services.exception;


import com.nelioalves.cursomc.services.CategoriaService;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String msg){
        super(msg);
    }
    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }

}
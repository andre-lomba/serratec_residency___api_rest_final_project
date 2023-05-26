package org.serratec.sales_manager_grupo5.exception;

public class UnauthorizedUserException extends RuntimeException {

    public UnauthorizedUserException(String menssagem) {
        super(menssagem);
    }

}

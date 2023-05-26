package org.serratec.sales_manager_grupo5.exception;

public class UnmatchingPasswordException extends RuntimeException {

    public UnmatchingPasswordException(String mensagem) {
        super(mensagem);
    }
}

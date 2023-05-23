package org.serratec.sales_manager_grupo5.exception;

import java.util.List;

public class ErroResposta {
    private String dataHora;
    private Integer status;
    private String titulo;
    private List<String> erros;

    public ErroResposta(String dataHora, Integer status, String titulo, List<String> erros) {
        this.dataHora = dataHora;
        this.status = status;
        this.titulo = titulo;
        this.erros = erros;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}

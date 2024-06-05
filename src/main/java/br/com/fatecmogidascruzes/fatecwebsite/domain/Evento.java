package br.com.fatecmogidascruzes.fatecwebsite.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "eventos")
public class Evento {
    private Long id;
    private String nome;
    private Date data;
    private String descricao;

    public Evento(String nome, Date data, String descricao) {
        setNome(nome);
        setData(data);
        setDescricao(descricao);
    }

    public Evento() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

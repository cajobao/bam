package br.iesb.bam.dados.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface DAO<T> {

    T incluir(T t) throws HibernateException;

    T alterar(T t) throws HibernateException;

    void excluir(T t) throws HibernateException;

    T consultar(Integer id) throws HibernateException;

    List<T> listar() throws HibernateException;

    List<T> consultarPorExemplo(T t) throws HibernateException;

    Session getSessao();

    void getCriteria();
}

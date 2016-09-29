package br.iesb.bam.util;

public class Constantes {

    //Unidade de persistencia
    public static final String PERSISTENCE_UNIT_NAME = "bam_homologacao";
    //usuario
    public static final String ID_USUARIO = "idUsuario";
    public static final String NM_USUARIO = "nmUsuario";
    public static final String DS_SENHA = "dsSenha";
    //obras

    //tipos de pessoa
    public static final String TIPO_ALUNO = "A";
    public static final String TIPO_PROFESSOR = "P";
    public static final String TIPO_FUNCIONARIO = "B";
    //tipos de obra

    public static final int TIPO_OBRA_LIVRO = 1;
    public static final int TIPO_OBRA_ENCICLOPEDIA = 2;
    public static final int TIPO_OBRA_ARTIGO = 3;
    public static final int TIPO_OBRA_PERIODICO = 4;
    
    //mensagens
    //sucesso
    public static final String MSGS1 = "Registro incluído com sucesso";
    public static final String MSGS2 = "Registro alterado com sucesso";
    public static final String MSGS3 = "Registro excluído com sucesso";
    //alerta
    public static final String MSGA1 = "Campo(s) obrigatório(s) não informado(s)";
    public static final String MSGA2 = "Nenhum registro encontrado para os parâmetros informados.";
    public static final String MSGA3 = "Preencha pelo menos um parâmetro para consulta";
    public static final String MSGA4 = "Tem certeza que excluir o(s) item(s) selecionado(s)?";
    public static final String MSGA5 = "Nenhum registro selecionado";
    public static final String MSGA6 = "Não é possível excluir este registro pois existem vínculos ativos";
    public static final String MSGA7 = "Só é permitido 5 obras por empréstimo";
    public static final String MSGA8 = "Este ítem já está adicionado à lista";
    public static final String MSGA9 = "Este registro já está cadastrado";
    public static final String MSGA10 = "Login e/ou senha inválidos";
    public static final String MSGA11 = "Pessoa não selecionada";
    public static final String MSGA12 = "Obra(s) não selecionada(s)";
    //erro
    public static final String MSGE1 = "Ocorreu um erro de banco de dados";

    

}

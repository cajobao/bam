package br.iesb.bam.util;

import org.apache.commons.lang.StringUtils;

/**
 * Utilitário para strings.
 */
public final class StringUtil {

    private static final int NUMERO_UM = 1;

    private static final String AA_EE_II_OO_UU_YY = "AaEeIiOoUuYy";

    private static final String STRING_ESPACO = " ";

    private static final String UNICODE =
        "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"             
        + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" 
        + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" 
        + "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1"
        + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" 
        + "\u00C5\u00E5"                                                             
        + "\u00C7\u00E7" 
        + "\u0150\u0151\u0170\u0171";

    private static final String PLAIN_ASCII =
        "AaEeIiOoUu"    // grave
        + AA_EE_II_OO_UU_YY
        + AA_EE_II_OO_UU_YY
        + "AaOoNn"        // tilde
        + AA_EE_II_OO_UU_YY
        + "Aa"            // ring
        + "Cc"            // cedilla
        + "OoUu";          // double acute

    private static final int QUATRO = 4;

    private StringUtil() {
        //Construtor privado
    }

    /**
     * Formata uma {@link String} para inicias maiúsculas, separada por espaços em branco
     * <pre>
     * Exemplo: 
     *   Entrada: JOAO DA SILVA
     *   Saída:   Joao da Silva
     * </pre>  
     * @param value Valor a ser formatado 
     * @return valor formatado
     */
    public static String getNomeFormatado(final String value) {
        final String[] valores = value.toLowerCase().trim().split(STRING_ESPACO);
        final String[] reservados = {"da", "de", "do", "das", "dos", "e"};
        final StringBuffer out = new StringBuffer("");
        for (String valor : valores) {
            if (contemAlgumResevado(valor, reservados)) {
                out.append(valor);
            } else {
                out.append(StringUtils.capitalize(valor));
            }
            out.append(STRING_ESPACO);
        }
        return out.toString().trim(); 
    }

    /**
     * Retorna apena o primeiro nome, separado por espaço.
     * 
     * @param nomeCompleto Nome completo.
     * @return primeio nome
     */
    public static String getPrimeiroNome(final String nomeCompleto) {
        return nomeCompleto.split(STRING_ESPACO)[0];
    }

    /**
     * Verifica se a string contém algum valor reservado. 
     * 
     * @param valor Valor a ser verificado
     * @param reservados Valores reservados
     * @return <code>true</code>, se contém algum valor reservado;
     *         <code>false</code>, se não.
     */
    private static boolean contemAlgumResevado(final String valor, final String[] reservados) {
        boolean contem = false;
        for (String reservado : reservados) {
            if (StringUtils.containsOnly(valor, reservado)) {
                contem = true;
            }
        }
        return contem;
    }

    /**
     * Remove os acentos de uma string
     * 
     * @param string com acentuação
     * @return string sem acentuação
     */
    public static String removerAcentos(final String acentuada) {
        String resposta = acentuada;
        
        if (!isBlank(acentuada)) {
            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < acentuada.length(); i++) {
                final char caracter = acentuada.charAt(i);
                final int pos = UNICODE.indexOf(caracter);
                if (pos > -1) {
                    builder.append(PLAIN_ASCII.charAt(pos));
                } else {
                    builder.append(caracter);
                }
            }
            resposta = builder.toString();
        }
        return resposta;
    }

    /**
     * Verifica se a string é vazia
     *
     * @param string String a ser verificada
     * @return <code>true</code>, se vazia; <code>false</code> se não.
     */
    public static boolean isBlank(final String string) {
        return StringUtils.isBlank(string);
    }

    /**
     * Preenche a string com o caracter à esquerda.
     * 
     * @param texto String a ser preenchida
     * @param caracter Caracter
     * @param size 
     * @return string concatenada com o caracter
     */
    public static String leftFill(final String texto, final char caracter, final int size) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (size - texto.length()); i++) {
            builder.append(caracter);
        }
        builder.append(texto);
        return builder.toString();
    }

    /**
     * Preenche a string com o caracter à direita.
     * 
     * @param texto String a ser preenchida
     * @param caracter Caracter
     * @param size
     * @return string concatenada com o caracter
     */
    public static String rightFill(final String texto, final char caracter, final int size) {
        final StringBuilder builder = new StringBuilder(texto);
        for (int i = 0; i < (size - texto.length()); i++) {
            builder.append(caracter);
        }
        return builder.toString();
    }

    /**
     * Passa o primeiro caracter da string para UpperCase
     * 
     * @param string String a ser alterada
     * @return strinc com o primeiro caracter em UpperCase
     */
    public static String firstToUpper(final String string) {
        String retorno = string;
        if (!isBlank(string)) {
            retorno = string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
        }
        return retorno;
    }

    /**
     * Passa o primeiro caracter da string para LowerCase
     * 
     * @param string String a ser alterada
     * @return strinc com o primeiro caracter em LowerCase
     */
    public static String firstToLower(final String string) {
        String retorno = string;
        if (!isBlank(string)) {
            retorno = string.substring(0, NUMERO_UM).toLowerCase() + string.substring(NUMERO_UM, string.length());
        }
        return retorno;
    }

    /**
     * Monta uma string separada pelo 'separador' com os valores do array
     *  
     * @param array Array de string
     * @param separador Separado da string
     * @return String 
     */
    public static String arrayToString(final String[] array, final String separador) {
        final StringBuilder retorno = new StringBuilder();
        for (String str : array) {
            retorno.append(str + separador);
        }
        if (retorno.length() > 0) {
            retorno.delete(retorno.length() - separador.length(), retorno.length());
        }
        return retorno.toString();
    }

    /**
     * Remove as máscaras da string
     *
     * @param string
     * @return string sem máscaras
     */
    public static String removerMascaras(final String string) {
        String retorno = string;
        if (!isBlank(string)) {
            retorno = string.replaceAll("\\p{Punct}", "").trim();
            retorno = retorno.replaceAll(STRING_ESPACO, "").trim();
        }
        return retorno;
    }

    /**
     * Transforma uma string <code>null</code> em <code>""</code>.<br/>
     * Caso a string não seja <code>null</code>, retorna seu valor. 
     * @param string
     * @return string
     */
    public static String nullSafeGet(final String string) {
        String retorno = string;
        if (string == null) {
            retorno = "";
        }
        return retorno;
    }

    /**
     * Retorna os quatro primeiros caracteres de uma string
     *
     * @param string
     * @return quatro primeiros caracteres de uma string
     */
    public static String getPrimeirosQuatroCaracteres(final String string) {
        return string.substring(0, QUATRO);
    }

    /**
     * Converte o valor de uma {@link String} para Hexadecimal
     *
     * @param valor
     * @return valor da string em Hexadecimal
     */
    public static String converterParaHexadecimal(final String valor) {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < valor.length(); i++) {
            buffer.append(Integer.toHexString(valor.charAt(i)));
        }
        return buffer.toString();
    }

    /**
     * Converte o valor de uma {@link String} para int.
     * Caso o valor informado seja <code>null</code>, retorna <code>zero</code>.
     * 
     * @param valor Valor a ser convertido.
     * @return valor <code>int</code>
     */
    public static int toInt(final String valor) {
        int resposta = 0;
        try {
            if (valor != null) {
                resposta = Integer.parseInt(valor);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Conversão de valor inteiro errado:" + valor, e); //NOPMD
        }
        return resposta;
    }

    /**
     * Converte o valor de uma {@link String} para <code>long</code>.
     * Caso o valor informado seja <code>null</code>, retorna <code>zero</code>.
     * 
     * @param valor Valor a ser convertido.
     * @return valor <code>long</code>
     */
    public static long toLong(final String valor) {
        long resposta = 0;
        try {
            if (valor != null) {
                resposta = Long.parseLong(valor);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Conversão de valor long errado:" + valor, e); //NOPMD
        }
        return resposta;
    }
    
    /**
     * Completa uma string à esquerda com o caracter informado para a quantidade de dígitos necessários na string
     *
     * @param valor
     * @param digitos
     * @param caracter
     * @return
     */
    public static String preencherAEsquerda(String valor, int digitos, char caracter) {
        String retorno = valor;
        int length = retorno.length();
        int diff;
        if (length != digitos) {
            if (length > digitos) {
                diff = length - digitos;
                retorno = retorno.substring(diff);
            } else {
                diff = digitos - length;
                for (int i = 0; i < diff; i++) {
                    retorno = caracter + retorno;
                }
            }
        }
        return retorno;
    }
}

package br.com.conceito.bradipe.util;

import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;

import java.math.BigDecimal;

public final class UsuarioUtil {

    private UsuarioUtil() {
        // evita instanciar
    }

    public static class UsuarioInfo {
        private final String login;
        private final String nome;
        private final String email;

        public UsuarioInfo(String login, String nome, String email) {
            this.login = login;
            this.nome = nome;
            this.email = email;
        }

        public String getLogin() { return login; }
        public String getNome()  { return nome; }
        public String getEmail() { return email; }
    }

    public static UsuarioInfo buscar(ContextoAcao ctx, BigDecimal codUsuario) throws Exception {
        if (codUsuario == null) {
            throw new Exception("CODUSU não informado.");
        }

        QueryExecutor query = ctx.getQuery();
        try {
            query.setParam("CODUSU", codUsuario);
            query.nativeSelect(
                    "SELECT NOMEUSU, NVL(NOMEUSUCPLT, NOMEUSU) AS NOME, EMAIL " +
                            "FROM TSIUSU " +
                            "WHERE CODUSU = {CODUSU}"
            );

            if (query.next()) {
                String login = query.getString("NOMEUSU");
                String nome  = query.getString("NOME");
                String email = query.getString("EMAIL");
                return new UsuarioInfo(login, nome, email);
            }

            // não achou: você pode retornar null ou lançar exceção
            throw new Exception("Nenhum usuário encontrado para CODUSU=" + codUsuario);

        } finally {
            query.close();
        }
    }

    public static String  listaEmailValidadores(ContextoAcao ctx) throws Exception {

        QueryExecutor q = ctx.getQuery();
        StringBuilder emails = new StringBuilder();

        try {
            q.nativeSelect(
                    "SELECT EMAIL " +
                            "FROM TSIUSU " +
                            "WHERE AD_VALIDAPRECADASTRO = 'S' " + // add TSIUSU, Texto CheckBox
                            "AND EMAIL IS NOT NULL"
            );

            while (q.next()) {
                String email = q.getString("EMAIL");

                if (email != null && !email.trim().isEmpty()) {
                    if (emails.length() > 0) {
                        emails.append(";"); // separador padrão de e-mail
                    }
                    emails.append(email.trim());
                }
            }

            return emails.toString();

        } finally {
            q.close();
        }
    }
}


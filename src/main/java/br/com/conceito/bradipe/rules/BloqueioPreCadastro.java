package br.com.conceito.bradipe.rules;

import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.regrasnegocio.ContextoRegra;
import br.com.sankhya.extensions.regrasnegocio.RegraNegocioJava;

import java.math.BigDecimal;

public class BloqueioPreCadastro implements RegraNegocioJava {

    @Override
    public void executa(ContextoRegra contexto) throws Exception {

        BigDecimal nuNota = contexto.getNunota();
        BigDecimal sequencia = contexto.getSequencia();

        if (nuNota == null || sequencia == null) return;

        BigDecimal codProd = null;
        String descrProd = null;

        // 1) Descobre produto do item
        QueryExecutor q = contexto.getQuery();
        try {
            q.setParam("NUNOTA", nuNota);
            q.setParam("SEQUENCIA", sequencia);

            q.nativeSelect(
                    "SELECT ITE.CODPROD, PRO.DESCRPROD " +
                            "  FROM TGFITE ITE " +
                            "  INNER JOIN TGFPRO PRO ON PRO.CODPROD = ITE.CODPROD " +
                            " WHERE ITE.NUNOTA = {NUNOTA} " +
                            "   AND ITE.SEQUENCIA = {SEQUENCIA}"
            );

            if (q.next()) {
                codProd = q.getBigDecimal("CODPROD");
                descrProd = q.getString("DESCRPROD");
            }
        } finally {
            q.close();
        }

        if (codProd == null) return;

        // 2) Verifica flag no produto
        String liberado = null;
        QueryExecutor q2 = contexto.getQuery();
        try {
            q2.setParam("CODPROD", codProd);
            q2.nativeSelect("SELECT AD_CADLIBERADO FROM TGFPRO WHERE CODPROD ={CODPROD}");

            if (q2.next()) {
                liberado = q2.getString("AD_CADLIBERADO");
            }
        } finally {
            q2.close();
        }

        if ("N".equalsIgnoreCase(liberado)) {
            String msg =
                    "Produto bloqueado para faturamento (Pré-cadastro).\n" +
                            "Produto: " + codProd + " - " + (descrProd != null ? descrProd : "") + "\n" +
                            "Solicite a liberação do cadastro antes de prosseguir.";

            contexto.setSucesso(false);
            contexto.setMensagem(msg);
            contexto.mostraErro(msg);
        }
    }
}

package br.com.conceito.bradipe.actions;

import br.com.conceito.bradipe.service.registro.AtualizarProduto;
import br.com.conceito.bradipe.util.ParamUtil;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class CopiarProdutoSimilar implements AcaoRotinaJava {

    @Override
    public void doAction(ContextoAcao ctx) throws Exception {

        Registro[] selecionados = ctx.getLinhas();
        if (selecionados == null || selecionados.length == 0) {
            ctx.setMensagemRetorno("Nenhum registro selecionado. Selecione ao menos 1 produto na grade para aplicar a referência.");
            return;
        }

        BigDecimal codUsuLogado = ctx.getUsuarioLogado();
        String codNcm = ParamUtil.getStringOpcional(ctx, "NCM");
        BigDecimal codProd = ParamUtil.getBigDecimalOpcional(ctx, "CODPROD");

        if (codNcm == null && codProd == null) {
            ctx.mostraErro("Para cópia de Produto Similar, informe o NCM ou o Código do Produto.");
            return;
        }

        if (codProd != null && codProd.signum() <= 0) {
            ctx.mostraErro("Código do Produto inválido.");
            return;
        }

        if (codNcm != null && codNcm.length() != 8) {
            ctx.mostraErro("NCM inválido. Informe 8 dígitos.");
            return;
        }

        AtualizarProduto copiarProduto = new AtualizarProduto();
        int qtd = 0;
        for (Registro registro : selecionados) {

            BigDecimal codAlvo = (BigDecimal) registro.getCampo("CODPROD");
            if (codAlvo == null) continue;

            copiarProduto.atualizaProdutoSimilar(ctx, codNcm, codProd, codAlvo);
            qtd++;
        }

        ctx.setMensagemRetorno(qtd + " produto(s) atualizado(s) com sucesso!");
    }

}


package br.com.conceito.bradipe.service.registro;

import br.com.conceito.bradipe.service.produto.ProdutoSimilarService;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;

import java.math.BigDecimal;

public class AtualizarProduto {

    public void atualizaProdutoSimilar(ContextoAcao ctx, String ncm, BigDecimal produtoSimilar, BigDecimal produtoSelecionado) throws Exception {

        ProdutoSimilarService similar = new ProdutoSimilarService();
        similar.buscaPorNCMProduto(ctx, ncm, produtoSimilar);

        try {
            QueryExecutor q = ctx.getQuery();

            q.setParam("CODPROD", produtoSelecionado);

            // Params vindos do ProdutoSimilarService (fonte)
            q.setParam("CODGRUPOPROD", similar.getCodGrupo());
            q.setParam("NCM", similar.getCodigoNcm());
            q.setParam("USOPROD", similar.getUsoProd());
            q.setParam("ORIGPROD", similar.getOrigProd());
            q.setParam("ICMSGERENCIA", similar.getIcmsGerencia());
            q.setParam("CODLOCALPADRAO", similar.getCodLocalPadrao());

            q.setParam("TEMICMS", similar.getTemICMS());
            q.setParam("GRUPOICMS", similar.getGrupoICMS());
            q.setParam("GRUPOICMS2", similar.getGrupoICMS2());
            q.setParam("CALCDIFAL", similar.getCalcDifal());
            q.setParam("CLASSUBTRIB", similar.getClasSubTrib());
            q.setParam("TIPSUBST", similar.getTipSubst());
            q.setParam("CODESPECST", similar.getCodEspeCST());

            q.setParam("TIPGTINNFE", similar.getTipGtinNfe());
            q.setParam("PRODUTONFE", similar.getProdutoNfe());
            q.setParam("GRUPOCSSL", similar.getGrupoCSLL());
            q.setParam("GRUPOPIS", similar.getGrupoPis());
            q.setParam("GRUPOCOFINS", similar.getGrupoCofins());
            q.setParam("GRUPOIBSCBS", similar.getGrupoIbsCbs());

            q.setParam("CODIPI", similar.getCodIpi());
            q.setParam("TEMIPICOMPRA", similar.getTemIpiCompra());
            q.setParam("TEMIPIVENDA", similar.getTemIpiVenda());
            q.setParam("CODENQIPIENT", similar.getCodEnqIpiEnt());
            q.setParam("CODENQIPISAI", similar.getCodEnqIpSai());
            q.setParam("CSTIPIENT", similar.getCstIpiEnt());
            q.setParam("CSTIPISAI", similar.getCstIpiSai());

            q.setParam("USALOCAL", similar.getUsaLocal());
            q.setParam("CALCULOGIRO", similar.getCalculoGiro());
            q.setParam("CALRUPTURAESTOQUE", similar.getCalRuputuraEstoque());

            q.setParam("APURAPRODEPE", similar.getApuraProdepe());
            q.setParam("INDESPPRODEPE", similar.getIndEspProdepe());

            q.update(
                    "UPDATE TGFPRO SET " +
                            " NCM = {NCM}, " +
                            " CODGRUPOPROD = {CODGRUPOPROD}, " +
                            " USOPROD = {USOPROD}, " +
                            " ORIGPROD = {ORIGPROD}, " +
                            " ICMSGERENCIA = {ICMSGERENCIA}, " +
                            " CODLOCALPADRAO = {CODLOCALPADRAO}, " +
                            " TEMICMS = {TEMICMS}, " +
                            " GRUPOICMS = {GRUPOICMS}, " +
                            " GRUPOICMS2 = {GRUPOICMS2}, " +
                            " CALCDIFAL = {CALCDIFAL}, " +
                            " CLASSUBTRIB = {CLASSUBTRIB}, " +
                            " TIPSUBST = {TIPSUBST}, " +
                            " CODESPECST = {CODESPECST}, " +
                            " TIPGTINNFE = {TIPGTINNFE}, " +
                            " PRODUTONFE = {PRODUTONFE}, " +
                            " GRUPOCSSL = {GRUPOCSSL}, " +
                            " GRUPOPIS = {GRUPOPIS}, " +
                            " GRUPOCOFINS = {GRUPOCOFINS}, " +
                            " GRUPOIBSCBS = {GRUPOIBSCBS}, " +
                            " CODIPI = {CODIPI}, " +
                            " TEMIPICOMPRA = {TEMIPICOMPRA}, " +
                            " TEMIPIVENDA = {TEMIPIVENDA}, " +
                            " CODENQIPIENT = {CODENQIPIENT}, " +
                            " CODENQIPISAI = {CODENQIPISAI}, " +
                            " CSTIPIENT = {CSTIPIENT}, " +
                            " CSTIPISAI = {CSTIPISAI}, " +
                            " USALOCAL = {USALOCAL}, " +
                            " CALCULOGIRO = {CALCULOGIRO}, " +
                            " CALRUPTURAESTOQUE = {CALRUPTURAESTOQUE}, " +
                            " APURAPRODEPE = {APURAPRODEPE}, " +
                            " INDESPPRODEPE = {INDESPPRODEPE} " +
                            "WHERE CODPROD = {CODPROD}"
            );

        } catch (Exception e) {
            ctx.mostraErro("Erro ao tentar atualizar (item): " + produtoSelecionado + ". \n" + e.getMessage());
        }
    }
}

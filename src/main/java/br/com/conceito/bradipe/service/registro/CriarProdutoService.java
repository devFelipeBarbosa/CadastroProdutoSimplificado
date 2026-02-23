package br.com.conceito.bradipe.service.registro;

import br.com.conceito.bradipe.service.produto.ProdutoSimilarService;
import br.com.conceito.bradipe.util.UsuarioUtil;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class CriarProdutoService {

    public static class ResultadoCriacao {
        private final BigDecimal codProd;
        private final String descrProd;
        private final String emailSolicitante;
        private final String emailValidadores;

        public ResultadoCriacao(BigDecimal codProd, String descrProd, String emailSolicitante, String emailValidadores) {
            this.codProd = codProd;
            this.descrProd = descrProd;
            this.emailSolicitante = emailSolicitante;
            this.emailValidadores = emailValidadores;
        }

        public BigDecimal getCodProd() {
            return codProd;
        }

        public String getDescrProd() {
            return descrProd;
        }

        public String getEmailSolicitante() {
            return emailSolicitante;
        }

        public String getEmailValidadores() {
            return emailValidadores;
        }


    }

    public ResultadoCriacao CriarProduto(ContextoAcao ctx,
                                         String descrProd,
                                         String codVol,
                                         BigDecimal codGrupoProd,
                                         String referencia,
                                         String refForn,
                                         String codNcm,
                                         BigDecimal codProd,
                                         String marca,
                                         String fabricante,
                                         String similar,
                                         String imp,
                                         String obs) throws Exception {

        //cria uma nova linha na TGFPRO
        Registro novoItem = ctx.novaLinha("TGFPRO");
        ProdutoSimilarService ps = new ProdutoSimilarService();

        if ("S".equalsIgnoreCase(similar)) {
            ps.buscaPorNCMProduto(ctx, codNcm, codProd);
            novoItem.setCampo("AD_CODPRODSIMILAR", ps.getCodProd());               // variavel add TGFPRO, Inteiro (Padrão)
            novoItem.setCampo("AD_DESCRSIMILAR", ps.getDescrProd());               // variavel add TGFPRO, Texto (Padrão)
            novoItem.setCampo("USOPROD", ps.getUsoProd());
            novoItem.setCampo("ORIGPROD", ps.getOrigProd());
            novoItem.setCampo("ICMSGERENCIA", ps.getIcmsGerencia());
            novoItem.setCampo("CODLOCALPADRAO",  ps.getCodLocalPadrao());
            novoItem.setCampo("TEMICMS", ps.getTemICMS());
            novoItem.setCampo("GRUPOICMS",  ps.getGrupoICMS());
            novoItem.setCampo("GRUPOICMS2", ps.getGrupoICMS2());
            novoItem.setCampo("CALCDIFAL", ps.getCalcDifal());
            novoItem.setCampo("CLASSUBTRIB", ps.getClasSubTrib());
            novoItem.setCampo("TIPSUBST", ps.getTipSubst());
            novoItem.setCampo("CODESPECST", ps.getCodEspeCST());
            novoItem.setCampo("TIPGTINNFE", ps.getTipGtinNfe());
            novoItem.setCampo("PRODUTONFE", ps.getProdutoNfe());
            novoItem.setCampo("GRUPOCSSL", ps.getGrupoCSLL());
            novoItem.setCampo("GRUPOPIS", ps.getGrupoPis());
            novoItem.setCampo("GRUPOCOFINS", ps.getGrupoCofins());
            novoItem.setCampo("GRUPOIBSCBS", ps.getGrupoIbsCbs());
            novoItem.setCampo("CODIPI", ps.getCodIpi());
            novoItem.setCampo("TEMIPICOMPRA", ps.getTemIpiCompra());
            novoItem.setCampo("TEMIPIVENDA", ps.getTemIpiVenda());
            novoItem.setCampo("CODENQIPIENT", ps.getCodEnqIpiEnt());
            novoItem.setCampo("CODENQIPISAI", ps.getCodEnqIpSai());
            novoItem.setCampo("CSTIPIENT", ps.getCstIpiEnt());
            novoItem.setCampo("CSTIPISAI", ps.getCstIpiSai());
            novoItem.setCampo("USALOCAL", ps.getUsaLocal());
            novoItem.setCampo("CALCULOGIRO", ps.getCalculoGiro());
            novoItem.setCampo("CALRUPTURAESTOQUE", ps.getCalRuputuraEstoque());

        } else {
            novoItem.setCampo("CSTIPIENT", 49);
            novoItem.setCampo("CSTIPISAI", 99);
            novoItem.setCampo("USOPROD", 'R');
            novoItem.setCampo("USALOCAL", 'S');

            if ("S".equalsIgnoreCase(imp)) {
                novoItem.setCampo("ORIGPROD", '2');
            }
        }

        //Campos Obrigatórios
        if ("S".equalsIgnoreCase(similar) && codGrupoProd == null) {
            novoItem.setCampo("CODGRUPOPROD", ps.getCodGrupo());
        } else {
            novoItem.setCampo("CODGRUPOPROD", codGrupoProd);
        }

        if("S".equalsIgnoreCase(similar) && codNcm == null) {
            novoItem.setCampo("NCM", ps.getCodigoNcm());
        } else {
            novoItem.setCampo("NCM", codNcm);
        }

        novoItem.setCampo("DESCRPROD", descrProd);
        novoItem.setCampo("CODVOL", codVol);
        novoItem.setCampo("REFERENCIA", referencia);

        if (marca != null && !marca.trim().isEmpty()) {
            novoItem.setCampo("MARCA", marca.trim().toUpperCase());
        }

        if (fabricante != null && !fabricante.trim().isEmpty()) {
            novoItem.setCampo("FABRICANTE", fabricante.trim().toUpperCase());
        }

        if (refForn != null && !refForn.trim().isEmpty()) {
            novoItem.setCampo("REFFORN", refForn.trim().toUpperCase());
        }

        novoItem.setCampo("AD_OBSPRECADASTRO", obs);                            // variavel add TGFPRO, Texto (Caixa de Texto)
        novoItem.setCampo("AD_CADLIBERADO", 'N');                         // variavel add TGFPRO, Texto (CheckBox)
        novoItem.setCampo("AD_DTSOLICITACADASTRO", new java.util.Date());       // variavel add TGFPRO, Data

        UsuarioUtil.UsuarioInfo usuario = UsuarioUtil.buscar(ctx, ctx.getUsuarioLogado());
        String nomeUsu = ctx.getUsuarioLogado() + " - " + usuario.getNome();
        String emailUsu = usuario.getEmail();
        String emailVal = UsuarioUtil.listaEmailValidadores(ctx);

        novoItem.setCampo("AD_SOLICITANTECADASTRO", nomeUsu);                   // variavel add TGFPRO, texto (Padrão)

        novoItem.save();

        BigDecimal codProdGerado = (BigDecimal) novoItem.getCampo("CODPROD");
        String descrSalva = (String) novoItem.getCampo("DESCRPROD");

        return new ResultadoCriacao(codProdGerado, descrSalva, emailUsu, emailVal);

    }

}

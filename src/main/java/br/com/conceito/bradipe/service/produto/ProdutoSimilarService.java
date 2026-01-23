package br.com.conceito.bradipe.service.produto;

import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;

import java.math.BigDecimal;

public class ProdutoSimilarService {

    private int codProd, codGrupo, codLocalPadrao, grupoICMS, grupoICMS2, clasSubTrib;
    private int tipGtinNfe, produtoNfe, grupoIbsCbs, codIpi, codEnqIpiEnt, codEnqIpSai, cstIpiEnt, cstIpiSai, codEspeCST;
    private String descrProd, codigoNcm, origProd, usoProd, icmsGerencia, temICMS, calcDifal, tipSubst, grupoCSLL, grupoPis;
    private String grupoCofins, temIpiCompra, temIpiVenda, usaLocal, calculoGiro, calRuputuraEstoque;

    public String getCodigoNcm() {
        return codigoNcm;
    }

    public String getOrigProd() {
        return origProd;
    }

    public void setOrigProd(String origProd) {
        this.origProd = origProd;
    }

    public void setCodigoNcm(String codigoNcm) {
        this.codigoNcm = codigoNcm;
    }

    public int getCodEnqIpSai() {
        return codEnqIpSai;
    }

    public void setCodEnqIpSai(int codEnqIpSai) {
        this.codEnqIpSai = codEnqIpSai;
    }

    public int getTipGtinNfe() {
        return tipGtinNfe;
    }

    public void setTipGtinNfe(int tipGtinNfe) {
        this.tipGtinNfe = tipGtinNfe;
    }

    public String getDescrProd() {
        return descrProd;
    }

    public void setDescrProd(String descrProd) {
        this.descrProd = descrProd;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    public int getCodLocalPadrao() {
        return codLocalPadrao;
    }

    public void setCodLocalPadrao(int codLocalPadrao) {
        this.codLocalPadrao = codLocalPadrao;
    }

    public int getGrupoICMS() {
        return grupoICMS;
    }

    public void setGrupoICMS(int grupoICMS) {
        this.grupoICMS = grupoICMS;
    }

    public int getGrupoICMS2() {
        return grupoICMS2;
    }

    public void setGrupoICMS2(int grupoICMS2) {
        this.grupoICMS2 = grupoICMS2;
    }

    public int getClasSubTrib() {
        return clasSubTrib;
    }

    public void setClasSubTrib(int clasSubTrib) {
        this.clasSubTrib = clasSubTrib;
    }

    public int getProdutoNfe() {
        return produtoNfe;
    }

    public void setProdutoNfe(int produtoNfe) {
        this.produtoNfe = produtoNfe;
    }

    public int getGrupoIbsCbs() {
        return grupoIbsCbs;
    }

    public void setGrupoIbsCbs(int grupoIbsCbs) {
        this.grupoIbsCbs = grupoIbsCbs;
    }

    public int getCodIpi() {
        return codIpi;
    }

    public void setCodIpi(int codIpi) {
        this.codIpi = codIpi;
    }

    public int getCodEnqIpiEnt() {
        return codEnqIpiEnt;
    }

    public void setCodEnqIpiEnt(int codEnqIpiEnt) {
        this.codEnqIpiEnt = codEnqIpiEnt;
    }

    public int getCstIpiEnt() {
        return cstIpiEnt;
    }

    public void setCstIpiEnt(int cstIpiEnt) {
        this.cstIpiEnt = cstIpiEnt;
    }

    public int getCstIpiSai() {
        return cstIpiSai;
    }

    public void setCstIpiSai(int cstIpiSai) {
        this.cstIpiSai = cstIpiSai;
    }

    public String getUsoProd() {
        return usoProd;
    }

    public void setUsoProd(String usoProd) {
        this.usoProd = usoProd;
    }

    public String getIcmsGerencia() {
        return icmsGerencia;
    }

    public void setIcmsGerencia(String icmsGerencia) {
        this.icmsGerencia = icmsGerencia;
    }

    public String getTemICMS() {
        return temICMS;
    }

    public void setTemICMS(String temICMS) {
        this.temICMS = temICMS;
    }

    public String getCalcDifal() {
        return calcDifal;
    }

    public void setCalcDifal(String calcDifal) {
        this.calcDifal = calcDifal;
    }

    public String getTipSubst() {
        return tipSubst;
    }

    public void setTipSubst(String tipSubst) {
        this.tipSubst = tipSubst;
    }

    public int getCodEspeCST() {
        return codEspeCST;
    }

    public void setCodEspeCST(int codEspeCST) {
        this.codEspeCST = codEspeCST;
    }

    public String getGrupoCSLL() {
        return grupoCSLL;
    }

    public void setGrupoCSLL(String grupoCSLL) {
        this.grupoCSLL = grupoCSLL;
    }

    public String getGrupoPis() {
        return grupoPis;
    }

    public void setGrupoPis(String grupoPis) {
        this.grupoPis = grupoPis;
    }

    public String getGrupoCofins() {
        return grupoCofins;
    }

    public void setGrupoCofins(String grupoCofins) {
        this.grupoCofins = grupoCofins;
    }

    public String getTemIpiCompra() {
        return temIpiCompra;
    }

    public void setTemIpiCompra(String temIpiCompra) {
        this.temIpiCompra = temIpiCompra;
    }

    public String getTemIpiVenda() {
        return temIpiVenda;
    }

    public void setTemIpiVenda(String temIpiVenda) {
        this.temIpiVenda = temIpiVenda;
    }

    public String getUsaLocal() {
        return usaLocal;
    }

    public void setUsaLocal(String usaLocal) {
        this.usaLocal = usaLocal;
    }

    public String getCalculoGiro() {
        return calculoGiro;
    }

    public void setCalculoGiro(String calculoGiro) {
        this.calculoGiro = calculoGiro;
    }

    public String getCalRuputuraEstoque() {
        return calRuputuraEstoque;
    }

    public void setCalRuputuraEstoque(String calRuputuraEstoque) {
        this.calRuputuraEstoque = calRuputuraEstoque;
    }

    public void buscaPorNCMProduto(ContextoAcao res, String ncm, BigDecimal produto) throws Exception {

        String ncmNorm = (ncm == null) ? null : ncm.trim();
        if (ncmNorm != null && ncmNorm.isEmpty()) ncmNorm = null;

        BigDecimal prodNorm = (produto != null && produto.signum() > 0) ? produto : null;

        if (ncmNorm == null && prodNorm == null) {
            res.mostraErro("Informe o NCM ou o Código do Produto para buscar produto similar.");
            return;
        }

        QueryExecutor query = res.getQuery();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("PRD.CODPROD,PRD.DESCRPROD,PRD.NCM,PRD.CODGRUPOPROD,PRD.USOPROD,PRD.ORIGPROD,PRD.ICMSGERENCIA,PRD.CODLOCALPADRAO,")
                .append("PRD.TEMICMS,PRD.GRUPOICMS,PRD.GRUPOICMS2,PRD.CALCDIFAL,PRD.CLASSUBTRIB,PRD.TIPSUBST,PRD.CODESPECST,")
                .append("PRD.TIPGTINNFE,PRD.PRODUTONFE,PRD.GRUPOCSSL,PRD.GRUPOPIS,PRD.GRUPOCOFINS,PRD.GRUPOIBSCBS,")
                .append("PRD.CODIPI,PRD.TEMIPICOMPRA,PRD.TEMIPIVENDA,PRD.CODENQIPIENT,PRD.CODENQIPISAI,PRD.CSTIPIENT,PRD.CSTIPISAI,")
                .append("PRD.USALOCAL,PRD.CALCULOGIRO,PRD.CALRUPTURAESTOQUE ")
                .append("FROM TGFPRO PRD ")
                .append("WHERE ");

        // Prioridade: CODPROD; senão, NCM
        if (prodNorm != null) {
            query.setParam("CODPROD", prodNorm);
            sql.append("PRD.CODPROD = {CODPROD}");
        } else {
            query.setParam("CODNCM", ncmNorm);
            sql.append("PRD.NCM = {CODNCM} ")
                    .append("AND PRD.CODPROD = (SELECT MAX(P.CODPROD) FROM TGFPRO P WHERE P.NCM = {CODNCM})");
        }

        query.nativeSelect(sql.toString());

        try {
            boolean achou = false;

            while (query.next()) {
                achou = true;

                setCodProd(query.getInt("CODPROD"));
                setDescrProd(query.getString("DESCRPROD"));
                setCodigoNcm(query.getString("NCM"));
                setCodGrupo(query.getInt("CODGRUPOPROD"));
                setUsoProd(query.getString("USOPROD"));
                setOrigProd(query.getString("ORIGPROD"));
                setIcmsGerencia(query.getString("ICMSGERENCIA"));
                setCodLocalPadrao(query.getInt("CODLOCALPADRAO"));
                setTemICMS(query.getString("TEMICMS"));
                setGrupoICMS(query.getInt("GRUPOICMS"));
                setGrupoICMS2(query.getInt("GRUPOICMS2"));
                setCalcDifal(query.getString("CALCDIFAL"));
                setClasSubTrib(query.getInt("CLASSUBTRIB"));
                setTipSubst(query.getString("TIPSUBST"));
                setCodEspeCST(query.getInt("CODESPECST"));
                setTipGtinNfe(query.getInt("TIPGTINNFE"));
                setProdutoNfe(query.getInt("PRODUTONFE"));
                setGrupoCSLL(query.getString("GRUPOCSSL"));
                setGrupoPis(query.getString("GRUPOPIS"));
                setGrupoCofins(query.getString("GRUPOCOFINS"));
                setGrupoIbsCbs(query.getInt("GRUPOIBSCBS"));
                setCodIpi(query.getInt("CODIPI"));
                setTemIpiCompra(query.getString("TEMIPICOMPRA"));
                setTemIpiVenda(query.getString("TEMIPIVENDA"));
                setCodEnqIpiEnt(query.getInt("CODENQIPIENT"));
                setCodEnqIpSai(query.getInt("CODENQIPISAI"));
                setCstIpiEnt(query.getInt("CSTIPIENT"));
                setCstIpiSai(query.getInt("CSTIPISAI"));
                setUsaLocal(query.getString("USALOCAL"));
                setCalculoGiro(query.getString("CALCULOGIRO"));
                setCalRuputuraEstoque(query.getString("CALRUPTURAESTOQUE"));
            }

            if (!achou) {
                res.mostraErro("Nenhum produto similar encontrado com os parâmetros informados.");
            }

        } catch (Exception e) {
            res.mostraErro("Erro ao buscar Item Similar (NCM/Cód. Produto): \n" + e.getMessage());
        } finally {
            query.close();
        }
    }

}

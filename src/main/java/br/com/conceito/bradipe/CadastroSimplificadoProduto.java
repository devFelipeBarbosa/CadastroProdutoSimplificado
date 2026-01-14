package br.com.conceito.bradipe;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;


import java.math.BigDecimal;


public class CadastroSimplificadoProduto implements AcaoRotinaJava{

    @Override
    public void doAction(ContextoAcao ctx) throws Exception {
        ctx.setMensagemRetorno("Gradle + Sankhya funcionando!");

        String descrProd = ctx.getParam("DESCRPROD").toString();
        String codVol = ctx.getParam("CODVOL").toString();
        BigDecimal codGrupoProd =  (BigDecimal) ctx.getParam("CODGRUPOPROD");
        BigDecimal referencia = (BigDecimal) ctx.getParam("REFERENCIA");
        BigDecimal codNcm = (BigDecimal) ctx.getParam("NCM");
        Boolean similarNCM = (boolean) ctx.getParam("SIMILARNCM");
        String obsCadastro = ctx.getParam("OBSERVACOESCADASTRO").toString();





    }
}

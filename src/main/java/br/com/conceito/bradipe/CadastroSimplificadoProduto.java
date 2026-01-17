package br.com.conceito.bradipe;

import br.com.conceito.bradipe.service.produto.ProdutoSimilarService;
import br.com.conceito.bradipe.util.ParamUtil;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;

import java.math.BigDecimal;

public class CadastroSimplificadoProduto implements AcaoRotinaJava{

    @Override
    public void doAction(ContextoAcao ctx) throws Exception {

        BigDecimal codUsuLogado = ctx.getUsuarioLogado();

        String descrProd = ParamUtil.getStringObrigatorio(ctx, "DESCRPROD", "Descrição do Produto");
        String codVol    = ParamUtil.getStringObrigatorio(ctx, "CODVOL", "Unidade");
        BigDecimal codGrupoProd = ParamUtil.getBigDecimalObrigatorio(ctx, "CODGRUPOPROD", "Grupo de Produto/Serviço");
        BigDecimal referencia = ParamUtil.getBigDecimalOpcional(ctx, "REFERENCIA");
        String refForn = ParamUtil.getStringOpcional(ctx, "REFFORN");
        String codNcm = ParamUtil.getStringOpcional(ctx, "NCM");
        BigDecimal codProd = ParamUtil.getBigDecimalOpcional(ctx, "CODPROD");
        String similarNCM = ParamUtil.getStringOpcional(ctx, "SIMILARNCM");
        String obsCadastro = ParamUtil.getStringOpcional(ctx, "OBSERVACOESCADASTRO");

        // validação tamanho
        if (descrProd.length() > 100) {
            ctx.mostraErro("Atenção! Descrição acima do permitido (máximo 100).");
            return;
        } else if (descrProd.length() < 2) {
            ctx.mostraErro("Atenção! Descrição abaixo do permitido (mínimo 2).");
            return;
        }

        if ("S".equalsIgnoreCase(similarNCM)) {
            if (codNcm == null && codProd == null) {
                ctx.mostraErro("Para busca de Produtos Similares, informe o NCM ou o Código do Produto.");
                return;
            }

            if (codNcm != null) {
                if (codNcm.length() != 8) {
                    ctx.mostraErro("NCM inválido. Informe 8 dígitos.");
                    return;
                }
            }

            if (codProd != null && codProd.signum() <= 0) {
                ctx.mostraErro("Código do Produto inválido.");
                return;
            }

            ProdutoSimilarService serviceSimilar = new ProdutoSimilarService();
            serviceSimilar.buscaPorNCM(ctx, codNcm, codProd);
            ctx.mostraErro("Produto Similar: " + serviceSimilar.getCodProd() + "; PIS: " + serviceSimilar.getGrupoPis());
        }




    }
}

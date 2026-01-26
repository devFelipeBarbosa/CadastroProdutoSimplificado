package br.com.conceito.bradipe.actions;

import br.com.conceito.bradipe.service.registro.CriarProdutoService;
import br.com.conceito.bradipe.util.ParamUtil;
import br.com.conceito.bradipe.util.UsuarioUtil;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class CadastroSimplificadoProduto implements AcaoRotinaJava {

    private static final Pattern EMAIL_RX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    public void doAction(ContextoAcao ctx) throws Exception {

        BigDecimal codUsuLogado = ctx.getUsuarioLogado();

        String descrProd = ParamUtil.getStringObrigatorio(ctx, "DESCRPROD", "Descrição do Produto");
        String codVol    = ParamUtil.getStringObrigatorio(ctx, "CODVOL", "Unidade");
        BigDecimal codGrupoProd = ParamUtil.getBigDecimalObrigatorio(ctx, "CODGRUPOPROD", "Grupo de Produto/Serviço");
        String referencia = ParamUtil.getStringOpcional(ctx, "REFERENCIA");
        String refForn = ParamUtil.getStringOpcional(ctx, "REFFORN");
        String codNcm = ParamUtil.getStringOpcional(ctx, "NCM");
        BigDecimal codProd = ParamUtil.getBigDecimalOpcional(ctx, "CODPROD");

        String similar = ParamUtil.getStringOpcional(ctx, "SIMILAR");
        similar = (similar == null || similar.trim().isEmpty()) ? "N" : similar;

        String importado = ParamUtil.getStringOpcional(ctx, "IMPORTADO");
        importado = (importado == null || importado.trim().isEmpty()) ? "N" : importado;

        String obsCadastro = ParamUtil.getStringOpcional(ctx, "OBSERVACOESCADASTRO");

        // validação tamanho
        if (descrProd.length() > 100) {
            ctx.mostraErro("Atenção! Descrição acima do permitido (máximo 100).");
            return;
        } else if (descrProd.length() < 2) {
            ctx.mostraErro("Atenção! Descrição abaixo do permitido (mínimo 2).");
            return;
        }

        if ("S".equalsIgnoreCase(similar)) {
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

        } else {
            if (codNcm == null) {
                ctx.mostraErro("Para continuidade no cadastro, informe o NCM. Caso contrário, acione a opção: Similar por NCM/Produto, e informe uma referência válida (NCM ou Produto)");
                return;
            }
        }

        //cria o produto
        CriarProdutoService prd = new CriarProdutoService();
        CriarProdutoService.ResultadoCriacao p = prd.CriarProduto(
                ctx, descrProd, codVol, codGrupoProd, referencia, refForn, codNcm,
                codProd, similar, importado, obsCadastro
        );


        String mensagemRetorno =
                "Produto criado com sucesso!\n\n" +
                        "Código: " + p.getCodProd() + "\n" +
                        "Descrição: " + p.getDescrProd();


        try {
            // e-mails dos validadores
            String emailsValidadores = UsuarioUtil.listaEmailValidadores(ctx);

            // e-mail do solicitante (usuário logado)
            UsuarioUtil.UsuarioInfo solicitante = UsuarioUtil.buscar(ctx, codUsuLogado);
            String emailSolicitante = (solicitante != null) ? solicitante.getEmail() : null;

            String destinatarios = montarDestinatarios(emailsValidadores, emailSolicitante);

            if (destinatarios != null && !destinatarios.isEmpty()) {

                String assunto =
                        "[Sankhya][Pré-cadastro Produto] " + p.getCodProd() + " - " + safe(p.getDescrProd());

                String corpo =
                        "Olá,\n\n" +
                                "Foi registrada uma solicitação de PRÉ-CADASTRO de produto e é necessária validação para liberação de faturamento.\n\n" +
                                "Produto: " + p.getCodProd() + " - " + safe(p.getDescrProd()) + "\n" +
                                "Solicitante: " + safe(solicitante != null ? solicitante.getNome() : "") +
                                (emailSolicitante != null ? " (" + emailSolicitante + ")" : "") + "\n" +
                                "Cód. NCM: " + safe(codNcm) + "\n" +
                                "Cód. Grupo Prd.: " + (codGrupoProd != null ? codGrupoProd.toPlainString() : "") + "\n" +
                                "Unidade: " + safe(codVol) + "\n\n" +
                                "Ação necessária: revisar o cadastro e liberar para faturamento conforme política interna.\n\n" +
                                "Mensagem automática - não responder.";

                ctx.eMail(assunto, corpo, destinatarios);

            } else {
                mensagemRetorno += "\n\n⚠️ Aviso: não foi possível enviar e-mail (sem destinatários válidos).";
            }

        } catch (Exception e) {
            // não derruba o cadastro por causa do e-mail
            mensagemRetorno += "\n\n⚠️ Aviso: produto criado, mas houve falha no envio do e-mail.\n" +
                    "Motivo: " + e.getMessage();
        }

        ctx.setMensagemRetorno(mensagemRetorno);
    }

    private static String safe(String s) {
        return (s == null) ? "" : s.trim();
    }

    private static String montarDestinatarios(String... listas) {
        Set<String> set = new LinkedHashSet<>();

        for (String lista : listas) {
            if (lista == null) continue;

            String[] parts = lista.split("[;,\n\r\\s]+");
            for (String p : parts) {
                String email = (p == null) ? "" : p.trim().toLowerCase();
                if (!email.isEmpty() && EMAIL_RX.matcher(email).matches()) {
                    set.add(email);
                }
            }
        }

        return String.join(";", set);
    }
}

## 🎯 Objetivos

Este projeto tem como objetivo principal **simplificar e padronizar o cadastro de produtos**
no ERP **Sankhya**, permitindo que usuários das **Centrais de Venda** realizem o
**pré-cadastro de novos itens de forma rápida, controlada e orientada**.

Os objetivos específicos são:

- Permitir o **pré-cadastro de produtos diretamente nas centrais de venda**
- Reduzir retrabalho e cadastros incompletos realizados fora do fluxo comercial
- Garantir que o produto seja **validado pela equipe fiscal antes do faturamento**
- Padronizar informações mínimas obrigatórias para criação de novos produtos
- Melhorar a comunicação entre áreas Comercial e Fiscal

---

## 📦 Escopo da Solução

A solução contempla o desenvolvimento de uma **customização Java (Gradle) para o Sankhya**,
disponibilizada por meio de um **Botão de Ação** nas Centrais de Venda.

### ✔️ Está dentro do escopo

- Botão de ação nas **Centrais de Venda / Venda Assistida**
- Abertura de **popup** para coleta dos dados mínimos do produto
- Criação do produto na base de dados (**TGFPRO**) em modo de **pré-cadastro**
- Retorno do **código do produto (CODPROD)** ao usuário após a criação
- Possibilidade de **copiar parâmetros fiscais de um produto similar por NCM**
- Marcação do produto como **pendente de validação fiscal**
- Preparação para **bloqueio de faturamento** até liberação fiscal

### ❌ Fora do escopo (neste momento)

- Automatização completa da classificação fiscal
- Integrações externas (SEFAZ, terceiros, APIs externas)
- Validações fiscais avançadas no momento do pré-cadastro
- Manutenção ou ajuste automático de regras tributárias
- Geração automática de preços, custos ou políticas comerciais

---

## 🔄 Fluxo resumido

1. Usuário inicia o cadastro pela **Central de Vendas**
2. Preenche os dados mínimos no popup de pré-cadastro
3. Sistema cria o produto em **status pendente**
4. Código do produto é retornado ao usuário
5. Equipe Fiscal valida e complementa o cadastro
6. Produto é liberado para faturamento

---

# Scale Monitoring System (Java)

Sistema **CLI** em Java para controlar a **calibração entre duas balanças**.  
Lê arquivos de leituras do chão de fábrica, extrai o **último peso** de cada balança e decide se estão **conformes** com base em uma **tolerância (kg)**.  
Ao longo do projeto, os dados serão **persistidos** para alimentar **relatórios** e **gráficos** (total de aferições, conformes e desvios), aplicando **POO**, **boas práticas de arquitetura**, **segurança** e **controle de acesso**.

---

## Sumário
- [Visão](#visao)
- [Estado atual](#estado-atual)
- [Formato de entrada](#formato-de-entrada)
- [Regra de decisão](#regra-de-decisao)
- [Pré-requisitos](#pre-requisitos)
- [Como executar](#como-executar)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Saídas](#saidas)
- [Testes rápidos](#testes-rapidos)
- [Roadmap](#roadmap)
- [Licença](#licenca)

---

## Visao
- **Objetivo:** informar rapidamente se as balanças estão calibradas e manter histórico para análise de processo.
- **Integração chão de fábrica:** ingestão inicial por arquivos; evoluções previstas para conectores (ex.: TCP/serial, MQTT/OPC-UA, REST).
- **Evolução técnica:** do CLI simples para aplicação com **POO**, camadas, persistência, relatórios, **segurança** (RBAC) e, futuramente, **framework Spring**.

## Estado atual
- Aplicação **CLI** que lê `data/scale1.txt` e `data/scale2.txt` (UTF-8).
- Considera **apenas a última linha válida** de cada arquivo.
- Compara pesagens usando tolerância configurada no código.
- Exibe resultado no terminal (mensagens claras e formatadas).

## Formato de entrada
Cada linha representa uma leitura:
```
YYYY-MM-DDTHH:MM:SS±HH:MM,999.99
```
- **Timestamp** ISO-8601 com offset (ex.: `2025-02-09T07:52:00-03:00`)
- **Separador de campos:** vírgula `,`
- **Peso:** decimal com **ponto** (ex.: `980.00`) — **kg**

**Exemplo**
```
2025-02-09T07:52:00-03:00,980.00
2025-02-09T08:10:00-03:00,982.10
```

## Regra de decisao
- **Conforme:** `|pesoScale1 − pesoScale2| ≤ TOLERANCE_KG`
- **Desvio:** `|pesoScale1 − pesoScale2| > TOLERANCE_KG`
- **Borda:** diferença **igual** à tolerância é **aprovada**.

## Pre-requisitos
- **JDK 17+**
- Arquivos `data/scale1.txt` e `data/scale2.txt` existentes e legíveis (UTF-8).

## Como executar
### Eclipse
1. Verifique o *Working directory* da configuração de execução apontando para a **raiz do projeto**.
2. Execute `com.scalemonitor.app.Main` como **Java Application**.

### Terminal (opcional)
```bash
# a partir da raiz do projeto
javac -encoding UTF-8 -d bin src/com/scalemonitor/app/Main.java
java -cp bin com.scalemonitor.app.Main
```

## Estrutura do projeto
```
.
├─ src/
│  └─ com/scalemonitor/app/Main.java
├─ data/
│  ├─ scale1.txt
│  └─ scale2.txt
├─ LICENSE
└─ README.md
```

## Saidas
- **Conforme:** imprime pesos e “Scales Calibrated!” em **stdout**.
- **Desvio:** imprime pesos e “Scales Uncalibrated…” (alerta).  
- **Erros de execução** (ex.: arquivo ausente): mensagem em **stderr**; códigos de saída serão introduzidos nas próximas fases.

> Recomendação: exibir também **diferença (delta)** e **tolerância** na mensagem para facilitar auditoria.

## Testes rapidos
- `scale1=980.00`, `scale2=980.00` → **Conforme**
- `scale1=980.00`, `scale2=982.00` (tol 2.00) → **Conforme**
- `scale1=980.00`, `scale2=982.01` (tol 2.00) → **Desvio**
- Arquivo vazio / linha inválida → deve ser **avisado** (planejado)

## Roadmap
**Fase 0 — CLI mínimo (concluindo)**
- [x] Leitura de arquivos e decisão por tolerância
- [x] Impressão formatada de pesos

**Fase 1 — Robustez & Observabilidade**
- [ ] Contabilizar/relatar **linhas inválidas** e **arquivos vazios**
- [ ] Exibir **delta** e **tolerância** na saída
- [ ] Introduzir **códigos de saída** (0/≠0)
- [ ] Parametrizar **tolerância** e **caminhos** (args/env)

**Fase 2 — POO & Camadas**
- [ ] Entidades e serviços (separação de responsabilidades)
- [ ] Testes básicos
- [ ] Logs e configuração externa

**Fase 3 — Persistência**
- [ ] Persistir decisões (CSV/JSON → banco local H2/SQLite)
- [ ] Consultas de agregação (totais, conformes, desvios)

**Fase 4 — Relatórios & Gráficos**
- [ ] Exportação para BI/planilha e gráficos embutidos
- [ ] Métricas/KPIs e séries temporais

**Fase 5 — Integrações & Segurança**
- [ ] Conectores com chão de fábrica
- [ ] **RBAC** (perfis), auditoria, boas práticas de segurança

**Fase 6 — Evolução de stack**
- [ ] API/UI e adoção de **Spring** quando apropriado

## Licenca
Consulte `LICENSE`.

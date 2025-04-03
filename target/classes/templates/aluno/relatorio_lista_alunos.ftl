<!DOCTYPE html>
<html>

<head>
    <style>
        @page { <!-- Configurações para repetir cabeçalho e rodape em todas paginas -->
            size: A4;
            margin: 100px 50px;

            @top-left {
                content: element(logo);
            }

            @top-right {
                align: right;
                content: element(header);
            }

            @bottom-left {
                content: "Procergs - APM Quarkus";
                font-size: 12px;
            }

            @bottom-right {
                content: "Página " counter(page) " de " counter(pages);
                font-size: 12px;
            }
        }

        .header {
            position: running(header);
        }

        .logo {
            position: running(logo);
        }

        body {
            font-family: Arial, sans-serif;
        }

        table {
            width: 100%;
            -fs-table-paginate: paginate; <!-- Configuração para repetir thead da tabela em todas paginas -->
            border-spacing: 0;
        }

        tr {
            page-break-inside: avoid;
        }

        td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
            word-wrap: break-word;
            overflow-wrap: anywhere;
            white-space: normal;
        }

        th {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>
    <#include "header.ftl">
        <div class="content">

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Campo Teste 0</th>
                        <th>Campo Teste 1</th>
                        <th>Campo Teste 2</th>
                        <th>Versão</th>
                    </tr>
                </thead>
                <tbody>
                    <#list alunos as aluno>
                        <tr>
                            <td>${aluno.id}</td>
                            <td>${aluno.nomeAlu}</td>
                            <td>${aluno.campoTeste0!"N/A"}</td>
                            <td>${aluno.campoTeste1!"N/A"}</td>
                            <td>${aluno.campoTeste2!"N/A"}</td>
                            <td>${aluno.versao!"N/A"}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
</body>

</html>
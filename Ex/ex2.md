xercícios Práticos: Arquivos, Diretórios e Serialização em Kotlin
Esta lista de exercícios foi projetada para cobrir os principais conceitos de manipulação de arquivos em Kotlin, desde operações básicas com texto até a serialização de objetos e gerenciamento de diretórios.Exercício 1: Leitura e Escrita de Arquivos de Texto
Praticar as operações fundamentais de I/O com arquivos de texto.
Crie uma função que receba uma lista de strings (representando tarefas) e as salve em um arquivo chamado tarefas.txt. Cada tarefa deve ocupar uma linha.
Escreva uma função que leia o arquivo tarefas.txt e exiba cada tarefa no console, prefixada com um número de linha (ex: "1. Comprar pão").
Crie uma função que adicione uma nova tarefa ao final do arquivo tarefas.txt sem apagar as tarefas existentes.
Exercício 2: Serialização e Desserialização com JSON
Entender como converter objetos Kotlin para o formato JSON e vice-versa.
Crie uma data class chamada ConfiguracaoUsuario com as seguintes propriedades: nome (String), idioma (String) e tema (String, ex: "escuro" ou "claro"). Lembre-se de anotá-la com @Serializable.
Crie uma instância de ConfiguracaoUsuario, serialize-a para uma string JSON formatada (com indentação) e salve-a em um arquivo chamado config.json.
Escreva uma função que leia o arquivo config.json, o converta de volta para um objeto ConfiguracaoUsuario e imprima as configurações no console.
Exercício 3: Manipulação de Arquivos Binários
Aprender a lidar com dados brutos em formato de bytes.
Crie uma função que gere um ByteArray com 5 bytes representando um cabeçalho de arquivo simples (ex: 0x4B, 0x4F, 0x54, 0x4C, 0x4E - que corresponde a "KOTLN" em ASCII). Salve este array de bytes em um arquivo chamado app.dat.
Escreva uma função que leia o arquivo app.dat, verifique se os 5 bytes lidos correspondem ao cabeçalho esperado e imprima uma mensagem de sucesso ou falha.
Exercício 4: Gerenciamento de Diretórios
Praticar a criação, listagem e exclusão de diretórios e arquivos.
Crie uma função que organize uma estrutura de diretórios da seguinte forma:/backup
/fotos
/documentos
Escreva uma função que liste os subdiretórios dentro de backup. Em seguida, crie um arquivo vazio chamado notas.txt dentro de /backup/documentos.
Crie uma função que receba o caminho do diretório backup e o exclua de forma recursiva, apagando todo o seu conteúdo (subdiretórios e arquivos).
Exercício 5: Crie um programa que:
Peça ao usuário para digitar várias linhas de texto
Salve esse texto em um arquivo
Depois leia e mostre o conteúdo do arquivo
Exercício 6  : Crie um sistema simples de cadastro de usuários:
Armazene os dados (nome, email, idade) em um arquivo CSV
Implemente funções para adicionar, listar e buscar usuários
Exercício 7  : Crie um programa que:
Leia um arquivo de texto
Conte a frequência de cada palavra
Salve o resultado em um novo arquivo
Exercício 8  : Implemente um sistema de backup:
Copie todos os arquivos de uma pasta para outra
Mantenha apenas os 5 backups mais recentes

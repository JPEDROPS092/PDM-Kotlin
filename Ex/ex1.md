EXERCÍCIOS BÁSICOS KOTLIN
1. Sintaxe Básica
   Exercício 1.1: Crie um programa que:
   • Declare uma variável imutável com seu nome
   • Declare uma variável mutável com sua idade
   • Imprima: "Meu nome é [nome] e tenho [idade] anos"
   • Incremente a idade e imprima novamente
   Exercício 1.2: Escreva uma expressão que:
   • Receba um número inteiro
   • Retorne "Positivo" se for maior que zero, "Negativo" se for menor e "Zero" caso contrário
2. Funções
   Exercício 2.1: Crie uma função chamada ehPar que:
   • Recebe um Int
   • Retorna true se for par, false caso contrário (usando expressão única)
   Exercício 2.2: Crie uma função de extensão para String chamada inverte que:
   • Retorna a string invertida
   Exemplo: "Kotlin".inverte() → "niltok"
3. Null Safety
   Exercício 3.1: Escreva uma função comprimentoOuZero que:
   • Recebe uma String?
   • Retorna o tamanho da string ou 0 se for nula (use safe call e Elvis operator)
   Exercício 3.2: Crie uma função descreveEndereco que:
   • Recebe rua (String?), número (Int?), cidade (String?)
   • Retorna uma string no formato "Rua [rua], Nº [número], [cidade]"
   • Substitua qualquer valor nulo por "Não informado"
4. Classes e Data Classes
   Exercício 4.1: Crie uma data class Aluno com:
   • nome (String)
   • matricula (String)
   • notas (List<Double>)
   • Adicione uma função que calcula a média
   Exercício 4.2: Crie uma classe Retangulo com:
   • largura e altura (Double no construtor)
   • Função que calcula área
   • Função que calcula perímetro
   • Função que retorna "Quadrado" se largura == altura, "Retângulo" caso contrário

Exercícios complementares
1. Numa certa loja de eletrodomésticos, o comerciário encarregado da seção de televisores recebe, mensalmente, um salário mínimo mais comissão. Essa comissão é calculada em relação ao tipo e ao número de televisores vendidos por mês, obedecendo ao quadro abaixo:
TIPO 	Nº DE TELEVISORES VENDIDOS 	COMISSÕES
A cores 	Maior ou igual a 10
Menor ou igual a 10 	14% do preço por televisor vendido
13% do preço por televisor vendido
Preto e branco 	Maior ou igual a 20
Menor do que 20 	13% do preço por televisor vendido
12% do preço por televisor vendido
Sabe-se ainda, que ele tem um desconto de 8% sobre seu salário bruto para o INSS.Se o seu salário total (mínimo + comissões – INSS), for maior que o limite de isenção do imposto de renda, ele ainda terá um desconto de 15% sobre o que ultrapassar o limite de isenção retido na fonte. Sabendo-se que existem 20 empregados nesta seção, leia os valores do salário mínimo, do limite de isenção de IRRF, os preços dos televisores em cores e preto e branco e, para cada comerciário, o número de sua inscrição, o número de televisores a cores e o número de televisores preto e branco vendidos; calcule e escreva o número de inscrição de cada empregado, seu salário bruto e seu salário líquido.
2. Uma empresa decidiu fazer um levantamento em relação aos candidatos que se apresentarem para preenchimento de vagas no seu quadro de funcionários, utilizando processamento eletrônico. Supondo que você seja o programador encarregado desse levantamento, fazer um programa que: a) leia um conjunto de dados para cada candidato contendo:
b) número de inscrição do candidato,
c) idade,
d) sexo (masculino, feminino);
e) experiência no serviço (sim ou não);

O último conjunto contém o número de inscrição do candidato igual a zero.
Calcule:
a) o número de candidatos do sexo feminino,
b) o número de candidatos do sexo masculino,
c) idade média dos homens que já têm experiência no serviço,
d) porcentagem dos homens com mais de 45 anos entre o total de homens,
e) número de mulheres que têm idade inferior a 35 anos e com experiência no serviço,
f) a menor idade entre mulheres que já têm experiência no serviço;
Escreva:
• o número de inscrição das mulheres pertencentes ao grupo descrito no item e,
• o que foi calculado em cada item acima especificado.

    3. O sistema de avaliação de uma determinada disciplina obedece aos seguintes critérios: 
a) Durante o semestre são dadas três notas;
b) A nota final é obtida pela média aritmética das notas dadas durante o curso;
c) É considerado aprovado o aluno que obtiver a nota final superior ou igual a 60 e que tiver comparecido a um mínimo de 40 aulas.
Fazer um programa que:
a) Leia um conjunto de dados contendo o número de matrícula, as três notas e a frequência (número de aulas frequentadas) de 100 alunos.
Fazer um programa que:
b) Calcule:
i. a nota final de cada aluno;
ii. a maior e a menor nota da turma;
iii. a nota média da turma;
iv. o total de alunos reprovados;
v. a porcentagem de alunos reprovados por infrequência.
c) Escreva:
a) para cada aluno, o número de matrícula, a frequência, a nota final e o código (aprovado ou reprovado);
b) o que foi calculado no item b (2,3,4, e 5). 
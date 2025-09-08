package exercicios

import kotlin.math.round

// Exercício 1: Sistema de comissão de vendedores
data class Vendedor(
    val inscricao: Int,
    val tvCores: Int,
    val tvPB: Int
)

fun calcularSalarioVendedor(
    vendedor: Vendedor,
    salarioMinimo: Double,
    precoTvCores: Double,
    precoTvPB: Double,
    limiteIsencaoIRRF: Double
): Triple<Double, Double, Double> { // (salário bruto, desconto INSS + IRRF, salário líquido)
    
    // Calcular comissões
    val comissaoTvCores = when {
        vendedor.tvCores >= 10 -> vendedor.tvCores * precoTvCores * 0.14
        else -> vendedor.tvCores * precoTvCores * 0.13
    }
    
    val comissaoTvPB = when {
        vendedor.tvPB >= 20 -> vendedor.tvPB * precoTvPB * 0.13
        else -> vendedor.tvPB * precoTvPB * 0.12
    }
    
    val salarioBruto = salarioMinimo + comissaoTvCores + comissaoTvPB
    
    // Desconto INSS (8%)
    val descontoINSS = salarioBruto * 0.08
    val salarioAposINSS = salarioBruto - descontoINSS
    
    // Desconto IRRF (15% sobre o que ultrapassar o limite)
    val descontoIRRF = if (salarioAposINSS > limiteIsencaoIRRF) {
        (salarioAposINSS - limiteIsencaoIRRF) * 0.15
    } else 0.0
    
    val salarioLiquido = salarioAposINSS - descontoIRRF
    val totalDescontos = descontoINSS + descontoIRRF
    
    return Triple(salarioBruto, totalDescontos, salarioLiquido)
}

fun exercicio1Complementar() {
    println("=== EXERCÍCIO 1: Sistema de Comissão ===")
    
    val salarioMinimo = 1320.0
    val limiteIsencaoIRRF = 2000.0
    val precoTvCores = 1200.0
    val precoTvPB = 800.0
    
    val vendedores = listOf(
        Vendedor(1001, 12, 25),
        Vendedor(1002, 8, 15),
        Vendedor(1003, 15, 30),
        Vendedor(1004, 5, 10),
        Vendedor(1005, 20, 40)
    )
    
    println("INSCRIÇÃO\tSALÁRIO BRUTO\tSALÁRIO LÍQUIDO")
    println("=========\t=============\t===============")
    
    vendedores.forEach { vendedor ->
        val (bruto, _, liquido) = calcularSalarioVendedor(
            vendedor, salarioMinimo, precoTvCores, precoTvPB, limiteIsencaoIRRF
        )
        println("${vendedor.inscricao}\t\tR$ ${"%.2f".format(bruto)}\t\tR$ ${"%.2f".format(liquido)}")
    }
}

// Exercício 2: Levantamento de candidatos
data class Candidato(
    val inscricao: Int,
    val idade: Int,
    val sexo: String, // "M" ou "F"
    val experiencia: Boolean
)

fun exercicio2Complementar() {
    println("\n=== EXERCÍCIO 2: Levantamento de Candidatos ===")
    
    val candidatos = listOf(
        Candidato(1001, 28, "F", true),
        Candidato(1002, 35, "M", true),
        Candidato(1003, 42, "M", false),
        Candidato(1004, 30, "F", false),
        Candidato(1005, 50, "M", true),
        Candidato(1006, 32, "F", true),
        Candidato(1007, 27, "F", true),
        Candidato(1008, 48, "M", false),
        Candidato(1009, 29, "F", false),
        Candidato(1010, 45, "M", true)
    )
    
    val candidatosFeminino = candidatos.count { it.sexo == "F" }
    val candidatosMasculino = candidatos.count { it.sexo == "M" }
    
    val homensComExperiencia = candidatos.filter { it.sexo == "M" && it.experiencia }
    val idadeMediaHomensExperientes = if (homensComExperiencia.isNotEmpty()) {
        homensComExperiencia.map { it.idade }.average()
    } else 0.0
    
    val homensTotal = candidatos.count { it.sexo == "M" }
    val homensMais45 = candidatos.count { it.sexo == "M" && it.idade > 45 }
    val porcentagemHomensMais45 = if (homensTotal > 0) {
        (homensMais45.toDouble() / homensTotal) * 100
    } else 0.0
    
    val mulheresInferior35ComExperiencia = candidatos.filter { 
        it.sexo == "F" && it.idade < 35 && it.experiencia 
    }
    
    val mulheresComExperiencia = candidatos.filter { it.sexo == "F" && it.experiencia }
    val menorIdadeMulherComExperiencia = mulheresComExperiencia.minOfOrNull { it.idade } ?: 0
    
    println("a) Número de candidatos do sexo feminino: $candidatosFeminino")
    println("b) Número de candidatos do sexo masculino: $candidatosMasculino")
    println("c) Idade média dos homens com experiência: ${"%.1f".format(idadeMediaHomensExperientes)} anos")
    println("d) Porcentagem de homens com mais de 45 anos: ${"%.1f".format(porcentagemHomensMais45)}%")
    println("e) Número de mulheres < 35 anos com experiência: ${mulheresInferior35ComExperiencia.size}")
    println("f) Menor idade entre mulheres com experiência: $menorIdadeMulherComExperiencia anos")
    
    println("\nInscrições das mulheres < 35 anos com experiência:")
    mulheresInferior35ComExperiencia.forEach { 
        println("- ${it.inscricao}")
    }
}

// Exercício 3: Sistema de avaliação de disciplina
data class AlunoAvaliacao(
    val matricula: Int,
    val nota1: Double,
    val nota2: Double,
    val nota3: Double,
    val frequencia: Int
) {
    val notaFinal: Double get() = (nota1 + nota2 + nota3) / 3.0
    val aprovado: Boolean get() = notaFinal >= 60.0 && frequencia >= 40
    val reprovarPorInfrequencia: Boolean get() = frequencia < 40 && notaFinal >= 60.0
}

fun exercicio3Complementar() {
    println("\n=== EXERCÍCIO 3: Sistema de Avaliação ===")
    
    // Simulando dados para demonstração (apenas 10 alunos em vez de 100)
    val alunos = listOf(
        AlunoAvaliacao(2021001, 75.0, 80.0, 70.0, 42),
        AlunoAvaliacao(2021002, 60.0, 55.0, 65.0, 35),
        AlunoAvaliacao(2021003, 85.0, 90.0, 80.0, 45),
        AlunoAvaliacao(2021004, 40.0, 45.0, 50.0, 40),
        AlunoAvaliacao(2021005, 70.0, 75.0, 68.0, 38),
        AlunoAvaliacao(2021006, 95.0, 88.0, 92.0, 47),
        AlunoAvaliacao(2021007, 55.0, 60.0, 58.0, 42),
        AlunoAvaliacao(2021008, 30.0, 35.0, 40.0, 30),
        AlunoAvaliacao(2021009, 80.0, 75.0, 85.0, 44),
        AlunoAvaliacao(2021010, 65.0, 70.0, 75.0, 36)
    )
    
    val maiorNota = alunos.maxOfOrNull { it.notaFinal } ?: 0.0
    val menorNota = alunos.minOfOrNull { it.notaFinal } ?: 0.0
    val notaMediaTurma = alunos.map { it.notaFinal }.average()
    val totalReprovados = alunos.count { !it.aprovado }
    val reprovarPorInfrequencia = alunos.count { it.reprovarPorInfrequencia }
    val porcentagemInfrequencia = (reprovarPorInfrequencia.toDouble() / alunos.size) * 100
    
    println("MATRÍCULA\tFREQUÊNCIA\tNOTA FINAL\tSITUAÇÃO")
    println("=========\t==========\t==========\t========")
    
    alunos.forEach { aluno ->
        val situacao = if (aluno.aprovado) "APROVADO" else "REPROVADO"
        println("${aluno.matricula}\t\t${aluno.frequencia}\t\t${"%.1f".format(aluno.notaFinal)}\t\t$situacao")
    }
    
    println("\n=== ESTATÍSTICAS DA TURMA ===")
    println("Maior nota: ${"%.1f".format(maiorNota)}")
    println("Menor nota: ${"%.1f".format(menorNota)}")
    println("Nota média da turma: ${"%.1f".format(notaMediaTurma)}")
    println("Total de alunos reprovados: $totalReprovados")
    println("Porcentagem de reprovação por infrequência: ${"%.1f".format(porcentagemInfrequencia)}%")
}

fun main() {
    exercicio1Complementar()
    exercicio2Complementar()
    exercicio3Complementar()
}
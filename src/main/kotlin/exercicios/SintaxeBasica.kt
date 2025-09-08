package exercicios

// Exercício 1.1: Variáveis e impressão
fun exercicio1_1() {
    val nome = "João" // Variável imutável
    var idade = 25    // Variável mutável
    
    println("Meu nome é $nome e tenho $idade anos")
    idade++
    println("Meu nome é $nome e tenho $idade anos")
}

// Exercício 1.2: Expressão condicional
fun exercicio1_2(numero: Int): String {
    return when {
        numero > 0 -> "Positivo"
        numero < 0 -> "Negativo"
        else -> "Zero"
    }
}

// Exercício 2.1: Função ehPar
fun ehPar(numero: Int): Boolean = numero % 2 == 0

// Exercício 2.2: Função de extensão para String
fun String.inverte(): String = this.reversed()

// Exercício 3.1: Safe call e Elvis operator
fun comprimentoOuZero(texto: String?): Int = texto?.length ?: 0

// Exercício 3.2: Descrição de endereço
fun descreveEndereco(rua: String?, numero: Int?, cidade: String?): String {
    val ruaInfo = rua ?: "Não informado"
    val numeroInfo = numero?.toString() ?: "Não informado"
    val cidadeInfo = cidade ?: "Não informado"
    
    return "Rua $ruaInfo, Nº $numeroInfo, $cidadeInfo"
}

// Exercício 4.1: Data class Aluno
data class Aluno(
    val nome: String,
    val matricula: String,
    val notas: List<Double>
) {
    fun calcularMedia(): Double {
        return if (notas.isEmpty()) 0.0 else notas.average()
    }
}

// Exercício 4.2: Classe Retangulo
class Retangulo(private val largura: Double, private val altura: Double) {
    
    fun calcularArea(): Double = largura * altura
    
    fun calcularPerimetro(): Double = 2 * (largura + altura)
    
    fun tipoForma(): String = if (largura == altura) "Quadrado" else "Retângulo"
}

fun main() {
    println("=== Exercício 1.1 ===")
    exercicio1_1()
    
    println("\n=== Exercício 1.2 ===")
    println("5: ${exercicio1_2(5)}")
    println("-3: ${exercicio1_2(-3)}")
    println("0: ${exercicio1_2(0)}")
    
    println("\n=== Exercício 2.1 ===")
    println("4 é par? ${ehPar(4)}")
    println("7 é par? ${ehPar(7)}")
    
    println("\n=== Exercício 2.2 ===")
    println("Kotlin invertido: ${"Kotlin".inverte()}")
    
    println("\n=== Exercício 3.1 ===")
    println("Comprimento de 'Hello': ${comprimentoOuZero("Hello")}")
    println("Comprimento de null: ${comprimentoOuZero(null)}")
    
    println("\n=== Exercício 3.2 ===")
    println(descreveEndereco("Rua das Flores", 123, "São Paulo"))
    println(descreveEndereco(null, null, null))
    
    println("\n=== Exercício 4.1 ===")
    val aluno = Aluno("João", "2023001", listOf(8.5, 9.0, 7.5))
    println("Aluno: ${aluno.nome}, Média: ${aluno.calcularMedia()}")
    
    println("\n=== Exercício 4.2 ===")
    val retangulo = Retangulo(5.0, 3.0)
    val quadrado = Retangulo(4.0, 4.0)
    
    println("Retângulo - Área: ${retangulo.calcularArea()}, Perímetro: ${retangulo.calcularPerimetro()}, Tipo: ${retangulo.tipoForma()}")
    println("Quadrado - Área: ${quadrado.calcularArea()}, Perímetro: ${quadrado.calcularPerimetro()}, Tipo: ${quadrado.tipoForma()}")
}
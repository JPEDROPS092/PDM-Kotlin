package exercicios

import java.io.File

// Exercício 1: Validação de Entrada
fun lerIdade() {
    println("=== EXERCÍCIO 1: Validação de Entrada ===")
    print("Digite sua idade: ")
    
    try {
        val entrada = readLine() ?: ""
        val idade = entrada.toInt()
        println("Sua idade é: $idade anos")
    } catch (e: NumberFormatException) {
        println("❌ Erro: Por favor, digite um número válido para a idade!")
        println("Você digitou texto em vez de um número.")
    }
}

// Exercício 2: Cálculo de Média
fun calcularMedia(notas: List<Int>) {
    println("\n=== EXERCÍCIO 2: Cálculo de Média ===")
    println("Calculando média das notas: $notas")
    
    try {
        if (notas.isEmpty()) {
            throw IllegalArgumentException("A lista de notas não pode estar vazia.")
        }
        
        val media = notas.sum().toDouble() / notas.size
        println("✅ Média calculada: ${"%.2f".format(media)}")
        
    } catch (e: IllegalArgumentException) {
        println("❌ Erro: ${e.message}")
    } catch (e: ArithmeticException) {
        println("❌ Erro aritmético: ${e.message}")
    }
}

// Exercício 3: Exceção Personalizada
class SenhaInvalidaException(message: String) : IllegalArgumentException(message)

fun validarSenha(senha: String) {
    println("\n=== EXERCÍCIO 3: Validação de Senha ===")
    println("Validando senha: ${senha.map { '*' }.joinToString("")}")
    
    try {
        when {
            senha.length < 8 -> {
                throw SenhaInvalidaException("A senha deve ter pelo menos 8 caracteres. Atual: ${senha.length}")
            }
            !senha.any { it.isDigit() } -> {
                throw SenhaInvalidaException("A senha deve conter pelo menos um número.")
            }
            else -> {
                println("✅ Senha válida!")
            }
        }
    } catch (e: SenhaInvalidaException) {
        println("❌ Senha inválida: ${e.message}")
    }
}

// Exercício 4: Leitura de Arquivo com runCatching
fun lerDadosDoArquivo(caminho: String): Map<String, String>? {
    println("\n=== EXERCÍCIO 4: Leitura de Arquivo ===")
    println("Tentando ler arquivo: $caminho")
    
    return runCatching {
        val linhas = File(caminho).readLines()
        val dados = mutableMapOf<String, String>()
        
        linhas.forEach { linha ->
            if (linha.contains(":")) {
                val partes = linha.split(":", limit = 2)
                if (partes.size == 2) {
                    dados[partes[0].trim()] = partes[1].trim()
                }
            }
        }
        
        dados.toMap()
    }.onSuccess { dados ->
        println("✅ Arquivo lido com sucesso!")
        dados.forEach { (chave, valor) ->
            println("  $chave: $valor")
        }
    }.onFailure { exception ->
        println("❌ Erro ao ler arquivo: ${exception.javaClass.simpleName} - ${exception.message}")
    }.getOrNull()
}

// Função auxiliar para criar arquivo de teste
fun criarArquivoTeste(caminho: String) {
    try {
        File(caminho).writeText("""
            nome:João
            idade:30
            cidade:São Paulo
        """.trimIndent())
        println("Arquivo de teste criado: $caminho")
    } catch (e: Exception) {
        println("Erro ao criar arquivo de teste: ${e.message}")
    }
}

// Exercícios adicionais de demonstração
fun demonstrarTryWithResources() {
    println("\n=== DEMONSTRAÇÃO: Try com Recursos ===")
    
    val nomeArquivo = "teste_recursos.txt"
    
    try {
        File(nomeArquivo).writeText("Teste de recursos\nSegunda linha\nTerceira linha")
        
        // Usando use (equivalente ao try-with-resources do Java)
        File(nomeArquivo).useLines { linhas ->
            println("Linhas do arquivo:")
            linhas.forEachIndexed { index, linha ->
                println("${index + 1}: $linha")
            }
        }
        
    } catch (e: Exception) {
        println("Erro: ${e.message}")
    } finally {
        // Limpeza
        File(nomeArquivo).delete()
        println("Arquivo de teste removido.")
    }
}

fun demonstrarMultiplasExcecoes() {
    println("\n=== DEMONSTRAÇÃO: Múltiplas Exceções ===")
    
    val casos = listOf(
        "123" to "Conversão válida",
        "abc" to "Texto inválido",
        "" to "String vazia",
        "999999999999999999999" to "Número muito grande"
    )
    
    casos.forEach { (entrada, descricao) ->
        println("\nTestando: $descricao -> '$entrada'")
        
        try {
            val numero = entrada.toInt()
            val resultado = 100 / numero
            println("✅ Resultado: 100 / $numero = $resultado")
            
        } catch (e: NumberFormatException) {
            println("❌ Formato inválido: Não é possível converter '$entrada' para número")
        } catch (e: ArithmeticException) {
            println("❌ Erro aritmético: ${e.message}")
        } catch (e: Exception) {
            println("❌ Erro inesperado: ${e.javaClass.simpleName} - ${e.message}")
        }
    }
}

fun main() {
    println("🔧 DEMONSTRAÇÃO DE TRATAMENTO DE EXCEÇÕES EM KOTLIN\n")
    
    // Exercício 1: Validação de entrada
    lerIdade()
    
    // Exercício 2: Cálculo de média
    println("\nTestando com lista válida:")
    calcularMedia(listOf(8, 7, 9, 6))
    
    println("\nTestando com lista vazia:")
    calcularMedia(listOf())
    
    // Exercício 3: Validação de senha
    println("\nTestando senhas:")
    validarSenha("123") // Muito curta
    validarSenha("senhavalida") // Sem número
    validarSenha("minhasenha123") // Válida
    
    // Exercício 4: Leitura de arquivo
    val arquivoTeste = "dados_teste.txt"
    criarArquivoTeste(arquivoTeste)
    
    val dados = lerDadosDoArquivo(arquivoTeste)
    println("Dados retornados: $dados")
    
    println("\nTentando ler arquivo inexistente:")
    lerDadosDoArquivo("arquivo_inexistente.txt")
    
    // Limpeza
    File(arquivoTeste).delete()
    
    // Demonstrações adicionais
    demonstrarTryWithResources()
    demonstrarMultiplasExcecoes()
    
    println("\n✨ Demonstração concluída!")
}
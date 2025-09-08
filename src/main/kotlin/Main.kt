import questoes.*

fun main() {
    println("🎯 PROJETO PDM - KOTLIN")
    println("📚 Exercícios Básicos + Sistema Supermercado SPW")
    println("════════════════════════════════════════════════")
    println()
    
    while (true) {
        println("📋 MENU PRINCIPAL:")
        println("1. Exercícios Básicos de Sintaxe")
        println("2. Exercícios Complementares")
        println("3. Exercícios de Manipulação de Arquivos")
        println("4. Exercícios de Tratamento de Exceções")
        println("5. Sistema Supermercado - Questões Parte I (AP2)")
        println("6. Sistema Supermercado - Questões Parte II (AF)")
        println("0. Sair")
        println()
        
        print("Escolha uma opção: ")
        val opcao = readLine()?.trim()
        
        println()
        
        when (opcao) {
            "1" -> {
                println("🔧 Para executar exercícios básicos de sintaxe:")
                println("./gradlew run --main-class=exercicios.SintaxeBasicaKt")
            }
            
            "2" -> {
                println("📊 Para executar exercícios complementares:")
                println("./gradlew run --main-class=exercicios.ExerciciosComplementaresKt")
            }
            
            "3" -> {
                println("📁 Para executar exercícios de manipulação de arquivos:")
                println("./gradlew run --main-class=exercicios.ManipulacaoArquivosKt")
            }
            
            "4" -> {
                println("⚠️ Para executar exercícios de tratamento de exceções:")
                println("./gradlew run --main-class=exercicios.TratamentoExcecoesKt")
            }
            
            "5" -> {
                menuParteI()
            }
            
            "6" -> {
                menuParteII()
            }
            
            "0" -> {
                println("👋 Encerrando programa...")
                break
            }
            
            else -> {
                println("❌ Opção inválida! Tente novamente.")
            }
        }
        
        println("\n" + "=".repeat(50) + "\n")
    }
}

fun menuParteI() {
    println("🏪 SISTEMA SUPERMERCADO SPW - PARTE I (AP2)")
    println("════════════════════════════════════════")
    
    while (true) {
        println("\n📋 QUESTÕES DISPONÍVEIS:")
        println("1. Questão 1 - Listagem completa do estoque")
        println("2. Questão 2 - Estoque agrupado por gênero")
        println("3. Questão 3 - Itens válidos por gênero e marca")
        println("4. Questão 4 - Contagem válidos/vencidos")
        println("5. Questão 5 - Carrinho com códigos ímpares")
        println("6. Questão 6 - Seleção aleatória de itens")
        println("7. Questão 7 - Busca por marca")
        println("8. Questão 8 - Verificação de código")
        println("0. Voltar ao menu principal")
        
        print("\nEscolha uma questão: ")
        val opcao = readLine()?.trim()
        
        println()
        
        when (opcao) {
            "1" -> AP2Questao1.main(arrayOf())
            "2" -> AP2Questao2.main(arrayOf())
            "3" -> AP2Questao3.main(arrayOf())
            "4" -> AP2Questao4.main(arrayOf())
            "5" -> AP2Questao5.main(arrayOf())
            "6" -> AP2Questao6.main(arrayOf())
            "7" -> AP2Questao7.main(arrayOf())
            "8" -> AP2Questao8.main(arrayOf())
            "0" -> return
            else -> println("❌ Opção inválida!")
        }
        
        println("\n" + "-".repeat(50))
    }
}

fun menuParteII() {
    println("🏪 SISTEMA SUPERMERCADO SPW - PARTE II (AF)")
    println("═══════════════════════════════════════")
    
    while (true) {
        println("\n📋 QUESTÕES DISPONÍVEIS:")
        println("1. Questão 1 - Busca por gênero (polimorfismo)")
        println("2. Questão 2 - Busca por data de validade")
        println("3. Questão 3 - Produtos mais baratos por gênero")
        println("4. Questão 4 - Análise financeira completa")
        println("5. Questão 5 - Movimentação avançada de itens")
        println("6. Questão 6 - Sistema com dois carrinhos")
        println("7. Questão 7 - Verificação final de código")
        println("0. Voltar ao menu principal")
        
        print("\nEscolha uma questão: ")
        val opcao = readLine()?.trim()
        
        println()
        
        when (opcao) {
            "1" -> AFQuestao1.main(arrayOf())
            "2" -> AFQuestao2.main(arrayOf())
            "3" -> AFQuestao3.main(arrayOf())
            "4" -> AFQuestao4.main(arrayOf())
            "5" -> AFQuestao5.main(arrayOf())
            "6" -> AFQuestao6.main(arrayOf())
            "7" -> AFQuestao7.main(arrayOf())
            "0" -> return
            else -> println("❌ Opção inválida!")
        }
        
        println("\n" + "-".repeat(50))
    }
}
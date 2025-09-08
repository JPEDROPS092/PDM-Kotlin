package questoes

import supermercado.SupermercadoWeb

class AFQuestao4 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            
            println("=== ANÁLISE FINANCEIRA DO SUPERMERCADO SPW ===")
            println()
            
            // Passo 1: Calcular lucro potencial com todos os itens
            val todosItens = estoque.getItens()
            val lucroTotal = todosItens.sumOf { it.getProduto().getPreco().toDouble() }
            
            println("1. SITUAÇÃO INICIAL DO ESTOQUE:")
            println("   Total de itens no estoque: ${todosItens.size}")
            println("   Lucro potencial (venda de todos os itens): R$ ${String.format("%.2f", lucroTotal)}")
            println()
            
            // Separar itens válidos e vencidos
            val itensValidos = todosItens.filter { it.valido() }
            val itensVencidos = todosItens.filter { !it.valido() }
            
            println("   Itens válidos: ${itensValidos.size}")
            println("   Itens vencidos: ${itensVencidos.size}")
            println()
            
            // Passo 2: Calcular prejuízo com itens vencidos
            val prejuizo = itensVencidos.sumOf { it.getProduto().getPreco().toDouble() }
            
            println("2. CÁLCULO DO PREJUÍZO:")
            println("   Valor dos itens vencidos (prejuízo): R$ ${String.format("%.2f", prejuizo)}")
            
            // Mostrar distribuição do prejuízo por gênero
            val prejuizoPorGenero = itensVencidos.groupBy { it.getProduto().getGenero().getNome() }
            println("   Distribuição do prejuízo por gênero:")
            prejuizoPorGenero.forEach { (genero, itens) ->
                val valorGenero = itens.sumOf { it.getProduto().getPreco().toDouble() }
                println("     $genero: ${itens.size} itens - R$ ${String.format("%.2f", valorGenero)}")
            }
            println()
            
            // Passo 3: Remover itens vencidos do estoque (simulação)
            println("3. REMOVENDO ITENS VENCIDOS DO ESTOQUE...")
            
            // Criar uma cópia da lista para evitar ConcurrentModificationException
            val itensVencidosParaRemover = itensVencidos.toList()
            itensVencidosParaRemover.forEach { item ->
                estoque.saiItem(item)
            }
            
            // Verificar novo estado do estoque
            val itensRestantes = estoque.getItens()
            val lucroAposLimpeza = itensRestantes.sumOf { it.getProduto().getPreco().toDouble() }
            
            println("   Itens removidos: ${itensVencidosParaRemover.size}")
            println("   Itens restantes no estoque: ${itensRestantes.size}")
            println("   Lucro potencial após limpeza: R$ ${String.format("%.2f", lucroAposLimpeza)}")
            println()
            
            // Passo 4: Calcular lucro real
            val lucroReal = lucroAposLimpeza - prejuizo
            
            println("4. CÁLCULO DO LUCRO REAL:")
            println("   Lucro potencial inicial: R$ ${String.format("%.2f", lucroTotal)}")
            println("   (-) Prejuízo com itens vencidos: R$ ${String.format("%.2f", prejuizo)}")
            println("   (=) LUCRO REAL: R$ ${String.format("%.2f", lucroReal)}")
            println()
            
            // Estatísticas adicionais
            val percentualPrejuizo = (prejuizo / lucroTotal) * 100
            val percentualLucroReal = (lucroReal / lucroTotal) * 100
            
            println("=== ANÁLISE PERCENTUAL ===")
            println("   Percentual de prejuízo: ${String.format("%.1f", percentualPrejuizo)}%")
            println("   Percentual do lucro real: ${String.format("%.1f", percentualLucroReal)}%")
            println()
            
            // Análise por gênero após limpeza
            println("=== ESTOQUE APÓS LIMPEZA ===")
            println("GÊNERO\t\t\tITENS RESTANTES\tVALOR POTENCIAL")
            println("-------\t\t\t---------------\t---------------")
            
            val generos = supermercado.getGeneros()
            generos.forEach { genero ->
                val itensGenero = estoque.getItens(genero)
                val valorGenero = itensGenero.sumOf { it.getProduto().getPreco().toDouble() }
                
                println("${genero.getNome().padEnd(15)}\t${itensGenero.size.toString().padStart(10)}\t\t" +
                        "R$ ${String.format("%.2f", valorGenero)}")
            }
            
            println()
            println("TOTAL GERAL:\t\t${itensRestantes.size}\t\tR$ ${String.format("%.2f", lucroAposLimpeza)}")
            
            // Recomendações
            println()
            println("=== RECOMENDAÇÕES GERENCIAIS ===")
            when {
                percentualPrejuizo > 30 -> println("⚠️  CRÍTICO: Alto percentual de prejuízo! Revisar gestão de estoque.")
                percentualPrejuizo > 15 -> println("⚠️  ATENÇÃO: Percentual de prejuízo elevado. Melhorar rotatividade.")
                else -> println("✅ SATISFATÓRIO: Percentual de prejuízo dentro do aceitável.")
            }
            
            if (itensVencidos.isNotEmpty()) {
                val generoMaiorPrejuizo = prejuizoPorGenero.maxByOrNull { it.value.sumOf { item -> item.getProduto().getPreco().toDouble() } }
                if (generoMaiorPrejuizo != null) {
                    println("💡 Foco: Gênero '${generoMaiorPrejuizo.key}' apresenta maior prejuízo.")
                }
            }
        }
    }
}
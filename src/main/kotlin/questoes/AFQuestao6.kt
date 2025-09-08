package questoes

import supermercado.SupermercadoWebModificado

class AFQuestao6 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWebModificado()
            val estoque = supermercado.getEstoque()
            val carrinhoA = supermercado.getCarrinhoA()
            val carrinhoB = supermercado.getCarrinhoB()
            
            println("=== SISTEMA COM DOIS CARRINHOS ===")
            println()
            
            // Situação inicial
            println("SITUAÇÃO INICIAL:")
            mostrarEstadoCompleto(estoque, carrinhoA, carrinhoB)
            
            // Passo 1: Preencher carrinhoA com itens válidos
            println("\n1. PREENCHENDO CARRINHO A COM ITENS VÁLIDOS:")
            
            val todosItens = estoque.getItens().toList()
            val itensValidos = todosItens.filter { it.valido() }
            
            println("   Total de itens válidos disponíveis: ${itensValidos.size}")
            
            var itensMovidosParaA = 0
            itensValidos.forEach { item ->
                val antesEstoque = estoque.getItens().size
                carrinhoA.adicionaItem(item, estoque)
                val depoisEstoque = estoque.getItens().size
                
                if (antesEstoque > depoisEstoque) {
                    itensMovidosParaA++
                }
            }
            
            println("   ✅ ${itensMovidosParaA} itens válidos movidos para Carrinho A")
            
            // Passo 2: Preencher carrinhoB com itens inválidos (vencidos)
            println("\n2. PREENCHENDO CARRINHO B COM ITENS INVÁLIDOS:")
            
            val itensRestantes = estoque.getItens().toList()
            val itensInvalidos = itensRestantes.filter { !it.valido() }
            
            println("   Total de itens inválidos disponíveis: ${itensInvalidos.size}")
            
            var itensMovidosParaB = 0
            itensInvalidos.forEach { item ->
                val antesEstoque = estoque.getItens().size
                carrinhoB.adicionaItem(item, estoque)
                val depoisEstoque = estoque.getItens().size
                
                if (antesEstoque > depoisEstoque) {
                    itensMovidosParaB++
                }
            }
            
            println("   ✅ ${itensMovidosParaB} itens inválidos movidos para Carrinho B")
            
            // Passo 3: Mostrar situação final
            println("\n3. SITUAÇÃO FINAL:")
            mostrarEstadoCompleto(estoque, carrinhoA, carrinhoB)
            
            // Análises detalhadas
            println("\n=== ANÁLISES DETALHADAS ===")
            
            val itensCarrinhoA = carrinhoA.getItens()
            val itensCarrinhoB = carrinhoB.getItens()
            val itensEstoqueRestante = estoque.getItens()
            
            // Análise Carrinho A (válidos)
            println("\nCARRINHO A (Itens Válidos):")
            if (itensCarrinhoA.isNotEmpty()) {
                val distribuicaoGeneroA = itensCarrinhoA.groupBy { it.getProduto().getGenero().getNome() }
                distribuicaoGeneroA.forEach { (genero, itens) ->
                    val valor = itens.sumOf { it.getProduto().getPreco().toDouble() }
                    println("   $genero: ${itens.size} itens (R$ ${String.format("%.2f", valor)})")
                }
                println("   Total a pagar: R$ ${String.format("%.2f", carrinhoA.totalAPagar())}")
            } else {
                println("   (Vazio)")
            }
            
            // Análise Carrinho B (inválidos)
            println("\nCARRINHO B (Itens Inválidos):")
            if (itensCarrinhoB.isNotEmpty()) {
                val distribuicaoGeneroB = itensCarrinhoB.groupBy { it.getProduto().getGenero().getNome() }
                distribuicaoGeneroB.forEach { (genero, itens) ->
                    val valor = itens.sumOf { it.getProduto().getPreco().toDouble() }
                    println("   $genero: ${itens.size} itens (R$ ${String.format("%.2f", valor)} - PREJUÍZO)")
                }
                println("   Total do prejuízo: R$ ${String.format("%.2f", carrinhoB.totalAPagar())}")
            } else {
                println("   (Vazio)")
            }
            
            // Análise do estoque restante
            println("\nESTOQUE RESTANTE:")
            if (itensEstoqueRestante.isNotEmpty()) {
                val validosRestantes = itensEstoqueRestante.count { it.valido() }
                val invalidosRestantes = itensEstoqueRestante.count { !it.valido() }
                
                println("   Itens válidos restantes: $validosRestantes")
                println("   Itens inválidos restantes: $invalidosRestantes")
                
                if (validosRestantes > 0 || invalidosRestantes > 0) {
                    println("   ⚠️  ATENÇÃO: Nem todos os itens foram movidos para os carrinhos!")
                }
            } else {
                println("   ✅ Estoque completamente vazio - todos os itens foram categorizados!")
            }
            
            // Resumo financeiro
            println("\n=== RESUMO FINANCEIRO ===")
            val receitaPotencial = carrinhoA.totalAPagar()
            val prejuizoPotencial = carrinhoB.totalAPagar()
            val lucroLiquido = receitaPotencial - prejuizoPotencial
            
            println("Receita potencial (Carrinho A): R$ ${String.format("%.2f", receitaPotencial)}")
            println("Prejuízo com vencidos (Carrinho B): R$ ${String.format("%.2f", prejuizoPotencial)}")
            println("Lucro líquido estimado: R$ ${String.format("%.2f", lucroLiquido)}")
            
            val percentualPrejuizo = if (receitaPotencial > 0) (prejuizoPotencial / receitaPotencial) * 100 else 0.0
            println("Percentual de prejuízo: ${String.format("%.1f", percentualPrejuizo)}%")
        }
        
        private fun mostrarEstadoCompleto(
            estoque: supermercado.Estoque, 
            carrinhoA: supermercado.Carrinho, 
            carrinhoB: supermercado.Carrinho
        ) {
            val itensEstoque = estoque.getItens()
            val itensCarrinhoA = carrinhoA.getItens()
            val itensCarrinhoB = carrinhoB.getItens()
            
            println("┌─────────────────────────────────────────────────────┐")
            println("│                    ESTADO ATUAL                    │")
            println("├─────────────────────────────────────────────────────┤")
            println("│ ESTOQUE:     ${itensEstoque.size.toString().padStart(3)} itens                              │")
            println("│ CARRINHO A:  ${itensCarrinhoA.size.toString().padStart(3)} itens (R$ ${String.format("%8.2f", carrinhoA.totalAPagar())})        │")
            println("│ CARRINHO B:  ${itensCarrinhoB.size.toString().padStart(3)} itens (R$ ${String.format("%8.2f", carrinhoB.totalAPagar())})        │")
            println("│ TOTAL:       ${(itensEstoque.size + itensCarrinhoA.size + itensCarrinhoB.size).toString().padStart(3)} itens                              │")
            println("└─────────────────────────────────────────────────────┘")
        }
    }
}
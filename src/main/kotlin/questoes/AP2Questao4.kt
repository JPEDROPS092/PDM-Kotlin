package questoes

import supermercado.SupermercadoWeb

class AP2Questao4 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            val todosItens = estoque.getItens()
            
            val itensValidos = todosItens.count { it.valido() }
            val itensVencidos = todosItens.count { !it.valido() }
            val somaItens = itensValidos + itensVencidos
            val totalEstoque = estoque.qtdItens()
            
            println("=== RELATÓRIO DE VALIDADE DO ESTOQUE ===")
            println()
            println("Itens válidos (não vencidos): $itensValidos")
            println("Itens vencidos: $itensVencidos")
            println("Soma dos itens (válidos + vencidos): $somaItens")
            println("Total de itens no estoque: $totalEstoque")
            println()
            
            if (somaItens == totalEstoque) {
                println("✅ VERIFICAÇÃO: A soma confere com o total do estoque!")
            } else {
                println("❌ VERIFICAÇÃO: A soma NÃO confere com o total do estoque!")
            }
            
            println()
            println("=== DETALHAMENTO ===")
            println("Porcentagem de itens válidos: ${String.format("%.1f", (itensValidos.toDouble() / totalEstoque) * 100)}%")
            println("Porcentagem de itens vencidos: ${String.format("%.1f", (itensVencidos.toDouble() / totalEstoque) * 100)}%")
        }
    }
}
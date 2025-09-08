package questoes

import supermercado.SupermercadoWeb

class AP2Questao5 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            val carrinho = supermercado.getCarrinho()
            
            // Obter todos os itens do estoque
            val todosItens = estoque.getItens().toMutableList()
            
            // Selecionar itens com código ímpar
            val itensCodigoImpar = todosItens.filter { it.getCodigo() % 2 != 0L }
            
            println("=== ITENS COM CÓDIGO ÍMPAR SELECIONADOS ===")
            println("${itensCodigoImpar.size} itens serão adicionados ao carrinho")
            println()
            
            // Adicionar ao carrinho (usando uma cópia da lista para evitar ConcurrentModificationException)
            val itensParaAdicionar = itensCodigoImpar.toList()
            itensParaAdicionar.forEach { item ->
                carrinho.adicionaItem(item, estoque)
            }
            
            // Mostrar itens do carrinho
            val itensCarrinho = carrinho.getItens()
            println("=== ITENS NO CARRINHO ===")
            println("CÓD\tNOME\t\t\tGENERO\t\t\tMARCA\t\tPREÇO\tVALIDADE\tVÁLIDO?")
            println("---\t-------------\t\t-------------\t\t---------\t-------\t----------\t-------")
            
            itensCarrinho.forEach { item ->
                val produto = item.getProduto()
                val valido = if (item.valido()) "sim" else "não"
                
                println("${item.getCodigo()}\t${produto.getNome().take(12).padEnd(12)}\t\t" +
                        "${produto.getGenero().getNome().padEnd(12)}\t\t" +
                        "${produto.getMarca().getNome().take(8).padEnd(8)}\t" +
                        "R$${String.format("%.2f", produto.getPreco())}\t" +
                        "${item.getValidade().toString()}\t$valido")
            }
            
            println()
            println("Quantidade no carrinho: ${itensCarrinho.size} itens")
            println("Total a pagar: R$ ${String.format("%.2f", carrinho.totalAPagar())}")
            
            // Mostrar itens restantes no estoque
            val itensRestantes = estoque.getItens()
            println()
            println("=== ITENS RESTANTES NO ESTOQUE ===")
            println("Quantidade restante: ${itensRestantes.size} itens")
            
            println()
            println("=== RESUMO ===")
            println("Itens movidos para carrinho: ${itensCarrinho.size}")
            println("Itens restantes no estoque: ${itensRestantes.size}")
            println("Total original: ${itensCarrinho.size + itensRestantes.size}")
        }
    }
}
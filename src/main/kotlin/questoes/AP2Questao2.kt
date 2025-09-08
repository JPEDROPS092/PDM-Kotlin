package questoes

import supermercado.SupermercadoWeb

class AP2Questao2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            val generos = supermercado.getGeneros()
            
            println("###### ESTOQUE POR GÊNERO ######")
            println("CÓD\tNOME\t\t\tGENERO\t\t\tMARCA\t\tPREÇO\tVALIDADE\tVÁLIDO?")
            println("---\t-------------\t\t-------------\t\t---------\t-------\t----------\t-------")
            
            var totalGeral = 0
            
            generos.forEach { genero ->
                val itensDoGenero = estoque.getItens(genero)
                
                itensDoGenero.forEach { item ->
                    val produto = item.getProduto()
                    val valido = if (item.valido()) "sim" else "não"
                    
                    println("${item.getCodigo()}\t${produto.getNome().take(12).padEnd(12)}\t\t" +
                            "${produto.getGenero().getNome().padEnd(12)}\t\t" +
                            "${produto.getMarca().getNome().take(8).padEnd(8)}\t" +
                            "R$${String.format("%.2f", produto.getPreco())}\t" +
                            "${item.getValidade().toString()}\t$valido")
                }
                
                println("TOTAL GENERO: ${itensDoGenero.size} itens.")
                totalGeral += itensDoGenero.size
            }
            
            println()
            println("TOTAL: $totalGeral itens.")
        }
    }
}
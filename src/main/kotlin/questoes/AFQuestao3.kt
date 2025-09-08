package questoes

import supermercado.SupermercadoWeb

class AFQuestao3 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            val generos = supermercado.getGeneros()
            
            println("=== PRODUTOS MAIS BARATOS DE CADA GÊNERO ===")
            println()
            
            val produtosMaisBaratos = mutableMapOf<String, Pair<supermercado.Produto, Float>>()
            
            generos.forEach { genero ->
                val itensDoGenero = estoque.getItens(genero)
                
                if (itensDoGenero.isNotEmpty()) {
                    // Agrupar por produto e pegar os preços únicos
                    val produtosDoGenero = itensDoGenero
                        .map { it.getProduto() }
                        .distinctBy { it.getCodigo() } // Evita produtos duplicados
                    
                    // Encontrar o produto com menor preço
                    val produtoMaisBarato = produtosDoGenero.minByOrNull { it.getPreco() }
                    
                    if (produtoMaisBarato != null) {
                        produtosMaisBaratos[genero.getNome()] = 
                            Pair(produtoMaisBarato, produtoMaisBarato.getPreco())
                    }
                }
            }
            
            if (produtosMaisBaratos.isEmpty()) {
                println("❌ Nenhum produto encontrado no estoque!")
                return
            }
            
            println("GÊNERO\t\t\tPRODUTO MAIS BARATO\t\tMARCA\t\tPREÇO")
            println("-------\t\t\t-------------------\t\t------\t\t-----")
            
            produtosMaisBaratos.forEach { (nomeGenero, produtoPreco) ->
                val produto = produtoPreco.first
                val preco = produtoPreco.second
                
                println("${nomeGenero.padEnd(15)}\t${produto.getNome().take(20).padEnd(20)}\t" +
                        "${produto.getMarca().getNome().take(10).padEnd(10)}\t" +
                        "R$ ${String.format("%.2f", preco)}")
            }
            
            println()
            println("=== ANÁLISE DETALHADA ===")
            
            produtosMaisBaratos.forEach { (nomeGenero, produtoPreco) ->
                val produto = produtoPreco.first
                val preco = produtoPreco.second
                
                // Contar quantos itens deste produto estão no estoque
                val itensDisponiveis = estoque.getItens(produto).size
                val itensValidos = estoque.getItens(produto).count { it.valido() }
                
                println()
                println("📦 $nomeGenero:")
                println("   Produto mais barato: ${produto.getNome()}")
                println("   Marca: ${produto.getMarca().getNome()}")
                println("   Preço: R$ ${String.format("%.2f", preco)}")
                println("   Itens disponíveis no estoque: $itensDisponiveis")
                println("   Itens válidos: $itensValidos")
                
                // Comparar com outros produtos do mesmo gênero
                val genero = supermercado.getGeneros().find { it.getNome() == nomeGenero }
                if (genero != null) {
                    val todosItensGenero = estoque.getItens(genero)
                    val todosProdutosGenero = todosItensGenero
                        .map { it.getProduto() }
                        .distinctBy { it.getCodigo() }
                        .sortedBy { it.getPreco() }
                    
                    if (todosProdutosGenero.size > 1) {
                        println("   Outros produtos do gênero:")
                        todosProdutosGenero.drop(1).take(3).forEach { outroProduto ->
                            val diferenca = outroProduto.getPreco() - preco
                            println("     - ${outroProduto.getNome()} (${outroProduto.getMarca().getNome()}): " +
                                   "R$ ${String.format("%.2f", outroProduto.getPreco())} " +
                                   "(+R$ ${String.format("%.2f", diferenca)})")
                        }
                    }
                }
            }
            
            // Resumo geral
            val menorPrecoGeral = produtosMaisBaratos.values.minByOrNull { it.second }
            val maiorPrecoGeral = produtosMaisBaratos.values.maxByOrNull { it.second }
            
            println()
            println("=== RESUMO GERAL ===")
            if (menorPrecoGeral != null && maiorPrecoGeral != null) {
                println("Produto mais barato geral: ${menorPrecoGeral.first.getNome()} - R$ ${String.format("%.2f", menorPrecoGeral.second)}")
                println("Produto mais caro entre os mais baratos: ${maiorPrecoGeral.first.getNome()} - R$ ${String.format("%.2f", maiorPrecoGeral.second)}")
                
                val diferenca = maiorPrecoGeral.second - menorPrecoGeral.second
                println("Diferença de preço: R$ ${String.format("%.2f", diferenca)}")
            }
        }
    }
}
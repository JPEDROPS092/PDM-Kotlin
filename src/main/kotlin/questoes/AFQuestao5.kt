package questoes

import supermercado.SupermercadoWeb
import kotlin.random.Random

class AFQuestao5 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            val carrinho = supermercado.getCarrinho()
            
            println("=== DEMONSTRAÇÃO DE MOVIMENTAÇÃO DE ITENS ===")
            println()
            
            // Passo 1: Mostrar estoque e carrinho inicial
            println("1. SITUAÇÃO INICIAL:")
            mostrarEstoqueECarrinho(estoque, carrinho, "INICIAL")
            
            // Passo 2: Preencher carrinho com 10 itens aleatórios
            println("\n2. PREENCHENDO CARRINHO COM 10 ITENS ALEATÓRIOS:")
            
            val todosItens = estoque.getItens().toMutableList()
            val quantidadeParaAdicionar = minOf(10, todosItens.size)
            
            if (todosItens.isEmpty()) {
                println("❌ Estoque vazio! Não é possível adicionar itens ao carrinho.")
                return
            }
            
            println("   Adicionando $quantidadeParaAdicionar itens aleatórios ao carrinho...")
            
            // Lista auxiliar para rastrear os itens adicionados (necessária para o passo 3)
            val itensAdicionados = mutableListOf<supermercado.ItemProduto>()
            
            repeat(quantidadeParaAdicionar) {
                if (todosItens.isNotEmpty()) {
                    val indiceAleatorio = Random.nextInt(todosItens.size)
                    val itemSelecionado = todosItens.removeAt(indiceAleatorio)
                    
                    // Tentar adicionar ao carrinho
                    val itensBefore = estoque.getItens().size
                    carrinho.adicionaItem(itemSelecionado, estoque)
                    val itensAfter = estoque.getItens().size
                    
                    // Se o item foi realmente removido do estoque, adicionar à lista auxiliar
                    if (itensBefore > itensAfter) {
                        itensAdicionados.add(itemSelecionado)
                        println("     ✓ Item ${itemSelecionado.getCodigo()} - ${itemSelecionado.getProduto().getNome()} adicionado")
                    }
                }
            }
            
            mostrarEstoqueECarrinho(estoque, carrinho, "APÓS ADIÇÃO")
            
            // Passo 3: Colocar todos os itens do carrinho de volta no estoque
            println("\n3. RETORNANDO TODOS OS ITENS DO CARRINHO AO ESTOQUE:")
            
            if (itensAdicionados.isEmpty()) {
                println("   ❌ Nenhum item foi adicionado ao carrinho anteriormente.")
            } else {
                println("   Retornando ${itensAdicionados.size} itens ao estoque...")
                
                // Usar lista auxiliar para retornar os itens
                itensAdicionados.forEach { item ->
                    carrinho.removeItem(item, estoque)
                    println("     ✓ Item ${item.getCodigo()} - ${item.getProduto().getNome()} retornado ao estoque")
                }
            }
            
            mostrarEstoqueECarrinho(estoque, carrinho, "FINAL")
            
            // Verificação final
            println("\n=== VERIFICAÇÃO DE INTEGRIDADE ===")
            val carrinhoFinal = carrinho.getItens()
            val estoqueFinal = estoque.getItens()
            
            if (carrinhoFinal.isEmpty()) {
                println("✅ Carrinho está vazio conforme esperado")
            } else {
                println("❌ Erro: Carrinho deveria estar vazio!")
            }
            
            println("📊 Resumo final:")
            println("   - Itens no estoque: ${estoqueFinal.size}")
            println("   - Itens no carrinho: ${carrinhoFinal.size}")
            println("   - Valor total do estoque: R$ ${String.format("%.2f", estoqueFinal.sumOf { it.getProduto().getPreco().toDouble() })}")
            println("   - Valor total do carrinho: R$ ${String.format("%.2f", carrinho.totalAPagar())}")
        }
        
        private fun mostrarEstoqueECarrinho(estoque: supermercado.Estoque, carrinho: supermercado.Carrinho, momento: String) {
            println()
            println("=== SITUAÇÃO $momento ===")
            
            val itensEstoque = estoque.getItens()
            val itensCarrinho = carrinho.getItens()
            
            println("ESTOQUE:")
            println("   Total de itens: ${itensEstoque.size}")
            if (itensEstoque.isNotEmpty()) {
                println("   Valor total: R$ ${String.format("%.2f", itensEstoque.sumOf { it.getProduto().getPreco().toDouble() })}")
                
                val distribuicaoPorGenero = itensEstoque.groupBy { it.getProduto().getGenero().getNome() }
                println("   Distribuição por gênero:")
                distribuicaoPorGenero.forEach { (genero, itens) ->
                    println("     $genero: ${itens.size} itens")
                }
            }
            
            println("CARRINHO:")
            println("   Total de itens: ${itensCarrinho.size}")
            if (itensCarrinho.isNotEmpty()) {
                println("   Total a pagar: R$ ${String.format("%.2f", carrinho.totalAPagar())}")
                
                val distribuicaoPorGenero = itensCarrinho.groupBy { it.getProduto().getGenero().getNome() }
                println("   Distribuição por gênero:")
                distribuicaoPorGenero.forEach { (genero, itens) ->
                    println("     $genero: ${itens.size} itens")
                }
            } else {
                println("   (Carrinho vazio)")
            }
        }
    }
}
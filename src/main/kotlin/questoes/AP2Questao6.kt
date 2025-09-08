package questoes

import supermercado.SupermercadoWeb
import kotlin.random.Random

class AP2Questao6 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            val carrinho = supermercado.getCarrinho()
            
            // Obter apenas itens válidos (não vencidos)
            val itensValidos = estoque.getItens().filter { it.valido() }.toMutableList()
            
            if (itensValidos.isEmpty()) {
                println("❌ Nenhum item válido encontrado no estoque!")
                return
            }
            
            // Determinar quantidade aleatória de itens para adicionar (entre 5 e 15)
            val quantidadeParaAdicionar = Random.nextInt(5, minOf(16, itensValidos.size + 1))
            
            println("=== SELEÇÃO ALEATÓRIA DE ITENS VÁLIDOS ===")
            println("Total de itens válidos disponíveis: ${itensValidos.size}")
            println("Quantidade a ser adicionada aleatoriamente: $quantidadeParaAdicionar")
            println()
            
            // Selecionar itens aleatoriamente
            val itensSelecionados = mutableListOf<supermercado.ItemProduto>()
            repeat(quantidadeParaAdicionar) {
                if (itensValidos.isNotEmpty()) {
                    val indiceAleatorio = Random.nextInt(itensValidos.size)
                    val item = itensValidos.removeAt(indiceAleatorio)
                    itensSelecionados.add(item)
                }
            }
            
            // Adicionar itens selecionados ao carrinho
            itensSelecionados.forEach { item ->
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
            println("Quantidade de itens no carrinho: ${itensCarrinho.size}")
            println("Total a pagar: R$ ${String.format("%.2f", carrinho.totalAPagar())}")
            
            // Mostrar itens restantes no estoque
            val itensRestantes = estoque.getItens()
            println()
            println("=== ITENS RESTANTES NO ESTOQUE ===")
            println("Total de itens restantes: ${itensRestantes.size}")
            
            // Estatísticas por gênero
            println()
            println("=== ESTATÍSTICAS POR GÊNERO ===")
            val generos = supermercado.getGeneros()
            generos.forEach { genero ->
                val itensDoGeneroNoCarrinho = itensCarrinho.count { it.getProduto().getGenero().getNome() == genero.getNome() }
                val itensDoGeneroNoEstoque = itensRestantes.count { it.getProduto().getGenero().getNome() == genero.getNome() }
                
                println("${genero.getNome()}: $itensDoGeneroNoCarrinho no carrinho, $itensDoGeneroNoEstoque no estoque")
            }
        }
    }
}
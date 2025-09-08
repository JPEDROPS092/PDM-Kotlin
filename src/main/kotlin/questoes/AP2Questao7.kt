package questoes

import supermercado.SupermercadoWeb

class AP2Questao7 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            
            print("Digite o nome da marca para buscar: ")
            val nomeMarca = readLine()?.trim() ?: ""
            
            if (nomeMarca.isEmpty()) {
                println("❌ Nome da marca não pode estar vazio!")
                return
            }
            
            println()
            println("=== BUSCA POR MARCA: \"$nomeMarca\" ===")
            
            // Buscar marca correspondente (case-insensitive)
            val marcaEncontrada = supermercado.getMarcas()
                .find { it.getNome().lowercase().contains(nomeMarca.lowercase()) }
            
            if (marcaEncontrada == null) {
                println("❌ Marca não encontrada!")
                println()
                println("Marcas disponíveis:")
                supermercado.getMarcas().forEach { marca ->
                    println("- ${marca.getNome()}")
                }
                return
            }
            
            // Obter itens válidos da marca encontrada
            val itensValidosDaMarca = estoque.getItens(marcaEncontrada)
                .filter { it.valido() }
            
            if (itensValidosDaMarca.isEmpty()) {
                println("❌ Nenhum item válido encontrado para a marca \"${marcaEncontrada.getNome()}\"")
                return
            }
            
            println("✅ Encontrados ${itensValidosDaMarca.size} itens válidos da marca \"${marcaEncontrada.getNome()}\"")
            println()
            
            println("CÓD\tNOME\t\t\tGENERO\t\t\tMARCA\t\tPREÇO\tVALIDADE\tVÁLIDO?")
            println("---\t-------------\t\t-------------\t\t---------\t-------\t----------\t-------")
            
            itensValidosDaMarca.forEach { item ->
                val produto = item.getProduto()
                val valido = if (item.valido()) "sim" else "não"
                
                println("${item.getCodigo()}\t${produto.getNome().take(12).padEnd(12)}\t\t" +
                        "${produto.getGenero().getNome().padEnd(12)}\t\t" +
                        "${produto.getMarca().getNome().take(8).padEnd(8)}\t" +
                        "R$${String.format("%.2f", produto.getPreco())}\t" +
                        "${item.getValidade().toString()}\t$valido")
            }
            
            println()
            println("TOTAL ENCONTRADO: ${itensValidosDaMarca.size} itens válidos")
            
            // Estatísticas adicionais
            val valorTotalItens = itensValidosDaMarca.sumOf { it.getProduto().getPreco().toDouble() }
            val generos = itensValidosDaMarca.groupBy { it.getProduto().getGenero().getNome() }
            
            println()
            println("=== ESTATÍSTICAS ===")
            println("Valor total dos itens: R$ ${String.format("%.2f", valorTotalItens)}")
            println("Distribuição por gênero:")
            generos.forEach { (genero, itens) ->
                println("  $genero: ${itens.size} itens")
            }
        }
    }
}
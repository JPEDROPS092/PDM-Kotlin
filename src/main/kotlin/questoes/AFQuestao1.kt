package questoes

import supermercado.*

class AFQuestao1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            
            print("Digite o nome do gênero desejado (Leite, Presunto, Achocolatado): ")
            val nomeGenero = readLine()?.trim() ?: ""
            
            if (nomeGenero.isEmpty()) {
                println("❌ Nome do gênero não pode estar vazio!")
                return
            }
            
            println()
            println("=== BUSCA POR GÊNERO: \"$nomeGenero\" ===")
            
            // Instanciar objeto do gênero baseado na entrada do usuário
            val genero = when (nomeGenero.lowercase()) {
                "leite" -> Leite()
                "presunto" -> Presunto()
                "achocolatado" -> Achocolatado()
                else -> {
                    println("❌ Gênero não reconhecido!")
                    println("Gêneros disponíveis: Leite, Presunto, Achocolatado")
                    return
                }
            }
            
            // Usar o método getItens() com o objeto gênero como parâmetro
            val itensDoGenero = estoque.getItens(genero)
            
            if (itensDoGenero.isEmpty()) {
                println("❌ Nenhum item encontrado para o gênero \"${genero.getNome()}\"")
                return
            }
            
            println("✅ Encontrados ${itensDoGenero.size} itens do gênero \"${genero.getNome()}\"")
            println()
            
            println("###### ESTOQUE ######")
            println("CÓD\tNOME\t\t\tGENERO\t\t\tMARCA\t\tPREÇO\tVALIDADE\tVÁLIDO?")
            println("---\t-------------\t\t-------------\t\t---------\t-------\t----------\t-------")
            
            itensDoGenero.forEach { item ->
                val produto = item.getProduto()
                val valido = if (item.valido()) "sim" else "não"
                
                println("${item.getCodigo()}\t${produto.getNome().take(12).padEnd(12)}\t\t" +
                        "${produto.getGenero().getNome().padEnd(12)}\t\t" +
                        "${produto.getMarca().getNome().take(8).padEnd(8)}\t" +
                        "R$${String.format("%.2f", produto.getPreco())}\t" +
                        "${item.getValidade().toString()}\t$valido")
            }
            
            println()
            println("TOTAL: ${itensDoGenero.size} itens.")
            
            // Estatísticas adicionais
            val itensValidos = itensDoGenero.count { it.valido() }
            val itensVencidos = itensDoGenero.count { !it.valido() }
            val valorTotal = itensDoGenero.sumOf { it.getProduto().getPreco().toDouble() }
            
            println()
            println("=== ESTATÍSTICAS DO GÊNERO ===")
            println("Itens válidos: $itensValidos")
            println("Itens vencidos: $itensVencidos")
            println("Valor total: R$ ${String.format("%.2f", valorTotal)}")
            
            // Distribuição por marca
            val distribuicaoPorMarca = itensDoGenero.groupBy { it.getProduto().getMarca().getNome() }
            println("Distribuição por marca:")
            distribuicaoPorMarca.forEach { (marca, itens) ->
                println("  $marca: ${itens.size} itens")
            }
        }
    }
}
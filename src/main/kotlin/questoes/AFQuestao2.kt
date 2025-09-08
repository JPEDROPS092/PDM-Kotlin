package questoes

import supermercado.SupermercadoWeb
import supermercado.Data

class AFQuestao2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val supermercado = SupermercadoWeb()
            val estoque = supermercado.getEstoque()
            
            println("Digite uma data para buscar produtos vencidos:")
            
            print("Dia (1-31): ")
            val dia = readLine()?.toIntOrNull()
            if (dia == null || dia !in 1..31) {
                println("❌ Dia inválido!")
                return
            }
            
            print("Mês (1-12): ")
            val mes = readLine()?.toIntOrNull()
            if (mes == null || mes !in 1..12) {
                println("❌ Mês inválido!")
                return
            }
            
            print("Ano: ")
            val ano = readLine()?.toIntOrNull()
            if (ano == null || ano < 1900) {
                println("❌ Ano inválido!")
                return
            }
            
            val dataLimite = Data(dia, mes, ano)
            
            println()
            println("=== BUSCA POR PRODUTOS VENCIDOS ANTES DE ${dataLimite.toString()} ===")
            
            // Buscar todos os itens do estoque
            val todosItens = estoque.getItens()
            
            // Filtrar itens cuja data de validade é anterior (mais antiga) que a data fornecida
            val listaX = mutableListOf<supermercado.ItemProduto>()
            
            todosItens.forEach { item ->
                if (item.getValidade().antes(dataLimite)) {
                    listaX.add(item)
                }
            }
            
            if (listaX.isEmpty()) {
                println("✅ Nenhum produto encontrado com validade anterior a ${dataLimite.toString()}")
                return
            }
            
            println("❌ Encontrados ${listaX.size} produtos com validade ultrapassada")
            println()
            
            println("CÓD\tNOME\t\t\tGENERO\t\t\tMARCA\t\tPREÇO\tVALIDADE\tDIAS EM ATRASO")
            println("---\t-------------\t\t-------------\t\t---------\t-------\t----------\t--------------")
            
            listaX.forEach { item ->
                val produto = item.getProduto()
                
                // Calcular diferença aproximada em dias (simplificado)
                val diasAtraso = calcularDiferencaAproximada(item.getValidade(), dataLimite)
                
                println("${item.getCodigo()}\t${produto.getNome().take(12).padEnd(12)}\t\t" +
                        "${produto.getGenero().getNome().padEnd(12)}\t\t" +
                        "${produto.getMarca().getNome().take(8).padEnd(8)}\t" +
                        "R$${String.format("%.2f", produto.getPreco())}\t" +
                        "${item.getValidade().toString()}\t$diasAtraso dias")
            }
            
            println()
            println("TOTAL: ${listaX.size} itens vencidos.")
            
            // Estatísticas adicionais
            val valorPrejuizo = listaX.sumOf { it.getProduto().getPreco().toDouble() }
            val distribucaoPorGenero = listaX.groupBy { it.getProduto().getGenero().getNome() }
            
            println()
            println("=== ANÁLISE DOS PRODUTOS VENCIDOS ===")
            println("Valor total em prejuízo: R$ ${String.format("%.2f", valorPrejuizo)}")
            println("Distribuição por gênero:")
            distribucaoPorGenero.forEach { (genero, itens) ->
                val valorGenero = itens.sumOf { it.getProduto().getPreco().toDouble() }
                println("  $genero: ${itens.size} itens (R$ ${String.format("%.2f", valorGenero)})")
            }
        }
        
        private fun calcularDiferencaAproximada(dataAnterior: Data, dataPosterior: Data): Int {
            // Cálculo aproximado de diferença em dias (simplificado)
            val diasPorMes = 30
            val diasPorAno = 365
            
            val diasAnterior = dataAnterior.getDia() + (dataAnterior.getMes() * diasPorMes) + (dataAnterior.getAno() * diasPorAno)
            val diasPosterior = dataPosterior.getDia() + (dataPosterior.getMes() * diasPorMes) + (dataPosterior.getAno() * diasPorAno)
            
            return kotlin.math.abs(diasPosterior - diasAnterior)
        }
    }
}
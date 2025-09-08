package supermercado

class Carrinho {
    private val itens = mutableListOf<ItemProduto>()
    
    fun adicionaItem(item: ItemProduto, estoque: Estoque) {
        val itemRetirado = estoque.saiItem(item)
        if (itemRetirado != null) {
            itens.add(itemRetirado)
        }
    }
    
    fun removeItem(item: ItemProduto, estoque: Estoque) {
        if (itens.remove(item)) {
            estoque.entraItem(item)
        }
    }
    
    fun getItens(): List<ItemProduto> = itens.toList()
    
    fun totalAPagar(): Double {
        return if (itens.isEmpty()) {
            0.0
        } else {
            itens.sumOf { it.getProduto().getPreco().toDouble() }
        }
    }
}
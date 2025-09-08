package supermercado

class Estoque {
    private val itens = mutableListOf<ItemProduto>()
    
    fun entraItem(item: ItemProduto) {
        itens.add(item)
    }
    
    fun saiItem(item: ItemProduto): ItemProduto? {
        return if (itens.remove(item)) {
            item
        } else {
            null
        }
    }
    
    fun getItens(): List<ItemProduto> = itens.toList()
    
    fun getItens(marca: Marca): List<ItemProduto> {
        return itens.filter { it.getProduto().getMarca().getNome() == marca.getNome() }
    }
    
    fun getItens(produto: Produto): List<ItemProduto> {
        return itens.filter { it.getProduto().getCodigo() == produto.getCodigo() }
    }
    
    fun getItens(genero: Genero): List<ItemProduto> {
        return itens.filter { it.getProduto().getGenero().getNome() == genero.getNome() }
    }
    
    fun qtdItens(): Int = itens.size
}
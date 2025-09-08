package supermercado

class ItemProduto(
    private val codigo: Long,
    private val produto: Produto,
    private val validade: Data
) {
    
    fun getCodigo(): Long = codigo
    fun getProduto(): Produto = produto
    fun getValidade(): Data = validade
    
    fun valido(): Boolean {
        val hoje = Data.dataHoje()
        return validade.depois(hoje)
    }
    
    override fun toString(): String {
        return "ItemProduto(codigo=$codigo, produto=${produto.getNome()}, validade=${validade.toString()}, válido=${if(valido()) "sim" else "não"})"
    }
}
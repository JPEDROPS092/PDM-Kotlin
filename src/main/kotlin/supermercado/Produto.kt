package supermercado

class Produto(
    private val codigo: Int,
    private var nome: String,
    private var preco: Float,
    private val marca: Marca,
    private val genero: Genero
) {
    
    fun getCodigo(): Int = codigo
    fun getNome(): String = nome
    fun getPreco(): Float = preco
    fun getMarca(): Marca = marca
    fun getGenero(): Genero = genero
    
    fun setNome(nome: String) {
        this.nome = nome
    }
    
    fun setPreco(preco: Float) {
        this.preco = preco
    }
    
    override fun toString(): String {
        return "Produto(codigo=$codigo, nome='$nome', preco=$preco, marca=${marca.getNome()}, genero=${genero.getNome()})"
    }
}
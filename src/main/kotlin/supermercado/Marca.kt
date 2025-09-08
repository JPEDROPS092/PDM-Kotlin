package supermercado

class Marca(private var nome: String) {
    
    fun getNome(): String = nome
    
    fun setNome(nome: String) {
        this.nome = nome
    }
    
    override fun toString(): String = nome
}
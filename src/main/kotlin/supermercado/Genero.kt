package supermercado

abstract class Genero(protected var nome: String) {
    abstract fun getNome(): String
    abstract fun setNome(nome: String)
    
    override fun toString(): String = nome
}

class Leite : Genero("Leite") {
    override fun getNome(): String = nome
    override fun setNome(nome: String) {
        this.nome = nome
    }
}

class Presunto : Genero("Presunto") {
    override fun getNome(): String = nome
    override fun setNome(nome: String) {
        this.nome = nome
    }
}

class Achocolatado : Genero("Achocolatado") {
    override fun getNome(): String = nome
    override fun setNome(nome: String) {
        this.nome = nome
    }
}
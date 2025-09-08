package supermercado

abstract class Genero(private var _nome: String) {
    abstract fun getNome(): String
    abstract fun setNome(nome: String)
    
    protected fun getInternalNome(): String = _nome
    protected fun setInternalNome(nome: String) {
        this._nome = nome
    }
    
    override fun toString(): String = _nome
}

class Leite : Genero("Leite") {
    override fun getNome(): String = getInternalNome()
    override fun setNome(nome: String) {
        setInternalNome(nome)
    }
}

class Presunto : Genero("Presunto") {
    override fun getNome(): String = getInternalNome()
    override fun setNome(nome: String) {
        setInternalNome(nome)
    }
}

class Achocolatado : Genero("Achocolatado") {
    override fun getNome(): String = getInternalNome()
    override fun setNome(nome: String) {
        setInternalNome(nome)
    }
}
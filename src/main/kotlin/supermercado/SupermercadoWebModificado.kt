package supermercado

import kotlin.random.Random

class SupermercadoWebModificado {
    private val estoque = Estoque()
    private val carrinhoA = Carrinho()
    private val carrinhoB = Carrinho()
    private val marcas = mutableListOf<Marca>()
    private val produtos = mutableListOf<Produto>()
    private val generos = mutableListOf<Genero>()
    
    init {
        iniciaSupermercado()
    }
    
    private fun iniciaSupermercado() {
        // Criar gêneros
        val leite = Leite()
        val achocolatado = Achocolatado()
        val presunto = Presunto()
        generos.addAll(listOf(leite, achocolatado, presunto))
        
        // Criar marcas
        val nestle = Marca("Nestlé")
        val itambe = Marca("Itambé")
        val duleite = Marca("Duleite")
        val abf = Marca("ABF")
        val pepsico = Marca("Pepsico do Brasil")
        val perdigao = Marca("Perdigão")
        val sadia = Marca("Sadia")
        val fazenda = Marca("Fazenda")
        val bretzke = Marca("Bretzke")
        
        marcas.addAll(listOf(nestle, itambe, duleite, abf, pepsico, perdigao, sadia, fazenda, bretzke))
        
        // Criar produtos de Leite
        produtos.add(Produto(1, "Ninho", 10.35f, nestle, leite))
        produtos.add(Produto(2, "Molico", 12.50f, nestle, leite))
        produtos.add(Produto(3, "Farinha Láctea", 8.99f, nestle, leite))
        produtos.add(Produto(4, "Leite em Pó Itambé", 9.80f, itambe, leite))
        produtos.add(Produto(5, "Leite em Caixa", 4.50f, duleite, leite))
        produtos.add(Produto(6, "Leite em Pó", 11.20f, duleite, leite))
        produtos.add(Produto(7, "Leite em Caixa", 4.80f, fazenda, leite))
        produtos.add(Produto(8, "Tio Joca", 11.00f, fazenda, leite))
        
        // Criar produtos de Achocolatado
        produtos.add(Produto(9, "Nescau", 12.50f, nestle, achocolatado))
        produtos.add(Produto(10, "Chocolate em Pó", 15.99f, nestle, achocolatado))
        produtos.add(Produto(11, "Ovomaltine", 15.00f, abf, achocolatado))
        produtos.add(Produto(12, "Toddy", 13.75f, pepsico, achocolatado))
        produtos.add(Produto(13, "Muky", 10.50f, bretzke, achocolatado))
        
        // Criar produtos de Presunto
        produtos.add(Produto(14, "Delícia", 5.50f, perdigao, presunto))
        produtos.add(Produto(15, "Salsichon", 7.50f, sadia, presunto))
        produtos.add(Produto(16, "Presunto Especial", 8.99f, perdigao, presunto))
        
        // Criar itens de produto e adicionar ao estoque
        var codigoItem = 1000L
        
        // 10 itens de cada gênero de 3 marcas diferentes
        val produtosLeite = produtos.filter { it.getGenero() is Leite }.take(3)
        val produtosAchocolatado = produtos.filter { it.getGenero() is Achocolatado }.take(3) 
        val produtosPresunto = produtos.filter { it.getGenero() is Presunto }.take(3)
        
        val todosProdutosSelecionados = produtosLeite + produtosAchocolatado + produtosPresunto
        
        todosProdutosSelecionados.forEach { produto ->
            repeat(10) {
                val diasValidade = Random.nextInt(-30, 365) // Alguns vencidos, outros válidos
                val dataValidade = Data(
                    Random.nextInt(1, 29),
                    Random.nextInt(1, 13),
                    if (diasValidade < 0) 2009 else Random.nextInt(2024, 2026)
                )
                
                val item = ItemProduto(codigoItem++, produto, dataValidade)
                estoque.entraItem(item)
            }
        }
    }
    
    fun getMarcas(): List<Marca> = marcas.toList()
    fun getProdutos(): List<Produto> = produtos.toList()
    fun getEstoque(): Estoque = estoque
    fun getCarrinhoA(): Carrinho = carrinhoA
    fun getCarrinhoB(): Carrinho = carrinhoB
    fun getGeneros(): List<Genero> = generos.toList()
}
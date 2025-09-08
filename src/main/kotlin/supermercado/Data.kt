package supermercado

import java.time.LocalDate

class Data(private var dia: Int, private var mes: Int, private var ano: Int) {
    
    fun toString(): String = String.format("%02d/%02d/%04d", dia, mes, ano)
    
    fun setData(dia: Int, mes: Int, ano: Int) {
        this.dia = dia
        this.mes = mes
        this.ano = ano
    }
    
    fun antes(outraData: Data): Boolean {
        return when {
            ano < outraData.ano -> true
            ano > outraData.ano -> false
            mes < outraData.mes -> true
            mes > outraData.mes -> false
            dia < outraData.dia -> true
            else -> false
        }
    }
    
    fun depois(outraData: Data): Boolean {
        return when {
            ano > outraData.ano -> true
            ano < outraData.ano -> false
            mes > outraData.mes -> true
            mes < outraData.mes -> false
            dia > outraData.dia -> true
            else -> false
        }
    }
    
    fun igual(outraData: Data): Boolean {
        return dia == outraData.dia && mes == outraData.mes && ano == outraData.ano
    }
    
    companion object {
        fun dataHoje(): Data {
            val hoje = LocalDate.now()
            return Data(hoje.dayOfMonth, hoje.monthValue, hoje.year)
        }
    }
    
    fun getDia(): Int = dia
    fun getMes(): Int = mes
    fun getAno(): Int = ano
}
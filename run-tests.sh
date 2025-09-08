#!/bin/bash

# 🚀 Script para Testar Todas as Funcionalidades do Projeto PDM-Kotlin

echo "🎯 PDM-KOTLIN - SCRIPT DE TESTES COMPLETOS"
echo "============================================="
echo

# Compilar projeto
echo "📦 Compilando projeto..."
./gradlew build -q
if [ $? -eq 0 ]; then
    echo "✅ Compilação bem-sucedida!"
else
    echo "❌ Falha na compilação!"
    exit 1
fi
echo

# Função para executar com timeout
run_with_timeout() {
    local class_name=$1
    local description=$2
    echo "🔧 Executando: $description"
    echo "   Comando: ./gradlew run -q --main-class=$class_name"
    
    # Criar arquivo temporário com input simulado
    echo -e "0\n" > /tmp/input.txt
    
    timeout 10s ./gradlew run -q --main-class=$class_name < /tmp/input.txt
    
    if [ $? -eq 124 ]; then
        echo "   ⏱️ Execução finalizada (timeout de 10s)"
    elif [ $? -eq 0 ]; then
        echo "   ✅ Execução concluída com sucesso"
    else
        echo "   ⚠️ Execução finalizada com código: $?"
    fi
    echo
}

# Menu principal
echo "1️⃣ TESTES DOS EXERCÍCIOS BÁSICOS:"
echo "=================================="

# Exercícios básicos
run_with_timeout "exercicios.SintaxeBasicaKt" "Exercícios de Sintaxe Básica"
run_with_timeout "exercicios.ExerciciosComplementaresKt" "Exercícios Complementares"
run_with_timeout "exercicios.ManipulacaoArquivosKt" "Manipulação de Arquivos"
run_with_timeout "exercicios.TratamentoExcecoesKt" "Tratamento de Exceções"

echo "2️⃣ TESTES DO SISTEMA SUPERMERCADO - PARTE I (AP2):"
echo "================================================="

# Questões AP2
run_with_timeout "questoes.AP2Questao1Kt" "AP2 Questão 1 - Listagem do Estoque"
run_with_timeout "questoes.AP2Questao2Kt" "AP2 Questão 2 - Estoque por Gênero"
run_with_timeout "questoes.AP2Questao3Kt" "AP2 Questão 3 - Itens Válidos"
run_with_timeout "questoes.AP2Questao4Kt" "AP2 Questão 4 - Contagem Válidos/Vencidos"
run_with_timeout "questoes.AP2Questao5Kt" "AP2 Questão 5 - Carrinho Códigos Ímpares"
run_with_timeout "questoes.AP2Questao6Kt" "AP2 Questão 6 - Seleção Aleatória"
run_with_timeout "questoes.AP2Questao8Kt" "AP2 Questão 8 - Verificação de Código"

echo "3️⃣ TESTES DO SISTEMA SUPERMERCADO - PARTE II (AF):"
echo "================================================="

# Questões AF
run_with_timeout "questoes.AFQuestao3Kt" "AF Questão 3 - Produtos Mais Baratos"
run_with_timeout "questoes.AFQuestao4Kt" "AF Questão 4 - Análise Financeira"
run_with_timeout "questoes.AFQuestao5Kt" "AF Questão 5 - Movimentação Avançada"
run_with_timeout "questoes.AFQuestao6Kt" "AF Questão 6 - Sistema Dois Carrinhos"
run_with_timeout "questoes.AFQuestao7Kt" "AF Questão 7 - Verificação Final"

echo "4️⃣ TESTE DO MENU PRINCIPAL INTERATIVO:"
echo "======================================"
echo "🔧 Para executar o menu principal interativo:"
echo "   ./gradlew run -q --main-class=MainKt"
echo

echo "5️⃣ COMANDOS INDIVIDUAIS PARA TESTES MANUAIS:"
echo "============================================"
echo "📚 Exercícios Básicos:"
echo "   ./gradlew run -q --main-class=exercicios.SintaxeBasicaKt"
echo "   ./gradlew run -q --main-class=exercicios.ExerciciosComplementaresKt"
echo "   ./gradlew run -q --main-class=exercicios.ManipulacaoArquivosKt"
echo "   ./gradlew run -q --main-class=exercicios.TratamentoExcecoesKt"
echo
echo "🏪 Sistema Supermercado SPW (AP2):"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao1Kt"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao2Kt"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao3Kt"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao4Kt"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao5Kt"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao6Kt"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao7Kt"
echo "   ./gradlew run -q --main-class=questoes.AP2Questao8Kt"
echo
echo "🏆 Sistema Supermercado SPW (AF):"
echo "   ./gradlew run -q --main-class=questoes.AFQuestao1Kt"
echo "   ./gradlew run -q --main-class=questoes.AFQuestao2Kt"
echo "   ./gradlew run -q --main-class=questoes.AFQuestao3Kt"
echo "   ./gradlew run -q --main-class=questoes.AFQuestao4Kt"
echo "   ./gradlew run -q --main-class=questoes.AFQuestao5Kt"
echo "   ./gradlew run -q --main-class=questoes.AFQuestao6Kt"
echo "   ./gradlew run -q --main-class=questoes.AFQuestao7Kt"
echo

echo "6️⃣ TESTES COM KOTLIN COMPILER DIRETO:"
echo "===================================="
echo "kotlinc -cp 'build/libs/*:build/classes/kotlin/main' -script src/main/kotlin/exercicios/SintaxeBasica.kt"
echo

echo "7️⃣ INFORMAÇÕES DO PROJETO:"
echo "=========================="
echo "📊 Estrutura do projeto:"
find src -name "*.kt" | head -20
echo "   ... (total de $(find src -name "*.kt" | wc -l) arquivos Kotlin)"
echo
echo "📈 Estatísticas:"
echo "   - Linhas de código: $(find src -name "*.kt" -exec cat {} \; | wc -l)"
echo "   - Classes implementadas: $(grep -r "^class\|^abstract class\|^data class" src --include="*.kt" | wc -l)"
echo "   - Funções implementadas: $(grep -r "^fun\|^    fun" src --include="*.kt" | wc -l)"
echo

# Limpeza
rm -f /tmp/input.txt

echo "✅ SCRIPT DE TESTES CONCLUÍDO!"
echo "==============================================="
echo "🎯 Todos os comandos estão prontos para uso!"
echo "📚 Consulte o README.md para documentação completa"
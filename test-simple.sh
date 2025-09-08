#!/bin/bash

echo "🚀 TESTES SIMPLIFICADOS - PDM KOTLIN"
echo "===================================="
echo

# Compilar
echo "📦 Compilando..."
./gradlew build -q
echo "✅ Compilado com sucesso!"
echo

# Listar arquivos disponíveis
echo "📁 Arquivos Kotlin disponíveis:"
find src/main/kotlin -name "*.kt" | sort
echo

# Executar questão específica que não precisa de input
echo "🔧 Executando AP2 Questão 8 (Verificação de Código):"
echo "---------------------------------------------------"
kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao8.kt
echo

echo "🔧 Executando AF Questão 7 (Verificação Final):"
echo "----------------------------------------------"
kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AFQuestao7.kt
echo

echo "📋 COMANDOS PARA TESTES MANUAIS:"
echo "================================"
echo
echo "🎯 Menu Principal Interativo:"
echo "   ./gradlew run -q"
echo
echo "📚 Executar exercícios específicos (sem input):"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/exercicios/SintaxeBasica.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/exercicios/ExerciciosComplementares.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/exercicios/ManipulacaoArquivos.kt"
echo
echo "🏪 Executar questões do supermercado (sem input):"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao1.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao2.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao3.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao4.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao5.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao6.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AP2Questao8.kt"
echo
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AFQuestao3.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AFQuestao4.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AFQuestao5.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AFQuestao6.kt"
echo "   kotlinc -cp build/classes/kotlin/main -script src/main/kotlin/questoes/AFQuestao7.kt"
echo
echo "⚠️ Questões que precisam de input do usuário (use interativamente):"
echo "   - AP2Questao7 (busca por marca)"
echo "   - AFQuestao1 (busca por gênero)"  
echo "   - AFQuestao2 (busca por data)"
echo "   - TratamentoExcecoes (validação de entrada)"
echo
echo "🎯 Para testar essas questões interativas:"
echo "   ./gradlew run -q  # Use o menu principal"
echo
echo "📊 Estatísticas do projeto:"
echo "   - Arquivos Kotlin: $(find src -name "*.kt" | wc -l)"
echo "   - Linhas de código: $(find src -name "*.kt" -exec cat {} \; | wc -l)"
echo "   - Classes: $(grep -r "^class\|^abstract class\|^data class" src --include="*.kt" | wc -l)"
echo
echo "✅ TESTES PRONTOS! Use os comandos acima para testar individualmente."
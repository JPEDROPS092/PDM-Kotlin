# 🔄 Pull Request

## 📋 Descrição

### O que foi alterado?
Breve descrição das mudanças implementadas.

### Por que foi alterado?
Contexto e motivação para as mudanças.

### Closes #(issue)
- Closes #[número da issue]

## 🔧 Tipo de Mudança

Marque as opções que se aplicam:

- [ ] 🐛 **Bug fix** (mudança que corrige um problema)
- [ ] ✨ **New feature** (mudança que adiciona funcionalidade)
- [ ] 💥 **Breaking change** (correção ou funcionalidade que causa mudança na API existente)
- [ ] 📚 **Documentation** (melhoria na documentação)
- [ ] 🔧 **Refactoring** (mudança de código que não corrige bug nem adiciona funcionalidade)
- [ ] ⚡ **Performance** (mudança que melhora a performance)
- [ ] 🧪 **Tests** (adição ou correção de testes)
- [ ] 🔨 **Chore** (mudanças no build, ferramentas, etc.)

## 🎯 Categoria do Projeto

- [ ] 📚 **Exercícios Básicos** - Sintaxe, arquivos, exceções
- [ ] 🏪 **Sistema SPW** - Classes base do supermercado
- [ ] 📝 **Questões AP2** - Questões da Parte I
- [ ] 🏆 **Questões AF** - Questões da Parte II  
- [ ] 🎮 **Menu Principal** - Interface interativa
- [ ] 🔧 **Infraestrutura** - Build, scripts, CI/CD

## 🧪 Como Testar

### Pré-requisitos
- Java 17+
- Gradle 8.0+

### Passos para testar

1. ```bash
   git checkout feature-branch-name
   ./gradlew build
   ```

2. ```bash
   ./gradlew run
   # ou
   ./test-simple.sh
   ```

3. **Verificar:**
   - [ ] Funcionalidade X funciona corretamente
   - [ ] Não há regressões em Y
   - [ ] Interface responde adequadamente

### Casos de Teste Específicos

```bash
# Para Sistema SPW
java -cp "build/classes/kotlin/main:$(find ~/.gradle -name 'kotlin-stdlib-*.jar' | head -1)" questoes.AP2Questao1

# Para Exercícios
java -cp "build/classes/kotlin/main:$(find ~/.gradle -name 'kotlin-stdlib-*.jar' | head -1)" exercicios.SintaxeBasicaKt
```

## 📊 Impacto

### Performance
- [ ] ✅ Sem impacto na performance
- [ ] ⚡ Melhora a performance
- [ ] ⚠️ Pode impactar a performance (explicar abaixo)

### Compatibilidade
- [ ] ✅ Mantém compatibilidade total
- [ ] ⚠️ Mudança menor na API (documentada)
- [ ] 💥 Breaking change (justificado e documentado)

### Funcionalidades Afetadas
- [ ] Menu Principal
- [ ] Sistema de Estoque
- [ ] Sistema de Carrinho  
- [ ] Exercícios Básicos
- [ ] Questões AP2/AF
- [ ] Scripts de Build/Teste

## ✅ Checklist

### Código
- [ ] 🏗️ Código compila sem erros (`./gradlew build`)
- [ ] 🧪 Testes passam (`./test-simple.sh`)  
- [ ] 📏 Código segue padrões do projeto (Kotlin conventions)
- [ ] 🔍 Sem warnings ou code smells
- [ ] 🎯 Funcionalidade testada manualmente

### Documentação
- [ ] 📖 README.md atualizado (se necessário)
- [ ] 📝 Comentários no código (se necessário)
- [ ] 🔗 Links e referências atualizados
- [ ] 📋 CHANGELOG.md atualizado (se aplicável)

### Git
- [ ] 📝 Commit messages seguem [Conventional Commits](https://www.conventionalcommits.org/)
- [ ] 🌟 Branch atualizada com a main/develop
- [ ] 🧹 Commits limpos (squash se necessário)

### Review
- [ ] 👀 Self-review realizado
- [ ] 🤝 Ready for community review
- [ ] 📢 Notificações relevantes enviadas

## 📸 Screenshots/Evidências

### Antes
<!-- Screenshot ou log do comportamento anterior -->

### Depois  
<!-- Screenshot ou log do novo comportamento -->

## 📚 Context e Motivação

### Problema Resolvido
Descreva o problema que esta PR resolve.

### Abordagem Escolhida
Explique por que esta abordagem foi escolhida vs. alternativas.

### Considerações de Design
Decisões arquiteturais importantes tomadas.

## 🔄 Breaking Changes

Se esta PR contém breaking changes, descreva:

### Mudanças na API
```kotlin
// Antes
fun antigoMetodo(): String

// Depois  
fun novoMetodo(parametro: String): String
```

### Migration Guide
Instruções para atualizar código existente:

1. Substituir `antigoMetodo()` por `novoMetodo("valor")`
2. Atualizar imports se necessário
3. Executar testes

## 📋 Tarefas Futuras

Issues ou melhorias que podem ser implementadas posteriormente:

- [ ] #123 - Melhoria relacionada A
- [ ] #456 - Funcionalidade complementar B

## 🤝 Revisor Sugerido

@username - Expertise na área X

## 📞 Perguntas

Questões específicas para os revisores:

1. A abordagem X está adequada?
2. Existe uma forma melhor de implementar Y?

---

### 📋 Para os Revisores

**Por favor, verifiquem:**
- [ ] 🏗️ Arquitetura e design
- [ ] 🧪 Cobertura de testes
- [ ] 📖 Clareza da documentação
- [ ] 🎯 Usabilidade educacional
- [ ] ⚡ Performance e otimizações

**Obrigado pela review! 🙏**
package exercicios

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

// Exercício 1: Leitura e Escrita de Arquivos de Texto

fun salvarTarefas(tarefas: List<String>, nomeArquivo: String = "tarefas.txt") {
    try {
        File(nomeArquivo).writeText(tarefas.joinToString("\n"))
        println("Tarefas salvas com sucesso em $nomeArquivo")
    } catch (e: IOException) {
        println("Erro ao salvar tarefas: ${e.message}")
    }
}

fun lerTarefas(nomeArquivo: String = "tarefas.txt") {
    try {
        if (!File(nomeArquivo).exists()) {
            println("Arquivo $nomeArquivo não encontrado!")
            return
        }
        
        val tarefas = File(nomeArquivo).readLines()
        println("=== LISTA DE TAREFAS ===")
        tarefas.forEachIndexed { index, tarefa ->
            println("${index + 1}. $tarefa")
        }
    } catch (e: IOException) {
        println("Erro ao ler tarefas: ${e.message}")
    }
}

fun adicionarTarefa(novaTarefa: String, nomeArquivo: String = "tarefas.txt") {
    try {
        File(nomeArquivo).appendText("\n$novaTarefa")
        println("Tarefa adicionada com sucesso!")
    } catch (e: IOException) {
        println("Erro ao adicionar tarefa: ${e.message}")
    }
}

// Exercício 2: Serialização e Desserialização com JSON

@Serializable
data class ConfiguracaoUsuario(
    val nome: String,
    val idioma: String,
    val tema: String
)

fun salvarConfiguracaoJson(config: ConfiguracaoUsuario, nomeArquivo: String = "config.json") {
    try {
        val json = Json { prettyPrint = true }
        val jsonString = json.encodeToString(config)
        File(nomeArquivo).writeText(jsonString)
        println("Configuração salva em JSON com sucesso!")
    } catch (e: Exception) {
        println("Erro ao salvar configuração JSON: ${e.message}")
    }
}

fun lerConfiguracaoJson(nomeArquivo: String = "config.json"): ConfiguracaoUsuario? {
    return try {
        if (!File(nomeArquivo).exists()) {
            println("Arquivo $nomeArquivo não encontrado!")
            return null
        }
        
        val jsonString = File(nomeArquivo).readText()
        val config = Json.decodeFromString<ConfiguracaoUsuario>(jsonString)
        
        println("=== CONFIGURAÇÕES DO USUÁRIO ===")
        println("Nome: ${config.nome}")
        println("Idioma: ${config.idioma}")
        println("Tema: ${config.tema}")
        
        config
    } catch (e: Exception) {
        println("Erro ao ler configuração JSON: ${e.message}")
        null
    }
}

// Exercício 3: Manipulação de Arquivos Binários

fun criarArquivoBinario(nomeArquivo: String = "app.dat") {
    try {
        val cabecalho = byteArrayOf(0x4B, 0x4F, 0x54, 0x4C, 0x4E) // "KOTLN" em ASCII
        File(nomeArquivo).writeBytes(cabecalho)
        println("Arquivo binário $nomeArquivo criado com sucesso!")
    } catch (e: IOException) {
        println("Erro ao criar arquivo binário: ${e.message}")
    }
}

fun verificarArquivoBinario(nomeArquivo: String = "app.dat") {
    try {
        if (!File(nomeArquivo).exists()) {
            println("Arquivo $nomeArquivo não encontrado!")
            return
        }
        
        val bytesLidos = File(nomeArquivo).readBytes()
        val cabecalhoEsperado = byteArrayOf(0x4B, 0x4F, 0x54, 0x4C, 0x4E)
        
        if (bytesLidos.contentEquals(cabecalhoEsperado)) {
            println("✓ SUCESSO: Cabeçalho do arquivo está correto!")
        } else {
            println("✗ FALHA: Cabeçalho do arquivo não confere!")
        }
    } catch (e: IOException) {
        println("Erro ao verificar arquivo binário: ${e.message}")
    }
}

// Exercício 4: Gerenciamento de Diretórios

fun criarEstruturaDiretorios() {
    try {
        val diretorios = listOf("backup", "backup/fotos", "backup/documentos")
        
        diretorios.forEach { dir ->
            val diretorio = File(dir)
            if (!diretorio.exists()) {
                diretorio.mkdirs()
                println("Diretório '$dir' criado com sucesso!")
            } else {
                println("Diretório '$dir' já existe.")
            }
        }
    } catch (e: Exception) {
        println("Erro ao criar estrutura de diretórios: ${e.message}")
    }
}

fun listarSubdiretorios() {
    try {
        val backupDir = File("backup")
        if (!backupDir.exists()) {
            println("Diretório backup não existe!")
            return
        }
        
        println("=== SUBDIRETÓRIOS EM /backup ===")
        backupDir.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                println("- ${file.name}")
            }
        }
        
        // Criar arquivo notas.txt em /backup/documentos
        val notasFile = File("backup/documentos/notas.txt")
        if (!notasFile.exists()) {
            notasFile.createNewFile()
            println("Arquivo 'notas.txt' criado em /backup/documentos")
        }
    } catch (e: Exception) {
        println("Erro ao listar subdiretórios: ${e.message}")
    }
}

fun excluirDiretorioRecursivo(caminho: String) {
    try {
        val diretorio = File(caminho)
        if (!diretorio.exists()) {
            println("Diretório $caminho não existe!")
            return
        }
        
        if (diretorio.deleteRecursively()) {
            println("Diretório $caminho excluído com sucesso!")
        } else {
            println("Falha ao excluir diretório $caminho")
        }
    } catch (e: Exception) {
        println("Erro ao excluir diretório: ${e.message}")
    }
}

// Exercício 5: Entrada de texto do usuário

fun exercicio5() {
    println("=== EXERCÍCIO 5: Entrada de Texto ===")
    println("Digite várias linhas de texto (digite 'FIM' para finalizar):")
    
    val linhas = mutableListOf<String>()
    
    while (true) {
        val linha = readLine()
        if (linha == "FIM" || linha == null) break
        linhas.add(linha)
    }
    
    // Salvar no arquivo
    val nomeArquivo = "texto_usuario.txt"
    File(nomeArquivo).writeText(linhas.joinToString("\n"))
    println("Texto salvo em $nomeArquivo")
    
    // Ler e mostrar o conteúdo
    println("\n=== CONTEÚDO DO ARQUIVO ===")
    println(File(nomeArquivo).readText())
}

// Exercício 6: Sistema de cadastro CSV

data class Usuario(val nome: String, val email: String, val idade: Int) {
    fun toCSV(): String = "$nome,$email,$idade"
    
    companion object {
        fun fromCSV(linha: String): Usuario? {
            return try {
                val partes = linha.split(",")
                if (partes.size == 3) {
                    Usuario(partes[0], partes[1], partes[2].toInt())
                } else null
            } catch (e: Exception) {
                null
            }
        }
    }
}

object SistemaCadastro {
    private const val ARQUIVO_CSV = "usuarios.csv"
    
    fun adicionarUsuario(usuario: Usuario) {
        try {
            val arquivo = File(ARQUIVO_CSV)
            if (!arquivo.exists()) {
                arquivo.writeText("nome,email,idade\n")
            }
            arquivo.appendText("${usuario.toCSV()}\n")
            println("Usuário ${usuario.nome} adicionado com sucesso!")
        } catch (e: Exception) {
            println("Erro ao adicionar usuário: ${e.message}")
        }
    }
    
    fun listarUsuarios() {
        try {
            val arquivo = File(ARQUIVO_CSV)
            if (!arquivo.exists()) {
                println("Arquivo de usuários não existe!")
                return
            }
            
            val linhas = arquivo.readLines()
            if (linhas.size <= 1) {
                println("Nenhum usuário cadastrado.")
                return
            }
            
            println("=== USUÁRIOS CADASTRADOS ===")
            linhas.drop(1).forEach { linha ->
                val usuario = Usuario.fromCSV(linha)
                usuario?.let {
                    println("Nome: ${it.nome}, Email: ${it.email}, Idade: ${it.idade}")
                }
            }
        } catch (e: Exception) {
            println("Erro ao listar usuários: ${e.message}")
        }
    }
    
    fun buscarUsuario(nome: String) {
        try {
            val arquivo = File(ARQUIVO_CSV)
            if (!arquivo.exists()) return
            
            val linhas = arquivo.readLines()
            val usuarioEncontrado = linhas.drop(1)
                .mapNotNull { Usuario.fromCSV(it) }
                .find { it.nome.lowercase().contains(nome.lowercase()) }
            
            if (usuarioEncontrado != null) {
                println("Usuário encontrado:")
                println("Nome: ${usuarioEncontrado.nome}, Email: ${usuarioEncontrado.email}, Idade: ${usuarioEncontrado.idade}")
            } else {
                println("Usuário não encontrado.")
            }
        } catch (e: Exception) {
            println("Erro ao buscar usuário: ${e.message}")
        }
    }
}

// Exercício 7: Contador de palavras

fun contarPalavras(arquivoEntrada: String, arquivoSaida: String = "frequencia_palavras.txt") {
    try {
        if (!File(arquivoEntrada).exists()) {
            println("Arquivo $arquivoEntrada não encontrado!")
            return
        }
        
        val texto = File(arquivoEntrada).readText().lowercase()
        val palavras = texto.split(Regex("\\W+")).filter { it.isNotBlank() }
        val frequencia = palavras.groupingBy { it }.eachCount()
        
        val resultado = StringBuilder()
        resultado.append("=== FREQUÊNCIA DAS PALAVRAS ===\n")
        frequencia.toSortedMap().forEach { (palavra, count) ->
            resultado.append("$palavra: $count\n")
        }
        
        File(arquivoSaida).writeText(resultado.toString())
        println("Frequência das palavras salva em $arquivoSaida")
        
    } catch (e: Exception) {
        println("Erro ao contar palavras: ${e.message}")
    }
}

// Exercício 8: Sistema de backup

object SistemaBackup {
    
    fun criarBackup(pastaOrigem: String, pastaBackup: String) {
        try {
            val origem = File(pastaOrigem)
            if (!origem.exists()) {
                println("Pasta origem não existe: $pastaOrigem")
                return
            }
            
            val timestamp = System.currentTimeMillis()
            val backupDir = File("$pastaBackup/backup_$timestamp")
            backupDir.mkdirs()
            
            origem.copyRecursively(backupDir, overwrite = true)
            println("Backup criado: ${backupDir.absolutePath}")
            
            // Manter apenas os 5 backups mais recentes
            manterBackupsRecentes(pastaBackup, 5)
            
        } catch (e: Exception) {
            println("Erro ao criar backup: ${e.message}")
        }
    }
    
    private fun manterBackupsRecentes(pastaBackup: String, quantidade: Int) {
        try {
            val backupDir = File(pastaBackup)
            if (!backupDir.exists()) return
            
            val backups = backupDir.listFiles { file -> 
                file.isDirectory && file.name.startsWith("backup_")
            }?.sortedByDescending { it.name.substring(7).toLongOrNull() ?: 0L }
            
            if (backups != null && backups.size > quantidade) {
                backups.drop(quantidade).forEach { backup ->
                    backup.deleteRecursively()
                    println("Backup antigo removido: ${backup.name}")
                }
            }
        } catch (e: Exception) {
            println("Erro ao limpar backups antigos: ${e.message}")
        }
    }
}

fun main() {
    println("=== DEMONSTRAÇÃO DOS EXERCÍCIOS DE ARQUIVOS ===\n")
    
    // Exercício 1
    println("1. EXERCÍCIO DE TAREFAS")
    val tarefas = listOf("Comprar pão", "Estudar Kotlin", "Fazer exercícios")
    salvarTarefas(tarefas)
    lerTarefas()
    adicionarTarefa("Revisar código")
    println()
    
    // Exercício 2
    println("2. EXERCÍCIO JSON")
    val config = ConfiguracaoUsuario("João", "pt-BR", "escuro")
    salvarConfiguracaoJson(config)
    lerConfiguracaoJson()
    println()
    
    // Exercício 3
    println("3. EXERCÍCIO ARQUIVO BINÁRIO")
    criarArquivoBinario()
    verificarArquivoBinario()
    println()
    
    // Exercício 4
    println("4. EXERCÍCIO DIRETÓRIOS")
    criarEstruturaDiretorios()
    listarSubdiretorios()
    println()
    
    // Exercício 6 (demonstração)
    println("6. SISTEMA DE CADASTRO")
    SistemaCadastro.adicionarUsuario(Usuario("João Silva", "joao@email.com", 30))
    SistemaCadastro.adicionarUsuario(Usuario("Maria Santos", "maria@email.com", 25))
    SistemaCadastro.listarUsuarios()
    SistemaCadastro.buscarUsuario("João")
    println()
    
    // Exercício 7 (usando arquivo de tarefas como exemplo)
    println("7. CONTADOR DE PALAVRAS")
    contarPalavras("tarefas.txt")
    
    // Exercício 8
    println("8. SISTEMA DE BACKUP")
    SistemaBackup.criarBackup(".", "backups")
}
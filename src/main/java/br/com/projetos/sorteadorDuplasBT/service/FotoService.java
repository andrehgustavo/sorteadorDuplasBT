package br.com.projetos.sorteadorDuplasBT.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoService {

    private static final String DIRETORIO_FOTOS = "uploads/fotos/";

    public String salvarFoto(MultipartFile file, Long jogadorId) throws IOException {
        String nomeArquivo = "jogador_" + jogadorId + "_" + System.currentTimeMillis() + ".jpg";
        Path caminhoArquivo = Paths.get(DIRETORIO_FOTOS, nomeArquivo);
        
        Files.createDirectories(caminhoArquivo.getParent()); // Garante que o diretório existe
        Files.write(caminhoArquivo, file.getBytes());

        return "/fotos/" + nomeArquivo; // Retorna a URL relativa da foto
    }
}

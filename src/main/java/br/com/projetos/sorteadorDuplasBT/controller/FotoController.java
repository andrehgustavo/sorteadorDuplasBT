package br.com.projetos.sorteadorDuplasBT.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



    @RestController
    @RequestMapping("/fotos")
    public class FotoController {
    @GetMapping("/{nomeArquivo}")
    public ResponseEntity<Resource> obterFoto(@PathVariable String nomeArquivo) throws IOException {
        Path caminhoArquivo = Paths.get("uploads/fotos/", nomeArquivo);
        Resource resource = new UrlResource(caminhoArquivo.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

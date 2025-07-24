package com.example.func;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegrasArquivos {
    
    //Metodo para organizar por tipo de arquivo
    public static void tipoDeArquivo(String diretorio) {

        Map<String, List<String>> tiposArquivosMap = new HashMap<>();

        //lista de extencao 
        tiposArquivosMap.put("Imagens", List.of(".jpeg", ".jpg", ".png", ".gif", ".tiff", ".tif", ".bmp", ".webp", ".raw", ".svg", ".ai", ".eps", ".pdf", ".ico"));
        tiposArquivosMap.put("Documentos", List.of(".pdf", ".docx", ".txt", ".odt", ".rtf", ".xlsx", ".xls", ".ods", ".csv", ".pptx", ".ppt", ".odp", ".html", ".xml", ".json", ".yml", ".ini", ".log", ".sql", ".accdb", ".mdb"));
        tiposArquivosMap.put("Planilhas", List.of(".xlsx", ".xls", ".xlsm", ".xlsb", ".xltx", ".xltm", ".ods", ".csv", ".tsv", ".dif", ".slk", ".gnumeric", ".numbers", ".fods"));
        tiposArquivosMap.put("Videos", List.of(".mp4", ".mov", ".wmv", ".avi", ".mkv", ".flv", ".webm", ".mpeg", ".mpg", ".3gp", ".ts", ".vob", ".ogv", ".m4v", ".rmvb", ".divx", ".xvid"));
        tiposArquivosMap.put("Audios", List.of(".mp3", ".wav", ".aac", ".flac", ".ogg", ".wma", ".m4a", ".alac", ".aiff", ".aif", ".opus", ".amr", ".voc"));
        tiposArquivosMap.put("Executaveis", List.of(".exe", ".msi", ".bat", ".cmd", ".sh", ".app", ".com", ".jar", ".vbs", ".ps1", ".wsf", ".gadget"));
        tiposArquivosMap.put("Compactados", List.of(".zip", ".rar", ".7z", ".tar", ".tgz", ".tbz2", ".iso", ".dmg", ".gz", ".bz2", ".xz", ".arj", ".lzh"));
        tiposArquivosMap.put("Torrents", List.of(".torrent"));

        File pasta = new File(diretorio);

        File[] arquivos = pasta.listFiles();

        //parte para organizacao
        for (File arquivo : arquivos) {
            if (arquivo.isFile()) {
                boolean movido = false;
                String extensao = getExtensao(arquivo.getName());

                for (Map.Entry<String, List<String>> entry : tiposArquivosMap.entrySet()) {
                    if (entry.getValue().contains(extensao.toLowerCase())) {
                        moverArquivo(arquivo, new File(pasta, entry.getKey()));
                        movido = true;
                        break;
                    }
                }

                if (!movido) {
                    moverArquivo(arquivo, new File(pasta, "Outros"));
                }
            }
        }
    }

    //Metodo para organizar por extencao
    public static void extencao(String diretorio){
        List<String> extencoesList = List.of(".jpeg", ".jpg", ".png", ".gif", ".tiff", ".tif", ".bmp", ".webp", ".raw", ".svg", ".ai", ".eps", ".pdf", ".ico", ".docx", ".txt", ".odt", ".rtf", ".xls", ".ods", ".csv", ".pptx", ".ppt", ".odp", ".html", ".xml", ".json", ".yml", ".ini", ".log", ".sql", ".accdb", ".mdb", ".xlsx", ".xlsm", ".xlsb", ".xltx", ".xltm", ".tsv", ".dif", ".slk", ".gnumeric", ".numbers", ".fods", ".mp4", ".mov", ".wmv", ".avi", ".mkv", ".flv", ".webm", ".mpeg", ".mpg", ".3gp", ".ts", ".vob", ".ogv", ".m4v", ".rmvb", ".divx", ".xvid", ".mp3", ".wav", ".aac", ".flac", ".ogg", ".wma", ".m4a", ".alac", ".aiff", ".aif", ".opus", ".amr", ".voc", ".exe", ".msi", ".bat", ".cmd", ".sh", ".app", ".com", ".jar", ".vbs", ".ps1", ".wsf", ".gadget", ".zip", ".rar", ".7z", ".tar", ".tgz", ".tbz2", ".iso", ".dmg", ".gz", ".bz2", ".xz", ".arj", ".lzh", ".torrent");

        File pasta = new File(diretorio);
        File[] arquivos = pasta.listFiles();

        for (File arquivo : arquivos) {
            if (arquivo.isFile()) {
                boolean movido = false;
                String extensao = getExtensao(arquivo.getName());
                
                for (String itens : extencoesList) {
                    if(itens.contains(extensao.toLowerCase())){
                        moverArquivo(arquivo, new File(pasta, itens));
                        movido = true;
                        break;
                    }
                }

                if (!movido) {
                    moverArquivo(arquivo, new File(pasta, "Outros"));
                }
            }
        }
    }

    // Método para obter extensão do arquivo
    private static String getExtensao(String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf(".");
        return i > 0 ? nomeArquivo.substring(i) : "";
    }

    // Método para mover o arquivo
    private static void moverArquivo(File arquivo, File pastaDestino) {
        try {
            if (!pastaDestino.exists()) {
                pastaDestino.mkdir();
            }
            Files.move(arquivo.toPath(), pastaDestino.toPath().resolve(arquivo.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Erro ao mover arquivo: " + arquivo.getName());
        }
    }
}

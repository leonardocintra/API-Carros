package br.com.livro.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class UploadService {
	public String upload(String fileName, InputStream in) throws IOException {
		if (fileName == null || in == null) {
			throw new IllegalArgumentException("Parâmetros inválidos no upload");
		}

		// Pasta temporaria da JVM
		File tmpDir = new File(System.getProperty("java.io.tmpdir"), "carros");
		if (!tmpDir.exists()) {
			// cria a pasta se não existe
			tmpDir.mkdir();
		}

		// cria o arquivo
		File file = new File(tmpDir, fileName);
		// Abre a OutPutStream para escrever no arquivo
		FileOutputStream out = new FileOutputStream(file);
		// escreve dados no arquivo
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(out);
		// retorna o caminho do arquivo
		String path = file.getAbsolutePath();
		return path;
	}
}

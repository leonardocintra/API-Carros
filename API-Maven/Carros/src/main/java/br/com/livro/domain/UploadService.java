package br.com.livro.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.google.api.services.storage.model.StorageObject;
import com.google.common.io.Files;

import br.com.livro.util.CloudStorageUtil;

@Component
public class UploadService {
	
	private static final String PROJECT_ID = "512212512205";
	private static final String ACCOUNT_ID = "512212512205-v691296m6bkob35qmnq7bs4frkdsbg8g@developer.gserviceaccount.com";
	private static final String APP_NAME = "Livro Lecheta";
	private static final String BUCKET_NAME = "apicarros";
	
	
	
	
	
	public String upload(String fileName, InputStream in) throws Exception {
		// Salva o arquivo na pasta temporária da JVM
		File file = saveToTmpDir(fileName, in);
		// Faz upload para o CloudStorage
		String url = uploadToCloudStorage(file);
		// Retorna a url do arquivo;
		return url;
	}
	
	public String uploadToCloudStorage(File file) throws Exception{
		// Arquivo .p12 chave privada
		String s = System.getProperty("p12File");
		if (s == null)
			throw new IOException("Erro no servidor. Verificar chave privada");
		
		File p12File = new File(s);
		if (!p12File.exists())
			throw new IOException("Erro no servidor. Verificar chave privada");
		
		// Conecta no Cloud Storage
		CloudStorageUtil c = new CloudStorageUtil(APP_NAME);
		c.connect(ACCOUNT_ID, p12File);
		// Upload
		String fileName = file.getName();
		String contentType = getContentType(fileName);
		String storageProjectId = PROJECT_ID;
		StorageObject obj = c.upload(BUCKET_NAME, file, contentType, storageProjectId);
		if (obj == null)
			throw new IOException("Erro ao fazer upload.");
		
		// retorna a URL publica
		String url = String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, obj.getName());
		return url;
	}
	// Retorna o content-type para a extensão fornecida
	private String getContentType(String fileName) {
		String ext = Files.getFileExtension(fileName);
		
		if ("png".equals(ext))
			return "image/png";
		else if ("jpg".equals(ext) || "jpeg".equals(ext))
			return "image/jpg";
		else if ("gif".equals(ext))
			return "image/gif";
		else
			return "text/plain";		
	}
	
	private File saveToTmpDir(String fileName, InputStream in) throws FileNotFoundException, IOException{
		if (fileName == null || in == null)
			throw new IllegalArgumentException("Parâmetros inválidos");
		
		// pasta temporaria da JVM
		File tmpDir = new File(System.getProperty("java.io.tmpdir"), "carros");
		if (!tmpDir.exists())
			tmpDir.mkdir();
		
		// Cria o arquivo
		File file = new File(tmpDir, fileName);
		FileOutputStream out = new FileOutputStream(file);
		
		// Escreve os dados no arquivo
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(out);
		return file;
	}
}

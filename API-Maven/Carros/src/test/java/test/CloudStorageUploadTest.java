package test;
import java.io.File;
import com.google.api.services.storage.model.StorageObject;

import br.com.livro.util.CloudStorageUtil;

public class CloudStorageUploadTest {
	private static final String BUCKET_NAME = "apicarros";

	public static void main(String[] args) throws Exception {
		CloudStorageUtil c = new CloudStorageUtil("Livro Lecheta");
		// Campo Email address criado no console.
		String accountId = "512212512205-v691296m6bkob35qmnq7bs4frkdsbg8g@developer.gserviceaccount.com";
		// Arquivo p12 baixado no console no momento de criar a chave.
		File p12File = new File("apicarros-e2886c094bf1.p12");
		// Conecta
		c.connect(accountId, p12File);
		// Upload
		System.out.println("Fazendo upload...");
		File file = new File("Ferrari_FF.png");
		String contentType = "image/png";
		String storageProjectId = "512212512205";
		StorageObject obj = c.upload(BUCKET_NAME, file, contentType,
				storageProjectId);
		System.out.println("Upload OK, objeto: " + obj.getName());
	}
}
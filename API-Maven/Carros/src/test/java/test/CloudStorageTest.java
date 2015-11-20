package test;
import java.io.File;
import java.util.List;
import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.StorageObject;

import br.com.livro.util.CloudStorageUtil;

public class CloudStorageTest {
	private static final String BUCKET_NAME = "apicarros";

	public static void main(String[] args) throws Exception {
		CloudStorageUtil c = new CloudStorageUtil("Livro Lecheta");
		// Campo Email address criado no console.
		String accountId = "512212512205-v691296m6bkob35qmnq7bs4frkdsbg8g@developer.gserviceaccount.com";
		// Arquivo p12 baixado no console no momento de criar a chave.
		File p12File = new File("apicarros-e2886c094bf1.p12");
		// Conecta
		c.connect(accountId, p12File);
		// Consulta o bucket
		Bucket bucket = c.getBucket(BUCKET_NAME);
		System.out.println("name: " + bucket.getName());
		System.out.println("location: " + bucket.getLocation());
		System.out.println("timeCreated: " + bucket.getTimeCreated());
		System.out.println("owner: " + bucket.getOwner());
		// Lista os arquivos
		List<StorageObject> files = c.getBucketFiles(bucket);
		for (StorageObject object : files) {
			System.out.println("> " + object.getName() + " ("
					+ object.getSize() + " bytes)");
		}
	}
}
package homepagewrecker.domain.service.download.impl;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import homepagewrecker.domain.service.download.Downloader;

@Service
public class DownloaderImpl implements Downloader {

	@Override
	public void download(URL url, Path destination) {
		try (final CloseableHttpClient client = HttpClients.createDefault();
				final CloseableHttpResponse response = client.execute(new HttpGet(url.toString()))) {
			Files.deleteIfExists(destination);

			final int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				final HttpEntity entity = response.getEntity();
				//                FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("r--rw-r--"));
				//                Path temp = Files.createTempFile(destination, null, ".html", attr);
				Files.write(destination, EntityUtils.toString(entity, StandardCharsets.UTF_8).getBytes(), StandardOpenOption.CREATE);
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		} catch (ClientProtocolException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}

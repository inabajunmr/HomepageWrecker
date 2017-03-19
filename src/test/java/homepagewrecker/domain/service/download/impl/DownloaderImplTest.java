package homepagewrecker.domain.service.download.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import homepagewrecker.domain.service.download.Downloader;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DownloaderImplTest {

	@Autowired
	private Downloader downloader;

	@Test
	public void test() throws MalformedURLException {
		downloader.download(new URL("http://google.com"), Paths.get("src/test/resources/test.html"));
	}

}

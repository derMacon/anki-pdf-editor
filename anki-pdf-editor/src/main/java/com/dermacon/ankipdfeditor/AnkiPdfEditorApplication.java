package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.ui.TerminalLauncher;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AnkiPdfEditorApplication.class, args);
		new TerminalLauncher().run();
//		InputStream in = AnkiPdfEditorApplication.class.getResourceAsStream("/.vimrc");
//		FileUtils.copyInputStreamToFile(in, new File("/home/silasUser/Documents/projects/codecademy_revenue_reactExample/temp.txt"));
////		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
////		System.out.println(reader.readLine());
	}
}

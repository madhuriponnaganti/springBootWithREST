package com.fileupload.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpload {

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "D://java//";

	public  String uploadFile(final MultipartFile file) throws Exception {

		try {

			// Get the file and save it somewhere
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

			Files.createDirectories(path.getParent());

			// Open the file, creating it if it doesn't exist
			try (final BufferedWriter out = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
					StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				
				String line;
				while ((line = bufferedReader.readLine()) != null)
				{
					out.write(line);
				}
				
				out.write(file.getInputStream().toString());
			return  "You successfully uploaded '" + file.getOriginalFilename() + "'";

			}
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}

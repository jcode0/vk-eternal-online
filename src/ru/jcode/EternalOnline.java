package ru.jcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class EternalOnline {
	
	public static void main(String[] args) {
		showLogo();
		System.out.println("Получение токена из файла 'token'");
		
		try {
			Scanner scanner = new Scanner(new File("token"));
			if(scanner.hasNextLine()) {
				new OnlineThread(scanner.nextLine());
			}else {
				System.err.println("Добавьте токен в файл 'token'");				
			}
			
			scanner.close();
		}catch (FileNotFoundException e) {
			System.err.println("Файл 'token' не найден");
			createDefaultConfig();
		}
	}
	
	private static void createDefaultConfig() {
		try {
			new File("token").createNewFile();
		} catch (IOException e) {
			System.err.println("Критическая ошибка >> " + e.getMessage());
		}
	}
	
	private static void showLogo() {
		System.out.println("\n"
						 + " _____ _                        _   _____       _ _            \n"
						 + "|  ___| |                      | | |  _  |     | (_)           \n"
						 + "| |__ | |_ ___ _ __ _ __   __ _| | | | | |_ __ | |_ _ __   ___ \n"
						 + "|  __|| __/ _ \\ '__| '_ \\ / _` | | | | | | '_ \\| | | '_ \\ / _ \\\n"
						 + "| |___| ||  __/ |  | | | | (_| | | \\ \\_/ / | | | | | | | |  __/\n"
						 + "\\____/ \\__\\___|_|  |_| |_|\\__,_|_|  \\___/|_| |_|_|_|_| |_|\\___|\n\n"
						 + "	dev by jcode\n"
						 + "	version 1.5\n");
	}
}
package formatting.src;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class UtilWrites 
{
	private static final String PATH = "C:\\Users\\phgoodw\\Documents\\1.16.5 Mods\\SMC\\src\\main\\resources\\";
	private static final String FORMATTING = "formatting\\";
	
	private static final String ASSETS = "assets\\suprememc\\";
	private static final String ASSETS_FORMAT = FORMATTING + ASSETS;
	private static final String DATA = "data\\suprememc\\";
	private static final String DATA_FORMAT = FORMATTING + DATA;
	
	private static final String REPLACED_NAME = "name_of_item";
	private static final String REPLACED_NAME2 = "name_of_0item";
	
	public static void writeAssetTo(String name, String loc, String filename)
	{
		String destination = PATH + ASSETS + loc + "\\";
	    String oldContents = readTheFile(PATH + ASSETS_FORMAT + loc + "\\" + filename);
	    String newContents = oldContents.replace(REPLACED_NAME, name);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + name + ".json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + name + ".json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	}
	
	public static void writeAssetATo(String name, String altName, String loc, String filename)
	{
		String destination = PATH + ASSETS + loc + "\\";
	    String oldContents = readTheFile(PATH + ASSETS_FORMAT + loc + "\\" + filename);
	    String contents = oldContents.replace(REPLACED_NAME, name);
	    String newContents = contents.replace(REPLACED_NAME2, altName);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + name + ".json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + name + ".json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	}
	
	public static void writeAssetAITo(String name, String altName, String inclusion, String loc, String filename)
	{
		String destination = PATH + ASSETS + loc + "\\";
	    String oldContents = readTheFile(PATH + ASSETS_FORMAT + loc + "\\" + filename);
	    String contents = oldContents.replace(REPLACED_NAME, name);
	    String newContents = contents.replace(REPLACED_NAME2, altName);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + name + inclusion + ".json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + name + ".json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	}
	
	
	public static void writeAssetITo(String name, String inclusion, String loc, String filename)
	{
		String destination = PATH + ASSETS + loc + "\\";
	    String oldContents = readTheFile(PATH + ASSETS_FORMAT + loc + "\\" + filename);
	    String newContents = oldContents.replace(REPLACED_NAME, name);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + name + inclusion + ".json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + name + inclusion + ".json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	}
	
	public static void writeDataTo(String subfix, String loc, String filename)
	{
		String destination = PATH + DATA + loc + "\\";
	    String oldContents = readTheFile(PATH + DATA_FORMAT + loc + "\\" + filename);
	    String newContents = oldContents.replace(REPLACED_NAME, subfix);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + subfix + ".json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + subfix + ".json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	}
	
	public static void writeDataTo(String subfix, String subfix2, String loc, String filename)
	{
		String destination = PATH + DATA + loc + "\\";
	    String oldContents = readTheFile(PATH + DATA_FORMAT + loc + "\\" + filename);
	    String contents = oldContents.replace(REPLACED_NAME, subfix);
	    String newContents = contents.replace(REPLACED_NAME2, subfix2);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + subfix + ".json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + subfix + ".json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	    
	}
	
	public static void writeDataToStonecutter(String subfix, String subfix2, String loc, String filename)
	{
		String destination = PATH + DATA + loc + "\\";
	    String oldContents = readTheFile(PATH + DATA_FORMAT + loc + "\\" + filename);
	    String contents = oldContents.replace(REPLACED_NAME, subfix);
	    String newContents = contents.replace(REPLACED_NAME2, subfix2);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + subfix + "_from_" + subfix2 + "_stonecutting.json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + subfix + "_from_" + subfix2 + "_stonecutting.json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	    
	}
	
	public static void writeDataTo(String subfix, String subfix2, String inclusion, String loc, String filename)
	{
		String destination = PATH + DATA + loc + "\\";
	    String oldContents = readTheFile(PATH + DATA_FORMAT + loc + "\\" + filename);
	    String contents = oldContents.replace(REPLACED_NAME, subfix);
	    String newContents = contents.replace(REPLACED_NAME2, subfix2);
	    try
	    {
	        FileWriter myWriter = new FileWriter(destination + subfix + inclusion + ".json");
	        myWriter.write(newContents);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file: " + subfix + inclusion + ".json {" + loc + "}");
	    }
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	}
	
	
	
	private static String readTheFile(String filePath) 
	{
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
		{
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
}

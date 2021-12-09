package source.suprememc.util.strings;

public class StringUtil 
{
	public static String convertToName(String message) 
	{
		message = message.replace("_", " ");
	    char[] charArray = message.toCharArray();
	    boolean foundSpace = true;
	    for(int i = 0; i < charArray.length; i++) 
	    {
	    	if(Character.isLetter(charArray[i])) 
	    	{
	    		if(foundSpace) 
	    		{
	    			charArray[i] = Character.toUpperCase(charArray[i]);
	    			foundSpace = false;
	    		}
	    	}
	    	else foundSpace = true;
	    }
	    message = String.valueOf(charArray);
	    return message;
	}
}

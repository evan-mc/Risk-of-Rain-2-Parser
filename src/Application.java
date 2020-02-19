import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Application
{
    public static void main(String[] args)
    {
        try
        {
            File file = new File(args[0]);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("stat");

            for(int i = 0; i < 18; ++i)
            {
                Node curr = nodes.item(i);
                Node child = curr.getFirstChild();

                String elementName = curr.getAttributes().item(0).getNodeValue();
                elementName = parseString(elementName);

                if(i == 1) //converts the seconds into hours
                {
                    System.out.println("\n" + elementName + " (in hours): " + Double.parseDouble(child.getNodeValue()) /3600);
                }
                else
                {
                    System.out.println("\n" + elementName + ": " + child.getNodeValue());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String parseString(String input)
    {
        String[] arr = input.split("(?=\\p{Upper})");

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < arr.length-1; ++i)
        {
            builder.append(arr[i] + " ");
        }
        builder.append(arr[arr.length-1]);

        return builder.toString().toLowerCase();
    }
}

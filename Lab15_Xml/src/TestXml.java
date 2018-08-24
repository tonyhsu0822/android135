
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class TestXml {
    
    private String filename = "Books.xml";
    
    public TestXml(String[] args) {
        
    }
    
    public void run() {
        
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // set input with a reader
            parser.setInput(new FileReader(filename));
            
            //  first event ; if parser hasn't reached the end; next event
            for(int event = parser.next(); event != XmlPullParser.END_DOCUMENT; event = parser.next()) {
                switch (event) {
                    // start of a tag (<tag>)
                    case XmlPullParser.START_TAG:
                        System.out.print("<" + parser.getName() + " ");
                        for(int i = 0; i < parser.getAttributeCount(); i++){
                            System.out.print(parser.getAttributeName(i)
                                    + "=\"" + parser.getAttributeValue(i) + "\"");
                        }
                        System.out.println(">");
                        break;
                    // end of a tag (</tag>)
                    case XmlPullParser.END_TAG:
                        System.out.println("</" + parser.getName() + ">");
                        break;
                    // content of a tag (<>content</>)
                    // probably has something other (tab or whitespace)
                    case XmlPullParser.TEXT:
                        if(!parser.getText().trim().isEmpty())
                            System.out.println(parser.getText());
                        break;
                    default:
                        break;
                }
            }
            
        } catch (XmlPullParserException | IOException ex) {
            Logger.getLogger(TestXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

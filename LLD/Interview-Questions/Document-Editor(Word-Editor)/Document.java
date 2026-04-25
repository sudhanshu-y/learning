import java.util.ArrayList;
import java.util.List;

public class Document {
    
    private List<DocumentElement> docElements;

    public Document(){
        this.docElements = new ArrayList<>();
    }

    public void addElement(DocumentElement de){
        docElements.add(de);
    }

    public void removeElement(DocumentElement de){
        docElements.remove(de);
    }

    public void render(){
        System.out.println("Rendering: ");
        docElements.forEach(de -> de.render());
    }
}

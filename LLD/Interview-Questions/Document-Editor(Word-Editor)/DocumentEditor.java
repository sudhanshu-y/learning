public class DocumentEditor {
    
    private Document doc;
    private Persistance p;

    public DocumentEditor(Persistance p){
        this.doc = new Document();
        this.p = p;
    }

    public void addText(String text){
        doc.addElement(new TextElement(text));
    }

    public void addImage(String imgPath){
        doc.addElement(new ImageElement(imgPath));
    }

    public void render(){
        doc.render();
    }

    public void save(){
        p.save();
    }
}

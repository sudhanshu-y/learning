public class Client {
    public static void main(String[] args) {
        DocumentEditor de = new DocumentEditor(new SaveToDb());

        de.addText("Text line 1");
        de.addImage("/file/path/file.png");

        de.render();

        de.save();
    }
}

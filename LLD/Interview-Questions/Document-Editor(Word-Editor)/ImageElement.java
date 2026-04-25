public class ImageElement implements DocumentElement {

    private String imgPath;

    public ImageElement(String imgPath){
        this.imgPath = imgPath;
    }

    @Override
    public void render() {
        System.out.println("[Image]: " + imgPath);
    }
    
}

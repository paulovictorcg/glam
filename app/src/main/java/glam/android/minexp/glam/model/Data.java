package glam.android.minexp.glam.model;

public class Data {
    private int[] resources;
    private String[] texts;

    public Data(String[] texts, int[] resources) {
        this.texts = texts;
        this.resources = resources;
    }

    public String[] getTexts() {
        return this.texts;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public int[] getResources() {
        return this.resources;
    }

    public void setResources(int[] resources) {
        this.resources = resources;
    }
}

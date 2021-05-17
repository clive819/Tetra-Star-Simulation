package ui;

public class TestUIClient {

    public static void main(String[] args) {
        TetraUI ui = new TetraUI();
        TestBackgroundRenderable b = new TestBackgroundRenderable();
        ui.mainUIPanel.drawingPanel.addRenderable(b);
        TestForegroundRenderable f = new TestForegroundRenderable();
        ui.mainUIPanel.drawingPanel.addRenderable(f);

        int numCols = 10;
        int numRows = 6;

        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {
                TestTFaceRenderable t = new TestTFaceRenderable(x, y, numCols, numRows);
                ui.mainUIPanel.drawingPanel.addRenderable(t);
            }
        }
    }
}

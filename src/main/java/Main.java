import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        final int width = 160;
        final int height = 44;

        char[] buffer = new char[width * height];
        float[] zBuffer = new float[width * height];
        Arrays.fill(buffer, '.');
        Arrays.fill(zBuffer, 0);

        CubeRenderer renderer = new CubeRenderer(width, height, 100, 40, 0.6f);
        float A = 0, B = 0, C = 0;

        while (true) {
            Arrays.fill(buffer, '.');
            Arrays.fill(zBuffer, 0);

            renderer.renderCube(20, -40, buffer, zBuffer, A, B, C, '@');
            renderer.renderCube(10, 10, buffer, zBuffer, A, B, C, '$');
            renderer.renderCube(5, 40, buffer, zBuffer, A, B, C, '#');

            System.out.print("\033[H");
            for (int k = 0; k < width * height; k++) {
                System.out.print(buffer[k]);
                if (k % width == width - 1)
                    System.out.println();
            }

            A += 0.05;
            B += 0.05;
            C += 0.01;

            Utilities.sleep(16); // 16 ms para simular 60 FPS
        }
    }
}

public class CubeRenderer {
    private final int width;
    private final int height;
    private final int distanceFromCam;
    private final float K1;
    private final float incrementSpeed;

    public CubeRenderer(int width, int height, int distanceFromCam, float K1, float incrementSpeed) {
        this.width = width;
        this.height = height;
        this.distanceFromCam = distanceFromCam;
        this.K1 = K1;
        this.incrementSpeed = incrementSpeed;
    }

    private float calculateX(float i, float j, float k, float A, float B, float C) {
        return j * (float) Math.sin(A) * (float) Math.sin(B) * (float) Math.cos(C) - k * (float) Math.cos(A) * (float) Math.sin(B) * (float) Math.cos(C) +
               j * (float) Math.cos(A) * (float) Math.sin(C) + k * (float) Math.sin(A) * (float) Math.sin(C) + i * (float) Math.cos(B) * (float) Math.cos(C);
    }

    private float calculateY(float i, float j, float k, float A, float B, float C) {
        return j * (float) Math.cos(A) * (float) Math.cos(C) + k * (float) Math.sin(A) * (float) Math.cos(C) -
               j * (float) Math.sin(A) * (float) Math.sin(B) * (float) Math.sin(C) + k * (float) Math.cos(A) * (float) Math.sin(B) * (float) Math.sin(C) -
               i * (float) Math.cos(B) * (float) Math.sin(C);
    }

    private float calculateZ(float i, float j, float k, float A, float B) {
        return k * (float) Math.cos(A) * (float) Math.cos(B) - j * (float) Math.sin(A) * (float) Math.cos(B) + i * (float) Math.sin(B);
    }

    public void renderCube(float cubeWidth, float horizontalOffset, char[] buffer, float[] zBuffer, float A, float B, float C, char ch) {
        for (float cubeX = -cubeWidth; cubeX < cubeWidth; cubeX += incrementSpeed) {
            for (float cubeY = -cubeWidth; cubeY < cubeWidth; cubeY += incrementSpeed) {
                calculateForSurface(cubeX, cubeY, -cubeWidth, buffer, zBuffer, A, B, C, ch, horizontalOffset);
                calculateForSurface(cubeWidth, cubeY, cubeX, buffer, zBuffer, A, B, C, ch, horizontalOffset);
                calculateForSurface(-cubeWidth, cubeY, -cubeX, buffer, zBuffer, A, B, C, ch, horizontalOffset);
                calculateForSurface(-cubeX, cubeY, cubeWidth, buffer, zBuffer, A, B, C, ch, horizontalOffset);
                calculateForSurface(cubeX, -cubeWidth, -cubeY, buffer, zBuffer, A, B, C, ch, horizontalOffset);
                calculateForSurface(cubeX, cubeWidth, cubeY, buffer, zBuffer, A, B, C, ch, horizontalOffset);
            }
        }
    }

    private void calculateForSurface(float cubeX, float cubeY, float cubeZ, char[] buffer, float[] zBuffer, float A, float B, float C, char ch, float horizontalOffset) {
        float x = calculateX(cubeX, cubeY, cubeZ, A, B, C);
        float y = calculateY(cubeX, cubeY, cubeZ, A, B, C);
        float z = calculateZ(cubeX, cubeY, cubeZ, A, B) + distanceFromCam;
        float ooz = 1 / z;
        int xp = (int) (width / 2 + horizontalOffset + K1 * ooz * x * 2);
        int yp = (int) (height / 2 + K1 * ooz * y);
        int idx = xp + yp * width;
        if (idx >= 0 && idx < width * height && ooz > zBuffer[idx]) {
            zBuffer[idx] = ooz;
            buffer[idx] = ch;
        }
    }
}

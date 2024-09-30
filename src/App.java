import processing.core.*;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");

    }

    int u = 0;
    int car = 3;
    int x = 300;
    int y = 50;
    int speed = 9;
    

    public void setup() {
        background(200, 90, 100);

    }

    public void settings() {
        size(600, 600);
    }

    public void draw() {
        background(200, 90, 100);
        fill(200, 200);
        rect(u, height / 3 , 140, 70);

        u += car;
        if (u > width) {
            u = -5;
        }
        fill(255, 255, 0);
        ellipse(x, y, 50, 50);

        float distance = dist(x, y, u, height / 3);
        fill(0);
        textSize(16);
        text("Distance: " + nf(distance, 0, 2), 10, height - 10);
        }
    

    public void keyPressed() {
        if (keyCode == LEFT) {
          x -= speed;
        } else if (keyCode == RIGHT) {
          x += speed;
        } else if (keyCode == UP) {
          y -= speed;
        } else if (keyCode == DOWN) {
          y += speed;
        }
    }
}

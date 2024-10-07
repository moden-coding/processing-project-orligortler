import processing.core.*;

public class App extends PApplet {
  public static void main(String[] args) {
    PApplet.main("App");
  }

  int u = 0;
  int carY1 = 200;
  int car = 3;

  int v = 0;
  int carY2 = 500;
  int car2 = 2;

  int b = 0;
  int carY3 = 350;
  int car3 = -2;

  int x = 300;
  int y = 50;
  int speed = 9;
  boolean isDead = false;
  int lives = 3;
  int level = 1;

  public void setup() {
    background(152, 251, 152);
  }

  public void settings() {
    size(600, 600);
  }

  public void restart() {
    x = 300;
    y = 50;
    car = 3;
    car2 = 2;
  }

  public void draw() {
    if (isDead) {
      println("You died ");
      fill(255, 0, 0);
      textSize(52);
      text("You Died", width / 2 - 80, height / 2);
      textSize(40);
      text("press enter to restart", width / 2 - 160, height / 2 + 80);
      restart();
      level = 1;
    } else {
      gamePlaying();
      println("Level" + level);
      fill(255, 0, 0);
      textSize(30);
      text("Level " + level, width - 120, 40);
    }
    drawHearts();
  }

  public void hitByCar() {
    lives -= 1;
    x = 300;
    y = 50;
    if (lives == 0) {
      isDead = true;
    }
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
    } else if (keyCode == ENTER) {
      isDead = false;
      lives = 3;
    }
  }

  public void gamePlaying() {
    background(152, 251, 152);

    fill(200, 200);
    rect(u, carY1, 140, 70);
    u += car;
    if (u > width) {
      u = -5;
    }

    fill(200, 200);
    rect(v, carY2, 140, 70);
    v += car2;
    if (v > width) {
      v = -5;
    }

    fill(255, 255, 0);
    ellipse(x, y, 50, 50);

    processCars();
    gotToBottom();

    if (level > 5) {
      nextPartOfGame();
    }
  }

  public boolean isHitByCar(int circX, int circY, int carX, int carY) {
    float distanceTopLeft = dist(circX, circY, carX, carY);
    float distanceTopRight = dist(circX, circY, carX + 140, carY);
    float distanceBottomLeft = dist(circX, circY, carX, carY + 70);
    float distanceBottomRight = dist(circX, circY, carX + 140, carY + 70);
    float distanceBottomMiddle = dist(circX, circY, carX + 70, carY + 70);
    float distanceTopMiddle = dist(circX, circY, carX + 70, carY);
    if (distanceTopLeft < 25 || distanceTopRight < 25 ||
        distanceBottomLeft < 25 || distanceBottomRight < 25 ||
        distanceBottomMiddle < 25 || distanceTopMiddle < 25) {
      return true;
    }
    return false;
  }

  public void processCars() {

    if (isHitByCar(x, y, u, carY1)) {
      hitByCar();
    }

    if (isHitByCar(x, y, v, carY2)) {
      hitByCar();
    }

  }

  public void drawHearts() {
    int xHeartPlacmet = 100;
    for (int i = 0; i < lives; i++) {
      drawHeart(xHeartPlacmet, 20, 35);
      xHeartPlacmet += 50;
    }

  }

  public void drawHeart(float heartX, float heartY, float size) {
    noStroke();
    float radius = size / 2;
    fill(255, 0, 0);
    arc(heartX - radius / 2, heartY, radius, radius, PI, TWO_PI);
    arc(heartX + radius / 2, heartY, radius, radius, PI, TWO_PI);
    triangle(heartX - radius, heartY, heartX + radius, heartY, heartX, heartY + radius * 1.5f);

  }

  public void gotToBottom() {
    if (y > 600) {
      lives += 1;
      x = 300;
      y = 50;
      car += 1.5;
      car2 += 1.5;
      level += 1;
      car3 += 1;
    }

  }

  public void nextPartOfGame() {
    restart();

    fill(200, 200);
    rect(b, carY3, 140, 70);
    b += car3;
    if (b > width) {
      b = -5;
    }
    if (isHitByCar(x, y, v, carY3)) {
      hitByCar();

    }
  }
}

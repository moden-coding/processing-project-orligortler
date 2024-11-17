import processing.core.*;

public class App extends PApplet {
  public static void main(String[] args) {
    PApplet.main("App");
  }

  int u = 0;
  int carY1 = 200;
  int carspeed = 4;

  int v = 0;
  int carY2 = 600;
  int carspeed2 = 3;

  int b = 800;
  int carY3 = 400;
  int carspeed3 = -3;

  int x = 400;
  int y = 50;
  int speed = 13;
  int lives = 3;
  int level = 1;
  int highscore = 0;

  int gameState = 0; // 0 = start screen, 1 = playing
  int buttonX = 300, buttonY = 400, buttonW = 200, buttonH = 80; // Button dimensions

  public void setup() {
    background(152, 251, 152);
  }

  public void settings() {
    size(800, 800);
  }

  public void restart() {
    x = 400;
    y = 50;
    carspeed = 4; // Reset car 1 speed
    carspeed2 = 3; // Reset car 2 speed
    carspeed3 = -3; // Reset car 3 speed
  }

  public void draw() {
    if (gameState == 0) {
      birthScreen();
    } else if (gameState == 1) {
      playScreen();
    } else if (gameState == 2) {
      deathScreen();
    }
  }

  public void hitByCar() {
    lives -= 1;
    x = 400;
    y = 50;
    if (lives == 0) {
      gameState = 2;
    }
  }

  // cheat coades for testing
  public void keyPressed() {

    if (key == 'l') {
      level++;
      carspeed += 2;
      carspeed2 += 2;
      carspeed3 -= 2;
    }

    if (keyCode == LEFT) {
      x -= speed;
    } else if (keyCode == RIGHT) {
      x += speed;
    } else if (keyCode == UP) {
      y -= speed;
    } else if (keyCode == DOWN) {
      y += speed;
    } else if (keyCode == ENTER) {
      lives = 3;
      gameState = 0;
      restart();
    }
  }

  public void birthScreen() {
    // Start screen
    background(255, 182, 193); // baby pink background
    textAlign(CENTER);
    fill(20);
    textSize(30);
    text("Get to the bottom and dont get hit by the moveing blocks!", width / 2, 200);

    if (highscore > 0) {
      fill(20);
      textSize(40);
      text("Highscore: " + highscore, width / 2, 600);
    }

    // Border on start button
    stroke(204, 0, 102); // Magenta border color
    strokeWeight(9); // Border thickness
    rect(buttonX, buttonY, buttonW, buttonH);

    // Draw the start button
    noStroke();
    fill(255, 105, 180);
    rect(buttonX, buttonY, buttonW, buttonH); // Draw button
    fill(255);
    textSize(42);
    text("Start Game", buttonX + buttonW / 2, buttonY + buttonH / 2 + 10); // Button name

  }

  public void playScreen() {
    gamePlaying();
    // println("Level" + level);
    fill(255, 0, 0);
    textSize(30);
    text("Level " + level, width - 120, 40);
    drawHearts();
  }

  public void deathScreen() {
    println("You died ");
    fill(255, 0, 0);
    textSize(52);
    text("You Died", width / 2, height / 2);
    textSize(40);
    text("press enter to restart", width / 2, height / 2 + 80);
    if (level >= highscore) {
      highscore = level; // Update the highscore
      textSize(32);
      text("Your new highscore is: " + highscore, width / 2, height / 2 + 130);
    } else {
      textSize(32);
      text("So close to a new highscore! ", width / 2, height / 2 + 130);
    }
  }

  // how the boxes work
  public void gamePlaying() {
    background(152, 251, 152);
    drawCars();

    fill(255, 255, 0);
    ellipse(x, y, 50, 50);

    processCars(); // to see if the circle gets hit
    gotToBottom(); // if you beat the level


  }
  public void drawCars(){
    fill(200, 200);
    rect(u, carY1, 140, 70);
    u += carspeed;
    if (u > width) {
      u = -5;
    }

    fill(200, 200);
    rect(v, carY2, 140, 70);
    v += carspeed2;
    if (v > width) {
      v = -5;
    }

    if (level > 5) {
      fill(200, 200);
    rect(b, carY3, 140, 70);
    b += carspeed3;
    if (b <= 0) {
      b = 805;
    }
    }
  }

  // mesure the distance between car and points on square
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
    if (level > 5) {
      if (isHitByCar(x, y, b, carY3)) {
        hitByCar();
      }  
    }
  }

  // adding hearts and lives
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
    if (y > 800) {
      if (lives < 5) {
        lives += 1;
      }
      x = 400;
      y = 50;
      carspeed += 2;
      carspeed2 += 2;
      level += 1;
      if (level == 6) {
        restart();
      }
      if (level > 5) {
        carspeed3 -= 2;
      }
      if (level > highscore) {
        highscore = level;
      }
    }
  }


  public void mousePressed() {
    if (gameState == 0) {
      // Check if the mouse is over the play button
      if (mouseX > buttonX && mouseX < buttonX + buttonW && mouseY > buttonY && mouseY < buttonY + buttonH) {
        gameState = 1;
        level = 1; // Start the game
      }
    }
  }
}